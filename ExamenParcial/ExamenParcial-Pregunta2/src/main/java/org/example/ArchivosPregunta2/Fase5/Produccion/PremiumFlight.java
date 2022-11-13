package org.example.ArchivosPregunta2.Fase5.Produccion;

public class PremiumFlight extends Flight {

    public PremiumFlight(String id) {
        super(id);
    }

    @Override
    public boolean addPassenger(Passenger passenger) {
        if (passenger.isVip()) {
            return passengers.add(passenger);
        }
        return false;
    }

    @Override
    public boolean removePassenger(Passenger passenger) {
        return passengers.remove(passenger);
    }
    // Diseño de la lógica comercial para los pasajeros VIP.Pregunta 9
}
