package com.hms.deviceregistry.adapter.in.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.deviceregistry.adapter.in.web.dto.CreateDeviceRequest;
import com.hms.deviceregistry.application.in.CreateDeviceUseCase;
import com.hms.deviceregistry.application.in.DeleteDeviceUseCase;
import com.hms.deviceregistry.application.in.GetAllDevicesUseCase;
import com.hms.deviceregistry.application.in.GetDeviceByIdUseCase;
import com.hms.deviceregistry.application.in.UpdateDeviceUseCase;
import com.hms.deviceregistry.domain.model.Device;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final CreateDeviceUseCase createDeviceUseCase;
    private final GetAllDevicesUseCase getAllDevicesUseCase;
    private final GetDeviceByIdUseCase getDeviceByIdUseCase;
    private final UpdateDeviceUseCase updateDeviceUseCase;
    private final DeleteDeviceUseCase deleteDeviceUseCase;

    public DeviceController(
            CreateDeviceUseCase createDeviceUseCase,
            GetAllDevicesUseCase getAllDevicesUseCase,
            GetDeviceByIdUseCase getDeviceByIdUseCase,
            UpdateDeviceUseCase updateDeviceUseCase,
            DeleteDeviceUseCase deleteDeviceUseCase) {

        this.createDeviceUseCase = createDeviceUseCase;
        this.getAllDevicesUseCase = getAllDevicesUseCase;
        this.getDeviceByIdUseCase = getDeviceByIdUseCase;
        this.updateDeviceUseCase = updateDeviceUseCase;
        this.deleteDeviceUseCase = deleteDeviceUseCase;
    }

    @PostMapping
    public ResponseEntity<Device> createDevice(
            @Valid @RequestBody CreateDeviceRequest request) {

        Device device = new Device();

        device.setName(request.getName());
        device.setSerialNumber(request.getSerialNumber());
        device.setType(request.getType());
        device.setLocation(request.getLocation());
        device.setFirmwareVersion(request.getFirmwareVersion());

        Device createdDevice = createDeviceUseCase.createDevice(device);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdDevice);
    }

    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {

        List<Device> devices = getAllDevicesUseCase.getAllDevices();

        return ResponseEntity.ok(devices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(
            @PathVariable Long id) {

        Device device = getDeviceByIdUseCase.getDeviceById(id);

        return ResponseEntity.ok(device);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(
            @PathVariable Long id,
            @RequestBody Device device) {

        Device updatedDevice =
                updateDeviceUseCase.updateDevice(id, device);

        return ResponseEntity.ok(updatedDevice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(
            @PathVariable Long id) {

        deleteDeviceUseCase.deleteDevice(id);

        return ResponseEntity.noContent().build();
    }
}