package procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Motivos;

import org.apache.log4j.Logger;
import procesosComunes.WindowReportProcess;
import ventanasComunes.WindowReportScreen;

public class MotivosReportProcess extends WindowReportProcess {

	private final static Logger logger = Logger.getLogger(MotivosReportProcess.class);
	 
	public MotivosReportProcess() {
		screen=new WindowReportScreen(i_titulo, str_titulo, null, null, i_botones, str_botones,  null, null);
	}
	
	public boolean sintacticAnalysis(String titulo, String[] botones) {
		logger.debug("Motivos Report: inicio análisis sintáctico");
		return ((WindowReportScreen) screen).sintacticAnalysis(titulo, botones);
	}

}
