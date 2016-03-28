package com.tw.cisco.b2b.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
<<<<<<< HEAD
 * Created by chandrad on 3/20/16.
=======
 * Created by aswathyn on 21/03/16.
>>>>>>> master
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        features= {"src/test/resources/cucumber/"},
        glue= {"com/tw/cisco/b2b/steps/"},
        tags = "@KC",
        monochrome = true,
        plugin= {"json:target/cucumber-report.json", "html:target/cucumber-html-report"})
public class RunKCTest {
}
