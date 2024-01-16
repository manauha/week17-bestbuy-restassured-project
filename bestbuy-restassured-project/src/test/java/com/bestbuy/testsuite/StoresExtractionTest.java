package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given().
                when().
                get("/stores").
                then().statusCode(200);
    }

    //1. Extract the limit
    @Test
    public void test001() {

        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total
    @Test
    public void test002() {

        int total = response.extract().path("total");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th store
    @Test
    public void test003() {

        String storeName = response.extract().path("data[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of the fifth store is : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all the store
    @Test
    public void test004() {

        List<?> allStores = response.extract().path("data.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all store is : " + allStores);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the storeId of all the store
    @Test
    public void test005() {

        List<?>allStoreId = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeId of the all stores : " + allStoreId);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the size of the data list
    @Test
    public void test006() {

        List<?>datalist = response.extract().path("data.");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data list is : " + datalist);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {

        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'St Cloud'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all the values of St Cloud Store is : " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void test008() {

        List<String> model = response.extract().path("data.findAll{it.name == 'Rochester'}.address");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Rochester store address is : " + model.get(0));
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the services of 8th store
    @Test
    public void test009() {

        List<String> allServices = response.extract().path("data[7].services");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The services of the 8th Store : " + allServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test010() {

        List<?> services = response.extract().path("data.services*.findAll{it.name == 'Windows Store'}.storeservices");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeservices of the Windows Store name : " + services);
        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get all the storeId of all the store
    @Test
    public void test011() {

        List<?> allStoreID = response.extract().path("data.services.storeservices.storeId");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeId of all the store is : " + allStoreID);
        System.out.println("------------------End of Test---------------------------");
    }

    //12. Get id of all the store
    @Test
    public void test012() {

        List<?> storeID = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The ids of all the stores : " + storeID);
        System.out.println("------------------End of Test---------------------------");
    }

    //13. Find the store names Where state = ND
    @Test
    public void test013() {

        List<String> storeName = response.extract().path("data.findAll{it.state == 'ND'}.name");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store names in ND : " + storeName.get(0));
        System.out.println("------------------End of Test---------------------------");
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014() {

        List<?> totalService = response.extract().path("data.find{it.name == 'Rochester'}.services");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total number of the services at Rochester : " + totalService.size());
        System.out.println("The total number of the services at Rochester : " + totalService);
        System.out.println("------------------End of Test---------------------------");
    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015() {

        List<HashMap<String,?>> services = response.extract().path("data.services*.findAll{it.name=='Windows Store'}.storeservices.createdAt");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all services created at Windows Store  : " + services);
        System.out.println("------------------End of Test---------------------------");
    }

    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test016() {

        List<HashMap<String, ?>> totalService = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all services at Fargo store : " + totalService);
        System.out.println("------------------End of Test---------------------------");
    }

    //17. Find the zip of all the store
    @Test
    public void test017() {

        List<?> zipcode = response.extract().path("data.zip");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zipcode of all the stores : " + zipcode);
        System.out.println("------------------End of Test---------------------------");
    }

    //18. Find the zip of store name = Roseville
    @Test
    public void test018() {

        List< ?> storeName = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zipcode of the Roseville store : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }

    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019() {

        //List<HashMap<?, ?>> storeService = response.extract().path("data[2].services.find{it.name =='Magnolia Home Theater'}.storeservice");
        List<HashMap<String, ?>> storeServices = response.extract().path("data.services*.findAll{it.name == 'Magnolia Home Theater'}.storeservices");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The details of Magnolia Home Theater store services: " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //20. Find the lat of all the stores
    @Test
    public void test020() {

        List<HashMap<Object, ?>> latStore = response.extract().path("data.lat");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The lat of all the stores : " +latStore);
        System.out.println("------------------End of Test---------------------------");
    }
}
