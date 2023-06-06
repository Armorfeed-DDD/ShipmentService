package com.armorfeed.api.shipments.resources;

import javax.validation.constraints.NotNull;

import com.armorfeed.api.shipments.domain.enums.ShipmentStatus;
import com.armorfeed.api.shipments.utils.ValidateShipmentStatus;

import lombok.Getter;
@Getter
public class UpdateShipmentResource {
    @NotNull(message = "Shipment id must not be null")
    private Long id;
    @NotNull(message = "Status must not be null")
    @ValidateShipmentStatus(enumClass = ShipmentStatus.class,
        message = "Shipment status is not valid")
    private String status;
    @NotNull(message = "Vehicle id must not be null")
    private Long vehicle_id;
}
