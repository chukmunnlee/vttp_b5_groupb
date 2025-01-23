package vttp.batch5.paf.day26.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonObject;

import static vttp.batch5.paf.day26.repositories.Constants.*;

@Repository
public class CommentsRepository {

    @Autowired
    private MongoTemplate template;

    //
    // db.comments.find({ gid: 123 })
    //      .sort({ rating: -1 })
    //      .limit(5)
    //      projection({ _id: 0, user: 1, rating: 1, c_text: 1 })
    public List<JsonObject> getCommentsByGid(int gid) {

        Criteria criteria = Criteria.where(F_GID).is(gid);

        Query query = Query.query(criteria)
            .with(Sort.by(Sort.Direction.DESC, F_RATING))
            .limit(5);
        query.fields()
            .include(F_USER, F_RATING, F_C_TEXT)
            .exclude(F_ID);

        return template.find(query, Document.class, C_COMMENTS)
            .stream()
            .map(doc ->
                Json.createObjectBuilder()
                    .add(F_USER, doc.getString(F_USER))
                    .add(F_RATING, doc.getInteger(F_RATING))
                    .add(F_TEXT, doc.getString(F_C_TEXT))
                    .build())
            .toList();
    }

}
