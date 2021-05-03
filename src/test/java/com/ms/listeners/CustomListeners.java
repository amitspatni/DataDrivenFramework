package com.ms.listeners;



import com.ms.base.TestBase;
import com.ms.utilities.TestUtilities;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.*;
import org.testng.annotations.Test;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Locale;


public class CustomListeners extends TestBase implements ITestListener {



    @Override
    public void onTestStart(ITestResult result) {

        test = repo.startTest(result.getName().toUpperCase());

        if (!TestUtilities.isTestRunnable(result.getName(),excel )) {

            throw new SkipException("Skipping the tests" + result.getName() + " as the run mode is false");
        }


    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.log(LogStatus.PASS, result.getName().toUpperCase()+" PASS");
        repo.endTest(test);
        repo.flush();
    }

    @Override
    public void onTestFailure(ITestResult result) {
    System.setProperty("org.uncommons.reportng.escape-output", "false");
        try {
            TestUtilities.captureScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TestBase.test.log(LogStatus.FAIL, result.getName().toUpperCase()+" FAIL with Exception" + result.getThrowable());
        TestBase.test.log(LogStatus.FAIL,test.addScreenCapture(TestUtilities.screenShotFileName));

        Reporter.log("Capture Screenshot");
        Reporter.log("<a target=\"_blank\" href="+TestUtilities.screenShotFileName+">Screenshot</a>");
        Reporter.log("<br>");
        Reporter.log("<a target=\"_blank\" href="+TestUtilities.screenShotFileName+"><img src=" +TestUtilities.screenShotPath + TestUtilities.screenShotFileName+" height=500 width=300> </img></a>");
        repo.endTest(test);
        repo.flush();

    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test.log(LogStatus.SKIP, "Test is skipped " + result.getName());
        repo.endTest(test);
        repo.flush();

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
