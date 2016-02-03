package com.tw.cisco.b2b.helper;

import com.tw.cisco.b2b.pages.HomePage;
import com.tw.cisco.b2b.pages.LoginPage;
import org.openqa.selenium.WebDriver;

/**
 * Created by aswathyn on 02/02/16.
 */
public class PageHelper {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;

    public PageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void ckLogin(String userName, String passwrd) {
        loginPage = new LoginPage(driver);
        homePage=loginPage.enterEmail(userName).enterCredentials(passwrd);
        homePage.getHeaderNav().CKlogout();
    }

}
