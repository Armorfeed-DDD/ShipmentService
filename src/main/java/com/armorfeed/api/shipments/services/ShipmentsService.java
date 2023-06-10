package com.armorfeed.api.shipments.services;

import com.armorfeed.api.shipments.domain.entities.Shipment;
import com.armorfeed.api.shipments.repositories.ShipmentRepository;
import com.armorfeed.api.shipments.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentsService {
    @Autowired
    ShipmentRepository shipmentRepository;

    @Autowired
    EnhancedModelMapper enhancedModelMapper;

    public void Save(Shipment shipment) { shipmentRepository.save(shipment);}

    public List<Shipment> getShipmentsByUsersEnterpriseId(Long users_enterprise_id){
        return shipmentRepository.findByUsersEnterpriseId(users_enterprise_id);
    }
}
