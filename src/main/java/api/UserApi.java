package api;

import dto.pet.Pet;
import dto.user.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class UserApi extends BaseApi{
    private static final String USER_PATH = "/user";

    @Step("Create user")
    public static Response createUser(User user){
        return sendPostRequest(USER_PATH, user);
    }

    @Step("Get User Login: {username} {password}")
    public static Response getUserLogin(String username, String password){
        Map<String,String>credentials = new HashMap<>();
        credentials.put("username", username);
        credentials.put("password", password);
        return sendGetRequestWithQueryParams(USER_PATH+"/login", credentials);
    }


}
