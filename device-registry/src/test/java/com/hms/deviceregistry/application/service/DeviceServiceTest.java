
package com.hms.deviceregistry.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hms.deviceregistry.application.exception.DeviceNotFoundException;
import com.hms.deviceregistry.application.out.DeviceRepositoryPort;
import com.hms.deviceregistry.domain.model.Device;
import com.hms.deviceregistry.domain.model.DeviceType;

public class DeviceServiceTest {

    @Mock
    private DeviceRepositoryPort deviceRepositoryPort;

    private DeviceService deviceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        deviceService = new DeviceService(deviceRepositoryPort);
    }

    private Device createTestDevice() {

        Device device = new Device();

        device.setId(1L);
        device.setName("Temperature Sensor");
        device.setSerialNumber("TEMP-001");
        device.setType(DeviceType.SENSOR);
        device.setLocation("Pune");
        device.setFirmwareVersion("1.0.0");

        return device;
    }

   
    

    @Test
    void shouldCreateDevice() {

        Device device = createTestDevice();

        when(deviceRepositoryPort.save(device))
                .thenReturn(device);

        Device result = deviceService.createDevice(device);

        assertNotNull(result);

        assertEquals(
                "TEMP-001",
                result.getSerialNumber()
        );

        verify(deviceRepositoryPort)
                .save(device);
    }

  
    

    @Test
    void shouldGetAllDevices() {

        Device device1 = createTestDevice();

        Device device2 = createTestDevice();
        device2.setId(2L);
        device2.setSerialNumber("TEMP-002");

        List<Device> devices =
                Arrays.asList(device1, device2);

        when(deviceRepositoryPort.findAll())
                .thenReturn(devices);

        List<Device> result =
                deviceService.getAllDevices();

        assertNotNull(result);

        assertEquals(
                2,
                result.size()
        );

        assertEquals(
                "TEMP-001",
                result.get(0).getSerialNumber()
        );

        assertEquals(
                "TEMP-002",
                result.get(1).getSerialNumber()
        );

        verify(deviceRepositoryPort)
                .findAll();
    }

   
    

    @Test
    void shouldGetDeviceById() {

        Device device = createTestDevice();

        when(deviceRepositoryPort.findById(1L))
                .thenReturn(Optional.of(device));

        Device result =
                deviceService.getDeviceById(1L);

        assertNotNull(result);

        assertEquals(
                1L,
                result.getId()
        );

        assertEquals(
                "Temperature Sensor",
                result.getName()
        );

        assertEquals(
                "TEMP-001",
                result.getSerialNumber()
        );

        verify(deviceRepositoryPort)
                .findById(1L);
    }



    @Test
    void shouldThrowExceptionWhenDeviceDoesNotExist() {

        when(deviceRepositoryPort.findById(999L))
                .thenReturn(Optional.empty());

        DeviceNotFoundException exception =
                assertThrows(
                        DeviceNotFoundException.class,
                        () -> deviceService.getDeviceById(999L)
                );

        assertEquals(
                "Device not found with id: 999",
                exception.getMessage()
        );

        verify(deviceRepositoryPort)
                .findById(999L);
    }




    @Test
    void shouldUpdateDevice() {

        Device existingDevice =
                createTestDevice();

        Device updatedDevice =
                createTestDevice();

        updatedDevice.setName(
                "Updated Temperature Sensor"
        );

        updatedDevice.setLocation(
                "Mumbai"
        );

        updatedDevice.setFirmwareVersion(
                "2.0.0"
        );

        when(deviceRepositoryPort.findById(1L))
                .thenReturn(Optional.of(existingDevice));

        when(deviceRepositoryPort.save(existingDevice))
                .thenReturn(existingDevice);

        Device result =
                deviceService.updateDevice(
                        1L,
                        updatedDevice
                );

        assertNotNull(result);

        assertEquals(
                "Updated Temperature Sensor",
                result.getName()
        );

        assertEquals(
                "Mumbai",
                result.getLocation()
        );

        assertEquals(
                "2.0.0",
                result.getFirmwareVersion()
        );

        verify(deviceRepositoryPort)
                .findById(1L);

        verify(deviceRepositoryPort)
                .save(existingDevice);
    }

   
    
    
    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingDevice() {

        Device device =
                createTestDevice();

        when(deviceRepositoryPort.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                DeviceNotFoundException.class,
                () -> deviceService.updateDevice(
                        999L,
                        device
                )
        );

        verify(deviceRepositoryPort)
                .findById(999L);

        verify(
                deviceRepositoryPort,
                never()
        ).save(any(Device.class));
    }

  

    @Test
    void shouldDeleteDevice() {

        Device device =
                createTestDevice();

        when(deviceRepositoryPort.findById(1L))
                .thenReturn(Optional.of(device));

        deviceService.deleteDevice(1L);

        verify(deviceRepositoryPort)
                .findById(1L);

        verify(deviceRepositoryPort)
                .deleteById(1L);
    }

   
    

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingDevice() {

        when(deviceRepositoryPort.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                DeviceNotFoundException.class,
                () -> deviceService.deleteDevice(999L)
        );

        verify(deviceRepositoryPort)
                .findById(999L);

        verify(
                deviceRepositoryPort,
                never()
        ).deleteById(999L);
    }
}