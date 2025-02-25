package vttp.batch5.day35.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import vttp.batch5.day35.server.repositories.BGGRepository;

@Service
public class BGGService {

    @Autowired
    private BGGRepository bggRepo;

    public JsonArray search(String q, int count) {
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        bggRepo.search(q, count).stream()
            .map(doc -> {
                return Json.createObjectBuilder()
                    .add("gid", doc.getInteger("gid"))
                    .add("name", doc.getString("name"))
                    .build();
            })
            .forEach(arrBuilder::add);

        return arrBuilder.build();
    }
}
