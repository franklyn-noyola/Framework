package ventanasITATAHost.General;

import java.util.GregorianCalendar;
import bloques.CrudBlock;
import procesosITATAHost.General.OpcionesRevisionLogsCampo;
import procesosITATAHost.General.OpcionesRevisionLogsDesplegable;
import procesosITATAHost.General.OpcionesRevisionLogsFecha;
import unidadesGraficas.Button;
import unidadesGraficas.TipoBy;
import ventanasComunes.Screen;

public class RevisionLogsScreen extends Screen {

	private CrudBlock crud;
	private Button limpiarButton;
	
	private TipoBy i_encabezado=null;
	private String str_encabezado=null;
	private TipoBy i_limpiarButton=TipoBy.ID;
	private String str_limpiarButton="ctl00_ContentZone_BtnClear";
	private final int num_criterios_campo=2;
	private final int num_criterios_desplegable=2;
	private final int num_criterios_fecha=2;
	private TipoBy[] i_label={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_label={"ctl00_ContentZone_txt_operator_lbl_Description","ctl00_ContentZone_txt_key_lbl_Description",
			"ctl00_ContentZone_cmb_object_lbl_Description","ctl00_ContentZone_cmb_action_lbl_Description",
			"ctl00_ContentZone_dt_from_lbl_Description","ctl00_ContentZone_dt_to_lbl_Description"};
	private TipoBy[] i_field={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_field={"ctl00_ContentZone_txt_operator_box_data","ctl00_ContentZone_txt_key_box_data",
			"ctl00_ContentZone_cmb_object_cmb_dropdown","ctl00_ContentZone_cmb_action_cmb_dropdown",
			"ctl00_ContentZone_dt_from_box_date","ctl00_ContentZone_dt_from_box_hour",
			"ctl00_ContentZone_dt_to_box_date","ctl00_ContentZone_dt_to_box_hour"};
	private TipoBy[][] i_tooltip={{TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID},{TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID}};
	private String[][] str_tooltip={{"ctl00_ContentZone_dt_from_ValDateFormat","ctl00_ContentZone_dt_from_ValDateReq",
		"ctl00_ContentZone_dt_from_ValTimeReq","ctl00_ContentZone_dt_from_ValTimeFormat"},
		{"ctl00_ContentZone_dt_to_ValDateFormat","ctl00_ContentZone_dt_to_ValDateReq",
		 "ctl00_ContentZone_dt_to_ValTimeReq","ctl00_ContentZone_dt_to_ValTimeFormat"}};
	
	public RevisionLogsScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, TipoBy[] i_botones, String[] str_botones, TipoBy i_msgerror, String str_msgerror) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,i_msgerror,str_msgerror);
		crud=new CrudBlock(i_encabezado, str_encabezado, num_criterios_campo, num_criterios_desplegable,
				num_criterios_fecha, i_label, str_label, i_field, str_field,i_tooltip,str_tooltip);
		limpiarButton=new Button(i_limpiarButton,str_limpiarButton);
	}
	
	public void desplegarOpcion(OpcionesRevisionLogsDesplegable desplegable,String opcion) {
		crud.desplegarCriterioDesplegable(desplegable.ordinal(),opcion);
	}
	
	public boolean limpiar() {
		limpiarButton.click();
		
		GregorianCalendar c=new GregorianCalendar();
		String hoy=String.format("%1$td/%1$tm/%1$tY", c);
		//String hoy = Integer.toString(c.get(Calendar.DATE))+"/"+Integer.toString(c.get(Calendar.MONTH)+1)+"/"+Integer.toString(c.get(Calendar.YEAR));
		
		boolean resultado=crud.leerFecha(OpcionesRevisionLogsFecha.Desde.ordinal()).equals(hoy) &&
						  crud.leerFecha(OpcionesRevisionLogsFecha.Hasta.ordinal()).equals(hoy) &&
						  crud.leerHora(OpcionesRevisionLogsFecha.Desde.ordinal()).equals("00:00") &&
						  crud.leerHora(OpcionesRevisionLogsFecha.Hasta.ordinal()).equals("23:59");
		for (int i=0;i<num_criterios_campo;i++) {
			if (!crud.leerOpcion(i).equals("")) {
				resultado=false;
			}
		}
		return resultado;
	}
	
	public String leerOpcion(OpcionesRevisionLogsCampo opcion) {
		return crud.leerOpcion(opcion.ordinal());
	}
	
	public void escribirOpcion(OpcionesRevisionLogsCampo opcion, String valor) {
		crud.escribirOpcion(opcion.ordinal(), valor);
	}
	
	public String leerFecha(OpcionesRevisionLogsFecha opcion) {
		return crud.leerFecha(opcion.ordinal());
	}
	
	public void escribirFecha(OpcionesRevisionLogsFecha opcion, String fecha, String hora) {
		crud.escribirFecha(opcion.ordinal(), fecha, hora);
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String encabezado, String[] labels) {
		
		boolean resultado=super.sintacticAnalysis(titulo, subtitulo, botones);
		return resultado && crud.sintacticAnalysis(null,labels);

	}
}
