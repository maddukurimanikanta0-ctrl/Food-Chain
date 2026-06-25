package service;

import java.util.HashMap;

public class WaitingTimeService {

    private HashMap<String,Integer>
            prepTime =
            new HashMap<>();

    public WaitingTimeService() {

        prepTime.put(
                "KFC",
                12);

        prepTime.put(
                "Pizza Hut",
                18);

        prepTime.put(
                "Domino's",
                10);

        prepTime.put(
                "Burger King",
                15);
    }

    public int getPreparationTime(
            String restaurantName) {

        return prepTime.getOrDefault(
                restaurantName,
                15);
    }

    public void showETA(
            String restaurantName,
            int deliveryTime) {

        int prep =
                getPreparationTime(
                        restaurantName);

        System.out.println(
                "\n===== WAITING TIME =====");

        System.out.println(
                "Preparation Time : "
                + prep
                + " Minutes");

        System.out.println(
                "Delivery Time : "
                + deliveryTime
                + " Minutes");

        System.out.println(
                "Total ETA : "
                + (prep + deliveryTime)
                + " Minutes");
    }
}