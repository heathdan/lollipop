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
        glue= {"com/tw/cisco/b2b/steps"},
        tags = "@Functional1",
        format= {"pretty","json:target/cucumber-report.json", "html:target/cucumber"})
public class RunExpertiseTest {
}
