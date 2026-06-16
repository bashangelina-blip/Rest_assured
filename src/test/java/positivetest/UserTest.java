package positivetest;

import api.UserApi;
import dto.pet.Pet;
import dto.user.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testdata.UserFactory;

public class UserTest extends BaseTest {


    @Test(groups = {"smoke", "regression"})
    public void createUserTest() {
        User user = UserFactory.defaultUser();
        Response response = userApi.createUser(user);
        response.then()
                .statusCode(200);

        User createdUser = response.as(User.class);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(createdUser.getUsername(),user.getUsername());
        softAssert.assertEquals(createdUser.getId(), user.getId());
    }
    @Test(groups = {"smoke", "regression"})
    public void getUserLoginTest(){
        User user = UserFactory.defaultUser();
        Response createResponse = userApi.createUser(user);
        String username = createResponse.jsonPath().getString("username");
        String password = createResponse.jsonPath().getString("password");

        Response loginResponse = userApi.getUserLogin(username, password);

        loginResponse.then()
                .statusCode(200);
    }

}
