package steps;

import io.cucumber.datatable.DataTable;
import Utils.CommonMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class selectCalenderTestMethodSteps extends CommonMethods {
    private String DataTableExpectedDateFromDate;
    private String DataTableExpectedDateToDate;

    @Given("user clicks on Recruitment button")
    public void user_clicks_on_recruitment_button() {
         click(dashboardPage.recruitmentButton);
    }
    @When("user enters From Date calender month year and day")
    public void userEntersFromDateCalenderMonthYearAndDay(DataTable dateInfo) {
        click(recruitmentPage.dateOfApplicationDatePickerTriggerFromDate);
        selectFromCalenderWithDropDowns(recruitmentPage.datePickerMonthFromDate,
                                        recruitmentPage.datePickerYearFromDate,
                                        recruitmentPage.datePickerTableFromDate,dateInfo);
        DataTableExpectedDateFromDate =dateInfo.cell(1,3);
    }
    @Then("date is entered into the From Date field")
    public void dateIsEnteredIntoTheFromDateField() {
        String actualDate = recruitmentPage.dateOfApplicationDatePickerTextFieldFromDate.getAttribute("value");
        Assert.assertEquals(actualDate, DataTableExpectedDateFromDate);
    }
    @When("user enters To Date calender month year and day")
    public void userEntersToDateCalenderMonthYearAndDay(DataTable dateInfo) {
        click(recruitmentPage.dateOfApplicationDatePickerTriggerToDate);
        selectFromCalenderWithDropDowns(recruitmentPage.datePickerMonthToDate,
                                        recruitmentPage.datePickerYearToDate,
                                        recruitmentPage.datePickerTableFromDate,dateInfo);
        DataTableExpectedDateToDate =dateInfo.cell(1,3);
    }
    @Then("date is entered into the To Date field")
    public void dateIsEnteredIntoTheToDateField() {
        String actualDate = recruitmentPage.dateOfApplicationDatePickerTextFieldToDate.getAttribute("value");
        Assert.assertEquals(actualDate, DataTableExpectedDateToDate);
    }
}
