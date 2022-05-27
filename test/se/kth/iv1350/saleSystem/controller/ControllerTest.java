package se.kth.iv1350.saleSystem.controller;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.saleSystem.exceptions.ConnectionException;
import se.kth.iv1350.saleSystem.exceptions.InsufficientPaymentException;
import se.kth.iv1350.saleSystem.exceptions.NoItemFoundException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Controller controller;



    @BeforeEach
    void setUp() {
        controller = new Controller(LogManager.getLogger("Test"));
        controller.startSale();
    }

    @AfterEach
    void tearDown() {
        controller = null;
    }

    @Test
    void testNoItemFoundException() {
       try {
           controller.enterItem(555);
           fail("enterItem fails to catch NoItemFoundException");
       } catch (NoItemFoundException ignored){

       }catch (ConnectionException e){
           fail("Tries and fails to connects to database");
       }
    }

    @Test
    void testCorrectItemAddedTest(){
        try {
            assertEquals(1, controller.enterItem(1).getItemList().get(0).getItemID(),
                        "Incorrect item added in saleDTO");
        } catch (ConnectionException e) {
            fail("enterItem tries and fails to connect to database");
        }
    }

    @Test
    void endSaleTest() {
        assertAll("controller doesn't return saleDTO with correct dateTime",
                ()-> assertEquals(LocalDateTime.now().getSecond(), controller.endSale().getDateTime().getSecond()),
                ()-> assertEquals(LocalDateTime.now().getMinute(), controller.endSale().getDateTime().getMinute()),
                ()-> assertEquals(LocalDateTime.now().getHour(), controller.endSale().getDateTime().getHour()),
                ()-> assertEquals(LocalDateTime.now().getDayOfMonth(), controller.endSale().getDateTime().getDayOfMonth()),
                ()-> assertEquals(LocalDateTime.now().getMonth(), controller.endSale().getDateTime().getMonth()),
                ()-> assertEquals(LocalDateTime.now().getYear(), controller.endSale().getDateTime().getYear())
        );

    }

    @Test
    void enterAmountPaidTest() {
        try {
            controller.enterItem(1);
        } catch (ConnectionException e) {
            fail("enterItem tries and fails to connect to database");
        }
        assertAll("Amount to return to costumer is incorrect",
                () -> assertEquals(80-(10*1.12), controller.enterAmountPaid(80)),
                () -> assertEquals(100-(10*1.12), controller.enterAmountPaid(100)),
                () -> assertEquals(200-(10*1.12), controller.enterAmountPaid(200)),
                () -> assertEquals(500-(10*1.12), controller.enterAmountPaid(500)));

    }

    @Test
    void testNegativeAmountPaid(){
        try {
            controller.enterItem(1);
        } catch (ConnectionException e) {
            fail("enterItem tries and fails to connect to database");
        }
        try{
            controller.enterAmountPaid(-10);
            fail("Program allows negative payment");
        } catch (IllegalArgumentException ignored){

        } catch (InsufficientPaymentException ignored){
            fail("negative payment is bypassed.");
        }
    }

    @Test
    void testInsufficientPaymentException(){
        try {
            controller.enterItem(1);
        } catch (ConnectionException e) {
            fail("enterItem tries and fails to connect to database");
        }
        try{
            controller.enterAmountPaid(10);
            fail("Program allows payment to be less than the total price of the sale");
        } catch (IllegalArgumentException ignored){
            fail("Program reads the paid amount to be <= 0.");
        } catch (InsufficientPaymentException ignored){

        }
    }


}