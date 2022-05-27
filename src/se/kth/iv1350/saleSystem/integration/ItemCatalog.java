package se.kth.iv1350.saleSystem.integration;

import se.kth.iv1350.saleSystem.exceptions.NoItemFoundException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
    * @param itemID used for finding item
    * @return item
    * @throws SQLException if connection to database fails.
    * @throws NoItemFoundException if no item is found.
    * @throws IllegalArgumentException if item ID is <= 0.
    * */
    public Item searchForItem(int itemID) throws SQLException {
        if(itemID <= 0)
            throw new IllegalArgumentException();
        if(itemID == 111)
            connectToDB();
        for(Item item: itemList){
            if(item.getItemID() == itemID)
                return item;
        }
        throw new NoItemFoundException("No Item Found.");
    }

    private void connectToDB() throws SQLException {
        String DB_URL = "jdbc:mysql://localhost/noConnectionTest";
        String USER = "guest";
        String PASS = "guest123";
        String QUERY = "{call getEmpName (?, ?)}";

        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        conn.prepareCall(QUERY);
    }
}