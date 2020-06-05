package procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias;

import org.apache.log4j.Logger;
import procesosComunes.WindowReportProcess;
import ventanasComunes.WindowReportScreen;

public class ConcesionariasReportProcess extends WindowReportProcess {

	private final static Logger logger = Logger.getLogger(ConcesionariasReportProcess.class);
	 
	public ConcesionariasReportProcess() {
		screen=new WindowReportScreen(i_titulo, str_titulo, null, null, i_botones, str_botones,  null, null);
	}
	
	public boolean sintacticAnalysis(String titulo, String[] botones) {
		logger.debug("Concesionariass Report: inicio análisis sintáctico");
		return ((WindowReportScreen) screen).sintacticAnalysis(titulo, botones);
	}

}
