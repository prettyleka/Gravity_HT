package PagesUI;

import Infrastructure.UI.Selenium.Configuration;
import Infrastructure.UI.Selenium.InfraStructureSelenium;
import org.openqa.selenium.By;

public class LoginPage {
    InfraStructureSelenium infraStructureSelenium = new InfraStructureSelenium();
    private static final Configuration configuration = new Configuration();
    private static final String userName = "//input[@id=\"username\"]";
    private static final String password = "//input[@id=\"password\"]";
    private static final String loginBtn = "//button[@class=\"btn__primary--large from__button--floating\"]";



    public void enterUserName() throws Exception {
        infraStructureSelenium.enterText(configuration.getValue("usernameLink"), By.xpath(userName));
    }
    public void enterPassword() throws Exception {
        infraStructureSelenium.enterText(configuration.getValue("passwordLink"), By.xpath(password));
    }
    public void clickLoginBtn(){
        infraStructureSelenium.click(By.xpath(loginBtn));
    }




}