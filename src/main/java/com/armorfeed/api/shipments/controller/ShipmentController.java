package com.armorfeed.api.shipments.controller;

import com.armorfeed.api.shipments.domain.entities.Shipment;
import com.armorfeed.api.shipments.services.ShipmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
