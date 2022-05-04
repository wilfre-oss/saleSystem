package se.kth.iv1350.saleSystem.view;
import se.kth.iv1350.saleSystem.controller.Controller;
import se.kth.iv1350.saleSystem.util.*;

import java.util.Scanner;

public class View {
    private Controller contr;
    private SaleDTO saleInfo;
    Scanner in = new Scanner(System.in);

    public View(Controller contr){
        this.contr = contr;
    }

    public void runView(){
        System.out.println("Starting view");
        boolean exit = false;
        while(!exit){
            contr.startSale();
            while(true){
                System.out.println("Scan next item");
                int itemID = in.nextInt();
                if(itemID == 0)
                    break;
                saleInfo = contr.enterItem(itemID);
            }
            in.nextLine();
            saleInfo = contr.endSale();
            System.out.println("Sale Ended \n");
            printSaleInfo();
            System.out.println("Enter amount paid.");
            double returnAmount = contr.enterAmountPaid(in.nextInt());
            System.out.println(String.format("Amount to Return: %.2f", returnAmount));
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
}
