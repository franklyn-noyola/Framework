package procesosITATAHost.ConfiguracionSistema.Operadores.GestionGruposOperadores;

import org.apache.log4j.Logger;
import procesosComunes.Error;
import unidadesGraficas.TipoBy;
import ventanasITATAHost.ConfiguracionSistema.Operadores.GestionGrupos.CrearModificarGrupoOperadoresScreen;
import procesosComunes.Process;

public class CrearModificarGrupoOperadoresProcess extends Process {
	
	private TipoBy[] i_botones= {TipoBy.ID,TipoBy.ID};
	private String[] str_botones= {"ctl00_ButtonsZone_BtnSubmit_IB_Label","ctl00_ButtonsZone_BtnBack_IB_Label"};
	private final static Logger logger = Logger.getLogger(CrearModificarGrupoOperadoresProcess.class);
	 
	public CrearModificarGrupoOperadoresProcess() {
		screen=new CrearModificarGrupoOperadoresScreen(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,
				i_msgerror,str_msgerror);
	}
	
	public String leerCampoDescripcion() {
		return ((CrearModificarGrupoOperadoresScreen) screen).leerOpcion(OpcionesCrearModificarGrupoOperadoresCampo.Descripcion);
	}
	
	public void escribirCampoDescripcion(String valor) {
		((CrearModificarGrupoOperadoresScreen) screen).escribirOpcion(OpcionesCrearModificarGrupoOperadoresCampo.Descripcion, valor);
	}
	
	public void borrarCampoDescripcion() {
		((CrearModificarGrupoOperadoresScreen) screen).borrarOpcion(OpcionesCrearModificarGrupoOperadoresCampo.Descripcion);
	}
	
	public boolean hayErrorValidacionDescripcion() {
		return ((CrearModificarGrupoOperadoresScreen) screen).hayErrorValidacionOpcion(OpcionesCrearModificarGrupoOperadoresCampo.Descripcion);
	}
	
	public String leerOpcionAccesoPlaza() {
		return ((CrearModificarGrupoOperadoresScreen) screen).leerOpcionDesplegable(OpcionesCrearModificarGrupoOperadoresDesplegable.AccesoPlaza);
	}
	
	public Error seleccionarOpcionAccesoPlaza(String opcion) {
		return ((CrearModificarGrupoOperadoresScreen) screen).desplegarOpcion(OpcionesCrearModificarGrupoOperadoresDesplegable.AccesoPlaza,opcion);
	}
	
	public String leerOpcionAccesoVia() {
		return ((CrearModificarGrupoOperadoresScreen) screen).leerOpcionDesplegable(OpcionesCrearModificarGrupoOperadoresDesplegable.AccesoVia);
	}
	
	public Error seleccionarOpcionAccesoVia(String opcion) {
		return ((CrearModificarGrupoOperadoresScreen) screen).desplegarOpcion(OpcionesCrearModificarGrupoOperadoresDesplegable.AccesoVia,opcion);
	}
	
	public void confirmar() {
		screen.clickBoton(BotonesCrearModificarGrupoOperadores.Confirmar.ordinal());
	}

	public void volver() {
		screen.clickBoton(BotonesCrearModificarGrupoOperadores.Volver.ordinal());
	}
	
	public void seleccionarTablaIzquierda(int indice) {
		((CrearModificarGrupoOperadoresScreen) screen).seleccionarTablaIzquierda(indice);
	}
	
	public void seleccionarTablaDerecha(int indice) {
		((CrearModificarGrupoOperadoresScreen) screen).seleccionarTablaDerecha(indice);
	}
	
	public void seleccionarTablaIzquierda(String opcion) {
		((CrearModificarGrupoOperadoresScreen) screen).seleccionarTablaIzquierda(opcion);
	}
	
	public void seleccionarTablaDerecha(String opcion) {
		((CrearModificarGrupoOperadoresScreen) screen).seleccionarTablaDerecha(opcion);
	}
	
	public void botonIzquierda() {
		((CrearModificarGrupoOperadoresScreen) screen).botonIzquierda();
	}
	
	public void botonDerecha() {
		((CrearModificarGrupoOperadoresScreen) screen).botonDerecha();
	}
	
	public boolean existeOpcionEnTablaIzquierda(String opcion) {
		return ((CrearModificarGrupoOperadoresScreen) screen).existeOpcionEnTablaIzquierda(opcion);
	}
	
	public boolean existeOpcionEnTablaDerecha(String opcion) {
		return ((CrearModificarGrupoOperadoresScreen) screen).existeOpcionEnTablaDerecha(opcion);
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String encabezado_crud, String[] labels_crud, 
			String encabezado_tablas, String[] labels_tablas, String[] botones_tablas) {
		logger.debug("Modificar Grupos operadores: inicio análisis sintáctico");
		return ((CrearModificarGrupoOperadoresScreen) screen).sintacticAnalysis(titulo, subtitulo, botones, encabezado_crud, labels_crud,  encabezado_tablas, labels_tablas, botones_tablas);
	}

}
