package com.hotel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrowserDriver {
    public static final String MAC_CHROMEDRIVER_PATH = "./src/test/resources/drivers/Mac/chromedriver";
    public static final String MAC_FIREFOXDRIVER_PATH = "./src/test/resources/drivers/Mac/geckodriver";
    public static final String WIN_CHROMEDRIVER_PATH = "./src/test/resources/drivers/Windows/chromedriver.exe";
    public static final String WIN_FIREFOXDRIVER_PATH = "./src/test/resources/drivers/Windows/geckodriver.exe";
    WebDriver driver;

    private static final Logger log = LoggerFactory.getLogger(BrowserDriver.class);


    public WebDriver getBrowserDriver() {
        String operatingSystem = System.getProperty("os.name");

        String chromeEnv;
        String firefoxEnv;
        String browser = System.getProperty("browser", "firefox");

        if (operatingSystem.contains("Mac")) {
            chromeEnv = MAC_CHROMEDRIVER_PATH;
            firefoxEnv = MAC_FIREFOXDRIVER_PATH;
        } else if (operatingSystem.contains("Windows")) {
            chromeEnv = WIN_CHROMEDRIVER_PATH;
            firefoxEnv = WIN_FIREFOXDRIVER_PATH;
        } else {
            throw new IllegalStateException("Aborting tests: Operating System " + operatingSystem + " not recognised");
        }

        if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", firefoxEnv);
            driver = new FirefoxDriver();
        } else if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", chromeEnv);
            driver = new ChromeDriver();
        } else {
            throw new IllegalStateException("Browser " + browser + " is not supported by these tests");
        }

        log.info("Tests configured to run on operating system " + operatingSystem + " using browser " + browser);
        return driver;
    }

    public void closeBrowser() {
        driver.quit();
    }
}
