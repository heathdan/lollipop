package com.tw.cisco.b2b.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by aswathyn on 04/02/16.
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        features= {"src/test/resources/cucumber/"},
        glue= {"com/tw/cisco/b2b/steps/"},
        tags = "@Admin",
        monochrome = true,
        plugin= {"com.cucumber.listener.ExtentCucumberFormatter:target/output/Admin_report.html"})
public class RunCukesTest {


}



