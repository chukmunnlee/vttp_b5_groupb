package vttp.batch5.paf.day26.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ScrollPosition.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import static vttp.batch5.paf.day26.repositories.Constants.*;

import java.util.List;

@Repository
public class SeriesRepository {

   // Inject MongoTemplate
   @Autowired
   private MongoTemplate template;

   /*
    * db.series.distinct('genres')
    */
   public List<String> getAllGenres() {

      Query query = new Query();

      return template.findDistinct(query, F_GENRES, C_SERIES, String.class);

   }

   public List<String> genresByCountry(String country) {

      Criteria criteria = Criteria.where(F_NETWORK_COUNTRY_NAME)
            .regex(country, "i");

      Query query = Query.query(criteria);

      return template.findDistinct(query, F_GENRES, C_SERIES, String.class);

   }

   /*
      db.series.find({
         name: {
            $regex: 'a name',
            $options: 'i'
         }
    })
    */
    public List<Document> findSeriesByName(String name) {

      // Define the search criteria
      Criteria criteria_by_rating = Criteria.where(F_RATING_AVERAGE)
            .gte(8);
      Criteria criteria_by_name = Criteria.where(F_NAME)
            .regex(name, "i")
            .andOperator(criteria_by_rating);

      // Create the query based on the defined criteria
      Query query = Query.query(criteria_by_name)
            .with(
               Sort.by(Sort.Direction.DESC, F_RATING_AVERAGE)
            )
            .limit(5)
            .skip(5L);
      query.fields()
         .include(F_NAME, F_SUMMARY, F_IMAGE_ORIGINAL, F_RATING_AVERAGE)
         .exclude(F_ID);

      // Perform the search
      return template.find(query, Document.class, C_SERIES);

    }
   
}
