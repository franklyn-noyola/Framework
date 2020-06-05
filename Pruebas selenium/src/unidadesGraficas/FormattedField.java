package unidadesGraficas;

import org.apache.log4j.Logger;
import org.openqa.selenium.interactions.Actions;

public class FormattedField extends Element {
	
	private final static Logger logger = Logger.getLogger(FormattedField.class);
	
	public FormattedField(TipoBy i, String str) {
		super(i,str);
	}
	
	public void writeText(String str) {
		try {
			Actions action=new Actions(driver());
			action.sendKeys(str).build().perform();
		}
		catch (Exception e) {
			logger.fatal("Error al escribir en un campo formateado");
			logger.fatal(e.getMessage());
			System.exit(0);
		}
	}
	
	public String getText() {
		try {
			return webElement().getText();
		}
		catch (Exception e) {
			logger.fatal("Error al obtener texto de un campo");
			logger.fatal(e.getMessage());
			return null;
		}
	}
	
	public void clear() {
		try {
			 webElement().clear();
		}
		catch (Exception e) {
			logger.fatal("Error al borrar el texto de un campo");
			logger.fatal(e.getMessage());
		}
	}
	
	public String getValue() {
		try {
			return webElement().getAttribute("value");
		}
		catch (Exception e) {
			logger.fatal("Error al obtener el atributo value de un campo");
			logger.fatal(e.getMessage());
			return null;
		}
	}
	
}
