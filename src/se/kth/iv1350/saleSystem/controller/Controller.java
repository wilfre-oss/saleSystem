package se.kth.iv1350.saleSystem.controller;

import se.kth.iv1350.saleSystem.model.Sale;
import se.kth.iv1350.saleSystem.model.SaleObserver;
import se.kth.iv1350.saleSystem.util.*;
import se.kth.iv1350.saleSystem.exceptions.*;


import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Controller is the class that accesses other classes for the user
 */
public class Controller {
    private Sale sale;
    private final Logger logger;
    private List<SaleObserver> observerList = new ArrayList<>();

    public Controller(Logger logger){
        this.logger = logger;
    }

    /**
    * Creates a New Sale and assigns it to the controller's sale
     * and adds observers to Sale.
    * */
    public void startSale() {
        this.sale = new Sale();
        sale.addSaleObserver(observerList);
    }

    /**
     * Calls the addItem method and returns saleInfo as a SaleDTO
     *
     * @param itemID is the identifier used for finding items, is sent to sale.
     * @return SaleDTO(sale) a DTO of the sale.
     * @throws ConnectionException if unable to reach database.
     */
    public SaleDTO enterItem(int itemID) throws ConnectionException {
        try {
            sale.addItem(itemID);
        }
        catch (IllegalArgumentException ile) {
            System.out.println("Faulty itemID entry.");
            logger.info("Faulty itemID entry.");
        }
        catch (NoItemFoundException e) {
            System.out.println(e.getMessage());
            logger.info(e.getMessage());
        }
        catch (SQLException sqle){
            logger.warn(sqle.getMessage());
            throw new ConnectionException("Connection to database failed.");
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
    * @param amountPaid the amount paid for the sale.
     * @return returnAmount the amount to be returned to the costumer.
    */
    public double enterAmountPaid(double amountPaid) {
        if (amountPaid <= 0)
            throw new IllegalArgumentException("Payment must be provided");
        return sale.addPayment(amountPaid);
    }

    /**
     * adds observer to observerList in controller
     *
     * @param observer the observer to add
     */
    public void addSaleObserver(SaleObserver observer){
        observerList.add(observer);
    }

}
