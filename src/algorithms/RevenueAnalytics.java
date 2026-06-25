package algorithms;

public class RevenueAnalytics {

    private static double totalRevenue;

    private static int totalOrders;

    private static double highestOrder;

    private static double lowestOrder =
            Double.MAX_VALUE;

    public static void addRevenue(
            double amount) {

        totalRevenue += amount;

        totalOrders++;

        highestOrder =
                Math.max(
                        highestOrder,
                        amount);

        lowestOrder =
                Math.min(
                        lowestOrder,
                        amount);
    }

    public static double getRevenue() {

        return totalRevenue;
    }

    public static int getTotalOrders() {

        return totalOrders;
    }

    public static void showRevenue() {

        System.out.println(
                "\n===== REVENUE ANALYTICS =====");

        System.out.println(
                "Total Orders : "
                + totalOrders);

        System.out.println(
                "Total Revenue : Rs."
                + totalRevenue);

        if(totalOrders > 0) {

            System.out.println(
                    "Average Order Value : Rs."
                    + totalRevenue
                    / totalOrders);

            System.out.println(
                    "Highest Order : Rs."
                    + highestOrder);

            System.out.println(
                    "Lowest Order : Rs."
                    + lowestOrder);
        }
    }
}