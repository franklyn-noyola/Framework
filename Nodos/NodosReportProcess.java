package procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos;

import org.apache.log4j.Logger;
import procesosComunes.WindowReportProcess;
import ventanasComunes.WindowReportScreen;

public class NodosReportProcess extends WindowReportProcess {

	private final static Logger logger = Logger.getLogger(NodosReportProcess.class);
	 
	public NodosReportProcess() {
		screen=new WindowReportScreen(i_titulo, str_titulo, null, null, i_botones, str_botones,  null, null);
	}
	
	public boolean sintacticAnalysis(String titulo, String[] botones) {
		logger.debug("Nodos Report: inicio análisis sintáctico");
		return ((WindowReportScreen) screen).sintacticAnalysis(titulo, botones);
	}

}
