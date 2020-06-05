package unidadesGraficas;

import org.apache.log4j.Logger;

public class Field extends Element {
	
	private final static Logger logger = Logger.getLogger(Field.class);
	
	public Field(TipoBy i, String str) {
		super(i,str);
	}
	
	public void writeText(String str) {
		try {
			webElement().sendKeys(str);
		}
		catch (Exception e) {
			logger.fatal("Error al escribir en un campo");
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
