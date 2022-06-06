package se.kth.iv1350.saleSystem.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Controller controller;



    @BeforeEach
    void setUp() {
        controller = new Controller();
        controller.startSale();
    }

    @AfterEach
    void tearDown() {
        controller = null;
    }

    @Test
    void enterItemCatchExceptionTest() {
       try {
           controller.enterItem(555);
           fail("enterItem fails to catch exception");
       } catch (Exception e){
           
       }
    }

    @Test
    void enterItemCorrectItemAddedTest(){
        assertEquals(1, controller.enterItem(1).getItemList().get(0).getItemID(),
                    "Incorrect item added in saleDTO");
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
        controller.enterItem(1);
        assertAll("Amount to return to costumer is incorrect",
                () -> assertEquals(80-(10*1.12), controller.enterAmountPaid(80)),
                () -> assertEquals(100-(10*1.12), controller.enterAmountPaid(100)),
                () -> assertEquals(200-(10*1.12), controller.enterAmountPaid(200)),
                () -> assertEquals(500-(10*1.12), controller.enterAmountPaid(500)));

    }


}
