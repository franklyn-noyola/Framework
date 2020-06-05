package ventanasITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos;

import bloques.CrudBlock;
import bloques.CrudBlockVariable;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.ErroresValidacionCrearModificarNodo;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.OpcionesCrearNodoViaCampo;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.OpcionesCrearNodoViaVariable;
import unidadesGraficas.TipoBy;
import ventanasComunes.Screen;

public class CrearNodoViaScreen extends Screen {

	private CrudBlockVariable crudVar;
	private CrudBlock crud;
	
	private TipoBy i_encabezado_var=TipoBy.ID;
	private String str_encabezado_var="ctl00_ContentZone_LblSubtitlePlaza";
	private final int num_criterios_var=3;
	private TipoBy[] i_labelvar={TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_labelvar={"ctl00_ContentZone_LblPlzCode","ctl00_ContentZone_LblPlzDescription","ctl00_ContentZone_LblPlzAddress"};
	private TipoBy[] i_fieldvar={TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_fieldvar={"ctl00_ContentZone_LblPlzCodeVal","ctl00_ContentZone_LblPlzDescriptionVal","ctl00_ContentZone_LblPlzAddressVal"};
	private TipoBy i_encabezado_crud=TipoBy.ID;
	private String str_encabezado_crud="ctl00_ContentZone_LblSubtitleInfo";
	private final int num_criterios_campo=2;
	private final int num_criterios_desplegable=0;
	private final int num_criterios_fecha=0;
	private TipoBy[] i_label={TipoBy.ID,TipoBy.ID};
	private String[] str_label={"ctl00_ContentZone_LblCode","ctl00_ContentZone_LblAddress"};
	private TipoBy[] i_field={TipoBy.ID,TipoBy.ID,};
	private String[] str_field={"ctl00_ContentZone_BoxCode","ctl00_ContentZone_BoxAddress"};
	private TipoBy[][] i_tooltipcampo={{TipoBy.ID,TipoBy.ID},{TipoBy.ID,TipoBy.ID}};
	private String[][] str_tooltipcampo={{"ctl00_ContentZone_ValBoxCode","ctl00_ContentZone_ValBoxCodeExp"},
			{"ctl00_ContentZone_ValAddress1","ctl00_ContentZone_ValAddress2"}};
	private TipoBy[][][] i_tooltipfecha=null;
	private String[][][] str_tooltipfecha=null;
	
	public CrearNodoViaScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, TipoBy[] i_botones, String[] str_botones, TipoBy i_msgerror, String str_msgerror) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,i_msgerror,str_msgerror);
		crudVar=new CrudBlockVariable(i_encabezado_var, str_encabezado_var,num_criterios_var, i_labelvar,str_labelvar, 
				i_fieldvar,str_fieldvar,0, 0, 0, null, null, null, null,null, null,null, null);
		crud=new CrudBlock(i_encabezado_crud, str_encabezado_crud, num_criterios_campo, num_criterios_desplegable,
				 num_criterios_fecha, i_label, str_label, i_field, str_field,i_tooltipcampo, str_tooltipcampo,
				 i_tooltipfecha, str_tooltipfecha);
	}
	
	public String leerOpcionVariable(OpcionesCrearNodoViaVariable opcion) {
		return crudVar.leerOpcionVariable(opcion.ordinal());
	}
	
	public String leerOpcion(OpcionesCrearNodoViaCampo opcion) {
		return crud.leerOpcion(opcion.ordinal());
	}
	
	public void escribirOpcion(OpcionesCrearNodoViaCampo opcion, String valor) {
		crud.escribirOpcion(opcion.ordinal(), valor);
	}
	
	public void borrarOpcion(OpcionesCrearNodoViaCampo opcion) {
		crud.borrarOpcion(opcion.ordinal());
	}
	
	public boolean hayErrorValidacionOpcion(OpcionesCrearNodoViaCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(),ErroresValidacionCrearModificarNodo.Cualquiera.ordinal());
	}
	
	public boolean hayErrorValidacionOpcionRequerido(OpcionesCrearNodoViaCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(),ErroresValidacionCrearModificarNodo.Requerido.ordinal());
	}
	
	public boolean hayErrorValidacionOpcionFormato(OpcionesCrearNodoViaCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(),ErroresValidacionCrearModificarNodo.Formato.ordinal());
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones,  String encabezadoVar,
			String[] labelsVariable, String[] valoresvariables, String encabezado2, String[] labels) {
		
		boolean resultado=super.sintacticAnalysis(titulo, subtitulo, botones);
		resultado=crudVar.sintacticAnalysis(encabezadoVar, labelsVariable, valoresvariables,null) && resultado;
		return crud.sintacticAnalysis(encabezado2,labels) && resultado;

	}
}
