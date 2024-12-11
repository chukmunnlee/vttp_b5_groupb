package vttp.batch5.ssf.revision.services;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class TimeService {

   public String getTime() {
      return (new Date()).toString();
   }
   
}
