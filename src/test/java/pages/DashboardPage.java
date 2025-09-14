package pages;

import com.microsoft.playwright.Page;

public class DashboardPage {
  private Page page;

  private String burgerMenuButton = "#react-burger-menu-btn";
  private String logoutButton = "#logout_sidebar_link";

  public DashboardPage(Page page) {
    this.page = page;
  }

  public void logout() {
    page.click(burgerMenuButton);
    page.click(logoutButton);
  }
}
