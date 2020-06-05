package procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores;

import org.apache.log4j.Logger;
import unidadesGraficas.TipoBy;
import ventanasITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.ModificarOperadorScreen;
import procesosComunes.Process;

public class ModificarOperadorProcess  extends Process {
	
	private TipoBy[] i_botones= {TipoBy.ID,TipoBy.ID};
	private String[] str_botones= {"ctl00_ButtonsZone_BtnSubmit_IB_Label","ctl00_ButtonsZone_BtnBack_IB_Label"};
	private final static Logger logger = Logger.getLogger(ModificarOperadorProcess.class);
	 
	public ModificarOperadorProcess() {
		screen=new ModificarOperadorScreen(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones,
				str_botones, i_msgerror,str_msgerror);
	}
	
	public String leerConcesionaria() {
		return ((ModificarOperadorScreen) screen).leerOpcionVariable(OpcionesVerOperadorVariable.Concesionaria);
	}
	
	public String leerID() {
		return ((ModificarOperadorScreen) screen).leerOpcionVariable(OpcionesVerOperadorVariable.ID);
	}
	
	public String leerOpcionTitulo() {
		return ((ModificarOperadorScreen) screen).leerOpcion(OpcionesVerOperadorDesplegable.Titulo);
	}
	
	public String leerOpcionGenero() {
		return ((ModificarOperadorScreen) screen).leerOpcion(OpcionesVerOperadorDesplegable.Genero);
	}
	
	public String leerOpcionGrupoOP() {
		return ((ModificarOperadorScreen) screen).leerOpcion(OpcionesVerOperadorDesplegable.GrupoOP);
	}
	
	public String leerCampoNombre() {
		return ((ModificarOperadorScreen) screen).leerOpcionCrud1(OpcionesCrearOperadorCampoCrud1.Nombre);
	}
	
	public String leerCampoApellidos() {
		return ((ModificarOperadorScreen) screen).leerOpcionCrud1(OpcionesCrearOperadorCampoCrud1.Apellidos);
	}
	
	public String leerCampoDireccion() {
		return ((ModificarOperadorScreen) screen).leerOpcionCrud1(OpcionesCrearOperadorCampoCrud1.Direccion);
	}
	
	public String leerCampoCP() {
		return ((ModificarOperadorScreen) screen).leerOpcionCrud1(OpcionesCrearOperadorCampoCrud1.CP);
	}
	
	public String leerCampoCiudad() {
		return ((ModificarOperadorScreen) screen).leerOpcionCrud1(OpcionesCrearOperadorCampoCrud1.Ciudad);
	}
	
	public String leerCampoPais() {
		return ((ModificarOperadorScreen) screen).leerOpcionCrud1(OpcionesCrearOperadorCampoCrud1.Pais);
	}
	
	public String leerCampoEmail() {
		return ((ModificarOperadorScreen) screen).leerOpcionCrud1(OpcionesCrearOperadorCampoCrud1.Email);
	}
	
	public String leerCampoTelefono() {
		return ((ModificarOperadorScreen) screen).leerOpcionCrud1(OpcionesCrearOperadorCampoCrud1.Telefono);
	}
	
	public String leerCampoFechaNacimiento() {
		return ((ModificarOperadorScreen) screen).leerFechaCrud1(OpcionesCrearOperadorFechaCrud1.FechaNacimiento);
	}
	
	public void seleccionarOpcionTitulo(String opcion) {
		((ModificarOperadorScreen) screen).desplegarOpcion(OpcionesVerOperadorDesplegable.Titulo,opcion);
	}
	
	public void seleccionarOpcionGenero(String opcion) {
		((ModificarOperadorScreen) screen).desplegarOpcion(OpcionesVerOperadorDesplegable.Genero,opcion);
	}
	
	public void seleccionarOpcionGrupoOP(String opcion) {
		((ModificarOperadorScreen) screen).desplegarOpcion(OpcionesVerOperadorDesplegable.GrupoOP,opcion);
	}
	
