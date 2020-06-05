package procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias;

import org.apache.log4j.Logger;
import procesosComunes.Error;
import unidadesGraficas.TipoBy;
import ventanasITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.CrearModificarPlazaScreen;
import procesosComunes.Process;

public class CrearModificarPlazaProcess extends Process {
	
	private TipoBy[] i_botones= {TipoBy.ID,TipoBy.ID};
	private String[] str_botones= {"ctl00_ButtonsZone_BtnSubmit_IB_Label","ctl00_ButtonsZone_BtnBack_IB_Label"};
	private final static Logger logger = Logger.getLogger(CrearModificarPlazaProcess.class);
	private String str_msgerror="ctl00_ContentZone_lblMsg"; //Esto es particular de esta funcionalidad
	 
	public CrearModificarPlazaProcess() {
		screen=new CrearModificarPlazaScreen(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,
				i_msgerror,str_msgerror);
	}
	
	// Primer bloque
	
	public String leerCampoIDConcesionaria() {
		return ((CrearModificarPlazaScreen) screen).leerOpcionVariable(OpcionesCrearModificarPlazaVariable.ID);
	}
	
	public String leerCampoNombreConcesionaria() {
		return ((CrearModificarPlazaScreen) screen).leerOpcionVariable(OpcionesCrearModificarPlazaVariable.Nombre);
	}
	
	// Segundo bloque
	
	public String leerCampoCodigoPlaza() {
		return ((CrearModificarPlazaScreen) screen).leerOpcion(OpcionesCrearModificarPlazaCampo.Codigo);
	}
	
	public void escribirCampoCodigoPlaza(String valor) {
		borrarCampoNombrePlaza();
		((CrearModificarPlazaScreen) screen).escribirOpcion(OpcionesCrearModificarPlazaCampo.Codigo, valor);
	}
	
	public void borrarCampoCodigoPlaza() {
		((CrearModificarPlazaScreen) screen).borrarOpcion(OpcionesCrearModificarPlazaCampo.Codigo);
	}
	
	public String leerCampoNombrePlaza() {
		return ((CrearModificarPlazaScreen) screen).leerOpcion(OpcionesCrearModificarPlazaCampo.Nombre);
	}
	
	public void escribirCampoNombrePlaza(String valor) {
		borrarCampoNombrePlaza();
		((CrearModificarPlazaScreen) screen).escribirOpcion(OpcionesCrearModificarPlazaCampo.Nombre, valor);
	}
	
	public void borrarCampoNombrePlaza() {
		((CrearModificarPlazaScreen) screen).borrarOpcion(OpcionesCrearModificarPlazaCampo.Nombre);
	}
	
	public String leerCampoCarretera() {
		return ((CrearModificarPlazaScreen) screen).leerOpcion(OpcionesCrearModificarPlazaCampo.Carretera);
	}
	
	public void escribirCampoCarretera(String valor) {
		borrarCampoCarretera();
		((CrearModificarPlazaScreen) screen).escribirOpcion(OpcionesCrearModificarPlazaCampo.Carretera, valor);
	}
	
	public void borrarCampoCarretera() {
		((CrearModificarPlazaScreen) screen).borrarOpcion(OpcionesCrearModificarPlazaCampo.Carretera);
	}
	
	public String leerCampoKm() {
		return ((CrearModificarPlazaScreen) screen).leerOpcion(OpcionesCrearModificarPlazaCampo.Km);
	}
	
	public void escribirCampoKm(String valor) {
		borrarCampoKm();
		((CrearModificarPlazaScreen) screen).escribirOpcion(OpcionesCrearModificarPlazaCampo.Km, valor);
	}
	
	public void borrarCampoKm() {
		((CrearModificarPlazaScreen) screen).borrarOpcion(OpcionesCrearModificarPlazaCampo.Km);
	}
	
	public boolean hayErrorValidacionRequeridoCodigo() {
		return ((CrearModificarPlazaScreen) screen).hayErrorValidacionOpcion(OpcionesCrearModificarPlazaCampo.Codigo);
	}
	
	public boolean hayErrorValidacionFormatoCodigo() {
		return ((CrearModificarPlazaScreen) screen).hayErrorValidacionOpcion(OpcionesCrearModificarPlazaCampo.Codigo);
	}
	
	public boolean hayErrorValidacionRequeridoNombre() {
		return ((CrearModificarPlazaScreen) screen).hayErrorValidacionOpcion(OpcionesCrearModificarPlazaCampo.Nombre);
	}
	
	// Tercer bloque
	
