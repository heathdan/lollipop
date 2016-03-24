package com.tw.cisco.b2b.steps;

import com.tw.cisco.b2b.helper.CommonMethodsHelper;
import com.tw.cisco.b2b.pages.AssignExpertisePopupPage;
import com.tw.cisco.b2b.pages.DefineExpertisePage;
import com.tw.cisco.b2b.pages.ProfilePage;
import com.tw.cisco.b2b.pages.UserPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;

/**
 * Created by aswathyn on 16/02/16.
 */
public class ExpertiseStepdefs {

    private static String AREAOFEXPERTISE = null ;
    private WebDriver driver;
    DefineExpertisePage defineExpertisePage;
    UserPage userPage;
    ProfilePage profilePage;
    AssignExpertisePopupPage assignExpertisePopupPage;
    private static final Logger LOGGER = LoggerFactory.getLogger(ExpertiseStepdefs.class);

    public ExpertiseStepdefs(SharedDriver driver) {
        this.driver = driver;
        long threadId = Thread.currentThread().getId();
        String processName = ManagementFactory.getRuntimeMXBean().getName();
        LOGGER.info("Started in thread: " + threadId + ", in JVM: " + processName);

    }

    @Then("^user define a new TimeStamped expertise \"([^\"]*)\"$")
    public void userDefineANewExpertise(String arg0) throws Throwable {
        defineExpertisePage = new DefineExpertisePage(driver);
        AREAOFEXPERTISE = CommonMethodsHelper.timeStamp(arg0);
        defineExpertisePage.addExpertise(AREAOFEXPERTISE);
    }

    @Then("^user \"([^\"]*)\" should be marked expert in expertise \"([^\"]*)\"$")
    public void userShouldBeMarkedExpertInExpertise(String arg0, String arg1) throws Throwable {
        userPage = new UserPage(driver);
        Thread.sleep(2000);
        userPage.searchByExpertise("\"" +AREAOFEXPERTISE+ "\"").verifyExpertiseAsignment(new CommonMethodsHelper().getPropValue(arg0));
    }

    @And("^user search for the TimeStamped expertise \"([^\"]*)\"$")
    public void userSearchForTheTimeStampedExpertise(String arg0) throws Throwable {
        defineExpertisePage = new DefineExpertisePage(driver);
        defineExpertisePage.searchExpertise(AREAOFEXPERTISE) ;
    }

    @And("^user verifies that expertise \"([^\"]*)\" is assigned successfully$")
    public void userVerifiesThatExpertiseIsAssignedSuccessfully(String arg0) throws Throwable {
        defineExpertisePage = new DefineExpertisePage(driver);
        defineExpertisePage.verfiyExpertiseAsigned(AREAOFEXPERTISE);
    }

    @And("^assign the TimeStamped expertise \"([^\"]*)\" to the user \"([^\"]*)\"$")
    public void assignTheTimeStampedExpertiseToTheUser(String arg0, String arg1) throws Throwable {
        String email= CommonMethodsHelper.getPropValue(arg1);
        userPage = new UserPage(driver);
        userPage.searchUser("\""+email+"\"").clickAssignExpertise(email).assignExpertise(AREAOFEXPERTISE);
    }

    @Then("^admin should be able to delete the unused expertise \"([^\"]*)\"$")
    public void adminShouldBeAbleToDeleteTheUnusedExpertise(String arg0) throws Throwable {
        defineExpertisePage = new DefineExpertisePage(driver);
        defineExpertisePage.deleteUnusedExpertise(AREAOFEXPERTISE);

    }

    @Then("^user should not find an option to delete the assigned expertise \"([^\"]*)\"$")
    public void userShouldNotFindAnOptionToDeleteTheAssignedExpertise(String arg0) throws Throwable {
        defineExpertisePage = new DefineExpertisePage(driver);
        defineExpertisePage.verifyDeleteExpertiseIcon(AREAOFEXPERTISE);

    }

    @And("^user tags himself with the TimeStamped expertise \"([^\"]*)\"$")
    public void userTagsHimselfWithTheTimeStampedExpertise(String arg0) throws Throwable {
        profilePage = new ProfilePage(driver);
        profilePage.selfTagExpertise(AREAOFEXPERTISE);
    }


    @Then("^user opens the assign expertise pop up for \"([^\"]*)\"$")
    public void userOpensTheAssignExpertisePopUpFor(String arg0) throws Throwable {
        String email = CommonMethodsHelper.getPropValue(arg0);
        userPage = new UserPage(driver);
        userPage.searchUser("\""+email+"\"").clickAssignExpertise(email);
    }

    @And("^user deletes the self tagged expertise \"([^\"]*)\" and save the changes$")
    public void userDeletesTheSelfTaggedExpertiseAndSaveTheChanges(String arg0) throws Throwable {
        assignExpertisePopupPage = new AssignExpertisePopupPage(driver);
        assignExpertisePopupPage.deelteSelfTaggedExpertise(AREAOFEXPERTISE);
    }



}

