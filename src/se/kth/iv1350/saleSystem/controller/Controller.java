package se.kth.iv1350.saleSystem.controller;

import se.kth.iv1350.saleSystem.model.Sale;
import se.kth.iv1350.saleSystem.util.*;

public class Controller {
    private Sale sale;

    public Controller(){

    }
    /*
    * Creates a New Sale and assigns it to the controller's sale
    * */
    public void startSale() {
        this.sale = new Sale();
    }

    /*
    *Calls the addItem method and returns saleInfo as a SaleDTO
    *
    * @param itemID is the identifier used for finding items, is sent to sale.
    * */
    public SaleDTO enterItem(int itemID) {
        sale.addItem(itemID);
        return new SaleDTO(sale);
    }

    public SaleDTO endSale() {
        sale.endSale();
        return new SaleDTO(sale);
    }

    public double enterAmountPaid(double amountPaid) {
        return sale.addPayment(amountPaid);
    }
}
