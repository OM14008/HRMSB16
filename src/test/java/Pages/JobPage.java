package Pages;

import Utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JobPage extends CommonMethods {

    @FindBy(id="btnAdd")
    public WebElement addButton;
    @FindBy(id="jobTitle_jobTitle")
    public WebElement jobTitleF;
    @FindBy(id="jobTitle_jobDescription")
    public WebElement jobDescF;
    @FindBy(id="jobTitle_note")
    public WebElement jobNoteF;
    @FindBy(id="btnSave")
    public WebElement jobSvBtn;
    public JobPage(){
        PageFactory.initElements(driver,this);
    }
}
