package targil_rest_assured;

import java.util.logging.Logger;
import org.json.JSONObject;

public class CreateJsonBody {
	private static Logger logger = Logger.getLogger(CreateJsonBody.class.getName());
	
	public static String createJsonToBooking(String firstname, String lastname, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds) {
		
		// create a JSON object with sample data of booking
		logger.info("Start create a JSON object with sample data of booking");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("firstname", firstname);
		jsonObject.put("lastname", lastname);
		jsonObject.put("totalprice", totalprice);
		jsonObject.put("depositpaid", depositpaid);
		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", checkin);
		bookingdates.put("checkout", checkout);
		jsonObject.put("bookingdates", bookingdates);
		jsonObject.put("additionalneeds", additionalneeds);
		logger.info("End create a JSON object with sample data of booking");
		
		// get the JSON content as a string
		return jsonObject.toString();
	}
	
	public static String createJsonToAuth() {
		
		// create a JSON object with sample data of auth
		logger.info("Start create a JSON object with sample data of auth");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", "admin");
		jsonObject.put("password", "password123");
		logger.info("End create a JSON object with sample data of auth");
		
		// get the JSON content as a string
		return jsonObject.toString();
	}

}
