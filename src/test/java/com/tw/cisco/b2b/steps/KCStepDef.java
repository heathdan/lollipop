package com.tw.cisco.b2b.steps;

import com.tw.cisco.b2b.helper.CommonMethodsHelper;
import com.tw.cisco.b2b.pages.LeftNav;
import com.tw.cisco.b2b.pages.MyFilesPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;

/**
 * Created by aswathyn on 21/03/16.
 */
public class KCStepDef {

    private WebDriver driver;
    private static final Logger LOGGER = LoggerFactory.getLogger(ExpertiseStepdefs.class);
    private MyFilesPage myFilesPage;

    public KCStepDef(SharedDriver driver) {
        this.driver = driver;
        long threadId = Thread.currentThread().getId();
        String processName = ManagementFactory.getRuntimeMXBean().getName();
        LOGGER.info("Started in thread: " + threadId + ", in JVM: " + processName);
    }

    @Then("^the user should be able to upload file \"([^\"]*)\" from \"([^\"]*)\"$")
    public void theUserShouldBeAbleToUploadFileFrom(String fileName, String filePath) throws Throwable {
        String pathToUpload = CommonMethodsHelper.getUploadFilePath(filePath);
        String timeStampedFileName= CommonMethodsHelper.timeStamp(fileName);
        new MyFilesPage(driver).navToFileUpload().uploadFile(timeStampedFileName,pathToUpload).searchAndRefresh(timeStampedFileName);
    }

    @Given("^the user is on \"([^\"]*)\" KC page$")
    public void theUserIsOnKCPage(String arg0) throws Throwable {
        myFilesPage= new LeftNav(driver).navToKC().navToMyFiles();
    }
}
