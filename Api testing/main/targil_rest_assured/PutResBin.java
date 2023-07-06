package targil_rest_assured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

import java.util.logging.Logger;

import io.restassured.http.ContentType;

public class PutResBin {
	static String baseURL = "https://restful-booker.herokuapp.com";
	private static Logger logger = Logger.getLogger(PutResBin.class.getName());
	
	public static Response putBooking(int numOfBookingToUpdate, String firstname, String lastname, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds) {
		try {
			logger.info("Start put booking");
			String firstHeaderName = "Cookie";
			String token;
			
			// Do auth
			Response authResponse = PostResBin.postAuth();
			token = "token=" + authResponse.jsonPath().getString("token");
			
			// Set base URI
			RestAssured.baseURI = baseURL;
			
			// Do put
	        return (Response)given()
	                .header(firstHeaderName, token)
	                .contentType(ContentType.JSON)
	                .body(CreateJsonBody.createJsonToBooking(firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds))
	                .put("/booking/" + numOfBookingToUpdate).then().extract();	
		} finally {
			logger.info("End put booking");
		}
	}
}
