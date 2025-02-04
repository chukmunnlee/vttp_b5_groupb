package vttp.batch5.paf.day28.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.stereotype.Repository;

@Repository
public class BGGRepository {

    @Autowired
    private MongoTemplate template;

    /*
    db.games.aggregate([
       { $match: { name: { $regex: 'twilight', $options: 'i'} } },
       { $project: {
             gid: 1, name: 1, url: 1, image: 1, ranking: 1,
             _id: 0
          }
       },
       {
          $lookup: {
             from: 'comments',
             foreignField: 'gid',
             localField: 'gid',
             as: 'comments',
             pipeline: [
                { $project: { _id: 0, user: 1, c_text: 1, rating: 1 } },
                { $sort: { rating: 1 } },
                { $limit: 5 }
             ]
          }
       },
       { $sort: { ranking: 1 } },
    ])
    */
    public List<Document> findGamesAndComments(String name) {

        // Filter the name
        Criteria criteria = Criteria.where("name")
            .regex(name, "i");
        MatchOperation filterGamesByName = Aggregation.match(criteria);

        // Project fields from games
        ProjectionOperation projectFields = Aggregation.project("gid", "name", "ranking", "url", "image")
                .andExclude("_id");

        // Lookup the comments of a game
        ProjectionOperation projectComments = Aggregation.project("user", "c_text", "rating")
            .andExclude("_id");
        SortOperation sortCommentsByRating = Aggregation.sort(Sort.Direction.DESC, "rating");
        LimitOperation top5Comments = Aggregation.limit(5);

        LookupOperation joinCommentsByGid = LookupOperation.newLookup()
            .from("comments")
            .localField("gid").foreignField("gid")
            .pipeline(projectComments, sortCommentsByRating, top5Comments)
            .as("comments");

        // Sort the games by ranking
        SortOperation sortGamesByRanking = Aggregation.sort(Sort.Direction.ASC, "ranking");

        // Create the pipeline
        Aggregation pipeline = Aggregation.newAggregation(
                filterGamesByName, projectFields, joinCommentsByGid, sortGamesByRanking);

        return template.aggregate(pipeline, "games", Document.class).getMappedResults();
    }


}
