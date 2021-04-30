package com.ms.testcases;

import com.ms.base.TestBase;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class logintests extends TestBase {

    @Test(priority = 1)
    public void loginAsBankManager() throws InterruptedException {

// TO-DO logs needs to be fixed

        click("bmlBtn_CSS");

        String actualTitle = driver.getTitle();
        String expectedTitle = "Protractor practice website - Banking App";

        log.debug("Bank btn clicked");
        Assert.assertEquals(actualTitle, expectedTitle);

        //assertion needs to be fixed
        Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn_CSS"))), "login not successful");

    // Assert.assertTrue(false, "login not successful");



    }

}
