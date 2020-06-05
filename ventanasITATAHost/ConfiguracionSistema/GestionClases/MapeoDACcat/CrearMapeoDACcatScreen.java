package ventanasITATAHost.ConfiguracionSistema.GestionClases.MapeoDACcat;

import bloques.CrudRadioButtonBlock;
import procesosComunes.Error;
import procesosITATAHost.ConfiguracionSistema.GestionClases.MapeoDACcat.ErroresValidacionCampoCrearMapeoDACcat;
import procesosITATAHost.ConfiguracionSistema.GestionClases.MapeoDACcat.OpcionesCrearModificarMapeoDACcatCampo;
import procesosITATAHost.ConfiguracionSistema.GestionClases.MapeoDACcat.OpcionesCrearModificarMapeoDACcatRadioButton;
import procesosITATAHost.ConfiguracionSistema.GestionClases.MapeoDACcat.OpcionesCrearModificarMapeoDACcatDesplegable;
import unidadesGraficas.TipoBy;
import ventanasComunes.Screen;

public class CrearMapeoDACcatScreen extends Screen {

	private CrudRadioButtonBlock crud;
	
	private TipoBy i_encabezado_crud=TipoBy.ID;
	private String str_encabezado_crud="ctl00_ContentZone_LblSubtitleInfo_Lbl_SubtitleInfo";
	private final int num_criterios_campo=11;
	private final int num_criterios_desplegable=2;
	private final int num_criterios_fecha=0;
	private final int num_criterios_radiobutton=2;
	private final int[] num_checks={3,3};
	private TipoBy[] i_label={
							// Criterios campo
							TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,
							TipoBy.ID,TipoBy.ID,TipoBy.ID,
							// Criterios desplegable
							TipoBy.ID,TipoBy.ID};
	private String[] str_label={
							// Criterios campo
							"ctl00_ContentZone_BoxMapIndex_lbl_Description","ctl00_ContentZone_BoxMinDoubleWheel_lbl_Description",
							"ctl00_ContentZone_BoxMaxDoubleWheel_lbl_Description","ctl00_ContentZone_BoxMinAxles_lbl_Description",
							"ctl00_ContentZone_BoxMaxAxles_lbl_Description","ctl00_ContentZone_BoxMinAxle12dist_lbl_Description",
							"ctl00_ContentZone_BoxMaxAxle12dist_lbl_Description","ctl00_ContentZone_BoxMinHeight_lbl_Description",
							"ctl00_ContentZone_BoxMaxHeight_lbl_Description","ctl00_ContentZone_BoxMinWidth_lbl_Description",
							"ctl00_ContentZone_BoxMaxWidth_lbl_Description",
							// Criterios desplegable
							"ctl00_ContentZone_CmbCompany_lbl_Description",
							"ctl00_ContentZone_CmbClassId_lbl_Description"};
	private TipoBy[] i_field={
							// Criterios campo
							TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,
							TipoBy.ID,TipoBy.ID,TipoBy.ID,
							// Criterios desplegable
							TipoBy.ID,TipoBy.ID};
	private String[] str_field={
							// Criterios campo
							"ctl00_ContentZone_BoxMapIndex_box_data","ctl00_ContentZone_BoxMinDoubleWheel_box_data",
							"ctl00_ContentZone_BoxMaxDoubleWheel_box_data","ctl00_ContentZone_BoxMinAxles_box_data",
							"ctl00_ContentZone_BoxMaxAxles_box_data","ctl00_ContentZone_BoxMinAxle12dist_box_data",
							"ctl00_ContentZone_BoxMaxAxle12dist_box_data",
							"ctl00_ContentZone_BoxMinHeight_box_data","ctl00_ContentZone_BoxMaxHeight_box_data",
							"ctl00_ContentZone_BoxMinWidth_box_data","ctl00_ContentZone_BoxMaxWidth_box_data",
							// Criterios desplegable
							"ctl00_ContentZone_CmbCompany_cmb_dropdown","ctl00_ContentZone_CmbClassId_cmb_dropdown"};
	private TipoBy[][] i_tooltipcampo={{TipoBy.ID,TipoBy.ID},{null,TipoBy.ID},{null,TipoBy.ID},{null,TipoBy.ID},{null,TipoBy.ID},
							{null,TipoBy.ID},{null,TipoBy.ID},{null,TipoBy.ID},{null,TipoBy.ID},{null,TipoBy.ID},{null,TipoBy.ID}};
	private String[][] str_tooltipcampo={{"ctl00_ContentZone_BoxMapIndex_ValRequired","ctl00_ContentZone_BoxMapIndex_ValRegularExpression"},
							{null,"ctl00_ContentZone_BoxMinDoubleWheel_ValRegularExpression"},{null,"ctl00_ContentZone_BoxMaxDoubleWheel_ValRegularExpression"},
							{null,"ctl00_ContentZone_BoxMinAxles_ValRegularExpression"},{null,"ctl00_ContentZone_BoxMaxAxles_ValRegularExpression"},
							{null,"ctl00_ContentZone_BoxMinAxle12dist_ValRegularExpression"},{null,"ctl00_ContentZone_BoxMaxAxle12dist_ValRegularExpression"},
							{null,"ctl00_ContentZone_BoxMinHeight_ValRegularExpression"},{null,"ctl00_ContentZone_BoxMaxHeight_ValRegularExpression"},
							{null,"ctl00_ContentZone_BoxMinWidth_ValRegularExpression"},{null,"ctl00_ContentZone_BoxMaxWidth_ValRegularExpression"}};
	private final TipoBy[][][] i_tooltipfecha=null;
	private final String[][][] str_tooltipfecha=null;
	private TipoBy[][] i_labelRadioButton={							
							// Criterios check
							{TipoBy.ID,TipoBy.XPATH,TipoBy.XPATH,TipoBy.XPATH},
							{TipoBy.ID,TipoBy.XPATH,TipoBy.XPATH,TipoBy.XPATH}};
	private String[][] str_labelRadioButton={							
							// Criterios check
							{"ctl00_ContentZone_LblBus","//*[@id=\"ctl00_ContentZone_RdBus\"]/tbody/tr/td[1]/label",
														"//*[@id=\"ctl00_ContentZone_RdBus\"]/tbody/tr/td[2]/label",
														"//*[@id=\"ctl00_ContentZone_RdBus\"]/tbody/tr/td[3]/label"},
							{"ctl00_ContentZone_LblTrailer","//*[@id=\"ctl00_ContentZone_RdTrailer\"]/tbody/tr/td[1]/label",
															"//*[@id=\"ctl00_ContentZone_RdTrailer\"]/tbody/tr/td[2]/label",
															"//*[@id=\"ctl00_ContentZone_RdTrailer\"]/tbody/tr/td[3]/label"}};
	private TipoBy[][] i_RadioButton={
							// Criterios check
							{null,TipoBy.ID,TipoBy.ID,TipoBy.ID},
							{null,TipoBy.ID,TipoBy.ID,TipoBy.ID}};
	private String[][] str_RadioButton={							
							// Criterios check
							{null,"ctl00_ContentZone_RdBus_0","ctl00_ContentZone_RdBus_1","ctl00_ContentZone_RdBus_2"},
							{null,"ctl00_ContentZone_RdTrailer_0","ctl00_ContentZone_RdTrailer_1","ctl00_ContentZone_RdTrailer_2"}};

