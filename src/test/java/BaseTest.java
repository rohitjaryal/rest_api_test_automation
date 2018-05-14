import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;


public class BaseTest {
    protected static ResponseSpecification responseSpecs = null;
    protected static RequestSpecification requestSpecs = null;

    @Parameters("domainName")
    @BeforeSuite
    public void startTestSession(String domainName){
        RestAssured.baseURI = domainName;
        ResponseSpecBuilder builder = new ResponseSpecBuilder();
        builder.expectStatusCode(200);
        builder.expectContentType(ContentType.JSON);
        responseSpecs = builder.build();
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(domainName);
        requestSpecs = requestSpecBuilder.build();
        requestSpecs.log().all();
    }


    @AfterSuite
    public void tearDownTestSession(){

    }
}
