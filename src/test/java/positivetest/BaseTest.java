package positivetest;

import api.PetApi;
import api.StoreApi;
import api.UserApi;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected PetApi petApi;
    protected static Long savedOrderId;
    protected StoreApi storeApi;
    protected UserApi userApi;

    @BeforeMethod(alwaysRun = true)
    public void setup(){
        petApi = new PetApi();
        storeApi = new StoreApi();
        userApi = new UserApi();
    }

}
