package procesosITATAHost.ConfiguracionSistema.GestionClases.Categorias;

import org.apache.log4j.Logger;
import unidadesGraficas.TipoBy;
import ventanasITATAHost.ConfiguracionSistema.GestionClases.Categorias.ModificarCategoriaScreen;
import procesosComunes.Process;

public class ModificarCategoriaProcess  extends Process {
	
	private TipoBy[] i_botones= {TipoBy.ID,TipoBy.ID};
	private String[] str_botones= {"ctl00_ButtonsZone_BtnSubmit_IB_Label","ctl00_ButtonsZone_BtnBack_IB_Label"};
	private final static Logger logger = Logger.getLogger(ModificarCategoriaProcess.class);
	 
	public ModificarCategoriaProcess() {
		screen=new ModificarCategoriaScreen(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones,
				str_botones, i_msgerror,str_msgerror);
	}
	
	public String leerCampoConcesionaria() {
		return ((ModificarCategoriaScreen) screen).leerOpcionVariable(OpcionesModificarCategoriaVariable.Concesionaria);
	}
	
	public String leerCampoID() {
		return ((ModificarCategoriaScreen) screen).leerOpcionVariable(OpcionesModificarCategoriaVariable.ID);
	}
	
	public String leerOpcionTipo() {
		return ((ModificarCategoriaScreen) screen).leerOpcionDesplegable(OpcionesModificarCategoriaDesplegable.Tipo);
	}
	
	public String leerCampoDescripcion() {
		return ((ModificarCategoriaScreen) screen).leerOpcion(OpcionesModificarCategoriaCampo.Descripcion);
	}
	
	public void escribirCampoDescripcion(String valor) {
		borrarCampoDescripcion();
		((ModificarCategoriaScreen) screen).escribirOpcion(OpcionesModificarCategoriaCampo.Descripcion, valor);
	}
	
	public void borrarCampoDescripcion() {
		((ModificarCategoriaScreen) screen).borrarOpcion(OpcionesModificarCategoriaCampo.Descripcion);
	}
	
	public void seleccionarOpcionTipo(String opcion) {
		((ModificarCategoriaScreen) screen).desplegarOpcion(OpcionesModificarCategoriaDesplegable.Tipo,opcion);
	}
	
	public boolean hayErrorValidacionDescripcion() {
		return ((ModificarCategoriaScreen) screen).hayErrorValidacionOpcion(OpcionesModificarCategoriaCampo.Descripcion);
	}
	
	public boolean confirmar() {
		return screen.clickBoton(BotonesCrearModificarCategoria.Confirmar.ordinal());
	}
	
	public void volver() {
		screen.clickBoton(BotonesCrearModificarCategoria.Volver.ordinal());
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String encabezado,
			String[] labels_variables, String[] valorvariable, String[] labels) {
		logger.debug("Modificar categoría: inicio análisis sintáctico");
		return ((ModificarCategoriaScreen) screen).sintacticAnalysis(titulo, subtitulo, botones, encabezado, labels_variables,
				valorvariable, labels);
	}

}
