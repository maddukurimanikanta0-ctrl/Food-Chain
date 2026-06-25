package service;

import java.util.ArrayList;
import java.util.Scanner;

import algorithms.QuickSortRestaurants;
import model.Restaurant;
import util.InputHelper;

public class RestaurantService {

    public void showRestaurants(
            ArrayList<Restaurant> restaurants) {

        System.out.println(
                "\n===== NEARBY RESTAURANTS =====");

        for(int i = 0;
            i < restaurants.size();
            i++) {

            System.out.println(
                    (i + 1)
                    + ". "
                    + restaurants.get(i)
                            .getName());
        }
    }

    public void showTopRestaurants(
            ArrayList<Restaurant> restaurants) {

        Restaurant[] arr =
                restaurants.toArray(
                        new Restaurant[0]);

        QuickSortRestaurants sorter =
                new QuickSortRestaurants();

        sorter.quickSort(
                arr,
                0,
                arr.length - 1);

        System.out.println(
                "\n===== TOP 10 RESTAURANTS =====");

        for(int i = 0;
            i < Math.min(10, arr.length);
            i++) {

            System.out.printf(
                    "%d. %s | %.1f%n",
                    i + 1,
                    arr[i].getName(),
                    arr[i].getRating());
        }
    }

    public Restaurant selectRestaurant(
            ArrayList<Restaurant> restaurants,
            Scanner sc) {

    	int choice =
    	        InputHelper.readChoice(
    	                sc,
    	                "\nSelect Restaurant Number : ",
    	                1,
    	                restaurants.size());

        return restaurants.get(
                choice - 1);
    }
    public ArrayList<Restaurant> getNearbyRestaurants(
            ArrayList<Restaurant> restaurants,
            String area,
            String city) {

        ArrayList<Restaurant> nearby =
                new ArrayList<>();

        String userArea =
                area.toLowerCase();

        for(Restaurant restaurant : restaurants) {

            String location =
                    restaurant.getLocation()
                    .toLowerCase();

            if(location.contains(userArea)) {

                nearby.add(restaurant);
            }

            else if(userArea.contains("governorpet")
                    &&
                    (location.contains("benz")
                    || location.contains("labbipet")
                    || location.contains("bandar")
                    || location.contains("patamata"))) {

                nearby.add(restaurant);
            }

            else if(userArea.contains("benz")
                    &&
                    (location.contains("governorpet")
                    || location.contains("patamata")
                    || location.contains("bandar"))) {

                nearby.add(restaurant);
            }

            else if(userArea.contains("patamata")
                    &&
                    (location.contains("benz")
                    || location.contains("kanuru")
                    || location.contains("poranki"))) {

                nearby.add(restaurant);
            }
        }

        return nearby;
    }
    public void showNearbyRestaurants(
            ArrayList<Restaurant> restaurants) {

        System.out.println(
                "\n===== NEARBY RESTAURANTS =====");

        for(int i = 0;
            i < restaurants.size();
            i++) {

            System.out.println(
                    (i + 1)
                    + ". "
                    + restaurants.get(i)
                            .getName());
        }
    }
}