package se.kth.iv1350.saleSystem.view;

import se.kth.iv1350.saleSystem.model.SaleObserver;

/**
 * Saves the total revenue of all sales.
 */
public class TotalRevenueView implements SaleObserver {
    private double revenue;


    @Override
    public void addToTotalRevenue(double revenue){
        this.revenue += revenue;
        printRevenue();
    }

    private void printRevenue(){
        System.out.printf("Total Revenue: %.2f%n", revenue);
    }
}

