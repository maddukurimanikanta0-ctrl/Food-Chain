package service;

import algorithms.AgentAnalytics;
import algorithms.OrderAnalytics;

public class AdminService {

    public void showDashboard(
            int totalUsers,
            int totalOrders,
            double totalRevenue,
            String topRestaurant,
            String topAgent) {

        System.out.println(
                "\n========== ADMIN DASHBOARD ==========");

        System.out.println(
                "Total Users : "
                + totalUsers);

        System.out.println(
                "Total Orders : "
                + totalOrders);

        System.out.println(
                "Total Revenue : Rs."
                + totalRevenue);

        System.out.println(
                "Top Restaurant : "
                + topRestaurant);

        System.out.println(
                "Top Delivery Agent : "
                + topAgent);

        System.out.println(
                "Delivered Orders : "
                + OrderAnalytics
                .getDeliveredOrders());

        System.out.println(
                "Cancelled Orders : "
                + OrderAnalytics
                .getCancelledOrders());

        System.out.printf(
                "Success Rate : %.2f%%\n",
                OrderAnalytics
                .getSuccessRate());

        System.out.println(
                "Best Agent : "
                + AgentAnalytics
                .getTopAgent());
    }
}