	public void clicarNodoExistente() {
		((CrearModificarPlazaScreen) screen).clicarCriterioDesplegable(OpcionesCrearModificarPlazaCheckDropdown.NodoExistente);
	}
	
	public String leerCampoNodoExistente() {
		return ((CrearModificarPlazaScreen) screen).leerCriterioDesplegable(OpcionesCrearModificarPlazaCheckDropdown.NodoExistente);
	}
	
	public Error seleccionarCampoNodoExistente(String valor) {
		return ((CrearModificarPlazaScreen) screen).seleccionarCriterioDesplegable(OpcionesCrearModificarPlazaCheckDropdown.NodoExistente, valor);
	}
	
	public void clicarNuevoNodo() {
		((CrearModificarPlazaScreen) screen).clicarOpcionCheckField(OpcionesCrearModificarPlazaCheckField.NuevoNodo);
	}
	
	public String leerCampoNuevoNodo() {
		return ((CrearModificarPlazaScreen) screen).leerOpcionCheckField(OpcionesCrearModificarPlazaCheckField.NuevoNodo);
	}
	
	public void escribirCampoNuevoNodo(String valor) {
		borrarCampoNuevoNodo();
		((CrearModificarPlazaScreen) screen).escribirOpcionCheckField(OpcionesCrearModificarPlazaCheckField.NuevoNodo, valor);
	}
	
	public void borrarCampoNuevoNodo() {
		((CrearModificarPlazaScreen) screen).borrarOpcionCheckField(OpcionesCrearModificarPlazaCheckField.NuevoNodo);
	}
	
	public boolean hayErrorValidacionNuevoNodo() {
		return ((CrearModificarPlazaScreen) screen).hayErrorValidacionOpcionCheckField(OpcionesCrearModificarPlazaCheckField.NuevoNodo);
	}
	
	public boolean hayErrorValidacionFormatoNuevoNodo() {
		return ((CrearModificarPlazaScreen) screen).hayErrorValidacionOpcionCheckFieldFormato(OpcionesCrearModificarPlazaCheckField.NuevoNodo);
	}
	
	public boolean hayErrorValidacionRequeridoNuevoNodo() {
		return ((CrearModificarPlazaScreen) screen).hayErrorValidacionOpcionCheckFieldRequerido(OpcionesCrearModificarPlazaCheckField.NuevoNodo);
	}
	
	public String leerCampoDireccion() {
		return ((CrearModificarPlazaScreen) screen).leerOpcionCheck(OpcionesCrearModificarPlazaCheck.Direccion);
	}
	
	public void escribirCampoDireccion(String valor) {
		borrarCampoDireccion();
		((CrearModificarPlazaScreen) screen).escribirOpcionCheck(OpcionesCrearModificarPlazaCheck.Direccion, valor);
	}
	
	public void borrarCampoDireccion() {
		((CrearModificarPlazaScreen) screen).borrarOpcionCheck(OpcionesCrearModificarPlazaCheck.Direccion);
	}
	
	public boolean hayErrorValidacionDireccion() {
		return ((CrearModificarPlazaScreen) screen).hayErrorValidacionOpcionCheck(OpcionesCrearModificarPlazaCheck.Direccion);
	}
	
	public boolean hayErrorValidacionRequeridoDireccion() {
		return ((CrearModificarPlazaScreen) screen).hayErrorValidacionOpcionCheckRequerido(OpcionesCrearModificarPlazaCheck.Direccion);
	}
	
	public boolean hayErrorValidacionFormatoDireccion() {
		return ((CrearModificarPlazaScreen) screen).hayErrorValidacionOpcionCheckFormato(OpcionesCrearModificarPlazaCheck.Direccion);
	}
	
	public void confirmar() {
		screen.clickBoton(BotonesCrearModificarPlaza.Confirmar.ordinal());
	}

	public void volver() {
		screen.clickBoton(BotonesCrearModificarPlaza.Volver.ordinal());
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String encabezadovar, 
			String[] labelsvar,String[] valoresvar, String encabezado_crud, String[] labels_crud,
			String encabezado_check, String[] labels_check, String[] labels_checkdropdown, String[] labels_checkfield) {
		logger.debug("Crear/Modificar Plaza: inicio análisis sintáctico");
		return ((CrearModificarPlazaScreen) screen).sintacticAnalysis(titulo, subtitulo, botones, encabezadovar,
				labelsvar, valoresvar, encabezado_crud, labels_crud, encabezado_check, labels_check, labels_checkdropdown,
				labels_checkfield);
	}

}
