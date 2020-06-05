package procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje;

import org.apache.log4j.Logger;
import unidadesGraficas.TipoBy;
import ventanasITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje.CrearModificarParametrosPeajeScreen;
import procesosComunes.Process;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Motivos.BotonesCrearModificarMotivo;

public class CrearModificarParametroPeajeProcess extends Process {
	
	private TipoBy[] i_botones= {TipoBy.ID,TipoBy.ID};
	private String[] str_botones= {"ctl00_ButtonsZone_BtnSubmit_IB_Label","ctl00_ButtonsZone_BtnBack_IB_Label"};
	private final static Logger logger = Logger.getLogger(CrearModificarParametroPeajeProcess.class);
	 
	public CrearModificarParametroPeajeProcess() {
		screen=new CrearModificarParametrosPeajeScreen(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,
				i_msgerror,str_msgerror);
	}
	
	public String leerCampoParametro() {
		return ((CrearModificarParametrosPeajeScreen) screen).leerOpcion(OpcionesCrearModificarParametrosPeajeCampo.Parametro);
	}
	
	public void escribirCampoParametro(String valor) {
		borrarCampoParametro();
		((CrearModificarParametrosPeajeScreen) screen).escribirOpcion(OpcionesCrearModificarParametrosPeajeCampo.Parametro, valor);
	}
	
	public void borrarCampoParametro() {
		((CrearModificarParametrosPeajeScreen) screen).borrarOpcion(OpcionesCrearModificarParametrosPeajeCampo.Parametro);
	}
	
	public String leerCampoValor() {
		return ((CrearModificarParametrosPeajeScreen) screen).leerOpcion(OpcionesCrearModificarParametrosPeajeCampo.Valor);
	}
	
	public void escribirCampoValor(String valor) {
		borrarCampoValor();
		((CrearModificarParametrosPeajeScreen) screen).escribirOpcion(OpcionesCrearModificarParametrosPeajeCampo.Valor, valor);
	}
	
	public void borrarCampoValor() {
		((CrearModificarParametrosPeajeScreen) screen).borrarOpcion(OpcionesCrearModificarParametrosPeajeCampo.Valor);
	}
	
	public boolean hayErrorValidacionParametro() {
		return ((CrearModificarParametrosPeajeScreen) screen).hayErrorValidacionOpcion(OpcionesCrearModificarParametrosPeajeCampo.Parametro);
	}
	
	public void confirmar() {
		screen.clickBoton(BotonesCrearModificarMotivo.Confirmar.ordinal());
	}

	public void volver() {
		screen.clickBoton(BotonesCrearModificarMotivo.Volver.ordinal());
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String encabezado_crud, String[] labels_crud) {
		logger.debug("CrearModificar Parametro Peaje: inicio análisis sintáctico");
		return ((CrearModificarParametrosPeajeScreen) screen).sintacticAnalysis(titulo, subtitulo, botones, encabezado_crud, labels_crud);
	}

}
