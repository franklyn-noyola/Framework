package unidadesGraficas;

import org.apache.log4j.Logger;

public class RadioButton extends Element{

	private final static Logger logger = Logger.getLogger(RadioButton.class);
	
	public RadioButton(TipoBy i, String str) {
		super(i,str);
	}
	
	public void click() {
		try {
			webElement().click();
		}
		catch (Exception e) {
			logger.fatal("Error al pulsar un radio button");
			System.exit(0);
		}
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
			logger.fatal("Error al comprobar si un radio button está chequeado");
			return false;
		}
	}
	
	public String getValue() {
		try {
			String s=webElement().getAttribute("value");
			return s;
		}
		catch (Exception e) {
			logger.fatal("Error al obtener el atributo value de un radio button");
			System.exit(0);
			return null;
		}
	}
	
	public String getText() {
		try {
			return webElement().getText();
		}
		catch (Exception e) {
			logger.fatal("Error al obtener texto de un radio button");
			System.exit(0);
			return null;
		}
	}
}
