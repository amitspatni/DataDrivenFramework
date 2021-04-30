package com.ms.testcases;

import com.ms.base.TestBase;
import com.ms.utilities.TestUtilities;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class AddCustomer extends TestBase {



    @Test(dataProviderClass = TestUtilities.class, dataProvider = "getData")
    public void addCustomer(String firstName, String lastName, String postalCode, String alertText) throws IOException /* throws InterruptedException */ {

            softAssertion("xyz", "abc");

           click("addCustBtn_CSS");
           type("firstNameField_CSS", firstName);
            type("lastNameField_CSS", lastName);
            type("postalCodeField_CSS", postalCode);
            click("addCustomerBtn_CSS");

//        driver.findElement(By.cssSelector(OR.getProperty("addCustBtn"))).click();
//        driver.findElement(By.cssSelector(OR.getProperty("firstNameField"))).sendKeys(firstName);
//        driver.findElement(By.cssSelector(OR.getProperty("lastNameField"))).sendKeys(lastName);
//        driver.findElement(By.cssSelector(OR.getProperty("postalCodeField"))).sendKeys(postalCode);
//        driver.findElement(By.cssSelector(OR.getProperty("addCustomerBtn"))).click();
          Alert alert = wait.until(ExpectedConditions.alertIsPresent());
          Assert.assertTrue(alert.getText().contains(alertText));


          alert.accept();
          Assert.fail("this test cases failed intentionally");

        //Assert.assertTrue(alert.getText().contains(alertText));


        // this is available from REPORTNG this will add custom messages for the passed/failed cases
        //Reporter.log("Customer successfully created");


    }

}
