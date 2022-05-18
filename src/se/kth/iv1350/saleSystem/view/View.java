package se.kth.iv1350.saleSystem.view;
import org.apache.logging.log4j.Logger;
import se.kth.iv1350.saleSystem.controller.Controller;
import se.kth.iv1350.saleSystem.exceptions.*;
import se.kth.iv1350.saleSystem.util.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class View {
    private final Controller contr;
    private SaleDTO saleInfo;
    Scanner in = new Scanner(System.in);
    private final Logger logger;

    public View(Controller contr, Logger logger){
        this.logger = logger;
        this.contr = contr;
        this.contr.addSaleObserver(new TotalRevenueView());
        this.contr.addSaleObserver(new TotalRevenueFileOutput());
    }

    /**
    * the coded view that call from the controller to add items to the sale
    * and gets the saleInfo in return to display to the user
    *
    */
    public void runView(){
        System.out.println("Starting view");
        int itemID;
        boolean exit = false;
        while(!exit){
            contr.startSale();
            while(true){
                System.out.println("Scan next item");
                while(true){
                    try{
                        itemID = in.nextInt();
                        break;
                    }catch (InputMismatchException e){
                        in.next();
                        System.out.println("Faulty itemID entry");
                        logger.debug("Faulty itemID entry");
                    }
                }
                if(itemID == 0)
                    break;
                try {
                    saleInfo = contr.enterItem(itemID);
                } catch (ConnectionException e) {
                    System.out.println(e.getMessage());
                    logger.warn(e.getMessage());
                }
            }
            in.nextLine();
            saleEnded();
            exit = exitView();
        }
    }

    private void printSaleInfo() {
        System.out.println(saleInfo.toString());
    }

    private boolean exitView(){
        System.out.println("\nDo you want to start a new sale?");
        int choice = in.nextInt();
        System.out.println();
        if(choice == 0){
            return true;
        }
        return choice != 1;
    }

    private void saleEnded(){
        saleInfo = contr.endSale();
        System.out.println("Sale Ended \n");
        printSaleInfo();
        System.out.println("Enter amount paid.");
        while(true){
            try{
                double returnAmount = contr.enterAmountPaid(in.nextDouble());
                System.out.printf("Amount to Return: %.2f%n", returnAmount);
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                logger.info(e.getMessage());
            }catch (InputMismatchException e) {
                in.next();
                System.out.println("Invalid input for payment, must be provided as a number");
                logger.info("Invalid input", e.getCause());
            }
        }
    }
}
