package database;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import database.Category;
import database.Product;

public class Database {

	private Map<UUID, Product> productDatabase = new HashMap<>();
	
	public void addItem(UUID uuid, String name, BigDecimal price, Category category, BigDecimal discount, String description) {
		    productDatabase.put(uuid, new Product(name, uuid, price, category, discount, description));
			System.out.println("Success: new product added!!!");
	}

	public void delItem(UUID uuid) {
		productDatabase.remove(uuid);
		System.out.println("Success: product removed!!!");
	}
	
	public Map<UUID, Product> getProductDatabase() {
		return productDatabase;
	}
	
	public void modifyProductDiscount(String uuid, BigDecimal discount) {
		productDatabase.get(UUID.fromString(uuid)).setDiscount(discount);
		System.out.println("Success: discount modified!!!");
	}

}
