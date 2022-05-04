package se.kth.iv1350.saleSystem.model;
import se.kth.iv1350.saleSystem.util.*;
import se.kth.iv1350.saleSystem.integration.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sale {
    private ItemCatalog ic;
    private LocalDateTime dateTime;
    private double totalPrice;
    private List<ItemDTO> itemList = new ArrayList<>();
    private Store store;

    public Sale(){
        ic = new ItemCatalog();
    }

    public Sale addItem(int itemID){
        Item foundItem = ic.searchForItem(itemID);
        if(foundItem.getItemID() == 0){
            System.out.println("No item Found!");
            return this;
        }
        ItemDTO itemToAdd = new ItemDTO(foundItem);
        boolean itemAlreadyAdded = false;
        for (ItemDTO itemInList: itemList) {
            if(itemInList.getItemID() == itemToAdd.getItemID()){
                itemInList.increaseQuantity();
                itemAlreadyAdded = true;
            }
        }
        if(!itemAlreadyAdded)
            itemList.add(itemToAdd);
        calculateTotalPrice();
        return this;
    }

    public void endSale() {
        this.dateTime = LocalDateTime.now();
        calculateTotalPrice();
    }

    public double addPayment(double amountPaid) {
        Receipt receipt =  new Receipt(this, amountPaid);
        Printer.print(receipt);
        Register.addAmountPaid(amountPaid, receipt.getReturnAmount());
        return receipt.getReturnAmount();
    }

    public void calculateTotalPrice(){
        double totalPrice = 0;
        for(ItemDTO itemInList: itemList){
            totalPrice += itemInList.getPrice() * itemInList.getVat() * itemInList.getQuantity();
        }
        this.totalPrice = totalPrice;
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
}
