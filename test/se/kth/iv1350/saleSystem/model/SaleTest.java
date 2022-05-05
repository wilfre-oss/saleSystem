package se.kth.iv1350.saleSystem.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.saleSystem.integration.ItemCatalog;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {
    private Sale sale;

    @BeforeEach
    void setUp() {
        sale = new Sale();
    }

    @Test
    void addItem() {
        Sale saleTest = new Sale(sale);
        sale.addItem(1);
        if(saleTest.equals(sale))
            fail("sale dosen't update");
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
        assertEquals(80, sale.addPayment(100));
    }

}