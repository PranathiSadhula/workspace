package jira.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(features = "src/test/java/jira/feature", glue="jira.steps")
public class RunTest extends AbstractTestNGCucumberTests {
}