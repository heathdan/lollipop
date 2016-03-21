package com.tw.cisco.b2b.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by chandrad on 3/20/16.
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        features= {"src/test/resources/cucumber/"},
        glue= {"com/tw/cisco/b2b/steps/"},
        tags = "@Knowledge_Center",
        monochrome = true,
        plugin= {"json:target/cucumber-report.json", "html:target/cucumber-html-report"})
public class RunKCTest {
}
