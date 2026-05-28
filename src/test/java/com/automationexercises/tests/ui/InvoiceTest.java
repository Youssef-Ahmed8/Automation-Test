package com.automationexercises.tests.ui;

import com.automationexercises.apis.UserManagementAPI;
import com.automationexercises.drivers.GUIDriver;
import com.automationexercises.drivers.UITest;
import com.automationexercises.pages.*;
import com.automationexercises.pages.components.NavigationBarComponent;
import com.automationexercises.tests.BaseTest;
import com.automationexercises.utils.TimeManger;
import com.automationexercises.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
@Epic("Automation Exercise")
@Feature("UI Invoice")
@Story("Invoice")
@Severity(SeverityLevel.CRITICAL)
@Owner("Youssef")
@UITest
public class InvoiceTest extends BaseTest {
    String timestamp= TimeManger.getTimeStamp();
    @Test
    public void registerNewAccount(){
        new UserManagementAPI().createRegisterUserAccount(
                testData.getJsonData("name"),
                testData.getJsonData("email")+timestamp+"@gmail.com",
                testData.getJsonData("password"),
                testData.getJsonData("titleMale"),
                testData.getJsonData("day"),
                testData.getJsonData("month"),
                testData.getJsonData("year"),
                testData.getJsonData("firstName"),
                testData.getJsonData("lastName"),
                testData.getJsonData("company"),
                testData.getJsonData("address1"),
                testData.getJsonData("address2"),
                testData.getJsonData("country"),
                testData.getJsonData("zipcode"),
                testData.getJsonData("state"),
                testData.getJsonData("city"),
                testData.getJsonData("mobilePhone")
        ).verifyUserCreatedSuccessfully();
    }
    @Test(dependsOnMethods = "registerNewAccount")
    public void loginToAccount(){
        new SignUpLoginPage(driver).navigate().enterLoginEmail(testData.getJsonData("email")+timestamp+"@gmail.com").enterLoginPassword(testData.getJsonData("password")).
                clickOnLoginButton().navigationBarComponent.verifyUserLabel(testData.getJsonData("name"));

    }
    @Test(dependsOnMethods = {"loginToAccount", "registerNewAccount"})
    public void addProductToCart(){
        new ProductsPage(driver).navigate().
                clickOnAddToCart(testData.getJsonData("product.name")).
                validateItemAddedLabel(testData.getJsonData("messages.cartAdded"))
                .clickOnViewCart().
                verifyProductDetailsOnCart(testData.getJsonData("product.name"),testData.getJsonData("product.price"),testData.getJsonData("product.quantity"),testData.getJsonData("product.total"));
    }
    @Test(dependsOnMethods = {"addProductToCart","loginToAccount","registerNewAccount"})
    public void checkout(){
        new CartPage(driver).
                navigate().
                clickOnProceedToCheckout().
                verifyDeliveryAddress(testData.getJsonData("titleMale"),testData.getJsonData("firstName"),testData.getJsonData("lastName"),testData.getJsonData("company"),testData.getJsonData("address1"),
                        testData.getJsonData("address2"),testData.getJsonData("city"),testData.getJsonData("state"),testData.getJsonData("zipcode"),testData.getJsonData("country"),testData.getJsonData("mobilePhone"))
                .verifyBillingAddress(testData.getJsonData("titleMale"),testData.getJsonData("firstName"),testData.getJsonData("lastName"),testData.getJsonData("company"),testData.getJsonData("address1"),
                        testData.getJsonData("address2"),testData.getJsonData("city"),testData.getJsonData("state"),testData.getJsonData("zipcode"),testData.getJsonData("country"),testData.getJsonData("mobilePhone"));
    }

    @Test(dependsOnMethods ={"checkout","addProductToCart","loginToAccount","registerNewAccount"} )
    public void paymentTest(){
        new CheckoutPage(driver).clickOnPlaceOrder().
                fillCardInfo(testData.getJsonData("card.cardName"),
                        testData.getJsonData("card.cardNumber"),testData.getJsonData("card.cardCvv"),
                        testData.getJsonData("card.exMonth"),testData.getJsonData("card.exYear")).verifyPaymentSuccessMessage(testData.getJsonData("messages.payment"));
    }
    @Test(dependsOnMethods ={"paymentTest","checkout","addProductToCart","loginToAccount","registerNewAccount"} )
    public void verifyInvoiceDownload(){
        new PaymentPage(driver).clickOnDownloadInvoiceButton().verifyDownloadedFile(testData.getJsonData("invoiceName"));
    }
    @Test(dependsOnMethods = {"paymentTest","checkout","addProductToCart","loginToAccount","registerNewAccount"})
    public void deleteAccountAsPostCondition(){
        new UserManagementAPI().deleteUserAccount(testData.getJsonData("email")+timestamp+"@gmail.com",
                testData.getJsonData("password")).verifyUserDeletedSuccessfully();
    }


    //configurations
    @BeforeClass
    public void setup(){
        testData=new JsonReader("checkout-data");
        driver=new GUIDriver();
        new NavigationBarComponent(driver).navigate();

    }
    @AfterClass
    public void afterMethod() {
        driver.quitDriver();
    }
}
