package service;

import model.Order;
import model.OrderHistory;
import util.DatabaseService;

public class OrderTrackingService {

    public void trackOrder(
            String orderId) {

        DatabaseService db =
                new DatabaseService();

        Order order =
                OrderHistory.searchOrder(
                        orderId);

        if(order == null) {

            order =
                    db.getOrderById(
                            orderId);
        }
        if(order == null) {

            System.out.println(
                    "\nOrder Not Found");

            return;
        }

        System.out.println(
                "\n================================");

        System.out.println(
                "        ORDER DETAILS");

        System.out.println(
                "================================");

        System.out.println(
                "Order ID : "
                + order.getOrderId());

        System.out.println(
                "Restaurant : "
                + order.getRestaurantName());

        System.out.println(
                "Customer : "
                + order.getCustomerName());

        System.out.println(
                "Amount : Rs."
                + order.getAmount());

        System.out.println(
                "Status : "
                + order.getOrderStatus());

        System.out.println(
                "Delivery Slot : "
                + order.getDeliverySlot());

        System.out.println(
                "Delivery Agent : "
                + (order.getDeliveryAgent() == null
                   ? "Not Assigned Yet"
                   : order.getDeliveryAgent()));

        System.out.println(
                "ETA : "
                + (order.getEta() == null
                   ? "Available Before Dispatch"
                   : order.getEta()));
        System.out.println(
                "Route Status : "
                + (order.getRouteStatus() == null
                    ? "Pending"
                    : order.getRouteStatus()));

        System.out.println(
                "Tracking Status : "
                + (order.getTrackingStatus() == null
                    ? "Pending"
                    : order.getTrackingStatus()));

        System.out.println(
                "================================");
    }
}