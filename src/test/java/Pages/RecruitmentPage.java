package Pages;

import Utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RecruitmentPage extends CommonMethods {
    @FindBy(className = "ui-datepicker-trigger")
    public WebElement dateOfApplicationDatePickerTriggerFromDate;
    @FindBy(className = "ui-datepicker-div")
    public WebElement dateOfApplicationDatePicker;
    @FindBy(xpath = "//select[@class='ui-datepicker-month']")
    public WebElement datePickerMonthFromDate;
    @FindBy(xpath = "//select[@class='ui-datepicker-year']")
    public WebElement datePickerYearFromDate;
    @FindBy(xpath = "//table[@class='ui-datepicker-calendar']")

    public WebElement datePickerTableFromDate;
    @FindBy(xpath = "//input[@id='candidateSearch_fromDate']")
    public WebElement dateOfApplicationDatePickerTextFieldFromDate;
    @FindBy(xpath = "//input[@id='candidateSearch_toDate']/following-sibling::img[@class='ui-datepicker-trigger']")
    public WebElement dateOfApplicationDatePickerTriggerToDate;
    @FindBy(id = "candidateSearch_toDate")
    public WebElement dateOfApplicationDatePickerTextFieldToDate;
    @FindBy(className="ui-datepicker-month")
    public WebElement datePickerMonthToDate;
    @FindBy(className="ui-datepicker-year")
    public WebElement datePickerYearToDate;

    public RecruitmentPage(){
        PageFactory.initElements(driver,this);
    }
}
