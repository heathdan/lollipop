package com.tw.cisco.b2b.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by aswathyn on 21/03/16.
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        features= {"src/test/resources/cucumber/"},
        glue= {"com/tw/cisco/b2b/steps/"},
        tags = "@KC",
        monochrome = true,
        plugin= {"com.cucumber.listener.ExtentCucumberFormatter:target/output/KC_report.html"})
public class RunKCTest {

}
