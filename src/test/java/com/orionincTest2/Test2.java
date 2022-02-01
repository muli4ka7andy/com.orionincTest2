package com.orionincTest2;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Test2 {
    private static WebDriver driver;
    private static Actions actions;


    @BeforeClass
    public void initialize() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\driver\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        this.driver = new ChromeDriver(chromeOptions);
    }

    @Test
    public void Test2() {
        driver.get("https://www.google.com");
        click(By.xpath("//*[contains(text(), 'Sutinku')]"));

        sendKeys(By.name("q"), "Orion Innovation" + Keys.ENTER);
        click(By.xpath("//*[contains(text(), 'https://www.orioninc.com')]"));

        clickId(By.id("hs-eu-confirmation-button"));
        actions = new Actions(driver);
        WebElement companyLink = driver.findElement(By.xpath("//*[contains(text(), 'Company')]"));
        actions.moveToElement(companyLink).perform();


        List<WebElement> typeElement = driver.findElements(By.xpath("//div[@class='menu-panel dropdown-menu']"));
        for (WebElement element : typeElement) {
            String displayProp = element.getCssValue("display");

            if (displayProp.equals("none")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';", element);
            }
        }
        click(By.xpath("//*[contains(text(), 'Careers')]"));

        clickLinkPath(By.linkText("Europe"));
        click(By.xpath("//li[@data-value='lithuania-vilnius']"));
        click(By.xpath("//div[@id='teaser-9425']/div[2]/a"));
        click(By.xpath("//*[contains(text(), 'Apply Now')]"));
        // fill the form
        sendKeys(By.name("input_2"), "test");
        sendKeys(By.name("input_3"), "test");
        sendKeys(By.name("input_4"), "test@test.com");
        sendKeys(By.name("input_5"), "+3700934654");
        click(By.xpath("//b[@class='button']"));
        click(By.xpath("//li[contains(text(), 'Lithuania')]"));
        sendKeys(By.name("input_7"), "test");
        sendKeys(By.name("input_8"), "test");
        sendKeys(By.name("input_9"), "00000");
        sendKeys(By.name("input_11"), "test");
        String resumePath = "C:\\Resume\\Resume.pdf";
        driver.findElement(By.name("input_12")).sendKeys(resumePath);
        clickId(By.id("label_7_13_1"));

        clickId(By.id("gform_submit_button_7"));


    }

    private void click(By xpath) {
        getWait()
                .until(ExpectedConditions.elementToBeClickable(xpath))
                .click();
    }

    private void clickLinkPath(By linkPath) {
        getWait()
                .until(ExpectedConditions.elementToBeClickable(linkPath))
                .click();
    }

    private void clickId(By id) {
        getWait()
                .until(ExpectedConditions.elementToBeClickable(id))
                .click();
    }

    private void sendKeys(By name, String text) {
        getWait()
                .until(ExpectedConditions.visibilityOfElementLocated(name))
                .sendKeys(text);
    }


    private Wait<WebDriver> getWait() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
    }

    @AfterClass
    public void close() {
        driver.quit();
    }
}
