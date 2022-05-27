package se.kth.iv1350.saleSystem.exceptions;

import java.sql.SQLException;


/**
 * Exception to indicate that error occurred
 * when trying to connect to a database
 *
 */
public class ConnectionException extends SQLException {

    /**
     *
     * @param message to be sent on.
     */
    public ConnectionException(String message){
        super(message);
    }

    public ConnectionException(){

    }
}
