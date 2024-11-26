package vttp.batch5.ssf.day17.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.batch5.ssf.day17.models.Order;
import vttp.batch5.ssf.day17.repositories.OrderRepository;

@Service
public class OrderService {

   @Autowired
   private OrderRepository orderRepo;

   public String save(Order order) {
      String orderId = UUID.randomUUID().toString().substring(0, 8);

      orderRepo.save(orderId, order);

      return orderId;
   }


   
}
