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
public class CartTest extends BaseTest {
@Test
public void verifyProductDetailsOnCart(){
    new ProductsPage(driver).navigate().
            clickOnAddToCart(testData.getJsonData("product.name")).
            validateItemAddedLabel(testData.getJsonData("messages.cartAdded"))
            .clickOnViewCart().
            verifyProductDetailsOnCart(testData.getJsonData("product.name"),testData.getJsonData("product.price"),testData.getJsonData("product.quantity"),testData.getJsonData("product.total"));}


    //configurations
    @BeforeClass
    public void preCondition(){
        testData=new JsonReader("cart-data");
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
