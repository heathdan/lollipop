package com.tw.cisco.b2b.steps;

import com.tw.cisco.b2b.helper.DriverFactory;
import com.tw.cisco.b2b.pages.DefineExpertisePage;
import com.tw.cisco.b2b.pages.UserPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chandrad on 2/15/16.
 */


public class ExpertStepdefs extends DriverFactory {

    String AreaOfExpertise = "SME" + new SimpleDateFormat("YYYYMMddhhmmss").format(new Date());

    @Then("^user define a new TimeStamped expertise \"([^\"]*)\"$")
    public void userDefineANewExpertise(String arg0) throws Throwable {

        System.out.println(AreaOfExpertise);
        DefineExpertisePage page = new DefineExpertisePage(driver);
        page.addExpertise(AreaOfExpertise);
        page.searchExpertise(AreaOfExpertise);
    }

    @And("^assign the TimeStamped expertise \"([^\"]*)\" to the user \"([^\"]*)\"$")
    public void assignTheTimeStampedExpertiseToTheUser(String arg0, String arg1) throws Throwable {
        UserPage page = new UserPage(driver);
        page.searchUser("\""+arg1+"\"");
        page.clickAssignExpertise().assignExpertise(AreaOfExpertise);
    }

    @Then("^user \"([^\"]*)\" should be marked expert in expertise \"([^\"]*)\"$")
    public void userShouldBeMarkedExpertInExpertise(String arg0, String arg1) throws Throwable {
        UserPage page = new UserPage(driver);
        Thread.sleep(2000);
        page.searchByExpertise("\""+AreaOfExpertise+"\"");



    }
}
