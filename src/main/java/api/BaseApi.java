package api;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class BaseApi {

    protected RequestSpecification requestSpecification() {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .baseUri(Config.BASE_URL)
                .contentType(ContentType.JSON);

    }

    @Step("POST {endpoint}")
    public <T> Response sendPostRequest(String endpoint, T requestBody) {
        return requestSpecification()
                .log().all()
                .body(requestBody)
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    @Step("GET {endpoint}")
    public Response sendGetRequestWithPathParam(String endpoint, Long resourceId) {
        return requestSpecification()
                .log().all()
                .get(endpoint + "/{id}", resourceId)
                .then()
                .log().all()
                .extract()
                .response();
    }

    @Step("GET {endpoint}")
    public Response sendGetRequest(String endpoint) {
        return requestSpecification()
                .log().all()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }


    @Step("GET {endpoint} with query params")
    public Response sendGetRequestWithQueryParams(String endpoint, Map<String, ?> queryParams) {
        return requestSpecification()
                .queryParams(queryParams)
                .log().all()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    @Step("PUT {endpoint}")
    public <T> Response sendPutRequest(String endpoint, T requestBody) {
        return requestSpecification()
                .log().all()
                .body(requestBody)
                .put(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    @Step("DELETE {endpoint}")
    public Response sendDeleteRequest(String endpoint, Long resourceId) {
        return requestSpecification()
                .header("api_key", Config.API_KEY)
                .delete(endpoint + "/{id}", resourceId)
                .then()
                .extract()
                .response();
    }



}
