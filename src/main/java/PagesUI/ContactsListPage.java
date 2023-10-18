package PagesUI;

import Infrastructure.UI.Selenium.InfraStructureSelenium;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class ContactsListPage {
    InfraStructureSelenium infraStructureSelenium = new InfraStructureSelenium();
    private static final String  listOfContacts = "//main//ul/li//div[contains(@class, \"entity-result__content\")]";
    private static final String  totalNumberOfContacts = "//h2[contains(@class, \"pb2 t-black--light t-14\")]/div";
    private static final String nextBtn = "//button[contains(@class, \"button--next\")]";

    public JSONArray getAllContacts() {
        JSONArray allContacts = new JSONArray();
        while (!infraStructureSelenium.getAttributeBoolean(By.xpath(nextBtn), "disabled")) {
            List<WebElement> allElements = infraStructureSelenium.findAll(By.xpath(listOfContacts));
            for (WebElement webElement : allElements) {
                allContacts.add(JSONObject.toString("name", webElement.getAttribute("innerText").split("\n")[0]));
                allContacts.add(JSONObject.toString("working place", webElement.getAttribute("innerText").split("\n")[5]));
                allContacts.add(JSONObject.toString("living place", webElement.getAttribute("innerText").split("\n")[6]));

            }
            infraStructureSelenium.click(By.xpath(nextBtn));
        }
        return allContacts;
    }




}
