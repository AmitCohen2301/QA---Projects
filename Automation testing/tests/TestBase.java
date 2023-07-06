package Hw3;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
	public static WebDriver initializeDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\amitx\\Downloads\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}

	public static void initializeExcelTest(ReadExcelTest objExcelFileSanityTest) throws IOException {
		objExcelFileSanityTest.readExcl("exlFiles", "inputXLS.xls", "sheet1");
	}
}
