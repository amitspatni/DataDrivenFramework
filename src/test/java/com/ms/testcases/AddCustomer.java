package com.ms.testcases;

import com.ms.base.TestBase;
import com.ms.utilities.ExcelReader;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class AddCustomer extends TestBase {

    public static ExcelReader excel = null;

    @DataProvider
    public static Object[][] getData() {

        String userDirectory = System.getProperty("user.dir");
        String excelFilePath = userDirectory + "\\src\\test\\resources\\excel\\testdata.xlsx";



        if (excel==null) {

            excel = new ExcelReader(excelFilePath);
        }

        String sheetNames = "AddCustomer";
        int rows = excel.getRowCount(sheetNames);
        int cols = excel.getColumnCount(sheetNames);

        Object[][] data = new Object[rows-1][cols];

        for (int rowNum=2; rowNum<=rows; rowNum++ ) {

            for (int colNum=0; colNum<cols; colNum++) {

                data[rowNum-2][colNum] =   excel.getCellData(sheetNames, colNum, rowNum);

            }
        }
        return data;

    }


    @Test(dataProvider = "getData")
    public void addCustomer(String firstName, String lastName, String postalCode, String alertText) /* throws InterruptedException */ {

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
