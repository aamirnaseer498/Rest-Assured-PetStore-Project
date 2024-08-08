package tests;

import com.github.javafaker.Faker;
import endpoints.EndPoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import payloads.User;

public class UserTests {

    Faker faker;
    User user;

    @BeforeClass
    public void setupUser(){

        faker= new Faker();
        user= new User();

        user.setId(Integer.parseInt(faker.number().digits(5)));
        user.setUserName(faker.name().username());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password(8,12));
        user.setPhone(faker.phoneNumber().cellPhone());
        user.setUserStatus(Integer.parseInt(faker.number().digits(1)));

    }

    @Test(priority = 1)
    public void createUser(){

        Response response= EndPoints.createUser(user);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);

    }

    @Test(priority = 2)
    public void getUser(){

        Response response= EndPoints.getUser(user.getUserName());
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);

    }

}
