package co.edu.udea.codefactory.sitas.controller;

import co.edu.udea.codefactory.sitas.entity.Airport;
import co.edu.udea.codefactory.sitas.entity.Flight;
import co.edu.udea.codefactory.sitas.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Controller
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    Logger logger = LoggerFactory.getLogger(FlightController.class);

    @QueryMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @QueryMapping
    public Flight getFlightById(@Argument UUID id) {
        return flightService.getFlightById(id);
    }

    @QueryMapping
    public List<Flight> findFlightsByOriginDestinationDate(
            @Argument String departureDate,
            @Argument UUID origin,
            @Argument UUID destination
            ) throws ParseException {
        Airport departureAirport = new Airport();
        Airport arrivalAirport = new Airport();
        departureAirport.setId(origin);
        arrivalAirport.setId(destination);
        return flightService.findByDepartureDateEqualsAndOriginEqualsAndDestinationEquals(
                new SimpleDateFormat("yyyy-MM-dd").parse(departureDate),
                departureAirport,
                arrivalAirport);
    }

    @QueryMapping
    public List<Flight> findFlightsByOriginDestinationDateFilteredByDepartureTime(
            @Argument String departureDate,
            @Argument UUID origin,
            @Argument UUID destination,
            @Argument String departureTime,
            @Argument String finalDepartureTime
    ) throws ParseException {
        Airport departureAirport = new Airport();
        Airport arrivalAirport = new Airport();
        departureAirport.setId(origin);
        arrivalAirport.setId(destination);
        return flightService.findByDepartureDateEqualsAndOriginEqualsAndDestinationEqualsAndDepartureTimeRange(
                new SimpleDateFormat("yyyy-MM-dd").parse(departureDate),
                departureAirport,
                arrivalAirport,
                departureTime,
                finalDepartureTime);
    }

    @QueryMapping
    public List<Flight> findFlightsByOriginDestinationDateFilteredByArrivalTime(
            @Argument String departureDate,
            @Argument UUID origin,
            @Argument UUID destination,
            @Argument String arrivalTime,
            @Argument String finalArrivalTime
    ) throws ParseException {
        Airport departureAirport = new Airport();
        Airport arrivalAirport = new Airport();
        departureAirport.setId(origin);
        arrivalAirport.setId(destination);
        return flightService.findByDepartureDateEqualsAndOriginEqualsAndDestinationEqualsAndArrivalTimeRange(
                new SimpleDateFormat("yyyy-MM-dd").parse(departureDate),
                departureAirport,
                arrivalAirport,
                arrivalTime,
                finalArrivalTime);
    }
}
