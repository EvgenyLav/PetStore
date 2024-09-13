package tests.store;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ResponseBodyData;
import listener.CustomTpl;
import models.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.store.services.StoreService;


import java.util.Random;

import static assertions.conditions.Conditions.*;

import static utils.RandomTestData.getRandomOrder;

@Tag("API")
public class PetStoreTests {

    private static Random random;
    private static StoreService storeService;

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "https://petstore.swagger.io/v2/store/";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), CustomTpl.customLogFilter().withCustomTemplates());
        random = new Random();
        storeService = new StoreService();
    }


    @Test
    @Tag("Positive")
    public void getPetInventoriesTest(){
      ResponseBodyData responseBody = storeService.getPetInventoriesInfo().should(hasStatusCode(200)).asResponse().body();
      Assertions.assertNotNull(responseBody);
    }

    @Test
    @Tag("Negative")
    public void getPetInventoriesWithInvalidUrl(){
        storeService.getPetInventoriesInfo("sometext").should(hasStatusCode(404));

    }


    @Test
    @Tag("Positive")
    public void positivePlaceOrderTest(){
        Order order = getRandomOrder();
        Order orderInfo =  storeService.placeOrder(order).should(hasStatusCode(200)).as(Order.class);
        Assertions.assertEquals(orderInfo,order);
    }


    @Test
    @Tag("Negative")
    public void noDataInputPlaceOrderTest(){
        String noData = "";

        storeService.placeOrder(noData)
                .should(hasStatusCode(400))
                .should(hasMessage("No data"))
                .should(hasType("error"))
                .should(hasCode(1));
    }

    @Test
    @Tag("Negative")
    public void BadDataInputPlaceOrderTest(){
        String badData = "{/}";

        storeService.placeOrder(badData)
                .should(hasStatusCode(400))
                .should(hasMessage("bad input"))
                .should(hasType("unknown"))
                .should(hasCode(400));

    }

    @Test
    @Tag("Positive")
    public  void positiveGetOrderInformationTest(){
        Order order = getRandomOrder();
        storeService.placeOrder(order).should(hasStatusCode(200)).as(Order.class);

        Order orderInfo = storeService.getOrderInformation(order.getId())
                .should(hasStatusCode(200)).as(Order.class);
        Assertions.assertEquals(order,orderInfo);

    }


    @Test
    @Tag("Positive")
    public void deleteOrderAndOrderNotFoundTest(){

        Order order = getRandomOrder();

        storeService.placeOrder(order)
                .should(hasStatusCode(200)).as(Order.class);

        Order orderInfo = storeService.getOrderInformation(order.getId())
                .should(hasStatusCode(200)).as(Order.class);
        Assertions.assertEquals(order,orderInfo);

        storeService.deleteOrder(order.getId()).should(hasStatusCode(200));

        storeService.getOrderInformation(order.getId())
                .should(hasStatusCode(404))
                .should(hasMessage("Order not found"))
                .should(hasType("error"))
                .should(hasCode(1));
    }

    @Test
    @Tag("Negative")
    public void deleteNotExistsOrderTest(){

        Order order = getRandomOrder();

        storeService.placeOrder(order)
                .should(hasStatusCode(200)).as(Order.class);

        Order orderInfo = storeService.getOrderInformation(order.getId())
                .should(hasStatusCode(200)).as(Order.class);
        Assertions.assertEquals(order,orderInfo);

        storeService.deleteOrder(order.getId()).should(hasStatusCode(200));


        storeService.deleteOrder(order.getId()).should(hasStatusCode(404))
                .should(hasMessage("Order Not Found"))
                .should(hasType("unknown"))
                .should(hasCode(404));
    }
}







