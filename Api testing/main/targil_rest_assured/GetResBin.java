package targil_rest_assured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

import java.util.logging.Logger;

public class GetResBin {
	static String baseURL = "https://restful-booker.herokuapp.com";
	private static Logger logger = Logger.getLogger(GetResBin.class.getName());

	public static Response getAllBookings() {
		try {
			logger.info("Start get all bookings");
			// Set base URI
			RestAssured.baseURI = baseURL;
			// Get all booking
			return (Response) given().header("Content-type", "application/json").when().get("/booking").then().extract();
		} finally {
			logger.info("End get all bookings");
		}
	}

	public static Response getSpecificBooking(int numOfBooking) {
		try {
			logger.info("Start get specific booking");
			// Set base URI
			RestAssured.baseURI = baseURL;
			// Get specific booking
			return (Response) given().header("Content-type", "application/json").when().get("/booking/" + numOfBooking).then().extract();
		} finally {
			logger.info("End get specific booking");
		}
	}
}