package procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores;

import org.apache.log4j.Logger;
import procesosComunes.Error;
import unidadesGraficas.TipoBy;
import ventanasITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.CrearOperadorScreen;
import procesosComunes.Process;

public class CrearOperadorProcess extends Process {
	
	private TipoBy[] i_botones= {TipoBy.ID,TipoBy.ID};
	private String[] str_botones= {"ctl00_ButtonsZone_BtnSubmit_IB_Label","ctl00_ButtonsZone_BtnBack_IB_Label"};
	private final static Logger logger = Logger.getLogger(CrearOperadorScreen.class);
	 
	public CrearOperadorProcess() {
		screen=new CrearOperadorScreen(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones,
				str_botones,i_msgerror,str_msgerror);
	}
	
	public Error seleccionarOpcionConcesionaria(String opcion) {
		return ((CrearOperadorScreen) screen).desplegarOpcion(OpcionesCrearOperadorDesplegable.Concesionaria,opcion);
	}
	
	public Error seleccionarOpcionTitulo(String opcion) {
		return ((CrearOperadorScreen) screen).desplegarOpcion(OpcionesCrearOperadorDesplegable.Titulo,opcion);
	}
	
	public Error seleccionarOpcionGenero(String opcion) {
		return ((CrearOperadorScreen) screen).desplegarOpcion(OpcionesCrearOperadorDesplegable.Genero,opcion);
	}
	
	public Error seleccionarOpcionGrupoOP(String opcion) {
		return ((CrearOperadorScreen) screen).desplegarOpcion(OpcionesCrearOperadorDesplegable.GrupoOP,opcion);
	}
	
	public void escribirCampoNombre(String valor) {
		((CrearOperadorScreen) screen).escribirOpcionCrud1(OpcionesCrearOperadorCampoCrud1.Nombre, valor);
	}
	
	public boolean hayErrorValidacionNombre() {
		return ((CrearOperadorScreen) screen).hayErrorValidacionOpcionCrud1(OpcionesCrearOperadorCampoCrud1.Nombre);
	}
	
	public void escribirCampoApellidos(String valor) {
		((CrearOperadorScreen) screen).escribirOpcionCrud1(OpcionesCrearOperadorCampoCrud1.Apellidos, valor);
	}
	
	public boolean hayErrorValidacionApellidos() {
		return ((CrearOperadorScreen) screen).hayErrorValidacionOpcionCrud1(OpcionesCrearOperadorCampoCrud1.Apellidos);
	}
	
	public void escribirCampoDireccion(String valor) {
		((CrearOperadorScreen) screen).escribirOpcionCrud1(OpcionesCrearOperadorCampoCrud1.Direccion, valor);
	}
	
	public void escribirCampoCP(String valor) {
		((CrearOperadorScreen) screen).escribirOpcionCrud1(OpcionesCrearOperadorCampoCrud1.CP, valor);
	}
	
	public void escribirCampoCiudad(String valor) {
		((CrearOperadorScreen) screen).escribirOpcionCrud1(OpcionesCrearOperadorCampoCrud1.Ciudad, valor);
	}
	
	public void escribirCampoPais(String valor) {
		((CrearOperadorScreen) screen).escribirOpcionCrud1(OpcionesCrearOperadorCampoCrud1.Pais, valor);
	}
	
	public void escribirCampoEmail(String valor) {
		((CrearOperadorScreen) screen).escribirOpcionCrud1(OpcionesCrearOperadorCampoCrud1.Email, valor);
	}
	
	public void escribirCampoTelefono(String valor) {
		((CrearOperadorScreen) screen).escribirOpcionCrud1(OpcionesCrearOperadorCampoCrud1.Telefono, valor);
	}
	
	public void escribirCampoFecha(String valor) {
		((CrearOperadorScreen) screen).escribirFechaCrud1(OpcionesCrearOperadorFechaCrud1.FechaNacimiento, valor);
	}
	
	public void escribirCampoContraseña(String valor) {
		((CrearOperadorScreen) screen).escribirOpcionCrud2(OpcionesCrearOperadorCampoCrud2.Contraseña, valor);
	}
	
	public void borrarCampoFecha() {
		((CrearOperadorScreen) screen).borrarFechaCrud1(OpcionesCrearOperadorFechaCrud1.FechaNacimiento);
	}
	
	public boolean hayErrorValidacionFecha() {
		return ((CrearOperadorScreen) screen).hayErrorValidacionFechaCrud1(OpcionesCrearOperadorFechaCrud1.FechaNacimiento);
	}
	
	public boolean hayErrorValidacionRequeridoFecha() {
		return ((CrearOperadorScreen) screen).hayErrorValidacionFechaRequeridoCrud1(OpcionesCrearOperadorFechaCrud1.FechaNacimiento);
	}
	
	public boolean hayErrorValidacionFormatoFecha() {
		return ((CrearOperadorScreen) screen).hayErrorValidacionFechaCrud1(OpcionesCrearOperadorFechaCrud1.FechaNacimiento);
	}
	
	public boolean hayErrorValidacionContraseña() {
		return ((CrearOperadorScreen) screen).hayErrorValidacionOpcionCrud2(OpcionesCrearOperadorCampoCrud2.Contraseña);
	}
	
	public void escribirCampoRepitacontraseña(String valor) {
		((CrearOperadorScreen) screen).escribirOpcionCrud2(OpcionesCrearOperadorCampoCrud2.RepetirContraseña, valor);
	}
	
	public void confirmar() {
		screen.clickBoton(BotonesCrearOperador.Confirmar.ordinal());
	}
	
	public void volver() {
		screen.clickBoton(BotonesCrearOperador.Volver.ordinal());
	}
	
	/*public boolean hayMensajeError() {
		return screen.hayMensajeError();
	}
	
	public String verMensajeError() {
		return screen.verMensajeError();
	}*/
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String encabezado_crud1, String[] labels_crud1, 
			String encabezado_crud2, String[] labels_crud2) {
		logger.debug("Crear operador: inicio análisis sintáctico");
		return ((CrearOperadorScreen) screen).sintacticAnalysis(titulo, subtitulo, botones, encabezado_crud1, labels_crud1, encabezado_crud2, labels_crud2);
	}

}
