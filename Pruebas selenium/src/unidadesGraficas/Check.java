package unidadesGraficas;

import org.apache.log4j.Logger;

public class Check extends Element {

	private final static Logger logger = Logger.getLogger(Check.class);
	
	public Check(TipoBy i, String str) {
		super(i,str);
	}
	
	public boolean isChecked() {
		try {
			String checked=webElement().getAttribute("checked");
			if (checked!=null) {
				return checked.equals("true");
			}
			else {
				return false;
			}
		}
		catch (Exception e) {
			logger.fatal("Error al comprobar si un check está chequeado");
			return false;
		}
	}
	
	public void click() {
		try {
			super.driver().findElement(super.by()).click();
		}
		catch (Exception e) {
			logger.fatal("Error al pulsar un check");
			System.exit(0);
		}
	}
		
	public String getText() {
		try {
			return super.driver().findElement(super.by()).getText();
		}
		catch (Exception e) {
			logger.fatal("Error al obtener texto de un check");
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
}
