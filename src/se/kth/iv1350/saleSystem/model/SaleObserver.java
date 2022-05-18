package se.kth.iv1350.saleSystem.model;

public interface SaleObserver {
    /**
     * adds total price of a sale to total revenue of all sales
     *
     * @param revenue the revenue to be added to total.
     */
    void addToTotalRevenue(double revenue);


}
