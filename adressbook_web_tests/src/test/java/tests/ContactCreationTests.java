package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactCreationTests {
  private WebDriver driver;

  @BeforeEach
  public void setUp() {
    driver = new FirefoxDriver();
  }
  @AfterEach
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void test() {
    driver.findElement(By.linkText("add new")).click();
    driver.findElement(By.name("firstname")).click();
    driver.findElement(By.name("firstname")).sendKeys("Valeriya");
    driver.findElement(By.name("lastname")).click();
    driver.findElement(By.name("lastname")).sendKeys("Pobutova");
    driver.findElement(By.name("address")).click();
    driver.findElement(By.name("address")).sendKeys("Moscow");
    driver.findElement(By.name("mobile")).click();
    driver.findElement(By.name("mobile")).sendKeys("89998888888");
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).sendKeys("trety@mail.ru");
    driver.findElement(By.cssSelector("input:nth-child(75)")).click();
  }
}
