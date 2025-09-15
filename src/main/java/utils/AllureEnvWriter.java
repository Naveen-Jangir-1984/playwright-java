package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class AllureEnvWriter {
  public static void writeEnv(String env, String browser) {
    try {
      Properties props = new Properties();
      props.setProperty("Environment", env);
      props.setProperty("Browser", browser);

      // Save under allure-results (important)
      File allureResultsDir = new File("target/allure-results");
      if (!allureResultsDir.exists()) {
        allureResultsDir.mkdirs();
      }

      FileOutputStream out = new FileOutputStream(new File(allureResultsDir, "environment.properties"));
      props.store(out, "Allure Environment Properties");
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
