package models;

public class CartItem {
    private String name;
    private double unitPrice;
    private int quantity;
    private double totalPrice;

    public CartItem(String name, double unitPrice, int quantity, double totalPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

}
