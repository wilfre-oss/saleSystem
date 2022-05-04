package se.kth.iv1350.saleSystem.util;

public class Store {
    private String name;
    private Address address;

    public Store(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }
}
