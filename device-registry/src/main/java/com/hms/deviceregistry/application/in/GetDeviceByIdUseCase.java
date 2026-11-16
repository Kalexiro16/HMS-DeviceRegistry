package com.hms.deviceregistry.application.in;

import com.hms.deviceregistry.domain.model.Device;

public interface GetDeviceByIdUseCase {

    Device getDeviceById(Long id);
}