package com.armorfeed.api.shipments.controller;

import com.armorfeed.api.shipments.services.ShipmentsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/shipments/")
public class ShipmentController {

    @Autowired
    ShipmentsServices shipmentsServices;
}
