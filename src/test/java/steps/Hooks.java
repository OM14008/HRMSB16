package steps;

import Utils.CommonMethods;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends CommonMethods {
    @Before
    public void start() {
        openBrowserAndNavigateToURL();
    }

    @After
    public void end(Scenario scenario) {
        // (If) block is for the logInAndStayLoggedIn runner class, so I don't have to log in manually all the
        //time, it's not quiting the driver after Login.feature being executed
        if (!scenario.getSourceTagNames().contains("@loginAndStayLoggedIn")) {
            //we need this variable because my takeScreenshot method returns array of bytes
            byte[] pic;
            //here we are going to capture the screenshot and attach it to the report
            if (scenario.isFailed()) {
                pic = takeScreenshot("failed/" + scenario.getName());
            } else {
                pic = takeScreenshot("passed/" + scenario.getName());
            }
            //attach the screenshot to the report
            scenario.attach(pic, "image/png", scenario.getName());
            closeBrowser();
        }
    }
}
