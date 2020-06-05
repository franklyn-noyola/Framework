package procesosITATAHost.ConfiguracionSistema.TarifasMoneda.Calendario;

import org.apache.log4j.Logger;
import procesosComunes.WindowReportProcess;
import ventanasComunes.WindowReportScreen;

public class CalendarioReportProcess extends WindowReportProcess {

	private final static Logger logger = Logger.getLogger(CalendarioReportProcess.class);
	 
	public CalendarioReportProcess() {
		screen=new WindowReportScreen(i_titulo, str_titulo, null, null, i_botones, str_botones,  null, null);
	}
	
	public boolean sintacticAnalysis(String titulo, String[] botones) {
		logger.debug("Calendario Report: inicio análisis sintáctico");
		return ((WindowReportScreen) screen).sintacticAnalysis(titulo, botones);
	}

}
