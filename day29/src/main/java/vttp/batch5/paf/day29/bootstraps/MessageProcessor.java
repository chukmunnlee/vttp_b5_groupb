package vttp.batch5.paf.day29.bootstraps;

import java.time.Duration;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessor {

   @Autowired @Qualifier("myredis")
   private RedisTemplate<String, String> template;

   @Async
   public void startPoller() {

      // Create a polling thread
      final Runnable poller = () -> {

         final ListOperations<String, String> queue = template.opsForList();
         while (true) {
            Optional<String> opt = Optional.ofNullable(
               queue.rightPop("messages", Duration.ofSeconds(5)) // brpop
            );
            if (opt.isEmpty())
               continue;
            String data = opt.get();
            System.out.printf(">>>> DEQUEUED: [%s] %s\n", new Date(), data);
         }

      };

      // Submit messge poller to thread
      Executors.newSingleThreadExecutor().execute(poller);
   }
}
