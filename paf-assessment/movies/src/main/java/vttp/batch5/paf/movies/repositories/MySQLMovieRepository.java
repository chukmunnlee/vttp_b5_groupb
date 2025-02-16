package vttp.batch5.paf.movies.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vttp.batch5.paf.movies.models.MovieStats;

@Repository
public class MySQLMovieRepository {

  public static final String SQL_COUNT_RECORDS = "select count(*) as cnt from imdb";
  public static final String SQL_INSERT_IMDB_RECORD = """
    insert into imdb(imdb_id, vote_average, vote_count, release_date, revenues, budget, runtime)
      values (?, ?, ?, ?, ?, ?, ?)
  """;

  @Autowired
  private JdbcTemplate template;

  public int countRecords() {
    Integer count = template.queryForObject(SQL_COUNT_RECORDS, Integer.class);
    if (null != count)
      return count;
    return 0;
  }

  // TODO: Task 2.3
  // You can add any number of parameters and return any type from the method
  public int[] batchInsertMovies(List<MovieStats> stats) {

    List<Object[]> recs = stats.stream()
      .map(s -> {
        Object[] fields = new Object[7];
        fields[0] = s.getImdbId();
        fields[1] = s.getVoteAverage();
        fields[2] = s.getVoteCount();
        fields[3] = s.getReleaseDate();
        fields[4] = s.getRevenue();
        fields[5] = s.getBudget();
        fields[6] = s.getRuntime();
        return fields;
      })
      .toList();

    return template.batchUpdate(SQL_INSERT_IMDB_RECORD, recs);
  }

  // TODO: Task 3


}
