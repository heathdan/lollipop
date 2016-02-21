package com.tw.cisco.b2b.steps;

import com.tw.cisco.b2b.helper.CommonMethodsHelper;
import com.tw.cisco.b2b.pages.DefineExpertisePage;
import com.tw.cisco.b2b.pages.UserPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;

import java.lang.management.ManagementFactory;

/**
 * Created by aswathyn on 16/02/16.
 */
public class ExpertiseStepdefs {

    private static final String AREAOFEXPERTISE = new CommonMethodsHelper().timeStamp("SME");
    private WebDriver driver;
    DefineExpertisePage defineExpertisePage;
    UserPage userPage;

    public ExpertiseStepdefs(SharedDriver driver) {
        this.driver = driver;
        long threadId = Thread.currentThread().getId();
        String processName = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println("Started in thread: " + threadId + ", in JVM: " + processName);

    }

    @Then("^user define a new TimeStamped expertise \"([^\"]*)\"$")
    public void userDefineANewExpertise(String arg0) throws Throwable {

        System.out.println(AREAOFEXPERTISE);
        defineExpertisePage = new DefineExpertisePage(driver);
        defineExpertisePage.addExpertise(AREAOFEXPERTISE).searchExpertise(AREAOFEXPERTISE);
    }

    @And("^assign the TimeStamped expertise \"([^\"]*)\" to the user \"([^\"]*)\"$")
    public void assignTheTimeStampedExpertiseToTheUser(String arg0, String arg1) throws Throwable {
        userPage = new UserPage(driver);
        userPage.searchUser("\"" + arg1 + "\"").clickAssignExpertise().assignExpertise(AREAOFEXPERTISE);
    }

    @Then("^user \"([^\"]*)\" should be marked expert in expertise \"([^\"]*)\"$")
    public void userShouldBeMarkedExpertInExpertise(String arg0, String arg1) throws Throwable {
        userPage = new UserPage(driver);
        Thread.sleep(2000);
        userPage.searchByExpertise("\"" + AREAOFEXPERTISE + "\"");
        userPage.verifyExpertiseAsignment(arg0);
    }

}
