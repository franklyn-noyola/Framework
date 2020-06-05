package unidadesGraficas;

import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class ListaScroll extends Element {
	
	private final static Logger logger = Logger.getLogger(ListaScroll.class);
	
	public ListaScroll (TipoBy i, String str) {
		super(i,str);
	}

	public void seleccionarOpcion(String opcion) {
		try {
			Select selectBox = new Select(webElement());
			selectBox.selectByVisibleText(opcion);
		}
		catch (Exception e) {
			logger.fatal("Error al seleccionar opción por texto en un ListScroll");
			System.exit(0);
		}
	}
	
	public void seleccionarOpcion(int indice) {
		try {
			Select selectBox = new Select(webElement());
			selectBox.selectByIndex(indice);
		}
		catch (Exception e) {
			logger.fatal("Error al seleccionar opción por índice en un ListScroll");
			System.exit(0);
		}
	}
	
	public boolean existeOpcionEnTabla(String opcion) {
		Select selectBox=null;;
		try {
			selectBox = new Select(webElement());
		} catch (Exception e) {
			System.exit(0);
		}
		List<WebElement> lista=selectBox.getOptions();
		Iterator<WebElement> it=lista.iterator();
		boolean encontrado=false;
		while (it.hasNext() && !encontrado) {
			encontrado=it.next().getText().equals(opcion);
		}
		return encontrado;
	}
}
