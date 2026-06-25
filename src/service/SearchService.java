package service;

import java.util.ArrayList;

import model.Restaurant;

public class SearchService {

	public ArrayList<Restaurant>
	searchRestaurants(
	        ArrayList<Restaurant> restaurants,
	        String keyword) {

	    ArrayList<Restaurant>
	            result =
	            new ArrayList<>();

	    for(Restaurant restaurant :
	            restaurants) {

	    	String search =
	    	        keyword.toLowerCase().trim();

	    	String name =
	    	        restaurant.getName()
	    	        .toLowerCase();

	    	if(name.contains(search)
	    	        ||
	    	        search.contains(name)) {

	    	    result.add(
	    	            restaurant);
	    	}
	    }

	    return result;
	}
}