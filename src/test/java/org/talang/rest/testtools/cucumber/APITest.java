package org.talang.rest.testtools.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:restapi.integration",
        // tags = {"@restApiIntegration", "~@ignore"},
        //glue = {"org.talang.rest.testtools.context","org.talang.rest.testtools.stepdefs"},
        format = {"html:target/cucumber-report/restApiIntegration",
                "json:target/cucumber-report/restApiIntegration.json"})
public class APITest {
}
