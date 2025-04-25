package api.endPoints;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.util.ResourceBundle;

import api.payload.user;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class userEndPoints {
	
	
	// calling urls from properties files
	static ResourceBundle getURl()
	{
		ResourceBundle sourceBundle = ResourceBundle.getBundle("routes");
		
		return sourceBundle;
		
	}

	public static Response createUser(user payload) {
		
		//To call specific URL
		String post_url = getURl().getString("post_url");
		
		Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(payload)

				.when().post(post_url);

		return response;
	}

	public static Response GetUser(String username) {
		Response response = given().accept(ContentType.JSON).pathParam("username", username)

				.when().get(routes.get_url);

		return response;
	}

	public static Response UpdateUser(String username, user payload) {
		Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
				.pathParam("username", username).body(payload)

				.when().put(routes.put_url);

		return response;
	}

	public static Response DeleteUser(String username) {
		Response response = given().accept(ContentType.JSON).pathParam("username", username)
				.when().delete(routes.del_url);

		return response;
	}

}
