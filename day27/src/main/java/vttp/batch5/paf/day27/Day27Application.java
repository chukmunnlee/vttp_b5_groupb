package vttp.batch5.paf.day27;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.batch5.paf.day27.repositories.CommentsRepository;

@SpringBootApplication
public class Day27Application implements CommandLineRunner {

	@Autowired
	private CommentsRepository commentsRepo;

	public static void main(String[] args) {
		SpringApplication.run(Day27Application.class, args);
	}

	@Override
	public void run(String... args) {

		List<Document> results = commentsRepo.searchComments("love", "amazing", "excellent");

		for (Document d: results)
			System.out.printf(">>>> %s\n\n", d.toJson());

	}

}
