package service;

import java.util.ArrayList;

import model.MenuItem;

public class FoodSearchService {

	public MenuItem searchFood(
	        ArrayList<MenuItem> menu,
	        String foodName) {

	    for(MenuItem item : menu) {

	        if(item.getItemName()
	                .toLowerCase()
	                .contains(
	                        foodName.toLowerCase())) {

	            return item;
	        }
	    }

	    return null;
	}
}