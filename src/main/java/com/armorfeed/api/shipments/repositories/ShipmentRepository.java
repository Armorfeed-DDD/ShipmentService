package com.armorfeed.api.shipments.repositories;

import com.armorfeed.api.shipments.domain.entities.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment,Long> {

}
