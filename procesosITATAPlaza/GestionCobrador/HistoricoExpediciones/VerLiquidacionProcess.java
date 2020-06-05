package procesosITATAPlaza.GestionCobrador.HistoricoExpediciones;

import org.apache.log4j.Logger;
import unidadesGraficas.TipoBy;
import ventanasITATAPlaza.GestionCobrador.HistoricoExpediciones.VerLiquidacionScreen;
import procesosComunes.Process;
import procesosITATAHost.GestionCobrador.HistoricoExpediciones.BotonesVerLiquidacion;
import procesosITATAHost.GestionCobrador.HistoricoExpediciones.OpcionesVerLiquidacionCampo;
import procesosITATAHost.GestionCobrador.HistoricoExpediciones.OpcionesVerLiquidacionFecha;
import procesosITATAHost.GestionCobrador.HistoricoExpediciones.OpcionesVerLiquidacionVariable;

public class VerLiquidacionProcess extends Process {
	
	private TipoBy[] i_botones= {TipoBy.ID};
	private String[] str_botones= {"ctl00_ButtonsZone_BtnBack_IB_Label"};
	private String str_msgerror="ctl00_ContentZone_lblMsg"; //Esto es particular de esta funcionalidad
	
	private final static Logger logger = Logger.getLogger(VerLiquidacionProcess.class);
		
	public VerLiquidacionProcess() {
		screen=new VerLiquidacionScreen(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,
				i_msgerror,str_msgerror);
	}
	
	public String leerCampoNumBolsa() {
		return ((VerLiquidacionScreen) screen).leerOpcion(OpcionesVerLiquidacionCampo.NumBolsa);
	}
	
	public String leerCampoPlaza() {
		return ((VerLiquidacionScreen) screen).leerOpcionDesplegable(OpcionesVerLiquidacionDesplegable.Plaza);
	}
	
	public String leerCampoNumTurno() {
		return ((VerLiquidacionScreen) screen).leerOpcionVariable(OpcionesVerLiquidacionVariable.NumTurno);
	}
	
	public String leerCampoLiquidacion() {
		return ((VerLiquidacionScreen) screen).leerOpcionVariable(OpcionesVerLiquidacionVariable.Liquidacion);
	}
	
	public String leerHoraLiquidacionFecha() {
		return ((VerLiquidacionScreen) screen).leerFecha(OpcionesVerLiquidacionFecha.HoraLiquidacion);
	}
	
	public String leerHoraLiquidacionHora() {
		return ((VerLiquidacionScreen) screen).leerHora(OpcionesVerLiquidacionFecha.HoraLiquidacion);
	}
	
	public String leerValorEfectivoTablaTotales() {
		return ((VerLiquidacionScreen) screen).leerValorEfectivoTablaTotales();
	}
	
	public String leerValorChequesTablaTotales() {
		return ((VerLiquidacionScreen) screen).leerValorChequesTablaTotales();
	}
	
	public String leerValorTotalTablaTotales() {
		return ((VerLiquidacionScreen) screen).leerValorTotalTablaTotales();
	}
	
	public boolean validarTablaValores() {
		return ((VerLiquidacionScreen) screen).validarTablaValores();
	}
	
	public boolean validarTablaTotales(String total) {
		return ((VerLiquidacionScreen) screen).validarTablaTotales(total);
	}
	
	public void volver() {
		screen.clickBoton(BotonesVerLiquidacion.Volver.ordinal());
	}
	
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String encabezadovar,
			String[] labelsVariables, String[] valoresVariables, String[] labels, String encabezado2,
			String[] columnasgrid, String idConcesionaria, String[][] monedas, String[] cabeceraSuperiorTotales,
			String[] cabeceraInferiorTotales, String[] columna1) {
		logger.debug("Detalles Ver Liquidación: inicio análisis sintáctico");
		return ((VerLiquidacionScreen) screen).sintacticAnalysis(titulo, subtitulo, botones, encabezadovar,labelsVariables,
				valoresVariables,labels, encabezado2, columnasgrid, idConcesionaria, monedas, cabeceraSuperiorTotales,
				cabeceraInferiorTotales, columna1);
	}

}
