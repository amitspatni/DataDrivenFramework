package com.ms.base;


import com.ms.utilities.ExtentManager;
import com.ms.utilities.TestUtilities;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class TestBase {

    public static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties OR = new Properties();
    public static FileInputStream fis;
    public static Logger log = Logger.getLogger("devpinoyLogger");
    public static WebDriverWait wait;
    public static ExtentReports repo = ExtentManager.getInstance();
//    public static ExcelReader excel = new ExcelReader("C:\\Users\\user\\Desktop\\CG training\\GithubDemo\\PraticeTestNG\\way2automation_hybrid_framework\\src\\test\\resources\\excel\\testdata.xlsx");
    public static ExtentTest test;



    @BeforeSuite
    public void setUp() throws FileNotFoundException, IOException {
        // to be removed
        BasicConfigurator.configure();
        String prop = System.getProperty("user.dir");

        if (driver==null) {
            fis = new FileInputStream(prop+"\\src\\test\\properties\\config.properties");
            config.load(fis);
            log.debug("Config file loaded");

            fis = new FileInputStream(prop+"\\src\\test\\properties\\OR.properties");
            OR.load(fis);
            log.debug("OR File loaded");

            if (config.getProperty("browser").equals("chrome")) {

                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.get(config.getProperty("testsiteurl"));
                log.debug("Test url opened");
            }
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);

    }

    public Boolean isElementPresent(By by) {

        try {

            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }


    }

    public void click(String locators) {

        if (locators.endsWith("_CSS")) {

            driver.findElement(By.cssSelector(OR.getProperty(locators))).click();
            test.log(LogStatus.INFO, "clicked on " + locators + "using CSS");
        } else if (locators.endsWith("_XPATH")) {

            driver.findElement(By.xpath(OR.getProperty(locators))).click();
            test.log(LogStatus.INFO, "clicked on " + locators + " using XPATH");
        }

    }

    public void type(String locators, String inputValues) {

        if (locators.endsWith("_CSS")) {


            driver.findElement(By.cssSelector(OR.getProperty(locators))).sendKeys(inputValues);
            test.log(LogStatus.INFO, "typed on " + locators + "entered values " + inputValues + " using CSS ") ;
        } else if (locators.endsWith("_XPATH")) {

            driver.findElement(By.xpath(OR.getProperty(locators))).sendKeys(inputValues);
            test.log(LogStatus.INFO, "typed on " + locators + "entered values " + inputValues + " using XPATH ") ;
        }

    }

    public void softAssertion(String actual, String expected ) throws IOException {

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actual, expected, "validation failed");
        TestUtilities.captureScreenshot();

        test.log(LogStatus.WARNING, "This is just the warning");
        test.log(LogStatus.WARNING,test.addScreenCapture(TestUtilities.screenShotFileName));

    }


    @AfterSuite
    public void tearDown() {

        if (driver!=null){
            driver.quit();
        }

    }
}
