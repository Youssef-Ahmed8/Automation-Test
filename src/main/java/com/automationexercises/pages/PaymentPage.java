package com.automationexercises.pages;

import com.automationexercises.drivers.GUIDriver;
import org.openqa.selenium.By;

import javax.smartcardio.Card;

public class PaymentPage {
    private GUIDriver driver;
    public PaymentPage(GUIDriver driver) {
        this.driver=driver;
    }
    //vars
    private String PaymentEndPoint="/payment";



    //locators
    private final By nameOnCard=By.name("name_on_card");
    private final By CardNumber=By.name("card_number");
    private final By cardCvc=By.name("cvc");
    private final By CardMonthExpiration=By.name("expiry_month");
    private final By CardYearExpiration=By.name("expiry_year");
    private final By payButton=By.id("submit");
    private final By paymentSuccessMessage=By.cssSelector("h2 > b");
    private final By downloadInvoiceButton=By.xpath("//a[.='Download Invoice']");
    //actions
    public PaymentPage fillCardInfo(String nameOnCard,String CardNumber,String cardCvc,String CardMonthExpiration,String CardYearExpiration){
   driver.element().type(this.nameOnCard,nameOnCard).type(this.CardNumber,CardNumber).type(this.cardCvc,cardCvc)
           .type(this.CardMonthExpiration,CardMonthExpiration).type(this.CardYearExpiration,CardYearExpiration)
           .click(payButton);
   return this;
    }
    public PaymentPage clickOnDownloadInvoiceButton(){
        driver.element().click(downloadInvoiceButton);
        return this;
    }
    //validations
    public PaymentPage verifyPaymentSuccessMessage(String expectedMessage){
        driver.verification().Equals(driver.element().getText(paymentSuccessMessage),expectedMessage,"Payment Success Message");
        return this;
    }


    public PaymentPage verifyDownloadedFile(String invoiceName) {
        driver.verification().assertFileExists(invoiceName, " File is not existed");
        return this;
    }

}
