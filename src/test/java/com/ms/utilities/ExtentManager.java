package com.ms.utilities;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent==null) {

            extent =  new ExtentReports(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\extent.html", true, DisplayOrder.OLDEST_FIRST);

            extent.loadConfig(new File(System.getProperty("user.dir") + "\\src\\test\\extentconfig\\ReportsConfig.xml"));


        }
        return extent;
    }

}
