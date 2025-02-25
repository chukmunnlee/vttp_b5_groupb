package vttp.batch5.day35.server.controllers;

import java.util.Date;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@Controller
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationController {

    @PostMapping(path="/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> postRegister(@RequestBody String payload) {
        System.out.printf("GOT payload: %s\n", payload);
        String regId = UUID.randomUUID().toString().substring(0, 8);
        long timestamp = (new Date()).getTime();

        JsonObject resp = Json.createObjectBuilder()
            .add("regId", regId)
            .add("timestamp", timestamp)
            .build();

        System.out.printf(">>>> resp: %s\n", resp.toString());
        return ResponseEntity.ok(resp.toString());
    }

}
