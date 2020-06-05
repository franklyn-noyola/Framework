package procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos;

import org.apache.log4j.Logger;
import unidadesGraficas.TipoBy;
import ventanasITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.CrearNodoScreen;
import procesosComunes.Process;

public class CrearNodoProcess extends Process {
	
	private TipoBy[] i_botones= {TipoBy.ID,TipoBy.ID};
	private String[] str_botones= {"ctl00_ButtonsZone_BtnSubmit_IB_Label","ctl00_ButtonsZone_BtnBack_IB_Label"};
	private final static Logger logger = Logger.getLogger(CrearNodoProcess.class);
	 
	public CrearNodoProcess() {
		screen=new CrearNodoScreen(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,
				i_msgerror,str_msgerror);
	}
	
	public String leerCampoCodigo() {
		return ((CrearNodoScreen) screen).leerOpcion(OpcionesCrearNodoCampo.Codigo);
	}
	
	public void escribirCampoCodigo(String valor) {
		((CrearNodoScreen) screen).escribirOpcion(OpcionesCrearNodoCampo.Codigo, valor);
	}
	
	public void borrarCampoCodigo() {
		((CrearNodoScreen) screen).borrarOpcion(OpcionesCrearNodoCampo.Codigo);
	}
	
	public String leerCampoDireccion() {
		return ((CrearNodoScreen) screen).leerOpcion(OpcionesCrearNodoCampo.Direccion);
	}
	
	public void escribirCampoDireccion(String valor) {
		((CrearNodoScreen) screen).escribirOpcion(OpcionesCrearNodoCampo.Direccion, valor);
	}
	
	public void borrarCampoDireccion() {
		((CrearNodoScreen) screen).borrarOpcion(OpcionesCrearNodoCampo.Direccion);
	}
	
	public String leerCampoDescripcion() {
		return ((CrearNodoScreen) screen).leerOpcion(OpcionesCrearNodoCampo.Descripcion);
	}
	
	public void escribirCampoDescripcion(String valor) {
		((CrearNodoScreen) screen).escribirOpcion(OpcionesCrearNodoCampo.Descripcion, valor);
	}
	
	public void borrarCampoDescripcion() {
		((CrearNodoScreen) screen).borrarOpcion(OpcionesCrearNodoCampo.Descripcion);
	}
	
	public boolean hayErrorValidacionCodigo() {
		return ((CrearNodoScreen) screen).hayErrorValidacionOpcion(OpcionesCrearNodoCampo.Codigo);
	}
	
	public boolean hayErrorValidacionRequeridoCodigo() {
		return ((CrearNodoScreen) screen).hayErrorValidacionOpcionRequerido(OpcionesCrearNodoCampo.Codigo);
	}
	
	public boolean hayErrorValidacionFormatoCodigo() {
		return ((CrearNodoScreen) screen).hayErrorValidacionOpcionFormato(OpcionesCrearNodoCampo.Codigo);
	}
	
	public boolean hayErrorValidacionDireccion() {
		return ((CrearNodoScreen) screen).hayErrorValidacionOpcion(OpcionesCrearNodoCampo.Direccion);
	}
	
	public boolean hayErrorValidacionRequeridoDireccion() {
		return ((CrearNodoScreen) screen).hayErrorValidacionOpcionRequerido(OpcionesCrearNodoCampo.Direccion);
	}
	
	public boolean hayErrorValidacionFormatoDireccion() {
		return ((CrearNodoScreen) screen).hayErrorValidacionOpcionFormato(OpcionesCrearNodoCampo.Direccion);
	}
	
	public void confirmar() {
		screen.clickBoton(BotonesCrearModificarNodo.Confirmar.ordinal());
	}

	public void volver() {
		screen.clickBoton(BotonesCrearModificarNodo.Volver.ordinal());
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String encabezado_crud, String[] labels_crud) {
		logger.debug("Crear Nodo: inicio análisis sintáctico");
		return ((CrearNodoScreen) screen).sintacticAnalysis(titulo, subtitulo, botones, encabezado_crud, labels_crud);
	}

}
