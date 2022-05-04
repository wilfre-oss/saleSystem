package se.kth.iv1350.saleSystem.model;
import se.kth.iv1350.saleSystem.util.*;
import se.kth.iv1350.saleSystem.integration.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sale {
    private final ItemCatalog ic;
    private LocalDateTime dateTime;
    private double totalPrice;
    private List<ItemDTO> itemList = new ArrayList<>();
    private Store store;

    public Sale(){
        ic = new ItemCatalog();
    }
    /*
    *searches for item in the itemCatalog and adds them to itemList if found.
    *
    *@param itemID is the identifier used to find the items
    */
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
    /*
    *saves the dateTime and calculates the totalPrice
    */
    public void endSale() {
        this.dateTime = LocalDateTime.now();
        calculateTotalPrice();
    }
    /*
    *Adds the payment to the receipt and returns the amount to return.
    *
    * @param amountPaid is the amount paid for the sale
    * */
    public double addPayment(double amountPaid) {
        Receipt receipt =  new Receipt(this, amountPaid);
        Printer.print(receipt);
        Register.addAmountPaid(amountPaid, receipt.getReturnAmount());
        return receipt.getReturnAmount();
    }

    private void calculateTotalPrice(){
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
