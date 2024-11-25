package vttp.batch5.ssf.day16.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vttp.batch5.ssf.day16.models.SearchParams;
import vttp.batch5.ssf.day16.services.GiphyService;

@Controller
@RequestMapping
public class GiphyController {

   @Autowired
   private GiphyService giphySvc;

   @GetMapping("/search")
   public ModelAndView getSearch(@RequestParam MultiValueMap<String, String> queryParams) {

      SearchParams params = new SearchParams(
         queryParams.getFirst("query"),
         Integer.parseInt(queryParams.getFirst("limit")),
         queryParams.getFirst("rating"));

      List<String> images = giphySvc.search(params);

      ModelAndView mav = new ModelAndView();

      mav.setViewName("results");
      mav.addObject("query", params.query());
      mav.addObject("images", images);

      return mav;
   }
   
}
