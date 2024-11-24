package vttp.batch5.ssf.shoppingcart.models;

import jakarta.servlet.http.HttpSession;
import vttp.batch5.ssf.shoppingcart.Utils;

public class ShoppingSession {

	private final String name;
	private final HttpSession sess;
	private Cart cart = null;

	public ShoppingSession(String name, HttpSession sess) {
		this.name = name;
		this.sess = sess;
		sess.setAttribute(Utils.ATTR_NAME, name);
	}

	public void setCart(Cart cart) {
		this.cart = cart;
		sess.setAttribute(Utils.ATTR_CART, this.cart);
	}
	public Cart getCart() { 
		if (null == this.cart) 
			this.cart = (Cart)sess.getAttribute(Utils.ATTR_CART);
		return this.cart; 
	}

	public String getName() { return this.name; }

	public static ShoppingSession get(HttpSession sess) {
		String name = (String)sess.getAttribute(Utils.ATTR_NAME);

		ShoppingSession shoppingSess = new ShoppingSession(name, sess);
		return shoppingSess;
	}
}
