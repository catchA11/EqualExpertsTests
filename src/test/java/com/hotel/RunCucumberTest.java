package com.hotel;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        plugin = {"html:target/cucumber-html-report", "json:target/cucumber-json-report.json"},
        features = "classpath:com.hotel.features",
        glue = {"classpath:com/hotel/steps"}
)
public class RunCucumberTest {
}
