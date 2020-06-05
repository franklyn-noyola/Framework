package procesosITATAPlaza.GestionCobrador.HistoricoExpediciones;

import java.sql.ResultSet;

import org.apache.log4j.Logger;
import procesosComunes.Error;
import unidadesGraficas.TipoBy;
import ventanasComunes.FormScreen;
import ventanasITATAHost.GestionCobrador.HistoricoExpediciones.HistoricoExpedicionesScreen;
import procesosComunes.FormProcess;
import procesosITATAHost.GestionCobrador.HistoricoExpediciones.BotonesCrudHistoricoExpediciones;
import procesosITATAHost.GestionCobrador.HistoricoExpediciones.BotonesHistoricoExpediciones;
import procesosITATAHost.GestionCobrador.HistoricoExpediciones.OpcionesHistoricoExpedicionesFechaHora;
import procesosITATAHost.GestionCobrador.HistoricoExpediciones.OpcionesHistoricoExpedicionesRangoFechaHora;
import testsComunes.RutinasTestComunes;

public class HistoricoExpedicionesProcess extends FormProcess {
	
	private final int num_columnas=7;
	private final TipoBy[] i_botones= {TipoBy.ID,TipoBy.ID};
	private final String[] str_botones= {"ctl00_ButtonsZone_BtnRefresh_IB_Label","ctl00_ButtonsZone_BtnBack_IB_Label"};
	private final TipoBy i_encabezado=null;
	private final String str_encabezado=null;
	private final int num_criterios_campo=0;
	private final int num_criterios_desplegable=1;
	private final int num_criterios_fecha=0;
	private final TipoBy[] i_label={TipoBy.ID};
	private final String[] str_label={"ctl00_ContentZone_companyPlazaLane_cmb_plaza_lbl_Description"};
	private final TipoBy[] i_field={TipoBy.ID};
	private final String[] str_field={"ctl00_ContentZone_companyPlazaLane_cmb_plaza_cmb_dropdown"};
	private final TipoBy[][] i_tooltipcampo=null;
	private final String[][] str_tooltipcampo=null;
	private final TipoBy[][][] i_tooltipfecha=null;
	private final String[][][] str_tooltipfecha=null;
	
