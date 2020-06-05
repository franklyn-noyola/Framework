package procesosITATAHost.ConfiguracionSistema.GestionClases.Categorias;

import org.apache.log4j.Logger;
import procesosComunes.WindowReportProcess;
import ventanasComunes.WindowReportScreen;

public class CategoriasReportProcess extends WindowReportProcess {

	private final static Logger logger = Logger.getLogger(CategoriasReportProcess.class);
	 
	public CategoriasReportProcess() {
		screen=new WindowReportScreen(i_titulo, str_titulo, null, null, i_botones, str_botones,  null, null);
	}
	
	public boolean sintacticAnalysis(String titulo, String[] botones) {
		logger.debug("Categorías Report: inicio análisis sintáctico");
		return ((WindowReportScreen) screen).sintacticAnalysis(titulo, botones);
	}

}
