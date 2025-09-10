package tests.ui;

import base.BaseTest;
import pages.LoginPage;
import utils.JsonDataManager;

import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

  @Test
  public void testLogin() {
    String username = JsonDataManager.getUser("StandardUser", "username");
    String password = JsonDataManager.getUser("StandardUser", "password");

    LoginPage loginPage = new LoginPage(page);
    loginPage.login(username, password);
  }
}
