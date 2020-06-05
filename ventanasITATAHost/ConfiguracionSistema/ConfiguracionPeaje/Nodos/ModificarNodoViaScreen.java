package ventanasITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos;


import bloques.CrudBlockVariable;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.ErroresValidacionCrearModificarNodo;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.OpcionesModificarNodoViaCampo;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.OpcionesModificarNodoViaVariable1;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.OpcionesModificarNodoViaVariable2;
import unidadesGraficas.TipoBy;
import ventanasComunes.Screen;

public class ModificarNodoViaScreen extends Screen {

	private CrudBlockVariable crudVar1;
	private CrudBlockVariable crudVar2;
	
	private TipoBy i_encabezado_var1=TipoBy.ID;
	private String str_encabezado_var1="ctl00_ContentZone_LblSubtitlePlaza";
	private final int num_criterios_var1=3;
	private TipoBy[] i_labelvar1={TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_labelvar1={"ctl00_ContentZone_LblPlzCode","ctl00_ContentZone_LblPlzDescription","ctl00_ContentZone_LblPlzAddress"};
	private TipoBy[] i_fieldvar1={TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_fieldvar1={"ctl00_ContentZone_LblPlzCodeVal","ctl00_ContentZone_LblPlzDescriptionVal","ctl00_ContentZone_LblPlzAddressVal"};

	private TipoBy i_encabezado_var2=TipoBy.ID;
	private String str_encabezado_var2="ctl00_ContentZone_LblSubtitleInfo";
	private final int num_criterios_var2=1;
	private TipoBy[] i_labelvar2={TipoBy.ID};
	private String[] str_labelvar2={"ctl00_ContentZone_LblCode"};
	private TipoBy[] i_fieldvar2={TipoBy.ID};
	private String[] str_fieldvar2={"ctl00_ContentZone_LblCodeVal"};

	private final int num_criterios_campo=1;
	private final int num_criterios_desplegable=0;
	private final int num_criterios_fecha=0;
	private TipoBy[] i_label={TipoBy.ID};
	private String[] str_label={"ctl00_ContentZone_LblAddress"};
	private TipoBy[] i_field={TipoBy.ID};
	private String[] str_field={"ctl00_ContentZone_BoxAddress"};
	private TipoBy[][] i_tooltipcampo={{TipoBy.ID,TipoBy.ID}};
	private String[][] str_tooltipcampo={{"ctl00_ContentZone_ValAddress1","ctl00_ContentZone_ValAddress2"}};
	private TipoBy[][][] i_tooltipfecha=null;
	private String[][][] str_tooltipfecha=null;
	
	public ModificarNodoViaScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, TipoBy[] i_botones, String[] str_botones, TipoBy i_msgerror, String str_msgerror) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,i_msgerror,str_msgerror);
		crudVar1=new CrudBlockVariable(i_encabezado_var1, str_encabezado_var1,num_criterios_var1, i_labelvar1,str_labelvar1, 
				i_fieldvar1,str_fieldvar1,0, 0, 0, null, null, null, null,null, null, null, null);
		crudVar2=new CrudBlockVariable(i_encabezado_var2, str_encabezado_var2,num_criterios_var2, i_labelvar2,str_labelvar2, 
				i_fieldvar2,str_fieldvar2,num_criterios_campo, num_criterios_desplegable, num_criterios_fecha, i_label, 
				str_label, i_field, str_field,i_tooltipcampo, str_tooltipcampo, i_tooltipfecha, str_tooltipfecha);
	}
	
	public String leerOpcionVariable1(OpcionesModificarNodoViaVariable1 opcion) {
		return crudVar1.leerOpcionVariable(opcion.ordinal());
	}
	
	public String leerOpcionVariable2(OpcionesModificarNodoViaVariable2 opcion) {
		return crudVar2.leerOpcionVariable(opcion.ordinal());
	}
	
	public String leerOpcion(OpcionesModificarNodoViaCampo opcion) {
		return crudVar2.leerOpcion(opcion.ordinal());
	}
	
	public void escribirOpcion(OpcionesModificarNodoViaCampo opcion, String valor) {
		crudVar2.escribirOpcion(opcion.ordinal(), valor);
	}
	
	public void borrarOpcion(OpcionesModificarNodoViaCampo opcion) {
		crudVar2.borrarOpcion(opcion.ordinal());
	}
	
	public boolean hayErrorValidacionOpcion(OpcionesModificarNodoViaCampo opcion) {
		return crudVar2.hayErrorValidacionOpcion(opcion.ordinal(),ErroresValidacionCrearModificarNodo.Cualquiera.ordinal());
	}
	
	public boolean hayErrorValidacionOpcionRequerido(OpcionesModificarNodoViaCampo opcion) {
		return crudVar2.hayErrorValidacionOpcion(opcion.ordinal(),ErroresValidacionCrearModificarNodo.Requerido.ordinal());
	}
	
	public boolean hayErrorValidacionOpcionFormato(OpcionesModificarNodoViaCampo opcion) {
		return crudVar2.hayErrorValidacionOpcion(opcion.ordinal(),ErroresValidacionCrearModificarNodo.Formato.ordinal());
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones,  String encabezadoVar1,
			String[] labelsVariables1, String[] valoresvariables1, String encabezadoVar2, String[] labelsVariables2,
			String[] valoresvariables2, String[] labels) {
		
		boolean resultado=super.sintacticAnalysis(titulo, subtitulo, botones);
		resultado=crudVar1.sintacticAnalysis(encabezadoVar1, labelsVariables1, valoresvariables1,null) && resultado;
		return crudVar2.sintacticAnalysis(encabezadoVar2, labelsVariables2, valoresvariables2, labels) && resultado;


	}
}
