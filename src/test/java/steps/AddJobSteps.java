package steps;

import Utils.CommonMethods;
import Utils.DBUtils;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class AddJobSteps extends CommonMethods {

    String jobTit;
    String jobDesc;
    String jobN;

    @When("user clicks on the admin button")
    public void user_clicks_on_the_admin_button() {
        click(dashboardPage.adminButton);
    }
    @When("user clicks on the job")
    public void user_clicks_on_the_job() {
        click(dashboardPage.adminJobButton);
    }
    @When("user clicks on the job Title")
    public void user_clicks_on_the_job_title() {
        click(dashboardPage.adminJobJobTitleButton);
    }
    @When("user clicks on the add button")
    public void user_clicks_on_the_add_button() {
        click(jobPage.addButton);
    }
    @When("user enters job {string} title")
    public void user_enters_job_title(String jobTitle) {
        sendText(jobTitle,jobPage.jobTitleF);
        jobTit=jobTitle;
    }
    @When("user enters job description {string}")
    public void user_enters_job_description(String jobDescription) {
         sendText(jobDescription,jobPage.jobDescF);
         jobDesc=jobDescription;
    }
    @When("user enters job note {string}")
    public void user_enters_job_note(String jobNote) {
        sendText(jobNote,jobPage.jobNoteF);
        jobN=jobNote;
    }
    @When("user clicks on the save button")
    public void user_clicks_on_the_save_button() {
        click(jobPage.jobSvBtn);
    }
    @When("verify data is stored properly in database")
    public void verify_data_is_stored_properly_in_database() {
        String query = "select * from ohrm_job_title where job_title='" + jobTit + "' and job_description='" + jobDesc + "' and note='" + jobN + "';";
        List<Map<String, String>> data = DBUtils.fetch(query);
        Map<String, String> firstROw = data.get(0);
        String jTitleBE = firstROw.get("job_title");
        String jDescBE = firstROw.get("job_description");
        String jNoteBE = firstROw.get("note");
        Assert.assertEquals(jobTit, jTitleBE);
        Assert.assertEquals(jobDesc, jDescBE);
        Assert.assertEquals(jobN, jNoteBE);
    }
}
