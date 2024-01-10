package demo1;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LaunchBrowser {
	public static String hubURL = "https://hub.lambdatest.com/wd/hub";
	
	RemoteWebDriver driver;
	
	@BeforeTest
	public void setUp() throws Exception {
		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("121.0");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "thotanaveen52");
		ltOptions.put("accessKey", "y03Bc0qx7voPnjSiJh1LHpoJ6ziAvZQxTukchMW20R3NMzNNsc");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("build", "demo1");
		ltOptions.put("project", "SeleniumDemo");
		ltOptions.put("name", "LaunchBrowser");
		ltOptions.put("selenium_version", "4.0.0");
		ltOptions.put("w3c", true);
		browserOptions.setCapability("LT:Options", ltOptions);
		driver = new RemoteWebDriver(new URL(hubURL), browserOptions);
	}

	@Test
	public void dropDown() {
		driver.get("https://demo.automationtesting.in/Register.html");
		String title=driver.getTitle();
		System.out.println("Website title is :"+title);
		String expectedURL="https://demo.automationtesting.in/Register.html";
		String actualURL = driver.getCurrentUrl();
		Assert.assertEquals(actualURL, expectedURL);
		WebElement dropdown=driver.findElement(By.id("Skills"));
		Select sel=new Select(dropdown);
		sel.selectByVisibleText("Android");
		sel.selectByIndex(1);
		WebElement firstSelectedOption=sel.getFirstSelectedOption();
		System.out.println("First Selected value: "+ firstSelectedOption.getText());
		sel.selectByIndex(6);
		List<WebElement> list= sel.getAllSelectedOptions();
		for(WebElement skills: list) {
		    System.out.println(skills.getText());
		}
		driver.findElement(By.xpath("//*[@id=\"header\"]/nav/div/div[2]/ul/li[4]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"header\"]/nav/div/div[2]/ul/li[4]/ul/li[1]/a")).click();
		Alert alert=driver.switchTo().alert();
		alert.dismiss();
		driver.findElement(By.xpath("//button[@class=\"btn btn-danger\"]")).click();
		
		alert.accept();
		driver.findElement(By.xpath("//button[@class=\"btn btn-primary\"]")).click();
		alert.dismiss();
		driver.findElement(By.xpath("//button[@class=\"btn btn-info\"]")).click();
		alert.sendKeys("Naveen");
		alert.accept();
		driver.close();
	}
	
	/*@AfterTest
	public void tearDown() {
		driver.quit();
	}
	*/
}

