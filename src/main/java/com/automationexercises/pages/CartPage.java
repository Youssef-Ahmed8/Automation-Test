package com.automationexercises.pages;

import com.automationexercises.drivers.GUIDriver;
import com.automationexercises.pages.components.NavigationBarComponent;
import com.automationexercises.utils.dataReader.PropertyReader;
import org.openqa.selenium.By;

public class CartPage {
    private GUIDriver driver;
    private NavigationBarComponent navigationBar;
    public CartPage(GUIDriver driver) {
        this.driver = driver;
        this.navigationBar = new NavigationBarComponent(driver);
    }
    //vars
    private String cartPageEndPoint="/view_cart";

    //locators
    private final By proceedToCheckoutButton = By.xpath("//a[.='Proceed To Checkout']");

    //dynamic locators
    private By productName(String productName){
        return By.xpath("(//h4 /a[.='"+productName+"'])[1]");
    }
    private By productPrice(String productName){
        return By.xpath("(//h4 /a[.='"+productName+"']//following::td[@class='cart_price']/p)[1]");
    }
    private By productQuantity(String productName){
        return By.xpath("(//h4 /a[.='"+productName+"']//following::td[@class='cart_quantity']/button)[1]");
    }
    private By productTotal(String productName){
        return By.xpath("(//h4 /a[.='"+productName+"']//following::td[@class='cart_total']/p)[1]");
    }
    private By removeProduct(String productName){
        return By.xpath("(//h4 /a[.='"+productName+"']//following::td[@class='cart_delete']/a)[1]");
    }

    //actions
    public CartPage navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb")+cartPageEndPoint);
        return this;
    }
    public CheckoutPage clickOnProceedToCheckout(){
        driver.element().click(proceedToCheckoutButton);
        return new CheckoutPage(driver);
    }
    public CartPage removeProductFromCart(String productName){
        driver.element().click(removeProduct(productName));
        return this;
    }
    //validations
    public CartPage verifyProductDetailsOnCart(String productName,String price,String quantity,String total){
        String actualProductName=driver.element().getText(productName(productName));
        String actualPrice=driver.element().getText(productPrice(productName));
        String actualQuantity=driver.element().getText(productQuantity(productName));
        String actualTotal=driver.element().getText(productTotal(productName));
        driver.validation().Equals(actualProductName,productName,"Validating product name in cart");
        driver.validation().Equals(actualPrice,price,"Validating product price in cart");
        driver.validation().Equals(actualQuantity,quantity,"Validating product quantity in cart");
        driver.validation().Equals(actualTotal,total,"Validating product total in cart");
        return this;
    }
}
