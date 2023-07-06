package Pages;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}

	// Locator for items
	By checkout = By.xpath("//*[@id=\"post-39\"]/div/div/section[2]/div/div/div/div/div/div/div/div[2]/div/div/a"); // To checkout button	
	By placeOrder = By.xpath("//*[@id=\"place_order\"]"); // To place order button
	By res1 = By.xpath("//*[@id=\"post-40\"]/div/div/section[2]/div/div/div/div/div/div/div/form[3]/div[1]/ul/li"); // To result box 1

	// Click on checkout button
	public void clickOnCheckout() {
		driver.findElement(checkout).click();
	}
	
	// Fill the fields from excel sheet
	public void fillOutFromExcel(CheckoutPage checkOutPage, Sheet excelSheet, int numOfRowWithInfoToFill) {
		Row names = excelSheet.getRow(0);
		Row row = excelSheet.getRow(numOfRowWithInfoToFill); // numOfRowWithInfoToFill = 1
		for (int j = 0; j < row.getLastCellNum() - 2; j++) {
			Cell cell = row.getCell(j);
			if (cell.getCellType() == CellType.STRING) {
				checkOutPage.sendStringKeys(names.getCell(j).getStringCellValue(), cell);
			} else if (cell.getCellType() == CellType.NUMERIC) {
				System.out.print(String.valueOf(cell.getNumericCellValue()) + " ** ");
				checkOutPage.sendNumericKeys(names.getCell(j).getStringCellValue(), cell);
			}
		}
	}

	// Click on place order button
	public void clickPlaceOrder() {
		this.driver.findElement(placeOrder).click();
	}

	// Send numeric key
	public void sendNumericKeys(String nameOfBox, Cell cell) {
		WebElement element = driver.findElement(By.id(nameOfBox));
		element.sendKeys(String.valueOf((int)cell.getNumericCellValue()));
	}

	// Send string key
	public void sendStringKeys(String nameOfBox, Cell cell) {
		WebElement element = driver.findElement(By.id(nameOfBox));
		element.sendKeys(cell.getStringCellValue());
	}

	// Get text in result 1
	public String getTextRes1() {
		return this.driver.findElement(res1).getText();
	}
}
