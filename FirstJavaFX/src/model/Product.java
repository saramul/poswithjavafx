package model;

public class Product {
	private String id;
	private String name;
	private int price;
	private int amount;
	
	public Product() {
		super();
	}
	public Product(String id, String name, int price, int amount) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.amount = amount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
