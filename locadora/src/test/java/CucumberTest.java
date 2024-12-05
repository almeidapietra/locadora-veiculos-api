package edu.adatech.locadoradeveiculos;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "edu.adatech.locadoradeveiculos.steps",
        plugin = {"pretty", "json:target/cucumber-report.json"}
)
public class CucumberTest {
}
