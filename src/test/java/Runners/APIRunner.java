package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //features we use to provide the path of all the feature files
        features = "src/test/resources/features/",
        //glue keyword we use to provide the package where step definitions are available
        glue = "APIStepDef",
        //it stops actual execution, it quickly scans all the steps and will provide the missing step definition when it's set to true
        dryRun = false,
        //tag the scenarios, and it will execute the code you tag with regression,smoke,sprint1 etc
        tags = " @dynamic",
        //it means sometimes the console output for cucumber test is having same
        //irrelevant information, when you set it to true, it removes all that
        //irrelevant information from the console and will give you simple output
        monochrome = true,
        //pretty is used to print all the steps in the console
        plugin = {"pretty"}
)


public class APIRunner {
}
