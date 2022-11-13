package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class InvoiceTest {
    @DisplayName("Prueba del método calculate")
    @Test
    void calculateTest(){
        double value = new BigDecimal("2500").doubleValue();

        Invoice customer = new InvoiceBuilder().withAValueOf(value).fromTheUS();

        double result = customer.calculate();
        assertThat(result).isEqualTo(250);
        assertThat(customer.getCustomerType()).isEqualTo(Invoice.CustomerType.PERSON);
        assertThat(customer.getCountry()).isEqualTo("US");
    }

    @Test
    void taxesForCompanyAreTaxRateMultipliedByAmount() {
        double invoiceValue = 2500.0;
        double tax = 0.1;
        Invoice invoice = new InvoiceBuilder().asCompany().withCountry("NL").withAValueOf(invoiceValue).build();
        double calculatedValue = invoice.calculate();
        assertThat(calculatedValue).isEqualTo(invoiceValue * tax);
    }
}