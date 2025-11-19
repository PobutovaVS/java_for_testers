package tests;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import tests.TestBase;

public class ContactRemovalTests extends TestBase {
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
  public void contactRemovalTests() {
    driver.get("http://localhost/addressbook/index.php");
    driver.manage().window().setSize(new Dimension(1644, 894));
    driver.findElement(By.name("user")).sendKeys("admin");
    driver.findElement(By.name("pass")).click();
    driver.findElement(By.name("pass")).sendKeys("secret");
    driver.findElement(By.cssSelector("body")).click();
    driver.findElement(By.cssSelector("input:nth-child(7)")).click();
    driver.findElement(By.id("4")).click();
    driver.findElement(By.name("delete")).click();
    driver.findElement(By.linkText("Logout")).click();
    driver.close();
  }
}
