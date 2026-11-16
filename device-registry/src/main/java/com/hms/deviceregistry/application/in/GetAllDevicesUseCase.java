package com.hms.deviceregistry.application.in;
import java.util.*;

import com.hms.deviceregistry.domain.model.Device;


public interface GetAllDevicesUseCase {
	  List<Device> getAllDevices();

}
