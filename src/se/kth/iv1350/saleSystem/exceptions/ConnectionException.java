package se.kth.iv1350.saleSystem.exceptions;

import java.sql.SQLException;

public class ConnectionException extends SQLException {

    public ConnectionException(String message){
        super(message);
    }
}
