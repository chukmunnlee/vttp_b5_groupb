package vttp.batch5.ssf.day17.controllers;

import java.util.Date;

import javax.print.attribute.standard.Media;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping
public class TimeController {

   // POST /customer
   // Content-Type: application/x-www-form-urlencoded
   // Accept: text/html
   @PostMapping(path="/customer", produces = MediaType.TEXT_HTML_VALUE
         , consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
   public ResponseEntity<String> postCustomerAsHTML (@RequestBody MultiValueMap<String, String> form) {

      System.out.printf(">>> form: %s\n", form);

      String html = "<h1> %s has been registered </h1>".formatted(form.getFirst("name"));
       
      return ResponseEntity.ok(html);
   }

   // POST /customer
   // Content-Type: application/json
   // Accept: application/json
   @PostMapping(path="/customer", produces = MediaType.APPLICATION_JSON_VALUE
         , consumes = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<String> postCustomer(
      @RequestBody String payload) {

      System.out.printf(">>> PAYLOAD: %s\n", payload);

      // 202 Accepted
      // Content-Type: application/json
      // X-MyHeader: abc123
      // \r\n
      // {}  
      return ResponseEntity.accepted()
            .header("X-MyHeader", "abc123")
            .body("{}");
   }

   // GET /time
   // Accept: text/plain
   @GetMapping(path="/time", produces = MediaType.TEXT_PLAIN_VALUE)
   public ResponseEntity<String> getTimeAsText() {

      String time = (new Date()).toString();

      return ResponseEntity.ok(time);

   }

   // GET /time
   // Accept: application/json
   @GetMapping(path="/time", produces = MediaType.APPLICATION_JSON_VALUE)
   //@GetMapping(path="/time", produces = "application/json")
   //public ResponseEntity<Time> getTime() {
   public ResponseEntity<String> getTime() {

      /*
       * { "time": "a time" }
       */
      JsonObject resp = Json.createObjectBuilder()
            .add("time", (new Date()).toString())
            .build();

      // 200 OK
      // Content-Type: application/json
      // { "time": "a time" }

      System.out.printf(">>> resp: %s\n", resp.toString());

      // return ResponseEntity.status(200)
      //       .contentType(MediaType.APPLICATION_JSON)
      //       .body(resp.toString());

      //return ResponseEntity.ok(resp);
      //return ResponseEntity.ok(new Time((new Date()).toString()));
      return ResponseEntity.ok(resp.toString());
   }
   
}
