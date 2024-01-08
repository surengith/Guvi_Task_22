package com.SeleniumPracticeTask22.org;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DropdownAndSynchronization {

	public static void main(String[] args) throws IOException, InterruptedException {

		// Create a new instance of the ChromeDriver that includes WebDriver setup
		WebDriver driver = new ChromeDriver();

		// Maximizes the browser window
		driver.manage().window().maximize();

		// Create a global implicitlyWait for 10 seconds
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Create a explicit wait class for particular WebElement to wait
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Navigate to the URL
		driver.get("https://phptravels.com/demo/");

		// Locate the first name field using XPath
		WebElement firstName = driver.findElement(By.xpath("//input[@name='first_name']"));
		firstName.sendKeys("Surendhar");

		// Locate the last name field using XPath
		WebElement lastName = driver.findElement(By.xpath("//input[@name='last_name']"));
		lastName.sendKeys("P");

		// Locate the business Name field using XPath
		WebElement businessName = driver.findElement(By.xpath("//input[@name='business_name']"));
		businessName.sendKeys("SoftwareTesting");

		// Locate the email field using XPath
		WebElement email = driver.findElement(By.xpath("//input[@name='email']"));
		email.sendKeys("softwaretesting.st@gmail.com");

		// Locate the numb1 and numb2 using XPath and calculate them
		int sum1 = Integer.parseInt(driver.findElement(By.xpath("//span[@id='numb1']")).getText());
		int sum2 = Integer.parseInt(driver.findElement(By.xpath("//span[@id='numb2']")).getText());
		int sum = sum1 + sum2;

		// Locate the input result field using XPath and send the sum value
		WebElement resultNumber = driver.findElement(By.xpath("//input[@id='number']"));
		wait.until(ExpectedConditions.visibilityOf(resultNumber));
		resultNumber.sendKeys(String.valueOf(sum));

		// Locate the submit Button using XPath and click the submit Button
		WebElement submitButton = driver.findElement(By.xpath("//button[@id='demo']"));
		wait.until(ExpectedConditions.visibilityOf(submitButton));
		submitButton.click();

		// Verify that the form is submitted successfully
		WebElement verificationMessage = driver.findElement(By.xpath("//strong[normalize-space()='Thank you!']"));
		wait.until(ExpectedConditions.visibilityOf(verificationMessage));

		String actualMessage = verificationMessage.getText();
		String expectedMessage = "Thank you!";

		if (actualMessage.equalsIgnoreCase(expectedMessage)) {
			System.out.println("Form submitted successfully. Actual message: " + actualMessage);
		} else {
			System.out.println("Form submission failed. Actual message: " + actualMessage);
		}

		// Take a screenshot of the page after form submission
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File destination = new File(
				"C:\\Users\\Surendhar\\eclipse-workspace\\Guvi_Daily_Task\\screenshot\\phptravels.png");
		FileUtils.copyFile(source, destination);

		// Finally quit the browser
		driver.quit();
	}
}
