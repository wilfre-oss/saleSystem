package se.kth.iv1350.saleSystem.exceptions;

/**
 *  Exception to indicate an insufficient payment
 */
public class InsufficientPaymentException extends Exception {

    /**
     *
     * @param message to be sent on.
     */
    public InsufficientPaymentException(String message){
        super(message);
    }
}
