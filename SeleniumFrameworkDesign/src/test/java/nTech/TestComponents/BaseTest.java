package nTech.TestComponents;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import nTech.SeleniumFrameworkDesign.pageobjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
//	public LandingPage landingPage=new LandingPage(driver);
	public WebDriver initializeDriver() throws IOException {
//		public void initializeDriver() throws IOException {
		
//		Properties Class - Accessing selected browser using properties class
		
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\nTech\\resources\\GlobalData.properties");		
//		System.out.println("Path is : "+System.getProperty("user.dir"));
		prop.load(fis);
		
//		<N> Following code used java Ternary Operator (https://www.w3schools.com/java/java_conditions_shorthand.asp)
		String browserName=System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
		
//		String browserName=prop.getProperty("browser");
		
		if(browserName.contains("chrome")) {

//			WebDriverManager.chromedriver().setup();
//			
//			driver=new ChromeDriver();	
			
//			Headless browser code
			ChromeOptions options=new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			
			if(browserName.contains("headless")) {
			options.addArguments("headless");	
			}
			
			driver=new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900)); //Full Screen
//			driver.manage().window().setSize(new Dimension(1920, 1200));
			
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
//			Firefox
			System.setProperty("webdriver.gecko.driver", "C:\\Nuwan\\Selenium Drivers\\geckodriver\\v0.33.0\\geckodriver.exe");
			driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
//			Edge
			System.setProperty("webdriver.edge.driver", "edge.exe");
			driver=new EdgeDriver();
		}
		
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();	
		
		return driver;
		
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException{
		
//		String jsonContent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\nTech\\data\\PurchaseOrder.json"), StandardCharsets.UTF_8);
		String jsonContent=FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
//		Convert String to Hashmap Jackson Databind
		 ObjectMapper mapper = new ObjectMapper();
		 List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		 return data;		
	}
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		
		return System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		System.out.println("Executing from before Method");
		driver=initializeDriver();		
		landingPage=new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
//	Code by @Neth this method can replace the above method
//	@BeforeMethod(alwaysRun=true)
//	public void launchApplication() throws IOException {
//		driver=initializeDriver();		
//		landingPage=new LandingPage(driver);
//		landingPage.goTo();		
//	}
	
	@AfterMethod(alwaysRun=true)
	public void closeBrowser() throws InterruptedException {
//		Thread.sleep(5000);
		driver.close();
	}
	
	

}
