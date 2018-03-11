import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.util.List;

public class BookFlightPage extends BasePages {
    @FindBy(xpath = "//div[@class = 'button button--cta-selection button--cta-selection-arrow-bottom   ']")
    private WebElement flightButton;

    @FindBy(xpath = "//h2[text()=' Outbound flight']")
    private WebElement outboundFlightTitle;


    public BookFlightPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Check for present flight options
     * <p>
     * Check for present flight options. If any flight isn't present, return false
     * </p>
     *
     * @return true or false
     * @throws InterruptedException
     * @throws IOException
     */
    public boolean checkFlight() throws InterruptedException, IOException {
        try {
            waitForElementPresent(flightButton, 10000);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Select flight
     * <p>
     * Select depart's flight and if it needs return's flight
     * </p>
     *
     * @param flagReturnOn
     * @throws InterruptedException
     */
    public void selectFlight(Boolean flagReturnOn) throws InterruptedException {
        Thread.sleep(4000);
        List<WebElement> flightSelectButtons = driver.findElements(By.xpath("//div[@class='panel flight-result active']"));
        flightSelectButtons.get(0).click();
        Thread.sleep(4000);
        if (flagReturnOn) {
            WebElement flightSelectButton2 = driver.findElement(By.xpath("//div[@class='panel flight-result active']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", flightSelectButton2);
            flightSelectButton2.click();
            Thread.sleep(4000);
        }
        List<WebElement> nextButtons = driver.findElements(By.xpath("//button[@name = 'next_button']"));
        nextButtons.get(0).click();
        log.info("Moved to \"Get more options\" page");
    }
}