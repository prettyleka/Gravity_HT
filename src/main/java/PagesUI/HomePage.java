package PagesUI;

import Infrastructure.UI.Selenium.Configuration;
import Infrastructure.UI.Selenium.InfraStructureSelenium;
import org.openqa.selenium.By;

public class HomePage  {

    InfraStructureSelenium infraStructureSelenium = new InfraStructureSelenium();
    private static final Configuration configuration = new Configuration();
    String loginBtn = "//a[@class=\"nav__button-secondary btn-md btn-secondary-emphasis\"]";

    public void moveToBasePage() throws Exception {
        infraStructureSelenium.moveToUrl(configuration.getValue("url"));
    }
    public void clickLoginBtn(){
        infraStructureSelenium.click(By.xpath(loginBtn));
    }
}
