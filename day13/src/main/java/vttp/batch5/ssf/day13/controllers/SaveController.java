package vttp.batch5.ssf.day13.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import vttp.batch5.ssf.day13.models.Registration;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping
public class SaveController {

   @PostMapping("/exit")
   public String postExit(HttpSession sess, Model model) {
       //TODO: process POST request
       List<Registration> regList = (List<Registration>)sess.getAttribute(RegistrationController.REG_LIST);

       System.out.printf(">>> reglist: %s\n", regList);

       // Destroy the3 session
       sess.invalidate();

       model.addAttribute("reg", new Registration());
       
       return "index";
   }
   
   
}
