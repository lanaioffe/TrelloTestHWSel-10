package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTests extends TestBase {


    @Test
    public void loginToTrelloPositive() throws InterruptedException {

        //----Login to trello----

        WebElement loginIcon = driver.findElement(By
                .xpath("//a[@class='btn btn-sm btn-link text-white']"));

        loginIcon.click();
        waitUntilElementIsClickable(By.id("login"),30);
        WebElement userField = driver.findElement(By.id("user"));
        userField.click();
        userField.clear();
        userField.sendKeys("lanaioffe@mail.ru");
        driver.findElement(By.id("login")).click();

        waitUntilElementIsClickable(By.id("login-submit"),30);
        driver.findElement(By.id("login-submit")).click();

        waitUntilElementIsClickable(By.id("password"),30);
        waitUntilElementIsClickable(By.id("login-submit"),30);
        driver.findElement(By.id("password")).sendKeys("Rainbow02");
        driver.findElement(By.id("login-submit")).click();

        waitUntilElementIsClickable(By
                .xpath("//button[@data-test-id='header-boards-menu-button']"),30);

        Assert.assertTrue(driver.findElement(By
                .xpath("//button[@data-test-id='header-boards-menu-button']"))
                .isDisplayed());
        Assert.assertTrue(driver.findElement(By
                .xpath("//h3[@class='boards-page-board-section-header-name']"))
                .getText().equals("Personal Boards"));
    }

    @Test
    public void loginIncorrectPassNegative() throws InterruptedException {
        //----Login to trello----
        WebElement loginIcon = driver.findElement(By
                .xpath("//a[@class='btn btn-sm btn-link text-white']"));
        loginIcon.click();
        waitUntilElementIsClickable(By.id("login"),30);
        WebElement userField = driver.findElement(By.id("user"));
        userField.click();
        userField.clear();
        userField.sendKeys("lanaioffe@mail.ru");
        driver.findElement(By.id("login")).click();
        waitUntilElementIsClickable(By.id("login-submit"),30);

        driver.findElement(By.id("login-submit")).click();

        waitUntilElementIsClickable(By.id("password"),30);
        waitUntilElementIsClickable(By.id("login-submit"),30);
        driver.findElement(By.id("password")).sendKeys("abcdefjh2");
        driver.findElement(By.id("login-submit")).click();

        waitUntilElementIsVisible(By
                .xpath("//div[@id = 'login-error']/span"),10);

        Assert.assertTrue(driver.findElement(By
                .xpath("//div[@id = 'login-error']/span")).getText()
                .contains("Incorrect email address and / or password."));
    }

    @Test
    public void loginIncorrectUserNameNegative() throws InterruptedException {
        //----Login to trello----
        driver.findElement(By.xpath("//a[@class='btn btn-sm btn-link text-white']")).click();
        waitUntilElementIsClickable(By.id("login"),30);
        WebElement userField  = driver.findElement(By.id("user"));
        userField.click();
        userField.clear();
        userField.click();
        userField.sendKeys("abc@mai.com");
        Thread.sleep(5000);
        driver.findElement(By.id("login")).click();
        Thread.sleep(5000);
        System.out.println("Message: " + driver.findElement(By.xpath("//p[@class='error-message']")).getText());

        Thread.sleep(3000);waitUntilElementIsVisible(By
                .xpath("//p[@class='error-message']"),10);

        Assert.assertTrue(driver.findElement(By
                .xpath("//p[@class='error-message']")).getText().contains("There isn't an account for this email"));

    }


}