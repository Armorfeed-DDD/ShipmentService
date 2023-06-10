package com.armorfeed.api.shipments.controller;

import com.armorfeed.api.shipments.domain.entities.Shipment;
import com.armorfeed.api.shipments.resources.PatchShipmentVehicleIdResource;
import com.armorfeed.api.shipments.resources.UpdateShipmentResource;
import com.armorfeed.api.shipments.services.ShipmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/shipments/")
public class ShipmentController {

    @Autowired
    ShipmentsService shipmentsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Shipment saveShipment(@RequestBody Shipment shipment){
        shipmentsService.Save(shipment);
        return shipment;
    }

    @GetMapping("/enterprises/{users_enterprise_id}")
    public List<Shipment> getShipmentsByUsersEnterpriseId(@PathVariable Long users_enterprise_id){
        return shipmentsService.getShipmentsByUsersEnterpriseId(users_enterprise_id);
    }

    @GetMapping("/customers/{users_customer_id}")
    public List<Shipment> getShipmentByUsersCustomerId(@PathVariable Long users_customer_id){
        return shipmentsService.getShipmentsByUsersCustomerId(users_customer_id);
    }
    
    @PatchMapping
    public ResponseEntity<?> updateShipment(@RequestHeader("Authorization") String bearerToken, @RequestBody UpdateShipmentResource updateShipmentResource) {

        return this.shipmentsService.updateShipment(updateShipmentResource, bearerToken);
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> patchShimpentVehicleId(@RequestHeader("Autorization") String bearerToken, @PathVariable Long shipmentId, @RequestBody PatchShipmentVehicleIdResource patchShipmentVehicleIdResource){
        return shipmentsService.patchShipmentVehicleId(patchShipmentVehicleIdResource, shipmentId, bearerToken);
    }
}
