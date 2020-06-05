package unidadesGraficas;

import org.apache.log4j.Logger;

public class Label extends Element {

	private final static Logger logger = Logger.getLogger(Label.class);
	
	public Label (TipoBy i, String str) {
			super(i,str);
	}
	
	public String getText() {
		try {
			return webElement().getText();
		}
		catch (Exception e) {
			logger.fatal("Error al obtener texto de un label");
			return null;
		}
	}
	
	public String getValue() {
		try {
			return webElement().getAttribute("value");
		}
		catch (Exception e) {
			logger.fatal("Error al obtener el atributo value de un label");
			return null;
		}
	}
	
	public boolean sintacticAnalysis(String label) {
		boolean resultado=this.getText().equals(label);
		if (!resultado) {;
			logger.error("Error sintáctico en Label: se esperaba '"+label+"' y se ha encontrado '"+this.getText()+"'");
		}
		return resultado;
	}
	
	public boolean sintacticAnalysisValue(String label) {
		boolean resultado=this.getValue().equals(label);
		if (!resultado) {;
			logger.error("Error sintáctico en Label: se esperaba '"+label+"' y se ha encontrado '"+this.getValue()+"'");
		}
		return resultado;
	}
}
