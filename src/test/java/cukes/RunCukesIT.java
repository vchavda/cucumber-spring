package cukes;

/////////////////////////Notes///////////////////////////////////////////////////////////
// to skip a scenario (and so it won't even appear on the cucumber report
// you can set it here in the runner so if it finds a scenarion with
// tag @skip it wil skip it (offcourse you can name this tag anything I called it skip)

/// Runner class name has to be ended with IT (see pom) otherwise won't run
/////////////////////////////////////////////////////////////////////////////////////////


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber.json"},
        features = "src/test/resources/features",
        glue = "cukes.steps",
        tags = "~@skip")
public class RunCukesIT {
}
