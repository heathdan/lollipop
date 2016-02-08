package com.tw.cisco.b2b.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Iterator;
import java.util.List;

/**
 * Created by aswathyn on 01/02/16.
 */
public class AssignExpertisePopupPage extends BasePage<AssignExpertisePopupPage> {

    @FindBy(xpath = ".//div[@class='modal-create-header']")
    private WebElement expertisePopupWait ;

    @FindBys(@FindBy(xpath = ".//div[contains(text(),'Admin Assigned Expertise')]//span[@class='item-tag-text']"))
    private List<WebElement> adminAssignedExpertiseList;

    @FindBys(@FindBy(xpath =".//div[contains(text(),'Admin Assigned Expertise')]//i[@class='icon-remove red']"))
    private List<WebElement> unAssignExpertiseList;

    @FindBys(@FindBy(xpath =  ".//div[@id='selfTaggedExpertise']//span[@class='item-tag-text']"))
    private List<WebElement> selfTaggedExpertiseList ;

    @FindBys(@FindBy(xpath =  ".//div[@id='selfTaggedExpertise']//i[@class='icon-remove red']"))
    private List<WebElement> removeSelfTaggedExpertiseList ;

    @FindBys(@FindBy(xpath = ".//div[@class='tt-suggestion']"))
    private List<WebElement> expertiseSuggestList ;

    @FindBy(xpath = ".//input[@placeholder='Start typing to choose an Area of Expertise...' and @class='tt-query']")
    private WebElement expertiseTextField;

    @FindBy(xpath = ".//div[@id='assignExpertise']//button[text()='Save']")
    private WebElement saveExpertiseButton ;

    @FindBy(xpath = ".//div[@class='modal-create-header']//i")
    private WebElement closeExpertisePopupIcon ;



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
        return ExpectedConditions.visibilityOf(expertisePopupWait);
    }

    public UserPage expertiseOperation() throws InterruptedException {

        expertiseTextField.sendKeys("API");
        Thread.sleep(2000);
        Iterator<WebElement> itr = expertiseSuggestList.iterator();
        while(itr.hasNext()) {
            System.out.println(itr.next().getText());
        }
        closeExpertisePopupIcon.click();
        return new UserPage(driver);

    }
}
