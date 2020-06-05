package ventanasITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias;

import bloques.CrudBlock;
import procesosComunes.Error;
import bloques.CrudBlockVariable;
import bloques.CrudCheckLabelFieldBlock;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.ErroresValidacionCrearModificarPlaza;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.OpcionesCrearModificarPlazaCampo;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.OpcionesCrearModificarPlazaCheck;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.OpcionesCrearModificarPlazaCheckDropdown;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.OpcionesCrearModificarPlazaCheckField;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.OpcionesCrearModificarPlazaVariable;
import unidadesGraficas.TipoBy;
import ventanasComunes.Screen;

public class CrearModificarPlazaScreen extends Screen {

	private CrudBlockVariable crudVar;
	private CrudBlock crud;
	private CrudCheckLabelFieldBlock crudcheck;
	
	private TipoBy i_encabezado1=TipoBy.ID;
	private String str_encabezado1="ctl00_ContentZone_LblSubtitleCompany";
	private final int num_criterios_var=2;
	private TipoBy[] i_labelvar={TipoBy.ID,TipoBy.ID};
	private String[] str_labelvar={"ctl00_ContentZone_LblCmpyCode","ctl00_ContentZone_LblCmpyName"};
	private TipoBy[] i_fieldvar={TipoBy.ID,TipoBy.ID};
	private String[] str_fieldvar={"ctl00_ContentZone_LblCmpyCodeVal","ctl00_ContentZone_LblCmpyNameVal"};
	
