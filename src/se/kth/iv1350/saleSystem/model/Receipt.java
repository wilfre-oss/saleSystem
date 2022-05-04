package se.kth.iv1350.saleSystem.model;

import se.kth.iv1350.saleSystem.util.*;


import java.time.LocalDateTime;
import java.util.List;

public class Receipt {
    private LocalDateTime dateTime;
    private double totalPrice;
    private List<ItemDTO> itemList;
    private Store store;
    private double returnAmount;


    public Receipt(Sale sale, double amountPaid) {
        this.dateTime = sale.getDateTime();
        this.totalPrice = sale.getTotalPrice();
        this.itemList = sale.getItemList();
        this.store = sale.getStore();
        this.returnAmount = amountPaid - totalPrice;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public List<ItemDTO> getItemList() {
        return itemList;
    }

    public Store getStore() {
        return store;
    }

    public double getReturnAmount() {
        return returnAmount;
    }
}
