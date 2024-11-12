package vttp.batch5.ssf.day12.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// static import
import static vttp.batch5.ssf.day12.controllers.Constants.*;

import java.util.List;

@Controller
@RequestMapping("/generate")
public class NumbersController {

   // GET /generate?name=fred&count=4
   @GetMapping
   public String getGenerate(@RequestParam MultiValueMap<String, String> form, Model model) {

      String name = form.getFirst(ATTR_NAME);
      int count = 0;
      List<String> values;

      // Check if we have list query string
      if (form.containsKey(ATTR_LIST)) {
         // convert the CSV -> List<int> -> List<string>
         values = toList(form.getFirst(ATTR_LIST))
            .stream()
            .map(val -> "/numbers/number%d.jpg".formatted(val))
            .toList();

         model.addAttribute(ATTR_VALUES, values);

         // We don't have the name query string, then just display the list of numbers
         if (!form.containsKey(ATTR_NAME))
            return "list_nums";

         // Set the number count of the list
         count = values.size();

      } else {

         // Process as before
         count = toInt(form.getFirst(ATTR_COUNT), 4);
         // int[ 0, 5, 2 ] -> String['/numbers/number0.jpg', '/numbers/number5.jpg', '/numbers/number2.jpg' ]
         values = generateRandom(count).stream()
               .map(val -> "/numbers/number%d.jpg".formatted(val))
               .toList();
      }

      model.addAttribute(ATTR_NAME, name);
      model.addAttribute(ATTR_COUNT, count);
      model.addAttribute(ATTR_VALUES, values);

      return "random_nums";
   }

}
