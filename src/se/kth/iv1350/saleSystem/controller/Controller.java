package se.kth.iv1350.saleSystem.controller;

import se.kth.iv1350.saleSystem.model.Sale;
import se.kth.iv1350.saleSystem.util.*;

import java.sql.SQLException;
import java.util.Objects;


/**
 * Controller is the class that accesses other classes for the user
 */
public class Controller {
    private Sale sale;

    public Controller(){}

    /**
    * Creates a New Sale and assigns it to the controller's sale
    * */
    public void startSale() {
        this.sale = new Sale();
    }

    /**
     * Calls the addItem method and returns saleInfo as a SaleDTO
     *
     * @param itemID is the identifier used for finding items, is sent to sale.
     * @return SaleDTO of the sale
     */
    public SaleDTO enterItem(int itemID) {
        try {
            sale.addItem(itemID);
        }
        catch (IllegalArgumentException ile) {
            System.out.println("Faulty itemID entry.");
        }
        catch (NullPointerException e) {
            System.out.println("No item found.");
        }
        catch (SQLException sqle){
            System.out.println("Connection to database failed.");
        }
        return new SaleDTO(sale);
    }

    /**
    * makes a call to sale for final updates and to log the dateTime
    *
     * @return saleDTO of the sale
    */
    public SaleDTO endSale() {
        sale.endSale();
        return new SaleDTO(sale);
    }

    /**
    * sends the paid amount to sale for storage and calculation of return amount.
    *
     * @return returnAmount the amount to be returned to the costumer.
    */
    public double enterAmountPaid(double amountPaid) {
        return sale.addPayment(amountPaid);
    }

}
