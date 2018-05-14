import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class LoginTest extends BaseTest {

    String endpoint;
    @BeforeClass
    public void initialize() {
        endpoint = "/api/login";
    }

    @Test
    public void verifyLogin(){
        String requestBody="{\"email\":\"peter@klaven\",\"password\":\"cityslicka\"}";
        given().spec(requestSpecs).contentType("application/json").body(requestBody).
                when().post(endpoint).
                then().spec(responseSpecs).
                assertThat().body(matchesJsonSchemaInClasspath("testdata/login_schema.json"));
    }
}
