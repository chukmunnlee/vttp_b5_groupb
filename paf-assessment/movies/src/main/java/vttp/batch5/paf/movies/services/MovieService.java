package vttp.batch5.paf.movies.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp.batch5.paf.movies.models.MovieDetails;
import vttp.batch5.paf.movies.models.MovieStats;
import vttp.batch5.paf.movies.repositories.MySQLMovieRepository;
import vttp.batch5.paf.movies.repositories.MongoMovieRepository;

@Service
public class MovieService {

  private Logger logger = Logger.getLogger(MovieService.class.getName());

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
  public void getProlificDirectors() {
  }


  // TODO: Task 4
  // You may change the signature of this method by passing any number of parameters
  // and returning any type
  public void generatePDFReport() {

  }

}
