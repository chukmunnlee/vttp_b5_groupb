package vttp.batch5.ssf.shoppingcart.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.batch5.ssf.shoppingcart.Utils;
import vttp.batch5.ssf.shoppingcart.models.Cart;
import vttp.batch5.ssf.shoppingcart.repositories.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepo;

	public List<String> getCarts(String name) {
		return new LinkedList<>(cartRepo.getCartsByName(name));
	}

	public Optional<Cart> getCartById(String cartId) {
		if (Utils.VAL_NEW_CART.equals(cartId))
			return Optional.of(new Cart(cartId));
		return Optional.empty();
	}
}
