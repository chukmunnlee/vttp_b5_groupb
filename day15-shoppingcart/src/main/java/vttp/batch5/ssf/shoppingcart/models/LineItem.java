package vttp.batch5.ssf.shoppingcart.models;

public class LineItem {

	private String name;
	private int quantity;

	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }

	public void setQuantity(int quantity) { this.quantity = quantity; }
	public int getQuantity() { return this.quantity; }

	@Override
	public String toString() {
		return "LineItem{name=%s, quantity=%d}".formatted(name, quantity);
	}
}
