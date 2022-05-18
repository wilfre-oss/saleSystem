package se.kth.iv1350.saleSystem.exceptions;

public class NoItemFoundException extends RuntimeException{
    public NoItemFoundException(String message){
        super(message);
    }
}
