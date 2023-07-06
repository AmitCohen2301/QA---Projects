package tests_rest_assured;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import io.restassured.response.Response;
import targil_rest_assured.GetResBin;
import targil_rest_assured.PostResBin;
import targil_rest_assured.PutResBin;

public class AllTests {
	private static Logger logger = Logger.getLogger(AllTests.class.getName());
	private JSONObject jsonInputsToGet;
	private JSONObject jsonInputsToPost;
	private JSONObject jsonInputsToPut;

	@Before
	public void setUp() throws IOException, ParseException, Exception {
		try {
			logger.info("Start converting inputs json files");
			JSONParser jsonParser = new JSONParser();
			FileReader readerToGet = new FileReader("inputsToGet.json");
			FileReader readerToPost = new FileReader("inputsToPost.json");
			FileReader readerToPut = new FileReader("inputsToPut.json");
			
			//Read JSON file
			jsonInputsToGet = (JSONObject)jsonParser.parse(readerToGet);
			jsonInputsToPost = (JSONObject)jsonParser.parse(readerToPost);
			jsonInputsToPut = (JSONObject)jsonParser.parse(readerToPut);
			
			logger.info("End converting from inputs json files to JsonArray");
			
		} catch (Exception e) {
			logger.info("Failed converting from inputs json files to JsonArray");
			throw e;
		}
	}
	
	@Test
	public void testPostAndGetBooking() throws SQLException, Exception {
		logger.info("Start test post and get booking");
		
		// Post booking
		logger.info("Start reading and posting info from Json");
		JSONObject bookingdatesJsonObj = (JSONObject)jsonInputsToPost.get("bookingdates");
		Response postResponse = PostResBin.postBooking(
				(String)jsonInputsToPost.get("firstname"), 
				(String)jsonInputsToPost.get("lastname"), 
				Integer.parseInt(jsonInputsToPost.get("totalprice").toString()), 
				Boolean.parseBoolean(jsonInputsToPost.get("depositpaid").toString()), 
				(String)bookingdatesJsonObj.get("checkin"), 
				(String)bookingdatesJsonObj.get("checkout"), 
				(String)jsonInputsToPost.get("additionalneeds"));
		if(postResponse.getStatusCode() != 200)
			throw new Exception("Failed to post booking");
		logger.info("End reading and posting info from Json");
		
		// Get booking
		logger.info("Start geting booking");
		int bookingId = Integer.parseInt(postResponse.jsonPath().getString("bookingid"));		
		Response getResponse = GetResBin.getSpecificBooking(bookingId);
		if(getResponse.getStatusCode() != 200)
			throw new Exception("Failed to get the booking");
		logger.info("End geting booking");
		
		logger.info("End test post and get booking");
	}
	
	@Test
	public void testPostAndPutBooking() throws SQLException, Exception {
		logger.info("Start test post and put booking");
		
		// Post booking
		logger.info("Start reading and posting info from Json");
		JSONObject bookingdatesJsonObj = (JSONObject)jsonInputsToPost.get("bookingdates");
		Response postResponse = PostResBin.postBooking(
				(String)jsonInputsToPost.get("firstname"), 
				(String)jsonInputsToPost.get("lastname"), 
				Integer.parseInt(jsonInputsToPost.get("totalprice").toString()), 
				Boolean.parseBoolean(jsonInputsToPost.get("depositpaid").toString()), 
				(String)bookingdatesJsonObj.get("checkin"), 
				(String)bookingdatesJsonObj.get("checkout"), 
				(String)jsonInputsToPost.get("additionalneeds"));
		if(postResponse.getStatusCode() != 200)
			throw new Exception("Failed to post booking");
		logger.info("End reading and posting info from Json");
		
		// Put booking
		int bookingId = Integer.parseInt(postResponse.jsonPath().getString("bookingid"));
		logger.info("Start reading and puting info from Json");
		bookingdatesJsonObj = (JSONObject)jsonInputsToPut.get("bookingdates");
		Response putResponse = PutResBin.putBooking(
				bookingId, 
				(String)jsonInputsToPut.get("firstname"), 
				(String)jsonInputsToPut.get("lastname"), 
				Integer.parseInt(jsonInputsToPut.get("totalprice").toString()), 
				Boolean.parseBoolean(jsonInputsToPut.get("depositpaid").toString()), 
				(String)bookingdatesJsonObj.get("checkin"), 
				(String)bookingdatesJsonObj.get("checkout"), 
				(String)jsonInputsToPut.get("additionalneeds"));
		if(putResponse.getStatusCode() != 200)
			throw new Exception("Failed to put booking");
		logger.info("End reading and puting info from Json");
	}
	
	@Test
	public void testGetAllBookings() throws SQLException, Exception {
		logger.info("Start test get all bookings");
		
		// Get all booking
		Response getResponse = GetResBin.getAllBookings();
		if(getResponse.getStatusCode() != 200)
			throw new Exception("Failed to get all bookings");
		logger.info("End test get all bookings");
	}
	
	@Test
	public void testGetSpecificBooking() throws SQLException, Exception {
		logger.info("Start test get specific booking");
		
		// Get specific booking
		Response postResponse = GetResBin.getSpecificBooking(Integer.parseInt(jsonInputsToGet.get("numofbooking").toString()));
		if(postResponse.getStatusCode() != 200)
			throw new Exception("Failed to get specific booking");	
		logger.info("End test get specific booking");
	}
	
	public static void main(String args[]) {
		logger.info("Start all tests");
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		org.junit.runner.Result result = junit.run(AllTests.class);
		if (result.getFailureCount() > 0) {
			System.out.println("Test failed.");
			logger.info("End all tests");
			System.exit(1);
		} else {
			System.out.println("Test finished successfully.");
			logger.info("End all tests");
			System.exit(0);
		}
	}
}