package com.armorfeed.api.shipments.controller;

import com.armorfeed.api.shipments.domain.entities.Shipment;
import com.armorfeed.api.shipments.resources.UpdateShipmentResource;
import com.armorfeed.api.shipments.services.ShipmentsService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
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

    @PatchMapping
    public ResponseEntity<?> updateShipment(
        @RequestHeader("Authorization") String bearerToken,
        @RequestBody UpdateShipmentResource updateShipmentResource) {
        log.info("Bearer token is {}", bearerToken);
        return this.shipmentsService.updateShipment(updateShipmentResource, bearerToken);
    }
}
