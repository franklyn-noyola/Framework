package ventanasITATAHost.GestionCobrador.HistoricoExpediciones;


import bloques.CrudRangoFechaHoraBlock;
import procesosITATAHost.GestionCobrador.HistoricoExpediciones.ErroresValidacionFechaHistoricoExpedientes;
import procesosITATAHost.GestionCobrador.HistoricoExpediciones.OpcionesHistoricoExpedicionesFechaHora;
import procesosITATAHost.GestionCobrador.HistoricoExpediciones.OpcionesHistoricoExpedicionesRangoFechaHora;
import unidadesGraficas.TipoBy;
import ventanasComunes.FormRadioScreen;

public class HistoricoExpedicionesScreen extends FormRadioScreen {
	
	private CrudRangoFechaHoraBlock crud;
	
	private final int num_criterios_rangofecha=1;
	private TipoBy[][] i_labelrango={{TipoBy.ID,TipoBy.ID,TipoBy.ID}};
	private String[][] str_labelrango={{null, "ctl00_ContentZone_dateSelector_dt_from_lbl_Description",
					 "ctl00_ContentZone_dateSelector_dt_to_lbl_Description"}};
	private TipoBy[][] i_fieldrango={{TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID}};
	private String[][] str_fieldrango={{"ctl00_ContentZone_dateSelector_chk_dates","ctl00_ContentZone_dateSelector_dt_from_box_date",
					  "ctl00_ContentZone_dateSelector_dt_from_box_hour","ctl00_ContentZone_dateSelector_dt_to_box_date",
					  "ctl00_ContentZone_dateSelector_dt_to_box_hour"}};
	private final TipoBy[][][][] i_tooltiprango={{
			{{TipoBy.ID,TipoBy.ID},
			 {TipoBy.ID,TipoBy.ID}},
			{{TipoBy.ID,TipoBy.ID},
			 {TipoBy.ID,TipoBy.ID}}
			}};
	private final String[][][][] str_tooltiprango={{
		{{"ctl00_ContentZone_dateSelector_dt_from_ValDateReq","ctl00_ContentZone_dateSelector_dt_from_ValDateFormat"},
		 {"ctl00_ContentZone_dateSelector_dt_from_ValTimeReq","ctl00_ContentZone_dateSelector_dt_from_ValTimeFormat"}},
		{{"ctl00_ContentZone_dateSelector_dt_to_ValDateReq","ctl00_ContentZone_dateSelector_dt_to_ValDateFormat"},
		 {"ctl00_ContentZone_dateSelector_dt_to_ValTimeReq","ctl00_ContentZone_dateSelector_dt_to_ValTimeFormat"}}
		}};
	
