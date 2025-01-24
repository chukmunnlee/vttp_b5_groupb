package vttp.batch5.paf.day27.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Repository;

@Repository
public class CommentsRepository {

   @Autowired
   private MongoTemplate template;

   public List<Document> searchComments(String... terms) {

      TextCriteria criteria = TextCriteria.forDefaultLanguage()
            .matchingAny(terms);

      TextQuery query = (TextQuery)TextQuery.queryText(criteria)
         .sortByScore()
         .includeScore("weight")
         .limit(10);

      query.fields().include("c_text", "weight");

      return template.find(query, Document.class, "comments");
   }
   
}
