package se.kth.iv1350.saleSystem.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.saleSystem.integration.ItemCatalog;
import se.kth.iv1350.saleSystem.util.ItemDTO;

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
        ItemDTO itemDTO = new ItemDTO(ic.searchForItem(1));
        sale.addItem(1);
        if(!itemDTO.equals(sale.getItemList().get(0)))
            fail("Adding item to itemList failed");
    }

    @Test
    void addItemItemAlreadyAddedCheck(){
        sale.addItem(1);
        sale.addItem(1);
        assertEquals(2, sale.getItemList().get(0).getQuantity(),
                "Failed to increase quantity when item already added");
        sale.addItem(1);
        sale.addItem(1);
        assertEquals(4, sale.getItemList().get(0).getQuantity(),
                "Failed to increase quantity when item already added");
    }

    @Test
    void calculateTotalPriceCheck(){
        sale.addItem(1);
        sale.addItem(1);
        sale.addItem(2);
        assertEquals(2 * (10 * 1.12) + (15 * 1.25), sale.getTotalPrice(),
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