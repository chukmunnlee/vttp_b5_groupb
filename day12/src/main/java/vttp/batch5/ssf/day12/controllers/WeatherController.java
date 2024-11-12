package vttp.batch5.ssf.day12.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/weather")
public class WeatherController {

   @GetMapping
   public String getWeather(@RequestParam(defaultValue = "metrics") String unit, Model model) {
      model.addAttribute("city", "singapore");
      model.addAttribute("unit", unit);
      return "weather";
   }
   

   // GET /weather/singapore
   @GetMapping("{city}")
   public String getWeather( 
         @PathVariable String city, @RequestParam(defaultValue = "metrics") String unit,
         Model model) {

      model.addAttribute("city", city);
      model.addAttribute("unit", unit);
      return "weather";
   }
   
   
}
