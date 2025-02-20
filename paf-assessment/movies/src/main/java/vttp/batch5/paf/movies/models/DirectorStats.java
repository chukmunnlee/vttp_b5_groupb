package vttp.batch5.paf.movies.models;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public record DirectorStats(String directorName,
    int moviesCount, double totalRevenue, double totalBudget) { 

  public JsonObject toJson() {
    return Json.createObjectBuilder()
      .add("director_name", directorName)
      .add("movies_count", moviesCount)
      .add("total_revenue", totalRevenue)
      .add("total_budget", totalBudget)
      // Too lazy to create a separate method for the report
      .add("director", directorName)
      .add("count", moviesCount)
      .add("revenue", totalRevenue)
      .add("budget", totalBudget)
      .build();
  }

  public static DirectorStats toDirectorStats(Document doc) {
    return new DirectorStats(
        doc.getString("director"), 
        doc.getInteger("movies_count"), 
        doc.getDouble("total_revenue"), 
        doc.getDouble("total_budget"));
  }
}
