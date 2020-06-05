package procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos;

import org.apache.log4j.Logger;
import unidadesGraficas.TipoBy;
import ventanasITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.ModificarNodoScreen;
import procesosComunes.Process;

public class ModificarNodoProcess  extends Process {
	
	private TipoBy[] i_botones= {TipoBy.ID,TipoBy.ID};
	private String[] str_botones= {"ctl00_ButtonsZone_BtnSubmit_IB_Label","ctl00_ButtonsZone_BtnBack_IB_Label"};
	private final static Logger logger = Logger.getLogger(ModificarNodoProcess.class);
	 
	public ModificarNodoProcess() {
		screen=new ModificarNodoScreen(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones,
				str_botones, i_msgerror,str_msgerror);
	}
	
	public String leerCampoCodigo() {
		return ((ModificarNodoScreen) screen).leerOpcionVariable(OpcionesModificarNodoVariable.Codigo);
	}
	
	public String leerCampoDireccion() {
		return ((ModificarNodoScreen) screen).leerOpcion(OpcionesModificarNodoCampo.Direccion);
	}
	
	public void escribirCampoDireccion(String valor) {
		borrarCampoDireccion();
		((ModificarNodoScreen) screen).escribirOpcion(OpcionesModificarNodoCampo.Direccion, valor);
	}
	
	public void borrarCampoDireccion() {
		((ModificarNodoScreen) screen).borrarOpcion(OpcionesModificarNodoCampo.Direccion);
	}
	
	public String leerCampoDescripcion() {
		return ((ModificarNodoScreen) screen).leerOpcion(OpcionesModificarNodoCampo.Descripcion);
	}
	
	public void escribirCampoDescripcion(String valor) {
		borrarCampoDescripcion();
		((ModificarNodoScreen) screen).escribirOpcion(OpcionesModificarNodoCampo.Descripcion, valor);
	}
	
	public void borrarCampoDescripcion() {
		((ModificarNodoScreen) screen).borrarOpcion(OpcionesModificarNodoCampo.Descripcion);
	}
	
	public boolean hayErrorValidacionDireccion() {
		return ((ModificarNodoScreen) screen).hayErrorValidacionOpcion(OpcionesModificarNodoCampo.Direccion);
	}
	
	public boolean hayErrorValidacionRequeridoDireccion() {
		return ((ModificarNodoScreen) screen).hayErrorValidacionOpcionRequerido(OpcionesModificarNodoCampo.Direccion);
	}
	
	public boolean hayErrorValidacionFormatoDireccion() {
		return ((ModificarNodoScreen) screen).hayErrorValidacionOpcionFormato(OpcionesModificarNodoCampo.Direccion);
	}
	
	public boolean confirmar() {
		return screen.clickBoton(BotonesCrearModificarNodo.Confirmar.ordinal());
	}
	
	public void volver() {
		screen.clickBoton(BotonesCrearModificarNodo.Volver.ordinal());
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String encabezado,
			String[] labels_variables, String[] valorvariable, String[] labels) {
		logger.debug("Modificar nodo: inicio análisis sintáctico");
		return ((ModificarNodoScreen) screen).sintacticAnalysis(titulo, subtitulo, botones, encabezado, labels_variables,
				valorvariable, labels);
	}

}
