package model;

public class User {

    private String name;
    private String phone;
    private String area;
    private String city;
    private String password;

    public User(
            String name,
            String phone,
            String area,
            String city,
            String password) {

        this.name = name;
        this.phone = phone;
        this.area = area;
        this.city = city;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getArea() {

        if(area.contains(",")) {

            return area.split(",")[0].trim();
        }

        return area;
    }

    public String getCity() {
        return city;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return area + ", " + city;
    }
}