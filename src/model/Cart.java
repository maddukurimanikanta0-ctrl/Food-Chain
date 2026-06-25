package model;

import java.util.ArrayList;

public class Cart {

    private ArrayList<CartItem>
            cartItems =
            new ArrayList<>();

    public void addItem(
            MenuItem item,
            int quantity) {

        cartItems.add(
                new CartItem(
                        item,
                        quantity));

        System.out.println(
                item.getItemName()
                + " x "
                + quantity
                + " Added To Cart");
    }

    public ArrayList<CartItem>
    getCartItems() {

        return cartItems;
    }

    public void viewCart() {

        if(cartItems.isEmpty()) {

            System.out.println(
                    "\nCart Is Empty");

            return;
        }

        double total = 0;

        System.out.println(
                "\n========== CART ==========");

        for(CartItem cartItem :
                cartItems) {

            System.out.println(
                    cartItem.getItem()
                            .getItemName()
                    + " x "
                    + cartItem.getQuantity()
                    + " = Rs."
                    + cartItem.getTotalPrice());

            total +=
                    cartItem.getTotalPrice();
        }

        System.out.println(
                "----------------------");

        System.out.println(
                "Total Amount : Rs."
                + total);
    }

    public double getTotal() {

        double total = 0;

        for(CartItem cartItem :
                cartItems) {

            total +=
                    cartItem.getTotalPrice();
        }

        return total;
    }

    public double getGST() {

        return getTotal() * 0.05;
    }

    public double getDeliveryFee() {

        return 40;
    }

    public double getGrandTotal() {

        return getTotal()
                + getGST()
                + getDeliveryFee();
    }

    public boolean isEmpty() {

        return cartItems.isEmpty();
    }
}