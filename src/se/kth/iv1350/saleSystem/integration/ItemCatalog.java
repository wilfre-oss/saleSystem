package se.kth.iv1350.saleSystem.integration;

import java.util.ArrayList;
import java.util.List;

public class ItemCatalog {
    List<Item> itemList;

    public ItemCatalog() {
        this.itemList = new ArrayList<>();
        itemList.add(new Item(1, "Ham","Smoked ham" ,10, 1.12));
        itemList.add(new Item(2, "Cheese","switz cheese", 15, 1.25));
        itemList.add(new Item(3, "Beans","Canned beans" ,8, 1.06));
    }

    /*
    *searches for Items in to itemList using itemID and returns the found item
    *
    * @param ItemID used for finding item
    */
    public Item searchForItem(int itemID){
        Item foundItem = null;
        for(Item item: itemList){
            if(item.getItemID() == itemID)
                foundItem = item;
        }
        return foundItem;
    }
}