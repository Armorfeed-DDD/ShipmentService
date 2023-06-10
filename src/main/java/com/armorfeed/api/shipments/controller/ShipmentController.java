package com.armorfeed.api.shipments.controller;

import com.armorfeed.api.shipments.domain.entities.Shipment;
import com.armorfeed.api.shipments.resources.PatchShipmentVehicleIdResource;
import com.armorfeed.api.shipments.resources.UpdateShipmentResource;
import com.armorfeed.api.shipments.services.ShipmentsService;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("api/v1/shipments/")
public class ShipmentController {

    @Autowired
    ShipmentsService shipmentsService;

    @PostMapping
    public ResponseEntity<?> saveShipment(@RequestBody Shipment shipment){
        return shipmentsService.save(shipment);
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
    public ResponseEntity<?> updateShipment(
        @RequestBody @Valid UpdateShipmentResource updateShipmentResource,
        BindingResult bindingResult,
        @RequestHeader("Authorization") String bearerToken
        ) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors().stream().map((error) -> error.getDefaultMessage()).collect(Collectors.toList()));
        }
        log.info("Bearer token is {}", bearerToken);
        return this.shipmentsService.updateShipment(updateShipmentResource, bearerToken);
    }
    @DeleteMapping("{shipmentId}")
    public ResponseEntity<String>Delete(@PathVariable("shipmentId")Long shipmentId){
        return shipmentsService.deleteShipment(shipmentId);
    }

    @PatchMapping("set-vehicle/{shipmentId}")
    public ResponseEntity<?> patchShimpentVehicleId(
        @RequestHeader("Autorization") String bearerToken,
        @PathVariable("shipmentId") Long shipmentId,
        @RequestBody @Valid PatchShipmentVehicleIdResource patchShipmentVehicleIdResource,
        BindingResult bindingResult
        ){
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors().stream().map((error) -> error.getDefaultMessage()).collect(Collectors.toList()));
        }
        return shipmentsService.patchShipmentVehicleId(patchShipmentVehicleIdResource, shipmentId, bearerToken);
    }
}
