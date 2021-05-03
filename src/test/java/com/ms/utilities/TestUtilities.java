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
import java.util.Hashtable;

public class TestUtilities extends TestBase {

    public static String screenShotPath;
    public static String screenShotFileName;
    public static ExcelReader excel = null;
    public static String userDirectory = System.getProperty("user.dir");
    public static String excelFilePath = userDirectory + "\\src\\test\\resources\\excel\\testdata.xlsx";

    @DataProvider
    public static Object[][] getData(Method m) {

        if (excel==null) {

            excel = new ExcelReader(excelFilePath);
        }


        String sheetName = m.getName();
        int rows = excel.getRowCount(sheetName);
        int cols = excel.getColumnCount(sheetName);

        Object[][] data = new Object[rows-1][1];

        Hashtable<String, String> table = null;

        for (int rowNum=2; rowNum<=rows; rowNum++ ) {

                table = new Hashtable<String, String>();

                for (int colNum=0; colNum<cols; colNum++) {

                    table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum ));

                    data[rowNum-2][0] =   table;

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


    public static boolean isTestRunnable(String testname, ExcelReader excel){

        String sheetName = "TestSuite";
        int rows = excel.getRowCount(sheetName);


        for (int rowNum=2; rowNum<=rows; rowNum++) {

            System.out.println("Debugging  to figure out root cause row count is" + rowNum);

            String testCase = excel.getCellData(sheetName,"TCID", rowNum );



            if (testCase.equalsIgnoreCase(testname)) {

                String runmode = excel.getCellData(sheetName, "RunMode", rowNum );

                System.out.println("run mode for test case " + testCase + runmode);


                if (runmode.equalsIgnoreCase("Y"))
                    return true;
                else
                    return false;
            }
        }

        return false;

    }

}
