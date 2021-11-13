package com.gotravel.flightshoppingservice.entity;


import com.gotravel.flightshoppingservice.model.AirlineStatusType;
import lombok.*;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "airline")
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "airline_id")
    private int airlineId;

    @Column(name = "airline_name")
    private String airlineName;

    @Column(name = "iata_code")
    private String iataCode;

    @Lob
    private Blob logo;

    @Enumerated(EnumType.STRING)
    @Column(name = "airline_status")
    private AirlineStatusType airlineStatus;

}
