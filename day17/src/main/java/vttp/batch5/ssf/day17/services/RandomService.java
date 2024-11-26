package vttp.batch5.ssf.day17.services;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class RandomService {

   private Random rnd = new SecureRandom();

   public int getRandom() {
      return this.getRandom(100);
   }
   public int getRandom(int bound) {
      return rnd.nextInt(bound);
   }
   
}
