package selTesting;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class selTest {
	
	public static String getValue(int row, int col) throws IOException{
		
		File sheet = new File("edxSheet.xlsx");
		FileInputStream fis = new FileInputStream(sheet);
		XSSFWorkbook xwb = new XSSFWorkbook(fis);
		XSSFSheet edxSheet = xwb.getSheetAt(0);
		xwb.close();
		
		return edxSheet.getRow(row).getCell(col).getStringCellValue();
	}
	
	public static void launchWeb(WebDriver driver, String edxEmail, String edxPassword) throws IOException{
		
        driver.get("https://www.edx.org");        
        driver.manage().window().maximize();
        
        WebElement e = driver.findElement(By.cssSelector("html.js body.html.front.not-logged-in.no-sidebars.page-"
        		+ "node.page-node-.page-node-15321.node-type-home-page.site-name-hidden.front2 div#page.page header.region"
        		+ ".region-identity.edx-header div.region-inner.clearfix div.region-container.container div.region-container-inner"
        		+ ".header-search div.js-edx-header-ui.edx-header-ui nav.js-user-cta.user-cta a.btn"));

        e.click();
        
        e = driver.findElement(By.id("login-email"));
        e.sendKeys(edxEmail);

        e = driver.findElement(By.id("login-password"));
        e.sendKeys(edxPassword);
        
        e = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/section[1]/div/form/button"));
        e.click();
	}

	@Test
    public void TestLoginSucessFF() throws IOException{
    	
        WebDriver driver = new FirefoxDriver();
        
        String edxEmail = getValue(1, 0);
        String edxPassword = getValue(1, 1);
        
		launchWeb(driver, edxEmail, edxPassword);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.urlContains("dashboard"));
        
		assertEquals(driver.getCurrentUrl(), "https://courses.edx.org/dashboard");
		driver.quit();
    }
	
	@Test
    public void TestLoginSucessIE() throws IOException{
		
		System.setProperty("webdriver.ie.driver", "drivers\\IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver();
		
        String edxEmail = getValue(1, 0);
        String edxPassword = getValue(1, 1);
        
		launchWeb(driver, edxEmail, edxPassword);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.urlContains("dashboard"));
        
		assertEquals(driver.getCurrentUrl(), "https://courses.edx.org/dashboard");
		driver.quit();
	}
	
	@Test
    public void TestLoginSucessChrome() throws IOException, InterruptedException{
		
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
        String edxEmail = getValue(1, 0);
        String edxPassword = getValue(1, 1);
        
		launchWeb(driver, edxEmail, edxPassword);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.urlContains("dashboard"));
        
		assertEquals(driver.getCurrentUrl(), "https://courses.edx.org/dashboard");
		driver.quit();
	}
	
	@Test
    public void TestLoginFailFF() throws IOException, InterruptedException{
		
		WebDriver driver = new FirefoxDriver();
		
        String edxEmail = getValue(2, 0);
        String edxPassword = getValue(2, 1);
        
		launchWeb(driver, edxEmail, edxPassword);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("/html/body"
        		+ "/div[2]/div[2]/div/div/section[1]/div/div[3]"))));
		
		assertTrue(driver.findElement(By.xpath("/html/body"
        		+ "/div[2]/div[2]/div/div/section[1]/div/div[3]")).isDisplayed());
		driver.quit();
	}
	
	@Test
    public void TestLoginFailIE() throws IOException{
		
		System.setProperty("webdriver.ie.driver", "drivers\\IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver();
		
        String edxEmail = getValue(2, 0);
        String edxPassword = getValue(2, 1);
        
		launchWeb(driver, edxEmail, edxPassword);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("/html/body"
        		+ "/div[2]/div[2]/div/div/section[1]/div/div[3]"))));
		
		assertTrue(driver.findElement(By.xpath("/html/body"
        		+ "/div[2]/div[2]/div/div/section[1]/div/div[3]")).isDisplayed());
		driver.quit();
	}
	
	@Test
    public void TestLoginFailChrome() throws IOException, InterruptedException{
		
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
        String edxEmail = getValue(2, 0);
        String edxPassword = getValue(2, 1);
        
		launchWeb(driver, edxEmail, edxPassword);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("/html/body"
        		+ "/div[2]/div[2]/div/div/section[1]/div/div[3]"))));
		
		assertTrue(driver.findElement(By.xpath("/html/body"
        		+ "/div[2]/div[2]/div/div/section[1]/div/div[3]")).isDisplayed());
		driver.quit();
	}

}
