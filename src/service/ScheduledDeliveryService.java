package service;

import java.util.Scanner;

import util.InputHelper;

public class ScheduledDeliveryService {

    private boolean scheduled =
            false;

    public String scheduleOrder(
            Scanner sc) {

        System.out.println(
                "\n========= DELIVERY OPTION =========");

        System.out.println(
                "1. Deliver Now");

        System.out.println(
                "2. Schedule Delivery");

        int choice =
                InputHelper.readChoice(
                        sc,
                        "Choose : ",
                        1,
                        2);

        if(choice == 1) {

            scheduled = false;

            return "Immediate Delivery";
        }

        scheduled = true;

        String date =
                InputHelper.readString(
                        sc,
                        "Enter Date (DD/MM/YYYY) : ");

        String time =
                InputHelper.readString(
                        sc,
                        "Enter Time (HH:MM AM/PM) : ");

        System.out.println(
                "\nDelivery Scheduled");

        System.out.println(
                "Date : "
                + date);

        System.out.println(
                "Time : "
                + time);

        return date + " " + time;
    }

    public boolean isScheduled() {
        return scheduled;
    }
}