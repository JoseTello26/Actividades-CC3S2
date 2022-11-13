package org.example;

public class Invoice {

    public enum CustomerType{PERSON, COMPANY};
    private final double value;
    private final String country;
    private final CustomerType customerType;

    public Invoice(double value, String country, CustomerType customerType){
        this.value = value;
        this.country = country;
        this.customerType = customerType;
    }

    public double calculate(){
        double ratio = 0.1;
        return value*ratio;
    }

    public double getValue() {
        return value;
    }

    public String getCountry() {
        return country;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }
}