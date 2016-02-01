package com.tw.cisco.b2b.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * Created by aswathyn on 01/02/16.
 */
public class AssignExpertisePopupPage extends BasePage<AssignExpertisePopupPage> {



    public AssignExpertisePopupPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    public void instantiatePage(AssignExpertisePopupPage page) {
        try {
            PageFactory.initElements(driver, page);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return null;
    }
}
