package com.hms.deviceregistry.application.in;

import com.hms.deviceregistry.domain.model.Device;

public interface UpdateDeviceUseCase {
	
	Device updateDevice(Long id, Device device);

}
