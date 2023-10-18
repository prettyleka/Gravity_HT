package PagesUI;

import Infrastructure.UI.Selenium.InfraStructureSelenium;
import org.openqa.selenium.By;

public class UserFeedPage {
    InfraStructureSelenium infraStructureSelenium = new InfraStructureSelenium();
    private static final String userNameBtn = "//div[@class=\"t-16 t-black t-bold\"]";
    private static final String userName = "//h1";
    private static final String userWorkPlace = "//div[@class=\"text-body-medium break-words\"]";
    private static final String userCity = "//span[@class=\"text-body-small inline t-black--light break-words\"]";

    public void clickUserNameBtn(){
        infraStructureSelenium.click(By.xpath(userNameBtn));
    }
    public String getUserName(){
       return infraStructureSelenium.getText(By.xpath(userName));
    }
    public String getUserWorkPlace(){
        return infraStructureSelenium.getText(By.xpath(userWorkPlace));
    }
    public String getUserCity(){
        return infraStructureSelenium.getText(By.xpath(userCity));
    }

}
