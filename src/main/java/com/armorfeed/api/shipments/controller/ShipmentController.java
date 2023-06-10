package com.armorfeed.api.shipments.controller;

import com.armorfeed.api.shipments.domain.entities.Shipment;
import com.armorfeed.api.shipments.repositories.ShipmentRepository;
import com.armorfeed.api.shipments.services.ShipmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/shipments/{users_enterprise_id}")
    public List<Shipment> getShipmentsByUsersEnterpriseId(@PathVariable Long users_enterprise_id){
        return shipmentsService.getShipmentsByUsersEnterpriseId(users_enterprise_id);
    }

    @GetMapping("/shipment/{users_customer_id}")
    public List<Shipment> getShipmentByUsersCustomerId(@PathVariable Long users_customer_id){
        return shipmentsService.getShipmentsByUsersCustomerId(users_customer_id);
    }
}
