package ventanasITATAHost.General;

import bloques.CrudBlock;
import elementosBase.Tabla;
import procesosITATAHost.General.Avisos.OpcionesAvisosAuditoriaCampo;
import procesosITATAHost.General.Avisos.OpcionesAvisosAuditoriaDesplegable;
import unidadesGraficas.Button;
import unidadesGraficas.TipoBy;
import ventanasComunes.Screen;

public class AvisosAuditoriaScreen extends Screen {

	private CrudBlock crud;
	private Button cerrarAvisosMarcados;
	private Tabla tabla;
	
	private TipoBy i_encabezado=null;
	private String str_encabezado=null;
	private TipoBy i_cerrarAvisosButton=TipoBy.ID;
	private String str_cerrarAvisosButton="ctl00_ContentZone_BtnCloseAlerts";
	private final int num_criterios_campo=4;
	private final int num_criterios_desplegable=5;
	private final int num_criterios_fecha=2;
	private TipoBy[] i_label={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_label={"ctl00_ContentZone_txt_operator_lbl_Description","ctl00_ContentZone_cmb_status_lbl_Description",
			"ctl00_ContentZone_cmb_plazaNode_lbl_Description","ctl00_ContentZone_cmb_laneNode_lbl_Description","ctl00_ContentZone_dtActivation_lbl_Description"};
	private TipoBy[] i_field={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_field={"ctl00_ContentZone_txt_operator_lbl_Description","ctl00_ContentZone_cmb_status_cmb_dropdown",
			"ctl00_ContentZone_cmb_plazaNode_cmb_dropdown","ctl00_ContentZone_cmb_laneNode_cmb_dropdown","ctl00_ContentZone_dtActivation_box_date"};
	
	private int num_botonescrud=0;
	private int num_botonesgrid=4;
	private int columna_inicial=0; //Si columna_inicial>0 es pq la tabla tiene un check en la columna 0
	private TipoBy i_radios=null;
	private String str_radios=null;
	private TipoBy i_leyendamostrado=TipoBy.ID;
	private String strleyendamostrado="ctl00_ContentZone_tablePager_LblCounter"; // Es la misma de siempre
	private TipoBy i_cabecera=TipoBy.XPATH; // Es la misma de siempre
	private String str_cabecera="//*[@id='ctl00_ContentZone_TblResults']/tbody/"; // Es la misma de siempre
	private TipoBy[] i_botonescrud=null;
	private String[] str_botonescrud=null;
	private TipoBy[] i_botonesgrid={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_botonesgrid={"ctl00_ContentZone_tablePager_BtnFirst","ctl00_ContentZone_tablePager_BtnPrev","ctl00_ContentZone_tablePager_BtnNext","ctl00_ContentZone_tablePager_BtnLast"};

	
	public AvisosAuditoriaScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, TipoBy[] i_botones, String[] str_botones, TipoBy i_msgerror, String str_msgerror, int num_columnas) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,i_msgerror,str_msgerror);
		crud=new CrudBlock(i_encabezado, str_encabezado, num_criterios_campo, num_criterios_desplegable, num_criterios_fecha,i_label, str_label, i_field, str_field);
		tabla=new Tabla(num_columnas,i_cabecera, str_cabecera, i_leyendamostrado,strleyendamostrado,
				 i_botonescrud, str_botonescrud, i_botonesgrid, str_botonesgrid,columna_inicial);
		cerrarAlarmasActivas=new Button(i_cerrarAlarmasButton,str_cerrarAlarmasButton);
	}
	
	public void desplegarOpcion(OpcionesAvisosAuditoriaDesplegable desplegable,String opcion) {
		crud.desplegarCriterioDesplegable(desplegable.ordinal(),opcion);
	}
	
	public void escribirOpcion(OpcionesAvisosAuditoriaCampo opcion, String valor) {
		crud.escribirOpcion(opcion.ordinal(), valor);
	}
	
	public void seleccionarFila(int indice) {
		tabla.seleccionarFila(indice);
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String[] labels, String[] columnasgrid, String[] botonesgrid) {
		
		boolean resultado=super.sintacticAnalysis(titulo, subtitulo, botones);
		return resultado && crud.sintacticAnalysis(null,labels) && tabla.sintacticAnalysis(null, columnasgrid, botonesgrid);

	}
}
