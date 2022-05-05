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
        Controller testController = controller;
        controller.endSale();
        if(testController.equals(controller))
            fail("sale doesn't update in controller");
    }

    @Test
    void endSale() {
        Controller testController = controller;
        controller.endSale();
        if(testController.equals(controller))
            fail("sale doesn't update in controller");
    }

    @Test
    void enterAmountPaid() {
        assertAll(() -> assertEquals(-20, controller.enterAmountPaid(80)),
                () -> assertEquals(20, controller.enterAmountPaid(100)),
              () -> assertEquals(100, controller.enterAmountPaid(200)),
             () -> assertEquals(400, controller.enterAmountPaid(500)));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ControllerTest that))
            return false;
        return Objects.equals(controller, that.controller);
    }

    @Override
    public int hashCode() {
        return Objects.hash(controller);
    }
}