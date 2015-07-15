package selenium_course_kolomoets;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class DeleteDescription extends TestBase{
//  private WebDriver driver;
//  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

//  @Before
//  public void setUp() throws Exception {
//    driver = new FirefoxDriver();
//    baseUrl = "http://localhost/";
//    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//  }

  @Test
  public void testUntitled3() throws Exception {
    driver.get(baseUrl + "/php4dvd/#!/sort/name%20asc/page/1/results/8/");
    driver.findElement(By.cssSelector("#movie_15 > div.movie_cover > div.nocover")).click();
    driver.findElement(By.cssSelector("img[alt=\"Edit\"]")).click();
    driver.findElement(By.name("aka")).clear();
    driver.findElement(By.name("aka")).sendKeys("");
    driver.findElement(By.name("year")).clear();
    driver.findElement(By.name("year")).sendKeys("");
    driver.findElement(By.name("duration")).clear();
    driver.findElement(By.name("duration")).sendKeys("");
    driver.findElement(By.name("rating")).clear();
    driver.findElement(By.name("rating")).sendKeys("");
    driver.findElement(By.name("plots")).clear();
    driver.findElement(By.name("plots")).sendKeys("");
    driver.findElement(By.id("text_languages_0")).clear();
    driver.findElement(By.id("text_languages_0")).sendKeys("");
    driver.findElement(By.name("subtitles")).clear();
    driver.findElement(By.name("subtitles")).sendKeys("");
    driver.findElement(By.name("country")).clear();
    driver.findElement(By.name("country")).sendKeys("");
    driver.findElement(By.id("submit")).click();
    driver.findElement(By.name("year")).clear();
    driver.findElement(By.name("year")).sendKeys("2011");
    driver.findElement(By.id("submit")).click();
    driver.findElement(By.cssSelector("h1")).click();
  }

  @After
  public void tearDown() {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
