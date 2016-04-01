package com.tw.cisco.b2b.steps;

import com.tw.cisco.b2b.helper.CommonMethodsHelper;
import com.tw.cisco.b2b.navigation.TabbedNav;
import com.tw.cisco.b2b.pages.HomePage;
import com.tw.cisco.b2b.pages.LeftNav;
import com.tw.cisco.b2b.pages.MyActivityPage;
import com.tw.cisco.b2b.pages.MyFilesPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;

/**
 * Created by aswathyn on 21/03/16.
 */
public class KCStepDef {

    private WebDriver driver;
    private static final Logger LOGGER = LoggerFactory.getLogger(KCStepDef.class);
    private MyFilesPage myFilesPage;
    private String timeStampedFileName = null;
    HomePage homePage;

    public KCStepDef(SharedDriver driver) {
        this.driver = driver;
        long threadId = Thread.currentThread().getId();
        String processName = ManagementFactory.getRuntimeMXBean().getName();
        LOGGER.info("Started in thread: " + threadId + ", in JVM: " + processName);
    }

    @Then("^the user should be able to upload file \"([^\"]*)\" from \"([^\"]*)\"$")
    public void theUserShouldBeAbleToUploadFileFrom(String fileName, String filePath) throws Throwable {
        String pathToUpload = CommonMethodsHelper.getUploadFilePath(filePath);
        timeStampedFileName= CommonMethodsHelper.timeStamp(fileName);
        new MyFilesPage(driver).navToFileUpload().uploadFile(timeStampedFileName,pathToUpload).searchAndRefresh(timeStampedFileName);
    }

    @Given("^the user is on \"([^\"]*)\" KC page$")
    public void theUserIsOnKCPage(String arg0) throws Throwable {
        myFilesPage= new LeftNav(driver).navToKC().navToMyFiles();
    }

    @When("^I open \"([^\"]*)\" page$")
    public void iOpenPage(String arg0) throws Throwable {
        new MyFilesPage(driver).getHeaderNav().navToMyActivity().navToTab(TabbedNav.TabName.MYACTIVITIES);
    }

    @Then("^I should be able to see the \"([^\"]*)\" a \"([^\"]*)\" document activity$")
    public void iShouldBeAbleToSeeTheADocumentActivity(String arg0, String arg1) throws Throwable {
        new MyActivityPage(driver).searchActivity(timeStampedFileName).isActivityPresent(timeStampedFileName);
    }

    @And("^the user deletes the file \"([^\"]*)\"$")
    public void theUserDeletesTheFile(String arg0) throws Throwable {
       new LeftNav(driver).navToKC().navToMyFiles().searchAndDeleteFile(timeStampedFileName).isDeleteSuccess();
    }

    @Then("^the user \"([^\"]*)\" should be able to view sharedFile$")
    public void theUserShouldBeAbleToViewSharedFile(String arg0) throws Throwable {
       new LeftNav(driver).navToKC().navToSharedFiles().isFileSharedPresent(timeStampedFileName);
    }

    @When("^he shares it with \"([^\"]*)\"$")
    public void heSharesItWith(String arg0) throws Throwable {
        String userName = CommonMethodsHelper.getPropValue(arg0);
        new MyFilesPage(driver).shareFile(userName);
    }

    @Then("^the user should be able to edit document with \"([^\"]*)\" and \"([^\"]*)\" tags$")
    public void theUserShouldBeAbleToEditDocumentWithAndTags(String arg0, String arg1) throws Throwable {

    }
}
