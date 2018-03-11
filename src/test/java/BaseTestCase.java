import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;

// Implement TestNG listeners
@Listeners(Listener.class)

public abstract class BaseTestCase {
    static WebDriver driver;
    static ExtentReports extent;
    static ExtentTest test;
    protected final Log log = LogFactory.getLog(getClass());
    static Properties resources1 = new Properties();

    /**
     * Get resources#1
     * <p>
     * Get resources#1 from received path to property files
     * </p>
     *
     * @param pathFile1 - config property file
     * @throws IOException
     */
    public void getResources1(String pathFile1) throws IOException {
        FileInputStream resourcesFile1;
        try {
            resourcesFile1 = new FileInputStream(pathFile1);
            resources1.load(resourcesFile1);
        } catch (IOException e) {
            log.error("Properties file " + pathFile1 + "wasn't found");
        }
    }

    // Pages
    HomePage homePage;
    BookFlightPage bookFlightPage;
    GetMorePage getMorePage;

    abstract void initPages();

    abstract void before() throws IOException, InterruptedException;

    /**
     * Generates time stamp
     * <p>
     * Generates time stamp in predefined format
     * </p>
     *
     * @param pattern - date or time format which need for
     * @return String time stamp
     */
    static String createTimeStamp(String pattern) {
        long timeNow = System.currentTimeMillis();
        SimpleDateFormat Stamp = new SimpleDateFormat(pattern);
        return Stamp.format(timeNow);
    }

    /**
     * Setup chrome driver
     * <p>
     * Start the web driver and host
     * </p>
     */
    public void setupChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(resources1.getProperty("checkLogin.host"));
    }

    /**
     * Setup before testing
     * <p>
     * Running properties for report before start the testing process:
     * - Log properties
     * - ExtentReport
     * </p>
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @BeforeTest
    public void setUp() throws InterruptedException, IOException {
        log.info("Execution of \"Before testing\" is starting");
        PropertyConfigurator.configure("properties/log.properties");

        extent = new ExtentReports("reports//ExtentReport." + createTimeStamp("dd.MM.yyyy") + ".html",
                true);
        extent.loadConfig(new File("extent-config.xml"));
    }

    /**
     * Setup after testing
     * <p>
     * Stop and close all things we must after the testing:
     * - WebDriver
     * - ExtentReport
     * </p>
     */
    @AfterTest
    public void tearDown() {
        log.info("Execution of \"After testing\" is starting");
        if (driver != null) {
            log.info("Closing chrome browser");
            driver.quit();
        }
        extent.flush();
        extent.close();
    }
}