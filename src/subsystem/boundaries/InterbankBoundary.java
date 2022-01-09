package subsystem.boundaries;

import utils.API;

/**
 * This class implements functions for performing transactions on the bank side
 * It must have functions in InterbankInterface for further communication with the EcoBike subsystems
 */
public class InterbankBoundary {

	String query(String url, String data) {
		String response = null;
		try {
			response = API.post(url, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			throw new UnrecognizedException();
		}
		return response;
	}

}
