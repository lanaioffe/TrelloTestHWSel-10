package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CurrentBoardPageTests extends TestBase {
    @BeforeMethod
    public void initTest(){
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
    }

    @Test
    public void createNewList() throws InterruptedException {
//        ----Open 'QA 4 Auto' board--------
        driver.findElement(By.xpath("//div[@class='board-tile-details is-badged']")).click();
        waitUntilElementIsClickable(By.cssSelector(".placeholder"),30);

        //-----Add a new list ------
        driver.findElement(By.cssSelector(".placeholder")).click();
        waitUntilElementIsVisible(By.cssSelector(".list-name-input"),10);
        driver.findElement(By.cssSelector(".list-name-input")).sendKeys("New List");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        waitUntilElementIsClickable(By.cssSelector("a.js-cancel-edit"),10);
    }

    @Test
    public void addFirstCardInNewList() throws InterruptedException {

//        ----Open 'QA 4 Auto' board--------
        driver.findElement(By.xpath("//div[@class='board-tile-details is-badged']")).click();
        waitUntilElementIsClickable(By.cssSelector(".placeholder"),30);

        //--------Get quantity of 'Add another card' buttons at the beginning----
        int quantityAddAnotherButtonBeg = driver.findElements(By.xpath("//span[@class= 'js-add-another-card']")).size();

        //-----Add a new list------
        driver.findElement(By.cssSelector(".placeholder")).click();
        waitUntilElementIsVisible(By.cssSelector(".list-name-input"),10);
        driver.findElement(By.cssSelector(".list-name-input")).sendKeys("New List");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        waitUntilElementIsClickable(By.cssSelector("a.js-cancel-edit"),10);

        //----- Get the last 'Add card' button----
        List<WebElement> listAddCardButtons = driver.findElements(By.xpath("//span[@class = 'js-add-a-card']"));
        int sizeLstAddCardButtons = listAddCardButtons.size();
        WebElement lastAddCardButton = listAddCardButtons.get(sizeLstAddCardButtons-1);
        //----Add a first card for any new list
        lastAddCardButton.click();
        waitUntilElementIsClickable(By.xpath("//input[@class='primary confirm mod-compact js-add-card']"),10);
        driver.findElement(By.xpath("//textarea[@placeholder='Enter a title for this card…']")).sendKeys("text");
        driver.findElement(By.xpath("//input[@class='primary confirm mod-compact js-add-card']")).click();
        waitUntilElementIsClickable(By.cssSelector("a.js-cancel"),10);
        driver.findElement(By.cssSelector("a.js-cancel")).click();

        //--------Get quantity of 'Add another card' buttons at the end----

        int quantityAddAnotherButtonEnd = driver.findElements(By.xpath("//span[@class= 'js-add-another-card']")).size();

        Assert.assertEquals(quantityAddAnotherButtonBeg+1, quantityAddAnotherButtonEnd);
    }

    @Test
    public void deleteList() {
    //----Open 'QA 4 Auto' board
    waitUntilElementIsVisible(By.xpath("//div[@title='QA4 Auto']/.."),20);
    driver.findElement(By.xpath("//div[@title='QA4 Auto']/..")).click();
    waitUntilElementIsClickable(By.cssSelector(".placeholder"),30);
    WebElement addButton = driver.findElement(By.cssSelector(".placeholder"));
    int quantityListBegining;
        if (addButton.getText().equals("Add a list")){
        addButton.click();
        waitUntilElementIsClickable(By.xpath("//input[@name = 'name']"),5);
        driver.findElement(By.xpath("//input[@name = 'name']")).sendKeys("New List");
        driver.findElement(By.xpath("//input[@type = 'submit']")).click();
        waitUntilElementIsClickable(By.xpath("//a[@class='icon-lg icon-close dark-hover js-cancel-edit']"),5);
        driver.findElement(By.xpath("//a[@class='icon-lg icon-close dark-hover js-cancel-edit']")).click();
        quantityListBegining=1;
    } else {
        waitUntilElementIsClickable(By.cssSelector(".js-open-list-menu"),10);
        quantityListBegining = driver.findElements(By.cssSelector(".js-open-list-menu")).size();
    }

    //---- delete list----------------
    waitUntilElementIsClickable(By.cssSelector(".js-open-list-menu"),10);
        driver.findElement(By.cssSelector(".js-open-list-menu")).click();
    waitUntilElementIsClickable(By.cssSelector(".js-close-list"),10);
        driver.findElement(By.cssSelector(".js-close-list")).click();
    int quantityListEnd = driver.findElements(By.cssSelector(".js-open-list-menu")).size();

    Assert.assertEquals(quantityListBegining,quantityListEnd+1, "quantityListBegining is not quantityListEnd+1");
    }

}