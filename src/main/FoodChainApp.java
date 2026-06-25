package main;

import java.util.ArrayList;



import util.DatabaseService;
import java.util.Scanner;

import model.Cart;
import model.DeliveryAgent;
import model.MenuItem;
import model.Order;
import model.Restaurant;
import model.User;
import model.OrderHistory;

import service.AdminService;
import service.DeliveryService;
import service.MenuService;
import service.PaymentService;
import service.ReceiptService;
import service.RestaurantService;
import service.UserService;
import service.FeedbackService;
import service.SupportService;
import service.TrackingService;
import service.SearchService;
import service.FoodSearchService;
import service.OfferService;
import service.OrderTrackingService;
import service.WaitingTimeService;
import service.ScheduledDeliveryService;

import util.ExcelReader;
import util.OrderGenerator;
import util.InputHelper;

import algorithms.AgentAnalytics;
import algorithms.RevenueAnalytics;
import algorithms.HeapOrderPriority;
import algorithms.DijkstraRouting;
import algorithms.OrderAnalytics;

public class FoodChainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UserService userService =
                new UserService();

        RestaurantService restaurantService =
                new RestaurantService();

        MenuService menuService =
                new MenuService();

        PaymentService paymentService =
                new PaymentService();

        DeliveryService deliveryService =
                new DeliveryService();

        ReceiptService receiptService =
                new ReceiptService();

        ExcelReader reader =
                new ExcelReader();

        ArrayList<Restaurant> restaurants =
                reader.loadRestaurants();
        restaurantService
        .showTopRestaurants(
                restaurants);

        ArrayList<DeliveryAgent> agents =
                reader.loadDeliveryAgents();

        User currentUser = null;

        while(currentUser == null) {

            System.out.println(
                    "\n========== FOODCHAIN ==========");

            System.out.println("1. Register");
            System.out.println("2. Login");

            int choice =
                    InputHelper.readChoice(
                            sc,
                            "Enter Choice : ",
                            1,
                            2);

            switch(choice) {

            case 1:

                currentUser =
                        userService
                                .register(sc);

                break;

            case 2:

                currentUser =
                        userService
                                .login(sc);

                break;

            default:

                System.out.println(
                        "Invalid Choice");
            }
            
        }
        System.out.println(
                "\n========= DASHBOARD =========");

        System.out.println(
                "1. Place New Order");

        System.out.println(
                "2. Track Existing Order");

        System.out.println(
                "3. Exit");

        int dashboardChoice =
                InputHelper.readInt(
                        sc,
                        "Choose : ");
        if(dashboardChoice == 2) {

        	String orderIdToTrack =
        	        InputHelper.readString(
        	                sc,
        	                "\nEnter Order ID : ");

            OrderTrackingService tracker =
                    new OrderTrackingService();

            tracker.trackOrder(
                    orderIdToTrack);

            sc.close();

            return;
        }

        if(dashboardChoice == 3) {

            sc.close();

            return;
        }
        ArrayList<Restaurant>
        nearbyRestaurants =
        restaurantService
        .getNearbyRestaurants(
                restaurants,
                currentUser.getArea(),
                currentUser.getCity());
        System.out.println(
                "\n===== RECOMMENDED FOR YOU =====");

        for(int i = 0;
            i < Math.min(5,
                    nearbyRestaurants.size());
            i++) {

            System.out.println(
                    (i + 1)
                    + ". "
                    + nearbyRestaurants
                    .get(i)
                    .getName());
        }
        

        if(nearbyRestaurants.isEmpty()) {

            System.out.println(
                    "\nNo Restaurants Found In "
                    + currentUser.getArea()
                    + ", "
                    + currentUser.getCity());

           

            String choice =
                    InputHelper.readYesNo(
                            sc,
                            "\nDo You Want To View All Restaurants ? (Y/N) : ");
            if(choice.equalsIgnoreCase("Y")) {

                nearbyRestaurants =
                        restaurants;

            } else {

                System.out.println(
                        "\nExiting Application");

                sc.close();
                return;
            }
        }
        
        System.out.println(
                "\nRestaurants Near Your Location");

        restaurantService
        .showNearbyRestaurants(
                nearbyRestaurants);

        String explore =
                InputHelper.readYesNo(
                        sc,
                        "\nWant To Explore All Restaurants ? (Y/N) : ");

        ArrayList<Restaurant>
        displayRestaurants;

        if(explore.equalsIgnoreCase("Y")) {

            displayRestaurants =
                    restaurants;

            restaurantService
                    .showRestaurants(
                            restaurants);

        } else {

            displayRestaurants =
                    nearbyRestaurants;
        }
        SearchService searchService =
                new SearchService();

        Restaurant selectedRestaurant =
                null;

        System.out.print(
        		"\nSearch Restaurant Name (or press Enter to skip): ");
        String search =
                sc.nextLine();

        if(!search.isBlank()) {

            ArrayList<Restaurant>
            foundRestaurants =
            searchService.searchRestaurants(
                    displayRestaurants,
                    search);

            if(foundRestaurants.isEmpty()) {

                System.out.println(
                        "\nRestaurant Not Found");

            } else {

                System.out.println(
                        "\nMatching Restaurants :");

                for(int i = 0;
                    i < foundRestaurants.size();
                    i++) {

                    System.out.println(
                            (i + 1)
                            + ". "
                            + foundRestaurants.get(i)
                            .getName());
                }

                int selected =
                        InputHelper.readChoice(
                                sc,
                                "Select Restaurant Number : ",
                                1,
                                foundRestaurants.size());

                selectedRestaurant =
                        foundRestaurants.get(
                                selected - 1);
            }
        }

        if(selectedRestaurant == null) {

            selectedRestaurant =
                    restaurantService
                            .selectRestaurant(
                                    displayRestaurants,
                                    sc);
        }

        System.out.println(
                "\nSelected Restaurant : "
                + selectedRestaurant.getName());
        
     
       

        String sheetName =
                selectedRestaurant
                        .getRestaurantId()
                + " "
                + selectedRestaurant
                        .getName();

        ArrayList<MenuItem> menu =
                reader.loadMenu(
                        sheetName);

        menuService.showMenu(menu);
        Cart cart =
                new Cart();
        FoodSearchService foodSearch =
                new FoodSearchService();

        System.out.print(
                "\nSearch Food Item : ");

        String foodName =
                sc.nextLine();

        if(!foodName.isBlank()) {

            MenuItem foundItem =
                    foodSearch.searchFood(
                            menu,
                            foodName);

            if(foundItem != null) {

                System.out.println(
                        "\nItem Found");

                System.out.println(
                        foundItem.getItemName()
                        + " - Rs."
                        + foundItem.getPrice());

                String addChoice =
                        InputHelper.readYesNo(
                                sc,
                                "\nAdd This Item To Cart ? (Y/N) : ");

                if(addChoice.equalsIgnoreCase("Y")) {

                	int quantity =
                	        InputHelper.readPositiveInt(
                	                sc,
                	                "Enter Quantity : ");

                    cart.addItem(
                            foundItem,
                            quantity);
                
                }
            }
            else {

                System.out.println(
                        "\nFood Item Not Found");
            }
        }
        while(true) {

        	int itemNo =
        	        InputHelper.readChoice(
        	                sc,
        	                "\nEnter Item Number : ",
        	                1,
        	                menu.size());
           
        	int quantity =
        	        InputHelper.readPositiveInt(
        	                sc,
        	                "Enter Quantity : ");
            MenuItem item =
                    menuService.findItem(
                            menu,
                            itemNo);

            if(item != null) {

            	cart.addItem(
            	        item,
            	        quantity);

            } else {

                System.out.println(
                        "Invalid Item");
            }

            String more =
                    InputHelper.readYesNo(
                            sc,
                            "\nAdd More Items ? (Y/N) : ");

            if(more.equalsIgnoreCase("N")) {

                break;
            }
        }

        cart.viewCart();

        System.out.println(
                "\nGST : Rs."
                + cart.getGST());

        System.out.println(
                "Delivery Fee : Rs."
                + cart.getDeliveryFee());

        System.out.println(
                "Grand Total : Rs."
                + cart.getGrandTotal());
        OfferService offerService =
                new OfferService();

        offerService.showOffers();

        System.out.print(
                "\nEnter Coupon Code (Press Enter To Skip) : ");

        String coupon =
                sc.nextLine();

        double finalAmount =
                offerService.applyCoupon(
                        coupon,
                        cart.getGrandTotal());
        double discount =
                cart.getGrandTotal()
                - finalAmount;

        System.out.println(
                "\nFinal Amount After Discount : Rs."
                + finalAmount);
        ScheduledDeliveryService schedule =
                new ScheduledDeliveryService();

        String deliverySlot =
                schedule.scheduleOrder(sc);
        boolean isScheduled =
                schedule.isScheduled();

        System.out.println(
                "\nDelivery Scheduled : "
                + deliverySlot);
        
        System.out.println(
                "\n========= PAYMENT =========");

        System.out.println(
                "1. UPI");

        System.out.println(
                "2. Credit Card");

        System.out.println(
                "3. Cash On Delivery");

       
        int paymentChoice =
                InputHelper.readChoice(
                        sc,
                        "Select Payment Method : ",
                        1,
                        3);
        boolean paid =
                paymentService.payment(
                        paymentChoice,
                        finalAmount,
                        sc);

        if(!paid) {
            return;
        }
        RevenueAnalytics.addRevenue(
                finalAmount);
        OrderAnalytics.addOrder();

        String paymentMethod =
                "";

        switch(paymentChoice) {

        case 1:
            paymentMethod =
                    "UPI";
            break;

        case 2:
            paymentMethod =
                    "Credit Card";
            break;

        case 3:
            paymentMethod =
                    "Cash On Delivery";
            break;
        }

        String orderId =
                OrderGenerator
                        .generateOrderId();

        Order order =
                new Order(
                        orderId,
                        currentUser.getName(),
                        selectedRestaurant.getName(),
                        finalAmount,
                        paymentMethod);
        order.setCouponCode(coupon);
        order.setDiscount(discount);
        if(isScheduled) {

            order.setDeliverySlot(
                    deliverySlot);
        }
        HeapOrderPriority heap =
                new HeapOrderPriority();

        int priority;

        if(cart.getGrandTotal() >= 1000) {

            priority = 3;

        } else if(cart.getGrandTotal() >= 500) {

            priority = 2;

        } else {

            priority = 1;
        }

        heap.addOrder(order);

        String priorityLabel;

        if(priority == 3) {

            priorityLabel = "HIGH";

        } else if(priority == 2) {

            priorityLabel = "MEDIUM";

        } else {

            priorityLabel = "LOW";
        }

        System.out.println(
                "\nOrder Priority : "
                + priorityLabel);

        Order next =
                heap.processOrder();

        System.out.println(
                "\nProcessing Order : "
                + next.getOrderId());
        DijkstraRouting route =
                new DijkstraRouting();
        DeliveryAgent assignedAgent =
                deliveryService
                        .assignAgentByArea(
                                agents,
                                currentUser.getArea());

        if(isScheduled) {

            order.setOrderStatus(
                    "Scheduled");
            order.setRouteStatus(
                    "Pending");

            order.setTrackingStatus(
                    "Scheduled");

            DatabaseService db =
                    new DatabaseService();

            db.saveOrder(order);

            OrderHistory.addOrder(
                    order);
        }
        else if(assignedAgent != null) {

            route.showShortestRoute(
                    selectedRestaurant.getArea(),
                    currentUser.getArea());
            order.setRouteStatus(
                    "Route Generated");

            order.setTrackingStatus(
                    "Out For Delivery");

            int distance =
                    route.getLastDistance();

            double deliveryCost;

            if(distance <= 3) {

                deliveryCost = 20;

            } else if(distance <= 8) {

                deliveryCost = 35;

            } else {

                deliveryCost = 50;
            }

            System.out.println(
                    "\nEstimated Delivery Cost : Rs."
                    + deliveryCost);

            int finalEta =
                    route.getLastETA();

            WaitingTimeService wait =
                    new WaitingTimeService();

            wait.showETA(
                    selectedRestaurant.getName(),
                    finalEta);

            order.setEta(
                    finalEta + " Minutes");

            order.setDeliveryAgent(
                    assignedAgent.getName());

            order.setOrderStatus(
                    "Preparing Food");

            DatabaseService db =
                    new DatabaseService();

            db.saveOrder(order);

            OrderHistory.addOrder(
                    order);
        }

        System.out.println(
                "\n========= ORDER CONFIRMED =========");

        System.out.println(
                "Order ID : "
                + order.getOrderId());

        System.out.println(
                "Status : "
                + order.getOrderStatus());
     

        String cancel =
                InputHelper.readYesNo(
                        sc,
                        "\nCancel Order ? (Y/N) : ");

        if(cancel.equalsIgnoreCase("Y")) {

            if(order.canCancel()) {

                order.setOrderStatus(
                        "Cancelled");
                OrderAnalytics.addCancelledOrder();

                System.out.println(
                        "\n================================");

                System.out.println(
                        "       ORDER CANCELLED");

                System.out.println(
                        "================================");

                System.out.println(
                        "Order ID : "
                        + order.getOrderId());

                System.out.println(
                        "Customer : "
                        + currentUser.getName());

                System.out.println(
                        "Restaurant : "
                        + selectedRestaurant.getName());

                System.out.println(
                        "Status : "
                        + order.getOrderStatus());

                if(order.getPaymentMethod()
                        .equalsIgnoreCase(
                                "Cash On Delivery")) {

                    System.out.println(
                            "Payment Status : No Refund Required");
                }
                else {

                    System.out.println(
                            "Refund Status : Initiated");

                    System.out.println(
                            "Expected Refund : 3-5 Business Days");
                }

                System.out.println(
                        "\nThank You For Using FoodChain");

                sc.close();
                return;

            } else {

                System.out.println(
                        "\nOrder Cannot Be Cancelled");
            }
        }

        if(!isScheduled) {

            deliveryService
                    .showAgent(
                            assignedAgent);

            AgentAnalytics.addDelivery(
                    assignedAgent.getName(),
                    finalAmount);

            TrackingService trackingService =
                    new TrackingService();

            trackingService.trackOrder(
                    order);

            trackingService.simulateTracking(
                    order);
            OrderAnalytics.addDeliveredOrder();

            deliveryService.releaseAgent(
                    assignedAgent);
        }
        else {

            System.out.println(
                    "\n================================================");

            System.out.println(
                    "              ORDER SCHEDULED");

            System.out.println(
                    "================================================");

            System.out.println(
                    "Order ID          : "
                    + order.getOrderId());

            System.out.println(
                    "Restaurant        : "
                    + selectedRestaurant.getName());

            System.out.println(
                    "Customer          : "
                    + currentUser.getName());

            System.out.println(
                    "Order Amount      : Rs."
                    + order.getAmount());

            System.out.println(
                    "Payment Method    : "
                    + order.getPaymentMethod());

            System.out.println(
                    "Delivery Slot     : "
                    + deliverySlot);

            System.out.println(
                    "Order Status      : Scheduled");

            System.out.println(
                    "\n=========== DELIVERY DETAILS ===========");

            System.out.println(
                    "Delivery Agent    : Will Be Assigned");

            System.out.println(
                    "Route Status      : Will Be Generated");

            System.out.println(
                    "ETA               : Available Before Dispatch");

            System.out.println(
                    "\n=========== ORDER TIMELINE ===========");

            System.out.println(
                    "[✓] Order Scheduled");

            System.out.println(
                    "[ ] Restaurant Notified");

            System.out.println(
                    "[ ] Food Preparation");

            System.out.println(
                    "[ ] Delivery Agent Assignment");

            System.out.println(
                    "[ ] Out For Delivery");

            System.out.println(
                    "[ ] Delivered");

            System.out.println(
                    "\nFood Preparation Will Start");

            System.out.println(
                    "30 Minutes Before Delivery Time");

            System.out.println(
                    "\nUse Order ID "
                    + order.getOrderId()
                    + " To Track This Order");

            System.out.println(
                    "================================================");
        }
        OrderHistory.showHistory();
        receiptService
                .printReceipt(
                        currentUser,
                        order,
                        cart);
        FeedbackService feedbackService =
                new FeedbackService();

        feedbackService.collectFeedback(
                currentUser,
                sc);

        SupportService supportService =
                new SupportService();

        supportService.showSupport();

        System.out.println(
                "\nThank You For Using FoodChain");
        RevenueAnalytics.showRevenue();
        
        AdminService admin =
                new AdminService();
        String agentName =
                "Not Assigned";

        if(assignedAgent != null) {

            agentName =
                    assignedAgent.getName();
        }
        AgentAnalytics.showAnalytics();
        admin.showDashboard(
                userService.getTotalUsers(),
                OrderAnalytics.getTotalOrders(),
                RevenueAnalytics.getRevenue(),
                selectedRestaurant.getName(),
                agentName);

        sc.close();
    }
}