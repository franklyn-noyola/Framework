package procesosITATAHost.ConfiguracionSistema.GestionClases.MapeoDACcat;

import org.apache.log4j.Logger;
import procesosComunes.WindowReportProcess;
import ventanasComunes.WindowReportScreen;

public class MapeoDACcatReportProcess extends WindowReportProcess {

	private final static Logger logger = Logger.getLogger(MapeoDACcatReportProcess.class);
	 
	public MapeoDACcatReportProcess() {
		screen=new WindowReportScreen(i_titulo, str_titulo, null, null, i_botones, str_botones,  null, null);
	}
	
	public boolean sintacticAnalysis(String titulo, String[] botones) {
		logger.debug("Mapeo DAC-cat Report: inicio análisis sintáctico");
		return ((WindowReportScreen) screen).sintacticAnalysis(titulo, botones);
	}

}
