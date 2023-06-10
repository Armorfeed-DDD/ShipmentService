package com.armorfeed.api.shipments.providers.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "vehicles-service", value = "vehicles-service", url = "http://localhost:8089")
public interface VehiclesServiceFeignClient {
    @GetMapping("api/v1/vehicles/validate/{vehicleId}")
    public boolean isValidVehicleId(@PathVariable("vehicleId") Long vehicleId);
}