	public CrearMapeoDACcatScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, TipoBy[] i_botones,
								String[] str_botones, TipoBy i_msgerror, String str_msgerror) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,i_msgerror,str_msgerror);
		crud=new CrudRadioButtonBlock(i_encabezado_crud, str_encabezado_crud, num_criterios_campo, num_criterios_desplegable,
				 num_criterios_fecha, num_criterios_radiobutton, num_checks, i_label, str_label, i_field, str_field,i_tooltipcampo, str_tooltipcampo,
				 i_tooltipfecha, str_tooltipfecha, i_labelRadioButton, str_labelRadioButton, i_RadioButton, str_RadioButton);
	}
	
	public String leerOpcion(OpcionesCrearModificarMapeoDACcatCampo opcion) {
		return crud.leerOpcion(opcion.ordinal());
	}
	
	public void escribirOpcion(OpcionesCrearModificarMapeoDACcatCampo opcion, String valor) {
		crud.escribirOpcion(opcion.ordinal(), valor);
	}
	
	public void borrarOpcion(OpcionesCrearModificarMapeoDACcatCampo opcion) {
		crud.borrarOpcion(opcion.ordinal());
	}
	
	public String leerOpcionDesplegable(OpcionesCrearModificarMapeoDACcatDesplegable opcion) {
		return crud.leerCriterioDesplegable(opcion.ordinal());
	}
	
	public Error seleccionarOpcion(OpcionesCrearModificarMapeoDACcatDesplegable opcion, String valor) {
		return crud.desplegarCriterioDesplegable(opcion.ordinal(), valor);
	}
	
	public boolean hayErrorValidacionOpcion(OpcionesCrearModificarMapeoDACcatCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(),ErroresValidacionCampoCrearMapeoDACcat.Cualquiera.ordinal());
	}
	
	public boolean hayErrorValidacionOpcionRequerido(OpcionesCrearModificarMapeoDACcatCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(),ErroresValidacionCampoCrearMapeoDACcat.Requerido.ordinal());
	}
	
	public boolean hayErrorValidacionOpcionFormato(OpcionesCrearModificarMapeoDACcatCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(),ErroresValidacionCampoCrearMapeoDACcat.Formato.ordinal());
	}
	
	public String leerRadioButton(OpcionesCrearModificarMapeoDACcatRadioButton opcion) {
		return crud.leerCriterioRadioButton(opcion.ordinal());
	}
	
	public void clickRadioButton(int opcion, int check) {
		crud.clickCriterioRadioButton(opcion, check);
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones,  String encabezado_crud,
			String[] labels_crud, String[][] labels_RadioButton) {
		
		boolean resultado=super.sintacticAnalysis(titulo, subtitulo, botones);
		return crud.sintacticAnalysis(encabezado_crud,labels_crud, labels_RadioButton) && resultado;

	}
}
