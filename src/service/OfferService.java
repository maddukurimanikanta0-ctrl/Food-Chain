package service;

import java.util.HashMap;

public class OfferService {

    private HashMap<String, Double> offers =
            new HashMap<>();

    public OfferService() {

        offers.put("FLAT50", 50.0);
        offers.put("FREEDEL", 35.0);
        offers.put("BIRYANI20", 20.0);
    }

    public void showOffers() {

        System.out.println(
                "\n===== TODAY'S OFFERS =====");

        System.out.println(
                "FLAT50  -> Rs.50 Off Above Rs.299");

        System.out.println(
                "FREEDEL -> Free Delivery");

        System.out.println(
                "BIRYANI20 -> 20% Off On Biryani Orders");
    }

    public double applyCoupon(
            String coupon,
            double amount) {

        coupon =
                coupon.toUpperCase().trim();

        if(coupon.equals("FLAT50")
                && amount >= 299) {

            System.out.println(
                    "Coupon Applied : FLAT50");

            return amount - 50;
        }

        else if(coupon.equals("FREEDEL")) {

            System.out.println(
                    "Coupon Applied : FREEDEL");

            return amount - 40;
        }

        else if(coupon.equals("BIRYANI20")) {

            System.out.println(
                    "Coupon Applied : BIRYANI20");

            return amount * 0.80;
        }

        else if(!coupon.isBlank()) {

            System.out.println(
                    "Invalid Coupon");
        }

        return amount;
    }
}