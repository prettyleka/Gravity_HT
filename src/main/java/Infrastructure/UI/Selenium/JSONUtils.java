package Infrastructure.UI.Selenium;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.json.Json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class JSONReader {

    public static String getValue(String keyName) throws Exception  {
        Object obj = new JSONParser().parse(new FileReader("src/main/resources/config.json"));
        JSONObject jo = (JSONObject) obj;

        return jo.get(keyName).toString();
    }


    public static void writeJson(JSONObject jsonObject) throws IOException {
        FileWriter file = new FileWriter("src/main/resources/output.json");
        file.write(jsonObject.toJSONString());
        file.close();
    }



}
