package se.kth.iv1350.saleSystem.integration;

import org.junit.jupiter.api.Test;

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
        catch (Exception ignored){

        }
    }

    @Test
    void testSearchForItem(){
        int itemID = 1;
        Item foundItem;
        for(Item itemInList: ic.itemList){
            foundItem = ic.searchForItem(itemID++);
            assertEquals(itemInList.getItemID(), foundItem.getItemID(),
                    "SearchForItem doesn't find the correct item");
        }
    }
}