package ventanasITATAHost.General;

import bloques.CrudBlock;
import unidadesGraficas.TipoBy;
import ventanasComunes.Screen;

public class CambiarIdiomaScreen extends Screen {

	private CrudBlock crud;
	
	private TipoBy i_encabezado=TipoBy.ID;
	private String str_encabezado="ctl00_ContentZone_passwordSep_Lbl_SubtitleInfo";
	private final int num_criterios_campo=0;
	final int num_criterios_desplegable=1;
	final int num_criterios_fecha=0;
	private TipoBy[] i_label={TipoBy.ID};
	private String[] str_label={"ctl00_ContentZone_cmb_language_lbl_Description"};
	private TipoBy[] i_field={TipoBy.ID};
	private String[] str_field={"ctl00_ContentZone_cmb_language_cmb_dropdown"};
	private TipoBy[][] i_tooltipcampo=null; 
	private String[][] str_tooltipcampo=null;
	private TipoBy[][][] i_tooltipfecha=null;  
	private String[][][] str_tooltipfecha=null; 
	
	public CambiarIdiomaScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, TipoBy[] i_botones, String[] str_botones, TipoBy i_msgerror, String str_msgerror) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,i_msgerror,str_msgerror);
		crud=new CrudBlock(i_encabezado, str_encabezado, num_criterios_campo, num_criterios_desplegable,
				num_criterios_fecha, i_label, str_label, i_field, str_field,i_tooltipcampo,str_tooltipcampo,
				i_tooltipfecha,str_tooltipfecha);
	}
	
	public void desplegarOpcion(String opcion) {
		crud.desplegarCriterioDesplegable(0,opcion);
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String encabezado, String[] labels) {
		
		boolean resultado=super.sintacticAnalysis(titulo, subtitulo, botones);
		return resultado && crud.sintacticAnalysis(encabezado,labels);

	}
}
