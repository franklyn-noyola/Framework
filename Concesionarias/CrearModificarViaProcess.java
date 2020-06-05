package procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias;

import org.apache.log4j.Logger;
import procesosComunes.Error;
import unidadesGraficas.TipoBy;
import ventanasITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.CrearModificarPlazaScreen;
import ventanasITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.CrearModificarViaScreen;
import procesosComunes.Process;

public class CrearModificarViaProcess extends Process {
	
	private TipoBy[] i_botones= {TipoBy.ID,TipoBy.ID};
	private String[] str_botones= {"ctl00_ButtonsZone_BtnSubmit_IB_Label","ctl00_ButtonsZone_BtnBack_IB_Label"};
	private final static Logger logger = Logger.getLogger(CrearModificarViaProcess.class);
	private String str_msgerror="ctl00_ContentZone_lblMsg"; //Esto es particular de esta funcionalidad
	 
	public CrearModificarViaProcess() {
		screen=new CrearModificarViaScreen(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,
				i_msgerror,str_msgerror);
	}
	
	// Primer bloque
	
	public String leerCampoIDConcesionaria() {
		return ((CrearModificarViaScreen) screen).leerOpcionVariable1(OpcionesCrearModificarViaVariable1.ID);
	}
	
	public String leerCampoNombreConcesionaria() {
		return ((CrearModificarViaScreen) screen).leerOpcionVariable1(OpcionesCrearModificarViaVariable1.Nombre);
	}
	
	// Segundo bloque
	
	public String leerCampoCodigoPlaza() {
		return ((CrearModificarViaScreen) screen).leerOpcionVariable2(OpcionesCrearModificarViaVariable2.Codigo);
	}
	
	public String leerCampoNombrePlaza() {
		return ((CrearModificarViaScreen) screen).leerOpcionVariable2(OpcionesCrearModificarViaVariable2.Nombre);
	}

	public String leerCampoNodoPlaza() {
		return ((CrearModificarViaScreen) screen).leerOpcionVariable2(OpcionesCrearModificarViaVariable2.NodoPlaza);
	}
	
	// Tercer bloque
	
	public String leerCampoNumVia() {
		return ((CrearModificarViaScreen) screen).leerOpcion(OpcionesCrearModificarViaCampo.NumVia);
	}
	
	public void escribirCampoNumVia(String valor) {
		borrarCampoNumVia();
		((CrearModificarViaScreen) screen).escribirOpcion(OpcionesCrearModificarViaCampo.NumVia, valor);
	}
	
	public void borrarCampoNumVia() {
		((CrearModificarViaScreen) screen).borrarOpcion(OpcionesCrearModificarViaCampo.NumVia);
	}
	
	public String leerCampoSentido() {
		return ((CrearModificarViaScreen) screen).leerOpcionDesplegable(OpcionesCrearModificarViaDesplegable.Sentido);
	}
	
	public void seleccionarCampoSentido(String valor) {
		((CrearModificarViaScreen) screen).seleccionarOpcion(OpcionesCrearModificarViaDesplegable.Sentido, valor);
	}
	
	public String leerCampoTipoVia() {
		return ((CrearModificarViaScreen) screen).leerOpcionDesplegable(OpcionesCrearModificarViaDesplegable.Sentido);
	}
	
	public void seleccionarCampoTipoVia(String valor) {
		((CrearModificarViaScreen) screen).seleccionarOpcion(OpcionesCrearModificarViaDesplegable.TipoVia, valor);
	}
	
	public boolean hayErrorValidacionFormatoNumVia() {
		return ((CrearModificarViaScreen) screen).hayErrorValidacionOpcionFormato(OpcionesCrearModificarViaCampo.NumVia);
	}
	
	public boolean hayErrorValidacionRequeridoNumVia() {
		return ((CrearModificarViaScreen) screen).hayErrorValidacionOpcionRequerido(OpcionesCrearModificarViaCampo.NumVia);
	}
	
	// Cuarto bloque
	
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
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String encabezado1, String[] labelsvar1,String[] valoresvar1, 
			String encabezado2, String[] labelsvar2, String[] valoresvar2, String encabezado_crud, String[] labels_crud,
			String encabezado4, String[] labelCriteriosBusqueda, String[] labelCriteriosCheckDropdown, String[] labelCriteriosCheckField) {
		logger.debug("Crear/Modificar Via: inicio análisis sintáctico");
		return ((CrearModificarViaScreen) screen).sintacticAnalysis(titulo, subtitulo, botones, encabezado1, labelsvar1, valoresvar1, encabezado2,
				labelsvar2, valoresvar2, encabezado_crud, labels_crud, encabezado4, labelCriteriosBusqueda, labelCriteriosCheckDropdown,
				labelCriteriosCheckField);
	}

}
