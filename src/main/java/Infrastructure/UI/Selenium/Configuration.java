package Infrastructure.UI.Selenium;

import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.config.OperatingSystem;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

public class Configuration {

    public String getBrowser() throws Exception {
        return JSONReader.getValue("browser");
    }

    public DriverManagerType getDriverManagerType() throws Exception {
        String browser = getBrowser();
        return DriverManagerType.valueOf(browser);
    }

    public String getBaseApiPath() throws Exception {
        return JSONReader.getValue("baseApiPath");
    }
    public String getValue(String valueName) throws Exception {
        return JSONReader.getValue(valueName);
    }
}
