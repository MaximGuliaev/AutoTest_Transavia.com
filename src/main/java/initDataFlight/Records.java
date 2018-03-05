package initDataFlight;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class Records {
    //    protected final Log log = LogFactory.getLog(getClass());
    static Properties resources = new Properties();

    private String month;
    private String year;
    private String day;

    /**
     * Read resources
     * <p>
     * Read resources from the received path to property files
     * </p>
     *
     * @param pathFile
     * @throws IOException
     */
    public static void readResources(String pathFile) throws IOException {
        FileInputStream resourcesFile;
        try {
            resourcesFile = new FileInputStream(pathFile);
            resources.load(resourcesFile);
        } catch (IOException e) {
//            log.error("Properties file wasn't found");
        }
    }

    // Test Data;
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}