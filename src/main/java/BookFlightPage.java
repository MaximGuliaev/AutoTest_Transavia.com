import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class BookFlightPage extends BasePages {
    @FindBy(xpath = "//div[@class = 'button button--cta-selection button--cta-selection-arrow-bottom   ']")
    private WebElement flightButton;

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
}
