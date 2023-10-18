package UISelenium;

import Infrastructure.UI.Selenium.InfraStructureSelenium;
import Infrastructure.UI.Selenium.JSONUtils;
import PagesUI.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;


public class TestUI {

    private final HomePage homePage = new HomePage();
    private final LoginPage loginPage = new LoginPage();
    private final UserFeedPage userFeedPage = new UserFeedPage();
    private final UserPage userPage = new UserPage();
    private final ContactsListPage contactsListPage = new ContactsListPage();
    private final InfraStructureSelenium infraStructureSelenium = new InfraStructureSelenium();


    @Test(description = "")
    public void tryIt() throws Exception {
        JSONObject jo = new JSONObject();
        homePage.moveToBasePage();
        homePage.clickLoginBtn();
        loginPage.enterUserName();
        loginPage.enterPassword();
        loginPage.clickLoginBtn();
        userFeedPage.clickUserNameBtn();
        jo.put("myName", userFeedPage.getUserName());
        jo.put("myWorkplace", userFeedPage.getUserWorkPlace());
        jo.put("myCity", userFeedPage.getUserCity());
        userPage.clickContactListBtn();
        JSONArray x = contactsListPage.getAllContacts();
        jo.put("contacts", x);
        JSONUtils.writeJson(jo);
    }


    @AfterTest()
    public void close(){
        infraStructureSelenium.closeWindow();
    }


}
