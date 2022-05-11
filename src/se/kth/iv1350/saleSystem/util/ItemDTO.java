package se.kth.iv1350.saleSystem.util;

import se.kth.iv1350.saleSystem.integration.Item;

import java.util.Objects;

/**
 * DTO of item, stores item info.
 */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemDTO itemDTO)) return false;
        return getItemID() == itemDTO.getItemID() &&
                Double.compare(itemDTO.getPrice(), getPrice()) == 0
                && Double.compare(itemDTO.getVat(), getVat()) == 0
                && getQuantity() == itemDTO.getQuantity()
                && Objects.equals(getName(), itemDTO.getName())
                && Objects.equals(getDescription(), itemDTO.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getItemID(), getDescription(), getPrice(), getVat(), getQuantity());
    }
}
