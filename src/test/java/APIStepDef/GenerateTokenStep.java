package APIStepDef;

import Utils.APIConstants;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GenerateTokenStep {

    String baseURI = RestAssured.baseURI = APIConstants.baseURI;
    public static String token;

    @Given("a JWT is generated")
    public void a_jwt_is_generated() {
        //here we write the code to generate JWT token
        RequestSpecification request = given().
                header("Content-Type","application/json").
                body("{\n" +
                        "  \"email\": \"oleg@test.com\",\n" +
                        "  \"password\": \"test@2846\"\n" +
                        "}");
        Response response = request.when().post("/generateToken.php");
        token = "Bearer "+ response.jsonPath().getString("token");
        System.out.println(token);
    }
}
