package api.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endPoints.userEndPoints;
import api.payload.user;
import api.utilities.dataProvider;
import io.restassured.response.Response;

public class UserTestDataDriven {

	@Test(priority=1,dataProvider="AllData", dataProviderClass = dataProvider.class)
	public void testCreateUser(String userId, String userName, String fname, String lname, String email, String pwd, String phone)
	{

		user userPayload = new user();

		userPayload.setId(Integer.parseInt(userId));
	    userPayload.setUsername(userName);
	    userPayload.setFirstName(fname);
	    userPayload.setLastName(lname);
	    userPayload.setEmail(email);          // Fixed: email was wrongly set to lname
	    userPayload.setPassword(pwd);         // Fixed: password was wrongly set to email
	    userPayload.setPhone(phone);
		Response response = userEndPoints.createUser(userPayload);

		//log response
		response.then().log().all();


		//validation
		Assert.assertEquals(response.getStatusCode(),200);
	}

	@Test(priority=4,dataProvider="userNameData", dataProviderClass = dataProvider.class)
	public void testDeleteUser(String username) {

		//Response response = userEndPoints.DeleteUser(username);

		System.out.println("Delete User Data.");

		// log response
		//response.then().log().all();

		// validation
		//Assert.assertEquals(response.getStatusCode(), 200);

		// log
		// logger.info("Delete User executed.");

	}
	
	@Test(priority=3,dataProvider="userNameData", dataProviderClass = dataProvider.class)
	public void testUpdateUser(String updatedUsername) {
		Faker faker = new Faker();
		user userPayload = new user();
		userPayload.setUsername(faker.name().username());
		String oldUsername = "User1";
		Response response = userEndPoints.UpdateUser(oldUsername,userPayload);

		// log response
		response.then().log().all();

		// validation
		Assert.assertEquals(response.getStatusCode(), 200);

		// Read User data to check if first name is updated

		Response responsePostUpdate = userEndPoints.GetUser(updatedUsername);

		System.out.println("After Update User Data.");

		responsePostUpdate.then().log().all();

		// log
		// logger.info("Update User executed.");

	}

	@Test(priority=2,dataProvider="userNameData", dataProviderClass = dataProvider.class)
	public void testGetUserData(String username)
	{
		Response response = userEndPoints.GetUser(username);

		System.out.println("Read User Data.");
		//log response
		response.then().log().all();


		//validation
		// Assert.assertEquals(response.getStatusCode(),200);

		//log
		//logger.info("Get User Data executed.");
	}
	
}
