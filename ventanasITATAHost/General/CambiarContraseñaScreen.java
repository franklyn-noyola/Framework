package ventanasITATAHost.General;

import bloques.CrudBlock;
import procesosITATAHost.General.CambiarContraseña.OpcionesCambiarContraseña;
import unidadesGraficas.TipoBy;
import ventanasComunes.Screen;

public class CambiarContraseñaScreen extends Screen {

	private CrudBlock crud;
	
	private TipoBy i_encabezado=TipoBy.ID;
	private String str_encabezado="ctl00_ContentZone_passwordSep_Lbl_SubtitleInfo";
	private final int num_criterios_campo=2;
	private final int num_criterios_desplegable=1;
	private final int num_criterios_fecha=0;
	private TipoBy[] i_label={TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_label={"ctl00_ContentZone_txt_newPassword_lbl_Description","ctl00_ContentZone_txt_newPassword2_lbl_Description","ctl00_ContentZone_cmb_operator_lbl_Description"};
	private TipoBy[] i_field={TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_field={"ctl00_ContentZone_txt_newPassword_box_data","ctl00_ContentZone_txt_newPassword2_box_data","ctl00_ContentZone_cmb_operator_cmb_dropdown"};
	private TipoBy[][] i_tooltipcampo=null;  //OJO RELLENAR ESTO
	private String[][] str_tooltipcampo=null; //OJO RELLENAR ESTO
	private TipoBy[][][] i_tooltipfecha=null;  
	private String[][][] str_tooltipfecha=null; 
	
	public CambiarContraseñaScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, TipoBy[] i_botones, String[] str_botones, 
			TipoBy i_msgerror, String str_msgerror) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,i_msgerror,str_msgerror);
		crud=new CrudBlock(i_encabezado, str_encabezado, num_criterios_campo, num_criterios_desplegable,
				num_criterios_fecha, i_label, str_label, i_field, str_field,i_tooltipcampo,str_tooltipcampo,
				i_tooltipfecha,str_tooltipfecha);
	}
	
	public void desplegarOpcion(String opcion) {
		crud.desplegarCriterioDesplegable(0,opcion);
	}
	
	public void escribirOpcion(OpcionesCambiarContraseña opcion, String valor) {
		switch (opcion) {
			case NuevaContraseña:
				crud.escribirOpcion(0, valor);
			break;
			case RepetirContraseña:
				crud.escribirOpcion(1, valor);
			break;
		}
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String encabezado, String[] labels) {
		
		boolean resultado=super.sintacticAnalysis(titulo, subtitulo, botones);
		return crud.sintacticAnalysis(encabezado,labels) && resultado;

	}
}
