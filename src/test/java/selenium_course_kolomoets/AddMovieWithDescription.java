package selenium_course_kolomoets;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.testng.*;
import org.testng.annotations.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class AddMovieWithDescription extends TestBase {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Test
  public void AddMovie() throws Exception {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.findElement(By.xpath(".//*[@id='content']/section/nav/ul/li[1]/div/div/a/img")).click();
	
	checkAndFillMandatoryFields();
	
	driver.findElement(By.name("aka")).clear();
    driver.findElement(By.name("aka")).sendKeys("Test7");
    driver.findElement(By.name("plotoutline")).clear();
    driver.findElement(By.name("plotoutline")).sendKeys("Horror");
    driver.findElement(By.id("text_languages_0")).clear();
    driver.findElement(By.id("text_languages_0")).sendKeys("EN");
    driver.findElement(By.name("subtitles")).clear();
    driver.findElement(By.name("subtitles")).sendKeys("RUS");
    driver.findElement(By.name("country")).clear();
    driver.findElement(By.name("country")).sendKeys("USA");
    driver.findElement(By.id("submit")).click();
    driver.findElement(By.cssSelector("h1")).click();
  }
  

  private void checkAndFillMandatoryFields() {
	  if (!isElementPresent(By.xpath(".//*[@id='updateform']/table/tbody/tr[4]/td[2]/label")) || !isElementPresent(By.xpath(".//*[@id='updateform']/table/tbody/tr[2]/td[2]/label"))){
	    	System.out.println("Element not found");
	    	String e = null;
			throw new NoSuchElementException(e);   	
	    }
	  driver.findElement(By.id("submit")).click();	// попытка отправить форму с незаполненными обязательными полями "name", "year"
	  driver.findElement(By.name("name")).sendKeys("test_7");
	  driver.findElement(By.id("submit")).click();	//попытка отправить форму с незаполненным обяз.полем "year"
	  driver.findElement(By.name("year")).sendKeys("2001");
	
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
