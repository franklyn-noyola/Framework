package procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje;

import org.apache.log4j.Logger;
import unidadesGraficas.TipoBy;
import ventanasITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje.CrearModificarReglaScreen;
import procesosComunes.Process;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Motivos.BotonesCrearModificarMotivo;

public class CrearModificarReglaProcess extends Process {
	
	private TipoBy[] i_botones= {TipoBy.ID,TipoBy.ID};
	private String[] str_botones= {"ctl00_ButtonsZone_BtnSubmit_IB_Label","ctl00_ButtonsZone_BtnBack_IB_Label"};
	private final static Logger logger = Logger.getLogger(CrearModificarReglaProcess.class);
	 
	public CrearModificarReglaProcess() {
		screen=new CrearModificarReglaScreen(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,
				i_msgerror,str_msgerror);
	}
	
	public String leerCampoNombre() {
		return ((CrearModificarReglaScreen) screen).leerOpcionVariable(OpcionesCrearModificarReglaVariable.Nombre);
	}
	
	public void clicarCheckConcesionaria() {
		((CrearModificarReglaScreen) screen).clicarCheck(OpcionesCrearModificarReglaCheck.Concesionaria);
	}
	
	public boolean estaChequeadoConcesionaria() {
		return ((CrearModificarReglaScreen) screen).estaChequeado(OpcionesCrearModificarReglaCheck.Concesionaria);
	}
	
	public void clicarCheckPlaza() {
		((CrearModificarReglaScreen) screen).clicarCheck(OpcionesCrearModificarReglaCheck.Plaza);
	}
	
	public boolean estaChequeadoPlaza() {
		return ((CrearModificarReglaScreen) screen).estaChequeado(OpcionesCrearModificarReglaCheck.Plaza);
	}
	
	public void clicarCheckNumVia() {
		((CrearModificarReglaScreen) screen).clicarCheck(OpcionesCrearModificarReglaCheck.NumVia);
	}
	
	public boolean estaChequeadoNumVia() {
		return ((CrearModificarReglaScreen) screen).estaChequeado(OpcionesCrearModificarReglaCheck.NumVia);
	}
	
	public void clicarCheckSentido() {
		((CrearModificarReglaScreen) screen).clicarCheck(OpcionesCrearModificarReglaCheck.Sentido);
	}
	
	public boolean estaChequeadoSentido() {
		return ((CrearModificarReglaScreen) screen).estaChequeado(OpcionesCrearModificarReglaCheck.Sentido);
	}
	
	public void clicarCheckTipoVia() {
		((CrearModificarReglaScreen) screen).clicarCheck(OpcionesCrearModificarReglaCheck.TipoVia);
	}
	
	public boolean estaChequeadoTipoVia() {
		return ((CrearModificarReglaScreen) screen).estaChequeado(OpcionesCrearModificarReglaCheck.TipoVia);
	}
	
	public void seleccionarCampoConcesionaria(String valor) {
		((CrearModificarReglaScreen) screen).seleccionarCriterioDesplegable(OpcionesCrearModificarReglaCheck.Concesionaria, valor);
	}
	
	public void seleccionarCampoPlaza(String valor) {
		 ((CrearModificarReglaScreen) screen).seleccionarCriterioDesplegable(OpcionesCrearModificarReglaCheck.Plaza, valor);
	}
	
	public void seleccionarCampoNumVia(String valor) {
		((CrearModificarReglaScreen) screen).seleccionarCriterioDesplegable(OpcionesCrearModificarReglaCheck.NumVia, valor);
	}
	
	public void seleccionarCampoSentido(String valor) {
		 ((CrearModificarReglaScreen) screen).seleccionarCriterioDesplegable(OpcionesCrearModificarReglaCheck.Sentido, valor);
	}
	
	public void seleccionarCampoTipoVia(String valor) {
		 ((CrearModificarReglaScreen) screen).seleccionarCriterioDesplegable(OpcionesCrearModificarReglaCheck.TipoVia, valor);
	}
	
	public String leerCampoValor() {
		return ((CrearModificarReglaScreen) screen).leerOpcion(OpcionesCrearModificarReglaCampo.Valor);
	}
	
	public void escribirCampoValor(String valor) {
		borrarCampoValor();
		((CrearModificarReglaScreen) screen).escribirOpcion(OpcionesCrearModificarReglaCampo.Valor, valor);
	}
	
	public void borrarCampoValor() {
		((CrearModificarReglaScreen) screen).borrarOpcion(OpcionesCrearModificarReglaCampo.Valor);
	}
	
	public void confirmar() {
		screen.clickBoton(BotonesCrearModificarMotivo.Confirmar.ordinal());
	}

	public void volver() {
		screen.clickBoton(BotonesCrearModificarMotivo.Volver.ordinal());
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones,String encabezado_var, String[] labels_var,
			String encabezado_check, String[] labels_check, String encabezado_crud, String[] labels_crud) {
		logger.debug("CrearModificar Regla: inicio análisis sintáctico");
		return ((CrearModificarReglaScreen) screen).sintacticAnalysis(titulo, subtitulo, botones, encabezado_var, labels_var,
				encabezado_check, labels_check, encabezado_crud, labels_crud);
	}

}
