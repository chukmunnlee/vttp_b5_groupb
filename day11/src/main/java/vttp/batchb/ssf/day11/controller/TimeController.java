package vttp.batchb.ssf.day11.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


// make this a request handler
@Controller
// handles the /time resource
@RequestMapping(path = "/time") 
public class TimeController {

   @GetMapping("/gmt")
   public String getTimeGmt(Model model) {

      String currentTime = (new Date()).toString();

      model.addAttribute("currTime", "this is the GMT time: %s".formatted(currentTime));

      return "time";
   }
   

   // This method to handle GET /time
   // Inject the model into the handler
   @GetMapping()
   public String getTime(Model model) {
      // Generate some data for your view
      String currentTime = (new Date()).toString();
      
      // Bind the data to the model
      model.addAttribute("currTime", currentTime);

      // display the template/view
      return "time";
   }
   
}
