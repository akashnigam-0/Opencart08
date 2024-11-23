package testBase;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


public class baseClass {
	
protected WebDriver driver;
public Logger logger;	
public Properties p;

	@SuppressWarnings("deprecation")
	@BeforeClass(groups={"Sanity","Master","DataDriven"})
	@Parameters({"os","browser"})
	public void setUp(@Optional("Windows") String os,String br) throws IOException {
		
		FileReader file=new FileReader("./src//test//resources//config.properties");
		 p=new Properties();
		p.load(file);
		
		logger= LogManager.getLogger(this.getClass());
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities cap=new DesiredCapabilities();
			
			
			if(os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("Linux")) {
				cap.setPlatform(Platform.LINUX);
			}
			else{
				System.out.println("No matching os");
				return;
			}
			
			switch(br.toLowerCase()) {
			case "chrome" : cap.setBrowserName("chrome");break;
			case "edge" : cap.setBrowserName("MicrosoftEdge");break;
			default :System.out.print("Invalid browser");return; 
			
			}
			driver=new RemoteWebDriver(new URL("http://192.168.14.94:4444/wd/hub"),cap);
			
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch(br.toLowerCase()) {
			case "chrome" :driver = new ChromeDriver();break;
			case "edge" :driver = new EdgeDriver();break;
			case "firefox" :driver = new FirefoxDriver();break;
			default :System.out.print("Invalid browser");return; 
			
			}
		}
		
		
		
		  
		  driver.manage().deleteAllCookies();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	      driver.get(p.getProperty("url"));
	      driver.manage().window().maximize();
	      
	      
	}
	
	@AfterClass(groups={"Sanity","Master","DataDriven"})
	public void tearDown() {
		driver.quit();
	}
	
	
	public String randomStr() {
		String gs=RandomStringUtils.randomAlphabetic(5);
		return gs;
	}
	public String randomNum() {
		String g=RandomStringUtils.randomNumeric(10);
		return g;
	}	
	public String randomPass() {
		String g1=RandomStringUtils.randomNumeric(3);
		String g2=RandomStringUtils.randomAlphabetic(3);
		String gss=g1+"@"+g2;
		return gss;
	}

}
