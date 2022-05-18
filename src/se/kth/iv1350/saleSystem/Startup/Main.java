package se.kth.iv1350.saleSystem.Startup;

import org.apache.logging.log4j.LogManager;
import se.kth.iv1350.saleSystem.controller.Controller;
import se.kth.iv1350.saleSystem.view.View;



/**
 * is the startUp of the program
 * contains the main method
 */
public class Main {
    public static void main(String[] args){
        Controller contr = new Controller(LogManager.getLogger("Controller"));
        View view = new View(contr, LogManager.getLogger());
        view.runView();
    }

}
