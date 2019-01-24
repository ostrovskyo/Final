package user_interface;

import java.math.BigDecimal;
import java.util.Scanner;

import database.Category;
import service.BigDecimalValidator;
import service.DatabaseService;
import service.MaxLengthValidator;
import service.MinLengthValidator;
import service.PositiveNumberCheck;
import service.StringValidator;
import service.Validator;

public class MainMenu {

	DatabaseService dbService = new DatabaseService();
	Scanner scan = new Scanner(System.in);

	public void makeChoice() {
		String choice = "-1";
		
		while (!choice.equals("0")) {
			printMenu();
			choice = scan.nextLine();
			
			switch(choice) {
			case "1": addProductMenu(); break;
			case "2": getProductMenu(); break;
			case "3": dbService.getProductList(); break;
			case "4": deleteProductMenu(); break;
			case "5": getProductsInCategory(); break;
			case "6": modifyProductDiscount(); break;
			case "7": modifyProductPrice(); break;
			case "8": setCategoryDiscount(); break;
			case "0" : System.out.println("Bye"); return;
			}
		}
	}
	
	private void addProductMenu() {
		StringValidator sv = new StringValidator(new Validator[] {new MinLengthValidator(), new MaxLengthValidator()}); 
		Category category = getCategory();
		
		BigDecimalValidator bdv = new BigDecimalValidator(new Validator[] {new PositiveNumberCheck()});
		
		System.out.println("Enter product name:");
		String name = scan.nextLine();
		if(!sv.validate(name)) {
			System.out.println("Error: check NAME input! Must be from 1 to 20 characters.");
			makeChoice();
		}
		
		System.out.println("(OPTIONAL) Enter product description:");
		String description = scan.nextLine();

		System.out.println("Enter product price in EUR:");
		String pr = scan.nextLine();
		if(!bdv.validate(pr)) {
			System.out.println("Error: check PRICE input! Must be positive, greater than 0.");
			makeChoice();
		}
		BigDecimal price = new BigDecimal(pr);

		System.out.println("(OPTIONAL) Enter product discount in %:");
		String disc = scan.nextLine();
		BigDecimal discount;
		if(disc.equals("")) {
			discount = new BigDecimal(0);
		} else {
			discount = new BigDecimal(disc);
		}

		dbService.addItem(name, price, category, discount, description);
	}
	
	private void getProductMenu() {
		System.out.println("Enter product ID:");
		dbService.getItem(scan.nextLine());
	}
	
	private void deleteProductMenu() {
		System.out.println("Enter ID to delete:");
		dbService.delItem(scan.nextLine());
	}
	
	private Category getCategory() {
		Category category = null;
		System.out.println("Choose product category: (1)CLOTHING, (2)SHOES, (3)JEWERLY, (4)ELECTRONICS, (5)FOOD, (6)SPORTS");
		String ch = scan.nextLine();
		if(ch.equals(Integer.toString(1))) {category = Category.CLOTHING;}
		else if(ch.equals(Integer.toString(2))) {category = Category.SHOES;}
		else if(ch.equals(Integer.toString(3))) {category = Category.JEWERLY;}
		else if(ch.equals(Integer.toString(4))) {category = Category.ELECTRONICS;}
		else if(ch.equals(Integer.toString(5))) {category = Category.FOOD;}
		else if(ch.equals(Integer.toString(6))) {category = Category.SPORTS;}
		else {System.out.println("ERROR: wrong input!!!"); makeChoice();}
		return category;
	}
	
	private void getProductsInCategory() {
		Category category = getCategory();
		dbService.getProductListInCategory(category);
	}
	
	private void modifyProductDiscount(){
		System.out.println("Enter product ID, to modify discount:");
		String uuid = scan.nextLine();
		dbService.getItem(uuid);
		System.out.println("Enter new discount value in %:");
		BigDecimal disc = new BigDecimal(scan.nextLine());
		dbService.modifyProductDiscount(uuid, disc);
	}
	
	private void modifyProductPrice(){
		System.out.println("Enter product ID, to modify price:");
		String uuid = scan.nextLine();
		dbService.getItem(uuid);
		System.out.println("Enter new price value in EUR:");
		BigDecimal price = new BigDecimal(scan.nextLine());
		dbService.modifyProductPrice(uuid, price);
	}
	
	private void setCategoryDiscount() {
		System.out.println("Choose group to set discount on:");
		Category category = getCategory();
		System.out.println("Enter discount value in %:");
		BigDecimal disc = new BigDecimal(scan.nextLine());
		dbService.setCategoryDiscount(category, disc);
	}

	private void printMenu() {
		System.out.println("1. Add product");
		System.out.println("2. Get product by id");
		System.out.println("3. Get product list");
		System.out.println("4. Delete product by id");
		System.out.println("5. Get product list within category");
		System.out.println("6. Modify product discount");
		System.out.println("7. Modify product price");
		System.out.println("8. Set group discount");
		System.out.println("0. Exit");
	}

}