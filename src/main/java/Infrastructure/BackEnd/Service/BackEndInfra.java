package Infrastructure.BackEnd.Service;

import Infrastructure.BackEnd.Model.WeatherResponse.WeatherResponse;
import Infrastructure.UI.Selenium.Configuration;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class BackEndInfra{
    private static final String weather = "/data/2.5/weather?q={que}&APPID={APPIDvalue}&units={unitValue}";
    private static final Configuration configuration = new Configuration();
    private static final int numOfThreads;

    static {
        try {
            numOfThreads = Integer.parseInt(configuration.getValue("numOfThreads"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public BackEndInfra() throws Exception {
        if(numOfThreads >1){
            multiThreadingRunner(numOfThreads);
        }
        else {
            oneThreadRunner();
        }
    }

    public static RequestSpecification multiThreadingRunner(int numOfThreads) throws Exception {
        RestAssured.baseURI = String.valueOf(configuration.getBaseApiPath());
        ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);
            for (int i = 0; i < numOfThreads; i++) {
                Runnable task = () -> {
                    try {
                        RestAssured.given().header("Content-Type", "application/json");
                        int responseCode = RestAssured.given().get().statusCode();
                        if (responseCode==200) {
                            BufferedReader in = new BufferedReader(new InputStreamReader(RestAssured.given().get().asInputStream()));
                            String inputLine;
                            StringBuilder content = new StringBuilder();
                            while ((inputLine = in.readLine()) != null) {
                                content.append(inputLine);
                            }
                            in.close();
                       } else {
                            System.out.println("Request to " + RestAssured.baseURI + " in thread " + Thread.currentThread().getId() + " failed with response code " + responseCode);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                };
                executor.execute(task);
            }
            executor.shutdown();
        return RestAssured.given();
    }




    public static void oneThreadRunner() throws Exception {
        RestAssured.baseURI = String.valueOf(configuration.getBaseApiPath());
        RestAssured.given().header("Content-Type", "application/json");
    }

    public WeatherResponse getWeather(String cityCountry, String metric) throws Exception {
        String aapid = configuration.getValue("APIkey");
        return RestAssured.given().log().all()
                .pathParam("que", cityCountry)
                .pathParam("APPIDvalue", aapid)
                .pathParam("unitValue", metric)
                .get(weather)
                .then().statusCode(200).extract().response()
                .prettyPeek().as(WeatherResponse.class);
    }

}
