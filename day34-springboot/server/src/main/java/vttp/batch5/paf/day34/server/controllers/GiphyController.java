package vttp.batch5.paf.day34.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import vttp.batch5.paf.day34.server.services.GiphyService;

@Controller
@RequestMapping(path="/api", produces=MediaType.APPLICATION_JSON_VALUE)
public class GiphyController {

  @Autowired
  private GiphyService giphySvc;

  @GetMapping(path="/search")
  @ResponseBody
  @CrossOrigin(origins = "*")
  public ResponseEntity<String> search(@RequestParam String q, 
      @RequestParam(defaultValue = "pg") String rating, @RequestParam(defaultValue = "5") int limit) {

    JsonArrayBuilder builder = Json.createArrayBuilder();
    giphySvc.search(q, limit, rating)
        .stream()
        .forEach(builder::add);

    return ResponseEntity.ok(builder.build().toString());
  }
}
