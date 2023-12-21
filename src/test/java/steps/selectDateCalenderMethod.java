package steps;

import io.cucumber.datatable.DataTable;
import Utils.CommonMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;


public class selectDateCalenderMethod extends CommonMethods {

    @Given("user clicks on Recruitment button")
    public void user_clicks_on_recruitment_button() {
         click(dashboardPage.recruitmentButton);
    }
    @When("user enters month, year and day")
    public void user_enters_month_year_and_day(DataTable dataTable) {
        List<List<String>> data = dataTable.asLists(String.class);
        for (int i=0;i<data.size();i++) {
            List<String> rowOfDataTable = data.get(i);
            String elementFromDataTableRowMonth = rowOfDataTable.get(0);
            String elementFromDataTableRowYear = rowOfDataTable.get(1);
            String elementFromDataTableRowDay = rowOfDataTable.get(2);
            String elementFromDataTableRowExpectedDate = rowOfDataTable.get(3);
            if(i==0) {
                click(recruitmentPage.dateOfApplicationDatePickerTriggerFromDate);
                selectFromCalenderNoPagination(recruitmentPage.datePickerMonthFromDate, recruitmentPage.datePickerYearFromDate, recruitmentPage.datePickerTableFromDate, elementFromDataTableRowMonth, elementFromDataTableRowYear, elementFromDataTableRowDay);
                String actualDate = recruitmentPage.dateOfApplicationDatePickerTextFieldFromDate.getAttribute("value");
                Assert.assertEquals(elementFromDataTableRowExpectedDate, actualDate);
            }else{
                click(recruitmentPage.dateOfApplicationDatePickerTriggerToDate);
                selectFromCalenderNoPagination(recruitmentPage.datePickerMonthToDate, recruitmentPage.datePickerYearToDate, recruitmentPage.datePickerTableFromDate, elementFromDataTableRowMonth, elementFromDataTableRowYear, elementFromDataTableRowDay);
                String actualDate = recruitmentPage.dateOfApplicationDatePickerTextFieldToDate.getAttribute("value");
                Assert.assertEquals(elementFromDataTableRowExpectedDate, actualDate);
            }
        }

    }
}
