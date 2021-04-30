package com.ms.utilities;

import com.ms.base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class TestUtilities extends TestBase {

    public static String screenShotPath;
    public static String screenShotFileName;
    public static void captureScreenshot() throws IOException {

        screenShotPath = "\\target\\surefire-reports\\html\\";
        Date d =  new Date();
        screenShotFileName = d.toString().replace(":", "-").replace(" ", "_")+".jpg";

//        screenShotFileName = "error.jpg";

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + screenShotPath + screenShotFileName ) );


    }


}
