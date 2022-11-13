package org.example;

public class InvoiceBuilder {
    private String country = "NL";
    private Invoice.CustomerType customerType = Invoice.CustomerType.PERSON;
    private double value = 500;
    public InvoiceBuilder withCountry(String country){
        this.country = country;
        return this;
    }
    public InvoiceBuilder asCompany(){
        this.customerType = Invoice.CustomerType.COMPANY;
        return this;
    }
    public InvoiceBuilder withAValueOf(double value){
        this.value = value;
        return this;
    }
    public Invoice build(){
        return new Invoice(value, country, customerType);
    }

    public Invoice anyCompany() {
        return new Invoice(value, country, Invoice.CustomerType.COMPANY);
    }
    public Invoice fromTheUS() {
        return new Invoice(value,"US", customerType);
    }
}
