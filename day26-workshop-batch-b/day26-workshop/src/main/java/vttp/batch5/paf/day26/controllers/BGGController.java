package vttp.batch5.paf.day26.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp.batch5.paf.day26.models.Game;
import vttp.batch5.paf.day26.services.BGGService;

@Controller
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class BGGController {

    @Autowired
    private BGGService bggSvc;

    @GetMapping(path="/search")
    @ResponseBody
    public ResponseEntity<String> search(@RequestParam String q) {

        Optional<Game> opt = bggSvc.findGameByName(q);
        if (opt.isEmpty()) {
            JsonObject err = Json.createObjectBuilder()
                .add("message", "Cannot find any games with '%s'".formatted(q))
                .build();
            return ResponseEntity.status(404).body(err.toString());
        }

        Game game = opt.get();
        JsonObject resp = game.toJson();
        return ResponseEntity.ok(resp.toString());
    }

}
