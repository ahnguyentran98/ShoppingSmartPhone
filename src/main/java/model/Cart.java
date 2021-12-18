package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private List<Product> items;

	public Cart() {
		items = new ArrayList<>();
	}

	public void add(Product product) {
		product.setNumber(product.getNumber() + 1);
		for (Product x : items) {
			if (x.getId() == product.getId()) {
				x.setNumber(x.getNumber() + 1);
				return;
			}
		}
		items.add(product);
	}

	public void delete(Product product) {
		for (Product x : items) {
			if (x.getId() == product.getId()) {
				items.remove(x);
				return;
			}
		}
	}

	public void remove(Product product) {
		for (Product x : items) {
			if (x.getId() == product.getId()) {
				if (x.getNumber() == 1) {
					delete(product);
					return;
				} else {
					x.setNumber(x.getNumber() - 1);
					return;
				}
			}
		}
	}

	public double getAmount() {
		double s = 0;
		for (Product x : items) {
			s += x.getPrice() * x.getNumber();
		}
		return Math.round(s * 100.0) / 100.0;
	}

	public List<Product> getItems() {
		return items;
	}

}
