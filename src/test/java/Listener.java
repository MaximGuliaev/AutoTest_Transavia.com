import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class Listener implements ITestListener {
    static WebDriver driver;
    static ExtentTest test;
    static ExtentReports extent;

    /**
     * On java start
     * <p>
     * This will execute before the main java start (@Test)
     * </p>
     *
     * @param result
     */
    public void onTestStart(ITestResult result) {
        Reporter.log("The execution of the main java starts now");
    }

    /**
     * On java success
     * <p>
     * This will execute only when the java is pass
     * </p>
     *
     * @param result
     */
    public void onTestSuccess(ITestResult result) {
        printTestResults(result);
    }

    /**
     * On java failure
     * <p>
     * This will execute only on the event of fail java
     * </p>
     *
     * @param result
     */
    public void onTestFailure(ITestResult result) {
        if (driver != null) {

            try {
                this.test = ((BaseTestCase) result.getInstance()).test;
                test.log(LogStatus.ERROR, "Test was failed :" + test.addScreenCapture(doScreenshot(result)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            printTestResults(result);
            this.extent = ((BaseTestCase) result.getInstance()).extent;
            extent.endTest(test);
        }
    }

    /**
     * On java skipped
     * <p>
     * This will execute only if any of the main java(@Test) get skipped
     * </p>
     *
     * @param result
     */
    public void onTestSkipped(ITestResult result) {
        printTestResults(result);
    }

    /**
     * On java failed but within success percentage
     * <p>
     * This will executed in case of java pass or fail
     * </p>
     *
     * @param result
     */
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    /**
     * On start
     * <p>
     * This will execute before starting of java set/batch
     * </p>
     *
     * @param result
     */
    public void onStart(ITestContext result) {
        Reporter.log("Began executing java " + result.getName(), true);
    }

    /**
     * On finish
     * <p>
     * This will execute, once the Test set/batch is finished
     * </p>
     *
     * @param result
     */
    public void onFinish(ITestContext result) {
        Reporter.log("Completed executing java " + result.getName(), true);
    }

    /**
     * Generates time stamp
     * <p>
     * Generates time stamp in predefined format
     * </p>
     *
     * @return String time stamp
     */
    static String createTimeStamp() {
        long timeNow = System.currentTimeMillis();
        SimpleDateFormat Stamp = new SimpleDateFormat("HmmssS");
        return Stamp.format(timeNow);
    }

    /**
     * Do screenshot
     * <p>
     * Do screenshot with the specified name and folder for save
     * </p>
     *
     * @param result
     * @return String name and path to screenshot
     * @throws IOException
     */
    public String doScreenshot(ITestResult result) throws IOException {
        // since you need the driver in your screenshot method do this:
        this.driver = ((BaseTestCase) result.getInstance()).driver;

        File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File file = new File("reports/screenshots/" + createTimeStamp() + ".png");
        FileUtils.copyFile(scr, file);
        String pathnameScreenshot = file.getAbsolutePath();
        return pathnameScreenshot;
    }

    /**
     * Print java results
     * <p>
     * This will provide the information on the java
     * </p>
     *
     * @param result
     */
    private void printTestResults(ITestResult result) {
        Reporter.log("Test Method resides in " + result.getTestClass().getName(), true);
        if (result.getParameters().length != 0) {
            String params = null;
            for (Object parameter : result.getParameters()) {
                params += parameter.toString() + ",";
            }
            Reporter.log("Test Method had the following parameters : " + params, true);
        }
        String status = null;
        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                status = "Pass";
                break;
            case ITestResult.FAILURE:
                status = "Failed";
                break;
            case ITestResult.SKIP:
                status = "Skipped";
        }
        Reporter.log("Test Status: " + status, true);
    }
}