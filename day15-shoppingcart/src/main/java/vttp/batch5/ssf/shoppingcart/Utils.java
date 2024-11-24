package vttp.batch5.ssf.shoppingcart;

import jakarta.servlet.http.HttpSession;

public class Utils {

	public static final String ATTR_NAME = "name";
	public static final String ATTR_CART_IDS = "cartIds";
	public static final String ATTR_CARTS = "carts";
	public static final String ATTR_CART = "cart";
	public static final String ATTR_CART_ID = "cartId";
	public static final String ATTR_LINE_ITEM = "lineItem";

	public static final String VAL_NEW_CART = "NEW_CART";

	public static boolean isNewSession(HttpSession sess) {
		return (null == sess.getAttribute(ATTR_NAME));
	}

	public static void initializeSession(String name, HttpSession sess) {
		sess.setAttribute(ATTR_NAME, name);
	}

	public static void destroySession(HttpSession sess) {
		sess.invalidate();
	}
}
