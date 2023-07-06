package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FlamingoTshirtPage {
	WebDriver driver;

	public FlamingoTshirtPage(WebDriver driver) {
		this.driver = driver;
	}
	
	// Locator for items
	By flamingoTshirt = By.linkText("Flamingo Tshirt"); // To flamingo Tshirt
	By addToCart = By.name("add-to-cart"); // To add to cart button
	By viewCart = By.xpath("//*[@id=\"main\"]/div/div[1]/div/a"); // To view cart button
	By numOfFlamingoTshirtInCart = By.xpath("//input[@class=\"input-text qty text\"]"); // To number of tshirts in cart
	
	// Click on flamingo tshirt
	public void clickOnFlamingoTshirt() {
		driver.findElement(flamingoTshirt).click();
	}
	
	// Click on add to cart button
	public void clickAddToCart() {
		driver.findElement(addToCart).click();
	}
	
	// Click on view cart button
	public void clickViewCart() {
		driver.findElement(viewCart).click();
	}
	
	// Get number of flamingo tshirts in cart
	public int getNumOfFlamingoTshirtsInCart() {
		return Integer.parseInt(driver.findElement(numOfFlamingoTshirtInCart).getAttribute("value"));
	}
}
