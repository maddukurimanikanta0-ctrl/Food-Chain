package service;

import model.Order;

public class TrackingService {

    public void trackOrder(
            Order order) {

        System.out.println(
                "\n===== ORDER TRACKING =====");

        System.out.println(
                "Order ID : "
                + order.getOrderId());

        System.out.println(
                "Status : "
                + order.getOrderStatus());

        System.out.println(
                "Delivery Agent : "
                + order.getDeliveryAgent());

        System.out.println(
                "ETA : "
                + order.getEta());
    }
    public void simulateTracking(
            Order order) {

        System.out.println(
                "\n===== DELIVERY PROGRESS =====");

        String[] stages = {

                "Order Placed",
                "Restaurant Accepted",
                "Food Preparation Started",
                "Food Ready",
                "Picked Up By Delivery Agent",
                "Out For Delivery",
                "Delivered Successfully"
        };
        for(int i = 0;
        	    i < stages.length;
        	    i++) {

        	    System.out.println(
        	            stages[i]);

        	    if(i != stages.length - 1) {

        	        System.out.println("↓");
        	    }
        	}

        order.setOrderStatus(
                "Delivered");
    }
}