package ventanasITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias;

import bloques.CrudBlock;
import procesosComunes.Error;
import bloques.CrudBlockVariable;
import bloques.CrudCheckLabelFieldBlock;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.ErroresValidacionCrearModificarPlaza;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.OpcionesCrearModificarPlazaCheck;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.OpcionesCrearModificarPlazaCheckDropdown;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.OpcionesCrearModificarPlazaCheckField;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.OpcionesCrearModificarViaCampo;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.OpcionesCrearModificarViaDesplegable;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.OpcionesCrearModificarViaVariable1;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.OpcionesCrearModificarViaVariable2;
import unidadesGraficas.TipoBy;
import ventanasComunes.Screen;

public class CrearModificarViaScreen extends Screen {

	private CrudBlockVariable crudVar1;
	private CrudBlockVariable crudVar2;
	private CrudBlock crud;
	private CrudCheckLabelFieldBlock crudcheck;
	
	private TipoBy i_encabezado1=TipoBy.ID;
	private String str_encabezado1="ctl00_ContentZone_LblSubtitleCompany";
	private final int num_criterios_var1=2;
	private TipoBy[] i_labelvar1={TipoBy.ID,TipoBy.ID};
	private String[] str_labelvar1={"ctl00_ContentZone_LblCmpyCode","ctl00_ContentZone_LblCmpyName"};
	private TipoBy[] i_fieldvar1={TipoBy.ID,TipoBy.ID};
	private String[] str_fieldvar1={"ctl00_ContentZone_LblCmpyCodeVal","ctl00_ContentZone_LblCmpyNameVal"};
	
