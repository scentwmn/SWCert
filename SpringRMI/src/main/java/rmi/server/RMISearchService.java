package rmi.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public interface RMISearchService {
	Log logger = LogFactory.getLog(RMISearchService.class);

	String search(String keyword);
}