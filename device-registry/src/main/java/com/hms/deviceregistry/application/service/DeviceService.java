package com.hms.deviceregistry.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hms.deviceregistry.application.exception.DeviceNotFoundException;
import com.hms.deviceregistry.application.in.CreateDeviceUseCase;
import com.hms.deviceregistry.application.in.DeleteDeviceUseCase;
import com.hms.deviceregistry.application.in.GetAllDevicesUseCase;
import com.hms.deviceregistry.application.in.GetDeviceByIdUseCase;
import com.hms.deviceregistry.application.in.UpdateDeviceUseCase;
import com.hms.deviceregistry.application.out.DeviceRepositoryPort;
import com.hms.deviceregistry.domain.model.Device;

@Service
public class DeviceService implements
        CreateDeviceUseCase,
        GetAllDevicesUseCase,
        GetDeviceByIdUseCase,
        UpdateDeviceUseCase,
        DeleteDeviceUseCase {

    private final DeviceRepositoryPort deviceRepositoryPort;

    public DeviceService(DeviceRepositoryPort deviceRepositoryPort) {
        this.deviceRepositoryPort = deviceRepositoryPort;
    }

    @Override
    public Device createDevice(Device device) {
        return deviceRepositoryPort.save(device);
    }

    @Override
    public List<Device> getAllDevices() {
        return deviceRepositoryPort.findAll();
    }

    @Override
    public Device getDeviceById(Long id) {

        return deviceRepositoryPort.findById(id)
                .orElseThrow(() ->
                       new DeviceNotFoundException(id));
    }
    

    @Override
    public Device updateDevice(Long id, Device device) {

        Device existingDevice = deviceRepositoryPort.findById(id)
                .orElseThrow(() ->
                        new DeviceNotFoundException( id));

        existingDevice.setName(device.getName());
        existingDevice.setSerialNumber(device.getSerialNumber());
        existingDevice.setType(device.getType());
        existingDevice.setLocation(device.getLocation());
        existingDevice.setFirmwareVersion(device.getFirmwareVersion());

        return deviceRepositoryPort.save(existingDevice);
    }

    @Override
    public void deleteDevice(Long id) {

        deviceRepositoryPort.findById(id)
                .orElseThrow(() ->
                        new DeviceNotFoundException(id));

        deviceRepositoryPort.deleteById(id);
    }
}