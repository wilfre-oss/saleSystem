package se.kth.iv1350.saleSystem.exceptions;

public class NoItemFoundException extends RuntimeException{
    /**
     * Exception to indicate that no item could be found.
     *
     * @param message message to be sent on.
     */
    public NoItemFoundException(String message){
        super(message);
    }

    public NoItemFoundException(){

    }
}
