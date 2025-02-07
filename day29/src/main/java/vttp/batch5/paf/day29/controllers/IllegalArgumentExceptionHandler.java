package vttp.batch5.paf.day29.controllers;

import java.util.Date;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
public class IllegalArgumentExceptionHandler {

   @ExceptionHandler(exception = {IllegalArgumentException.class})
   public ModelAndView handleException(IllegalArgumentException ex) {
      System.err.println(">>>>> In ExceptionHandler");
      ModelAndView mav = new ModelAndView("error");
      mav.addObject("error", "Handler by ExceptionHandler");
      mav.addObject("message", "The message occurs on %s".formatted(new Date().toString()));
      mav.setStatus(HttpStatusCode.valueOf(400));
      return mav;
   }
   
}
