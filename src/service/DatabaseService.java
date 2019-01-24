package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import database.Category;
import database.Database;
import database.Product;

public class DatabaseService {
	
	private Database database = new Database();

	public void addItem(String name, BigDecimal price, Category category, BigDecimal discount, String description) {
		UUID uuid = UUID.randomUUID();
		database.addItem(uuid, name, price, category, discount, description); 
	}
	
	public void delItem(String uuid) {
		if (itemExist(uuid)) {
			database.delItem(UUID.fromString(uuid));
		} else {
			System.out.println("Failed: product with id " + uuid + " does not exist!!!");
		}
	}

	public void getProductList() {

		for (Product element : database.getProductDatabase().values()) {
			System.out.println(element);
		}
	}
	
	public void getProductListInCategory(Category category) {
		List<Product> productList = new ArrayList<>();
		for (Product element : database.getProductDatabase().values()) {
			if(element.getCategory() == category) {
				productList.add(element);
			}
		}
		for (Product element : productList) {
			System.out.println(element);
		}
	}
	
	public void getItem(String uuid) {
	if (itemExist(uuid)) {
		System.out.println(database.getProductDatabase().get(UUID.fromString(uuid)));
	} else {
		System.out.println("Failed: product with id " + uuid + " does not exist!!!");
	}
}
	
	private boolean itemExist(String uuid) {
		for (Product element : database.getProductDatabase().values()) {
			if (element.getId().equals(UUID.fromString(uuid))) {
				return true;
			}
		}
		return false;
	}
	
	public void modifyProductDiscount(String uuid, BigDecimal discount) {
	if(!itemExist(uuid)) {
		System.out.println("Failed: product with id " + uuid + " does not exist!!!");
		} else {
			database.getProductDatabase().get(UUID.fromString(uuid)).setDiscount(discount);
			System.out.println("Success: discount modified!!!");
		}
	}
	
	public void modifyProductPrice(String uuid, BigDecimal price) {
	if(!itemExist(uuid)) {
		System.out.println("Failed: product with id " + uuid + " does not exist!!!");
		} else {
			database.getProductDatabase().get(UUID.fromString(uuid)).setPrice(price);
			System.out.println("Success: price modified!!!");
		}
	}
	
	public void setCategoryDiscount(Category category, BigDecimal discount) {
		for (Product element : database.getProductDatabase().values()) {
			if(element.getCategory() == category) {
				element.setDiscount(discount);
			}
		}
	}
	

}
