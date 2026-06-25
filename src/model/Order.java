package model;

public class Order {

    private String orderId;
    private String customerName;
    private String restaurantName;
    private String paymentMethod;
    private String orderStatus;
    private String deliveryAgent;
    private String eta;
    private String deliverySlot;
    private String couponCode;
    private double discount;
    private String routeStatus;
    private String trackingStatus;
    private double amount;

    public Order(
            String orderId,
            String customerName,
            String restaurantName,
            double amount,
            String paymentMethod) {

        this.orderId = orderId;
        this.customerName = customerName;
        this.restaurantName = restaurantName;
        this.amount = amount;
        this.paymentMethod = paymentMethod;

        this.orderStatus =
                "Order Placed";
    }
    public String getRouteStatus() {
        return routeStatus;
    }

    public void setRouteStatus(String routeStatus) {
        this.routeStatus = routeStatus;
    }

    public String getTrackingStatus() {
        return trackingStatus;
    }

    public void setTrackingStatus(String trackingStatus) {
        this.trackingStatus = trackingStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(
            String orderStatus) {

        this.orderStatus =
                orderStatus;
    }

    public String getDeliveryAgent() {
        return deliveryAgent;
    }

    public void setDeliveryAgent(
            String deliveryAgent) {

        this.deliveryAgent =
                deliveryAgent;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(
            String eta) {

        this.eta = eta;
    }
    public String getDeliverySlot() {
        return deliverySlot;
    }

    public void setDeliverySlot(
            String deliverySlot) {

        this.deliverySlot =
                deliverySlot;
    }
    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public boolean canCancel() {

        return orderStatus.equals(
                "Preparing Food")
                ||
               orderStatus.equals(
                       "Order Placed");
    }
}