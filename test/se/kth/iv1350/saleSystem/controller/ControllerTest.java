package se.kth.iv1350.saleSystem.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.saleSystem.model.Sale;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Controller controller = new Controller();


    @BeforeEach
    void setUp() {
        controller.startSale();
    }

    @Test
    void enterItem() {
        Controller testController = new Controller();
        controller.enterItem(1);
        if(testController.equals(controller))
            fail("sale doesn't update in controller");
    }

    @Test
    void endSale() {
        Controller testController = new Controller(controller);
        controller.endSale();
        if(testController.equals(controller))
            fail("sale doesn't update in controller");
    }

    @Test
    void enterAmountPaid() {
        controller.enterItem(1);
        assertAll(() -> assertEquals(80-(10*1.12), controller.enterAmountPaid(80)),
                () -> assertEquals(100-(10*1.12), controller.enterAmountPaid(100)),
              () -> assertEquals(200-(10*1.12), controller.enterAmountPaid(200)),
             () -> assertEquals(500-(10*1.12), controller.enterAmountPaid(500)));

    }


}