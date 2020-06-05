package ventanasITATAHost.ConfiguracionSistema.Operadores.GestionOperadores;

import bloques.CrudBlockVariable;
import procesosComunes.Error;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.ErroresValidacionCampoVerCrearOperador;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.ErroresValidacionFechaVerCrearOperador;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.OpcionesCrearOperadorCampoCrud1;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.OpcionesCrearOperadorFechaCrud1;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.OpcionesVerOperadorDesplegable;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.OpcionesVerOperadorVariable;
import unidadesGraficas.Check;
import unidadesGraficas.TipoBy;
import unidadesGraficas.Title;
import ventanasComunes.Screen;

public class ModificarOperadorScreen extends Screen {

	private CrudBlockVariable crud;
	private Title encabezado2;
	private Check check2;
	private Title encabezado3;
	private Check check3;
	
	// Crud 1-> Revisar este bloqwue
	private TipoBy i_encabezado_crud1=TipoBy.ID;
	private String str_encabezado_crud1="ctl00_ContentZone_operatorSep_Lbl_SubtitleInfo";
	private final int num_criterios_var=2;
	private TipoBy[] i_labelvar={TipoBy.ID,TipoBy.ID};
	private String[] str_labelvar={"ctl00_ContentZone_tollCompany_Lbl_lbl_Description",
			"ctl00_ContentZone_operatorId_lbl_Description"};
	private TipoBy[] i_fieldvar={TipoBy.ID,TipoBy.ID};
	private String[] str_fieldvar={"ctl00_ContentZone_tollCompany_Lbl_box_data",
			"ctl00_ContentZone_operatorId_box_data"};
	private final int num_criterios_campo=8;
	private final int num_criterios_desplegable=3;
	private final int num_criterios_fecha=1;
	private TipoBy[] i_label1={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,
			TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_label1={"ctl00_ContentZone_forename_lbl_Description","ctl00_ContentZone_surname_lbl_Description",
			"ctl00_ContentZone_txt_address_lbl_Description","ctl00_ContentZone_txt_postcode_lbl_Description",
			"ctl00_ContentZone_txt_city_lbl_Description","ctl00_ContentZone_txt_country_lbl_Description",
			"ctl00_ContentZone_email_lbl_Description","ctl00_ContentZone_txt_phone_lbl_Description",
			"ctl00_ContentZone_cmb_title_lbl_Description",
			"ctl00_ContentZone_cmb_gender_lbl_Description","ctl00_ContentZone_group_lbl_Description",
			"ctl00_ContentZone_lbl_dateBirth"};
	private TipoBy[] i_field={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,
			TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,null};
	private String[] str_field={"ctl00_ContentZone_forename_box_data","ctl00_ContentZone_surname_box_data",
			"ctl00_ContentZone_txt_address_box_data","ctl00_ContentZone_txt_postcode_box_data",
			"ctl00_ContentZone_txt_city_box_data","ctl00_ContentZone_txt_country_box_data",
			"ctl00_ContentZone_email_box_data","ctl00_ContentZone_txt_phone_box_data",
			"ctl00_ContentZone_cmb_title_cmb_dropdown",
			"ctl00_ContentZone_cmb_gender_cmb_dropdown","ctl00_ContentZone_group_cmb_dropdown",
			"ctl00_ContentZone_dt_birth_box_date",null};
	private TipoBy[][] i_tooltipcampo1={{TipoBy.ID,null},{TipoBy.ID,null},null,null,null,null,null,null};
	private String[][] str_tooltipcampo1= {{"ctl00_ContentZone_forname_validationMsgs",null},{"ctl00_ContentZone_surname_validationMsgs",null},
			null,null,null,null,null,null};
	private TipoBy[][][] i_tooltipfecha1= {{{TipoBy.ID,TipoBy.ID},{null,null}}};
	private String[][][] str_tooltipfecha1={{{"ctl00_ContentZone_dt_birth_ValDateFormat","ctl00_ContentZone_dt_birth_ValDateReq"},{null,null}}};
	
	// Crud 2
	private TipoBy i_encabezado_crud2=TipoBy.ID;
	private String str_encabezado_crud2="ctl00_ContentZone_stateSep_Lbl_SubtitleInfo";
	private TipoBy i_label2=TipoBy.XPATH;
	private String str_label2="//*[@id=\"ctl00_ContentZone_StateDiv\"]/div[2]/span/label";

	// Crud 3
	
	private TipoBy i_encabezado_crud3=TipoBy.ID;
	private String str_encabezado_crud3="ctl00_ContentZone_passwordSep_Lbl_SubtitleInfo";
	private TipoBy i_label3=TipoBy.XPATH;
	private String str_label3="//*[@id=\"ctl00_ContentZone_PasswordResetDiv\"]/span/label";
	
	public ModificarOperadorScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, TipoBy[] i_botones, String[] str_botones,
			TipoBy i_msgerror, String str_msgerror) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,i_msgerror,str_msgerror);
		crud=new CrudBlockVariable(i_encabezado_crud1, str_encabezado_crud1,num_criterios_var, i_labelvar,str_labelvar, 
				i_fieldvar,str_fieldvar,num_criterios_campo, num_criterios_desplegable,
				 num_criterios_fecha, i_label1, str_label1, i_field, str_field,i_tooltipcampo1, str_tooltipcampo1,
				 i_tooltipfecha1, str_tooltipfecha1);
		encabezado2=new Title(i_encabezado_crud2,str_encabezado_crud2);
		check2=new Check(i_label2,str_label2);
		encabezado3=new Title(i_encabezado_crud3,str_encabezado_crud3);
		check3=new Check(i_label3,str_label3);
	}

	public String leerOpcionVariable(OpcionesVerOperadorVariable opcion) {
		return crud.leerOpcionVariable(opcion.ordinal());
	}
	
	public String leerOpcion(OpcionesVerOperadorDesplegable desplegable) {
		return crud.leerCriterioDesplegable(desplegable.ordinal());
	}
	
	public Error desplegarOpcion(OpcionesVerOperadorDesplegable desplegable,String opcion) {
		return crud.desplegarCriterioDesplegable(desplegable.ordinal(),opcion);
	}
	
	public String leerOpcionCrud1(OpcionesCrearOperadorCampoCrud1 opcion) {
		return crud.leerOpcion(opcion.ordinal());
	}
	
	public String leerFechaCrud1(OpcionesCrearOperadorFechaCrud1 opcion) {
		return crud.leerFecha(opcion.ordinal());
	}
	
	public void escribirOpcionCrud1(OpcionesCrearOperadorCampoCrud1 opcion, String valor) {
		crud.borrarOpcion(opcion.ordinal());
		crud.escribirOpcion(opcion.ordinal(), valor);
	}
	
	public void escribirFechaCrud1(OpcionesCrearOperadorFechaCrud1 opcion, String valor) {
		crud.borrarFecha(opcion.ordinal());
		crud.escribirFecha(opcion.ordinal(), valor, null);
	}
	
	public boolean hayErrorValidacionOpcionCrud1(OpcionesCrearOperadorCampoCrud1 opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(), ErroresValidacionCampoVerCrearOperador.Cualquiera.ordinal());
	}
	
	public boolean hayErrorValidacionFechaCrud1(OpcionesCrearOperadorFechaCrud1 opcion) {
		return crud.hayErrorValidacionFechaFecha(opcion.ordinal(),ErroresValidacionFechaVerCrearOperador.Cualquiera.ordinal());
	}
	
	public boolean hayErrorValidacionFormatoFecha(OpcionesCrearOperadorFechaCrud1 opcion) {
		return crud.hayErrorValidacionFechaFecha(opcion.ordinal(),ErroresValidacionFechaVerCrearOperador.Formato.ordinal());
	}
	
	public boolean hayErrorValidacionRequeridoFecha(OpcionesCrearOperadorFechaCrud1 opcion) {
		return crud.hayErrorValidacionFechaFecha(opcion.ordinal(),ErroresValidacionFechaVerCrearOperador.Requerido.ordinal());
	}
	
	public void borrarFechaCrud1(OpcionesCrearOperadorFechaCrud1 opcion) {
		crud.borrarFecha(opcion.ordinal());
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones,  String encabezado_crud1,
			String[] labels_variable, String[] valoresvariables, String[] labels_crud1, 
			String encabezado_crud2, String label_crud2,String encabezado_crud3, String label_crud3) {
		
		boolean resultado=super.sintacticAnalysis(titulo, subtitulo, botones);
		return crud.sintacticAnalysis(encabezado_crud1,labels_variable,valoresvariables,labels_crud1) &&
				encabezado2.sintacticAnalysis(encabezado_crud2) && 
				encabezado3.sintacticAnalysis(encabezado_crud3) &&
				check2.sintacticAnalysis(label_crud2) && 
				check3.sintacticAnalysis(label_crud3) && resultado;

	}
}
