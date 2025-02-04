package vttp.batch5.paf.day28.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.BucketOperation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;

@Repository
public class BreweriesRepository {

   @Autowired
   private MongoTemplate template;

   /*
    * db.breweries.aggregate([
    *    { $match: { country: { $regex: 'country', $options: 'i' } } },
    *    { $project: { _id: 0, name: 1, address1: 1, city: 1 } },
    *    { $group: { _id: '$city', breweries: { $push: { name: '$name', address: '$address1' } } },
    *    { $sort: { _id: 1 } }
    * ])
    */
   public List<Document> getBreweriesByCountry(String country) {

      // Create one or more pipeline stages 
      // Create a $match - MatchOperation
      Criteria criteria = Criteria.where("country")
            .regex(country, "i");
      MatchOperation filterByCountry = Aggregation.match(criteria);

      // Project name, address1, city
      ProjectionOperation projectDetails = Aggregation.project("name", "address1", "city")
            .andExclude("_id");

      // Group breweries by the city
      // { $group: { _id: '$city', breweries: { $push: '$name' } } }
      // GroupOperation byCity = Aggregation.group("city")
      //       .push("name").as("breweries")
      //       .count().as("count");
      // { $group: { _id: '$city', breweries: { $push: { name: '$name', address: '$address1' } } },
      GroupOperation byCity = Aggregation.group("city")
            .push(
               new BasicDBObject()
                  .append("name", "$name")
                  .append("address", "$address1")
            ).as("breweries")
            .count().as("count");

      // Sort the city
      SortOperation sortByCity = Aggregation.sort(Sort.Direction.ASC, "_id");

      // Assemble pipeline with stages
      Aggregation pipeline = Aggregation.newAggregation(
         filterByCountry, projectDetails, byCity, sortByCity);

      // Execute the pipeline
      AggregationResults<Document> results = template.aggregate(pipeline, "breweries", Document.class);

      return results.getMappedResults();
   }

   /*
    * db.styles.aggregate([
         {
            $lookup: {
               from: 'beers',
               foreignField: 'style_id',
               localField: 'id',
               as: 'beers',
               pipeline: [
                  { $project: { _id: 0, name: 1 } }
               ]
            }
         }
      ])
    */
   public List<Document> listBeersByStyle() {

      // Project the beer name only
      ProjectionOperation beerName = Aggregation.project("name")
            .andExclude("_id");

      // Lookup operation
      // LookupOperation lookupBeers = Aggregation.lookup("beers", "id", "style_id", "beers");
      LookupOperation lookupBeers = LookupOperation.newLookup()
            .from("beers")
            .localField("id").foreignField("style_id")
            .pipeline(beerName)
            .as("beers");

      // Create a pipeline
      Aggregation pipeline = Aggregation.newAggregation(lookupBeers);

      // Execute the pipeline
      return template.aggregate(pipeline, "styles", Document.class).getMappedResults();
   }

   /*
    * db.beers.aggregate([
         {
            $bucket: {
               groupBy: '$abv',
               boundaries: [ 0, 3, 5, 7, 9, 15 ],
               default: '>=15',
               output: {
                  beers: { $push: "$name" }
               }
            }
         }
      ])
    */
   public List<Document> categorizeBeerByAlcoholVolume() {

      BucketOperation alcoholByVolume = Aggregation.bucket("abv")
            .withBoundaries(0, 3, 5, 7, 9, 15)
            .withDefaultBucket(">=15")
            .andOutput("name").push().as("beers")
            .andOutputCount().as("count");

      Aggregation pipeline = Aggregation.newAggregation(alcoholByVolume);

      return template.aggregate(pipeline, "beers", Document.class).getMappedResults();
   }
   
}
