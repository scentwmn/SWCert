package rmi.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import rmi.server.RMISearchService;

public class RmiCallServiceImpl implements RMICallService {
	Log logger = LogFactory.getLog(RmiCallServiceImpl.class);

	RMISearchService rmiSearchService;

	public void setRmiSearchService(RMISearchService rmiSearchService) {
		this.rmiSearchService = rmiSearchService;
	}

	public String call(String keyword) {
		return rmiSearchService.search(keyword);
	}
}
