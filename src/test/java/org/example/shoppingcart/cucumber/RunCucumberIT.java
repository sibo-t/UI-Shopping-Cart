package org.example.shoppingcart.cucumber;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue="org/example/shoppingcart/steps",
        tags="@shopping-cart"
)

public class RunCucumberIT {

}