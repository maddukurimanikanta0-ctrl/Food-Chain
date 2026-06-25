package model;

import java.util.ArrayList;
import java.util.Comparator;

public class OrderHistory {

    private static ArrayList<Order>
            orders =
            new ArrayList<>();

    public static void addOrder(
            Order order) {

        orders.add(order);

        orders.sort(
                Comparator.comparing(
                        Order::getOrderId));
    }

    public static Order searchOrder(
            String orderId) {

        int low = 0;
        int high =
                orders.size() - 1;

        while(low <= high) {

            int mid =
                    (low + high) / 2;

            int cmp =
                    orders.get(mid)
                    .getOrderId()
                    .compareTo(orderId);

            if(cmp == 0)
                return orders.get(mid);

            if(cmp < 0)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return null;
    }

    public static void showHistory() {

        System.out.println(
                "\n===== ORDER HISTORY =====");

        for(Order order :
                orders) {

            System.out.println(
                    order.getOrderId()
                    + " | "
                    + order.getRestaurantName()
                    + " | Rs."
                    + order.getAmount()
                    + " | "
                    + order.getOrderStatus());
        }
    }
}