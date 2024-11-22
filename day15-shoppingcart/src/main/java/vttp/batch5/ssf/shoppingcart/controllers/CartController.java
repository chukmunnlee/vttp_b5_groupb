package vttp.batch5.ssf.shoppingcart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import vttp.batch5.ssf.shoppingcart.services.CartService;

@Controller
@RequestMapping
public class CartController {

	@Autowired
	private CartService cartSvc;

	@GetMapping("/carts")
	public ModelAndView getCarts(@RequestParam String name, HttpSession sess) {

		List<String> carts = cartSvc.getCarts(name);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("cart-list");
		mav.addObject("carts", carts);
		mav.addObject("name", name);
		mav.setStatus(HttpStatusCode.valueOf(200));

		return mav;
	}
}
