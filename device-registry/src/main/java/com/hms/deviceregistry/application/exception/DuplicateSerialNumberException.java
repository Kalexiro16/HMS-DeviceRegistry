package com.hms.deviceregistry.application.exception;

public class DuplicateSerialNumberException extends RuntimeException {

    public DuplicateSerialNumberException(String serialNumber) {
        super("Device with serial number already exists: " + serialNumber);
    }
}