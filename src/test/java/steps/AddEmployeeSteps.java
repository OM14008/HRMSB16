package steps;

import Utils.CommonMethods;
import Utils.Constants;
import Utils.DBUtils;
import Utils.ExcelReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {
    String fnFirstName;
    String fnMiddleName;
    String fnLastName;
    String empId;
    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        //WebElement pimOption = driver.findElement(By.id("menu_pim_viewPimModule"));
        //pimOption.click();
        click(dashboardPage.pimOption);
    }
    @When("user clicks on add employee button")
    public void user_clicks_on_add_employee_button() {
       //WebElement addEmployeeBtn=driver.findElement(By.id("menu_pim_addEmployee"));
       click(dashboardPage.addEmployeeButton);
    }
    @When("user enters firstname and lastname")
    public void user_enters_firstname_and_lastname() {
        //WebElement firstNameTextField = driver.findElement(By.id("firstName"));
       // firstNameTextField.sendKeys("aendro");
        //WebElement lastNameTextField = driver.findElement(By.id("lastName"));
        //lastNameTextField.sendKeys("farewell");
        sendText("aendro",addEmployeePage.firstNameField);
        sendText("farewell",addEmployeePage.lastNameField);
    }
    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        //WebElement saveButton = driver.findElement(By.id("btnSave"));
       // saveButton.click();
        click(addEmployeePage.saveButton);
    }
    @Then("employee added successfully")
    public void employee_added_successfully() {
        System.out.println("Employee added successfully");
    }
    @When("user enters {string} and {string} and {string}")
    public void user_enters_and_and(String firstName, String middleName, String lastName) {
        this.fnFirstName=firstName;
        this.fnMiddleName =middleName;
        this.fnLastName =lastName;
        sendText(firstName, addEmployeePage.firstNameField);
        sendText(middleName, addEmployeePage.middleNameField);
        sendText(lastName, addEmployeePage.lastNameField);
        empId=addEmployeePage.employeeIdField.getAttribute("value");
    }
    @When("user enters {string} and {string} and {string} in data driven format")
    public void user_enters_and_and_in_data_driven_format(String firstName, String middleName, String lastName) {
        sendText(firstName, addEmployeePage.firstNameField);
        sendText(middleName, addEmployeePage.middleNameField);
        sendText(lastName, addEmployeePage.lastNameField);
    }
    @When("user enters firstName and middleName and lastName and verify employee has added")
    public void user_enters_firstName_and_middleName_and_lastName_and_verify_employee_has_added
            (io.cucumber.datatable.DataTable dataTable) {
        //we need list of maps to get multiple values from datatable which is coming
        // from feature file
        List<Map<String, String>> employeeNames = dataTable.asMaps();

        for (Map<String, String> employee:employeeNames
        ) {
            //getting the values against the key in map
            String firstNameValue = employee.get("firstName");
            String middleNameValue = employee.get("middleName");
            String lastNameValue = employee.get("lastName");

            //filling the name in the fields
            sendText(firstNameValue, addEmployeePage.firstNameField);
            sendText(middleNameValue, addEmployeePage.middleNameField);
            sendText(lastNameValue, addEmployeePage.lastNameField);
            click(addEmployeePage.saveButton);
            //after adding one employee, we will add another employee
            //for this, we are clicking on add employee button in the loop itself
            click(dashboardPage.addEmployeeButton);
        }

    }
    @When("user adds multiple employees using excel from {string} and verify it")
    public void user_adds_multiple_employees_using_excel_from_and_verify_it(String sheetName) throws InterruptedException, IOException {
        List<Map<String,String>> newEmployees = ExcelReader.read(sheetName, Constants.EXCEL_READER_PATH);
        Iterator<Map<String,String>> itr = newEmployees.iterator();
        while(itr.hasNext()){
            Map<String,String> mapNewEmployee = itr.next();
            System.out.println(mapNewEmployee);
            sendText(mapNewEmployee.get("FirstName"),addEmployeePage.firstNameField);
            sendText(mapNewEmployee.get("MiddleName"),addEmployeePage.middleNameField);
            sendText(mapNewEmployee.get("LastName"),addEmployeePage.lastNameField);
            sendText(mapNewEmployee.get("Photograph"),addEmployeePage.photograph);

            if( ! addEmployeePage.checkBoxLocator.isSelected()){
                click(addEmployeePage.checkBoxLocator);
            }
            sendText(mapNewEmployee.get("UserName"),addEmployeePage.usernameTextFieldBox);
            sendText(mapNewEmployee.get("Password"),addEmployeePage.passwordTextFieldBox);
            sendText(mapNewEmployee.get("ConfirmPassword"),addEmployeePage.confirmPasswordBox);
            //when we add employee system generates unique id and we save it
            String empIdValue = addEmployeePage.employeeIdField.getAttribute("value");
            click(addEmployeePage.saveButton);
            Thread.sleep(5000);
            //verifying employee has been added
            click(dashboardPage.empListOption);
            sendText(empIdValue,employeeSearchPage.idTextField);
            click(employeeSearchPage.searchButton);
            //print the value from the table raw
            List<WebElement> rowData = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));
            for(int i=0;i<rowData.size();i++){
                System.out.println("I'm inside the loop");
                //it will return all the data one by one from the row
                String rowText = rowData.get(i).getText();
                System.out.println(rowText);
                String expectedData = empIdValue+" "+mapNewEmployee.get("FirstName")+" "+mapNewEmployee.get("MiddleName")
                        +" "+mapNewEmployee.get("LastName");
                Assert.assertEquals(rowText,expectedData);
                //OR
                //Assert.assertTrue(expectedData,rowText);
            }
            click(dashboardPage.addEmployeeButton);

        }
    }


    @Then("verify employee is stored in database")
    public void verifyEmployeeIsStoredInDatabase() {
        String query = "select emp_firstname, emp_middle_name, emp_lastname from hs_hr_employees where employee_id = " + empId + ";";
        List<Map<String,String>> mapList=DBUtils.fetch(query);
        Map<String,String> firstRow=mapList.get(0);
        String dbFirstName=firstRow.get("emp_firstname");
        String dbMiddleName=firstRow.get("emp_middle_name");
        String dbLastName=firstRow.get("emp_lastname");
        Assert.assertEquals("FirstName from frontend does not match the FirstName from dataBase",fnFirstName,dbFirstName);
        Assert.assertEquals("MiddleName from frontend does not match the MiddleName from dataBase", fnMiddleName,dbMiddleName);
        Assert.assertEquals("LastName from frontend does not match the LastName from dataBase", fnLastName,dbLastName);

    }
}

