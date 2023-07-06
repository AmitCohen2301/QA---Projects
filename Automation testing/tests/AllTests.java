package Hw3;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Dimension;
import Pages.CategoriesPage;
import Pages.CheckoutPage;
import Pages.FlamingoTshirtPage;

public class AllTests {
	private Logger logger;
	private WebDriver driver;
	private ReadExcelTest objExcelFileTest;
	
	// Initialize
	@Before
	public void setUp() throws IOException {
		this.driver = TestBase.initializeDriver();
		this.logger = Logger.getLogger(AllTests.class.getName());
		this.objExcelFileTest = new ReadExcelTest();
		TestBase.initializeExcelTest(objExcelFileTest);
	}

	// Close window after each test
	@After
	public void tearDown() {
		driver.quit();
	}

	// Add FlamingoTshirt to cart test
	@Test
	public void addFlamingoTshirtToCart() throws Exception {
		this.logger.info("Start AddFlamingoTshirtToCart Test");
		// Test name: addFlamingoTshirtToCart
		// Step # | name | value
		// 1 | open | ATID site
		driver.get("https://atid.store/");
		this.logger.info("Opening atid website");
		Thread.sleep(500);

		// 2 | setWindowSize | 1052x666
		driver.manage().window().setSize(new Dimension(1052, 666));
		this.logger.info("Set window size");
		Thread.sleep(500);

		// 3 | click on Flamingo Tshirt
		FlamingoTshirtPage flamingoTshirtPage = new FlamingoTshirtPage(driver);
		flamingoTshirtPage.clickOnFlamingoTshirt();
		this.logger.info("Click on flamingo tshirt");
		Thread.sleep(500);

		// 4 | left click on add to cart
		flamingoTshirtPage.clickAddToCart();
		this.logger.info("Click on add to cart");
		Thread.sleep(500);

		// 5 | click on view cart
		flamingoTshirtPage.clickViewCart();
		this.logger.info("Click on view cart");
		Thread.sleep(500);

		// 6 | Check if there is 1 flamingo tshirt in cart
		this.logger.info("Check how many flamingo tshirts in cart");
		if (flamingoTshirtPage.getNumOfFlamingoTshirtsInCart() != 1)
			throw new Exception("Failed to add item to cart");
		this.logger.info("End AddFlamingoTshirtToCart Test");
	}

	// Add two FlamingoTshirt to cart test
	@Test
	public void addTwoFlamingoTshirtToCart() throws Exception {
		this.logger.info("Start AddTwoFlamingoTshirtToCart Test");
		// Test name: addTwoFlamingoTshirtToCart
		// Step # | name | value
		// 1 | open | ATID site
		driver.get("https://atid.store/");
		this.logger.info("Opening atid website");
		Thread.sleep(500);

		// 2 | setWindowSize | Max size
		driver.manage().window().setSize(new Dimension(1052, 666));
		this.logger.info("Set window size");
		Thread.sleep(500);

		// 3 | click on Flamingo Tshirt
		FlamingoTshirtPage flamingoTshirtPage = new FlamingoTshirtPage(driver);
		flamingoTshirtPage.clickOnFlamingoTshirt();
		this.logger.info("Click on flamingo tshirt");
		Thread.sleep(500);

		// 4 | left click on add to cart
		flamingoTshirtPage.clickAddToCart();
		this.logger.info("Click on add to cart");
		Thread.sleep(500);

		// 5 | second left click on add to cart
		flamingoTshirtPage.clickAddToCart();
		this.logger.info("Second click on add to cart");
		Thread.sleep(500);

		// 6 | click on view cart
		flamingoTshirtPage.clickViewCart();
		this.logger.info("Click on view cart");
		Thread.sleep(500);

		// 7 | Check if there is 1 flamingo tshirt in cart
		this.logger.info("Check how many flamingo tshirts in cart");
		if (flamingoTshirtPage.getNumOfFlamingoTshirtsInCart() != 2)
			throw new Exception("Failed to add 2 items to cart");
		this.logger.info("End AddTwoFlamingoTshirtToCart Test");
	}

