package bussiness;
import java.util.ArrayList;

import models.Category;

public class CategoryEndpoint {

	public ArrayList<String> getCategories() {
		Category[] categories = Category.values();
		ArrayList<String> result = new ArrayList<String>();

		for (Category category : categories) {
			result.add(category.name());
		}

		return result;
	}
}
