package PagesUI;

import Infrastructure.UI.Selenium.InfraStructureSelenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage  {

    InfraStructureSelenium infraStructureSelenium = new InfraStructureSelenium();


    String searchLine_ID = "APjFqb";
    String searchBtnOpenedList_xpath = "//div[@class=\"lJ9FBc\"]//input[@name= \"btnK\"]";
    String searchBtnClosedList_xpath = "//div[@class=\"FPdoLc lJ9FBc\"]//input[@name= \"btnK\"]";

    public HomePage() throws Exception {
    }

    public void moveToGoogle(){
        infraStructureSelenium.moveToUrl("http://google.com");
    }
    public void enterText(String text){
        infraStructureSelenium.type(text, By.id(searchLine_ID));
    }

    public void clickSearch_ListOpened(){
        infraStructureSelenium.click(By.xpath(searchBtnOpenedList_xpath));
    }
}
