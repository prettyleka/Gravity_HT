package UISelenium;

import Infrastructure.UI.Selenium.InfraStructureSelenium;
import PagesUI.HomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;


public class TestItNow {


    HomePage homePage = new HomePage();
    InfraStructureSelenium infraStructureSelenium = new InfraStructureSelenium();

    public TestItNow() throws Exception {
    }


    @Test(description = "")
    public void tryIt(){
        homePage.moveToGoogle();
        homePage.enterText("Dogs");
        homePage.clickSearch_ListOpened();
        infraStructureSelenium.openNewTab();
        infraStructureSelenium.closeTab(1);
    }


    @AfterTest()
    public void close(){
        infraStructureSelenium.closeWindow();
    }


}
