package se.kth.iv1350.saleSystem.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.saleSystem.integration.ItemCatalog;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {
    private Sale sale;

    @BeforeEach
    void setUp() {
        sale = new Sale();
        sale.addItem(1);

    }

    @Test
    void addItem() {

    }

    @Test
    void endSale() {
    }

    @Test
    void addPayment() {
        sale.setTotalPrice(20);
        assertEquals(80, sale.addPayment(100));
    }

}