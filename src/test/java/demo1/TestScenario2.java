package demo1;

import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestScenario2 {
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
		ltOptions.put("name", "TestScenario2");
		ltOptions.put("selenium_version", "4.0.0");
		ltOptions.put("w3c", true);
		browserOptions.setCapability("LT:Options", ltOptions);
		driver = new RemoteWebDriver(new URL(hubURL), browserOptions);
		
	}
	@Test
	public void dragAndDrop() {
		driver.get("https://www.lambdatest.com/selenium-playground/");
		driver.findElement(By.linkText("Drag & Drop Sliders")).click();
		Actions moveSlider = new Actions (driver);
		WebElement slider = driver.findElement(By.xpath("//*[@id=\"slider3\"]/div/input"));
		moveSlider.dragAndDropBy(slider, 215, 0).build().perform();
		WebElement output = driver.findElement(By.xpath("//output[contains(text(),'95')]"));
		Assert.assertEquals(output.getText(), "95");
		}
	@AfterClass
	public void tearDown() {
		driver.quit();
}
}

