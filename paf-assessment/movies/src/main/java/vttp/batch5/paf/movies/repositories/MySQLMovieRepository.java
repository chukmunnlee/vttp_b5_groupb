package vttp.batch5.paf.movies.repositories;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.sql.RowSet;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.batch5.paf.movies.models.MovieStats;

@Repository
public class MySQLMovieRepository {

  public static final String SQL_COUNT_RECORDS = "select count(*) as cnt from imdb";
  public static final String SQL_INSERT_IMDB_RECORD = """
    insert into imdb(imdb_id, vote_average, vote_count, release_date, revenues, budget, runtime)
      values (?, ?, ?, ?, ?, ?, ?)
  """;
  public static final String SQL_SELECT_MOVIES_BY_ID = """
    select count(*) as movies_count, sum(revenues) as total_revenue, sum(budget) as total_budget
    from imdb where imdb_id in (%s) 
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
    public Optional<Document> findMoviesByIds(List<String> imdbIds) {
    String placeholders = String.join(",", Collections.nCopies(imdbIds.size(), "?"));
    String sql = SQL_SELECT_MOVIES_BY_ID.formatted(placeholders);

    List<Document> results = new LinkedList<>();

    SqlRowSet rs = template.queryForRowSet(sql, imdbIds.toArray());

    if (!rs.next())
      return Optional.empty();

    Document doc = new Document();
    //select count(*) as movies_count, sum(revenues) as total_revenue, sum(budget) as total_budget
    doc.put("movies_count", rs.getInt("movies_count"));
    doc.put("total_revenue", rs.getDouble("total_revenue"));
    doc.put("total_budget", rs.getDouble("total_budget"));
    results.add(doc);

    return Optional.of(doc);

  }


}
