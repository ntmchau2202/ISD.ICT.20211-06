import exceptions.ecobike.EcoBikeException;
import utils.DBUtils;

public class EcoBikeMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			DBUtils.initializeDBInstance();
		} catch (EcoBikeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
