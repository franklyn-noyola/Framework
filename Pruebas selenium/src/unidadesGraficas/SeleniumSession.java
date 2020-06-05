package unidadesGraficas;

import org.openqa.selenium.firefox.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.ArrayList;

public class SeleniumSession {

	private static WebDriver driverPlaza=null;
	private static WebDriver driverHost=null;
	private static WebDriver driverActivo=null;
	private static int numOpenedSessions=0;
	
	public SeleniumSession(String url, Navigator navigator, int mode) {
		
		switch (mode) {
			case 0: //Plaza
				driverPlaza=addDriver(url, navigator); driverActivo=driverPlaza; break;
			case 1: //Host
				driverHost=addDriver(url, navigator); driverActivo=driverHost;  break;
			default: break;
		}
	}
	
	public WebDriver addDriver(String url, Navigator navigator) {
		WebDriver driver=null;
		switch (navigator) {
			case CHROME:
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\morenos\\eclipse-workspace\\chromedriver.exe");
				ArrayList<String> optionsList = new ArrayList<String>();
				ChromeOptions chromeOptions = new ChromeOptions();
				optionsList.add("--start-maximized");
				optionsList.add("--incognito");
				chromeOptions.addArguments(optionsList);
				driver = new ChromeDriver(chromeOptions);
				break;
			case FIREFOX: 
				driver = new FirefoxDriver();
				break;
			case IE:
				break;
			default:
				break;
			}
		driver.get(url);
		numOpenedSessions++;
		return driver;
	}
	
	public boolean firstDriver() {
		if (numOpenedSessions==1) return true;
		else return false;
	}
	
	public boolean allDriversClosed() {
		if (numOpenedSessions==0) return true;
		else return false;
	}
	
	public static WebDriver driver() {
		if (driverActivo==driverPlaza) return driverPlaza;
		else return driverHost;
	}
	
	public static void setDriver(WebDriver newdriver) {
		if (driverActivo==driverPlaza) driverPlaza=newdriver;
		else driverHost=newdriver;
	}
	
	public void close() {
		if (driverActivo==driverPlaza) {
			driverPlaza.close();
			numOpenedSessions--;
			if (driverHost!=null) {
				driverActivo=driverHost;
			}
		}
		else {
			driverHost.close();
			numOpenedSessions--;
			if (driverPlaza!=null) {
				driverActivo=driverPlaza;
			}
		}
	}
}
