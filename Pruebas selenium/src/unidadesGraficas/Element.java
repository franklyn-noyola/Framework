package unidadesGraficas;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class Element {

	private By by=null;
	private static boolean error = false;
	private final static Logger logger = Logger.getLogger(Element.class);
	
	public Element(TipoBy i, String str) {
		if (i!=null && str!=null) {
		switch (i) {
			case ID:
				by=By.id(str);
				break;
			case CLASSNAME:
				by=By.className(str);
				break;
			case CSS:
				by=By.cssSelector(str);
				break;
			case LINK:
				by=By.linkText(str);
				break;
			case PARTIALLINK:
				by=By.partialLinkText(str);
				break;
			case TAGNAME:
				by=By.tagName(str);
				break;
			case XPATH:
				by=By.xpath(str);
				break;
			default:
				setError(true);
		}
		}	
	}
	
	public By by() {
		return by;
	}
	
	public WebElement webElement() throws Exception {
		try {
			return driver().findElement(by());
		}
		catch (Exception e) {
			logger.fatal("Error al obtener el webElement");
			//logger.fatal(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public List<WebElement> webElements() throws Exception {
		try {
			return driver().findElements(by());
		}
		catch (Exception e) {
			logger.fatal("Error al obtener el webElement");
			//logger.fatal(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public WebDriver driver() {
		try {
			return SeleniumSession.driver();
		}
		catch (Exception e) {
			logger.fatal("Error al obtener el driver");
			logger.fatal(e.getMessage());
			System.exit(0);
			return null;
		}
	}
	
	public void setError(boolean error) {
		this.error=error;
	}
	
	public boolean error() {
		return error;
	}
}
