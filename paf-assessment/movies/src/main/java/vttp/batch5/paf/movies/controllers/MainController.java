package vttp.batch5.paf.movies.controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp.batch5.paf.movies.models.DirectorStats;
import vttp.batch5.paf.movies.services.MovieService;

@Controller
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class MainController {

  private Logger logger = Logger.getLogger(MainController.class.getName());

  @Autowired
  private MovieService movieSvc;

  // TODO: Task 3
  @GetMapping(path="/summary")
  @ResponseBody
  public ResponseEntity<String> getSummary(@RequestParam(defaultValue = "5") int count) {
    JsonArrayBuilder builder = Json.createArrayBuilder();
    movieSvc.getProlificDirectors(count).stream()
        .map(DirectorStats::toJson)
        .forEach(builder::add);
    JsonArray results = builder.build();

    return ResponseEntity.ok(results.toString());
  }
   

  
  // TODO: Task 4
  @GetMapping(path="/summary/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
  @ResponseBody
  public ResponseEntity<byte[]> getSummaryAsPDFReport(@RequestParam(defaultValue = "5") int count) {
    try {
      byte[] report = movieSvc.generatePDFReport(count);
      return ResponseEntity.ok(report);
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Generating PDF", ex);
      JsonObject error = Json.createObjectBuilder()
          .add("error", ex.getMessage())
          .build();
      return ResponseEntity.status(HttpStatusCode.valueOf(500))
        .contentType(MediaType.APPLICATION_JSON)
        .body(error.toString().getBytes());
    }
  }


}
