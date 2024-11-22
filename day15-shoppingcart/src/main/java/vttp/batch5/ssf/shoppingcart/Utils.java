package vttp.batch5.ssf.shoppingcart;

import jakarta.servlet.http.HttpSession;
import vttp.batch5.ssf.shoppingcart.models.ShoppingSession;

public class Utils {

	public static final String ATTR_NAME = "name";


	public static ShoppingSession initializeSession(String name, HttpSession sess) {
		if (null == sess.getAttribute(ATTR_NAME))
			sess.setAttribute(ATTR_NAME, name);
		return new ShoppingSession(name);
	}
}
