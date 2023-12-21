package steps;

import Pages.LoginPage;
import Utils.CommonMethods;
import Utils.ConfigReader;
import Utils.Log;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Command;

import java.time.Duration;

public class LoginSteps extends CommonMethods {

    @Given("user is navigated to HRMS application")
    public void user_is_navigated_to_hrms_application() {
        openBrowserAndNavigateToURL();
    }
    @When("user enters valid admin username and password")
    public void user_enters_valid_admin_username_and_password() {
        //WebElement userNameField=driver.findElement(By.id("txtUsername"));
        //WebElement passwordField=driver.findElement(By.id("txtPassword"));
        // userNameField.sendKeys(ConfigReader.getPropertyValue("username"));
        //passwordField.sendKeys(ConfigReader.getPropertyValue("password"));
        //LoginPage loginPage = new LoginPage();

        Log.startTestCase("the test case started");
        sendText(ConfigReader.getPropertyValue("username"),loginPage.userNameField);
        sendText(ConfigReader.getPropertyValue("password"),loginPage.passwordField);
    }
    @When("user clicks on login button")
    public void user_clicks_on_login_button() {

        //WebElement loginButton = driver.findElement(By.name("Submit"));
        //loginButton.click();
        //LoginPage loginPage = new LoginPage();
        click(loginPage.loginButton);
    }
    @Then("user is successfully logged in the application")
    public void user_is_successfully_logged_in_the_application() {
        System.out.println("My test case is passed");
    }


    @When("user enters ess username and password")
    public void user_enters_ess_username_and_password() {
        // WebElement userNameField=driver.findElement(By.id("txtUsername"));
        //WebElement passwordField=driver.findElement(By.id("txtPassword"));
        //userNameField.sendKeys("dalima123");
        //passwordField.sendKeys("Hum@nhrm123");
        //LoginPage loginPage = new LoginPage();
        sendText(ConfigReader.getPropertyValue("essUsername"),loginPage.userNameField);
        sendText(ConfigReader.getPropertyValue("essPassword"),loginPage.passwordField);
    }
    @When("user enters invalid admin username and password")
    public void user_enters_invalid_admin_username_and_password() {
        //WebElement userNameField=driver.findElement(By.id("txtUsername"));
        //WebElement passwordField=driver.findElement(By.id("txtPassword"));
        //LoginPage loginPage = new LoginPage();
        sendText(ConfigReader.getPropertyValue("invalidUsername"),loginPage.userNameField);
        sendText(ConfigReader.getPropertyValue("invalidPassword"),loginPage.passwordField);
    }
    @Then("error message is displayed")
    public void error_message_is_displayed() {
        System.out.println("Error message is displayed");
    }
    @When("user enters {string} and {string} and verifying the {string} for the combinations")
    public void user_enters_and_and_verifying_the_for_the_combinations
            (String username, String password, String errorMessageExpected) {
        sendText(username, loginPage.userNameField);
        sendText(password, loginPage.passwordField);
        click(loginPage.loginButton);
        //fetching the error message from the web element
        String errorMessageActual = loginPage.errorMessageField.getText();
        //error message coming from feature file too which we can compare
        Assert.assertEquals("value does not match", errorMessageExpected, errorMessageActual);

    }








}
