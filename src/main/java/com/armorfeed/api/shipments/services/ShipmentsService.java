package com.armorfeed.api.shipments.services;

import com.armorfeed.api.shipments.domain.entities.Shipment;
import com.armorfeed.api.shipments.domain.enums.ShipmentStatus;
import com.armorfeed.api.shipments.providers.feignclients.UsersServiceFeignClient;
import com.armorfeed.api.shipments.providers.feignclients.VehiclesServiceFeignClient;
import com.armorfeed.api.shipments.repositories.ShipmentRepository;
import com.armorfeed.api.shipments.resources.CreateShipmentResource;
import com.armorfeed.api.shipments.resources.PatchShipmentVehicleIdResource;
import com.armorfeed.api.shipments.resources.UpdateShipmentResource;
import com.armorfeed.api.shipments.security.FeignRequestInterceptor;
import com.armorfeed.api.shipments.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
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
    UsersServiceFeignClient usersServiceFeignClient;

    @Autowired
    VehiclesServiceFeignClient vehiclesServiceFeignClient;

    public ResponseEntity<?> save(CreateShipmentResource shipment) {
        List<String> errors = new LinkedList<>();
        if(usersServiceFeignClient.validateCustomerId(shipment.getCustomerId()) == false) {
            errors.add("Customer with id " + shipment.getCustomerId() + " does not exist");
            log.info("Customer with id {} does not exist", shipment.getCustomerId());
        }
        if(usersServiceFeignClient.validateEnterpriseId(shipment.getEnterpriseId()) == false) {
            errors.add("Enterprise with id " + shipment.getEnterpriseId() + " does not exist");
            log.info("Enterprise with id {} does not exist", shipment.getEnterpriseId());
        }
        if(errors.isEmpty() == false) {
            return ResponseEntity.badRequest().body(errors);
        }
        Shipment newShipment = enhancedModelMapper.map(shipment, Shipment.class);
        Shipment result = shipmentRepository.save(newShipment);
        log.info("New shipment was successfully created");
        return ResponseEntity.ok().body(result);
    }

    public List<Shipment> getShipmentsByUsersEnterpriseId(Long users_enterprise_id){
        return shipmentRepository.findByEnterpriseId(users_enterprise_id);
    }

    public List<Shipment> getShipmentsByUsersCustomerId(Long users_customer_id){
        return shipmentRepository.findByCustomerId(users_customer_id);
    }
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
    public ResponseEntity<String> deleteShipment(Long shipmentId) {
        if(shipmentRepository.findById(shipmentId).isPresent()){
            shipmentRepository.deleteById(shipmentId);
            return ResponseEntity.ok("Shipment Eliminated");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shipment with the given id was not found");
        }
    }

    

    public ResponseEntity<?> patchShipmentVehicleId(PatchShipmentVehicleIdResource request, Long shipmentId, String bearerToken) {

        Optional<Shipment> shipmentResult = shipmentRepository.findById(shipmentId);
        if(shipmentResult.isEmpty()) {
            log.info("Shipment with id {} doesn't exist", shipmentId);
            return ResponseEntity.badRequest().body("Shipment with given id doesn't exist");
        }

        Shipment currentShipment = shipmentResult.get();
        log.info("A shipment with id {} was found", currentShipment.getId());

        feignRequestInterceptor.setBearerToken(bearerToken);
        if (vehiclesServiceFeignClient.isValidVehicleId(request.getVehicleId()) == false) {
            log.info("Vehicle with id {} doesn't exist", request.getVehicleId());
            return ResponseEntity.badRequest().body("Vehicle with given vehicle id doesn't exist");
        }
        currentShipment.setVehicle_id(request.getVehicleId());

        Shipment patchShipment = shipmentRepository.saveAndFlush(currentShipment);
        log.info("shipment was updated successfully");
        return ResponseEntity.ok().body(patchShipment);
    }
}
