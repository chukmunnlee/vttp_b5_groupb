package vttp.batch5.ssf.shoppingcart.models;

public class Item {

	private String name;
	private int quantity;

	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }

	public void setQuantity(int quantity) { this.quantity = quantity; }
	public int getQuantity() { return this.quantity; }

	@Override
	public String toString() {
		return "Item[name=%s, quantity=%d]".formatted(this.name, this.quantity);
	}
}
