package se.kth.iv1350.saleSystem.integration;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ItemCatalogTest {
    ItemCatalog ic = new ItemCatalog();

    @Test
    void testInvalidItemID() {
        try{
            ic.searchForItem(555);
            fail("Item search returns nonexistent item");
        }
        catch (NullPointerException ignored){

        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
            fail("Try and fails to connect to database");
        }
    }

    @Test
    void testSearchForItem(){
        int itemID = 1;
        Item foundItem;
        for(Item itemInList: ic.itemList){
            try {
                foundItem = ic.searchForItem(itemID++);
                assertEquals(itemInList.getItemID(), foundItem.getItemID(),
                        "SearchForItem doesn't find the correct item");
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                fail("Failure to connect to database");
            }

        }
    }

    @Test
    void testConnectionFailure(){
        int itemID = 111;
        try {
            ic.searchForItem(itemID);
            fail("Doesn't throw connection exception");
        } catch (SQLException ignored) {

        }
    }
}