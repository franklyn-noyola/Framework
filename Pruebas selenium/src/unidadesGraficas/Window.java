package unidadesGraficas;

import java.util.Iterator;
import org.apache.log4j.Logger;

public class Window {
	
	String idparent;
	private final static Logger logger = Logger.getLogger(Window.class);
	
	public void pasarANuevaVentana() {
		try {
			Iterator<String> parent=SeleniumSession.driver().getWindowHandles().iterator();
			idparent=parent.next();  
			String child=parent.next();
			SeleniumSession.setDriver(SeleniumSession.driver().switchTo().window(child));
		}
		catch (Exception e) {
			logger.fatal("Error al pasar a ventana");
			System.exit(0);
		}
	}
	
	public void volverAVentanaAnterior() {
		SeleniumSession.driver().close();
		SeleniumSession.setDriver(SeleniumSession.driver().switchTo().window(idparent));
	}

}
