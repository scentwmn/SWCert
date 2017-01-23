package rmi.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public interface RMICallService {
	Log logger = LogFactory.getLog(RMICallService.class);

	String call(String keyword);
}
