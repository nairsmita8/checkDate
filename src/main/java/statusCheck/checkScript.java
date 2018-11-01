package statusCheck;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class checkScript {
	
	@Test
	public static void checkDate() throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "C://Users//nairs//chromedriver_win32//chromedriver.exe");
	WebDriver driver =  new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	driver.get("https://www.uscis.gov/");
	
	driver.findElement(By.xpath("//a[text()='CHECK PROCESSING TIMES']")).click();
	
	WebElement form = driver.findElement(By.xpath("//select[@id='selectForm']"));
	Select selectForm = new Select(form);
	form.click();
	selectForm.selectByVisibleText("I-129 | Petition for a Nonimmigrant Worker");
	form.click();
	
	Thread.sleep(100);
	Select selectOffice = new Select(driver.findElement(By.xpath("//select[@id='officeOrCenter']")));
	driver.findElement(By.xpath("//select[@id='officeOrCenter']")).click();
	driver.findElement(By.xpath("//select[@id='officeOrCenter']")).sendKeys("cal");
	driver.findElement(By.xpath("//select[@id='officeOrCenter']")).sendKeys(Keys.ENTER);
	
	
	driver.findElement(By.xpath("//a[@title = 'Get processing time']")).click();
	
	Thread.sleep(4000);
	
	String name = driver.findElement(By.xpath("//div[text()='H-1B - Specialty occupation - Change of status in the U.S.']")).getText();
	
	String date = driver.findElement(By.xpath("//div[text()='H-1B - Specialty occupation - Change of status in the U.S.']/following-sibling::div")).getText();
	
	Assert.assertEquals("H-1B - Specialty occupation - Change of status in the U.S.", name,"The name is "+name);
	Assert.assertEquals("February 23, 2018", date,"Date is "+date);
	
	driver.quit();
	}
}
