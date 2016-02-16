package com.tw.cisco.b2b.steps;

import com.tw.cisco.b2b.helper.SharedDriver;
import com.tw.cisco.b2b.pages.DefineExpertisePage;
import com.tw.cisco.b2b.pages.UserPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chandrad on 2/15/16.
 */


public class ExpertStepdefs {
    private WebDriver driver;
    DefineExpertisePage defineExpertisePage;
    UserPage userPage;
    String AreaOfExpertise = "SME" + new SimpleDateFormat("YYYYMMddhhmmss").format(new Date());

    public ExpertStepdefs(SharedDriver driver) {
        this.driver = driver;
    }

    @Then("^user define a new TimeStamped expertise \"([^\"]*)\"$")
    public void userDefineANewExpertise(String arg0) throws Throwable {

        System.out.println(AreaOfExpertise);
        defineExpertisePage = new DefineExpertisePage(driver);
        defineExpertisePage.addExpertise(AreaOfExpertise).searchExpertise(AreaOfExpertise);
        //defineExpertisePage.searchExpertise(AreaOfExpertise);
    }

    @And("^assign the TimeStamped expertise \"([^\"]*)\" to the user \"([^\"]*)\"$")
    public void assignTheTimeStampedExpertiseToTheUser(String arg0, String arg1) throws Throwable {
        userPage = new UserPage(driver);
        userPage.searchUser("\""+arg1+"\"").clickAssignExpertise().assignExpertise(AreaOfExpertise);
    }

    @Then("^user \"([^\"]*)\" should be marked expert in expertise \"([^\"]*)\"$")
    public void userShouldBeMarkedExpertInExpertise(String arg0, String arg1) throws Throwable {
        //userPage= new UserPage(driver);
        Thread.sleep(2000);
        userPage.searchByExpertise("\""+AreaOfExpertise+"\"");



    }
}
