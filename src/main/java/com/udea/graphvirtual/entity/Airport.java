package co.edu.udea.codefactory.sitas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "airport")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "airport_code", unique = true)
    private String airportCode;

    @Column(name = "airport_name", unique = true)
    private String airportName;

    @Column(name = "airport_city")
    private String airportCity;

    @Column(name = "airport_country")
    private String airportCountry;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "origin")
    private Set<Flight> origin;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "destination")
    private Set<Flight> destination;
}
