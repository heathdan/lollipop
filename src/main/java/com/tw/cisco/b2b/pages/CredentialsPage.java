package com.tw.cisco.b2b.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

/**
 * Created by aswathyn on 20/01/16.
 */
public class CredentialsPage extends BasePage<CredentialsPage> {
    @FindBy(css="#IDToken2")
    private WebElement password;

    @FindBy(id="Button1")
    private WebElement signIn;

    @FindBy(css=".product-info-inner>p")
    private WebElement environment;

    public static final String ENVIRONMENT= "CLKS T2-QA Environment";

    public CredentialsPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(environment);
    }

    @Override
    protected void instantiatePage(CredentialsPage page) {
        try {
            PageFactory.initElements(driver, page);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    protected HomePage enterCredentials(String passwrd) {
        password.sendKeys(passwrd);
        signIn.click();
        implicitWaitMethod();
        return new HomePage(driver);
    }

    protected CredentialsPage verifyEnvironment(){
        Assert.assertEquals(environment.getText(), ENVIRONMENT);
        return this;
    }

    /*********************GET/SET METHODS***************************/

    public WebElement getPassword() {
        return password;
    }

    public WebElement getSignIn() {
        return signIn;
    }

    public WebElement getEnvironment() {
        return environment;
    }
}
