package ventanasITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje;

import bloques.CrudBlock;
import procesosComunes.Error;
import bloques.CrudBlockVariable;
import bloques.CrudCheckLabelDropdownBlock;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje.OpcionesCrearModificarReglaCampo;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje.OpcionesCrearModificarReglaCheck;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje.OpcionesCrearModificarReglaVariable;
import unidadesGraficas.TipoBy;
import ventanasComunes.Screen;

public class CrearModificarReglaScreen extends Screen {

	private CrudBlockVariable crudVar;
	private CrudCheckLabelDropdownBlock crudCheck;
	private CrudBlock crud;
	
	private TipoBy i_encabezadovar=TipoBy.ID;
	private String str_encabezadovar="ctl00_ContentZone_LblSubtitleParameter";
	private final int num_criterios_var=1;
	private TipoBy[] i_labelvar={TipoBy.ID,TipoBy.ID};
	private String[] str_labelvar={"ctl00_ContentZone_LblTollCompany","ctl00_ContentZone_LblId"};
	private TipoBy[] i_fieldvar={TipoBy.ID,TipoBy.ID};
	private String[] str_fieldvar={"ctl00_ContentZone_LblTollCompanyVal","ctl00_ContentZone_LblIdVal"};
	private final int num_criterios_campovar=0;
	private final int num_criterios_desplegablevar=0;
	private final int num_criterios_fechavar=0;
	private TipoBy[] i_label={TipoBy.ID,TipoBy.ID};
	private String[] str_label=null;
	private TipoBy[] i_field=null;
	private String[] str_field=null;
	private TipoBy[][] i_tooltipvar=null;
	private String[][] str_tooltipvar=null;
	
	private TipoBy i_encabezadocheck=TipoBy.ID;
	private String str_encabezadocheck="ctl00_ContentZone_LblSubtitleConditions";
	private final int num_criterios_check=5;
	private TipoBy[] i_labelcheck={TipoBy.XPATH,TipoBy.XPATH,TipoBy.XPATH,TipoBy.XPATH,TipoBy.XPATH};
	private String[] str_labelcheck={"//*[@id=\"content\"]/span[7]/label","//*[@id=\"content\"]/span[4]/label",
									"//*[@id=\"content\"]/span[3]/label","//*[@id=\"content\"]/span[2]/label",
									"//*[@id=\"content\"]/span[1]/label"};
	private TipoBy[] i_check={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_check={"ctl00_ContentZone_ChkCompany","ctl00_ContentZone_ChkPlaza","ctl00_ContentZone_ChkLaneNum",
									"ctl00_ContentZone_ChkLaneDir", "ctl00_ContentZone_ChkLaneType"};
	private TipoBy[] i_dropdown={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_dropdown={"ctl00_ContentZone_CmbCompany","ctl00_ContentZone_CmbPlaza",
									"ctl00_ContentZone_CmbLaneNum","ctl00_ContentZone_CmbLaneDir","ctl00_ContentZone_CmbLaneType"};
	private final int num_criterios_campocheck=0;
	private final int num_criterios_desplegablecheck=0;
	private final int num_criterios_fechacheck=0;
	private TipoBy[] i_label2=null;
	private String[] str_label2=null;
	private TipoBy[] i_field2=null;
	private String[] str_field2=null;
	private TipoBy[][] i_tooltip2=null;
	private String[][] str_tooltip2=null;
	
	private TipoBy i_encabezado_crud=TipoBy.ID;
	private String str_encabezado_crud="ctl00_ContentZone_LblSubtitleValue";
	private final int num_criterios_campo=1;
	private final int num_criterios_desplegable=0;
	private final int num_criterios_fecha=0;
	private TipoBy[] i_label3={TipoBy.ID};
	private String[] str_label3={"ctl00_ContentZone_LblValue"};
	private TipoBy[] i_field3={TipoBy.ID};
	private String[] str_field3={"ctl00_ContentZone_BoxValue"};
	private TipoBy[][] i_tooltipcampo=null;
	private String[][] str_tooltipcampo=null;
	private final TipoBy[][][] i_tooltipfecha=null;
	private final String[][][] str_tooltipfecha=null;
	
	public CrearModificarReglaScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, TipoBy[] i_botones, String[] str_botones, TipoBy i_msgerror, String str_msgerror) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,i_msgerror,str_msgerror);
		crudVar=new CrudBlockVariable(i_encabezadovar, str_encabezadovar, num_criterios_var, i_labelvar, str_labelvar, 
				i_fieldvar,str_fieldvar,num_criterios_campovar, num_criterios_desplegablevar,
				 num_criterios_fechavar, i_label, str_label, i_field, str_field,i_tooltipvar, str_tooltipvar,null,null);
		crudCheck=new CrudCheckLabelDropdownBlock(i_encabezadocheck, str_encabezadocheck, num_criterios_campocheck, num_criterios_desplegablecheck,
				 num_criterios_fechacheck, num_criterios_check, i_label2, str_label2,
				 i_field2,  str_field2,  i_tooltip2,  str_tooltip2, null, null,
				 i_labelcheck,  str_labelcheck, i_check,  str_check, i_dropdown,  str_dropdown);
		crud=new CrudBlock(i_encabezado_crud, str_encabezado_crud, num_criterios_campo, num_criterios_desplegable,
				 num_criterios_fecha, i_label3, str_label3, i_field3, str_field3, i_tooltipcampo, str_tooltipcampo,
				 i_tooltipfecha, str_tooltipfecha);
	}

	public String leerOpcionVariable(OpcionesCrearModificarReglaVariable opcion) {
		return crudVar.leerOpcion(opcion.ordinal());
	}
	
	public void escribirOpcion(OpcionesCrearModificarReglaCampo opcion, String valor) {
		crud.escribirOpcion(opcion.ordinal(), valor);
	}
	
	public String leerOpcion(OpcionesCrearModificarReglaCampo opcion) {
		return crud.leerOpcion(opcion.ordinal());
	}
	
	public void borrarOpcion(OpcionesCrearModificarReglaCampo opcion) {
		crud.borrarOpcion(opcion.ordinal());
	}
	
	public void clicarCheck(OpcionesCrearModificarReglaCheck opcion) {
		crudCheck.clickCriterioCheck(opcion.ordinal());
	}
	
	public boolean estaChequeado(OpcionesCrearModificarReglaCheck opcion) {
		return crudCheck.estaChequeadoCriterioCheck(opcion.ordinal());
	}
	
	public Error seleccionarCriterioDesplegable(OpcionesCrearModificarReglaCheck opcion, String valor) {
		return crudCheck.seleccionarCriterioDesplegable(opcion.ordinal(), valor);
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String encabezado_var, String[] labels_var,
			String encabezado_check, String[] labels_check, String encabezado_crud, String[] labels_crud) {
		
		boolean resultado=super.sintacticAnalysis(titulo, subtitulo, botones);
		resultado=crudVar.sintacticAnalysis(encabezado_var,labels_var) && resultado;
		resultado= crudCheck.sintacticAnalysis(encabezado_check,labels_check) && resultado;
		return crud.sintacticAnalysis(encabezado_crud,labels_crud) && resultado;

	}
}
