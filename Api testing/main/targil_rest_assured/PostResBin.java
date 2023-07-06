package targil_rest_assured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

import java.util.logging.Logger;

public class PostResBin {
	static String baseURL = "https://restful-booker.herokuapp.com";
	private static Logger logger = Logger.getLogger(PostResBin.class.getName());

	public static Response postBooking(String firstname, String lastname, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds) {
		try {
			logger.info("Start post booking");
			// Set base URI
			RestAssured.baseURI = baseURL;
			// Post booking
			return (Response) given().header("Content-type", "application/json").and().body(CreateJsonBody.createJsonToBooking(firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds)).when().post("/booking").then().extract();	
		} finally {
			logger.info("End post booking");
		}
	}
	
	public static Response postAuth() {
		try {
			logger.info("Start post auth");
			// Set base URI
			RestAssured.baseURI = baseURL;
			// Post auth
			return (Response) given().header("Content-type", "application/json").and().body(CreateJsonBody.createJsonToAuth()).when().post("/auth").then().extract();
		} finally {
			logger.info("End post auth");
		}
	}
}