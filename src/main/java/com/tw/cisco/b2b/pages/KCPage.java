package com.tw.cisco.b2b.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * Created by aswathyn on 08/02/16.
 */
public class KCPage extends BasePage<KCPage> {

    @FindBy(xpath = ".//span[text()='Knowledge Library']")
    private WebElement knowledgeLibrary;

    @FindBy(xpath = ".//span[text()='My Files']")
    private WebElement myFiles;

    @FindBy(xpath = ".//span[text()='Shared Files']")
    private WebElement sharedFiles;


    public KCPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        //waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    protected void instantiatePage(KCPage page) {
        try {
            PageFactory.initElements(driver, page);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return null;
    }

    /*
    public BasePage navToKCPage(String subPage) {
        BasePage page = null;
        System.out.println(subPage);
        switch (subPage) {
            case "Knowledge Library":
                knowledgeLibrary.click();
                page = new KnowLedgeLibraryPage(driver);
                break;

            case "My Files":
                myFiles.click();
                page = new MyFiles(driver);
                break;

            case "Shared Files":
                sharedFiles.click();
                page = new SharedFiles(driver);
                break;

            default:
                System.out.println("Error in finding the page");
                break;
        }
        return page;
    } */
}
