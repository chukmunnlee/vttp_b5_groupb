package vttp.batch5.paf.day29.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@Controller
@RequestMapping
public class RegistrationController {

   @GetMapping(path="/api/account/{acctId}")
   @ResponseBody
   public ResponseEntity<String> getAccount(@PathVariable String acctId) {

      if (!"abc123".equals(acctId.toLowerCase()))
         throw new IllegalAccessError("You cannot view %s balance".formatted(acctId));

      JsonObject payload = Json.createObjectBuilder()
            .add("acctId", acctId)
            .add("balance", 100)
            .build();

      return ResponseEntity.ok(payload.toString());
   }

   @PostMapping(path="/register")
   public ModelAndView postRegister( @RequestBody MultiValueMap<String, String> form) {

      String name = form.getFirst("name");
      if ("bob".equals(name.toLowerCase()))
         throw new IllegalArgumentException("Not is not available: %s".formatted("bob"));

      ModelAndView mav = new ModelAndView();
      mav.setViewName("registered");
      mav.addObject("name", name);
      return mav;
   }  
}
