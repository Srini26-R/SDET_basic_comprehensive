package comprehensive_3;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AutifyTrialTest {

	private WebDriver driver = null;

	public void runAutifyTest() {

		ChromeOptions option = new ChromeOptions();
		option.addArguments("start-maximized");

		driver = new ChromeDriver(option);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		try {
			// Navigate to Autify website
			driver.get("https://autify.com/");
			String parentWindow = driver.getWindowHandle();

			// Click on "Autify Nexus" link
			WebElement autifyNexusTrialLink = driver.findElement(By.xpath(
					"(//div[@class='product-div']//a[contains(@href,'nexus')]//div[contains(@class,'external')]//child::img)[1]"));
			autifyNexusTrialLink.click();
			Thread.sleep(3000);

			// Switch to child window
			Set<String> allWindows = driver.getWindowHandles();
			String childWindow = null;
			for (String window : allWindows) {
				if (!window.equals(parentWindow)) {
					childWindow = window;
					break;
				}
			}

			driver.switchTo().window(childWindow);

			// Verify the title of the child window
			String expectedTitle = "Autify Nexus: AI-Powered Test Automation Built on Playwright";
			String actualTitle = driver.getTitle();
			if (expectedTitle.equals(actualTitle)) {
				System.out.println("Child window title verified: " + actualTitle);
			} else {
				System.out.println("Child window did not open!");
				System.out.println("Unexpected title: " + actualTitle);
			}

			// Close only the child window
			driver.close();

			// Switch back to the parent window
			driver.switchTo().window(parentWindow);
			Thread.sleep(3000);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}

	}

	public static void main(String[] args) {
		AutifyTrialTest autifyTrialTest = new AutifyTrialTest();
		autifyTrialTest.runAutifyTest();
	}

}