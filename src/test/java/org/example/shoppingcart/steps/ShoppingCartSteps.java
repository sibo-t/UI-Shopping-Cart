package org.example.shoppingcart.steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.shoppingcart.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertTrue;

public class ShoppingCartSteps {
    private final HomePage homePage;
    WebDriver driver;

    public ShoppingCartSteps() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://react-shopping-cart-67954.firebaseapp.com/");
        this.homePage = new HomePage(driver);
    }

    @When("the user filter products by size {string}")
    public void theUserFilterProductsBySize(String size) {
        homePage.sortProductsBySize(size);
    }

    @Then("the number of products found are {string}")
    public void theNumberOfProductsFoundAre(String noOfProducts) {
        assertTrue(homePage.actualNumberOfProductsIs(noOfProducts));
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

    @When("the user adds the product {string}")
    public void theUserAddsTheProduct(String productName) {
        //hello
        homePage.addProductsToCart(productName);
    }

    @Then("the product {string} is present in the cart")
    public void theProductIsPresentInTheCart(String productName) {
        assertTrue(homePage.isProductInCart(productName));
    }
}