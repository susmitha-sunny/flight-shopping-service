package com.gotravel.flightshoppingservice.entity;


import com.gotravel.flightshoppingservice.model.MealType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private int flightId;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;
    /*@Column(name = "airline_id")
    private int airlineId;*/

    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "business_seat_count")
    private int businessSeatCount;

    @Column(name = "economy_seat_count")
    private int economySeatCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "meal")
    private MealType meal;

}
