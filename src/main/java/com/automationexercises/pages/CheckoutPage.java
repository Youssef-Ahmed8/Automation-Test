package com.automationexercises.pages;

import com.automationexercises.drivers.GUIDriver;
import com.automationexercises.pages.components.NavigationBarComponent;
import com.automationexercises.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CheckoutPage {
    private final GUIDriver driver;
    private NavigationBarComponent navigationBar;
    String CheckoutPageEndPoint="/checkout";
    public CheckoutPage(GUIDriver driver) {
        this.driver = driver;
        this.navigationBar = new NavigationBarComponent(driver);
    }
    //vars

    //locators
    private final By placeOrderButton=By.xpath("//a[.='Place Order']");
    //delivery address
    private final By deliveryName = By.xpath("//ul[@id='address_delivery']/li[@class='address_firstname address_lastname']");
    private final By deliveryCompany=By.xpath("//ul[@id='address_delivery']/li[@class='address_address1 address_address2'][1]");
    private final By deliveryAddress1=By.xpath("//ul[@id='address_delivery']/li[@class='address_address1 address_address2'][2]");
    private final By deliveryAddress2=By.xpath("//ul[@id='address_delivery']/li[@class='address_address1 address_address2'][3]");
    private final By deliveryCityStateZip=By.xpath("//ul[@id='address_delivery']/li[@class='address_city address_state_name address_postcode']");
    private final By deliveryCountry=By.xpath("//ul[@id='address_delivery']/li[@class='address_country_name']");
    private final By deliveryPhone=By.xpath("//ul[@id='address_delivery']/li[@class='address_phone']");
    //billing address
    private final By billingName = By.xpath("//ul[@id='address_invoice']/li[@class='address_firstname address_lastname']");
    private final By billingCompany=By.xpath("//ul[@id='address_invoice']/li[@class='address_address1 address_address2'][1]");
    private final By billingAddress1=By.xpath("//ul[@id='address_invoice']/li[@class='address_address1 address_address2'][2]");
    private final By billingAddress2=By.xpath("//ul[@id='address_invoice']/li[@class='address_address1 address_address2'][3]");
    private final By billingCityStateZip=By.xpath("//ul[@id='address_invoice']/li[@class='address_city address_state_name address_postcode']");
    private final By billingCountry=By.xpath("//ul[@id='address_invoice']/li[@class='address_country_name']");
    private final By billingPhone=By.xpath("//ul[@id='address_invoice']/li[@class='address_phone']");


    //actions
    @Step("Navigate to checkout page")
    public CheckoutPage navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb")+CheckoutPageEndPoint);
        return this;
    }
    @Step("Click on place order button")
    public PaymentPage clickOnPlaceOrder() {
        driver.element().click(placeOrderButton);
        return new PaymentPage(driver);
    }
    //validations
    @Step("verify Delivery Address")
    public CheckoutPage verifyDeliveryAddress(String title,String fname,String lname,String company,String address1,String address2,String city,String State,String Zip,String country,String phone){
        driver.validation().Equals(driver.element().getText(deliveryName),(title+". "+fname+" "+lname),"Validating delivery name");
        driver.validation().Equals(driver.element().getText(deliveryCompany),company,"Validating delivery company");
        driver.validation().Equals(driver.element().getText(deliveryAddress1),address1,"Validating delivery address1");
        driver.validation().Equals(driver.element().getText(deliveryAddress2),address2,"Validating delivery address2");
        driver.validation().Equals(driver.element().getText(deliveryCityStateZip),(city+" "+State+" "+Zip),"Validating delivery city, state and zip");
        driver.validation().Equals(driver.element().getText(deliveryCountry),country,"Validating delivery country");
        driver.validation().Equals(driver.element().getText(deliveryPhone),phone,"Validating delivery phone");
        return this;
    }
    @Step("verify Billing Address")
    public CheckoutPage verifyBillingAddress(String title,String fname,String lname,String company,String address1,String address2,String city,String State,String Zip,String country,String phone){
        driver.validation().Equals(driver.element().getText(billingName),(title+". "+fname+" "+lname),"Validating delivery name");
        driver.validation().Equals(driver.element().getText(billingCompany),company,"Validating delivery company");
        driver.validation().Equals(driver.element().getText(billingAddress1),address1,"Validating delivery address1");
        driver.validation().Equals(driver.element().getText(billingAddress2),address2,"Validating delivery address2");
        driver.validation().Equals(driver.element().getText(billingCityStateZip),(city+" "+State+" "+Zip),"Validating delivery city, state and zip");
        driver.validation().Equals(driver.element().getText(billingCountry),country,"Validating delivery country");
        driver.validation().Equals(driver.element().getText(billingPhone),phone,"Validating delivery phone");
        return this;
    }

}