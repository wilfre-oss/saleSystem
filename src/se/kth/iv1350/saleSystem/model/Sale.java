package se.kth.iv1350.saleSystem.model;

import se.kth.iv1350.saleSystem.util.*;
import se.kth.iv1350.saleSystem.integration.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Sale get amd stores the information of the sale
 * to be sent down to the controller
 */
public class Sale {
    private final ItemCatalog ic;
    private LocalDateTime dateTime;
    private double totalPrice;
    private List<ItemDTO> itemList = new ArrayList<>();
    private Store store;
    private List<SaleObserver> observerList = new ArrayList<>();


    public Sale(){
        ic = new ItemCatalog();
    }

    /**
    * searches for item in the itemCatalog and adds them to itemList if found.
    * ic.searchForItem() throws exception if no item is found.
    * ends with calculation the totalPrice from all items in the itemList.
    *
    *@param itemID is the identifier used to find the items
    */
    public void addItem(int itemID) throws SQLException {
        Item foundItem = ic.searchForItem(itemID);
        addToItemList(foundItem);
        calculateTotalPrice();
    }

    private void addToItemList(Item foundItem){
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
    }

    /**
    *saves the dateTime and calculates the totalPrice
     * and notifies the observers
    */
    public void endSale() {
        this.dateTime = LocalDateTime.now();
        calculateTotalPrice();
        notifyObservers();
    }
    /**
    * Adds the payment to the receipt and returns the amount to return.
    *
    * @param amountPaid is the amount paid for the sale
     * @return receipt.returnAmount the amount to be returned to the costumer
    * */
    public double addPayment(double amountPaid) {
        Receipt receipt =  new Receipt(this, amountPaid);
        if(receipt.getReturnAmount() < 0)
            throw new IllegalArgumentException("Payment must cover whole price.");
        Printer.print(receipt);
        Register.addAmountPaid(amountPaid, receipt.getReturnAmount());
        return receipt.getReturnAmount();
    }

    private void calculateTotalPrice(){
        double totalPrice = 0;

        for(ItemDTO itemInList: itemList){
            totalPrice += itemInList.getPrice() * itemInList.getVat() * itemInList.getQuantity();
        }

        this.totalPrice = (double)Math.round(totalPrice * 100000d) / 100000d;
    }

    private void notifyObservers(){
        for(SaleObserver observer: observerList){
            observer.addToTotalRevenue(totalPrice);
        }
    }

    /**
     * adds observer to observerList.
     *
     * @param observers the observer to add.
     */
    public void addSaleObserver(List<SaleObserver> observers){
        observerList.addAll(observers);
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

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Sale sale))
            return false;
        return Double.compare(sale.getTotalPrice(),
                getTotalPrice()) == 0 && Objects.equals(ic, sale.ic) &&
                Objects.equals(getDateTime(), sale.getDateTime()) &&
                Objects.equals(getItemList(), sale.getItemList()) &&
                Objects.equals(getStore(), sale.getStore());
    }

    @Override
    public int hashCode() {
        return Objects.hash(ic, getDateTime(), getTotalPrice(), getItemList(), getStore());
    }
}
