package se.kth.iv1350.saleSystem.integration;

public class Item {
    private int itemID;
    private String name;
    private String description;
    private double price;
    private double vat;

    public Item(){

    }

    public Item(int itemID, String name){
        this.itemID = itemID;
        this.name = name;
    }

    public Item(int itemID, String name, double price, double vat) {
        this.itemID = itemID;
        this.name = name;
        this.price = price;
        this.vat = vat;
    }

    public Item(int itemID, String name, String description, double price, double vat) {
        this.itemID = itemID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.vat = vat;
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
}
