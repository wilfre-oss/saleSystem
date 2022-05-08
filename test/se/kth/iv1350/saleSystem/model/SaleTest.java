package se.kth.iv1350.saleSystem.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {
    private Sale sale;

    @BeforeEach
    void setUp() {
        sale = new Sale();
    }

    @AfterEach
    void tearDown(){
        sale = null;
    }

    @Test
    void addItem() {
        Sale saleTest = new Sale(sale);
        sale.addItem(1);
        if(saleTest.equals(sale))
            fail("Failed to add Item to Sale");
    }

    @Test
    void endSale() {
        Sale saleTest = new Sale(sale);
        sale.endSale();
        if(saleTest.equals(sale))
            fail("sale doesn't update");
    }

    @Test
    void addPayment() {
        sale.setTotalPrice(20);
        assertAll(
                () -> assertEquals(80, sale.addPayment(100),"Add payment doesn't calculate return amount correctly"),
                () -> assertEquals(30, sale.addPayment(50),"Add payment doesn't calculate return amount correctly"),
                () -> assertEquals(10, sale.addPayment(30),"Add payment doesn't calculate return amount correctly"),
                () -> assertEquals(180, sale.addPayment(200),"Add payment doesn't calculate return amount correctly")
        );
    }

}