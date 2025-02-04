package vttp.batch5.paf.day28.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vttp.batch5.paf.day28.models.Game;
import vttp.batch5.paf.day28.services.BGGService;

@Controller
@RequestMapping
public class BGGController {

    @Autowired
    private BGGService bggSvc;

    @GetMapping(path="/search")
    public ModelAndView search(@RequestParam String q) {

        List<Game> games = bggSvc.findGamesAndComments(q);

        ModelAndView mav = new ModelAndView("search_result");
        mav.setStatus(HttpStatusCode.valueOf(200));
        mav.addObject("games", games);
        mav.addObject("q", q);
        return mav;
    }

}
