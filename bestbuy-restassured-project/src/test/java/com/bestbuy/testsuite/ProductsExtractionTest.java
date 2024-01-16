package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given().
                when().
                get("/products").
                then().statusCode(200);
    }

    //21. Extract the limit
    @Test
    public void test021() {

        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //22. Extract the total
    @Test
    public void test022() {

        int total = response.extract().path("total");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //23. Extract the name of 5th product
    @Test
    public void test023() {

        String fifthProduct = response.extract().path("data[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of the fifth product is : " + fifthProduct);
        System.out.println("------------------End of Test---------------------------");
    }

    //24. Extract the names of all the products
    @Test
    public void test024() {

        List<?> allProducts = response.extract().path("data.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The list of all the products is : " + allProducts);
        System.out.println("------------------End of Test---------------------------");
    }

    //25. Extract the productId of all the products
    @Test
    public void test025() {

        List<?> allProductID = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The list of all product ids is  : " + allProductID);
        System.out.println("------------------End of Test---------------------------");
    }

    //26. Print the size of the data list
    @Test
    public void test026() {

        List<?> dataSizeList = response.extract().path("data");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of all the data  is : " + dataSizeList);
        System.out.println("------------------End of Test---------------------------");
    }

    //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4- Pack)
    @Test
    public void test027() {

        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all values of given product : " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2- Pack)
    @Test
    public void test028() {

        List<String> model = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The model of the given product is : " + model.get(0));
        System.out.println("------------------End of Test---------------------------");
    }

    //29. Get all the categories of 8th products
    @Test
    public void test029() {

        List<String> allCategories = response.extract().path("data[7].categories");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The categories of the 8th product : " + allCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    //30. Get categories of the store where product id = 150115
    @Test
    public void test030() {

        List<?> categories = response.extract().path("data.findAll{it.id == 150115}.categories");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The categories of the product id 150115 : " + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    //31. Get all the descriptions of all the products
    @Test
    public void test031() {

        List<?>description = response.extract().path("data.description");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The description of the all products : " + description);
        System.out.println("------------------End of Test---------------------------");
    }

    //32. Get id of all the all categories of all the products
    @Test
    public void test032() {

        List<?>ids = response.extract().path("data.categories.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The id of the all categories of all the products: " + ids);
        System.out.println("------------------End of Test---------------------------");
    }

    //33. Find the product names Where type = HardGood
    @Test
    public void test033() {

        List<?> productName = response.extract().path("data.findAll{it.type == 'HardGood'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The products name whose type is HardGood : " + productName);
        System.out.println("------------------End of Test---------------------------");
    }

    //34. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test034() {

        List<?> cat = response.extract().path("data.find{it.name = 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total number of categories for Duracell - AA 1.5V CopperTop Batteries (4-Pack) : " + cat.size());
        System.out.println("The total number of categories for Duracell - AA 1.5V CopperTop Batteries (4-Pack) : " + cat);
        System.out.println("------------------End of Test---------------------------");
    }

    //35. Find the createdAt for all products whose price < 5.49
    @Test
    public void test035() {

        List<?> createdAt = response.extract().path("data.findAll{it.price < 5.49}.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for all products price < 5.49 : " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    //36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4- Pack)”
    @Test
    public void test036() {

        List<?> categories = response.extract().path("data.find{it.name = 'Energizer - MAX Batteries AA (4- Pack)'}.categories");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The categories of the product Energizer - MAX Batteries AA (4- Pack) : " + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    //37. Find the manufacturer of all the products
    @Test
    public void test037() {

        List<?> manufacturer = response.extract().path("data.manufacturer");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The list of the manufacturers : " + manufacturer);
        System.out.println("------------------End of Test---------------------------");
    }

    //38. Find the image of products whose manufacturer is = Energizer
    @Test
    public void test038() {

        List<?> productsImage = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The image of product whose manufacturer is Energizer : " + productsImage);
        System.out.println("------------------End of Test---------------------------");
    }

    //39. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test039() {

        List<?> createdAt = response.extract().path("data.findAll{it.price > 5.99}.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for all products price > 5.99 : " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    //40. Find the uri of all the products
    @Test
    public void test040() {

        List<HashMap<String,String>> uri = response.extract().path("data.url");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The uri of the all product : " + uri);
        System.out.println("------------------End of Test---------------------------");
    }
}
