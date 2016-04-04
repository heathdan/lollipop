package com.tw.cisco.b2b.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by chandrad on 2/16/16.
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        features= {"src/test/resources/cucumber/"},
        glue= {"com/tw/cisco/b2b/steps/"},
        tags = "@Expertise",
        monochrome = true,
        plugin= {"com.cucumber.listener.ExtentCucumberFormatter:target/output/Expertise_report.html"})
public class RunExpertiseTest {
}