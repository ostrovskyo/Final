package database;

import java.math.BigDecimal;
import java.util.UUID;

import service.ActualPriceCalculation;

public class Product {

	private static final ActualPriceCalculation ACTUAL_PRICE_CALCULATION = new ActualPriceCalculation();
	
	private String name;
	private UUID id;
	private BigDecimal price;
	private Category category;
	private BigDecimal actualPrice;
	private BigDecimal discount;
	private String description;

	public Product(String name, UUID id, BigDecimal price, Category category, BigDecimal discount, String description) {
		this.name = name;
		this.id = id;
		this.price = price;
		this.category = category;
		this.discount = discount;
		this.description = description;
		this.actualPrice = ACTUAL_PRICE_CALCULATION.getActualPrice(price, discount);
	}

	public String getName() {
		return name;
	}

	public UUID getId() {
		return id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Category getCategory() {
		return category;
	}

	public BigDecimal getDiscount() {
		return discount;
	}
	
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
		this.actualPrice = ACTUAL_PRICE_CALCULATION.getActualPrice(price, discount);
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
		this.actualPrice = ACTUAL_PRICE_CALCULATION.getActualPrice(price, discount);
	}

	public String getDescription() {
		return description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actualPrice == null) ? 0 : actualPrice.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((discount == null) ? 0 : discount.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (actualPrice == null) {
			if (other.actualPrice != null)
				return false;
		} else if (!actualPrice.equals(other.actualPrice))
			return false;
		if (category != other.category)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (discount == null) {
			if (other.discount != null)
				return false;
		} else if (!discount.equals(other.discount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", id=" + id + ", price=" + price +"EUR" + ", category=" + category + ", actualPrice="
				+ actualPrice + "EUR" + ", discount=" + discount + "%" +", description=" + description + "]";
	}


}
