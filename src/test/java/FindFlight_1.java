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
     * Run pages before java
     * <p>
     * Running all pages we need before start these java:
     * - homePage
     * - bookFlightPage
     * </p>
     */
    protected void initPages() {
        homePage = PageFactory.initElements(BaseTestCase.driver, HomePage.class);
        bookFlightPage = PageFactory.initElements(BaseTestCase.driver, BookFlightPage.class);
    }

    /**
     * Setup before java
     * <p>
     * Running all methods we need before start these java:
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
        Thread.sleep(40000);
        initPages();
    }

    /**
     * Find flight
     * <p>
     * The main java method:
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
        Flight flight = Flight.getFlight("src/resources/flight#1.properties");
        homePage.findFlight(flight);

        boolean flightOk = bookFlightPage.checkFlight();
        try {
            Assert.assertTrue(flightOk, "Test data for flight is not correct");
        } catch (AssertionError t) {
            test.log(LogStatus.FAIL, "System does not can find and offer flight options");
            throw new AssertionError(t);
        }
        test.log(LogStatus.PASS, "System finds and offers flight options");
        log.info("Test data for flight is correct");

        extent.endTest(test);
    }
}