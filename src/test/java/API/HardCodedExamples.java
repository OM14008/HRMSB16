package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {
    //baseURI = baseURL + endpoint
    //given - preparation
    //when - hitting the endpoint
    //base URI = base URL

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    // value of token should be same as postman
    public static String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2OTEwMTgxMTEsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY5MTA2MTMxMSwidXNlcklkIjoiNTYyMSJ9.fxqx9RxbuXP9xlAkYVSK8O8p0i3RYRZ2wFYnsNeqK7o";
    static String employee_id;
    static String emp_middle_name;

    //in this method we are going to create an employee
    //we need headers, body to prepare the request
    @Test
    public void acreateEmployee(){
        //preparing the request
        RequestSpecification request = given().header("Content-Type","application/json").
                header("Authorization", token).body("{\n" +
                        "  \"emp_firstname\": \"justin\",\n" +
                        "  \"emp_lastname\": \"azzuri\",\n" +
                        "  \"emp_middle_name\": \"ms\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2000-07-21\",\n" +
                        "  \"emp_status\": \"happy\",\n" +
                        "  \"emp_job_title\": \"QA\"\n" +
                        "}");
        //hitting the endpoint
        Response response = request.when().post("/createEmployee.php");
        //verifying the response
        response.then().assertThat().statusCode(201);
        //  System.out.println(response);
        //this method we use to print the response of API in console
        response.prettyPrint();
        //verify all the values and headers from response
        response.then().assertThat().body("Employee.emp_firstname",
                equalTo("justin"));
        response.then().assertThat().body("Employee.emp_middle_name",
                equalTo("ms"));
        response.then().assertThat().body("Message",
                equalTo("Employee Created"));
        response.then().assertThat().header("X-Powered-By","PHP/7.2.18");
        //it will return the employee id, and it will save it in the variable
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);
    }
    @Test
    public void bgetCreatedEmployee(){
        RequestSpecification request = given().header("Authorization",token)
                .queryParam("employee_id",employee_id);
        Response response = request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
        String tempEmpId = response.jsonPath().getString("employee.employee_id");
        Assert.assertEquals(employee_id,tempEmpId);
    }
    @Test
    public void cUpdateEmployee(){
        RequestSpecification request = given().header("Content-Type","application/json")
                .header("Authorization",token).
                body("{\n" +
                        "  \"employee_id\": \""+employee_id+"\",\n" +
                        "  \"emp_firstname\": \"Flakin\",\n" +
                        "  \"emp_lastname\": \"Born\",\n" +
                        "  \"emp_middle_name\": \"K\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"1985-07-29\",\n" +
                        "  \"emp_status\": \"deal\",\n" +
                        "  \"emp_job_title\": \"root\"\n" +
                        "}");
        Response response = request.when().put("/updateEmployee.php");
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("Message",equalTo("Employee record Updated"));
    }
    @Test
    public void dgetCreatedEmployee(){
        RequestSpecification request = given().header("Authorization",token)
                .queryParam("employee_id",employee_id);
        Response response = request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
       // String tempEmpId = response.jsonPath().getString("employee.employee_id");
        // Assert.assertEquals(employee_id,tempEmpId);
    }







}
