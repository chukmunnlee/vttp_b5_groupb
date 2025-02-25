package vttp.batch5.day35.server.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class BGGRepository {

    @Autowired
    private MongoTemplate template;

    public List<Document> search(String q, int count) {
        Criteria criteria = Criteria.where("name")
            .regex(q, "i");
        Query query = Query.query(criteria)
            .limit(count)
            .with(Sort.by(Sort.Direction.ASC, "name"));
        query.fields()
            .include("gid", "name")
            .exclude("_id");
        return template.find(query, Document.class, "games");
    }

}
