package unidadesGraficas;

import org.apache.log4j.Logger;

public class LabelVariable extends Element {

	private final static Logger logger = Logger.getLogger(LabelVariable.class);
	
	public LabelVariable (TipoBy i, String str) {
			super(i,str);
	}
	
	public String getText() {
		try {
			return webElement().getText();
		}
		catch (Exception e) {
			logger.fatal("Error al obtener texto de un label variable");
			return null;
		}
	}
	
	public String getValue() {
		try {
			return webElement().getAttribute("value");
		}
		catch (Exception e) {
			logger.fatal("Error al obtener el atributo value de un label variable");
			return null;
		}
	}
	
	public boolean sintacticAnalysis(String label) {
		String texto=this.getText();
		if (texto==null) {
			texto=this.getValue();
		}
		boolean resultado=texto.equals(label);
		if (!resultado && texto.length()==0) {
			texto=this.getValue();
			resultado=texto.equals(label);
		}
		if (!resultado) {;
			logger.error("Error sintáctico en LabelVariable: se esperaba '"+label+"' y se ha encontrado '"+texto+"'");
		}
		return resultado;
	}

}
