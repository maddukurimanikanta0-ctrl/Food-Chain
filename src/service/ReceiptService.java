package service;

import model.Cart;
import model.Order;
import model.User;

public class ReceiptService {

    public void printReceipt(
            User user,
            Order order,
            Cart cart) {

        System.out.println(
                "\n================================");

        System.out.println(
                "        FOODCHAIN RECEIPT");

        System.out.println(
                "================================");

        System.out.println(
                "Order ID      : "
                + order.getOrderId());

        System.out.println(
                "Customer      : "
                + user.getName());

        System.out.println(
                "Phone         : "
                + user.getPhone());

        System.out.println(
                "Address       : "
                + user.getAddress());

        System.out.println(
                "Payment Method: "
                + order.getPaymentMethod());

        System.out.println(
                "Status        : "
                + order.getOrderStatus());

        System.out.println(
                "--------------------------------");

        System.out.println(
                "Sub Total     : Rs."
                + cart.getTotal());

        System.out.println(
                "GST           : Rs."
                + cart.getGST());

        System.out.println(
                "Delivery Fee  : Rs."
                + cart.getDeliveryFee());
        if(order.getCouponCode() != null
                && !order.getCouponCode().isBlank()) {

            System.out.println(
                    "Coupon Used : "
                    + order.getCouponCode());

            System.out.println(
                    "Discount : Rs."
                    + order.getDiscount());
        }
        System.out.println(
                "--------------------------------");

        System.out.println(
                "Final Amount  : Rs."
                + order.getAmount());

        System.out.println(
                "================================");
    }
}