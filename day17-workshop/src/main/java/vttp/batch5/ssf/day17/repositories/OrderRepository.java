package vttp.batch5.ssf.day17.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import jakarta.json.JsonObject;
import vttp.batch5.ssf.day17.models.Order;

@Repository
public class OrderRepository {

   @Autowired @Qualifier("redis-0")
   private RedisTemplate<String, Object> template;

   // redis-cli
   // set orderId "{ .... }"
   public void save(String orderId, Order order) {

      JsonObject json = order.toJson();

      ValueOperations<String, Object> valueOps = template.opsForValue();
      valueOps.set(orderId, json.toString());

   }
   
}
