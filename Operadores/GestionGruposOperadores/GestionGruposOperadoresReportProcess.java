package procesosITATAHost.ConfiguracionSistema.Operadores.GestionGruposOperadores;

import org.apache.log4j.Logger;
import procesosComunes.WindowReportProcess;
import ventanasComunes.WindowReportScreen;

public class GestionGruposOperadoresReportProcess extends WindowReportProcess {

	private final static Logger logger = Logger.getLogger(GestionGruposOperadoresReportProcess.class);
	 
	public GestionGruposOperadoresReportProcess() {
		screen=new WindowReportScreen(i_titulo, str_titulo, null, null, i_botones, str_botones,  null, null);
	}
	
	public boolean sintacticAnalysis(String titulo, String[] botones) {
		logger.debug("Gestión Grupos Operadores Report: inicio análisis sintáctico");
		return ((WindowReportScreen) screen).sintacticAnalysis(titulo, botones);
	}

}
