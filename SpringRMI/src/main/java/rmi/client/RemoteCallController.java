package rmi.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RemoteCallController {
	private static final Log logger = LogFactory.getLog(RemoteCallController.class);

	@Autowired
	RMICallService rmiCallService;

	@Autowired
	public void setRMICallService(RMICallService rmiCallService) {
		this.rmiCallService = rmiCallService;
	}

	public static void main(String[] args) {
		
		RemoteCallController controller = new RemoteCallController();

		logger.debug("controll.....");

		String keyWord = "ABC";
		String result = controller.rmiCallService.call(keyWord);

		System.out.println("[client] result : " + result);
	}
}