	// Women category filtering test
	@Test
	public void womenCategoryFiltering() throws Exception {
		this.logger.info("Start WomenCategory Test");
		// Test name: womenCategoryFiltering
		// Step # | name | value
		// 1 | open | ATID site
		driver.get("https://atid.store/");
		this.logger.info("Opening atid website");
		Thread.sleep(500);

		// 2 | setWindowSize | Max size
		driver.manage().window().setSize(new Dimension(1052, 666));
		this.logger.info("Set window size");
		Thread.sleep(500);

		// 3 | left click on women
		CategoriesPage categoriesPage = new CategoriesPage(driver);
		categoriesPage.clickWomenCategory();
		this.logger.info("Click on women botton");
		Thread.sleep(500);

		// 4 | get all items in first page
		List<WebElement> allFilterCategories = categoriesPage.getAllCategoriesInFirstWomenPage();
		List<WebElement> allFilterItems = categoriesPage.getAllItemsInFirstWomenPage();
		this.logger.info("Get all items and categories in first women page");
		Thread.sleep(500);
		
		// 5 | Move on every element in first page
		int numOfItem = 0;
		for (WebElement filterCategory : allFilterCategories) {
			if (!(filterCategory.getText().equals("Women"))) {
				this.logger.info("Check specific item in first page that the main category is not women");
				allFilterItems.get(numOfItem).click();
				Thread.sleep(500);
				if (categoriesPage.getItemThatLinkTextIsWomen() == null || !(categoriesPage.getItemThatLinkTextIsWomen().getText().equals("Women"))) {
					throw new Exception("Not all items are for women");
				}
				driver.navigate().back();
				Thread.sleep(500);
			}
			else
				this.logger.info("Check specific item in first page that the main category is women");
			numOfItem++;
		}
		
		// 6 | Move to second page
		categoriesPage.clickWomenSecondPageButton();
		this.logger.info("Click on women second page botton");
		Thread.sleep(500);
		
		// 7 | get all items in second page
		allFilterCategories = categoriesPage.getAllCategoriesInSecondWomenPage();
		allFilterItems = categoriesPage.getAllItemsInSecondWomenPage();
		this.logger.info("Get all items and categories in second women page");
		Thread.sleep(500);
		
		// 8 | Move on every element in second page
		numOfItem = 0;
		for (WebElement filterCategory : allFilterCategories) {
			if (!(filterCategory.getText().equals("Women"))) {
				this.logger.info("Check specific item in second page that the main category is not women");
				allFilterItems.get(numOfItem).click();
				Thread.sleep(500);
				if (categoriesPage.getItemThatLinkTextIsWomen() == null || !(categoriesPage.getItemThatLinkTextIsWomen().getText().equals("Women"))) {
					throw new Exception("Not all items are for women");
				}
				driver.navigate().back();
				Thread.sleep(500);
			}
			else
				this.logger.info("Check specific item in second page that the main category is women");
			numOfItem++;
		}
		this.logger.info("End WomenCategory Test");
	}

	// Men category filtering test
	@Test
	public void mensCategoryFiltering() throws Exception {
		this.logger.info("Start MensCategory Test");
		// Test name: menCategoryFiltering
		// Step # | name | value
		// 1 | open | ATID site
		driver.get("https://atid.store/");
		this.logger.info("Opening atid website");
		Thread.sleep(500);

		// 2 | setWindowSize | Max size
		driver.manage().window().setSize(new Dimension(1052, 666));
		this.logger.info("Set window size");
		Thread.sleep(500);

		// 3 | click on men
		CategoriesPage categoriesPage = new CategoriesPage(driver);
		categoriesPage.clickMensCategoryButton();
		this.logger.info("Click on mens botton");
		Thread.sleep(500);

		// 4 | get all items in first page
		List<WebElement> allFilterCategories = categoriesPage.getAllCategoriesInFirstMensPage();
		List<WebElement> allFilterItems = categoriesPage.getAllItemsInFirstMensPage();
		this.logger.info("Get all items and categories in first mens page");
		Thread.sleep(500);

		// 5 | Move on every element in first page
		int numOfItem = 0;
		for (WebElement filterCategory : allFilterCategories) {
			if (!(filterCategory.getText().equals("Men"))) {
				this.logger.info("Check specific item in first page that the main category is not men");
				allFilterItems.get(numOfItem).click();
				Thread.sleep(500);
				if (categoriesPage.getItemThatLinkTextIsMen() == null || !(categoriesPage.getItemThatLinkTextIsMen().getText().equals("Men"))) {
					throw new Exception("Not all items are for mens");
				}
				Thread.sleep(500);
				driver.navigate().back();
				Thread.sleep(500);
			}
			else
				this.logger.info("Check specific item in first page that the main category is men");
			numOfItem++;
		}
		
		// 6 | Move to second page
		categoriesPage.clickMensSecondPageButton();
		this.logger.info("Click on mens second page botton");
		Thread.sleep(500);
		
		// 7 | get all items in second page
		allFilterCategories = categoriesPage.getAllCategoriesInSecondMensPage();
		allFilterItems = categoriesPage.getAllItemsInSecondMensPage();
		this.logger.info("Get all items and categories in second mens page");
		Thread.sleep(500);
		
		// 8 | Move on every element in second page
		numOfItem = 0;
		for (WebElement filterCategory : allFilterCategories) {
			if (!(filterCategory.getText().equals("Men"))) {
				this.logger.info("Check specific item in second page that the main category is not men");
				allFilterItems.get(numOfItem).click();
				Thread.sleep(500);
				if (categoriesPage.getItemThatLinkTextIsMen() == null || !(categoriesPage.getItemThatLinkTextIsMen().getText().equals("Men"))) {
					throw new Exception("Not all items are for mens");
				}
				driver.navigate().back();
				Thread.sleep(500);
			}
			else
				this.logger.info("Check specific item in second page that the main category is men");
			numOfItem++;
		}
		this.logger.info("End MensCategory Test");
	}

