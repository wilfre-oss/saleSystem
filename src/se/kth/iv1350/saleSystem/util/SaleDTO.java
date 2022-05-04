package se.kth.iv1350.saleSystem.util;


import se.kth.iv1350.saleSystem.model.Sale;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class SaleDTO {
    private LocalDateTime dateTime;
    private double totalPrice;
    private List<ItemDTO> itemList;
    private Store store;
    private double totalVat;

    public SaleDTO(Sale sale){
        this.dateTime = sale.getDateTime();
        this.totalPrice = sale.getTotalPrice();
        this.itemList = sale.getItemList();
        this.store = sale.getStore();
        this.totalVat = calculateTotalVAT();
    }

    private double calculateTotalVAT(){
        double sumOfPrice = 0;
        for (ItemDTO itemInList: itemList)
            sumOfPrice += itemInList.getPrice() * itemInList.getQuantity();
        return ((totalPrice - sumOfPrice) / sumOfPrice) * 100;
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

    public double getTotalVat() {
        return totalVat;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String result = itemList.stream().
                map(String::valueOf).
                collect(Collectors.joining("\n"));
        return  dtf.format(dateTime) + "\n" +
                result + "\n" +
                String.format("Total: %.2f", totalPrice) + "   " +
                String.format("total VAT: %.2f", totalVat) + "%\n"
                ;
    }
}
