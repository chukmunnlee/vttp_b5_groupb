package vttp.batch5.ssf.day11.controllers;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// GET / or GET /index.html
@RequestMapping
public class PictureOfTheDayController {

   @GetMapping(path={"/", "/index.html"})
   public String getIndex(Model model) {
      model.addAttribute("imageOfTheDay", getPictureOfTheDay());
      return "picture_of_the_day";
   }

   private String getPictureOfTheDay() {

      Random rnd = new SecureRandom();
      int idx = rnd.nextInt(Constants.PICTURES.length);
      return "/images/%s".formatted(Constants.PICTURES[idx]);

   }
   
}
