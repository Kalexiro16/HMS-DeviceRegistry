package com.hms.deviceregistry.application.in;
import com.hms.deviceregistry.domain.model.Device;

public interface CreateDeviceUseCase {
	Device createDevice(Device device);

}
