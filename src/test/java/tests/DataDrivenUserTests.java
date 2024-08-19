package tests;

import endpoints.EndPoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import payloads.User;
import utilities.DataProvider;

public class DataDrivenUserTests {

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProvider.class)
    public void createMultipleUsers(String userID, String userName, String firstName, String lastName, String email, String password, String phone){

        User user= new User();

        user.setId(Integer.parseInt(userID));
        user.setUserName(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);

        Response response= EndPoints.createUser(user);
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProvider.class)
    public void deleteAllUsers(String userName){

        Response response= EndPoints.deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(), 200);

    }

}
