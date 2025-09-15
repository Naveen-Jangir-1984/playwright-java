package tests.api;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.JsonDataManager;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PostsTest {

  @Test
  @Description("Fetch Posts")
  @Severity(SeverityLevel.CRITICAL)
  public void testPosts() {
    RestAssured.useRelaxedHTTPSValidation();
    String baseUrl = JsonDataManager.getBaseUrl("api");
    Response response = RestAssured
        .given()
        .contentType("application/json")
        .when()
        .get(baseUrl)
        .then()
        .statusCode(200) // Expect 200 OK
        .extract().response();

    Assert.assertEquals(response.statusCode(), 200);
  }
}
