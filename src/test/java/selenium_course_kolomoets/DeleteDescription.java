package selenium_course_kolomoets;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.testng.*;
import org.testng.annotations.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class DeleteDescription extends TestBase{
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Test
  public void delDescription() throws Exception {

    driver.findElement(By.cssSelector(".movie_cover")).click();
    driver.findElement(By.xpath(".//*[@id='content']/section/nav/ul/li[3]/div/div/a/img")).click();
    
    //удаляем содержимое всех полей, в том числе и обязательных:
    cleanOut(driver.findElement(By.name("name")));
    cleanOut(driver.findElement(By.name("aka")));
    cleanOut(driver.findElement(By.name("year")));
    cleanOut(driver.findElement(By.name("duration")));
    cleanOut(driver.findElement(By.name("rating")));
    cleanOut(driver.findElement(By.name("plotoutline")));
    cleanOut(driver.findElement(By.name("plots")));
    cleanOut(driver.findElement(By.id("text_languages_0")));
    cleanOut(driver.findElement(By.name("subtitles")));
    cleanOut(driver.findElement(By.name("country")));    

    //пытаемся отправить форму с незаполненными обязательными полями. Должны появиться подсказки возле обяз. полей:
    driver.findElement(By.id("submit")).click();
    if (!isElementPresent(By.xpath(".//*[@id='updateform']/table/tbody/tr[4]/td[2]/label")) && !isElementPresent(By.xpath(".//*[@id='updateform']/table/tbody/tr[2]/td[2]/label")))
    {
    	System.out.println("Element(s) not found");
    	String e = null;
		throw new org.openqa.selenium.NoSuchElementException(e);   	
    }

    driver.findElement(By.name("name")).sendKeys(Keys.CONTROL + "z");
    driver.findElement(By.name("year")).sendKeys(Keys.CONTROL + "z");
    driver.findElement(By.id("submit")).click();
    driver.findElement(By.cssSelector("h1")).click();
  }
  
  private void cleanOut(WebElement element) throws InterruptedException {
	  element.sendKeys(Keys.CONTROL + "a");
	  Thread.sleep(200);
	  element.sendKeys(Keys.DELETE);
	
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
