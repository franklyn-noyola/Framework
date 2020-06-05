package unidadesGraficas;

import org.apache.log4j.Logger;

public class Tooltip extends Element {

	private final static Logger logger = Logger.getLogger(Tooltip.class);
	
	public Tooltip(TipoBy i, String str) {
		super(i,str);
	}
	
	public String getText() {
		try {
			return webElement().getText();
		} catch (Exception e) {
			logger.fatal("Error al obtener el texto de un tooltip");
			System.exit(0);
			return null;
		}
	}
	
	public String getTitle() {
		try {
			return webElement().getAttribute("title");
		}
		catch (Exception e) {
			logger.fatal("Error al obtener el atributo title de un tooltip");
			System.exit(0);
			return null;
		}
	}
	
	public boolean hayMensajeError() {
		try {
			return webElement().isDisplayed();
		} catch (Exception e) {
			System.exit(0);
			return false;
		}
	}
	
}
