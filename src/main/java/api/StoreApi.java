package api;

import dto.store.Inventory;
import dto.store.Order;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class StoreApi extends BaseApi{

    private static final String STORE_PATH = "/store";

    @Step("Get Statuses with quantities")
    public Inventory getStatusesWithQuantities(){
        return sendGetRequest(STORE_PATH+ "/inventory")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Inventory.class);
    }

    @Step("Place an order")
    public Response placeAnOrder(Order order){
        return sendPostRequest(STORE_PATH+"/order",order);
    }

    @Step("Get order by Id: {orderId}")
    public Order getOrderById(Long orderId){
        return sendGetRequestWithPathParam(STORE_PATH+"/order", orderId)
                .then()
                .statusCode(200)
                .extract()
                .as(Order.class);
    }

}
