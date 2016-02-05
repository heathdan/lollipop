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
        glue = {"src/test/java/com/tw/cisco/b2b/steps/"},
        tags = {"@Functional"},
        format= {"pretty","json:target/cucumber-report.json", "html:target/cucumber"})
public class RunCukesTest {

}



