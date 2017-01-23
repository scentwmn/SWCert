package rmi.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RMISearchServiceImpl implements RMISearchService {
	Log logger = LogFactory.getLog(RMISearchServiceImpl.class);

	@Override
	public String search(String keyword) {

		logger.debug("[server] call service keyword : " + keyword);

		return keyword;
	}
}
