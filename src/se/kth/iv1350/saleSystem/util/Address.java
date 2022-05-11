package se.kth.iv1350.saleSystem.util;

/**
 * an address object
 */
public class Address {
    private int zipCode;
    private String city;
    private String Street;

    public Address(){

    }

    public Address(int zipCode, String city, String street) {
        this.zipCode = zipCode;
        this.city = city;
        Street = street;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return Street;
    }
}
