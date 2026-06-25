package model;

public class Restaurant {

    private String restaurantId;
    private String name;
    private String location;
    private String city;
    private String zone;
    private String area;
    private double rating;

    public Restaurant(
            String restaurantId,
            String name,
            String location,
            String city,
            String zone) {

        this.restaurantId = restaurantId;
        this.name = name;
        this.location = location;
        this.city = city;
        this.zone = zone;

        this.area =
                extractArea(location);

        this.rating =
                3.5 + Math.random() * 1.5;
    }

    private String extractArea(
            String location) {

        if(location == null
                || location.isBlank()) {

            return "";
        }

        String[] parts =
                location.split(",");

        return parts[0].trim();
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {

        if(location == null) {
            return "";
        }

        return location;
    }

    public String getArea() {

        return location
                .split(",")[0]
                .trim();
    }

    public String getCity() {
        return city;
    }

    public String getZone() {
        return zone;
    }

    public double getRating() {
        return rating;
    }
    

    public void setRating(
            double rating) {

        this.rating = rating;
    }

    @Override
    public String toString() {

        return "Restaurant ID : "
                + restaurantId
                + "\nName : "
                + name
                + "\nArea : "
                + area
                + "\nLocation : "
                + location
                + "\nCity : "
                + city
                + "\nZone : "
                + zone
                + "\nRating : "
                + String.format("%.1f", rating);
    }
}