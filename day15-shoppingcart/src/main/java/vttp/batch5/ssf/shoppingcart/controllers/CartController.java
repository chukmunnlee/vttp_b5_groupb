package vttp.batch5.ssf.shoppingcart.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import vttp.batch5.ssf.shoppingcart.Utils;
import vttp.batch5.ssf.shoppingcart.models.Cart;
import vttp.batch5.ssf.shoppingcart.models.LineItem;
import vttp.batch5.ssf.shoppingcart.models.ShoppingSession;
import vttp.batch5.ssf.shoppingcart.services.CartService;

@Controller
@RequestMapping
public class CartController {

	@Autowired
	private CartService cartSvc;

	@PostMapping("/cart")
	public ModelAndView postCart(@ModelAttribute LineItem lineItem, HttpSession sess) {

		ModelAndView mav = new ModelAndView();

		ShoppingSession shoppingSess = ShoppingSession.get(sess);
		Cart cart = shoppingSess.getCart();

		cart.addContent(lineItem);

		mav.addObject(Utils.ATTR_NAME, shoppingSess.getName());
		mav.addObject(Utils.ATTR_CART_ID, cart.getCartId());
		mav.addObject(Utils.ATTR_CART, cart);
		mav.addObject(Utils.ATTR_LINE_ITEM, new LineItem());

		return mav;
	}

	@GetMapping("/carts")
	public ModelAndView getCarts(@RequestParam String name, HttpSession sess) {

		Utils.initializeSession(name, sess);

		List<String> carts = cartSvc.getCarts(name);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("cart-list");
		mav.addObject(Utils.ATTR_CART_IDS, carts);
		mav.addObject(Utils.ATTR_NAME, name);
		mav.setStatus(HttpStatusCode.valueOf(200));

		return mav;
	}

	@GetMapping("/cart/{cartId}")
	public ModelAndView getCart(@PathVariable String cartId, HttpSession sess, Model model) {

		ModelAndView mav = new ModelAndView();
		ShoppingSession shoppingSess = ShoppingSession.get(sess);
		Optional<Cart> opt = cartSvc.getCartById(cartId);

		if (opt.isEmpty()) {
			// Do something
		}

		Cart cart = opt.get();
		shoppingSess.setCart(cart);

		mav.setViewName("cart");

		mav.addObject(Utils.ATTR_NAME, shoppingSess.getName());
		mav.addObject(Utils.ATTR_CART_ID, cartId);
		mav.addObject(Utils.ATTR_CART, cart);
		mav.addObject(Utils.ATTR_LINE_ITEM, new LineItem());

		return mav;
	}
}
