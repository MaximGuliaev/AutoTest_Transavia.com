import initDataFlight.Flight;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePages {
    @FindBy(id = "routeSelection_DepartureStation-input")
    private WebElement flightFromDropDown;

    @FindBy(id = "routeSelection_ArrivalStation-input")
    private WebElement flightToDropDown;

    @FindBy(xpath = "//span[@class = 'datepicker-trigger icon-font icon-calendar']")
    private WebElement departOnDate;

    @FindBy(id = "dateSelection_IsReturnFlight")
    private WebElement returnOnFlag;

    @FindBy(id = "booking-passengers-input")
    private WebElement bookingPassengers;

    @FindBy(xpath = "//*[text()='Adults ']/../following-sibling::div//button[@class = 'button button-secondary increase']")
    private WebElement plusAdultsPassengersButton;

    @FindBy(xpath = "//*[text()='Children ']/../following-sibling::div//button[@class = 'button button-secondary increase']")
    private WebElement plusChildrenPassengersButton;

    @FindBy(xpath = "//*[text()='Babies ']/../following-sibling::div//button[@class = 'button button-secondary increase']")
    private WebElement plusBabiesPassengersButton;

    @FindBy(xpath = "//button[@class='button button-secondary close']")
    private WebElement saveButton;

    @FindBy(xpath = "//ul[@class='bulletless']//preceding-sibling::div//button")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Find flight
     * <p>
     * Find flight from the received object (flight)
     * </p>
     *
     * @param flight
     * @throws InterruptedException
     */
    public void findFlight(Flight flight) throws InterruptedException {
        log.info("Method for finding flight from... to... was started");
        flightFromDropDown.click();
        WebElement departureStation = driver.findElement(By.xpath("//li[text()='" + flight.getFlightFrom() + "']"));
        departureStation.click();

        flightToDropDown.click();
        WebElement arrivalStation = driver.findElement(By.xpath("//li[text()='" + flight.getFlightTo() + "']"));
        arrivalStation.click();

        departOnDate.click();
        selectDate(flight.getDepartOnMonth(), flight.getDepartOnYear(), flight.getDepartOnDay());

        if (flight.getFlagReturnOn() == true) {
            setCheckbox(returnOnFlag, flight.getFlagReturnOn());
            selectDate(flight.getReturnOnMonth(), flight.getReturnOnYear(), flight.getReturnOnDay());
            //here need some change because on the page we have two the same active calendars
        }

        if (Integer.parseInt(flight.getAdults()) > 1 || Integer.parseInt(flight.getChildren()) > 0 || Integer.parseInt(flight.getBabies()) > 0) {
            bookingPassengers.click();
            for (int i = 1; i < Integer.parseInt(flight.getAdults()); i++) {
                plusAdultsPassengersButton.click();
            }
            for (int i = 0; i < Integer.parseInt(flight.getChildren()); i++) {
                plusChildrenPassengersButton.click();
            }
            for (int i = 0; i < Integer.parseInt(flight.getBabies()); i++) {
                plusBabiesPassengersButton.click();
            }
            saveButton.click();
        }

        searchButton.click();
        log.info("Moved to \"Book flight\" page");
    }
}