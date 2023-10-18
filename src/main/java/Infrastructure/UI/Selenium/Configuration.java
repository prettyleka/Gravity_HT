package Infrastructure.UI.Selenium;

import io.github.bonigarcia.wdm.config.DriverManagerType;

public class Configuration {

    public String getBrowser() throws Exception {
        return JSONUtils.getValue("browser");
    }

    public DriverManagerType getDriverManagerType() throws Exception {
        String browser = getBrowser();
        return DriverManagerType.valueOf(browser);
    }

    public String getBaseApiPath() throws Exception {
        return JSONUtils.getValue("baseApiPath");
    }
    public String getValue(String valueName) throws Exception {
        return JSONUtils.getValue(valueName);
    }
}
