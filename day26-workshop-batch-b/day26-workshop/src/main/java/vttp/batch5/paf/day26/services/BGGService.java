package vttp.batch5.paf.day26.services;

import static vttp.batch5.paf.day26.repositories.Constants.F_GID;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.JsonObject;
import vttp.batch5.paf.day26.models.Game;
import vttp.batch5.paf.day26.repositories.CommentsRepository;
import vttp.batch5.paf.day26.repositories.GamesRepository;

@Service
public class BGGService {

    @Autowired
    private GamesRepository gamesRepo;

    @Autowired
    private CommentsRepository commentsRepo;

    public Optional<Game> findGameByName(String name) {

        Optional<JsonObject> opt = gamesRepo.findBoardgameByName(name);
        if (opt.isEmpty())
            return Optional.empty();

        JsonObject game = opt.get();
        int gid = game.getInt(F_GID);

        List<JsonObject> comments = commentsRepo.getCommentsByGid(gid);

        return Optional.of(Game.toGame(game, comments));
    }

}
