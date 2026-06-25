package model;

public class DeliveryAgent {

    private String agentId;
    private String name;
    private String phone;
    private String vehicle;
    private String zone;
    private String status;
    private double rating;

    public DeliveryAgent(String agentId,
                         String name,
                         String phone,
                         String vehicle,
                         String zone,
                         String status,
                         double rating) {

        this.agentId = agentId;
        this.name = name;
        this.phone = phone;
        this.vehicle = vehicle;
        this.zone = zone;
        this.status = status;
        this.rating = rating;
    }
    private boolean available = true;

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getVehicle() {
        return vehicle;
    }

    public String getZone() {
        return zone;
    }

    public String getStatus() {
        return status;
    }

    public double getRating() {
        return rating;
    }
}