	private TipoBy i_encabezado2=TipoBy.ID;
	private String str_encabezado2="ctl00_ContentZone_LblSubtitleInfo";
	private final int num_criterios_campo=4;
	private final int num_criterios_desplegable=0;
	private final int num_criterios_fecha=0;
	private TipoBy[] i_label={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_label={"ctl00_ContentZone_LblCode","ctl00_ContentZone_LblName","ctl00_ContentZone_LblRoad","ctl00_ContentZone_LblKm"};
	private TipoBy[] i_field={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_field={"ctl00_ContentZone_BoxCode","ctl00_ContentZone_BoxName","ctl00_ContentZone_BoxRoad","ctl00_ContentZone_BoxKm"};
	private TipoBy[][] i_tooltipcampo={{TipoBy.ID,TipoBy.ID},{TipoBy.ID,null},null,null};
	private String[][] str_tooltipcampo={{"ctl00_ContentZone_ValBoxCode","ctl00_ContentZone_ValBoxCodeExpr"},{"ctl00_ContentZone_ValBoxName1",null},null,null};
	private final TipoBy[][][] i_tooltipfecha=null;
	private final String[][][] str_tooltipfecha=null;
	
	private TipoBy i_encabezado3=TipoBy.ID;
	private String str_encabezado3="ctl00_ContentZone_LblSubtitleNode";
	private final int num_criterios_campocheck=1;
	private final int num_criterios_desplegablecheck=0;
	private final int num_criterios_fechacheck=0;
	private final int num_criterios_checkdropdown=1;
	private final int num_criterios_checkfield=1;
	// Parámetros CrudBlock
	private TipoBy[] i_labelcheck={TipoBy.ID};
	private String[] str_labelcheck={"ctl00_ContentZone_LblNodeAddress"};
	private TipoBy[] i_fieldcheck={TipoBy.ID};
	private String[] str_fieldcheck={"ctl00_ContentZone_BoxNodeAddress"};
	private TipoBy[][] i_tooltipcheck={{TipoBy.ID,TipoBy.ID}};
	private String[][] str_tooltipcheck={{"ctl00_ContentZone_ValBoxNodeAddress1","ctl00_ContentZone_ValBoxNodeAddress2"}};
	// Parámetros CrudBlockLabelDropDown
	private TipoBy[] i_labeldropdown={TipoBy.XPATH};
	private String[] str_labeldropdown={"//*[@id=\"ctl00_ContentZone_RbNode\"]/tbody/tr[1]/td/label"};
	private TipoBy[] i_check={TipoBy.ID};
	private String[] str_check={"ctl00_ContentZone_RbNode_0"};
	private TipoBy[] i_fielddropdown={TipoBy.ID};
	private String[] str_fielddropdown={"ctl00_ContentZone_CmbNodes"};
	// Parámetros CrudBlockCheckLabelField
	private TipoBy[] i_labelfield={TipoBy.XPATH};
	private String[] str_labelfield={"//*[@id=\"ctl00_ContentZone_RbNode\"]/tbody/tr[2]/td/label"};
	private TipoBy[] i_check2={TipoBy.ID};
	private String[] str_check2={"ctl00_ContentZone_RbNode_1"};
	private TipoBy[] i_fieldcheck2={TipoBy.ID};
	private String[] str_fieldcheck2={"ctl00_ContentZone_BoxNodeCode"};
	private TipoBy[][] i_tooltipcheck2={{TipoBy.ID,TipoBy.ID}};
	private String[][] str_tooltipcheck2={{"ctl00_ContentZone_ValReqBoxNodeCode","ctl00_ContentZone_ValBoxNodeCode"}};
	
	public CrearModificarPlazaScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, TipoBy[] i_botones, String[] str_botones,
			TipoBy i_msgerror, String str_msgerror) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,i_msgerror,str_msgerror);
		crudVar=new CrudBlockVariable(i_encabezado1, str_encabezado1,num_criterios_var, i_labelvar,str_labelvar, 
				i_fieldvar,str_fieldvar,num_criterios_campo, num_criterios_desplegable,
				 num_criterios_fecha, i_label, str_label, i_field, str_field,null, null,null,null);
		crud=new CrudBlock(i_encabezado2, str_encabezado2, num_criterios_campo, num_criterios_desplegable,
				 num_criterios_fecha, i_label, str_label, i_field, str_field, i_tooltipcampo, str_tooltipcampo,
				 i_tooltipfecha, str_tooltipfecha);
		crudcheck=new CrudCheckLabelFieldBlock(i_encabezado3, str_encabezado3, num_criterios_campocheck,  num_criterios_desplegablecheck,
				num_criterios_fechacheck, num_criterios_checkdropdown, num_criterios_checkfield, i_labelcheck, str_labelcheck,
				i_fieldcheck, str_fieldcheck, i_tooltipcheck, str_tooltipcheck, null,null,
				i_labeldropdown, str_labeldropdown, i_check, str_check, i_fielddropdown, str_fielddropdown, 
				i_labelfield, str_labelfield, i_check2, str_check2,
				i_fieldcheck2, str_fieldcheck2, i_tooltipcheck2, str_tooltipcheck2);
	}

	// Métodos para acceder al primer Bloque
	
	public String leerOpcionVariable(OpcionesCrearModificarPlazaVariable opcion) {
		return crudVar.leerOpcionVariable(opcion.ordinal());
	}
	
	// Métodos para acceder al segundo Bloque
	
	public String leerOpcion(OpcionesCrearModificarPlazaCampo opcion) {
		return crud.leerOpcion(opcion.ordinal());
	}
	
	public void escribirOpcion(OpcionesCrearModificarPlazaCampo opcion, String valor) {
		crud.escribirOpcion(opcion.ordinal(), valor);
	}
	
	public void borrarOpcion(OpcionesCrearModificarPlazaCampo opcion) {
		crud.borrarOpcion(opcion.ordinal());
	}
	
	public boolean hayErrorValidacionOpcion(OpcionesCrearModificarPlazaCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(), ErroresValidacionCrearModificarPlaza.Cualquiera.ordinal());
	}
	
	public boolean hayErrorValidacionOpcionRequerido(OpcionesCrearModificarPlazaCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(), ErroresValidacionCrearModificarPlaza.Requerido.ordinal());
	}
	
	public boolean hayErrorValidacionOpcionFormato(OpcionesCrearModificarPlazaCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(), ErroresValidacionCrearModificarPlaza.Formato.ordinal());
	}
	
	// Métodos para acceder al tercer Bloque

		// Métodos para acceder a los criterios Crud
	
	public String leerOpcionCheck(OpcionesCrearModificarPlazaCheck opcion) {
		return crudcheck.leerOpcion(opcion.ordinal());
	}
	
	public void escribirOpcionCheck(OpcionesCrearModificarPlazaCheck opcion, String valor) {
		crudcheck.escribirOpcion(opcion.ordinal(), valor);
	}
	
	public void borrarOpcionCheck(OpcionesCrearModificarPlazaCheck opcion) {
		crudcheck.borrarOpcion(opcion.ordinal());
	}
	
	public boolean hayErrorValidacionOpcionCheck(OpcionesCrearModificarPlazaCheck opcion) {
		return crudcheck.hayErrorValidacionOpcion(opcion.ordinal(), ErroresValidacionCrearModificarPlaza.Cualquiera.ordinal());
	}
	
	public boolean hayErrorValidacionOpcionCheckRequerido(OpcionesCrearModificarPlazaCheck opcion) {
		return crudcheck.hayErrorValidacionOpcion(opcion.ordinal(), ErroresValidacionCrearModificarPlaza.Requerido.ordinal());
	}
	
	public boolean hayErrorValidacionOpcionCheckFormato(OpcionesCrearModificarPlazaCheck opcion) {
		return crudcheck.hayErrorValidacionOpcion(opcion.ordinal(), ErroresValidacionCrearModificarPlaza.Formato.ordinal());
	}
	
		// Métodos para acceder a los criterios Dropdown
	
	public void clicarCriterioDesplegable(OpcionesCrearModificarPlazaCheckDropdown opcion) {
		crudcheck.clickCriterioCheck(opcion.ordinal());
	}
	
	public Error seleccionarCriterioDesplegable(OpcionesCrearModificarPlazaCheckDropdown opcion, String valor) {
		return crudcheck.seleccionarCriterioDesplegable(opcion.ordinal(), valor);
	}
	
	public String leerCriterioDesplegable(OpcionesCrearModificarPlazaCheckDropdown opcion) {
		return crudcheck.leerCriterioDesplegable(opcion.ordinal());
	}
	

	// Métodos para acceder a los criterios CheckField

	public void clicarOpcionCheckField(OpcionesCrearModificarPlazaCheckField opcion) {
		crudcheck.clickCriterioCheckField(opcion.ordinal());
	}
	
	public String leerOpcionCheckField(OpcionesCrearModificarPlazaCheckField opcion) {
		return crudcheck.leerCriterioCheckField(opcion.ordinal());
	}
	
	public void escribirOpcionCheckField(OpcionesCrearModificarPlazaCheckField opcion, String valor) {
		crudcheck.escribirCriterioCheckField(opcion.ordinal(), valor);
	}
	
	public void borrarOpcionCheckField(OpcionesCrearModificarPlazaCheckField opcion) {
		crudcheck.borrarOpcion(opcion.ordinal());
	}
	
	public boolean hayErrorValidacionOpcionCheckField(OpcionesCrearModificarPlazaCheckField opcion) {
		return crudcheck.hayErrorValidacionCheckField(opcion.ordinal(), ErroresValidacionCrearModificarPlaza.Cualquiera.ordinal());
	}
	
	public boolean hayErrorValidacionOpcionCheckFieldRequerido(OpcionesCrearModificarPlazaCheckField opcion) {
		return crudcheck.hayErrorValidacionCheckField(opcion.ordinal(), ErroresValidacionCrearModificarPlaza.Requerido.ordinal());
	}
	
	public boolean hayErrorValidacionOpcionCheckFieldFormato(OpcionesCrearModificarPlazaCheckField opcion) {
		return crudcheck.hayErrorValidacionCheckField(opcion.ordinal(), ErroresValidacionCrearModificarPlaza.Formato.ordinal());
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones,  String encabezadoVar,
			String[] labelsVariable, String[] valoresvariables, String encabezado2, String[] labels, String encabezado3,
			String[] labelscheck, String[] labelscheckdropdown, String[] labelscheckfield) {
		
		boolean resultado=super.sintacticAnalysis(titulo, subtitulo, botones);
		resultado=crudVar.sintacticAnalysis(encabezadoVar, labelsVariable, valoresvariables,null) && resultado;
		resultado=crud.sintacticAnalysis(encabezado2,labels) && resultado;
		return crudcheck.sintacticAnalysis(encabezado3,labelscheck,labelscheckdropdown,labelscheckfield) && resultado;
	}
}
