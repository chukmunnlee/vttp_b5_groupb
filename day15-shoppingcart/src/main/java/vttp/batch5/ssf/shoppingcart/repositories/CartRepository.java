package vttp.batch5.ssf.shoppingcart.repositories;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepository {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public Set<String> getCartsByName(String name) {
		return redisTemplate.keys("%s_*".formatted(name));
	}
}
