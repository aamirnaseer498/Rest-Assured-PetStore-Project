package endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.User;

import static io.restassured.RestAssured.given;

public class EndPoints {

    public static Response createUser(User payload){

        Response response= given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)

                .when()
                .post(Routes.postURL);

        return response;

    }

    public static Response getUser(String userName){

        Response response= given()
                .pathParam("username",userName)

                .when()
                .get(Routes.getURL);

        return response;

    }

    public static Response updateUser(User payload, String userName){

        Response response= given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", userName)
                .body(payload)

                .when()
                .put(Routes.putURL);

        return response;

    }

    public static Response deleteUser(String userName){

        Response response= given()
                .pathParam("username",userName)

                .when()
                .delete(Routes.deleteURL);

        return response;

    }

}
