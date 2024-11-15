package co.edu.udea.codefactory.sitas.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "flight_number")
    private String flightNumber;

    @JoinColumn(name = "origin")
    @ManyToOne
    private Airport origin;

    @JoinColumn(name = "destination")
    @ManyToOne
    private Airport destination;

    @Column(name = "seats_available")
    private int seatsAvailable;

    @Column(name = "departure_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date departureDate;

    @Column(name = "departure_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Temporal(TemporalType.TIME)
    private Calendar departureTime;

    @Column(name = "arrival_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date arrivalDate;

    @Column(name = "arrival_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Temporal(TemporalType.TIME)
    private Calendar arrivalTime;

    @Column(name = "price")
    private Double price;

    @Column(name = "distance")
    private Double distance;
}
