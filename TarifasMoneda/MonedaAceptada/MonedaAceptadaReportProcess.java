package procesosITATAHost.ConfiguracionSistema.TarifasMoneda.MonedaAceptada;

import org.apache.log4j.Logger;
import procesosComunes.WindowReportProcess;
import ventanasComunes.WindowReportScreen;

public class MonedaAceptadaReportProcess extends WindowReportProcess {

	private final static Logger logger = Logger.getLogger(MonedaAceptadaReportProcess.class);
	 
	public MonedaAceptadaReportProcess() {
		screen=new WindowReportScreen(i_titulo, str_titulo, null, null, i_botones, str_botones,  null, null);
	}
	
	public boolean sintacticAnalysis(String titulo, String[] botones) {
		logger.debug("Moneda aceptada Report: inicio análisis sintáctico");
		return ((WindowReportScreen) screen).sintacticAnalysis(titulo, botones);
	}

}
