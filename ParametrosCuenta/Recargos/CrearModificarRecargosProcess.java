package procesosITATAHost.ConfiguracionSistema.ParametrosCuenta.Recargos;

import org.apache.log4j.Logger;
import unidadesGraficas.TipoBy;
import ventanasITATAHost.ConfiguracionSistema.ParametrosCuenta.CrearModificarRecargosScreen;
import procesosComunes.Process;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Motivos.BotonesCrearModificarMotivo;

public class CrearModificarRecargosProcess extends Process {
	
	private TipoBy[] i_botones= {TipoBy.ID,TipoBy.ID};
	private String[] str_botones= {"ctl00_ButtonsZone_BtnSubmit_IB_Label","ctl00_ButtonsZone_BtnBack_IB_Label"};
	private final static Logger logger = Logger.getLogger(CrearModificarRecargosProcess.class);
	 
	public CrearModificarRecargosProcess() {
		screen=new CrearModificarRecargosScreen(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,
				i_msgerror,str_msgerror);
	}
	
	public String leerCampoNombre() {
		return ((CrearModificarRecargosScreen) screen).leerOpcion(OpcionesCrearModificarRecargosCampo.Nombre);
	}
	
	public void escribirCampoNombre(String valor) {
		borrarCampoNombre();
		((CrearModificarRecargosScreen) screen).escribirOpcion(OpcionesCrearModificarRecargosCampo.Nombre, valor);
	}
	
	public void borrarCampoNombre() {
		((CrearModificarRecargosScreen) screen).borrarOpcion(OpcionesCrearModificarRecargosCampo.Nombre);
	}
	
	public String leerCampoDescripcion() {
		return ((CrearModificarRecargosScreen) screen).leerOpcion(OpcionesCrearModificarRecargosCampo.Descripcion);
	}
	
	public void escribirCampoDescripcion(String valor) {
		borrarCampoDescripcion();
		((CrearModificarRecargosScreen) screen).escribirOpcion(OpcionesCrearModificarRecargosCampo.Descripcion, valor);
	}
	
	public void borrarCampoDescripcion() {
		((CrearModificarRecargosScreen) screen).borrarOpcion(OpcionesCrearModificarRecargosCampo.Descripcion);
	}
	
	public String leerCampoTipo() {
		return ((CrearModificarRecargosScreen) screen).leerOpcionDesplegable(OpcionesCrearModificarRecargosDesplegable.Tipo);
	}
	
	public void seleccionarCampoTipo(String valor) {
		((CrearModificarRecargosScreen) screen).seleccionarOpcionDesplegable(OpcionesCrearModificarRecargosDesplegable.Tipo, valor);
	}
	
	public String leerCampoTipoAplicacion() {
		return ((CrearModificarRecargosScreen) screen).leerOpcionDesplegable(OpcionesCrearModificarRecargosDesplegable.TipoAplicacion);
	}
	
	public void seleccionarCampoTipoAplicacion(String valor) {
		((CrearModificarRecargosScreen) screen).seleccionarOpcionDesplegable(OpcionesCrearModificarRecargosDesplegable.TipoAplicacion, valor);
	}
	
	public String leerCampoValor() {
		return ((CrearModificarRecargosScreen) screen).leerOpcion(OpcionesCrearModificarRecargosCampo.Valor);
	}
	
	public void escribirCampoValor(String valor) {
		borrarCampoValor();
		((CrearModificarRecargosScreen) screen).escribirOpcion(OpcionesCrearModificarRecargosCampo.Valor, valor);
	}
	
	public void borrarCampoValor() {
		((CrearModificarRecargosScreen) screen).borrarOpcion(OpcionesCrearModificarRecargosCampo.Valor);
	}
	
	public boolean hayErrorValidacionValor() {
		return ((CrearModificarRecargosScreen) screen).hayErrorValidacionOpcion(OpcionesCrearModificarRecargosCampo.Valor);
	}
	
	public void confirmar() {
		screen.clickBoton(BotonesCrearModificarMotivo.Confirmar.ordinal());
	}

	public void volver() {
		screen.clickBoton(BotonesCrearModificarMotivo.Volver.ordinal());
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String encabezado_crud, String[] labels_crud) {
		logger.debug("CrearModificar Recargo: inicio análisis sintáctico");
		return ((CrearModificarRecargosScreen) screen).sintacticAnalysis(titulo, subtitulo, botones, encabezado_crud, labels_crud);
	}

}
