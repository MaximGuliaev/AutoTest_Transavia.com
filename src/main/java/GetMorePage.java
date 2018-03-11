import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.util.List;

public class GetMorePage extends BasePages {
    @FindBy(xpath = "//h1[contains(text(),'Get more out of your trip!')]")
    private WebElement getMorePageTitle;

    @FindBy(xpath = "//div[@class = 'front']")
    private WebElement totalPriceField;

    public GetMorePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Check for present necessary option
     * <p>
     * Check for present necessary option. If necessary option isn't present, return false
     * </p>
     *
     * @return true or false
     * @throws InterruptedException
     * @throws IOException
     */
    private boolean checkPresentOption(WebElement element, long timeout) throws InterruptedException, IOException {
        try {
            waitForElementPresent(element, timeout);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Check that GetMorePage is present
     * <p>
     * Check that GetMorePage is present
     * </p>
     *
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public boolean checkGetMorePage() throws IOException, InterruptedException {
        return checkPresentOption(getMorePageTitle, 10000);
    }

    /**
     * Select more option
     * <p>
     * Select more option for flight if it need
     * </p>
     *
     * @param optionPackage
     * @return
     * @throws InterruptedException
     * @throws IOException
     */
    public String selectMoreOption(String optionPackage) throws InterruptedException, IOException {
        List<WebElement> getMoreOption = driver.findElements(By.xpath("//span[text()='" + optionPackage + "']"));
        List<WebElement> selectOptionalPackageButtons = driver.findElements(By.xpath("//button[@class='button button--selection']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", selectOptionalPackageButtons.get(4));

        if (optionPackage.equals("Plus") & (checkPresentOption(getMoreOption.get(1), 2000))) {
            selectOptionalPackageButtons.get(4).click();
            Thread.sleep(3000);
        }
        if (optionPackage.equals("Max") & (checkPresentOption(getMoreOption.get(1), 2000))) {
            selectOptionalPackageButtons.get(5).click();
            Thread.sleep(3000);
        }
        Thread.sleep(6000);
        return totalPriceField.getAttribute("value");
    }
}