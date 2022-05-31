package se.kth.iv1350.saleSystem.exceptions;

/**
 * Exception to indicate that no item was found.
 */
public class NoItemFoundException extends RuntimeException{
    /**
     *
     * @param message message to be sent on.
     */
    public NoItemFoundException(String message){
        super(message);
    }

    public NoItemFoundException(){

    }
}
