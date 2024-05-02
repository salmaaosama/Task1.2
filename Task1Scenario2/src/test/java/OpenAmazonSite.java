import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class OpenAmazonSite {

    WebDriver driver;

    @BeforeTest
    public void openBrowser() throws InterruptedException {
        driver = new ChromeDriver();
        //Set Browser resolution
        driver.manage().window().setSize(new Dimension(1024, 768));
        //Open amazon site
        driver.navigate().to("https://www.amazon.com/");
        Thread.sleep(3000);
    }


    @Test
    public void ValidData() throws InterruptedException {
        //Open amazon site
        driver.navigate().to("https://www.amazon.com/");
        //Open today&#39;s deals
        driver.findElement(By.xpath("//*[@id=\"nav-xshop\"]/a[1]")).click();
        //from the left side filters select ;Headphones and grocery
        driver.findElement(By.className("CheckboxFilter-module__checkboxLabel_21MUk0e7QdlZKH5Xcwap-F")).click();
        driver.findElement(By.xpath("//*[@id=\"grid-main-container\"]/div[2]/span[3]/ul/li[27]/label/input")).click();
        driver.findElement(By.linkText("$10 to $25")).click();
        Thread.sleep(2000);
        //go to the fourth page then select any item and add it to the cart
        driver.findElement(By.xpath("//*[@id=\"dealsGridLinkAnchor\"]/div/div[4]/div/ul/li[3]")).click(); //page2
        driver.findElement(By.xpath("//*[@id=\"dealsGridLinkAnchor\"]/div/div[4]/div/ul/li[3]")).click(); //page3
        driver.findElement(By.xpath("//*[@id=\"dealsGridLinkAnchor\"]/div/div[4]/div/ul/li[3]")).click(); //page4
        driver.findElement(By.className("DealContent-module__truncate_sWbxETx42ZPStTc9jwySW")).click();
        driver.findElement(By.className("a-button-text")).click();
    }
    @Test
    public void InvalidData() throws InterruptedException {
        driver.navigate().to("https://www.amazon.com/");
        driver.findElement(By.xpath("//*[@id=\"nav-xshop\"]/a[1]")).click();

        try {
            driver.findElement(By.className("NonExistentClassName")).click();
            driver.findElement(By.className("AnotherNonExistentClassName")).click();
        } catch (NoSuchElementException e) {
            System.out.println("Filters not found, as expected.");
        }

        try {
            driver.findElement(By.className("NonExistentDiscountFilter")).click();
        } catch (NoSuchElementException e) {
            System.out.println("Discount filter not found, as expected.");
        }

        try {
            driver.findElement(By.xpath("IncorrectPaginationXPath")).click();
            driver.findElement(By.className("NonExistentItem")).click();
        } catch (NoSuchElementException e) {
            System.out.println("Pagination or item selection failed, as expected.");
        }
    }

}
