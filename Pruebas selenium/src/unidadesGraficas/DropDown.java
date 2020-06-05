package unidadesGraficas;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.Select;
import procesosComunes.Error;

public class DropDown extends Element {
	
	private final static Logger logger = Logger.getLogger(DropDown.class);
	
	public DropDown (TipoBy i, String str) {
		super(i,str);
	}
	
	public String getText() {
		try {
			return webElement().getText();
		}
		catch (Exception e) {
			logger.fatal("Error al obtener texto de un dropdown");
			return null;
		}
	}

	public String getFirst() {
		Select comboBox;
		try {
			comboBox = new Select(webElement());
		} catch (Exception e) {
			logger.fatal("Error al seleccionar el primer elemento de un dropdown");
			return null;
		}
		return comboBox.getFirstSelectedOption().getText();
	}
	
	public Error seleccionarOpcion(String opcion) {
		Select selectBox;
		try {
			selectBox = new Select(webElement());
		    selectBox.selectByVisibleText(opcion);
			return Error.OK;
		} catch (Exception e) {
			logger.fatal("Error al seleccionar opción de un dropdown. No existe la opción '"+opcion+"'");
			return Error.Error;
		}
	}
}
