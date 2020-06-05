package ventanasITATAHost.ConfiguracionSistema.TarifasMoneda.Calendario;


import bloques.CrudRangoFechaHoraBlock;
import procesosComunes.Error;
import procesosITATAHost.ConfiguracionSistema.TarifasMoneda.Calendario.OpcionesCrearModificarCalendarioDesplegable;
import procesosITATAHost.ConfiguracionSistema.TarifasMoneda.Calendario.OpcionesCrearModificarCalendarioFechaHora;
import procesosITATAHost.ConfiguracionSistema.TarifasMoneda.Calendario.OpcionesCrearModificarCalendarioRangoFechaHora;
import unidadesGraficas.TipoBy;
import ventanasComunes.Screen;

public class CrearModificarCalendarioScreen extends Screen {
	
	private CrudRangoFechaHoraBlock crud;
	
	private final static TipoBy[] i_botones= {TipoBy.ID,TipoBy.ID};
	private final static String[] str_botones= {"ctl00_ContentZone_BtnSubmit","ctl00_ContentZone_BtnCancel"};
	private final TipoBy i_encabezado=null;
	private final String str_encabezado=null;
	private final int num_criterios_campo=0;
	private final int num_criterios_desplegable=1;
	private final int num_criterios_fecha=0;
	private final TipoBy[] i_label={TipoBy.ID};
	private final String[] str_label={"ctl00_ContentZone_LblType"};
	private final TipoBy[] i_field={TipoBy.ID};
	private final String[] str_field={"ctl00_ContentZone_CmbType"};
	private final TipoBy[][] i_tooltipcampo=null;
	private final String[][] str_tooltipcampo=null;
	private final TipoBy[][][] i_tooltipfecha=null;
	private final String[][][] str_tooltipfecha=null;
	
	private final int num_criterios_rangofecha=1;
	private TipoBy[][] i_labelrango={{TipoBy.ID,TipoBy.ID}};
	private String[][] str_labelrango={{null,"ctl00_ContentZone_dt_from_lbl_Description","ctl00_ContentZone_dt_to_lbl_Description"}};
	private TipoBy[][] i_fieldrango={{TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID}};
	private String[][] str_fieldrango={{"ctl00_ContentZone_ChkTo","ctl00_ContentZone_dt_from_box_date",
					  null,"ctl00_ContentZone_dt_to_box_date", null}};
	private final TipoBy[][][][] i_tooltiprango= {{{{TipoBy.ID,TipoBy.ID},{TipoBy.ID,TipoBy.ID}}}};
	private final String[][][][] str_tooltiprango= {{{{"ctl00_ContentZone_dt_from_ValDateReq","ctl00_ContentZone_dt_from_ValDateFormat"},
												   {"ctl00_ContentZone_dt_to_ValDateReq","ctl00_ContentZone_dt_to_ValDateFormat"}}}};
	
	public CrearModificarCalendarioScreen() {
		
		super(null, null, null, null, i_botones, str_botones, null, null);
		crud=new CrudRangoFechaHoraBlock(i_encabezado, str_encabezado,num_criterios_campo, num_criterios_desplegable,
				 num_criterios_fecha,num_criterios_rangofecha, i_label, str_label, i_field, str_field, i_tooltipcampo,
				 str_tooltipcampo,i_tooltipfecha, str_tooltipfecha, i_labelrango,str_labelrango,i_fieldrango,str_fieldrango,
				 i_tooltiprango, str_tooltiprango);
	}

	public String leerOpcionDesplegable(OpcionesCrearModificarCalendarioDesplegable desplegable,String opcion) {
		return crud.leerCriterioDesplegable(desplegable.ordinal());
	}
	
	public Error seleccionarOpcion(OpcionesCrearModificarCalendarioDesplegable opcion, String valor) {
		return crud.desplegarCriterioDesplegable(opcion.ordinal(), valor);
	}
	
	public void clicarCheckDesdeHasta(OpcionesCrearModificarCalendarioRangoFechaHora opcion) {
		crud.clickCriterioCheck(opcion.ordinal());
	}
	
	public boolean estaCheckActivado(OpcionesCrearModificarCalendarioRangoFechaHora opcion) {
		return crud.estaChequeadoCriterioRangoFechaHora(opcion.ordinal());
	}
	
	public String leerRangoFechaHoraFecha(OpcionesCrearModificarCalendarioRangoFechaHora opcion, OpcionesCrearModificarCalendarioFechaHora tipo) {
		switch (tipo) {
			case Desde: return crud.leerFechaDesdeFechaCriterio(opcion.ordinal());
			case Hasta: return crud.leerFechaHastaFechaCriterio(opcion.ordinal());
			default: return null;
		}
	}
	
	public void escribirRangoFechaHoraFecha(OpcionesCrearModificarCalendarioRangoFechaHora opcion, OpcionesCrearModificarCalendarioFechaHora tipo, String fecha) {
		switch (tipo) {
			case Desde: crud.escribirFechaDesdeFechaCriterio(opcion.ordinal(), fecha); break;
			case Hasta: crud.escribirFechaHastaFechaCriterio(opcion.ordinal(), fecha); break;
			default: break;
		}
	}
	
	public void borrarRangoFechaHoraFecha(OpcionesCrearModificarCalendarioRangoFechaHora opcion, OpcionesCrearModificarCalendarioFechaHora tipo) {
		switch (tipo) {
			case Desde: crud.borrarFechaDesdeFechaCriterio(opcion.ordinal()); break;
			case Hasta: crud.borrarFechaHastaFechaCriterio(opcion.ordinal()); break;
			default: break;
		}
	}
	
	public boolean sintacticAnalysis(String[] botones, String[] labelCriteriosBusqueda, String[][] labels_rangofecha) {
		
		boolean resultado=super.sintacticAnalysis(null, null, botones);
		return crud.sintacticAnalysis(null,labelCriteriosBusqueda, labels_rangofecha) && resultado;

	}
}
