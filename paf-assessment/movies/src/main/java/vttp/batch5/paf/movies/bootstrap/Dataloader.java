package vttp.batch5.paf.movies.bootstrap;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp.batch5.paf.movies.models.MovieDetails;
import vttp.batch5.paf.movies.models.MovieStats;
import vttp.batch5.paf.movies.services.MovieService;

import static vttp.batch5.paf.movies.Utils.*;

@Component
public class Dataloader implements CommandLineRunner {

  public static final int BATCH_SIZE = 25;

  private Logger logger = Logger.getLogger(Dataloader.class.getName());

  @Value("${datafile}")
  private String dataFile;

  @Autowired
  private MovieService movieSvc;

  //TODO: Task 2

  @Override
  public void run(String... args) {

    if (!movieSvc.shouldLoad())
      return;

    movieSvc.resetDatabase();

    logger.info("Loading data file: %s".formatted(dataFile));

    try (InputStream is = new FileInputStream(dataFile)) {
      BufferedInputStream bis = new BufferedInputStream(is);
      ZipInputStream zis = new ZipInputStream(bis);

      ZipEntry ze = zis.getNextEntry();
      if (null == ze)
        return;

      ByteArrayInputStream bais = new ByteArrayInputStream(zis.readAllBytes());

      try (Reader reader = new InputStreamReader(bais)) {
        BufferedReader br = new BufferedReader(reader);

        int read = 0;
        int batchSize = 0;
        String line;
        List<MovieStats> stats = new LinkedList<>();
        List<MovieDetails> details = new LinkedList<>();

        while ((line = br.readLine()) != null) {
          JsonObject rec = Json.createReader(new StringReader(line)).readObject();
          if (!isGreaterThanYear(rec.getString("release_date", ""), 2018))
            continue;

          batchSize++;
          stats.add(toMovieStats(rec));
          details.add(toMovieDetails(rec));
          if ((batchSize % BATCH_SIZE) > 0)
            continue;

          read += BATCH_SIZE;

          movieSvc.batchInsert(stats, details);

          // Reset
          batchSize = 0;
          stats = new LinkedList<>();
          details = new LinkedList<>();
        }

        read += stats.size();
        if (!stats.isEmpty())
          movieSvc.batchInsert(stats, details);

        logger.info("Total document read: %d".formatted(read));
      }
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Loading data", ex);
    }

  }

}
