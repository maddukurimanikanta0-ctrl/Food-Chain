package algorithms;

public class OrderAnalytics {

    private static int totalOrders;
    private static int cancelledOrders;
    private static int deliveredOrders;

    public static void addOrder() {

        totalOrders++;
    }

    public static void addCancelledOrder() {

        cancelledOrders++;
    }

    public static void addDeliveredOrder() {

        deliveredOrders++;
    }

    public static int getTotalOrders() {

        return totalOrders;
    }

    public static int getCancelledOrders() {

        return cancelledOrders;
    }

    public static int getDeliveredOrders() {

        return deliveredOrders;
    }
    public static double getSuccessRate() {

        if(totalOrders == 0)
            return 0;

        return
                deliveredOrders
                * 100.0
                / totalOrders;
    }
    public static void showAnalytics() {

        System.out.println(
                "\n===== ORDER ANALYTICS =====");

        System.out.println(
                "Total Orders : "
                + totalOrders);

        System.out.println(
                "Delivered Orders : "
                + deliveredOrders);

        System.out.println(
                "Cancelled Orders : "
                + cancelledOrders);

        if(totalOrders > 0) {

            double success =
                    (deliveredOrders * 100.0)
                    / totalOrders;

            System.out.println(
                    "Success Rate : "
                    + String.format(
                            "%.2f",
                            success)
                    + "%");
        }
    }
}