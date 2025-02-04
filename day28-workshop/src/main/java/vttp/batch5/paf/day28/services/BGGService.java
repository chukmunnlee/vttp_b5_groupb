package vttp.batch5.paf.day28.services;

import java.util.List;
import java.util.LinkedList;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.batch5.paf.day28.models.Comment;
import vttp.batch5.paf.day28.models.Game;
import vttp.batch5.paf.day28.repositories.BGGRepository;

@Service
public class BGGService {

    @Autowired
    private BGGRepository bggRepo;

    public List<Game> findGamesAndComments(String name) {

        List<Document> results = bggRepo.findGamesAndComments(name);
        List<Game> games = new LinkedList<>();
        for (Document gDoc: results) {
            Game game = new Game();
            game.setUrl(gDoc.getString("url"));
            game.setName(gDoc.getString("name"));
            game.setGameId(gDoc.getInteger("gid"));
            game.setImage(gDoc.getString("image"));
            game.setRanking(gDoc.getInteger("ranking"));
            for (Document cDoc: gDoc.getList("comments", Document.class)) {
                Comment comment = new Comment();
                comment.setText(cDoc.getString("c_text"));
                comment.setUser(cDoc.getString("user"));
                comment.setRating(cDoc.getInteger("rating"));
                game.addComment(comment);
            }
            games.add(game);
        }

        return games;
    }

}
