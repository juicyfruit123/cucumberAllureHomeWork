import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"/home/anton/Загрузки/cucumberAllureHomeWork/src/main/resources"}
        , glue = {"steps"})
public class RunCucumberTest {

}