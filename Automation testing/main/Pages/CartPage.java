package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		this.driver = driver;
	}

	// Locator for items
	By checkout = By.xpath("//*[@id=\"post-39\"]/div/div/div/section[2]/div/div/div/div/div/div/div/div[2]/div/div/a"); // To checkout button

	// Click on checkout button
	public void clickCheckout() {
		driver.findElement(checkout).click();
	}
}
