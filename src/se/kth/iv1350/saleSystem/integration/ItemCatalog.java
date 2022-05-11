package se.kth.iv1350.saleSystem.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * ItemCatalog is the class used for storing items
 * and searching for them using itemID
 */
public class ItemCatalog {
    List<Item> itemList;

    public ItemCatalog() {
        this.itemList = new ArrayList<>();
        itemList.add(new Item(1, "Ham","Smoked ham" ,10, 1.12));
        itemList.add(new Item(2, "Cheese","switz cheese", 15, 1.25));
        itemList.add(new Item(3, "Beans","Canned beans" ,8, 1.06));
    }

    /**
    * searches for Items in to itemList using itemID and returns the found item.
     * throws exception if no item is found.
    *
    * @param itemID used for finding item
     * @return item
    */
    public Item searchForItem(int itemID){
        if(itemID <= 0)
            throw new IllegalArgumentException();
        for(Item item: itemList){
            if(item.getItemID() == itemID)
                return item;
        }
        return null;
    }
}