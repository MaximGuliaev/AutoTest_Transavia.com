import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.*;

import java.util.List;

public abstract class BasePages {

    protected WebDriver driver;

    public BasePages(WebDriver driver) {
        this.driver = driver;
    }

    protected final Log log = LogFactory.getLog(getClass());

    /**
     * Wait for element present
     * <p>
     * Wait of the received element present on the page during the received timeout
     * </p>
     *
     * @param element
     * @param timeout
     * @throws InterruptedException
     */
    public void waitForElementPresent(WebElement element, long timeout) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int attempts = 0;
        while (System.currentTimeMillis() - startTime < timeout) {
            try {
                element.isDisplayed();
                return;
            } catch (NoSuchElementException e) {
                Thread.sleep(1000);
                attempts++;
                if (attempts == 5) {
                    log.info(element + " wasn't find after " +
                            (System.currentTimeMillis() - startTime) / 1000 + " seconds");
                    attempts = 0;
                }
            }
        }
        throw new NoSuchElementException(element + " wasn't founded");
    }

    /**
     * Select date
     * <p>
     * Select the received month, year and day in the dropdown calendar
     * </p>
     *
     * @param calendarNumber
     * @param month
     * @param year
     * @param day
     * @throws InterruptedException
     */
    public void selectDate(int calendarNumber, String month, String year, String day) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 60000) {
            try {
                WebElement flightMonth = driver.findElement(By.xpath("//span[@class='ui-datepicker-month'][text()='" + month + "']"));
                WebElement flightYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year'][text()='" + year + "']"));
                flightMonth.isDisplayed();
                flightYear.isDisplayed();
                break;
            } catch (NoSuchElementException e) {
                List<WebElement> nexMonthButtons = driver.findElements(By.xpath("//span[@class='ui-datepicker-year']/../preceding-sibling::a[1]"));
                nexMonthButtons.get(calendarNumber).click();
                Thread.sleep(1000);
            }
        }
        Thread.sleep(3000);
        List<WebElement> dateButtons = driver.findElements(By.xpath("//a[text()='" + day + "'][@class='ui-state-default ']"));
        dateButtons.get(calendarNumber).click();
        Thread.sleep(1000);

    }
}