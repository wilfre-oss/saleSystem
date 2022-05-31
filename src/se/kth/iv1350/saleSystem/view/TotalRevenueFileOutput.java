package se.kth.iv1350.saleSystem.view;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se.kth.iv1350.saleSystem.model.SaleObserver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * calculates the total revenue and log it to a file.
 */
public class TotalRevenueFileOutput implements SaleObserver {
    private double revenue;
    private final Logger logger = LogManager.getLogger();

    @Override
    public void addToTotalRevenue(double revenue){
        this.revenue += revenue;
        printToFile();
    }

    private void printToFile(){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Revenue.txt", true));
            writer.write(dtf.format(LocalDateTime.now()) + " || Total Revenue: " + df.format(revenue) + "\n");
            writer.close();
        } catch (IOException e) {
            logger.error("Logging Total Revenue failed.", e.getCause());
        }
    }
}