	public void escribirCampoNombre(String valor) {
		((ModificarOperadorScreen) screen).escribirOpcionCrud1(OpcionesCrearOperadorCampoCrud1.Nombre, valor);
	}
	
	public void escribirCampoApellidos(String valor) {
		((ModificarOperadorScreen) screen).escribirOpcionCrud1(OpcionesCrearOperadorCampoCrud1.Apellidos, valor);
	}
	
	public void escribirCampoDireccion(String valor) {
		((ModificarOperadorScreen) screen).escribirOpcionCrud1(OpcionesCrearOperadorCampoCrud1.Direccion, valor);
	}
	
	public void escribirCampoCP(String valor) {
		((ModificarOperadorScreen) screen).escribirOpcionCrud1(OpcionesCrearOperadorCampoCrud1.CP, valor);
	}
	
	public void escribirCampoCiudad(String valor) {
		((ModificarOperadorScreen) screen).escribirOpcionCrud1(OpcionesCrearOperadorCampoCrud1.Ciudad, valor);
	}
	
	public void escribirCampoPais(String valor) {
		((ModificarOperadorScreen) screen).escribirOpcionCrud1(OpcionesCrearOperadorCampoCrud1.Pais, valor);
	}
	
	public void escribirCampoEmail(String valor) {
		((ModificarOperadorScreen) screen).escribirOpcionCrud1(OpcionesCrearOperadorCampoCrud1.Email, valor);
	}
	
	public void escribirCampoTelefono(String valor) {
		((ModificarOperadorScreen) screen).escribirOpcionCrud1(OpcionesCrearOperadorCampoCrud1.Telefono, valor);
	}
	
	public void escribirCampoFechaNacimiento(String valor) {
		((ModificarOperadorScreen) screen).escribirFechaCrud1(OpcionesCrearOperadorFechaCrud1.FechaNacimiento, valor);
	}
	
	public boolean hayErrorValidacionNombre() {
		return ((ModificarOperadorScreen) screen).hayErrorValidacionOpcionCrud1(OpcionesCrearOperadorCampoCrud1.Nombre);
	}
	
	public boolean hayErrorValidacionApellidos() {
		return ((ModificarOperadorScreen) screen).hayErrorValidacionOpcionCrud1(OpcionesCrearOperadorCampoCrud1.Apellidos);
	}
	
	public boolean hayErrorValidacionFecha() {
		return ((ModificarOperadorScreen) screen).hayErrorValidacionFechaCrud1(OpcionesCrearOperadorFechaCrud1.FechaNacimiento);
	}
	
	public boolean hayErrorValidacionFormatoFecha() {
		return ((ModificarOperadorScreen) screen).hayErrorValidacionFormatoFecha(OpcionesCrearOperadorFechaCrud1.FechaNacimiento);
	}
	
	public boolean hayErrorValidacionRequeridoFecha() {
		return ((ModificarOperadorScreen) screen).hayErrorValidacionRequeridoFecha(OpcionesCrearOperadorFechaCrud1.FechaNacimiento);
	}
	
	public void borrarCampoFecha() {
		((ModificarOperadorScreen) screen).borrarFechaCrud1(OpcionesCrearOperadorFechaCrud1.FechaNacimiento);
	}
	
	public boolean confirmar() {
		return screen.clickBoton(BotonesCrearOperador.Confirmar.ordinal());
	}
	
	public void volver() {
		screen.clickBoton(BotonesCrearOperador.Volver.ordinal());
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String encabezado_crud1,
			String[] labels_variables, String[] valorvariable, String[] labels_crud1, 
			String encabezado_crud2, String label_crud2, String encabezado_crud3, String label_crud3) {
		logger.debug("Modificar operador: inicio análisis sintáctico");
		return ((ModificarOperadorScreen) screen).sintacticAnalysis(titulo, subtitulo, botones, encabezado_crud1, labels_variables,
				valorvariable, labels_crud1, encabezado_crud2, label_crud2, encabezado_crud3, label_crud3);
	}

}
