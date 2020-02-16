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
    public static void main(final String[] args) throws Throwable {
        String[] arguments = {"--plugin", "html:build/reports/cucumber", "--glue", "com/hotel/steps", "classpath:com.hotel.features"};
        cucumber.api.cli.Main.main(arguments);
    }
}
