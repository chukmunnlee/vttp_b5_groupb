package vttp.batch5.day35.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.JsonArray;
import vttp.batch5.day35.server.services.BGGService;

@Controller
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class BGGController {

    @Autowired
    private BGGService bggSvc;

    @GetMapping(path="/search")
    @ResponseBody
    public ResponseEntity<String> search(@RequestParam String q,
            @RequestParam(defaultValue = "10") int count) {
        JsonArray result = bggSvc.search(q, count);
        return ResponseEntity.ok(result.toString());
    }
}
