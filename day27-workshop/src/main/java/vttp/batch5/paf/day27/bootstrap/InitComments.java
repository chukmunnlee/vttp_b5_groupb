package vttp.batch5.paf.day27.bootstrap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;
import vttp.batch5.paf.day27.repositories.CommentsRepository;

@Component
public class InitComments implements CommandLineRunner {

    private final Logger logger = Logger.getLogger(InitComments.class.getName());

    @Autowired
    private CommentsRepository commentsRepo;

    @Override
    public void run(String... args) throws Exception {

        if ((args.length <= 0) || (!args[0].startsWith("--load=")))
            return;

        String[] terms = args[0].split("=");
        Path p = Paths.get(terms[1]);
        final String colName = p.getFileName().toString().split("\\.")[0];

        logger.info("### Dropping collection: %s".formatted(colName));
        commentsRepo.dropComments(colName);

        try (Reader r = new FileReader(p.toFile())) {
            BufferedReader br = new BufferedReader(r);

            logger.info("### Reading JSON file: %s".formatted(p.toString()));
            JsonReader jsonReader = Json.createReader(br);
            JsonArray arr = jsonReader.readArray();
            br.close();
            logger.info("### Procesing JSON documents: %d".formatted(arr.size()));
            arr.stream()
                .map(j -> Document.parse(j.toString()))
                .map(d ->  {
                    d.put("_id", d.getString("c_id"));
                    return d;
                })
                .forEach(d -> {
                    commentsRepo.insertComments(d, colName);
                });
        }

        logger.info("### Creating text index");
        commentsRepo.createTextIndex("c_text", colName);
    }

}
