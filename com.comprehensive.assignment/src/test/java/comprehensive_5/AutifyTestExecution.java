package comprehensive_5;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AutifyTestExecution {

	private WebDriver driver = null;
	private String appUrl = "https://autify.com/";

	@BeforeClass(alwaysRun = true)
	public void browserConfig() {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("start-maximized");
		driver = new ChromeDriver(option);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Test(priority = 1, groups = { "LaunchApp" })
	public void launchApp() {
		driver.get(appUrl);
	}

	@Test(priority = 2, dependsOnGroups = "LaunchApp", groups = { "ChildWindowTitleValidation" })
	public void switchWindowValidation() {

		try {
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

			// Assertion to verify expected and actual title of the child window
			Assert.assertEquals(actualTitle, expectedTitle, "Child window title validation failed!");

			// Close only the child window
			driver.close();

			// Switch back to the parent window
			driver.switchTo().window(parentWindow);
			Thread.sleep(3000);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(priority = 3, dependsOnGroups = "LaunchApp", groups = { "MandatoryFieldValidation" })
	public void mandatoryFieldsErrorValidation() {

		try {
			String parentWindow = driver.getWindowHandle();

			// Click on "Autify Nexus" link
			WebElement autifyNexusTrialLink = driver.findElement(By.xpath(
					"(//div[@class='product-div']//a[contains(@href,'nexus')]//div[contains(@class,'external')]//child::img)[1]"));
			autifyNexusTrialLink.click();
			Thread.sleep(3000);

			// Switch to child window
			Set<String> allWindows = driver.getWindowHandles();
			for (String winHandle : allWindows) {
				if (!winHandle.equals(parentWindow)) {
					driver.switchTo().window(winHandle);
					break;
				}
			}

			for (int i = 1; i <= 10; i++) {
				try {
					// Click on "Sign Up" without filling anything
					WebElement signUpBtn = driver.findElement(By.xpath("//div//input[@type='submit']"));
					signUpBtn.click();
					Thread.sleep(2000);
					break;
				} catch (ElementClickInterceptedException exc) {
					// Scroll to the element into view
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].scrollIntoView({block: 'center'});",
							driver.findElement(By.xpath("//div//input[@type='submit']")));
					continue;
				}
			}

			// Verify error messages are displayed
			List<WebElement> errorMessages = driver
					.findElements(By.xpath("//div//ul[@role='alert']//li//child::label"));

			List<WebElement> labelNamesList = driver
					.findElements(By.xpath("//div//ul[@role='alert']//preceding-sibling::label"));

			if (errorMessages.size() == 0) {
				System.out.println("❌ No error messages displayed.");
			} else {
				System.out.println("✅ Error messages found:");
				for (int i = 0; i < errorMessages.size(); i++) {
					if (i == labelNamesList.size()) {
						System.out
								.println("Terms & conditions checkbox" + " - " + errorMessages.get(i).getText() + "\n");
						break;
					}
					System.out.println(labelNamesList.get(i).getText() + " - " + errorMessages.get(i).getText());
				}
			}

			// Close only the child window
			driver.close();

			// Switch back to parent window
			driver.switchTo().window(parentWindow);
			Thread.sleep(3000);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}