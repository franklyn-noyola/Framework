package procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos;

import org.apache.log4j.Logger;
import unidadesGraficas.TipoBy;
import ventanasITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.CrearNodoViaScreen;
import procesosComunes.Process;

public class CrearNodoViaProcess extends Process {
	
	private TipoBy[] i_botones= {TipoBy.ID,TipoBy.ID};
	private String[] str_botones= {"ctl00_ButtonsZone_BtnSubmit_IB_Label","ctl00_ButtonsZone_BtnBack_IB_Label"};
	private final static Logger logger = Logger.getLogger(CrearNodoViaProcess.class);
	 
	public CrearNodoViaProcess() {
		screen=new CrearNodoViaScreen(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,
				i_msgerror,str_msgerror);
	}
	
	public String leerCampoCodigoPlaza() {
		return ((CrearNodoViaScreen) screen).leerOpcionVariable(OpcionesCrearNodoViaVariable.Codigo);
	}
	
	public String leerCampoDescripcionPlaza() {
		return ((CrearNodoViaScreen) screen).leerOpcionVariable(OpcionesCrearNodoViaVariable.Descripcion);
	}
	
	public String leerCampoDireccionPlaza() {
		return ((CrearNodoViaScreen) screen).leerOpcionVariable(OpcionesCrearNodoViaVariable.Direccion);
	}
	
	public String leerCampoCodigoVia() {
		return ((CrearNodoViaScreen) screen).leerOpcion(OpcionesCrearNodoViaCampo.Codigo);
	}
	
	public void escribirCampoCodigoVia(String valor) {
		borrarCampoCodigoVia();
		((CrearNodoViaScreen) screen).escribirOpcion(OpcionesCrearNodoViaCampo.Codigo, valor);
	}
	
	public void borrarCampoCodigoVia() {
		((CrearNodoViaScreen) screen).borrarOpcion(OpcionesCrearNodoViaCampo.Codigo);
	}
	
	public String leerCampoDireccionVia() {
		return ((CrearNodoViaScreen) screen).leerOpcion(OpcionesCrearNodoViaCampo.Direccion);
	}
	
	public void escribirCampoDireccionVia(String valor) {
		borrarCampoDireccionVia();
		((CrearNodoViaScreen) screen).escribirOpcion(OpcionesCrearNodoViaCampo.Direccion, valor);
	}
	
	public void borrarCampoDireccionVia() {
		((CrearNodoViaScreen) screen).borrarOpcion(OpcionesCrearNodoViaCampo.Direccion);
	}
	
	public boolean hayErrorValidacionCodigoVia() {
		return ((CrearNodoViaScreen) screen).hayErrorValidacionOpcion(OpcionesCrearNodoViaCampo.Codigo);
	}
	
	public boolean hayErrorValidacionRequeridoCodigoVia() {
		return ((CrearNodoViaScreen) screen).hayErrorValidacionOpcionRequerido(OpcionesCrearNodoViaCampo.Codigo);
	}
	
	public boolean hayErrorValidacionFormatoCodigoVia() {
		return ((CrearNodoViaScreen) screen).hayErrorValidacionOpcionFormato(OpcionesCrearNodoViaCampo.Codigo);
	}
	
	public boolean hayErrorValidacionDireccionVia() {
		return ((CrearNodoViaScreen) screen).hayErrorValidacionOpcion(OpcionesCrearNodoViaCampo.Direccion);
	}
	
	public boolean hayErrorValidacionRequeridoDireccionVia() {
		return ((CrearNodoViaScreen) screen).hayErrorValidacionOpcionRequerido(OpcionesCrearNodoViaCampo.Direccion);
	}
	
	public boolean hayErrorValidacionFormatoDireccionVia() {
		return ((CrearNodoViaScreen) screen).hayErrorValidacionOpcionFormato(OpcionesCrearNodoViaCampo.Direccion);
	}
	
	public void confirmar() {
		screen.clickBoton(BotonesCrearModificarNodo.Confirmar.ordinal());
	}

	public void volver() {
		screen.clickBoton(BotonesCrearModificarNodo.Volver.ordinal());
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String encabezadoVar,
			String[] labelsVariable, String[] valoresvariables, String encabezado2, String[] labels) {
		logger.debug("Crear Nodo Vía: inicio análisis sintáctico");
		return ((CrearNodoViaScreen) screen).sintacticAnalysis(titulo, subtitulo, botones, encabezadoVar,
				labelsVariable,valoresvariables, encabezado2, labels);
	}

}
