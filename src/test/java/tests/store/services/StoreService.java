package tests.store.services;

import assertions.AssertableResponse;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import models.Order;


import static io.restassured.RestAssured.given;
public class StoreService {


    @Step("Get information about Inventories")
    public AssertableResponse getPetInventoriesInfo(){
        return new AssertableResponse(given().get("inventory").then());

    }
    @Step("Place Order")
    public AssertableResponse placeOrder(Order order){

        return new AssertableResponse(given().contentType(ContentType.JSON)
                .body(order)
                .post("order")
                .then());
}

    @Step("Place Order")
    public AssertableResponse placeOrder(String order){

        return new AssertableResponse(given().contentType(ContentType.JSON)
                .body(order)
                .post("order")
                .then());
    }

    @Step("Get information about order")
    public AssertableResponse getOrderInformation(Integer orderId){
        return new AssertableResponse((given()
                .get("order/" + orderId)
                .then()));
    }


    public AssertableResponse deleteOrder(Integer orderId){
        return new AssertableResponse(given()
                .delete("order/" + orderId)
                .then());
    }

}
