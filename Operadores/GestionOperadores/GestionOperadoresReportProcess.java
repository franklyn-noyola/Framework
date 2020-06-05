package procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores;

import org.apache.log4j.Logger;
import procesosComunes.WindowReportProcess;
import ventanasComunes.WindowReportScreen;

public class GestionOperadoresReportProcess extends WindowReportProcess {

	private final static Logger logger = Logger.getLogger(GestionOperadoresReportProcess.class);
	 
	public GestionOperadoresReportProcess() {
		screen=new WindowReportScreen(i_titulo, str_titulo, null, null, i_botones, str_botones,  null, null);
	}
	
	public boolean sintacticAnalysis(String titulo, String[] botones) {
		logger.debug("Gesti�n Operadores Report: inicio an�lisis sint�ctico");
		return ((WindowReportScreen) screen).sintacticAnalysis(titulo, botones);
	}

}
