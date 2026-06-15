package positivetest;

import dto.store.Inventory;
import dto.store.Order;
import dto.store.ShipStatus;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class StoreTest extends BaseTest {

    @Test
    public void getInventoryTest(){
        Inventory inventory = storeApi.getStatusesWithQuantities();
    }

    @Test
    public void placeNewOrderTest(){
        Order newOrder = Order.builder()
                .id(System.currentTimeMillis())
                .petId(1L)
                .quantity(1)
                .shipDate(java.time.ZonedDateTime.now())
                .status(ShipStatus.placed)
                .complete(true)
                .build();
        Response response = storeApi.placeAnOrder(newOrder);
        response.then()
                .statusCode(200);
        BaseTest.savedOrderId = response.jsonPath().getLong("id");


        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(response);
        softAssert.assertNotNull(response.getBody());
        softAssert.assertNotNull(BaseTest.savedOrderId);

        softAssert.assertAll();


    }

    @Test(dependsOnMethods = "placeNewOrderTest")
    public void getOrderByIdTest(){
        Order order = storeApi.getOrderById(BaseTest.savedOrderId);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(order.getStatus().toString(), "placed");
        softAssert.assertAll();
    }

}