	// Accessories category filtering test
	@Test
	public void accessoriesCategory() throws Exception {
		this.logger.info("Start AccessoriesCategory Test");
		// Test name: accessoriesCategory
		// Step # | name | value
		// 1 | open | ATID site
		driver.get("https://atid.store/");
		this.logger.info("Opening atid website");
		Thread.sleep(500);

		// 2 | setWindowSize | Max size
		driver.manage().window().setSize(new Dimension(1052, 666));
		this.logger.info("Set window size");
		Thread.sleep(500);

		// 3 | click on accessories
		CategoriesPage categoriesPage = new CategoriesPage(driver);
		categoriesPage.clickAccCategory();
		this.logger.info("Click on accessories botton");
		Thread.sleep(500);
		
		// 4 | get all items
		List<WebElement> allFilterCategories = categoriesPage.getAllCategoriesInFirstAccPage();
		List<WebElement> allFilterItems = categoriesPage.getAllItemsInFirstAccPage();
		this.logger.info("Get all items and categories in first men page");
		Thread.sleep(500);
		
		// 5 | Move on every element
		int numOfItem = 0;
		for (WebElement filterCategory : allFilterCategories) {
			if (!(filterCategory.getText().equals("Accessories"))) {
				this.logger.info("Check specific item that the main category is not accessories");
				allFilterItems.get(numOfItem).click();
				Thread.sleep(500);
				if (categoriesPage.getItemThatLinkTextIsAcc() == null || !(categoriesPage.getItemThatLinkTextIsAcc().getText().equals("Accessories"))) {
					throw new Exception("Not all items are accessories");
				}
				Thread.sleep(500);
				driver.navigate().back();
				Thread.sleep(500);
			}
			else
				this.logger.info("Check specific item that the main category is accessories");
			numOfItem++;
		}
		this.logger.info("End AccessoriesCategory Test");
	}

	// Check out test
	@Test
	public void checkOut() throws Exception {
		this.logger.info("Start CheckOut Test");
		// Test name: accessoriesCategory
		// Step # | name | value
		// 1 | open | ATID site
		driver.get("https://atid.store/");
		this.logger.info("Opening atid website");
		Thread.sleep(500);

		// 2 | setWindowSize | Max size
		driver.manage().window().setSize(new Dimension(1052, 666));
		this.logger.info("Set window size");
		Thread.sleep(500);

		// 3 | click on Flamingo Tshirt
		FlamingoTshirtPage flamingoTshirtPage = new FlamingoTshirtPage(driver);
		flamingoTshirtPage.clickOnFlamingoTshirt();
		this.logger.info("Click on flamingo tshirt");
		Thread.sleep(500);

		// 4 | left click on add to cart
		flamingoTshirtPage.clickAddToCart();
		this.logger.info("Click on add to cart");
		Thread.sleep(500);

		// 5 | click on view cart
		flamingoTshirtPage.clickViewCart();
		this.logger.info("Click on view cart");
		Thread.sleep(500);

		// 6 | click on check out
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.clickOnCheckout();
		this.logger.info("Click on checkout");
		Thread.sleep(500);

		// 7 | fill the fields in form
		driver.manage().window().setSize(new Dimension(1052, 666));
		this.logger.info("start filling out the form");
		Thread.sleep(500);
		Sheet excelSheet = ReadExcelTest.getsheet();
		int rowCount = ReadExcelTest.getRowcount();
		checkoutPage.fillOutFromExcel(checkoutPage, excelSheet, rowCount);
		this.logger.info("finish filling out the form");
		Thread.sleep(500);
		
		// 8 | click on place order
		checkoutPage.clickPlaceOrder();
		this.logger.info("click on place order");
		Thread.sleep(500);
		
		// 9 | compare excepted result
		if((checkoutPage.getTextRes1() == null) || !(checkoutPage.getTextRes1().equals("Invalid payment method.")))
			throw new Exception("Failed to do checkout");
		Thread.sleep(500);
		this.logger.info("End CheckOut Test");
	}
	
	// Main to run all tests
	public static void main(String args[]) {
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		org.junit.runner.Result result = junit.run(AllTests.class);
		if (result.getFailureCount() > 0) {
			System.out.println("Test failed.");
			System.exit(1);
		} else {
			System.out.println("Test finished successfully.");
			System.exit(0);
		}
	}
}
