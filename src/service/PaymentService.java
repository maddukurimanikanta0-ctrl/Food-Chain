package service;

import java.util.Scanner;

import util.InputHelper;

public class PaymentService {

    public boolean payment(
            int choice,
            double amount,
            Scanner sc) {

        if(amount <= 0) {

            System.out.println(
                    "Cart Is Empty");

            return false;
        }

        System.out.println(
                "\n========= PAYMENT =========");

        switch(choice) {

        case 1:

            String phone =
                    InputHelper.readPhone(
                            sc,
                            "Enter UPI Number : ");

            String pin =
                    InputHelper.readUPIPin(
                            sc,
                            "Enter UPI PIN : ");

            System.out.println(
                    "UPI Verification Successful");

            break;

        case 2:

            String card =
                    InputHelper.readCardNumber(
                            sc,
                            "Card Number : ");

            String cvv =
                    InputHelper.readCVV(
                            sc,
                            "CVV : ");

            String expiry =
                    InputHelper.readExpiry(
                            sc,
                            "Expiry (MM/YY) : ");

            System.out.println(
                    "Card Verified");

            break;

        case 3:

            System.out.println(
                    "Cash On Delivery Selected");

            System.out.println(
                    "Amount To Pay : Rs."
                    + amount);

            return true;

        default:

            System.out.println(
                    "Invalid Payment Method");

            return false;
        }

        System.out.println(
                "Payment Successful");

        System.out.println(
                "Amount Paid : Rs."
                + amount);

        return true;
    }
}