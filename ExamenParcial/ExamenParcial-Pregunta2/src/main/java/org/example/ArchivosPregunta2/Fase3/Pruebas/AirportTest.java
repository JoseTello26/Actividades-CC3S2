package org.example.ArchivosPregunta2.Fase3.Produccion;

import org.example.ArchivosPregunta2.Fase2.Produccion.BusinessFlight;
import org.example.ArchivosPregunta2.Fase2.Produccion.EconomyFlight;
import org.example.ArchivosPregunta2.Fase2.Produccion.Flight;
import org.example.ArchivosPregunta2.Fase2.Produccion.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AirportTest {

    @DisplayName("Dado que hay un vuelo economico")
    @Nested
    class EconomyFlightTest {

        private org.example.ArchivosPregunta2.Fase2.Produccion.Flight economyFlight;

        @BeforeEach
        void setUp() {
            economyFlight = new EconomyFlight("1");
        }

        @Test
        public void testEconomyFlightRegularPassenger() {
            org.example.ArchivosPregunta2.Fase2.Produccion.Passenger jessica = new org.example.ArchivosPregunta2.Fase2.Produccion.Passenger("Jessica", false);

            assertEquals("1", economyFlight.getId());
            assertEquals(true, economyFlight.addPassenger(jessica));
            assertEquals(1, economyFlight.getPassengers().size());
            assertEquals("Jessica", economyFlight.getPassengers().get(0).getName());

            assertEquals(true, economyFlight.removePassenger(jessica));
            assertEquals(0, economyFlight.getPassengers().size());
        }

        @Test
        public void testEconomyFlightVipPassenger() {
            org.example.ArchivosPregunta2.Fase2.Produccion.Passenger cesar = new org.example.ArchivosPregunta2.Fase2.Produccion.Passenger("Cesar", true);

            assertEquals("1", economyFlight.getId());
            assertEquals(true, economyFlight.addPassenger(cesar));
            assertEquals(1, economyFlight.getPassengers().size());
            assertEquals("Cesar", economyFlight.getPassengers().get(0).getName());

            assertEquals(false, economyFlight.removePassenger(cesar));
            assertEquals(1, economyFlight.getPassengers().size());
        }

    }

    @DisplayName("Dado que hay un vuelo negocios")
    @Nested
    class BusinessFlightTest {
        private Flight businessFlight;

        @BeforeEach
        void setUp() {
            businessFlight = new BusinessFlight("2");
        }

        @Test
        public void testBusinessFlightRegularPassenger() {
            org.example.ArchivosPregunta2.Fase2.Produccion.Passenger jessica = new org.example.ArchivosPregunta2.Fase2.Produccion.Passenger("Jessica", false);

            assertEquals(false, businessFlight.addPassenger(jessica));
            assertEquals(0, businessFlight.getPassengers().size());
            assertEquals(false, businessFlight.removePassenger(jessica));
            assertEquals(0, businessFlight.getPassengers().size());

        }

        @Test
        public void testBusinessFlightVipPassenger() {
            org.example.ArchivosPregunta2.Fase2.Produccion.Passenger cesar = new Passenger("Cesar", true);

            assertEquals(true, businessFlight.addPassenger(cesar));
            assertEquals(1, businessFlight.getPassengers().size());
            assertEquals(false, businessFlight.removePassenger(cesar));
            assertEquals(1, businessFlight.getPassengers().size());

        }

    }
}
