package com.ms.utilities;

import com.ms.base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

public class TestUtilities extends TestBase {

    public static String screenShotPath;
    public static String screenShotFileName;
    public static ExcelReader excel = null;

    @DataProvider
    public static Object[][] getData(Method m) {

        String userDirectory = System.getProperty("user.dir");
        String excelFilePath = userDirectory + "\\src\\test\\resources\\excel\\testdata.xlsx";
        Object[][] data = null;

        if (excel==null) {

            excel = new ExcelReader(excelFilePath);
        }

            String sheetName = m.getName();
            int rows = excel.getRowCount(sheetName);
            int cols = excel.getColumnCount(sheetName);

            data = new Object[rows-1][cols];

            for (int rowNum=2; rowNum<=rows; rowNum++ ) {

                for (int colNum=0; colNum<cols; colNum++) {

                    data[rowNum-2][colNum] =   excel.getCellData(sheetName, colNum, rowNum);

                }
            }


        return data;

    }




    public static void captureScreenshot() throws IOException {

        screenShotPath = "\\target\\surefire-reports\\html\\";
        Date d =  new Date();
        screenShotFileName = d.toString().replace(":", "-").replace(" ", "_")+".jpg";

//        screenShotFileName = "error.jpg";

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + screenShotPath + screenShotFileName ) );


    }



}
