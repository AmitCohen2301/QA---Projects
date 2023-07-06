package Pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CategoriesPage {
	WebDriver driver;

	public CategoriesPage(WebDriver driver) {
		this.driver = driver;
	}

	// Locator for items
	By womenButton = By.xpath("//*[@id=\"menu-item-267\"]/a"); // To women button
	By womenSecondPageButton = By.xpath("//*[@id=\"main\"]/div/nav[2]/ul/li[2]/a"); // To women second page button
	By categoriesOfFirstWomenPage = By.className("ast-woo-product-category"); // All categories of first page in women
	By itemsOfFirstWomenPage = By.className("woocommerce-loop-product__title"); // All items of first page in women
	By categoriesOfSecondWomenPage = By.className("ast-woo-product-category"); // All categories of second page in women
	By itemsOfSecondWomenPage = By.className("woocommerce-loop-product__title"); // All items of second page in women
	By itemThatLinkTextIsWomen = By.linkText("Women"); // Item that link text is women
	By mensButton = By.xpath("//*[@id=\"menu-item-266\"]/a"); // To mens button
	By mensSecondPageButton = By.xpath("//*[@id=\"main\"]/div/nav[2]/ul/li[2]/a"); // To men second page button
	By categoriesOfFirstMensPage = By.className("ast-woo-product-category"); // All categories of first page in men
	By itemsOfFirstMensPage = By.className("woocommerce-loop-product__title"); // All items of first page in men
	By categoriesOfSecondMensPage = By.className("ast-woo-product-category"); // All categories of second page in men
	By itemsOfSecondMensPage = By.className("woocommerce-loop-product__title"); // All items of second page in men
	By itemThatLinkTextIsMen = By.linkText("Men"); // Item that link text is men
	By accButton = By.xpath("//*[@id=\"menu-item-671\"]/a"); // To accessories button
	By categoriesOfFirstAccPage = By.className("ast-woo-product-category"); // All categories of first page in accessories
	By itemsOfFirstAccPage = By.className("woocommerce-loop-product__title"); // All items of first page in accessories
	By categoriesOfSecondAccPage = By.className("ast-woo-product-category"); // All categories of second page in accessories
	By itemsOfSecondAccPage = By.className("woocommerce-loop-product__title"); // All items of second page in accessories
	By itemThatLinkTextIsAcc = By.linkText("Accessories"); // Item that link text is accessories

	// Click on women button
	public void clickWomenCategory() {
		this.driver.findElement(womenButton).click();
	}
	
	// Click on women second button
	public void clickWomenSecondPageButton() {
		this.driver.findElement(womenSecondPageButton).click();
	}
	
	// Get list of all categories in first women page
	public List<WebElement> getAllCategoriesInFirstWomenPage() {
		return driver.findElements(categoriesOfFirstWomenPage);
	}
	
	// Get list of all items in first women page
	public List<WebElement> getAllItemsInFirstWomenPage() {
		return driver.findElements(itemsOfFirstWomenPage);
	}
	
	// Get list of all categories in second women page
	public List<WebElement> getAllCategoriesInSecondWomenPage() {
		return driver.findElements(categoriesOfSecondWomenPage);
	}
	
	// Get list of all items in second women page
	public List<WebElement> getAllItemsInSecondWomenPage() {
		return driver.findElements(itemsOfSecondWomenPage);
	}
	
	// Get item that text is women
	public WebElement getItemThatLinkTextIsWomen() {
		return driver.findElement(itemThatLinkTextIsWomen);
	}
	
	// Click on mens button
	public void clickMensCategoryButton() {
		this.driver.findElement(mensButton).click();
	}
	
	// Click on mens second page button
	public void clickMensSecondPageButton() {
		this.driver.findElement(mensSecondPageButton).click();
	}
	
	// Get list of all categories in first men page
	public List<WebElement> getAllCategoriesInFirstMensPage() {
		return driver.findElements(categoriesOfFirstMensPage);
	}
	
	// Get list of all items in first men page
	public List<WebElement> getAllItemsInFirstMensPage() {
		return driver.findElements(itemsOfFirstMensPage);
	}
	
	// Get list of all categories in second men page
	public List<WebElement> getAllCategoriesInSecondMensPage() {
		return driver.findElements(categoriesOfSecondMensPage);
	}
	
	// Get list of all items in second men page
	public List<WebElement> getAllItemsInSecondMensPage() {
		return driver.findElements(itemsOfSecondMensPage);
	}
	
	// Get item that text is men
	public WebElement getItemThatLinkTextIsMen() {
		return driver.findElement(itemThatLinkTextIsMen);
	}
	
	// Click on accessories button
	public void clickAccCategory() {
		this.driver.findElement(accButton).click();
	}
	
	// Get list of all categories in first accessories page
	public List<WebElement> getAllCategoriesInFirstAccPage() {
		return driver.findElements(categoriesOfFirstAccPage);
	}
	
	// Get list of all items in first accessories page
	public List<WebElement> getAllItemsInFirstAccPage() {
		return driver.findElements(itemsOfFirstAccPage);
	}
	
	// Get item that text is accessories
	public WebElement getItemThatLinkTextIsAcc() {
		return driver.findElement(itemThatLinkTextIsAcc);
	}
}
