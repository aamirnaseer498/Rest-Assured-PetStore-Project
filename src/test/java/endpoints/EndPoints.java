package endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.User;

import static io.restassured.RestAssured.given;

public class EndPoints {

    public static Response createUser(User payload){

        String requestURL= Routes.baseURL + "/user";

        Response response= given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)

                .when()
                .post(requestURL);

        return response;

    }

    public static Response getUser(String userName){

        String requestURL= Routes.baseURL + "/user/" + userName;

        Response response= given()

                .when()
                .get(requestURL);

        return response;

    }

    public static Response updateUser(User payload, String userName){

        String requestURL= Routes.baseURL + "/user/" + userName;

        Response response= given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)

                .when()
                .put(requestURL);


        return response;

    }

    public static Response deleteUser(String userName){

        String requestURL= Routes.baseURL + "/user/" + userName;

        Response response= given()

                .when()
                .delete(requestURL);

        return response;

    }

}
