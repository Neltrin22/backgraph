package co.edu.udea.codefactory.sitas.repository;

import co.edu.udea.codefactory.sitas.entity.Airport;
import co.edu.udea.codefactory.sitas.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface FlightRepository extends JpaRepository<Flight, UUID>, JpaSpecificationExecutor<Flight> {
    List<Flight> findByDepartureDateEqualsAndOriginEqualsAndDestinationEquals(Date departureTime, Airport departureAirport, Airport arrivalAirport);

    List<Flight> findByDepartureDateEqualsAndOriginEqualsAndDestinationEqualsAndDepartureTimeBetween(Date departureDate, Airport departureAirport, Airport arrivalAirport, Calendar departureTime, Calendar finalDepartureTime);

    List<Flight> findByDepartureDateEqualsAndOriginEqualsAndDestinationEqualsAndDepartureTimeEquals(Date departureDate, Airport departureAirport, Airport arrivalAirport, Calendar departureTime);

    List<Flight> findByDepartureDateEqualsAndOriginEqualsAndDestinationEqualsAndArrivalTimeBetween(Date departureDate, Airport departureAirport, Airport arrivalAirport, Calendar arrivalTime, Calendar finalArrivalTime);

    List<Flight> findByDepartureDateEqualsAndOriginEqualsAndDestinationEqualsAndArrivalTimeEquals(Date departureDate, Airport departureAirport, Airport arrivalAirport, Calendar arrivalTime);
}
