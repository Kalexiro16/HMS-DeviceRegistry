package com.hms.deviceregistry.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceJpaRepository extends JpaRepository<DeviceEntity, Long> {

}