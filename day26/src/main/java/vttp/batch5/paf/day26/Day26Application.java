package vttp.batch5.paf.day26;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.batch5.paf.day26.repositories.SeriesRepository;

@SpringBootApplication
public class Day26Application implements CommandLineRunner {

	@Autowired
	private SeriesRepository seriesRepo;

	public static void main(String[] args) {
		SpringApplication.run(Day26Application.class, args);
	}

	@Override
	public void run(String... args) {

		// List<Document> results = seriesRepo.findSeriesByName("ar");

		// for (Document d: results)
		// 	System.out.printf(">>>> %s\n\n", d.toJson());

		// System.out.printf(">>>>> results: %d\n", results.size());

		//List<String> results = seriesRepo.getAllGenres();
		List<String> results = seriesRepo.genresByCountry("canada");
		for (String g: results)
			System.out.printf(">>>> %s\n", g);

	}

}
