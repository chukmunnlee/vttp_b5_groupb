package vttp.batch5.ssf.shoppingcart.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.LinkedList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.batch5.ssf.shoppingcart.models.Item;

@Controller
@RequestMapping
public class CartController {

	@GetMapping(path={"/", "/index.html"})
	public String getIndex(Model model) {
		// Initialize the all items to blank
		model.addAttribute("item", new Item());
		model.addAttribute("cartItems", "");
		model.addAttribute("cart", new LinkedList<Item>());
		return "cart";
	}

	@PostMapping(path="/cart")
	public String postCart(@RequestBody MultiValueMap<String, String> form
			, Model model) {

		Item item = getItem(form);

		List<Item> cart = deserailzeCart(form);
		cart.add(item);

		model.addAttribute("item", new Item());
		model.addAttribute("stringifyCart", serializeCart(cart));
		model.addAttribute("cart", cart);

		return "cart";
	}

	// Method to convert from string (CSV) cart to List<item>
	private List<Item> deserailzeCart(MultiValueMap<String, String> form) {

		String strCart = form.getFirst("stringifyCart");
		if ((null == strCart) || (strCart.trim().length() <= 0))
			return new LinkedList<>();

		List<Item> tmpCart = Arrays.stream(strCart.split(","))
				.map(item -> item.split(":"))
				.map(fields -> {
					Item item = new Item();
					item.setName(fields[0]);
					item.setQuantity(Integer.parseInt(fields[1]));
					return item; }) 
				.toList();
		
		// Stream returns an immutable list. 
		// Wrap it in a new list to make the list mutable
		List<Item> cart = new LinkedList<>();
		for (Item i: tmpCart)
			cart.add(i);
		return cart;
	}

	// Method to convert from List<Item> cart to string (CSV) 
	private String serializeCart(List<Item> cart) {
		return cart.stream()
			.map(item -> "%s:%d".formatted(item.getName(), item.getQuantity()))
			.collect(Collectors.joining(","));
	}

	private Item getItem(MultiValueMap<String, String> form) {
		Item item = new Item();
		item.setName(form.getFirst("name"));
		item.setQuantity(Integer.parseInt(form.getFirst("quantity")));
		return item;
	}
}
