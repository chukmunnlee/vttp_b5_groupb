package vttp.batch5.ssf.shoppingcart.models;

import java.util.LinkedList;
import java.util.List;

public class Cart {

	private final String cartId;
	private List<LineItem> contents = new LinkedList<>();

	public Cart(String cartId) {
		this.cartId = cartId;
	}

	public String getCartId() { return this.cartId; }

	public void setContents(List<LineItem> contents) { this.contents = contents; }
	public List<LineItem> getContents() { return this.contents; }
	public void addContent(LineItem lineItem) { this.contents.add(lineItem); }

	public int size() { return this.contents.size(); }
}
