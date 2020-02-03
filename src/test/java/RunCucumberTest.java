import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import steps.BaseTest;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"/home/anton/Загрузки/cucumberAllureHomeWork/src/main/resources"}
        ,glue = {"steps" })
public class RunCucumberTest {

}