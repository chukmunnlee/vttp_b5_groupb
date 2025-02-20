package vttp.batch5.paf.movies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.batch5.paf.movies.models.DirectorStats;
import vttp.batch5.paf.movies.services.MovieService;

@SpringBootApplication
public class MoviesApplication implements CommandLineRunner {

  @Autowired
  private MovieService movieSvc;

	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}

  @Override
  public void run(String... args) {
    //List<DirectorStats> stats = movieSvc.getProlificDirectors(5);
    //System.out.printf(">>> stats: %s\n", stats);
  }

}
