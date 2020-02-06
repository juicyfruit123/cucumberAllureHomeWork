import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/login.feature"},
        plugin = {"io.qameta.allure.cucumber3jvm.AllureCucumber3Jvm"}
        , glue = {"steps"})
public class RunCucumberTest {

}