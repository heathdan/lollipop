package com.tw.cisco.b2b.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by aswathyn on 19/01/16.
 */
public class LoginPage extends BasePage<LoginPage> {

    @FindBy(id = "ember314")
    private WebElement email;

    @FindBy(css = ".btn.btn-primary.msg_page-button")
    private WebElement nextButton;

    public static final String PAGETITLE = "Cisco";

    public LoginPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);

    }

    @Override
    public String getPageTitle() {
        return this.PAGETITLE;
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {

        return ExpectedConditions.titleIs(getPageTitle());
    }

    @Override
    public void instantiatePage(LoginPage page) {
        try {
            PageFactory.initElements(driver, page);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public CredentialsPage enterEmail(String userName) {
        email.sendKeys(userName);
        nextButton.click();
        implicitWaitMethod();
        return new CredentialsPage(driver);
    }

    /*********************GET/SET METHODS***************************/
    public WebElement getEmailInput() {
        return email;
    }

    public WebElement getNextButton() {
        return nextButton;
    }
}



