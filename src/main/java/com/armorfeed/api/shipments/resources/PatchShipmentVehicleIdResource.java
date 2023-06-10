package com.armorfeed.api.shipments.resources;

import lombok.Getter;
import javax.validation.constraints.NotNull;

@Getter
public class PatchShipmentVehicleIdResource {

    @NotNull(message = "Vehicle id must not be null")
    private Long vehicleId;
}
