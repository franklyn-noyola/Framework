package procesosITATAHost.ConfiguracionSistema.GestionClases.Categorias;

import org.apache.log4j.Logger;
import unidadesGraficas.TipoBy;
import ventanasITATAHost.ConfiguracionSistema.GestionClases.Categorias.CrearCategoriaScreen;
import procesosComunes.Process;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Motivos.BotonesCrearModificarMotivo;


public class CrearCategoriaProcess extends Process {
	
	private TipoBy[] i_botones= {TipoBy.ID,TipoBy.ID};
	private String[] str_botones= {"ctl00_ButtonsZone_BtnSubmit_IB_Label","ctl00_ButtonsZone_BtnBack_IB_Label"};
	private final static Logger logger = Logger.getLogger(CrearCategoriaProcess.class);
	 
	public CrearCategoriaProcess() {
		screen=new CrearCategoriaScreen(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,
				i_msgerror,str_msgerror);
	}
	
	public String leerCampoIDCategoria() {
		return ((CrearCategoriaScreen) screen).leerOpcion(OpcionesCrearCategoriaCampo.IDCategoria);
	}
	
	public void escribirCampoIDCategoria(String valor) {
		((CrearCategoriaScreen) screen).escribirOpcion(OpcionesCrearCategoriaCampo.IDCategoria, valor);
	}
	
	public void borrarCampoIDCategoria() {
		((CrearCategoriaScreen) screen).borrarOpcion(OpcionesCrearCategoriaCampo.IDCategoria);
	}
	
	public boolean hayErrorValidacionIDCategoria() {
		return ((CrearCategoriaScreen) screen).hayErrorValidacionOpcion(OpcionesCrearCategoriaCampo.IDCategoria);
	}
	
	public boolean hayErrorValidacionRequeridoIDCategoria() {
		return ((CrearCategoriaScreen) screen).hayErrorValidacionOpcionRequerido(OpcionesCrearCategoriaCampo.IDCategoria);
	}
	
	public boolean hayErrorValidacionFormatoIDCategoria() {
		return ((CrearCategoriaScreen) screen).hayErrorValidacionOpcionFormato(OpcionesCrearCategoriaCampo.IDCategoria);
	}
	
	public String leerCampoDescripcion() {
		return ((CrearCategoriaScreen) screen).leerOpcion(OpcionesCrearCategoriaCampo.Descripcion);
	}
	
	public void escribirCampoDescripcion(String valor) {
		((CrearCategoriaScreen) screen).escribirOpcion(OpcionesCrearCategoriaCampo.Descripcion, valor);
	}
	
	public void borrarCampoDescripcion() {
		((CrearCategoriaScreen) screen).borrarOpcion(OpcionesCrearCategoriaCampo.Descripcion);
	}
	
	public boolean hayErrorValidacionRequeridoDescripcion() {
		return ((CrearCategoriaScreen) screen).hayErrorValidacionOpcion(OpcionesCrearCategoriaCampo.Descripcion);
	}
	
	public void seleccionarCampoConcesionaria(String valor) {
		((CrearCategoriaScreen) screen).seleccionarOpcionDesplegable(OpcionesCrearCategoriaDesplegable.Concesionaria, valor);
	}
	
	public String leerCampoTipo() {
		return ((CrearCategoriaScreen) screen).leerOpcionDesplegable(OpcionesCrearCategoriaDesplegable.Tipo);
	}
	
	public void seleccionarCampoTipo(String valor) {
		((CrearCategoriaScreen) screen).seleccionarOpcionDesplegable(OpcionesCrearCategoriaDesplegable.Tipo, valor);
	}
	
	public void confirmar() {
		screen.clickBoton(BotonesCrearModificarMotivo.Confirmar.ordinal());
	}

	public void volver() {
		screen.clickBoton(BotonesCrearModificarMotivo.Volver.ordinal());
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String encabezado_crud, String[] labels_crud) {
		logger.debug("Crear categoría: inicio análisis sintáctico");
		return ((CrearCategoriaScreen) screen).sintacticAnalysis(titulo, subtitulo, botones, encabezado_crud, labels_crud);
	}

}
