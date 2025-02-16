package vttp.batch5.paf.movies.repositories;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import vttp.batch5.paf.movies.models.MovieDetails;

@Repository
public class MongoMovieRepository {

  @Autowired
  private MongoTemplate template;

  public void resetDatabase() {
    template.dropCollection("imdb");
    template.dropCollection("errors");
  }

 // TODO: Task 2.3
 // You can add any number of parameters and return any type from the method
 // You can throw any checked exceptions from the method
 // Write the native Mongo query you implement in the method in the comments
 //
 //    db.imdb.insertMany([ {....}, {....} ])
 //
 public Collection<Document> batchInsertMovies(List<MovieDetails> details) {
   List<Document> docs = details.stream()
      .map(d -> {
        Document doc = new Document();
        doc.put("_id", d.getImdbId());
        doc.put("imdb_id", d.getImdbId());
        doc.put("title", d.getTitle());
        doc.put("overview", d.getOverview());
        doc.put("tagline", d.getTagline());
        doc.put("imdb_rating", d.getImdbRating());
        doc.put("imdb_votes", d.getImdbVotes());
        doc.put("directors", d.getDirectors().stream().toList());
        doc.put("genres", d.getGenres().stream().toList());
        return doc;
      })
      .toList();
      return template.insert(docs, "imdb");
 }

 // TODO: Task 2.4
 // You can add any number of parameters and return any type from the method
 // You can throw any checked exceptions from the method
 // Write the native Mongo query you implement in the method in the comments
 //
 //    native MongoDB query here
 //
 public void logError(List<String> imdbIds, String error) {

   Document doc = new Document();
   doc.put("imdb_ids", imdbIds);
   doc.put("error", error);
   doc.put("timestamp", new Date());

   template.insert(doc, "errors");
 }

 // TODO: Task 3
 // Write the native Mongo query you implement in the method in the comments
 //
 //    native MongoDB query here
 //


}
