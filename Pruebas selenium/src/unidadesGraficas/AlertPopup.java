package unidadesGraficas;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;

public class AlertPopup {
	
	private Alert alert;
	private final static Logger logger = Logger.getLogger(AlertPopup.class);
	
	
	public String getText() {
		try {
			return alert.getText();
		}
		catch (Exception e) {
			logger.fatal("Error al obtener texto de un alert");
			logger.fatal(e.getMessage());
			return null;
		}
	}
	
	public void aceptar() {
		try {
			alert.accept();
		}
		catch (Exception e) {
			logger.fatal("Error al aceptar un alert");
			logger.fatal(e.getMessage());
			System.exit(0);
		}
	}
	
	public void cancelar() {
		try {
			alert.dismiss();
		}
		catch (Exception e) {
			logger.fatal("Error al cancelar un alert");
			logger.fatal(e.getMessage());
			System.exit(0);
		}
	}
	
	public boolean hayAlerta() {
	    try {
	    	alert=SeleniumSession.driver().switchTo().alert();
	        return true;
	    }
	    catch (Exception e) {
	    	logger.fatal("Error en hayAlerta()");
			logger.fatal(e.getMessage());
	        return false;
	    }
	}
	
	public boolean sintacticAnalysis(String label) {
		boolean resultado=this.getText().equals(label);
		if (!resultado) {
			logger.error("Error sintáctico en AlertPopup: se esperaba '"+label+"' y se ha encontrado '"+this.getText()+"'");
		}
		return resultado;
	}
}
