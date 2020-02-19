# EqualExpertsTests
Example hotel booking test using Cucumber JVM suite by Lucy Sheehy

## Introduction
This project runs a set of functional tests on the Equal Experts example hotel booking page:
http://hotel-test.equalexperts.io

The tests in the src/test/resources/features folder are described in BDD format as a set of scenarios of the format:
Given some precondition 
When a specific event occurs
Then this is the expected result

The glue code, or steps that tie the plain English BDD format scenarios to the Java code that drives the tests are 
located in the src/test/java/steps folder. 

The project uses Selenium to interact with the hotel booking web interface and AssertJ for test assertions.

## Building and running the tests

This is a Maven project. Tests can be built with Maven and run in your IDE or
from the directory containing the pom file run the following command

```
mvn clean test
```

The above command will run in the default browser, Mozilla Firefox. 
To specify Google Chrome, run the following command:
```
mvn clean test -Dbrowser=chrome
```

No other browsers are configured to run with these tests.

## Dependencies

This test suite is configured to run in on the following operating systems:
* Mac OS X (verified on Catalina)
* MS Windows (verified on Windows 10)

And on the following browsers:

* Google Chrome v79
* Mozilla Firefox v72
### Note: The tests may not run correctly on older versions of these browsers.

## Test Report

A Cucumber test report is generated when the test suite is run from the command line or from the RunCucumberTest class. 
The report is in the /target/cucumber-html-report folder and can be viewed in a browser by opening the index.html file.

## Logging

Log4j logging is configured in the log4j.properties to output at INFO level to the console.

