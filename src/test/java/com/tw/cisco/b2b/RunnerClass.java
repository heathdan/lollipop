package com.tw.cisco.b2b;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


/**
 * Created by aswathyn on 23/01/16.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/com.tw.cisco.b2b",
        glue = {"src/test/java/com/tw/cisco/b2b/stepDefinitions"},
        tags = {"@Functional"},
        format= {"pretty","json:target/cucumber-report.json", "html:target/cucumber"})

public class RunnerClass {


}
