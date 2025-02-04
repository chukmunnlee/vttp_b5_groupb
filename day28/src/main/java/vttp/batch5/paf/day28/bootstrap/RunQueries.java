package vttp.batch5.paf.day28.bootstrap;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import vttp.batch5.paf.day28.repositories.BreweriesRepository;

@Component
public class RunQueries implements CommandLineRunner {

   @Autowired
   private BreweriesRepository breweriesRepo;

   @Override
   public void run(String... args) {

      //List<Document> results = breweriesRepo.getBreweriesByCountry("germany");
      //List<Document> results = breweriesRepo.listBeersByStyle();
      List<Document> results = breweriesRepo.categorizeBeerByAlcoholVolume();

      for (Document d: results)
         System.out.printf(">>> %s\n\n", d);

      System.out.printf("Count: %d\n", results.size());

   }
   
}
