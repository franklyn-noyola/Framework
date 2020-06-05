package ventanasITATAHost.General;

import bloques.CrudBlock;
import procesosITATAHost.General.ReimpresionInformes.OpcionesReimpresionInformesCampo;
import procesosITATAHost.General.ReimpresionInformes.OpcionesReimpresionInformesDesplegable;
import procesosITATAHost.General.ReimpresionInformes.OpcionesReimpresionInformesFecha;
import unidadesGraficas.TipoBy;
import ventanasComunes.Screen;

public class ReimpresionInformesScreen extends Screen {

	private CrudBlock crud;
	
	private TipoBy i_encabezado=TipoBy.ID;
	private String str_encabezado="ctl00_ContentZone_searchParamSep_Lbl_SubtitleInfo";
	private final int num_criterios_campo=1;
	private final int num_criterios_desplegable=1;
	private final int num_criterios_fecha=2;
	private TipoBy[] i_label={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_label={"ctl00_ContentZone_txt_operator_lbl_Description","ctl00_ContentZone_cmb_reportType_lbl_Description",
			"ctl00_ContentZone_dt_from_lbl_Description","ctl00_ContentZone_dt_to_lbl_Description",
			"ctl00_ContentZone_dt_from_lbl_hour","ctl00_ContentZone_dt_to_lbl_hour"};
	private TipoBy[] i_field={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_field={"ctl00_ContentZone_txt_operator_box_data","ctl00_ContentZone_cmb_reportType_cmb_dropdown","ctl00_ContentZone_dt_from_box_date","ctl00_ContentZone_dt_from_box_hour","ctl00_ContentZone_dt_to_box_date","ctl00_ContentZone_dt_to_box_hour"};
	private TipoBy[][] i_tooltipcampo={{null}};
	private String[][] str_tooltipcampo={{null}};
	private TipoBy[][][] i_tooltipfecha={{{TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID},{TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID}}};
	private String[][][] str_tooltipfecha={{{"ctl00_ContentZone_dt_from_ValDateFormat","ctl00_ContentZone_dt_from_ValDateReq",
						"ctl00_ContentZone_dt_from_ValTimeReq","ctl00_ContentZone_dt_from_ValTimeFormat"},
						{"ctl00_ContentZone_dt_to_ValDateFormat","ctl00_ContentZone_dt_to_ValDateReq",
						 "ctl00_ContentZone_dt_to_ValTimeReq","ctl00_ContentZone_dt_to_ValTimeFormat"}}};
	
	public ReimpresionInformesScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, TipoBy[] i_botones, String[] str_botones, TipoBy i_msgerror, String str_msgerror) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,i_msgerror,str_msgerror);
		crud=new CrudBlock(i_encabezado, str_encabezado, num_criterios_campo, num_criterios_desplegable,
				num_criterios_fecha, i_label, str_label, i_field, str_field,i_tooltipcampo,str_tooltipcampo,
				i_tooltipfecha,str_tooltipfecha);
	}
	
	public void desplegarOpcion(OpcionesReimpresionInformesDesplegable opcion,String valor) {
		crud.desplegarCriterioDesplegable(opcion.ordinal(),valor);
	}
	
	public void escribirOpcion(OpcionesReimpresionInformesCampo opcion, String valor) {
		crud.escribirOpcion(opcion.ordinal(), valor);
	}
	
	public void escribirFecha(OpcionesReimpresionInformesFecha opcion, String fecha, String hora) {
		crud.escribirFecha(opcion.ordinal(), fecha, hora);
	}
	
	public void borrarFecha(OpcionesReimpresionInformesFecha opcion) {
		crud.borrarFecha(opcion.ordinal());
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String encabezado, String[] labels) {
		
		boolean resultado=super.sintacticAnalysis(titulo, subtitulo, botones);
		return crud.sintacticAnalysis(encabezado,labels) && resultado;

	}
}