	public HistoricoExpedicionesScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo,
			String str_subtitulo, TipoBy[] i_botones, String[] str_botones, TipoBy i_msgerror, String str_msgerror,
			TipoBy i_encabezado, String str_encabezado,
			int num_criterios_campo,int num_criterios_desplegable,int num_criterios_fecha,
		TipoBy[] i_label, String[] str_label, TipoBy[] i_field, String[] str_field, TipoBy[][] i_tooltipcampo,
		String[][] str_tooltipcampo, TipoBy[][][] i_tooltipfecha, String[][][] str_tooltipfecha,
		TipoBy[] i_botones_crud, String[] str_botones_crud, TipoBy i_cabecera, String str_cabecera, 
		TipoBy i_leyendamostrado, String str_leyendamostrado, TipoBy[] i_botones_grid,
		String[] str_botones_grid, int num_columnas, int columna_inicial) {
		
		super(i_titulo, str_titulo, i_subtitulo, str_subtitulo, i_botones, str_botones,  i_msgerror,
				  str_msgerror,  i_encabezado,  str_encabezado,
				 num_criterios_campo, num_criterios_desplegable, num_criterios_fecha,
				 i_label,  str_label,  i_field,  str_field,  i_tooltipcampo,  str_tooltipcampo,
				 i_tooltipfecha,  str_tooltipfecha, i_botones_crud,  str_botones_crud,  i_cabecera,  str_cabecera, 
				 i_leyendamostrado,  str_leyendamostrado,  i_botones_grid,
				 str_botones_grid,  num_columnas);
		
		crud=new CrudRangoFechaHoraBlock(i_encabezado, str_encabezado,num_criterios_campo, num_criterios_desplegable,
				 num_criterios_fecha,num_criterios_rangofecha, i_label, str_label, i_field, str_field,i_tooltipcampo,
				 str_tooltipcampo,i_tooltipfecha, str_tooltipfecha, i_labelrango,str_labelrango,i_fieldrango,
				 str_fieldrango, i_tooltiprango, str_tooltiprango);
	}

	public void clicarCheckDesdeHasta(OpcionesHistoricoExpedicionesRangoFechaHora opcion) {
		crud.clickCriterioCheck(opcion.ordinal());
	}
	
	public boolean estaCheckActivado(OpcionesHistoricoExpedicionesRangoFechaHora opcion) {
		return crud.estaChequeadoCriterioRangoFechaHora(opcion.ordinal());
	}
	
	public String leerRangoFechaHoraFecha(OpcionesHistoricoExpedicionesRangoFechaHora opcion, OpcionesHistoricoExpedicionesFechaHora tipo) {
		switch (tipo) {
			case Desde: return crud.leerFechaDesdeFechaCriterio(opcion.ordinal());
			case Hasta: return crud.leerFechaHastaFechaCriterio(opcion.ordinal());
			default: return null;
		}
	}
	
	public String leerRangoFechaHoraHora(OpcionesHistoricoExpedicionesRangoFechaHora opcion, OpcionesHistoricoExpedicionesFechaHora tipo) {
		switch (tipo) {
			case Desde: return crud.leerFechaDesdeHoraCriterio(opcion.ordinal());
			case Hasta: return crud.leerFechaHastaHoraCriterio(opcion.ordinal());
			default: return null;
		}
	}
	
	public void escribirRangoFechaHoraFecha(OpcionesHistoricoExpedicionesRangoFechaHora opcion, OpcionesHistoricoExpedicionesFechaHora tipo, String fecha) {
		switch (tipo) {
			case Desde: crud.escribirFechaDesdeFechaCriterio(opcion.ordinal(), fecha); break;
			case Hasta: crud.escribirFechaHastaFechaCriterio(opcion.ordinal(), fecha); break;
			default: break;
		}
	}
	
	public void escribirRangoFechaHoraHora(OpcionesHistoricoExpedicionesRangoFechaHora opcion, OpcionesHistoricoExpedicionesFechaHora tipo, String hora) {
		switch (tipo) {
			case Desde: crud.escribirFechaDesdeHoraCriterio(opcion.ordinal(), hora); break;
			case Hasta: crud.escribirFechaHastaHoraCriterio(opcion.ordinal(), hora); break;
			default: break;
		}
	}
	
	public void borrarRangoFechaHoraFecha(OpcionesHistoricoExpedicionesRangoFechaHora opcion, OpcionesHistoricoExpedicionesFechaHora tipo) {
		switch (tipo) {
			case Desde: crud.borrarFechaDesdeFechaCriterio(opcion.ordinal()); break;
			case Hasta: crud.borrarFechaHastaFechaCriterio(opcion.ordinal()); break;
			default: break;
		}
	}
	
	public void borrarRangoFechaHoraHora(OpcionesHistoricoExpedicionesRangoFechaHora opcion, OpcionesHistoricoExpedicionesFechaHora tipo) {
		switch (tipo) {
			case Desde: crud.borrarFechaDesdeHoraCriterio(opcion.ordinal()); break;
			case Hasta: crud.borrarFechaHastaHoraCriterio(opcion.ordinal()); break;
			default: break;
		}
	}

	public boolean hayErrorValidacionFecha(OpcionesHistoricoExpedicionesRangoFechaHora opcion, OpcionesHistoricoExpedicionesFechaHora tipo) {
		switch (tipo) {
			case Desde: return crud.hayErrorValidacionFechaDesde(opcion.ordinal(),ErroresValidacionFechaHistoricoExpedientes.Cualquiera.ordinal());
			case Hasta: return crud.hayErrorValidacionFechaHasta(opcion.ordinal(),ErroresValidacionFechaHistoricoExpedientes.Cualquiera.ordinal());
			default: return false;
		}
	}
	
	public boolean hayErrorValidacionFechaRequerido(OpcionesHistoricoExpedicionesRangoFechaHora opcion, OpcionesHistoricoExpedicionesFechaHora tipo) {
		switch (tipo) {
			case Desde: return crud.hayErrorValidacionFechaDesde(opcion.ordinal(),ErroresValidacionFechaHistoricoExpedientes.Requerido.ordinal());
			case Hasta: return crud.hayErrorValidacionFechaHasta(opcion.ordinal(),ErroresValidacionFechaHistoricoExpedientes.Requerido.ordinal());
			default: return false;
		}
	}
	
	public boolean hayErrorValidacionFechaFormato(OpcionesHistoricoExpedicionesRangoFechaHora opcion, OpcionesHistoricoExpedicionesFechaHora tipo) {
		switch (tipo) {
			case Desde: return crud.hayErrorValidacionFechaDesde(opcion.ordinal(),ErroresValidacionFechaHistoricoExpedientes.Formato.ordinal());
			case Hasta: return crud.hayErrorValidacionFechaHasta(opcion.ordinal(),ErroresValidacionFechaHistoricoExpedientes.Formato.ordinal());
			default: return false;
		}
	}
	
	public boolean hayErrorValidacionHora(OpcionesHistoricoExpedicionesRangoFechaHora opcion, OpcionesHistoricoExpedicionesFechaHora tipo) {
		switch (tipo) {
			case Desde: return crud.hayErrorValidacionHoraDesde(opcion.ordinal(),ErroresValidacionFechaHistoricoExpedientes.Cualquiera.ordinal());
			case Hasta: return crud.hayErrorValidacionHoraHasta(opcion.ordinal(),ErroresValidacionFechaHistoricoExpedientes.Cualquiera.ordinal());
			default: return false;
		}
	}
	
	public boolean hayErrorValidacionHoraRequerido(OpcionesHistoricoExpedicionesRangoFechaHora opcion, OpcionesHistoricoExpedicionesFechaHora tipo) {
		switch (tipo) {
			case Desde: return crud.hayErrorValidacionHoraDesde(opcion.ordinal(),ErroresValidacionFechaHistoricoExpedientes.Requerido.ordinal());
			case Hasta: return crud.hayErrorValidacionHoraHasta(opcion.ordinal(),ErroresValidacionFechaHistoricoExpedientes.Requerido.ordinal());
			default: return false;
		}
	}
	
	public boolean hayErrorValidacionHoraFormato(OpcionesHistoricoExpedicionesRangoFechaHora opcion, OpcionesHistoricoExpedicionesFechaHora tipo) {
		switch (tipo) {
			case Desde: return crud.hayErrorValidacionHoraDesde(opcion.ordinal(),ErroresValidacionFechaHistoricoExpedientes.Formato.ordinal());
			case Hasta: return crud.hayErrorValidacionHoraHasta(opcion.ordinal(),ErroresValidacionFechaHistoricoExpedientes.Formato.ordinal());
			default: return false;
		}
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones,  String encabezado,
			String[] labelCriteriosBusqueda, String[][] labels_rangofecha,
			String[] labels_botonescrud, String[] labelTitulosColumnas, String[] labelBotonesGrid) {
		
		boolean resultado=super.sintacticAnalysis(titulo, subtitulo, botones, labels_botonescrud, encabezado, 
				labelCriteriosBusqueda, labelTitulosColumnas, labelBotonesGrid);
		return crud.sintacticAnalysis(encabezado,labelCriteriosBusqueda, labels_rangofecha) && resultado;

	}
}
