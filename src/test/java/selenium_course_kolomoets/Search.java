package selenium_course_kolomoets;

import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.testng.*;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;



public class Search extends TestBase {

  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();


  @Test
  public void correctSearch() throws Exception {
	  List<WebElement> elementsBefor = driver.findElements(By.cssSelector(".movie_cover"));
	  System.out.println();
	  
	  
	  driver.findElement(By.id("q")).clear();
	  driver.findElement(By.id("q")).sendKeys("test" + Keys.ENTER);
	  
	  WebDriverWait wait = new WebDriverWait(driver, 20);
//	  поскольку список фильмов должен скрыться, пытаемся дождаться пока появится новый список:
//	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".movie_cover"))); //но при этом условии не успевает подгрузиться новый список фильмов
	  
	  wait.until(ExpectedConditions.stalenessOf(elementsBefor.get(0))); // при этом условии результат правильный, но как-то сильно долго "думает", т.е. наверное работает неправильно/оптимально)
	  
	  List<WebElement> elementsAfter = driver.findElements(By.cssSelector(".movie_cover"));

	  System.out.println("Всего фильмов: " + elementsBefor.size() +", соответствуют критерию поиска: " + elementsAfter.size());
  }
  
  @Test
  public void incorrectSearch() throws InterruptedException{
	  driver.findElement(By.id("q")).clear();
	  driver.findElement(By.id("q")).sendKeys("[=dfg232ttth]{];';kkgd32w9502" + Keys.ENTER);
	  Thread.sleep(200); // и тут не совсем понятно. Если не указывать Thread.sleep, то он находит старый список, если указывать - ждёт больше, чем указано в sleep
	  if (isElementPresent(By.cssSelector(".movie_cover"))){
		  List<WebElement> afterIncorrctSearch = driver.findElements(By.cssSelector(".movie_cover"));
		  System.out.println("По некорректному запросу найдено " + afterIncorrctSearch.size());
	  }
	  else System.out.println("По некорректному запросу фильмов не найдено, всё Ок");
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
