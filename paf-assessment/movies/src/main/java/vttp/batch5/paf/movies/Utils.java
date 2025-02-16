package vttp.batch5.paf.movies;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import jakarta.json.JsonObject;
import vttp.batch5.paf.movies.models.MovieStats;
import vttp.batch5.paf.movies.models.MovieDetails;

public class Utils {

  private static final SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

  public static boolean isGreaterThanYear(String strDate, int year) {
    try {
      Date date = YYYY_MM_DD.parse(strDate);
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      return calendar.get(Calendar.YEAR) >= year;
    } catch (Exception ex) {
      return false;
    }
  }

  public static long readLong(String field, JsonObject json) {
    if (!json.containsKey(field) || json.isNull(field))
      return 0L;

    return json.getJsonNumber(field).longValue();
  }

  public static float readFloat(String field, JsonObject json) {
    if (!json.containsKey(field) || json.isNull(field))
      return 0f;

    return (float)json.getJsonNumber(field).doubleValue();
  }

  public static Date readDate(String field, JsonObject json) {
    if (!json.containsKey(field) || json.isNull(field))
      return new Date();

    try {
      return YYYY_MM_DD.parse(json.getString(field));
    } catch (Exception ex) {
      return new Date();
    }
  }

  public static List<String> readList(String field, JsonObject json) {
    if (!json.containsKey(field) || json.isNull(field))
      return List.of();

    String[] terms = json.getString(field).split(",");
    return Arrays.asList(terms);
  }

  public static MovieStats toMovieStats(JsonObject json) {
    MovieStats stats = new MovieStats();
    stats.setBudget(readLong("budget", json));
    stats.setRuntime(json.getInt("runtime", 0));
    stats.setImdbId(json.getString("imdb_id"));
    stats.setRevenue(readLong("revenue", json));
    stats.setVoteCount(readFloat("vote_count", json));
    stats.setReleaseDate(readDate("release_date", json));
    stats.setVoteAverage(readFloat("vote_average", json));
    return stats;
  }

  public static MovieDetails toMovieDetails(JsonObject json) {
    MovieDetails stats = new MovieDetails();
    stats.setTitle(json.getString("title", ""));
    stats.setImdbId(json.getString("imdb_id", ""));
    stats.setImdbVotes(readLong("imdb_votes", json));
    stats.setTagline(json.getString("tagline", ""));
    stats.setOverview(json.getString("overview", ""));
    stats.setImdbRating(json.getInt("imdb_rating", 0));
    stats.setGenres(readList("genres", json));
    stats.setDirectors(readList("director", json));
    return stats;
  }
}