	private final TipoBy i_leyendamostrado=TipoBy.ID;
	private final String str_leyendamostrado="ctl00_ContentZone_tablePager_LblCounter"; // Es la misma de siempre
	private final TipoBy i_cabecera=TipoBy.XPATH; // Es la misma de siempre
	private final String str_cabecera="//*[@id='ctl00_ContentZone_TblResults']/tbody/"; // Es la misma de siempre
	private final TipoBy[] i_botonescrud={TipoBy.ID};
	private final String[] str_botonescrud= {"ctl00_ContentZone_BtnDetails"};
	private final TipoBy[] i_botonesgrid={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private final String[] str_botonesgrid={"ctl00_ContentZone_tablePager_BtnFirst","ctl00_ContentZone_tablePager_BtnPrev","ctl00_ContentZone_tablePager_BtnNext","ctl00_ContentZone_tablePager_BtnLast"};

	
	private final static Logger logger = Logger.getLogger(HistoricoExpedicionesProcess.class);
	 
	public HistoricoExpedicionesProcess() {
		try {
			screen=new HistoricoExpedicionesScreen(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones,
					str_botones, i_msgerror,str_msgerror,  i_encabezado,  str_encabezado,
					 num_criterios_campo, num_criterios_desplegable, num_criterios_fecha,
					 i_label,  str_label,  i_field,  str_field,  i_tooltipcampo,  str_tooltipcampo,
					 i_tooltipfecha,  str_tooltipfecha, i_botonescrud,  str_botonescrud,  i_cabecera,  str_cabecera, 
					 i_leyendamostrado,  str_leyendamostrado,  i_botonesgrid,
					 str_botonesgrid,  num_columnas,1);
		}
		catch (RuntimeException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public String leerOpcionPlaza() {
		return ((FormScreen) screen).leerOpcionDesplegable(OpcionesHistoricoExpedicionesDesplegable.Plaza.ordinal());
	}
	
	public Error seleccionarOpcionPlaza(String opcion) {
		return ((FormScreen) screen).desplegarOpcion(OpcionesHistoricoExpedicionesDesplegable.Plaza.ordinal(),opcion);
	}
	
	public void clicarCheckDesdeHasta() {
		((HistoricoExpedicionesScreen) screen).clicarCheckDesdeHasta(OpcionesHistoricoExpedicionesRangoFechaHora.DesdeHasta);
	}
	
	public boolean estaCheckActivado() {
		return ((HistoricoExpedicionesScreen) screen).estaCheckActivado(OpcionesHistoricoExpedicionesRangoFechaHora.DesdeHasta);
	}
	
	public String leerFechaDesde(String valor) {
		return ((HistoricoExpedicionesScreen) screen).leerRangoFechaHoraFecha(OpcionesHistoricoExpedicionesRangoFechaHora.DesdeHasta, OpcionesHistoricoExpedicionesFechaHora.Desde);
	}
	
	public String leerFechaHasta(String valor) {
		return ((HistoricoExpedicionesScreen) screen).leerRangoFechaHoraFecha(OpcionesHistoricoExpedicionesRangoFechaHora.DesdeHasta, OpcionesHistoricoExpedicionesFechaHora.Hasta);
	}
	
	public String leerHoraDesde(String valor) {
		return ((HistoricoExpedicionesScreen) screen).leerRangoFechaHoraHora(OpcionesHistoricoExpedicionesRangoFechaHora.DesdeHasta, OpcionesHistoricoExpedicionesFechaHora.Desde);
	}
	
	public String leerHoraHasta(String valor) {
		return ((HistoricoExpedicionesScreen) screen).leerRangoFechaHoraHora(OpcionesHistoricoExpedicionesRangoFechaHora.DesdeHasta, OpcionesHistoricoExpedicionesFechaHora.Hasta);
	}
	
	public void escribirFechaDesde(String valor) {
		((HistoricoExpedicionesScreen) screen).escribirRangoFechaHoraFecha(OpcionesHistoricoExpedicionesRangoFechaHora.DesdeHasta, OpcionesHistoricoExpedicionesFechaHora.Desde, valor+"\n");
	}
	
	public void escribirFechaHasta(String valor) {
		((HistoricoExpedicionesScreen) screen).escribirRangoFechaHoraFecha(OpcionesHistoricoExpedicionesRangoFechaHora.DesdeHasta, OpcionesHistoricoExpedicionesFechaHora.Hasta, valor+"\n");
	}
	
	public void escribirHoraDesde(String valor) {
		((HistoricoExpedicionesScreen) screen).escribirRangoFechaHoraHora(OpcionesHistoricoExpedicionesRangoFechaHora.DesdeHasta, OpcionesHistoricoExpedicionesFechaHora.Desde, valor+"\n");
	}
	
	public void escribirHoraHasta(String valor) {
		((HistoricoExpedicionesScreen) screen).escribirRangoFechaHoraHora(OpcionesHistoricoExpedicionesRangoFechaHora.DesdeHasta, OpcionesHistoricoExpedicionesFechaHora.Hasta, valor+"\n");
	}
	
	public void borrarFechaDesde() {
		((HistoricoExpedicionesScreen) screen).borrarRangoFechaHoraFecha(OpcionesHistoricoExpedicionesRangoFechaHora.DesdeHasta, OpcionesHistoricoExpedicionesFechaHora.Desde);
	}
	
	public void borrarFechaHasta() {
		((HistoricoExpedicionesScreen) screen).borrarRangoFechaHoraFecha(OpcionesHistoricoExpedicionesRangoFechaHora.DesdeHasta, OpcionesHistoricoExpedicionesFechaHora.Hasta);
	}
	
	public void borrarHoraDesde() {
		((HistoricoExpedicionesScreen) screen).borrarRangoFechaHoraHora(OpcionesHistoricoExpedicionesRangoFechaHora.DesdeHasta, OpcionesHistoricoExpedicionesFechaHora.Desde);
	}
	
	public void borrarHoraHasta() {
		((HistoricoExpedicionesScreen) screen).borrarRangoFechaHoraHora(OpcionesHistoricoExpedicionesRangoFechaHora.DesdeHasta, OpcionesHistoricoExpedicionesFechaHora.Hasta);
	}
	
	public boolean hayErrorValidacionFechaDesde() {
		return ((HistoricoExpedicionesScreen) screen).hayErrorValidacionFecha(OpcionesHistoricoExpedicionesRangoFechaHora.DesdeHasta, OpcionesHistoricoExpedicionesFechaHora.Desde);
	}
	
	public boolean hayErrorValidacionRequeridoFechaDesde() {
		return ((HistoricoExpedicionesScreen) screen).hayErrorValidacionFechaRequerido(OpcionesHistoricoExpedicionesRangoFechaHora.DesdeHasta, OpcionesHistoricoExpedicionesFechaHora.Desde);
	}
	
	public boolean hayErrorValidacionFormatoFechaDesde() {
		return ((HistoricoExpedicionesScreen) screen).hayErrorValidacionFechaFormato(OpcionesHistoricoExpedicionesRangoFechaHora.DesdeHasta,  OpcionesHistoricoExpedicionesFechaHora.Desde);
	}
	
	public boolean hayErrorValidacionFechaHasta() {
		return ((HistoricoExpedicionesScreen) screen).hayErrorValidacionFecha(OpcionesHistoricoExpedicionesRangoFechaHora.DesdeHasta, OpcionesHistoricoExpedicionesFechaHora.Hasta);
	}
	
	public boolean hayErrorValidacionRequeridoFechaHasta() {
		return ((HistoricoExpedicionesScreen) screen).hayErrorValidacionFechaRequerido(OpcionesHistoricoExpedicionesRangoFechaHora.DesdeHasta, OpcionesHistoricoExpedicionesFechaHora.Hasta);
	}
	
	public boolean hayErrorValidacionFormatoFechaHasta() {
		return ((HistoricoExpedicionesScreen) screen).hayErrorValidacionFechaFormato(OpcionesHistoricoExpedicionesRangoFechaHora.DesdeHasta,  OpcionesHistoricoExpedicionesFechaHora.Hasta);
	}
	
	
	public boolean hayErrorValidacionHoraDesde() {
		return ((HistoricoExpedicionesScreen) screen).hayErrorValidacionHora(OpcionesHistoricoExpedicionesRangoFechaHora.DesdeHasta, OpcionesHistoricoExpedicionesFechaHora.Desde);
	}
	
	public boolean hayErrorValidacionRequeridoHoraDesde() {
		return ((HistoricoExpedicionesScreen) screen).hayErrorValidacionHoraRequerido(OpcionesHistoricoExpedicionesRangoFechaHora.DesdeHasta, OpcionesHistoricoExpedicionesFechaHora.Desde);
	}
	
	public boolean hayErrorValidacionFormatoHoraDesde() {
		return ((HistoricoExpedicionesScreen) screen).hayErrorValidacionHoraFormato(OpcionesHistoricoExpedicionesRangoFechaHora.DesdeHasta,  OpcionesHistoricoExpedicionesFechaHora.Desde);
	}
	
	public boolean hayErrorValidacionHoraHasta() {
		return ((HistoricoExpedicionesScreen) screen).hayErrorValidacionHora(OpcionesHistoricoExpedicionesRangoFechaHora.DesdeHasta, OpcionesHistoricoExpedicionesFechaHora.Hasta);
	}
	
	public boolean hayErrorValidacionRequeridoHoraHasta() {
		return ((HistoricoExpedicionesScreen) screen).hayErrorValidacionHoraRequerido(OpcionesHistoricoExpedicionesRangoFechaHora.DesdeHasta, OpcionesHistoricoExpedicionesFechaHora.Hasta);
	}
	
	public boolean hayErrorValidacionFormatoHoraHasta() {
		return ((HistoricoExpedicionesScreen) screen).hayErrorValidacionHoraFormato(OpcionesHistoricoExpedicionesRangoFechaHora.DesdeHasta,  OpcionesHistoricoExpedicionesFechaHora.Hasta);
	}
	
	public void detalles() {
		((FormScreen) screen).clickBotonCrud(BotonesCrudHistoricoExpediciones.Detalles.ordinal());
	}
	
	public void actualizar() {
		((FormScreen) screen).clickBoton(BotonesHistoricoExpediciones.Actualizar.ordinal());
	}
	
	public void volver() {
		screen.clickBoton(BotonesHistoricoExpediciones.Volver.ordinal());
	}
	
	public boolean buscarRegistroBDEnTabla (ResultSet rs) {
		String[] columnas= {"Expedición"};
		String[] valores=new String[1];
		try {
			valores[0]=rs.getString("banktour").trim();
			String[] resultado=obtenerFilaTabla(columnas, valores);
			while (resultado==null && !ultimaTabla()) {
				pulsarBotonTablaSiguiente();
				recargarTabla();
				resultado=obtenerFilaTabla(columnas, valores);
			}
			if (resultado!=null) {
				return compararRegistroDBFilaTabla(rs, resultado);
			}
			else {
				logger.error("No se ha encontrado la expedición "+valores[0]+" en la tabla");
				return false;
			}
		}
		catch (Exception e) {
			logger.fatal("Error al acceder a la BD");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean buscarRegistroTablaEnBD (ResultSet rs, String elemento) {
		try {
			String[] columnas={"Expedición"};
			String[] valores=new String[1];
			valores[0]=elemento;
			String[] resultado=obtenerFilaTabla(columnas, valores);
			return compararRegistroDBFilaTabla(rs, resultado);
		}
		catch (Exception e) {
			logger.fatal("Error al acceder a la BD");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean compararRegistroDBFilaTabla(ResultSet rs, String[] resultado) {
		try {
			String [] row=new String[resultado.length];
			row[0]=rs.getString("time").trim();
			row[0]=formatearFechaExpedicion(row[0]);
			row[1]=rs.getString("banktour").trim();
			row[2]=rs.getString("operator").trim();
			row[3]=resultado[3]; // OJO
			row[4]=rs.getString("vanplate");
			if (row[4]!=null)  {
				row[4]=row[4].trim();
			}
			else {
				row[4]=" ";
			}
			row[5]=rs.getString("employees");
			if (row[5]!=null)  {
				row[5]=row[5].trim();
			}
			else {
				row[5]=" ";
			}
			row[6]=rs.getString("comment");
			if (row[6]!=null)  {
				row[6]=row[6].trim();
			}
			else {
				row[6]=" ";
			}
			return RutinasTestComunes.assertArrayEquals(row,resultado);
		}
		catch (Exception e) {
			logger.fatal("Error al acceder a la BD");
			e.printStackTrace();
			return false;
		}
	}
	
	private String formatearFechaExpedicion(String fecha) {
		String año=fecha.substring(0,4);
		String mes=fecha.substring(4,6);
		String dia=fecha.substring(6,8);
		String hora=fecha.substring(8,10);
		String min=fecha.substring(10,12);
		String seg=fecha.substring(12,14);
		return dia+"/"+mes+"/"+año+" "+hora+":"+min+":"+seg;
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String[] botonesCRUD,
			String[] labelCriteriosBusqueda, String[][] labels_rangofecha, String[] columnasgrid, String[] botonesgrid) {
		logger.debug("Histórico expediciones: inicio análisis sintáctico");
		return ((HistoricoExpedicionesScreen) screen).sintacticAnalysis(titulo, subtitulo, botones, null, 
				labelCriteriosBusqueda, labels_rangofecha, botonesCRUD, columnasgrid, botonesgrid);
	}

}
