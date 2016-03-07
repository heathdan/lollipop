package com.tw.cisco.b2b.pages;

import com.tw.cisco.b2b.exceptions.ClickElementException;
import com.tw.cisco.b2b.exceptions.ElementNotFoundException;
import com.tw.cisco.b2b.exceptions.TextElementNotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    @FindBy(xpath = "//div[@class='brandingLoginForm']")
    private WebElement loginForm;

    public static final String ENVIRONMENT= "CLKS T2-QA Environment";

    static final Logger LOGGER = LoggerFactory.getLogger(CredentialsPage.class);

    public CredentialsPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(loginForm);
    }

    @Override
    protected void instantiatePage(CredentialsPage page) {
        try {
            PageFactory.initElements(driver, page);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public HomePage enterCredentials(String passwrd) {
        try {
            enterText(password,passwrd);
            clickButton(signIn);
            implicitWaitMethod();
        }
        catch (TextElementNotFoundException | ClickElementException | ElementNotFoundException ex) {
            LOGGER.error("Sign in not clickable",ex);
        }
        return new HomePage(driver);
    }

    /*********************GET/SET METHODS***************************/

}
