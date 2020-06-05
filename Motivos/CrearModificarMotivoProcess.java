package procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Motivos;

import org.apache.log4j.Logger;
import unidadesGraficas.TipoBy;
import ventanasITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Motivos.CrearModificarMotivoScreen;
import procesosComunes.Process;

public class CrearModificarMotivoProcess extends Process {
	
	private TipoBy[] i_botones= {TipoBy.ID,TipoBy.ID};
	private String[] str_botones= {"ctl00_ButtonsZone_BtnSubmit_IB_Label","ctl00_ButtonsZone_BtnBack_IB_Label"};
	private final static Logger logger = Logger.getLogger(CrearModificarMotivoProcess.class);
	 
	public CrearModificarMotivoProcess() {
		screen=new CrearModificarMotivoScreen(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,
				i_msgerror,str_msgerror);
	}
	
	public String leerCampoMotivo() {
		return ((CrearModificarMotivoScreen) screen).leerOpcion(OpcionesCrearModificarMotivosCampo.Motivo);
	}
	
	public void escribirCampoMotivo(String valor) {
		borrarCampoMotivo();
		((CrearModificarMotivoScreen) screen).escribirOpcion(OpcionesCrearModificarMotivosCampo.Motivo, valor);
	}
	
	public void borrarCampoMotivo() {
		((CrearModificarMotivoScreen) screen).borrarOpcion(OpcionesCrearModificarMotivosCampo.Motivo);
	}
	
	public String leerCampoDescripcion() {
		return ((CrearModificarMotivoScreen) screen).leerOpcion(OpcionesCrearModificarMotivosCampo.Descripcion);
	}
	
	public void escribirCampoDescripcion(String valor) {
		borrarCampoDescripcion();
		((CrearModificarMotivoScreen) screen).escribirOpcion(OpcionesCrearModificarMotivosCampo.Descripcion, valor);
	}
	
	public void borrarCampoDescripcion() {
		((CrearModificarMotivoScreen) screen).borrarOpcion(OpcionesCrearModificarMotivosCampo.Descripcion);
	}
	
	public String leerOpcionTipo() {
		return ((CrearModificarMotivoScreen) screen).leerOpcion(OpcionesCrearModificarMotivosDesplegable.Tipo);
	}
	
	public void seleccionarOpcionTipo(String opcion) {
		((CrearModificarMotivoScreen) screen).desplegarOpcion(OpcionesCrearModificarMotivosDesplegable.Tipo,opcion);
	}
	
	public boolean hayErrorValidacionMotivo() {
		return ((CrearModificarMotivoScreen) screen).hayErrorValidacionOpcion(OpcionesCrearModificarMotivosCampo.Motivo);
	}
	
	public boolean hayErrorValidacionDescripcion() {
		return ((CrearModificarMotivoScreen) screen).hayErrorValidacionOpcion(OpcionesCrearModificarMotivosCampo.Descripcion);
	}
	
	public void confirmar() {
		screen.clickBoton(BotonesCrearModificarMotivo.Confirmar.ordinal());
	}

	public void volver() {
		screen.clickBoton(BotonesCrearModificarMotivo.Volver.ordinal());
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String encabezado_crud, String[] labels_crud) {
		logger.debug("CrearModificar Motivo: inicio análisis sintáctico");
		return ((CrearModificarMotivoScreen) screen).sintacticAnalysis(titulo, subtitulo, botones, encabezado_crud, labels_crud);
	}

}
