import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.*;

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
     * Set checkbox
     * <p>
     * Set the received boolean in the received checkbox element
     * </p>
     *
     * @param checkbox
     * @param checked
     */
    public void setCheckbox(WebElement checkbox, Boolean checked) {
        if (!checkbox.isSelected() & checked || checkbox.isSelected() & !checked) {
            checkbox.click();
        }
    }

    /**
     * Select date
     * <p>
     * Select the received string month, year and day in the dropdown calendar
     * </p>
     *
     * @param month
     * @param year
     * @param day
     * @throws InterruptedException
     */
    public void selectDate(String month, String year, String day) throws InterruptedException {
        Thread.sleep(5000);
        try {
            WebElement departMonth = driver.findElement(By.xpath("//span[@class='ui-datepicker-month'][text()='" + month + "']"));
            WebElement departYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year'][text()='" + year + "']"));
            departMonth.isDisplayed();
            departYear.isDisplayed();
            return;
        } catch (NoSuchElementException e) {
            WebElement departYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year'][text()='" + year + "']"));
            departYear.click();
            WebElement nexMonthButton = driver.findElement(By.xpath("//span[@class='ui-datepicker-year'][text()='" + year + "']/../preceding-sibling::a[1]"));
            nexMonthButton.click();
            Thread.sleep(1000);
        }
        WebElement dateButton = driver.findElement(By.xpath("//a[text()='" + day + "'][@class='ui-state-default ']"));
        dateButton.click();
    }
}