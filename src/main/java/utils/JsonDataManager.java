package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonDataManager {
  private static JsonNode rootNode;

  private static String environment;

  public static void load(String filePath) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      rootNode = mapper.readTree(new File(filePath));
    } catch (IOException e) {
      throw new RuntimeException("Failed to load JSON file: " + filePath, e);
    }
  }

  public static String getBaseUrl(String env) {
    environment = env;
    return rootNode.path(env).path("baseUrl").asText();
  }

  public static String getUser(String user, String field) {
    return rootNode.path(environment).path("users").path(user).path(field).asText();
  }
}