package initDataFlight;

import java.io.IOException;

import static java.lang.Boolean.parseBoolean;

public class Flight extends Records {
    private String flightFrom;
    private String flightTo;
    private String departOnYear;
    private String departOnMonth;
    private String departOnDay;
    private Boolean flagReturnOn;
    private String returnOnYear;
    private String returnOnMonth;
    private String returnOnDay;
    private String adults;
    private String children;
    private String babies;


    public Flight(String flightFrom, String flightTo, String departOnMonth, String departOnYear, String departOnDay,
                  Boolean flagReturnOn, String returnOnMonth, String returnOnYear, String returnOnDay, String adults, String children, String babies) {
        setFlightFrom(flightFrom);
        setFlightTo(flightTo);
        setDepartOnMonth(departOnMonth);
        setDepartOnYear(departOnYear);
        setDepartOnDay(departOnDay);
        setFlagReturnOn(flagReturnOn);
        setDepartOnMonth(returnOnMonth);
        setDepartOnYear(returnOnYear);
        setDepartOnDay(returnOnDay);
        setAdults(adults);
        setChildren(children);
        setBabies(babies);
    }

    /**
     * Get flight
     * <p>
     * Create object "flight" from the received path to property files
     * </p>
     *
     * @param pathFile
     * @return Object "flight"
     * @throws IOException
     */
    public static Flight getFlight(String pathFile) throws IOException {
        readResources(pathFile);

        Flight businessAccount = new Flight(resources.getProperty("findFlight.flightFrom"),
                resources.getProperty("findFlight.flightTo"),
                resources.getProperty("findFlight.departOnMonth"),
                resources.getProperty("findFlight.departOnYear"),
                resources.getProperty("findFlight.departOnDay"),
                parseBoolean(resources.getProperty("findFlight.flagReturnOn")),
                resources.getProperty("findFlight.returnOnMonth"),
                resources.getProperty("findFlight.returnOnYear"),
                resources.getProperty("findFlight.returnOnDay"),
                resources.getProperty("findFlight.adults"),
                resources.getProperty("findFlight.children"),
                resources.getProperty("findFlight.babies"));
        return businessAccount;
    }

    public String getFlightFrom() {
        return flightFrom;
    }

    public void setFlightFrom(String flightFrom) {
        this.flightFrom = flightFrom;
    }

    public String getFlightTo() {
        return flightTo;
    }

    public void setFlightTo(String flightTo) {
        this.flightTo = flightTo;
    }

    public String getDepartOnYear() {
        return departOnYear;
    }

    public void setDepartOnYear(String departOnYear) {
        this.departOnYear = departOnYear;
    }

    public String getDepartOnMonth() {
        return departOnMonth;
    }

    public void setDepartOnMonth(String departOnMonth) {
        this.departOnMonth = departOnMonth;
    }

    public String getDepartOnDay() {
        return departOnDay;
    }

    public void setDepartOnDay(String departOnDay) {
        this.departOnDay = departOnDay;
    }

    public Boolean getFlagReturnOn() {
        return flagReturnOn;
    }

    public void setFlagReturnOn(Boolean flagReturnOn) {
        this.flagReturnOn = flagReturnOn;
    }

    public String getReturnOnYear() {
        return returnOnYear;
    }

    public void setReturnOnYear(String returnOnYear) {
        this.returnOnYear = returnOnYear;
    }

    public String getReturnOnMonth() {
        return returnOnMonth;
    }

    public void setReturnOnMonth(String returnOnMonth) {
        this.returnOnMonth = returnOnMonth;
    }

    public String getReturnOnDay() {
        return returnOnDay;
    }

    public void setReturnOnDay(String returnOnDay) {
        this.returnOnDay = returnOnDay;
    }

    public String getAdults() {
        return adults;
    }

    public void setAdults(String adults) {
        this.adults = adults;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public String getBabies() {
        return babies;
    }

    public void setBabies(String babies) {
        this.babies = babies;
    }
}