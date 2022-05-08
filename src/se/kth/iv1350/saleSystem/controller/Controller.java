package se.kth.iv1350.saleSystem.controller;

import se.kth.iv1350.saleSystem.model.Sale;
import se.kth.iv1350.saleSystem.util.*;

import java.util.Objects;

public class Controller {
    private Sale sale;

    public Controller(){}
    public Controller(Controller controller){
        this.sale = controller.getSale();
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
        try {
            sale.addItem(itemID);
        } catch (Exception e) {
            System.out.println("No item found.");
        }
        return new SaleDTO(sale);
    }

    public SaleDTO endSale() {
        sale.endSale();
        return new SaleDTO(sale);
    }

    public double enterAmountPaid(double amountPaid) {
        return sale.addPayment(amountPaid);
    }

    public Sale getSale() {
        return sale;
    }
}
