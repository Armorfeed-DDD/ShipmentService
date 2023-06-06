package com.armorfeed.api.shipments.domain.entities;

import lombok.*;

import javax.persistence.*;

import com.armorfeed.api.shipments.domain.enums.ShipmentStatus;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@With
@AllArgsConstructor
@Table(name = "shipments")
public class Shipment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String origin_city;

    @Column(nullable = false)
    private String destiny_city;

    @Column(nullable = false)
    private Date pick_up_date;

    @Column(nullable = false)
    private Date delivery_date;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private ShipmentStatus status;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private String current_country;

    @Column(nullable = false)
    private String current_city;

    @Column(nullable = false)
    private Long latitude;

    @Column(nullable = false)
    private Long longitude;

    @Column
    private Long vehicle_id;

    @Column(nullable = false)
    private Long users_enterprise_id;

    @Column(nullable = false)
    private Long users_customer_id;
}