	private TipoBy i_encabezado2=TipoBy.ID;
	private String str_encabezado2="ctl00_ContentZone_LblSubtitlePlaza";
	private final int num_criterios_var2=3;
	private TipoBy[] i_labelvar2={TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_labelvar2={"ctl00_ContentZone_LblPlzCode","ctl00_ContentZone_LblPlzName","ctl00_ContentZone_LblPlzNode"};
	private TipoBy[] i_fieldvar2={TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_fieldvar2={"ctl00_ContentZone_LblPlzCodeVal","ctl00_ContentZone_LblPlzNameVal","ctl00_ContentZone_LblPlzNodeVal"};
	
	private TipoBy i_encabezado3=TipoBy.ID;
	private String str_encabezado3="ctl00_ContentZone_LblSubtitleLane";
	private final int num_criterios_campo=1;
	private final int num_criterios_desplegable=2;
	private final int num_criterios_fecha=0;
	private TipoBy[] i_label={TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_label={"ctl00_ContentZone_LblLaneNum","ctl00_ContentZone_LblLaneDirection","ctl00_ContentZone_LblLaneType"};
	private TipoBy[] i_field={TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_field={"ctl00_ContentZone_BoxLaneNum","ctl00_ContentZone_CmbDir","ctl00_ContentZone_CmbType"};
	private TipoBy[][] i_tooltip={{TipoBy.ID,TipoBy.ID}};
	private String[][] str_tooltip={{"ctl00_ContentZone_ValBoxLaneNum","ctl00_ContentZone_ValBoxLaneNumExpr"}};
	private final TipoBy[][][] i_tooltipfecha=null;
	private final String[][][] str_tooltipfecha=null;
	
	private TipoBy i_encabezado4=TipoBy.ID;
	private String str_encabezado4="ctl00_ContentZone_LblSubtitleNode";
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
	
	public CrearModificarViaScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, TipoBy[] i_botones, String[] str_botones,
			TipoBy i_msgerror, String str_msgerror) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,i_msgerror,str_msgerror);
		crudVar1=new CrudBlockVariable(i_encabezado1, str_encabezado1,num_criterios_var1, i_labelvar1,str_labelvar1, 
				i_fieldvar1,str_fieldvar1,0, 0, 0, null, null, null, null,null, null, null, null);
		crudVar2=new CrudBlockVariable(i_encabezado2, str_encabezado2,num_criterios_var2, i_labelvar2,str_labelvar2, 
				i_fieldvar2,str_fieldvar2,0, 0, 0, null, null, null, null,null, null, null, null);
		crud=new CrudBlock(i_encabezado3, str_encabezado3, num_criterios_campo, num_criterios_desplegable,
				 num_criterios_fecha, i_label, str_label, i_field, str_field,i_tooltip, str_tooltip, i_tooltipfecha,
				 str_tooltipfecha);
		crudcheck=new CrudCheckLabelFieldBlock(i_encabezado4, str_encabezado4, num_criterios_campocheck,  num_criterios_desplegablecheck,
				num_criterios_fechacheck, num_criterios_checkdropdown, num_criterios_checkfield, i_labelcheck, str_labelcheck,
				i_fieldcheck, str_fieldcheck, i_tooltipcheck, str_tooltipcheck, null,null,
				i_labeldropdown, str_labeldropdown, i_check, str_check, i_fielddropdown, str_fielddropdown, 
				i_labelfield, str_labelfield, i_check2, str_check2,
				i_fieldcheck2, str_fieldcheck2, i_tooltipcheck2, str_tooltipcheck2);
	}

	// Métodos para acceder al primer Bloque
	
	public String leerOpcionVariable1(OpcionesCrearModificarViaVariable1 opcion) {
		return crudVar1.leerOpcionVariable(opcion.ordinal());
	}
	
	// Métodos para acceder al segundo Bloque
	
	public String leerOpcionVariable2(OpcionesCrearModificarViaVariable2 opcion) {
		return crudVar2.leerOpcionVariable(opcion.ordinal());
	}
	
	// Métodos para acceder al tercer Bloque
	
	public String leerOpcion(OpcionesCrearModificarViaCampo opcion) {
		return crud.leerOpcion(opcion.ordinal());
	}
	
	public void borrarOpcion(OpcionesCrearModificarViaCampo opcion) {
		crud.borrarOpcion(opcion.ordinal());
	}
	
	public void escribirOpcion(OpcionesCrearModificarViaCampo opcion, String valor) {
		crud.borrarOpcion(opcion.ordinal());
		crud.escribirOpcion(opcion.ordinal(), valor);
	}
	
	public String leerOpcionDesplegable(OpcionesCrearModificarViaDesplegable opcion) {
		return crud.leerCriterioDesplegable(opcion.ordinal());
	}
	
	public Error seleccionarOpcion(OpcionesCrearModificarViaDesplegable opcion, String valor) {
		return crud.desplegarCriterioDesplegable(opcion.ordinal(), valor);
	}
	
	public boolean hayErrorValidacionOpcion(OpcionesCrearModificarViaCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(), ErroresValidacionCrearModificarPlaza.Cualquiera.ordinal());
	}
	
	public boolean hayErrorValidacionOpcionRequerido(OpcionesCrearModificarViaCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(), ErroresValidacionCrearModificarPlaza.Requerido.ordinal());
	}
	
	public boolean hayErrorValidacionOpcionFormato(OpcionesCrearModificarViaCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(), ErroresValidacionCrearModificarPlaza.Formato.ordinal());
	}
	
	// Métodos para acceder al cuarto Bloque
	
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
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones,  String encabezadoVar1,
			String[] labelsVariable1, String[] valoresvariables1, String encabezadoVar2,
			String[] labelsVariable2, String[] valoresvariables2, String encabezado3, String[] labels,
			String encabezado4, String[] labelCriteriosBusqueda, String[] labelCriteriosCheckDropdown, String[] labelCriteriosCheckField) {
		
		boolean resultado=super.sintacticAnalysis(titulo, subtitulo, botones);
		resultado=crudVar1.sintacticAnalysis(encabezadoVar1, labelsVariable1, valoresvariables1,null) && resultado;
		resultado=crudVar2.sintacticAnalysis(encabezadoVar2, labelsVariable2, valoresvariables2,null) && resultado;
		resultado=crud.sintacticAnalysis(encabezado3,labels) && resultado;
		return crudcheck.sintacticAnalysis(encabezado4, labelCriteriosBusqueda, labelCriteriosCheckDropdown, labelCriteriosCheckField);

	}
}
