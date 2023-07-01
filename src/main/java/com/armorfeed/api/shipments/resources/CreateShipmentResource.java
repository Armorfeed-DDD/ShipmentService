package com.armorfeed.api.shipments.resources;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.armorfeed.api.shipments.domain.enums.ShipmentStatus;
import com.armorfeed.api.shipments.utils.ValidateShipmentStatus;

import lombok.Getter;

@Getter
public class CreateShipmentResource {

    @NotNull
    @NotBlank
    @NotEmpty
    private String origin_city;

    @NotNull
    @NotBlank
    @NotEmpty
    private String destiny_city;

    @NotNull
    private Date pick_up_date;

    @NotNull
    private Date delivery_date;

    @ValidateShipmentStatus(
        enumClass = ShipmentStatus.class,
        message = "Shipment status must be any of the followin values: [CREATED, IN_PROGRESS, PAUSED, DELETED, ABORTED, FINISHED]"
    )
    private String status;

    @NotNull
    private Long price;

    @NotNull
    @NotBlank
    @NotEmpty
    private String current_country;

    @NotNull
    @NotBlank
    @NotEmpty
    private String current_city;

    @NotNull
    private Long latitude;

    @NotNull
    private Long vehicle_id;

    @NotNull
    private Long enterpriseId;

    @NotNull
    private Long customerId;
}
