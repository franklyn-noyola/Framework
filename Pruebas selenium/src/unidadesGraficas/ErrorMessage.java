package unidadesGraficas;

import org.apache.log4j.Logger;

public class ErrorMessage extends Element {

	private final static Logger logger = Logger.getLogger(ErrorMessage.class);
	
	public ErrorMessage(TipoBy i, String str) {
		super(i,str);
	}
	
	public String getText() {
		try {
			return webElement().getText();
		} catch (Exception e) {
			System.exit(0);
			return null;
		}
	}
	
	public boolean hayMensajeError() {
		boolean hayMsg;
		try {
			hayMsg=webElement().isDisplayed();
		}
		catch (Exception e) {
			hayMsg=false;
		}
		return hayMsg;
	}
	
	public boolean sintacticAnalysis(String label) {
		boolean resultado=this.getText().equals(label);
		if (!resultado) {;
			logger.error("Error sintáctico en ErrorMessage: se esperaba '"+label+"' y se ha encontrado '"+this.getText()+"'");
		}
		return resultado;
	}
}
