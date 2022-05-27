package se.kth.iv1350.saleSystem.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.saleSystem.integration.ItemCatalog;
import se.kth.iv1350.saleSystem.util.ItemDTO;

import java.sql.SQLException;
import java.time.LocalDateTime;

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
    void addItemToItemListCheck() {
        ItemCatalog ic = new ItemCatalog();
        ItemDTO itemDTO = null;
        try {
            itemDTO = new ItemDTO(ic.searchForItem(1));
            sale.addItem(1);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            fail("Failure to connect to database");
        }
        if(!itemDTO.equals(sale.getItemList().get(0)))
            fail("Adding item to itemList failed");
    }

    @Test
    void addItemItemAlreadyAddedCheck(){
        try {
            sale.addItem(1);
            sale.addItem(1);
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Failure to connect to database");
        }
        assertEquals(2, sale.getItemList().get(0).getQuantity(),
                "Failed to increase quantity when item already added");
        try {
            sale.addItem(1);
            sale.addItem(1);
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Failure to connect to database");
        }
        assertEquals(4, sale.getItemList().get(0).getQuantity(),
                "Failed to increase quantity when item already added");
    }

    @Test
    void calculateTotalPriceCheck(){
        try {
            sale.addItem(1);
            sale.addItem(1);
            sale.addItem(2);
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Failure to connect to database");
        }
        double expected = (double)Math.round((2 * (10 * 1.12) + (15 * 1.25)) * 100000d) / 100000d;
        assertEquals(expected, sale.getTotalPrice(),
                    "TotalPrice calculation failed");
    }

    @Test
    void addItemInvalidItemIDCheck(){
        int itemID = 4;
        try {
            sale.addItem(itemID);
            fail("Invalid itemID check failed");
        } catch (Exception ignored){

        }
    }

    @Test
    void endSaleTest() {
        sale.endSale();
        assertEquals(LocalDateTime.now().getSecond(), sale.getDateTime().getSecond(),
                    "dateTime in sale not updated correctly");
    }

    @Test
    void addPaymentTest() {
        sale.setTotalPrice(20);
        assertAll(
                () -> assertEquals(80, sale.addPayment(100),"Add payment doesn't calculate return amount correctly"),
                () -> assertEquals(30, sale.addPayment(50),"Add payment doesn't calculate return amount correctly"),
                () -> assertEquals(10, sale.addPayment(30),"Add payment doesn't calculate return amount correctly"),
                () -> assertEquals(180, sale.addPayment(200),"Add payment doesn't calculate return amount correctly")
        );
    }

}