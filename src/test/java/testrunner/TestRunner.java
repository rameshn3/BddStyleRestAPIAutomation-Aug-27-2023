package testrunner;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(
    features = "C:\\eclipse-workspace\\BddStyleRestAPIAutomation-Aug-27-2023\\src\\test\\java\\Features",
    glue = {"step_def"},
    plugin={"pretty","html:target/cucumber-report.html", "json:json_output/cucumber.json", "junit:junit_xml/cucumber.xml"},
    dryRun=false, //mapping between feature & stepdefinition is proper or not
    monochrome=true,//display the console output in a proper readable format
    publish = true,
    stepNotifications=true,
    tags = "@smoke"		
    	)	
public class TestRunner {

}