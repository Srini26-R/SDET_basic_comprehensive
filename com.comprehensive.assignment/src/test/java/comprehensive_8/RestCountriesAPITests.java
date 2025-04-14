package comprehensive_8;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestCountriesAPITests {

	@Test
	public void testValidCapital() {
		// Send GET request and capture the response
		Response response = RestAssured.given().baseUri("https://restcountries.com/v3.1/capital/tallinn").when().get();

		// Extract and assert the status code
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200, "Expected HTTP status code 200");
	}

	@Test
	public void testInvalidCapital() {
		// Send GET request and capture the response
		Response response = RestAssured.given().baseUri("https://restcountries.com/v3.1/capital/Test_Capital123").when()
				.get();

		// Extract and assert the status code
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 404, "Expected HTTP status code 404");
	}

}