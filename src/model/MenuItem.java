package model;

public class MenuItem {

    private int serialNo;
    private String itemName;
    private double price;
    private String category;

    public MenuItem(int serialNo,
                    String itemName,
                    double price,
                    String category) {

        this.serialNo = serialNo;
        this.itemName = itemName;
        this.price = price;
        this.category = category;
    }

    public int getSerialNo() {
        return serialNo;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}