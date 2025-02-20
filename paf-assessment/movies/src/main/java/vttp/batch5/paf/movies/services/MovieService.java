package vttp.batch5.paf.movies.services;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.json.data.JsonDataSource;
import net.sf.jasperreports.pdf.JRPdfExporter;
import vttp.batch5.paf.movies.models.MovieDetails;
import vttp.batch5.paf.movies.models.DirectorStats;
import vttp.batch5.paf.movies.models.MovieStats;
import vttp.batch5.paf.movies.repositories.MySQLMovieRepository;
import vttp.batch5.paf.movies.repositories.MongoMovieRepository;

@Service
public class MovieService {

  private Logger logger = Logger.getLogger(MovieService.class.getName());

  @Value("${name}")
  private String name;

  @Value("${batch}")
  private String batch;

  @Autowired
  private MySQLMovieRepository mysqlRepo;

  @Autowired
  private MongoMovieRepository mongoRepo;

  public boolean shouldLoad() {
    return mysqlRepo.countRecords() <= 0;
  }

  public void resetDatabase() {
    mongoRepo.resetDatabase();
  }

  // TODO: Task 2
  // stats - MySQL
  // details - MongoDB
  public void batchInsert(List<MovieStats> stats, List<MovieDetails> details) {

    try {
      _batchInsert(stats, details);
    } catch (Exception ex) {
      logger.log(Level.WARNING, "Batch insert: %s".formatted(ex.getMessage()));
      // TODO: log error
      List<String> imdbIds = stats.stream()
          .map(s -> s.getImdbId())
          .toList();
      mongoRepo.logError(imdbIds, ex.getMessage());
    }
  }

  @Transactional
  private void _batchInsert(List<MovieStats> stats, List<MovieDetails> details) {
    mysqlRepo.batchInsertMovies(stats);
    mongoRepo.batchInsertMovies(details);
  }
  // TODO: Task 3
  // You may change the signature of this method by passing any number of parameters
  // and returning any type
  public List<DirectorStats> getProlificDirectors(int count) {
    List<Document> directors = mongoRepo.getTopNProlificDirectors(count);
    List<DirectorStats> stats = new LinkedList<>();
    for (Document doc: directors) {
      List<String> imdbIds = doc.getList("imdb_ids", String.class);
      Optional<Document> opt = mysqlRepo.findMoviesByIds(imdbIds);
      if (opt.isEmpty())
        continue;
      Document dir = opt.get();
      dir.put("director", doc.getString("_id"));
      stats.add(DirectorStats.toDirectorStats(dir));
    }
    return stats;
  }


  // TODO: Task 4
  // You may change the signature of this method by passing any number of parameters
  // and returning any type
  public byte[] generatePDFReport(int count) throws Exception {

    JsonObject reportRoot = Json.createObjectBuilder()
        .add("name", name)
        .add("batch", batch)
        .build();

    JsonArrayBuilder builder = Json.createArrayBuilder();
    getProlificDirectors(count).stream()
        .map(DirectorStats::toJson)
        .forEach(builder::add);

    JsonArray directors = builder.build();

    System.out.printf(">>>> directors: %s\n", directors);

    JsonDataSource reportDS = new JsonDataSource(
          new ByteArrayInputStream(reportRoot.toString().getBytes()));
    JsonDataSource directorDS = new JsonDataSource(
          new ByteArrayInputStream(directors.toString().getBytes()));

    Map<String, Object> params = new HashMap<>();
    params.put("DIRECTOR_TABLE_DATASET", directorDS);

    JasperReport report = (JasperReport)JRLoader.loadObject(new File("./data/director_movies_report.jasper"));

    JasperPrint print = JasperFillManager.fillReport(report, params, reportDS);

    return JasperExportManager.exportReportToPdf(print);

  }

}
