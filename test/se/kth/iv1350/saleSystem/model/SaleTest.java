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
        Sale saleTest = sale;
        sale.addItem(1);
        if(saleTest.equals(sale))
            fail("sale does not add item");
    }

    @Test
    void endSale() {
        Sale saleTest = sale;
        sale.addItem(1);
        if(saleTest.equals(sale))
            fail("sale doesn't update");
    }

    @Test
    void addPayment() {
        sale.setTotalPrice(20);
        assertEquals(80, sale.addPayment(100));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof SaleTest saleTest))
            return false;
        return Objects.equals(sale, saleTest.sale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sale);
    }
}