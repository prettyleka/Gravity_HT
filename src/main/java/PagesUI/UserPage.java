package PagesUI;

import Infrastructure.UI.Selenium.InfraStructureSelenium;
import org.openqa.selenium.By;

public class UserPage {
    InfraStructureSelenium infraStructureSelenium = new InfraStructureSelenium();
    private static final String contactList = "//a[contains(@id,\"ember\")]/span";
    public void clickContactListBtn(){
        infraStructureSelenium.click(By.xpath(contactList));
    }
}
