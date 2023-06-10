package com.armorfeed.api.shipments;
import com.armorfeed.api.shipments.controller.ShipmentController;
import com.armorfeed.api.shipments.domain.entities.Shipment;
import com.armorfeed.api.shipments.repositories.ShipmentRepository;
import com.armorfeed.api.shipments.services.ShipmentsService;
import org.bouncycastle.est.ESTException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShipmentTest {


    private ShipmentController shipmentController;

    @Mock
    private ShipmentsService shipmentsService;

    @BeforeEach
    public void setUp(){
        shipmentController = new ShipmentController(shipmentsService);
    }


    @Test
    public void TestSaveShipment(){
       Shipment shipment =new Shipment();
        ResponseEntity<Shipment> expectedResponse = ResponseEntity.status(HttpStatus.CREATED).body(shipment);
        when(shipmentsService.save(shipment)).thenReturn(shipment);


        ResponseEntity<Shipment> actualResponse = shipmentController.saveShipment(shipment);

        // Assert
        assertEquals(HttpStatus.CREATED, actualResponse.getStatusCode());
        assertEquals(expectedResponse.getBody(), actualResponse.getBody());
        verify(shipmentsService).save(shipment);
    }
}
