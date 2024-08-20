package tests;

import com.github.javafaker.Faker;
import endpoints.EndPoints;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import payloads.User;

public class UserTests {

    Faker faker;
    User user;
    Logger logger;

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

        logger= LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void createUser(){

        logger.info("*****Creating User*****");
        Response response= EndPoints.createUser(user);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("*****User is created*****");

    }

    @Test(priority = 2)
    public void getUser(){

        logger.info("*****Getting User*****");
        Response response= EndPoints.getUser(user.getUserName());
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("*****User is displayed*****");

    }

    @Test(priority = 3)
    public void updateUser(){

        logger.info("*****Updating User*****");
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().safeEmailAddress());

        Response response= EndPoints.updateUser(user, user.getUserName());
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);

        Response updatedResponse= EndPoints.getUser(user.getUserName());
        updatedResponse.then().log().body();
        Assert.assertEquals(updatedResponse.getStatusCode(),200);
        logger.info("*****User is updated*****");

    }

    @Test(priority = 4)
    public void deleteUser(){

        logger.info("*****Deleting User*****");
        Response response= EndPoints.deleteUser(user.getUserName());
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("*****User is deleted*****");

    }

}
