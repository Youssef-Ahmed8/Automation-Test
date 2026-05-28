package com.automationexercises.pages;

import com.automationexercises.drivers.GUIDriver;
import com.automationexercises.pages.components.NavigationBarComponent;
import com.automationexercises.utils.dataReader.PropertyReader;
import com.automationexercises.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.awt.*;

public class ProductsPage {
    private GUIDriver driver;
    public NavigationBarComponent navigationBar;
    public ProductsPage(GUIDriver driver) {
        this.driver = driver;
        this.navigationBar = new NavigationBarComponent(driver);
    }
    //variables
    private String productPage="/products";
//locators
private final By searchField = By.id("search_product");
private final By searchBtn = By.id("submit_search");
private final By itemAddedLabel = By.cssSelector(".modal-body>p");
private final By viewCartBtn = By.cssSelector("p>a[href='/view_cart']");
private final By continueShoppingBtn = By.cssSelector(".modal-footer >button");


private By productName (String productName)
{
    return By.xpath("//div[@class='overlay-content']/p[.='"+productName+"']");
}
private By productPrice (String productName)
{
    return By.xpath("//div[@class='overlay-content']/p[.='"+productName+"']//preceding-sibling::h2");
}
private By hoverOnProduct(String productName)
{
    return By.xpath("//div[@class='productinfo text-center']/p[.='"+productName+"']");
}
private By addToCartBtn(String productName)
{
    return By.xpath("//div[@class='overlay-content']/p[.='"+productName+"']//following-sibling::a");
}
private By viewProductBtn(String productName)
{
    return By.xpath("//p[.='"+productName+"']//following::div[@class='choose'][1]");
}

//actions
@Step("Navigate to products page")
public ProductsPage navigate(){
    driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb")+productPage);
    return this;
}
@Step("Search for product: {productName}")
public ProductsPage searchForProduct(String productName)
{
    driver.element().type(searchField,productName).click(searchBtn);
    return this;
}
@Step("click on Add to cart for product: {productName}")
public ProductsPage clickOnAddToCart(String productName)
{
    driver.element().hover(hoverOnProduct(productName)).click(addToCartBtn(productName));
    return this;
}
public ProductDetailsPage clickOnViewProduct(String productName)
{
    driver.element().click(viewProductBtn(productName));
    return new ProductDetailsPage(driver);
}
@Step("click on view Cart")
public CartPage clickOnViewCart()
{
    driver.element().click(viewCartBtn);
    return new CartPage(driver);
}
@Step("Click on continue shopping")
public ProductsPage clickOnContinueShopping()
{    driver.element().click(continueShoppingBtn);
    return this;}

//validations
@Step("Validate product details for {productName} with the {productPrice}")
public ProductsPage validateProductDetails(String productName, String productPrice) {
    String actualProductName = driver.element().hover(productName(productName)).getText(productName(productName));
    String actualProductPrice = driver.element().hover(productPrice(productPrice)).getText(productPrice(productName));
    LogsManager.info("Validating product details for product: ", actualProductName, " with price: ", actualProductPrice);
    driver.validation().Equals(actualProductName, productName, "Validating product name");
    driver.validation().Equals(actualProductPrice, productPrice, "Validating product price");
    return this;
}
public ProductsPage validateItemAddedLabel(String expectedLabel)
{
    String actualLabel=driver.element().getText(itemAddedLabel);
    LogsManager.info("Validating item added label: ", actualLabel);
    driver.validation().Equals(actualLabel,expectedLabel,"Validating item added label");
    return this;
}

}
