package com.hms.deviceregistry.adapter.out.persistence;

import java.util.*;

import org.springframework.stereotype.Component;

import com.hms.deviceregistry.application.out.DeviceRepositoryPort;
import com.hms.deviceregistry.domain.model.Device;

@Component
public class DeviceRepositoryAdapter implements DeviceRepositoryPort {

    private final DeviceJpaRepository deviceJpaRepository;

    public DeviceRepositoryAdapter(DeviceJpaRepository deviceJpaRepository) {
        this.deviceJpaRepository = deviceJpaRepository;
    }
    
    

    @Override
    public Device save(Device device) {

        DeviceEntity entity = toEntity(device);

        DeviceEntity savedEntity = deviceJpaRepository.save(entity);

        return toDomain(savedEntity);
    }

    
    
    @Override
    public List<Device> findAll() {

        List<DeviceEntity> entities = deviceJpaRepository.findAll();

        return entities.stream()
                .map(this::toDomain)
                .toList();
    }

    
    
    @Override
    public Optional<Device> findById(Long id) {

        return deviceJpaRepository.findById(id)
                .map(this::toDomain);
    }

    
    
    @Override
    public void deleteById(Long id) {

        deviceJpaRepository.deleteById(id);
    }

    
    
    
    private DeviceEntity toEntity(Device device) {

        DeviceEntity entity = new DeviceEntity();

        entity.setId(device.getId());
        entity.setName(device.getName());
        entity.setSerialNumber(device.getSerialNumber());
        entity.setType(device.getType());
        entity.setLocation(device.getLocation());
        entity.setFirmwareVersion(device.getFirmwareVersion());
        entity.setCreatedAt(device.getCreatedAt());
        entity.setUpdatedAt(device.getUpdatedAt());

        return entity;
    }

    private Device toDomain(DeviceEntity entity) {

        Device device = new Device();

        device.setId(entity.getId());
        device.setName(entity.getName());
        device.setSerialNumber(entity.getSerialNumber());
        device.setType(entity.getType());
        device.setLocation(entity.getLocation());
        device.setFirmwareVersion(entity.getFirmwareVersion());
        device.setCreatedAt(entity.getCreatedAt());
        device.setUpdatedAt(entity.getUpdatedAt());

        return device;
    }
}