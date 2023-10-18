package UISelenium;

import Infrastructure.BackEnd.Model.WeatherResponse.WeatherResponse;
import Infrastructure.BackEnd.Service.BackEndInfra;
import org.testng.annotations.Test;


public class TestAPI {
    private final BackEndInfra backEndInfra = new BackEndInfra();
    public TestAPI() throws Exception {
    }

    @Test(description = "")
    public void London() throws Exception {
        WeatherResponse london = backEndInfra.getWeather("London,uk", "imperial");
        System.out.println(london.sys.getCountry());
        System.out.println(london.main.getTemp());
    }

    @Test(description = "")
    public void NewYork() throws Exception {
        WeatherResponse ny = backEndInfra.getWeather("New York,us", "imperial");
        System.out.println(ny.sys.getCountry());
        System.out.println(ny.main.getTemp());
    }

    @Test(description = "")
    public void TelAviv() throws Exception {
        WeatherResponse ta = backEndInfra.getWeather("Tel-Aviv,il", "metric");
        System.out.println(ta.sys.getCountry());
        System.out.println(ta.main.getTemp());
    }
}
