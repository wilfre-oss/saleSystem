package se.kth.iv1350.saleSystem.integration;

import java.util.ArrayList;
import java.util.List;

public class ItemCatalog {
    List<Item> itemList;

    public ItemCatalog() {
        this.itemList = new ArrayList<>();
        itemList.add(new Item(1, "Ham", 10, 1.12));
        itemList.add(new Item(2, "Cheese", 15, 1.20));
        itemList.add(new Item(3, "Beans",8, 1.10));
    }

    public Item searchForItem(int itemID){
        Item foundItem = new Item(0, "Null");
        for(Item item: itemList){
            if(item.getItemID() == itemID)
                foundItem = item;
        }
        return foundItem;
    }
}