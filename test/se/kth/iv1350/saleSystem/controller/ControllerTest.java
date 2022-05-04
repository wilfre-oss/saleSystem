package se.kth.iv1350.saleSystem.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.saleSystem.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Sale sale;


    @BeforeEach
    void setUp() {
        sale = new Sale();
        sale.setTotalPrice(100);
    }

    @Test
    void startSale() {
    }

    @Test
    void enterItem() {
    }

    @Test
    void endSale() {

    }

    @Test
    void enterAmountPaid() {
        assertAll(() -> assertEquals(-20, sale.addPayment(80)),
                () -> assertEquals(20, sale.addPayment(120)),
              () -> assertEquals(100, sale.addPayment(200)),
             () -> assertEquals(400, sale.addPayment(500)));

    }
}