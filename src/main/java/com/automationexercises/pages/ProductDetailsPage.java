package com.automationexercises.pages;

import com.automationexercises.drivers.GUIDriver;
import com.automationexercises.pages.components.NavigationBarComponent;
import com.automationexercises.utils.dataReader.PropertyReader;
import com.automationexercises.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.Properties;

public class ProductDetailsPage {
    private String productDetailsEndPoint="/product_details/2";
    private GUIDriver driver;
    public NavigationBarComponent navigationBar;
    public ProductDetailsPage(GUIDriver driver) {
        this.driver = driver;
        this.navigationBar = new NavigationBarComponent(driver);
    }
    //locators
    private final By productName = By.cssSelector(".product-information>h2");
    private final By productPrice = By.cssSelector(".product-information >span>span");
    private final By name=By.id("name");
    private final By email=By.id("email");
    private final By reviewTextArea=By.id("review");
    private final By reviewButton=By.id("button-review");
    private final By reviewMsg=By.cssSelector("#review-section span");
    //actions
    public ProductDetailsPage navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb"+productDetailsEndPoint));
        return this;
    }
    @Step("Add review with name: {name}, email: {email} and review: {review}")
    public ProductDetailsPage addReview(String name,String email,String review){
        driver.element().type(this.name,name).type(this.email,email).type(reviewTextArea,review).click(reviewButton);
        return this;
    }
    //validations
    @Step("Validate product details with name: {pName} and price: {Pprice}")
    public ProductDetailsPage validateProductDetails(String pName,String Pprice){
        String actualProductName=driver.element().getText(productName);
        String actualProductPrice=driver.element().getText(productPrice);
        LogsManager.info("actual name: ", actualProductName," actual price: ", actualProductPrice);
        driver.validation().Equals(actualProductName,pName,"Validating product name");
        driver.validation().Equals(actualProductPrice,Pprice,"Validating product price");
        return this;
    }
    @Step("Validate review added successfully message: {expectedMsg}")
    public ProductDetailsPage validateReviewAddedSuccessfully(String expectedMsg){
        String actualMsg=driver.element().getText(reviewMsg);
        LogsManager.info("actual message: ", actualMsg);
        driver.verification().Equals(actualMsg,expectedMsg,"Validating review added successfully message");
        return this;
    }
}
