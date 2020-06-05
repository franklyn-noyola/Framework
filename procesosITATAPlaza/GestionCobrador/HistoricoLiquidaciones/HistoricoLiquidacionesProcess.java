package procesosITATAPlaza.GestionCobrador.HistoricoLiquidaciones;

import java.sql.ResultSet;

import org.apache.log4j.Logger;
import procesosComunes.Error;
import unidadesGraficas.TipoBy;
import ventanasComunes.FormScreen;
import ventanasITATAHost.GestionCobrador.HistoricoLiquidaciones.HistoricoLiquidacionesScreen;
import procesosComunes.FormProcess;
import procesosITATAHost.GestionCobrador.HistoricoLiquidaciones.BotonesCrudHistoricoLiquidaciones;
import procesosITATAHost.GestionCobrador.HistoricoLiquidaciones.BotonesHistoricoLiquidaciones;
import procesosITATAHost.GestionCobrador.HistoricoLiquidaciones.OpcionesHistoricoLiquidacionesFechaHora;
import procesosITATAHost.GestionCobrador.HistoricoLiquidaciones.OpcionesHistoricoLiquidacionesRangoFechaHora;
import testsComunes.RutinasTestComunes;

public class HistoricoLiquidacionesProcess extends FormProcess {
	
	private final int num_columnas=7;
	private final TipoBy[] i_botones= {TipoBy.ID,TipoBy.ID};
	private final String[] str_botones= {"ctl00_ButtonsZone_BtnRefresh_IB_Label","ctl00_ButtonsZone_BtnBack_IB_Label"};
	private final TipoBy i_encabezado=null;
	private final String str_encabezado=null;
	private final int num_criterios_campo=1;
	private final int num_criterios_desplegable=2;
	private final int num_criterios_fecha=0;
	private final TipoBy[] i_label={TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private final String[] str_label={"ctl00_ContentZone_txt_Collector_lbl_Description",
									  "ctl00_ContentZone_companyPlazaLane_cmb_plaza_lbl_Description",
									  "ctl00_ContentZone_cmb_Type_lbl_Description"};
	private final TipoBy[] i_field={TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private final String[] str_field={"ctl00_ContentZone_txt_Collector_box_data",
									  "ctl00_ContentZone_companyPlazaLane_cmb_plaza_cmb_dropdown",
									  "ctl00_ContentZone_cmb_Type_cmb_dropdown"};
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

	
	private final static Logger logger = Logger.getLogger(HistoricoLiquidacionesProcess.class);
	 
	public HistoricoLiquidacionesProcess() {
		try {
			screen=new HistoricoLiquidacionesScreen(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones,
					str_botones, i_msgerror,str_msgerror,  i_encabezado,  str_encabezado,
					 num_criterios_campo, num_criterios_desplegable, num_criterios_fecha,
					 i_label,  str_label,  i_field,  str_field,  i_tooltipcampo,  str_tooltipcampo, i_tooltipfecha,
					 str_tooltipfecha, i_botonescrud,  str_botonescrud,  i_cabecera,  str_cabecera, 
					 i_leyendamostrado,  str_leyendamostrado,  i_botonesgrid,
					 str_botonesgrid,  num_columnas,1);
		}
		catch (RuntimeException e) {
			System.exit(0);
		}
	}
	
	public String leerOpcionPlaza() {
		return ((FormScreen) screen).leerOpcionDesplegable(OpcionesHistoricoLiquidacionesDesplegable.Plaza.ordinal());
	}
	
	public Error seleccionarOpcionPlaza(String opcion) {
		return ((FormScreen) screen).desplegarOpcion(OpcionesHistoricoLiquidacionesDesplegable.Plaza.ordinal(),opcion);
	}
	
	public String leerOpcionTipo() {
		return ((FormScreen) screen).leerOpcionDesplegable(OpcionesHistoricoLiquidacionesDesplegable.Tipo.ordinal());
	}
	
	public Error seleccionarOpcionTipo(String opcion) {
		return ((FormScreen) screen).desplegarOpcion(OpcionesHistoricoLiquidacionesDesplegable.Tipo.ordinal(),opcion);
	}
	
	public void clicarCheckDesdeHasta() {
		((HistoricoLiquidacionesScreen) screen).clicarCheckDesdeHasta(OpcionesHistoricoLiquidacionesRangoFechaHora.DesdeHasta);
	}
	
	public boolean estaCheckActivado() {
		return ((HistoricoLiquidacionesScreen) screen).estaCheckActivado(OpcionesHistoricoLiquidacionesRangoFechaHora.DesdeHasta);
	}
	
	public String leerFechaDesde(String valor) {
		return ((HistoricoLiquidacionesScreen) screen).leerRangoFechaHoraFecha(OpcionesHistoricoLiquidacionesRangoFechaHora.DesdeHasta, OpcionesHistoricoLiquidacionesFechaHora.Desde);
	}
	
	public String leerFechaHasta(String valor) {
		return ((HistoricoLiquidacionesScreen) screen).leerRangoFechaHoraFecha(OpcionesHistoricoLiquidacionesRangoFechaHora.DesdeHasta, OpcionesHistoricoLiquidacionesFechaHora.Hasta);
	}
	
	public String leerHoraDesde(String valor) {
		return ((HistoricoLiquidacionesScreen) screen).leerRangoFechaHoraHora(OpcionesHistoricoLiquidacionesRangoFechaHora.DesdeHasta, OpcionesHistoricoLiquidacionesFechaHora.Desde);
	}
	
	public String leerHoraHasta(String valor) {
		return ((HistoricoLiquidacionesScreen) screen).leerRangoFechaHoraHora(OpcionesHistoricoLiquidacionesRangoFechaHora.DesdeHasta, OpcionesHistoricoLiquidacionesFechaHora.Hasta);
	}
	
	public void escribirFechaDesde(String valor) {
		((HistoricoLiquidacionesScreen) screen).escribirRangoFechaHoraFecha(OpcionesHistoricoLiquidacionesRangoFechaHora.DesdeHasta, OpcionesHistoricoLiquidacionesFechaHora.Desde, valor+"\n");
	}
	
	public void escribirFechaHasta(String valor) {
		((HistoricoLiquidacionesScreen) screen).escribirRangoFechaHoraFecha(OpcionesHistoricoLiquidacionesRangoFechaHora.DesdeHasta, OpcionesHistoricoLiquidacionesFechaHora.Hasta, valor+"\n");
	}
	
	public void escribirHoraDesde(String valor) {
		((HistoricoLiquidacionesScreen) screen).escribirRangoFechaHoraHora(OpcionesHistoricoLiquidacionesRangoFechaHora.DesdeHasta, OpcionesHistoricoLiquidacionesFechaHora.Desde, valor+"\n");
	}
	
	public void escribirHoraHasta(String valor) {
		((HistoricoLiquidacionesScreen) screen).escribirRangoFechaHoraHora(OpcionesHistoricoLiquidacionesRangoFechaHora.DesdeHasta, OpcionesHistoricoLiquidacionesFechaHora.Hasta, valor+"\n");
	}
	
	public void borrarFechaDesde() {
		((HistoricoLiquidacionesScreen) screen).borrarRangoFechaHoraFecha(OpcionesHistoricoLiquidacionesRangoFechaHora.DesdeHasta, OpcionesHistoricoLiquidacionesFechaHora.Desde);
	}
	
	public void borrarFechaHasta() {
		((HistoricoLiquidacionesScreen) screen).borrarRangoFechaHoraFecha(OpcionesHistoricoLiquidacionesRangoFechaHora.DesdeHasta, OpcionesHistoricoLiquidacionesFechaHora.Hasta);
	}
	
	public void borrarHoraDesde() {
		((HistoricoLiquidacionesScreen) screen).borrarRangoFechaHoraHora(OpcionesHistoricoLiquidacionesRangoFechaHora.DesdeHasta, OpcionesHistoricoLiquidacionesFechaHora.Desde);
	}
	
	public void borrarHoraHasta() {
		((HistoricoLiquidacionesScreen) screen).borrarRangoFechaHoraHora(OpcionesHistoricoLiquidacionesRangoFechaHora.DesdeHasta, OpcionesHistoricoLiquidacionesFechaHora.Hasta);
	}
	
	public boolean hayErrorValidacionFechaDesde() {
		return ((HistoricoLiquidacionesScreen) screen).hayErrorValidacionFecha(OpcionesHistoricoLiquidacionesRangoFechaHora.DesdeHasta, OpcionesHistoricoLiquidacionesFechaHora.Desde);
	}
	
	public boolean hayErrorValidacionRequeridoFechaDesde() {
		return ((HistoricoLiquidacionesScreen) screen).hayErrorValidacionFechaRequerido(OpcionesHistoricoLiquidacionesRangoFechaHora.DesdeHasta, OpcionesHistoricoLiquidacionesFechaHora.Desde);
	}
	
	public boolean hayErrorValidacionFormatoFechaDesde() {
		return ((HistoricoLiquidacionesScreen) screen).hayErrorValidacionFechaFormato(OpcionesHistoricoLiquidacionesRangoFechaHora.DesdeHasta,  OpcionesHistoricoLiquidacionesFechaHora.Desde);
	}
	
	public boolean hayErrorValidacionFechaHasta() {
		return ((HistoricoLiquidacionesScreen) screen).hayErrorValidacionFecha(OpcionesHistoricoLiquidacionesRangoFechaHora.DesdeHasta, OpcionesHistoricoLiquidacionesFechaHora.Hasta);
	}
	
	public boolean hayErrorValidacionRequeridoFechaHasta() {
		return ((HistoricoLiquidacionesScreen) screen).hayErrorValidacionFechaRequerido(OpcionesHistoricoLiquidacionesRangoFechaHora.DesdeHasta, OpcionesHistoricoLiquidacionesFechaHora.Hasta);
	}
	
	public boolean hayErrorValidacionFormatoFechaHasta() {
		return ((HistoricoLiquidacionesScreen) screen).hayErrorValidacionFechaFormato(OpcionesHistoricoLiquidacionesRangoFechaHora.DesdeHasta,  OpcionesHistoricoLiquidacionesFechaHora.Hasta);
	}
	
	
	public boolean hayErrorValidacionHoraDesde() {
		return ((HistoricoLiquidacionesScreen) screen).hayErrorValidacionHora(OpcionesHistoricoLiquidacionesRangoFechaHora.DesdeHasta, OpcionesHistoricoLiquidacionesFechaHora.Desde);
	}
	
	public boolean hayErrorValidacionRequeridoHoraDesde() {
		return ((HistoricoLiquidacionesScreen) screen).hayErrorValidacionHoraRequerido(OpcionesHistoricoLiquidacionesRangoFechaHora.DesdeHasta, OpcionesHistoricoLiquidacionesFechaHora.Desde);
	}
	
	public boolean hayErrorValidacionFormatoHoraDesde() {
		return ((HistoricoLiquidacionesScreen) screen).hayErrorValidacionHoraFormato(OpcionesHistoricoLiquidacionesRangoFechaHora.DesdeHasta,  OpcionesHistoricoLiquidacionesFechaHora.Desde);
	}
	
	public boolean hayErrorValidacionHoraHasta() {
		return ((HistoricoLiquidacionesScreen) screen).hayErrorValidacionHora(OpcionesHistoricoLiquidacionesRangoFechaHora.DesdeHasta, OpcionesHistoricoLiquidacionesFechaHora.Hasta);
	}
	
	public boolean hayErrorValidacionRequeridoHoraHasta() {
		return ((HistoricoLiquidacionesScreen) screen).hayErrorValidacionHoraRequerido(OpcionesHistoricoLiquidacionesRangoFechaHora.DesdeHasta, OpcionesHistoricoLiquidacionesFechaHora.Hasta);
	}
	
	public boolean hayErrorValidacionFormatoHoraHasta() {
		return ((HistoricoLiquidacionesScreen) screen).hayErrorValidacionHoraFormato(OpcionesHistoricoLiquidacionesRangoFechaHora.DesdeHasta,  OpcionesHistoricoLiquidacionesFechaHora.Hasta);
	}
	
	public void detalles() {
		((FormScreen) screen).clickBotonCrud(BotonesCrudHistoricoLiquidaciones.Detalles.ordinal());
	}
	
	public void actualizar() {
		((FormScreen) screen).clickBoton(BotonesHistoricoLiquidaciones.Actualizar.ordinal());
	}
	
	public void volver() {
		screen.clickBoton(BotonesHistoricoLiquidaciones.Volver.ordinal());
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
		logger.debug("Histórico liquidaciones: inicio análisis sintáctico");
		return ((HistoricoLiquidacionesScreen) screen).sintacticAnalysis(titulo, subtitulo, botones, null, 
				labelCriteriosBusqueda, labels_rangofecha, botonesCRUD, columnasgrid, botonesgrid);
	}

}
