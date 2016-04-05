package com.tw.cisco.b2b.pages;

import com.tw.cisco.b2b.exceptions.*;
import com.tw.cisco.b2b.navigation.HeaderNav;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    @FindBys(@FindBy(xpath =  ".//div[@id='selfTaggedExpertise']//span[@class='item-tag-text']/following-sibling::span/i"))
    private List<WebElement> removeSelfTaggedExpertiseList ;

    @FindBys(@FindBy(xpath = ".//div[@class='tt-suggestion']"))
    private List<WebElement> expertiseSuggestList ;

    @FindBy(xpath = ".//div[@class='tt-suggestion']/p")
    private WebElement expertiseSuggest ;

    @FindBy(xpath = ".//input[@placeholder='Start typing to choose an Area of Expertise...' and @class='tt-query']")
    private WebElement expertiseTextField;

    @FindBy(xpath = ".//div[@id='assignExpertise']//button[text()='Save']")
    private WebElement saveExpertiseButton ;

    @FindBy(id = "messageDiv")
    private WebElement sucessMessage;

    @FindBy(xpath = ".//div[@class='modal-create-header']//i")
    private WebElement closeExpertisePopupIcon ;

    private static final Logger LOGGER = LoggerFactory.getLogger(AssignExpertisePopupPage.class);

    HeaderNav headerNav;
    public AssignExpertisePopupPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    public void instantiatePage(AssignExpertisePopupPage page) {
        try {
            LOGGER.info("** instantiatePage(): "+ page.getClass().getSimpleName());
            PageFactory.initElements(driver, page);
        } catch(Exception e) {
            LOGGER.error("--- Error instantiating :"+page.getClass().getSimpleName());
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(expertisePopupWait);
    }

    public UserPage assignExpertise(String expertise) {
        try {
            LOGGER.info("Assigninng the expertise\""+expertise+"\"");
            enterText(expertiseTextField,expertise);
            LOGGER.info("Waiting for the auto-suggestion for \""+expertise+"\"");
            waitForElement(ExpectedConditions.visibilityOf(expertiseSuggest),expertiseSuggest);
            clickButton(expertiseSuggest);
            clickButton(saveExpertiseButton);
            LOGGER.info("waiting for the success message");
            new HeaderNav(driver).waitForSpinnerToStop();
        } catch (TextElementNotFoundException | ClickElementException | SpinnerNotDisappearException|SpinnerNotFoundException|ElementNotFoundException | ElementNotVisibleInUI ex) {
            LOGGER.error("Expertise assignment failed",ex);
        }
        return new UserPage(driver);
    }

    public UserPage deelteSelfTaggedExpertise(String expertise){
        try{
            LOGGER.info("Verifying the self tagged expertise");
            Assert.assertTrue("List of self tagged expertise contains timestamped expertise",searchInList(selfTaggedExpertiseList,expertise));
       //   Assert.assertEquals("Self tagged expertise gets updated as", expertise, selfTaggedExpertiseList.get(selfTaggedExpertiseList.size() -1 ).getText());
            LOGGER.info("Deleting the self tagged expertise");
            for (WebElement delIcon : removeSelfTaggedExpertiseList) {
                LOGGER.info("removing all the self tagged expertise");
                clickIcon(delIcon,"Untagging expertise");
            }
         // clickIcon(findInList(removeSelfTaggedExpertiseList,expertise),"deleting the self tagged expertise");
         // clickIcon(removeSelfTaggedExpertiseList.get(selfTaggedExpertiseList.size() -1),"deleting the self tagged expertise");
            LOGGER.info("Saving the action on expertise pop up");
            clickButton(saveExpertiseButton);
            headerNav = new HeaderNav(driver);
            headerNav.waitForSpinnerToStop();
        } catch(ClickElementException| ElementNotFoundException | SpinnerNotFoundException | SpinnerNotDisappearException | ClickIconNotFoundException ex ){
            LOGGER.error("Delete self tagged expertise failed , ex");
        }
        return new UserPage(driver);
    }
    
    public boolean searchInList(List<WebElement> webElement, String searchBy){
        boolean res = false;
        for (WebElement elm :webElement) {
            if (elm.getText().equals(searchBy))
                LOGGER.info(elm.getText());
                res = true;
                break;
        }
        return res;
    }

    public WebElement findInList(List<WebElement> webElement, String searchBy){
        WebElement res = null;
        for (WebElement elm : webElement){
            if(elm.getText().equals(searchBy))
                LOGGER.info(elm.getText());
                res = elm;
        }
        return res;
    }

    public UserPage verifyExpertiseAssigned(String expertise){

        for (WebElement exp : adminAssignedExpertiseList
             ) {
            if (exp.getText().equals(expertise))
                System.out.println("Pass");
            else {
                System.out.println("fail");
            }
        }

        closeExpertisePopupIcon.click();
        return new UserPage(driver);
    }


    public UserPage expertiseOperation(String expertise) throws InterruptedException {

        expertiseTextField.sendKeys(expertise);
        Thread.sleep(2000);
        Iterator<WebElement> itr = expertiseSuggestList.iterator();
        while(itr.hasNext()) {
            System.out.println(itr.next().getText());
        }
        closeExpertisePopupIcon.click();
        return new UserPage(driver);

    }
}
