package base;

import com.microsoft.playwright.*;

import utils.AllureEnvWriter;
import utils.JsonDataManager;
import org.testng.annotations.*;
import java.nio.file.Paths;

public class BaseTest {
  protected Playwright playwright;
  protected Browser browser;
  protected BrowserContext context;
  protected Page page;

  @BeforeMethod
  public void setUp() {
    // Load test data
    JsonDataManager.load("src/test/resources/Environments.json");

    // Read environment and browser dynamically (default: sit and chromium)
    String env = System.getProperty("env", "sit");
    String browserName = System.getProperty("browser", "chromium");
    AllureEnvWriter.writeEnv(env.toUpperCase(), browserName.toUpperCase());

    playwright = Playwright.create();
    browser = playwright.chromium().launch(
        new BrowserType.LaunchOptions().setHeadless(false));

    // Create a context with video recording enabled
    context = browser.newContext(
        new Browser.NewContextOptions()
            .setRecordVideoDir(Paths.get("target/videos/")) // where videos will be stored
            .setRecordVideoSize(1280, 720) // optional: resolution
    );

    // Create a page inside this context
    page = context.newPage();

    // Navigate to the URL
    String baseUrl = JsonDataManager.getBaseUrl(env);
    page.navigate(baseUrl);
  }

  @AfterMethod
  public void tearDown() {
    if (page != null) {
      // Get video path
      Video video = page.video();
      page.close();

      if (video != null) {
        String webmPath = video.path().toString();
        String mp4Path = webmPath.replace(".webm", ".mp4");

        // Convert .webm → .mp4 using ffmpeg
        String ffmpegPath = "C:\\Practice\\Java\\ffmpeg\\bin\\ffmpeg.exe";
        try {
          ProcessBuilder pb = new ProcessBuilder(
              ffmpegPath, "-y", "-i", webmPath, "-c:v", "libx264", "-crf", "23", "-preset", "fast", mp4Path);
          pb.inheritIO().start().waitFor();
          System.out.println("Video converted: " + mp4Path);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

    if (context != null)
      context.close();
    if (browser != null)
      browser.close();
    if (playwright != null)
      playwright.close();
  }
}
