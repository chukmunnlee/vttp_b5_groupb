package vttp.batch5.paf.day26.repositories;

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

import java.util.List;
import java.util.Optional;

@Repository
public class GamesRepository {

    @Autowired
    private MongoTemplate template;

    // db.games.find({
    //   name: { $regex: 'a name', $options: 'i' }
    // })
    // .sort({ ranking: -1 })
    // .limit(1)
    // .projection({ _id: 0, gid: 1, name: 1, ranking: 1, url: 1, image: 1 })
    public Optional<JsonObject> findBoardgameByName(String name) {

        Criteria criteria = Criteria.where(F_NAME)
            .regex(name, "i");

        Query query = Query.query(criteria)
            .with(Sort.by(Sort.Direction.DESC, F_RANKING))
            .limit(1);
        query.fields()
            .exclude(F_ID)
            .include(F_GID, F_NAME, F_RANKING, F_URL, F_IMAGE);

        List<Document> result = template.find(query, Document.class, C_GAMES);
        if (result.isEmpty())
            return Optional.empty();

        Document doc = result.get(0);
        JsonObject json = Json.createObjectBuilder()
            .add(F_GID, doc.getInteger(F_GID))
            .add(F_NAME, doc.getString(F_NAME))
            .add(F_RANKING, doc.getInteger(F_RANKING))
            .add(F_URL, doc.getString(F_URL))
            .add(F_IMAGE, doc.getString(F_IMAGE))
            .build();

        return Optional.of(json);
    }

}
