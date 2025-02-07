package vttp.batch5.paf.day29.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestControllerAdvice
public class IllegalAccessErrorHandler {

   @ExceptionHandler(
      exception = {IllegalAccessError.class},
      produces = MediaType.APPLICATION_JSON_VALUE
   )
   public ResponseEntity<String> handleIllegalAccess(IllegalAccessError ex) {
      JsonObject payload = Json.createObjectBuilder()
         .add("message", ex.getMessage())
         .build();
      return ResponseEntity.status(403).body(payload.toString());
   }
}
