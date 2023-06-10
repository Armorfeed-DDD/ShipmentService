package com.armorfeed.api.shipments.services;

import com.armorfeed.api.shipments.domain.entities.Shipment;
import com.armorfeed.api.shipments.repositories.ShipmentRepository;
import com.armorfeed.api.shipments.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ShipmentsService {
    @Autowired
    ShipmentRepository shipmentRepository;

    @Autowired
    EnhancedModelMapper enhancedModelMapper;

    public ResponseEntity<String> deleteShipment(Long shipmentId) {
        if(shipmentRepository.findById(shipmentId).isPresent()){
            shipmentRepository.deleteById(shipmentId);
            return ResponseEntity.ok("Shipment Eliminated");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shipment with the given id was not found");
        }
    }

    public void Save(Shipment shipment) { shipmentRepository.save(shipment);}

    
}
