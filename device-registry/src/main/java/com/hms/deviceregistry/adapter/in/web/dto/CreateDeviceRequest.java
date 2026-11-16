package com.hms.deviceregistry.adapter.in.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.hms.deviceregistry.domain.model.DeviceType;

public class CreateDeviceRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Serial number is required")
    private String serialNumber;

    @NotNull(message = "Type is required")
    private DeviceType type;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Firmware version is required")
    private String firmwareVersion;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public DeviceType getType() {
        return type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }
}