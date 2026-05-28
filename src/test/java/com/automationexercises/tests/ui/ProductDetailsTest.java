package com.automationexercises.tests.ui;

import com.automationexercises.drivers.GUIDriver;
import com.automationexercises.drivers.UITest;
import com.automationexercises.pages.ProductsPage;
import com.automationexercises.pages.components.NavigationBarComponent;
import com.automationexercises.tests.BaseTest;
import com.automationexercises.utils.dataReader.JsonReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
@UITest

public class ProductDetailsTest extends BaseTest {

@Test
public void verifyProductDetailsTCWithoutLogin(){
    new ProductsPage(driver).navigate().clickOnViewProduct(testData.getJsonData("product.name"))
            .validateProductDetails(testData.getJsonData("product.name"),
                    testData.getJsonData("product.price"));
}

@Test
public void verifyReviewMessageTCWithoutLogin(){
    new ProductsPage(driver).navigate().clickOnViewProduct(testData.getJsonData("product.name")).addReview(testData.getJsonData("review.name"),
            testData.getJsonData("review.email"),
            testData.getJsonData("review.review")).validateReviewAddedSuccessfully(testData.getJsonData("messages.review"));

}




    //configurations
    @BeforeClass
    public void preCondition(){
        testData=new JsonReader("products-details-data");
    }

    @BeforeMethod
    public void beforeMethod() {
        driver=new GUIDriver();
        new NavigationBarComponent(driver).navigate();
    }
    @AfterMethod
    public void afterMethod() {
        driver.quitDriver();
    }

}
