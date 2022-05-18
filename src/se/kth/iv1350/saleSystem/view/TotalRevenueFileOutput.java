package se.kth.iv1350.saleSystem.view;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se.kth.iv1350.saleSystem.model.SaleObserver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

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
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Revenue.txt"));
            writer.write(" Total Revenue: " + df.format(revenue));
            writer.close();
        } catch (IOException e) {
            logger.error("Logging Total Revenue failed.", e.getCause());
        }
    }
}
