package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin={"pretty","json:target/cucumberreports.json","junit:target/cucumber.xml"},
glue="stepDefinitions",
features="src\\test\\resources\\features",
tags={"@Regression"},
monochrome=false)
public class RunTest {

}