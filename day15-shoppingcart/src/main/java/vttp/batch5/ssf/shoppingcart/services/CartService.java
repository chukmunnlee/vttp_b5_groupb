package vttp.batch5.ssf.shoppingcart.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.batch5.ssf.shoppingcart.repositories.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepo;

	public List<String> getCarts(String name) {
		return new LinkedList<>(cartRepo.getCartsByName(name));
	}
}
