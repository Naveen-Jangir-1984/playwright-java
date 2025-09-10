package pages;

import com.microsoft.playwright.Page;

public class LoginPage {
	private Page page;

	private String usernameInput = "#user-name";
	private String passwordInput = "#password";
	private String loginButton = "#login-button";

	public LoginPage(Page page) {
		this.page = page;
	}

	public void login(String username, String password) {
		page.fill(usernameInput, username);
		page.fill(passwordInput, password);
		page.click(loginButton);
	}
}
