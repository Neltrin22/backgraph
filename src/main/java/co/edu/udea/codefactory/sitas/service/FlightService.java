package co.edu.udea.codefactory.sitas.service;

import co.edu.udea.codefactory.sitas.entity.Airport;
import co.edu.udea.codefactory.sitas.entity.Flight;
import co.edu.udea.codefactory.sitas.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(UUID id) {
        return flightRepository.findById(id).orElseThrow(() -> new
                RuntimeException("Flight not found"));
    }

    public List<Flight> findByDepartureDateEqualsAndOriginEqualsAndDestinationEquals(
            Date departureDate,
            Airport departureAirport,
            Airport arrivalAirport) {
        return flightRepository.findByDepartureDateEqualsAndOriginEqualsAndDestinationEquals(departureDate, departureAirport, arrivalAirport);
    }

    public List<Flight> findByDepartureDateEqualsAndOriginEqualsAndDestinationEqualsAndDepartureTimeRange(
            Date departureDate,
            Airport origin,
            Airport destination,
            String departureTime,
            String finalDepartureTime
    ) throws ParseException {
        Calendar initialTime = Calendar.getInstance();
        initialTime.setTime(new SimpleDateFormat("HH:mm:ss").parse(departureTime));
        if (finalDepartureTime != null) {
            Calendar finalTime = Calendar.getInstance();
            finalTime.setTime(new SimpleDateFormat("HH:mm:ss").parse(finalDepartureTime));
            return flightRepository.findByDepartureDateEqualsAndOriginEqualsAndDestinationEqualsAndDepartureTimeBetween(departureDate, origin, destination, initialTime, finalTime);
        }
        else{
            return flightRepository.findByDepartureDateEqualsAndOriginEqualsAndDestinationEqualsAndDepartureTimeEquals(departureDate, origin, destination, initialTime);
        }
    }

    public List<Flight> findByDepartureDateEqualsAndOriginEqualsAndDestinationEqualsAndArrivalTimeRange(
            Date departureDate,
            Airport origin,
            Airport destination,
            String arrivalTime,
            String finalArrivalTime
    ) throws ParseException {
        Calendar initialTime = Calendar.getInstance();
        initialTime.setTime(new SimpleDateFormat("HH:mm:ss").parse(arrivalTime));
        if (finalArrivalTime != null) {
            Calendar finalTime = Calendar.getInstance();
            finalTime.setTime(new SimpleDateFormat("HH:mm:ss").parse(finalArrivalTime));
            return flightRepository.findByDepartureDateEqualsAndOriginEqualsAndDestinationEqualsAndArrivalTimeBetween(departureDate, origin, destination, initialTime, finalTime);
        }
        else{
            return flightRepository.findByDepartureDateEqualsAndOriginEqualsAndDestinationEqualsAndArrivalTimeEquals(departureDate, origin, destination, initialTime);
        }
    }
}
