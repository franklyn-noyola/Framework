package ventanasITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos;

import bloques.CrudBlockVariable;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.ErroresValidacionCrearModificarNodo;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.OpcionesModificarNodoCampo;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.OpcionesModificarNodoVariable;
import unidadesGraficas.TipoBy;
import ventanasComunes.Screen;

public class ModificarNodoScreen extends Screen {

	private CrudBlockVariable crud;
	
	private TipoBy i_encabezado=TipoBy.ID;
	private String str_encabezado="ctl00_ContentZone_LblSubtitleInfo";
	private final int num_criterios_var=1;
	private TipoBy[] i_labelvar={TipoBy.ID};
	private String[] str_labelvar={"ctl00_ContentZone_LblCode"};
	private TipoBy[] i_fieldvar={TipoBy.ID};
	private String[] str_fieldvar={"ctl00_ContentZone_LblCodeVal"};
	private final int num_criterios_campo=2;
	private final int num_criterios_desplegable=0;
	private final int num_criterios_fecha=0;
	private TipoBy[] i_label={TipoBy.ID,TipoBy.ID};
	private String[] str_label={"ctl00_ContentZone_LblAddress","ctl00_ContentZone_LblDescription"};
	private TipoBy[] i_field={TipoBy.ID,TipoBy.ID};
	private String[] str_field={"ctl00_ContentZone_BoxAddress","ctl00_ContentZone_BoxDescription"};
	private TipoBy[][] i_tooltipcampo={{TipoBy.ID,TipoBy.ID},null};
	private String[][] str_tooltipcampo={{"ctl00_ContentZone_ValAddress1","ctl00_ContentZone_ValAddress2"},null};
	private TipoBy[][][] i_tooltipfecha=null;
	private String[][][] str_tooltipfecha=null;
	
	public ModificarNodoScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, TipoBy[] i_botones, String[] str_botones,
			TipoBy i_msgerror, String str_msgerror) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,i_msgerror,str_msgerror);
		crud=new CrudBlockVariable(i_encabezado, str_encabezado,num_criterios_var, i_labelvar,str_labelvar, 
				i_fieldvar,str_fieldvar,num_criterios_campo, num_criterios_desplegable,
				 num_criterios_fecha, i_label, str_label, i_field, str_field,i_tooltipcampo, str_tooltipcampo,
				 i_tooltipfecha, str_tooltipfecha);

	}

	public String leerOpcionVariable(OpcionesModificarNodoVariable opcion) {
		return crud.leerOpcionVariable(opcion.ordinal());
	}
	
	public String leerOpcion(OpcionesModificarNodoCampo opcion) {
		return crud.leerOpcion(opcion.ordinal());
	}
	
	public void borrarOpcion(OpcionesModificarNodoCampo opcion) {
		crud.borrarOpcion(opcion.ordinal());
	}
	
	public void escribirOpcion(OpcionesModificarNodoCampo opcion, String valor) {
		crud.borrarOpcion(opcion.ordinal());
		crud.escribirOpcion(opcion.ordinal(), valor);
	}
	
	public boolean hayErrorValidacionOpcion(OpcionesModificarNodoCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(), ErroresValidacionCrearModificarNodo.Cualquiera.ordinal());
	}
	
	public boolean hayErrorValidacionOpcionRequerido(OpcionesModificarNodoCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(), ErroresValidacionCrearModificarNodo.Requerido.ordinal());
	}
	
	public boolean hayErrorValidacionOpcionFormato(OpcionesModificarNodoCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(), ErroresValidacionCrearModificarNodo.Formato.ordinal());
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones,  String encabezado,
			String[] labels_variable, String[] valoresvariables, String[] labels) {
		
		boolean resultado=super.sintacticAnalysis(titulo, subtitulo, botones);
		return crud.sintacticAnalysis(encabezado,labels_variable,valoresvariables,labels) && resultado;

	}
}
