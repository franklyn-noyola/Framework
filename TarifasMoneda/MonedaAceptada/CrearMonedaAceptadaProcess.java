package procesosITATAHost.ConfiguracionSistema.TarifasMoneda.MonedaAceptada;

import org.apache.log4j.Logger;
import procesosComunes.Error;
import unidadesGraficas.TipoBy;
import ventanasITATAHost.ConfiguracionSistema.TarifasMoneda.MonedaAceptada.CrearMonedaAceptadaScreen;
import procesosComunes.Process;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Motivos.BotonesCrearModificarMotivo;


public class CrearMonedaAceptadaProcess extends Process {
	
	private TipoBy[] i_botones= {TipoBy.ID,TipoBy.ID};
	private String[] str_botones= {"ctl00_ButtonsZone_BtnSubmit_IB_Label","ctl00_ButtonsZone_BtnBack_IB_Label"};
	private final static Logger logger = Logger.getLogger(CrearMonedaAceptadaProcess.class);
	 
	public CrearMonedaAceptadaProcess() {
		screen=new CrearMonedaAceptadaScreen(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,
				i_msgerror,str_msgerror);
	}
	
	public String leerCampoDenominacion() {
		return ((CrearMonedaAceptadaScreen) screen).leerOpcion(OpcionesCrearMonedaAceptadaCampo.Denominacion);
	}
	
	public void escribirCampoDenominacion(String valor) {
		((CrearMonedaAceptadaScreen) screen).escribirOpcion(OpcionesCrearMonedaAceptadaCampo.Denominacion, valor);
	}
	
	public void borrarCampoDenominacion() {
		((CrearMonedaAceptadaScreen) screen).borrarOpcion(OpcionesCrearMonedaAceptadaCampo.Denominacion);
	}
	
	public boolean hayErrorValidacionRequeridoDenominacion() {
		return ((CrearMonedaAceptadaScreen) screen).hayErrorValidacionOpcionRequerido(OpcionesCrearMonedaAceptadaCampo.Denominacion);
	}
	
	public boolean hayErrorValidacionFormatoDenominacion() {
		return ((CrearMonedaAceptadaScreen) screen).hayErrorValidacionOpcionFormato(OpcionesCrearMonedaAceptadaCampo.Denominacion);
	}
	
	public String leerCampoConcesionaria() {
		return ((CrearMonedaAceptadaScreen) screen).leerOpcionDesplegable(OpcionesCrearMonedaAceptadaDesplegable.Concesionaria);
	}
	
	public Error seleccionarCampoConcesionaria(String valor) {
		return ((CrearMonedaAceptadaScreen) screen).seleccionarOpcionDesplegable(OpcionesCrearMonedaAceptadaDesplegable.Concesionaria, valor);
	}
	
	public String leerCampoTipoMoneda() {
		return ((CrearMonedaAceptadaScreen) screen).leerOpcionDesplegable(OpcionesCrearMonedaAceptadaDesplegable.TipoMoneda);
	}
	
	public Error seleccionarCampoTipoMoneda(String valor) {
		return ((CrearMonedaAceptadaScreen) screen).seleccionarOpcionDesplegable(OpcionesCrearMonedaAceptadaDesplegable.TipoMoneda, valor);
	}
	
	public void confirmar() {
		screen.clickBoton(BotonesCrearModificarMotivo.Confirmar.ordinal());
	}

	public void volver() {
		screen.clickBoton(BotonesCrearModificarMotivo.Volver.ordinal());
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String encabezado_crud, String[] labels_crud) {
		logger.debug("Crear Moneda aceptada: inicio análisis sintáctico");
		return ((CrearMonedaAceptadaScreen) screen).sintacticAnalysis(titulo, subtitulo, botones, encabezado_crud, labels_crud);
	}

}
