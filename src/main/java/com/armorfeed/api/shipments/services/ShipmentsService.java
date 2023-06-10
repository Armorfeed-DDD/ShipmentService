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
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class ShipmentsService {
    @Autowired
    ShipmentRepository shipmentRepository;

    @Autowired
    EnhancedModelMapper enhancedModelMapper;

    public void Save(Shipment shipment) { shipmentRepository.save(shipment);}
}
