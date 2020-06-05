package procesosITATAHost.ConfiguracionSistema.TarifasMoneda.Calendario;

import org.apache.log4j.Logger;
import procesosComunes.Error;
import ventanasComunes.FormScreen;
import ventanasComunes.Screen;
import ventanasITATAHost.ConfiguracionSistema.TarifasMoneda.Calendario.CrearModificarCalendarioScreen;
import procesosITATAHost.ConfiguracionSistema.TarifasMoneda.Calendario.BotonesCrearModificarCalendario;
import procesosITATAHost.ConfiguracionSistema.TarifasMoneda.Calendario.OpcionesCrearModificarCalendarioDesplegable;
import procesosITATAHost.ConfiguracionSistema.TarifasMoneda.Calendario.OpcionesCrearModificarCalendarioFechaHora;
import procesosITATAHost.ConfiguracionSistema.TarifasMoneda.Calendario.OpcionesCrearModificarCalendarioRangoFechaHora;

public class CrearModificarCalendarioProcess {
	
	Screen screen;
	
	private final static Logger logger = Logger.getLogger(CrearModificarCalendarioProcess.class);
	 
	public CrearModificarCalendarioProcess() {
		try {
			screen=new CrearModificarCalendarioScreen();
		}
		catch (RuntimeException e) {
			System.exit(0);
		}
	}
	
	public String leerOpcionTipoDia() {
		return ((FormScreen) screen).leerOpcionDesplegable(OpcionesCrearModificarCalendarioDesplegable.TipoDia.ordinal());
	}
	
	public Error seleccionarOpcionTipoDia(String opcion) {
		return ((FormScreen) screen).desplegarOpcion(OpcionesCrearModificarCalendarioDesplegable.TipoDia.ordinal(),opcion);
	}
	
	public void clicarCheckDesdeHasta() {
		((CrearModificarCalendarioScreen) screen).clicarCheckDesdeHasta(OpcionesCrearModificarCalendarioRangoFechaHora.DesdeHasta);
	}
	
	public boolean estaCheckActivado() {
		return ((CrearModificarCalendarioScreen) screen).estaCheckActivado(OpcionesCrearModificarCalendarioRangoFechaHora.DesdeHasta);
	}
	
	public String leerFechaDesde(String valor) {
		return ((CrearModificarCalendarioScreen) screen).leerRangoFechaHoraFecha(OpcionesCrearModificarCalendarioRangoFechaHora.DesdeHasta, OpcionesCrearModificarCalendarioFechaHora.Desde);
	}
	
	public String leerFechaHasta(String valor) {
		return ((CrearModificarCalendarioScreen) screen).leerRangoFechaHoraFecha(OpcionesCrearModificarCalendarioRangoFechaHora.DesdeHasta, OpcionesCrearModificarCalendarioFechaHora.Hasta);
	}
	
	public void escribirFechaDesde(String valor) {
		((CrearModificarCalendarioScreen) screen).escribirRangoFechaHoraFecha(OpcionesCrearModificarCalendarioRangoFechaHora.DesdeHasta, OpcionesCrearModificarCalendarioFechaHora.Desde, valor);
	}
	
	public void escribirFechaHasta(String valor) {
		((CrearModificarCalendarioScreen) screen).escribirRangoFechaHoraFecha(OpcionesCrearModificarCalendarioRangoFechaHora.DesdeHasta, OpcionesCrearModificarCalendarioFechaHora.Hasta, valor);
	}
	
	public void borrarFechaDesde() {
		((CrearModificarCalendarioScreen) screen).borrarRangoFechaHoraFecha(OpcionesCrearModificarCalendarioRangoFechaHora.DesdeHasta, OpcionesCrearModificarCalendarioFechaHora.Desde);
	}
	
	public void borrarFechaHasta() {
		((CrearModificarCalendarioScreen) screen).borrarRangoFechaHoraFecha(OpcionesCrearModificarCalendarioRangoFechaHora.DesdeHasta, OpcionesCrearModificarCalendarioFechaHora.Hasta);
	}
	
	public void confirmar() {
		((FormScreen) screen).clickBotonCrud(BotonesCrearModificarCalendario.Confirmar.ordinal());
	}
	
	public void cancelar() {
		((FormScreen) screen).clickBoton(BotonesCrearModificarCalendario.Cancelar.ordinal());
	}
	
	public boolean sintacticAnalysis(String[] botones, String[] labelCriteriosBusqueda, String[][] labels_rangofecha) {
		logger.debug("Crear/Modificar Calendario: inicio análisis sintáctico");
		return ((CrearModificarCalendarioScreen) screen).sintacticAnalysis(botones, labelCriteriosBusqueda, labels_rangofecha);
	}

}
