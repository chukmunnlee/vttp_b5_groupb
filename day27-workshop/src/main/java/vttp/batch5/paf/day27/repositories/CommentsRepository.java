package vttp.batch5.paf.day27.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Indexes;

@Repository
public class CommentsRepository {

    @Autowired
    private MongoTemplate template;

    public void dropComments(String colName) {
        template.dropCollection(colName);
    }

    public <T> T insertComments(T doc, String colName) {
        return template.insert(doc, colName);
    }

    public void createTextIndex(String fieldName, String colName) {
        MongoCollection<Document> col =  template.getCollection(colName);
        col.createIndex(Indexes.text(fieldName));
    }

}
