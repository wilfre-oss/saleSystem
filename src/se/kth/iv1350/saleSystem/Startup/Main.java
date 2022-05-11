package se.kth.iv1350.saleSystem.Startup;

import se.kth.iv1350.saleSystem.controller.Controller;
import se.kth.iv1350.saleSystem.view.View;

/**
 * is the startUp of the program
 */
public class Main {
    public static void main(String[] args){
        Controller contr = new Controller();
        View view = new View(contr);
        view.runView();
    }

}
