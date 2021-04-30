package com.ms.testcases;

import com.ms.base.TestBase;
import com.ms.utilities.ExcelReader;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OpenCustomer extends TestBase {

    public static ExcelReader excel = null;



    @Test(dataProvider = "getData")
    public void openCustomer(String customerName, String currency) /* throws InterruptedException */ {


        // T0 DO

        // this is available from REPORTNG this will add custom messages for the passed/failed cases
        //Reporter.log("Customer successfully created");


    }

}
