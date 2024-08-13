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

        user.setId(faker.idNumber().hashCode());
        user.setUserName(faker.name().username());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().safeEmailAddress());
        user.setPassword(faker.internet().password(8,12));
        user.setPhone(faker.phoneNumber().cellPhone());

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

    @Test(priority = 3)
    public void updateUser(){

        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().safeEmailAddress());

        Response response= EndPoints.updateUser(user, user.getUserName());
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);

        Response updatedResponse= EndPoints.getUser(user.getUserName());
        updatedResponse.then().log().body();
        Assert.assertEquals(updatedResponse.getStatusCode(),200);

    }

    @Test(priority = 4)
    public void deleteUser(){

        Response response= EndPoints.deleteUser(user.getUserName());
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);

    }

}
