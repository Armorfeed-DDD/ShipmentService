package com.armorfeed.api.shipments.services;

import com.armorfeed.api.shipments.repositories.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipmentsServices {
    @Autowired
    ShipmentRepository shipmentRepository;
}
