package com.automationexercises.tests.ui;

import com.automationexercises.drivers.GUIDriver;
import com.automationexercises.drivers.UITest;
import com.automationexercises.pages.ProductsPage;
import com.automationexercises.pages.components.NavigationBarComponent;
import com.automationexercises.tests.BaseTest;
import com.automationexercises.utils.TimeManger;
import com.automationexercises.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
@UITest

@Epic("Automation Exercise")
@Feature("UI Product Management")
@Story("Product Management")
@Severity(SeverityLevel.CRITICAL)
@Owner("Youssef")
public class ProductTest extends BaseTest {
    String timestamp= TimeManger.getTimeStamp();

    @Test
    @Description("Search for a product and validate its details")
    public void searchForProductWithoutLogin(){
     new  ProductsPage(driver).navigate().searchForProduct(testData.getJsonData("searchedProduct.name")).
             validateProductDetails(testData.getJsonData("searchedProduct.name"),testData.getJsonData("searchedProduct.price"));

    }


    @Test
    @Description("Add a product to the cart without logging in")
    public void addProductToCartWithoutLogin(){
        new ProductsPage(driver).navigate().clickOnAddToCart(testData.getJsonData("product1.name")).validateItemAddedLabel(testData.getJsonData("messages.cartAdded"));
    }





    //configurations
    @BeforeClass
    public void preCondition(){
        testData=new JsonReader("products-data");
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
