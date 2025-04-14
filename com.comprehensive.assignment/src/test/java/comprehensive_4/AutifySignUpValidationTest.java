package comprehensive_4;

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

public class AutifySignUpValidationTest {

	private WebDriver driver = null;

	public void runSignUpValidationTest() {

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
			for (String winHandle : allWindows) {
				if (!winHandle.equals(parentWindow)) {
					driver.switchTo().window(winHandle);
					break;
				}
			}

			for (int i = 1; i <= 6; i++) {
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
				System.out.println("✅ Error messages found:\n");
				for (int i = 0; i < errorMessages.size(); i++) {
					if (i == labelNamesList.size()) {
						System.out.println("Terms & conditions checkbox" + " - " + errorMessages.get(i).getText());
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
		} finally {
			driver.quit();
		}

	}

	public static void main(String[] args) {

		AutifySignUpValidationTest autifySignUpValidationTest = new AutifySignUpValidationTest();
		autifySignUpValidationTest.runSignUpValidationTest();

	}

}