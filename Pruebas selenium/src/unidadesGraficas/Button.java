package unidadesGraficas;

import org.apache.log4j.Logger;

public class Button extends Element {
	
	private final static Logger logger = Logger.getLogger(Button.class);
	
	public Button(TipoBy i, String str) {
		super(i,str);
	}
	
	public void click() {
		try {
			webElement().click();
		}
		catch (Exception e) {
			logger.fatal("Error al pulsar un botón");
			System.exit(0);
		}
	}
	
	public boolean click(String paginaIni) {
		try {
			webElement().click();
			if (driver().getPageSource().contains(paginaIni)) {
				return false;
			}
			else {
				return true;
			}
		}
		catch (Exception e) {
			logger.fatal("Error al pulsar un botón");
			System.exit(0);
			return false;
		}
	}
	
	public String getText() {
		try {
			return webElement().getText();
		}
		catch (Exception e) {
			logger.fatal("Error al obtener texto de un botón");
			return null;
		}
	}
	
	public String getValue() {
		try {
			return webElement().getAttribute("value");
		}
		catch (Exception e) {
			logger.fatal("Error al obtener el atributo value de un botón");
			return null;
		}
	}
	
	public boolean habilitado() {
		try {
			return(webElement().getAttribute("disabled")==null);
		}
		catch (Exception e) {
			logger.fatal("Error al obtener el atributo disabled de un botón");
			return false;
		}
	}
	
	public boolean sintacticAnalysis(String label) {
		boolean resultado=this.getText().equals(label);
		String s=this.getValue();
		if (s!=null) {
			resultado=s.equals(label) || resultado;
		}
		if (!resultado) {
			logger.error("Error sintáctico en Button: se esperaba '"+label+"' y se ha encontrado '"+this.getText()+"'");
		}
		return resultado;
	}
}
