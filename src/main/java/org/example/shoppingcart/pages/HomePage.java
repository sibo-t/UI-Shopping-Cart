package org.example.shoppingcart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    By byProductsFound = By.xpath("//main[@class='sc-ebmerl-4 iliWeY']/p");
    By byProductsInCart = By.xpath("//button[@title='remove product from cart']/following-sibling::img");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    By getBySize(String size){
        return By.xpath(String.format("//input[@value='%s']//parent::label/parent::div", size));
    }

    By getButtonByProduct(String productName){
        return By.xpath(String.format("//p[contains(text(), '%s')]/following-sibling::button", productName));
    }

    public void sortProductsBySize(String size){
        driver.findElement(getBySize(size)).click();
    }

    public void addProductsToCart(String productName){
        driver.findElement(getButtonByProduct(productName)).click();
    }

    public boolean isProductInCart(String productName){
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(45L));
            return   wait.until(textToBePresentInElementsLocated(byProductsInCart, productName));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean actualNumberOfProductsIs(String expectedNumberOfProducts){
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(45L));
            return   wait.until(ExpectedConditions.textToBePresentInElementLocated(byProductsFound, expectedNumberOfProducts));
        } catch (Exception e) {
            return false;
        }
    }

    public static ExpectedCondition<Boolean> textToBePresentInElementsLocated(final By locator, final String text) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    for (WebElement element:driver.findElements(locator)) {
                        String elementText = element.getText();
                        if(elementText.contains(text)){
                            return true;
                        }
                    }

                    return false;
                } catch (StaleElementReferenceException var3) {
                    return false;
                }
            }

            public String toString() {
                return String.format("text ('%s') to be present in elements found by %s", text, locator);
            }
        };
    }
}
