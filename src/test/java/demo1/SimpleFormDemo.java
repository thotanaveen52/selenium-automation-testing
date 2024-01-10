package demo1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class SimpleFormDemo {

    private WebDriver driver;

    @Parameters({"browser", "os"})
    @BeforeClass
    public void setUp(String browser, String os) throws MalformedURLException {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            setCommonCapabilities(options);
            driver = new RemoteWebDriver(new URL(getLambdaTestURL(os)), options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            setCommonCapabilities(options);
            driver = new RemoteWebDriver(new URL(getLambdaTestURL(os)), options);
        } else {
            throw new IllegalArgumentException("Invalid browser provided: " + browser);
        }
        driver.manage().window().maximize();
    }

    private void setCommonCapabilities(Object options) {
        // Set common capabilities like network logs, video recording, screenshots, console logs
        // Refer to the Capability Generator for desired capabilities
    	
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
		
    }

    private String getLambdaTestURL(String os) {
        // Return LambdaTest URL based on the provided OS
        // Example: "https://USERNAME:ACCESS_KEY@hub.lambdatest.com/wd/hub"
        return "https://USERNAME:ACCESS_KEY@hub.lambdatest.com/wd/hub";
    }

    @DataProvider(name = "browserOS")
    public Object[][] getBrowserOS() {
        return new Object[][]{
                {"chrome", "Windows 10"},
                {"firefox", "macOS Mojave"}
                // Add more browser-OS combinations as needed
        };
    }

    @Test(dataProvider = "browserOS", threadPoolSize = 2, invocationCount = 2)
    public void testSimpleFormDemo(String browser, String os) {
        // Navigate to the LambdaTest Selenium Playground page
        driver.get("https://www.lambdatest.com/selenium-playground");

        // Click "Simple Form Demo" under Input Forms
        WebElement simpleFormDemo = driver.findElement(By.linkText("Simple Form Demo"));
        simpleFormDemo.click();

        // Validate that the URL contains "simple-form-demo"
        String currentURL = driver.getCurrentUrl();
        assert currentURL.contains("simple-form-demo") : "URL validation failed";

        // Create a variable for a string value
        String welcomeMessage = "Welcome to LambdaTest";

        // Use this variable to enter values in the "Enter Message" text box
        WebElement enterMessageTextbox = driver.findElement(By.id("user-message"));
        enterMessageTextbox.sendKeys(welcomeMessage);

        // Click "Get Checked Value"
        WebElement getCheckedValueButton = driver.findElement(By.xpath("//button[text()='Get Checked Value']"));
        getCheckedValueButton.click();

        // Validate whether the same text message is displayed in the right-hand panel
        WebElement displayedMessage = driver.findElement(By.id("display"));
        assert displayedMessage.getText().equals("Your Message: " + welcomeMessage) : "Message validation failed";
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
