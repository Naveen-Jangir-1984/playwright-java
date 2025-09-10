package base;

import com.microsoft.playwright.*;
import utils.JsonDataManager;
import org.testng.annotations.*;

public class BaseTest {
	protected Playwright playwright;
	protected Browser browser;
	protected Page page;

	@BeforeMethod
	public void setUp() {
		// Load test data
		JsonDataManager.load("src/test/resources/TestData.json");

		// Read environment dynamically (default: qa)
		String env = System.getProperty("env", "sit");
		String baseUrl = JsonDataManager.getBaseUrl(env);

		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		page = browser.newPage();

		// Naviagte to the url
		page.navigate(baseUrl);
	}

	@AfterMethod
	public void tearDown() {
		browser.close();
		playwright.close();
	}
}
