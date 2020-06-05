package ventanasITATAHost.ConfiguracionSistema.Operadores.GestionOperadores;

import bloques.CrudBlock;
import procesosComunes.Error;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.ErroresValidacionCampoVerCrearOperador;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.ErroresValidacionFechaVerCrearOperador;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.OpcionesCrearOperadorCampoCrud1;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.OpcionesCrearOperadorCampoCrud2;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.OpcionesCrearOperadorDesplegable;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.OpcionesCrearOperadorFechaCrud1;
import unidadesGraficas.TipoBy;
import ventanasComunes.Screen;

public class CrearOperadorScreen extends Screen {

	private CrudBlock crud1;
	private CrudBlock crud2;
	
	// Crud 1
	private TipoBy i_encabezado_crud1=TipoBy.ID;
	private String str_encabezado_crud1="ctl00_ContentZone_operatorSep_Lbl_SubtitleInfo";
	private final int num_criterios_campo1=8;
	private final int num_criterios_desplegable1=4;
	private final int num_criterios_fecha1=1;
	private TipoBy[] i_label1={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,
			TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_label1={"ctl00_ContentZone_forename_lbl_Description","ctl00_ContentZone_surname_lbl_Description",
			"ctl00_ContentZone_txt_address_lbl_Description","ctl00_ContentZone_txt_postcode_lbl_Description",
			"ctl00_ContentZone_txt_city_lbl_Description","ctl00_ContentZone_txt_country_lbl_Description",
			"ctl00_ContentZone_email_lbl_Description","ctl00_ContentZone_txt_phone_lbl_Description",
			"ctl00_ContentZone_tollCompany_Cmb_lbl_Description","ctl00_ContentZone_cmb_title_lbl_Description",
			"ctl00_ContentZone_cmb_gender_lbl_Description","ctl00_ContentZone_group_lbl_Description",
			"ctl00_ContentZone_lbl_dateBirth"};
	private TipoBy[] i_field1={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,
			TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,null};
	private String[] str_field1={"ctl00_ContentZone_forename_box_data","ctl00_ContentZone_surname_box_data",
			"ctl00_ContentZone_txt_address_box_data","ctl00_ContentZone_txt_postcode_box_data",
			"ctl00_ContentZone_txt_city_box_data","ctl00_ContentZone_txt_country_box_data",
			"ctl00_ContentZone_email_box_data","ctl00_ContentZone_txt_phone_box_data",
			"ctl00_ContentZone_tollCompany_Cmb_cmb_dropdown","ctl00_ContentZone_cmb_title_cmb_dropdown",
			"ctl00_ContentZone_cmb_gender_cmb_dropdown","ctl00_ContentZone_group_cmb_dropdown",
			"ctl00_ContentZone_dt_birth_box_date",null};
	private TipoBy[][] i_tooltipcampo1={{TipoBy.ID,null},{TipoBy.ID,null},null,null,null,null,null,null};
	private String[][] str_tooltipcampo1= {{"ctl00_ContentZone_forename_ValRequired",null},{"ctl00_ContentZone_surname_ValRequired",null},
			null,null,null,null,null,null};
	private TipoBy[][][] i_tooltipfecha1= {{{TipoBy.ID,TipoBy.ID},{null,null}}};
	private String[][][] str_tooltipfecha1={{{"ctl00_ContentZone_dt_birth_ValDateFormat","ctl00_ContentZone_dt_birth_ValDateReq"},{null,null}}};
	
	// Crud 2
	private TipoBy i_encabezado_crud2=TipoBy.ID;
	private String str_encabezado_crud2="ctl00_ContentZone_passwordSep_Lbl_SubtitleInfo";
	private final int num_criterios_campo2=2;
	private final int num_criterios_desplegable2=0;
	private final int num_criterios_fecha2=0;
	private TipoBy[] i_label2={TipoBy.ID,TipoBy.ID};
	private String[] str_label2={"ctl00_ContentZone_password_lbl_Description","ctl00_ContentZone_password2_lbl_Description"};
	private TipoBy[] i_field2={TipoBy.ID,TipoBy.ID};
	private String[] str_field2={"ctl00_ContentZone_password_box_data","ctl00_ContentZone_password2_box_data"};
	private TipoBy[][] i_tooltipcampo2={{TipoBy.ID},null};
	private String[][] str_tooltipcampo2= {{"ctl00_ContentZone_password_ValRequired"},null};
	private TipoBy[][][] i_tooltipfecha2=null;
	private String[][][] str_tooltipfecha2=null;
	
	public CrearOperadorScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, TipoBy[] i_botones, String[] str_botones, TipoBy i_msgerror, String str_msgerror) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,i_msgerror,str_msgerror);
		crud1=new CrudBlock(i_encabezado_crud1, str_encabezado_crud1, num_criterios_campo1, num_criterios_desplegable1,
				 num_criterios_fecha1, i_label1, str_label1, i_field1, str_field1,i_tooltipcampo1, str_tooltipcampo1,
				 i_tooltipfecha1, str_tooltipfecha1);
		crud2=new CrudBlock(i_encabezado_crud2, str_encabezado_crud2, num_criterios_campo2, num_criterios_desplegable2,
				 num_criterios_fecha2, i_label2, str_label2, i_field2, str_field2,i_tooltipcampo2, str_tooltipcampo2,
				 i_tooltipfecha2, str_tooltipfecha2);
	}

	
	public Error desplegarOpcion(OpcionesCrearOperadorDesplegable desplegable,String opcion) {
		return crud1.desplegarCriterioDesplegable(desplegable.ordinal(),opcion);
	}
	
	public void escribirOpcionCrud1(OpcionesCrearOperadorCampoCrud1 opcion, String valor) {
		crud1.escribirOpcion(opcion.ordinal(), valor);
	}
	
	public void escribirFechaCrud1(OpcionesCrearOperadorFechaCrud1 opcion, String valor) {
		crud1.escribirFecha(opcion.ordinal(), valor, null);
	}
	
	public void borrarFechaCrud1(OpcionesCrearOperadorFechaCrud1 opcion) {
		crud1.borrarFecha(opcion.ordinal());
	}
	
	public void escribirOpcionCrud2(OpcionesCrearOperadorCampoCrud2 opcion, String valor) {
		crud2.escribirOpcion(opcion.ordinal(), valor);
	}
	
	public boolean hayErrorValidacionOpcionCrud1(OpcionesCrearOperadorCampoCrud1 opcion) {
		return crud1.hayErrorValidacionOpcion(opcion.ordinal(), ErroresValidacionCampoVerCrearOperador.Cualquiera.ordinal());
	}
	
	public boolean hayErrorValidacionFechaCrud1(OpcionesCrearOperadorFechaCrud1 opcion) {
		return crud1.hayErrorValidacionFechaFecha(opcion.ordinal(),ErroresValidacionFechaVerCrearOperador.Cualquiera.ordinal());
	}
	
	public boolean hayErrorValidacionFechaRequeridoCrud1(OpcionesCrearOperadorFechaCrud1 opcion) {
		return crud1.hayErrorValidacionFechaFecha(opcion.ordinal(),ErroresValidacionFechaVerCrearOperador.Requerido.ordinal());
	}
	
	public boolean hayErrorValidacionFechaFormatoCrud1(OpcionesCrearOperadorFechaCrud1 opcion) {
		return crud1.hayErrorValidacionFechaFecha(opcion.ordinal(),ErroresValidacionFechaVerCrearOperador.Formato.ordinal());
	}
	
	public boolean hayErrorValidacionOpcionCrud2(OpcionesCrearOperadorCampoCrud2 opcion) {
		return crud2.hayErrorValidacionOpcion(opcion.ordinal(),ErroresValidacionCampoVerCrearOperador.Cualquiera.ordinal());
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones,  String encabezado_crud1, String[] labels_crud1, 
			String encabezado_crud2, String[] labels_crud2) {
		
		boolean resultado=super.sintacticAnalysis(titulo, subtitulo, botones);
		return crud1.sintacticAnalysis(encabezado_crud1,labels_crud1) &&
			   crud2.sintacticAnalysis(encabezado_crud2,labels_crud2) && resultado;

	}
}
