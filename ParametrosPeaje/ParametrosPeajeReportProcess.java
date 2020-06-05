package procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje;

import org.apache.log4j.Logger;
import procesosComunes.WindowReportProcess;
import ventanasComunes.WindowReportScreen;

public class ParametrosPeajeReportProcess extends WindowReportProcess {

	private final static Logger logger = Logger.getLogger(ParametrosPeajeReportProcess.class);
	 
	public ParametrosPeajeReportProcess() {
		screen=new WindowReportScreen(i_titulo, str_titulo, null, null, i_botones, str_botones,  null, null);
	}
	
	public boolean sintacticAnalysis(String titulo, String[] botones) {
		logger.debug("Parametros Peaje Report: inicio análisis sintáctico");
		return ((WindowReportScreen) screen).sintacticAnalysis(titulo, botones);
	}

}
