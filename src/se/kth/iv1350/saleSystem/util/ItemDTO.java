package se.kth.iv1350.saleSystem.util;

import se.kth.iv1350.saleSystem.integration.Item;

public class ItemDTO {
    private String name;
    private int itemID;
    private String description;
    private double price;
    private double vat;
    private int quantity;

    public ItemDTO(Item item){
        this.name = item.getName();
        this.itemID = item.getItemID();
        this.description = item.getDescription();
        this.price = item.getPrice();
        this.vat = item.getVat();
        this.quantity = 1;
    }
    public ItemDTO(String name, int itemID, String description, int price, int vat) {
        this.name = name;
        this.itemID = itemID;
        this.description = description;
        this.price = price;
        this.vat = vat;
        this.quantity = 1;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getItemID() {
        return itemID;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public double getVat() {
        return vat;
    }

    public int getQuantity(){
        return quantity;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity;
    }


}
