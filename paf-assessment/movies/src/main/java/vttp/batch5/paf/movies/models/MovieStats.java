package vttp.batch5.paf.movies.models;

import java.util.Date;

public class MovieStats {

  private String imdbId;
  private float voteCount;
  private float voteAverage;
  private Date releaseDate;
  private long revenue;
  private long budget;
  private int runtime;

  public String getImdbId() { return imdbId; }
  public void setImdbId(String imdbId) { this.imdbId = imdbId; }

  public float getVoteCount() { return voteCount; }
  public void setVoteCount(float voteCount) { this.voteCount = voteCount; }

  public float getVoteAverage() { return voteAverage; }
  public void setVoteAverage(float voteAverage) { this.voteAverage = voteAverage; }

  public Date getReleaseDate() { return releaseDate; }
  public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }

  public long getRevenue() { return revenue; }
  public void setRevenue(long revenue) { this.revenue = revenue; }

  public long getBudget() { return budget; }
  public void setBudget(long budget) { this.budget = budget; }

  public int getRuntime() { return runtime; }
  public void setRuntime(int runtime) { this.runtime = runtime; }

  @Override
  public String toString() {
      return "MovieStats{imdb_id=%s}".formatted(imdbId);
  }
}
