package endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import payloads.User;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class EndPoints {

    public static Response createUser(User payload){

        String requestURL= Routes.postURL;
        System.out.println(requestURL);

        Response response= given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(new Gson().toJson(payload))

                    .when()
                    .post(requestURL);

            System.out.println(payload.getUserName());

        return response;

    }

    public static Response getUser(String userName){

        String requestURL= Routes.getURL + userName;
        System.out.println(requestURL);
        System.out.println(userName);

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
