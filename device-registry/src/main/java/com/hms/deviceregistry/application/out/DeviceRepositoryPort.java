package com.hms.deviceregistry.application.out;

import java.util.*;

import com.hms.deviceregistry.domain.model.Device;

public interface DeviceRepositoryPort {

	Device save(Device device);
	
	List<Device> findAll();
	
	Optional<Device> findById(Long id);

	void deleteById(Long id);
}
