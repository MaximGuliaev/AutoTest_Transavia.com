import com.relevantcodes.extentreports.LogStatus;
import initDataFlight.Flight;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

// Implement TestNG listeners
@Listeners(Listener.class)

public class FindFlight_1 extends BaseTestCase {

    /**
     * Run pages for testing
     * <p>
     * Running all pages we need for these testing:
     * - homePage
     * - bookFlightPage
     * </p>
     */
    protected void initPages() {
        homePage = PageFactory.initElements(driver, HomePage.class);
        bookFlightPage = PageFactory.initElements(driver, BookFlightPage.class);
    }

    /**
     * Setup before testing
     * <p>
     * Running all methods we need before start these testing:
     * - getResources1
     * - setupChromeDriver
     * - initPages
     * </p>
     *
     * @throws InterruptedException
     * @throws IOException
     */
    void before() throws InterruptedException, IOException {
        getResources1("src/resources/config.properties");
        setupChromeDriver();
        Thread.sleep(40000); // This is for input captcha
        initPages();
    }

    /**
     * Find flight
     * <p>
     * The main testing method:
     * - transfers departure and arrival data
     * - check that the system finds and offers flight options
     * </p>
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test
    public void findFlight_1() throws Exception {
        before();
        log.info("Execution of Main java is carring on");
        test = BaseTestCase.extent.startTest("findFlight_1", "Find flight from departure station to arrival station which are specified in the test data");
        test.log(LogStatus.INFO, "Find flight from... to... was started");
        Flight criteriaFlight = Flight.getFlight("src/resources/flight#1.properties");
        homePage.findFlight(criteriaFlight);

        boolean flightOk = bookFlightPage.checkFlight();
        try {
            Assert.assertTrue(flightOk, "Test data for flight is not correct");
        } catch (AssertionError t) {
            test.log(LogStatus.FAIL, "System does not can find and offer any flight options");
            throw new AssertionError(t);
        }
        test.log(LogStatus.PASS, "System finds and offers some flight options");
        log.info("Test data for flight is correct");

        extent.endTest(test);
    }
}