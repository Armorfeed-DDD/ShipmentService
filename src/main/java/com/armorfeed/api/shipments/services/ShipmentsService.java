package com.armorfeed.api.shipments.services;

import com.armorfeed.api.shipments.domain.entities.Shipment;
import com.armorfeed.api.shipments.domain.enums.ShipmentStatus;
import com.armorfeed.api.shipments.providers.feignclients.VehiclesServiceFeignClient;
import com.armorfeed.api.shipments.repositories.ShipmentRepository;
import com.armorfeed.api.shipments.resources.UpdateShipmentResource;
import com.armorfeed.api.shipments.security.FeignRequestInterceptor;
import com.armorfeed.api.shipments.shared.mapping.EnhancedModelMapper;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class ShipmentsService {
    @Autowired
    ShipmentRepository shipmentRepository;

    @Autowired
    EnhancedModelMapper enhancedModelMapper;

    @Autowired
    FeignRequestInterceptor feignRequestInterceptor;

    @Autowired
    VehiclesServiceFeignClient vehiclesServiceFeignClient;

    public void Save(Shipment shipment) { shipmentRepository.save(shipment);}

    public ResponseEntity<?> updateShipment(UpdateShipmentResource updateShipmentResource, String bearerToken) {

        Optional<Shipment> shipmentResult = shipmentRepository.findById(updateShipmentResource.getId());
        if(shipmentResult.isEmpty()) {
            log.info("Shipment with id {} does not exist", updateShipmentResource.getId());
            return ResponseEntity.badRequest().body("Shipment with given id does not exist");
        }

        Shipment currentShipment = shipmentResult.get();
        log.info("A shipment with id {} was found", currentShipment.getId());

        // Le pasamos el token al feign client
        feignRequestInterceptor.setBearerToken(bearerToken);
        if(vehiclesServiceFeignClient.isValidVehicleId(updateShipmentResource.getVehicle_id()) == false) {
            log.info("Vehicle with id {} does not exist", updateShipmentResource.getVehicle_id());
            return ResponseEntity.badRequest().body("Vehicle with given vehicle id does not exist");
        }

        currentShipment.setStatus(ShipmentStatus.valueOf(updateShipmentResource.getStatus()));
        currentShipment.setVehicle_id(updateShipmentResource.getVehicle_id());

        Shipment updatedShipment = shipmentRepository.saveAndFlush(currentShipment);
        log.info("shipment was updated successfully");
        return ResponseEntity.ok().body(updatedShipment);
    }
}
