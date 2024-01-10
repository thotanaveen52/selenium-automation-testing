package demo1;

import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestScenario3 {
	public static String hubURL = "https://hub.lambdatest.com/wd/hub";
	RemoteWebDriver driver;
	
	@BeforeClass
	public void setUp() throws Exception {
		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("121.0");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "thotanaveen52");
		ltOptions.put("accessKey", "y03Bc0qx7voPnjSiJh1LHpoJ6ziAvZQxTukchMW20R3NMzNNsc");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("build", "SeleniumJavaAssignment");
		ltOptions.put("project", "SeleniumDemo");
		ltOptions.put("name", "TestScenario3");
		ltOptions.put("selenium_version", "4.0.0");
		ltOptions.put("w3c", true);
		browserOptions.setCapability("LT:Options", ltOptions);
		driver = new RemoteWebDriver(new URL(hubURL), browserOptions);
		
	}
	@Test
	public void formSubmit () {
		driver.get("https://www.lambdatest.com/selenium-playground/");
		driver.findElement(By.linkText("Input Form Submit")).click();
		WebElement submit=driver.findElement(By.xpath("//button[contains(text(),\"Submit\")]"));
		submit.click();
		WebElement name= driver.findElement(By.id("name"));
		String actualErrorMsg = name.getAttribute("validationMessage");
		String expectedErrorMsg = "Please fill out this field.";
		Assert.assertEquals(actualErrorMsg,expectedErrorMsg);
		name.sendKeys("Naveen");
		driver.findElement(By.id("inputEmail4")).sendKeys("naveen@gmail.com");
		driver.findElement(By.name("password")).sendKeys("naveen123");
		driver.findElement(By.xpath("//input[@id=\"company\"]")).sendKeys("ABC");
		driver.findElement(By.xpath("//input[@id=\"websitename\"]")).sendKeys("abc.com");
		WebElement country = driver.findElement(By.name("country"));
		Select sel = new Select(country);
		sel.selectByVisibleText("United States");
		driver.findElement(By.id("inputCity")).sendKeys("Madhapur");
		driver.findElement(By.id("inputAddress1")).sendKeys("Dr No: 1-11, beach road");
		driver.findElement(By.id("inputAddress2")).sendKeys("Ayyappa Society");
		driver.findElement(By.id("inputState")).sendKeys("Hyd");
		driver.findElement(By.xpath("//input[@placeholder=\"Zip code\"]")).sendKeys("432677");
		submit.click();
		String validationMsg = driver.findElement(By.xpath("//p[@class=\"success-msg hidden\"]")).getText();
		String expectedMsg = "Thanks for contacting us, we will get back to you shortly.";
		Assert.assertEquals(validationMsg, expectedMsg);
		
		}
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
