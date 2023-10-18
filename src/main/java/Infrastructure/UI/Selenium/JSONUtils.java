package Infrastructure.UI.Selenium;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class JSONUtils {

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
