package com.armorfeed.api.shipments.repositories;

import com.armorfeed.api.shipments.domain.entities.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment,Long> {
    List<Shipment> findByCustomerId(Long customerId);
    List<Shipment> findByEnterpriseId(Long enterpriseId);
}
