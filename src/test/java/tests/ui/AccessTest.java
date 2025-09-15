package tests.ui;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pages.DashboardPage;
import pages.LoginPage;
import utils.JsonDataManager;

import org.testng.annotations.Test;

public class AccessTest extends BaseTest {

  @Test
  @Description("Login")
  @Severity(SeverityLevel.CRITICAL)
  public void testAccess() {
    String username = JsonDataManager.getUser("StandardUser", "username");
    String password = JsonDataManager.getUser("StandardUser", "password");

    LoginPage loginPage = new LoginPage(page);
    loginPage.login(username, password);

    DashboardPage dashboardPage = new DashboardPage(page);
    dashboardPage.logout();
  }
}
