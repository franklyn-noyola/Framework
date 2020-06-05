package unidadesGraficas;

import org.apache.log4j.Logger;

public class Title extends Element {

	private final static Logger logger = Logger.getLogger(Title.class);
	
	public Title(TipoBy i, String str) {
		super(i,str);
	}
	
	public String getText() {
		try {
			return webElement().getText();
		}
		catch (Exception e) {
			logger.fatal("Error al obtener texto de un Title");
			System.exit(0);
			return null;
		}
	}
	
	public boolean sintacticAnalysis(String label) {
		boolean resultado=this.getText().equals(label);
		if (!resultado) {
			logger.error("Error sintáctico en Title: se esperaba '"+label+"' y se ha encontrado '"+this.getText()+"'");
		}
		return resultado;
	}
}
