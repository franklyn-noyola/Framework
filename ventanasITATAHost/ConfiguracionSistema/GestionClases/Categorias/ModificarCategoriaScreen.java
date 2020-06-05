package ventanasITATAHost.ConfiguracionSistema.GestionClases.Categorias;

import bloques.CrudBlockVariable;
import procesosComunes.Error;
import procesosITATAHost.ConfiguracionSistema.GestionClases.Categorias.ErroresValidacionCampoModificarCategoria;
import procesosITATAHost.ConfiguracionSistema.GestionClases.Categorias.OpcionesModificarCategoriaCampo;
import procesosITATAHost.ConfiguracionSistema.GestionClases.Categorias.OpcionesModificarCategoriaDesplegable;
import procesosITATAHost.ConfiguracionSistema.GestionClases.Categorias.OpcionesModificarCategoriaVariable;
import unidadesGraficas.TipoBy;
import ventanasComunes.Screen;

public class ModificarCategoriaScreen extends Screen {

	private CrudBlockVariable crud;
	
	private TipoBy i_encabezado=TipoBy.ID;
	private String str_encabezado="ctl00_ContentZone_LblSubtitleInfo";
	private final int num_criterios_var=2;
	private TipoBy[] i_labelvar={TipoBy.ID,TipoBy.ID};
	private String[] str_labelvar={"ctl00_ContentZone_LblTollCompany","ctl00_ContentZone_LblId"};
	private TipoBy[] i_fieldvar={TipoBy.ID,TipoBy.ID};
	private String[] str_fieldvar={"ctl00_ContentZone_LblTollCompanyVal","ctl00_ContentZone_LblIdVal"};
	private final int num_criterios_campo=1;
	private final int num_criterios_desplegable=1;
	private final int num_criterios_fecha=0;
	private TipoBy[] i_label={TipoBy.ID,TipoBy.ID};
	private String[] str_label={"ctl00_ContentZone_LblDescription","ctl00_ContentZone_LblType"};
	private TipoBy[] i_field={TipoBy.ID,TipoBy.ID};
	private String[] str_field={"ctl00_ContentZone_BoxDescription","ctl00_ContentZone_CmbType"};
	private TipoBy[][] i_tooltipcampo={{TipoBy.ID}};
	private String[][] str_tooltipcampo= {{"ctl00_ContentZone_ValDescription"}};
	private TipoBy[][][] i_tooltipfecha=null;
	private String[][][] str_tooltipfecha=null;
	
	public ModificarCategoriaScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, TipoBy[] i_botones, String[] str_botones,
			TipoBy i_msgerror, String str_msgerror) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,i_msgerror,str_msgerror);
		crud=new CrudBlockVariable(i_encabezado, str_encabezado,num_criterios_var, i_labelvar,str_labelvar, 
				i_fieldvar,str_fieldvar,num_criterios_campo, num_criterios_desplegable,
				 num_criterios_fecha, i_label, str_label, i_field, str_field,i_tooltipcampo, str_tooltipcampo,
				 i_tooltipfecha, str_tooltipfecha);

	}

	public String leerOpcionVariable(OpcionesModificarCategoriaVariable opcion) {
		return crud.leerOpcionVariable(opcion.ordinal());
	}
	
	public String leerOpcionDesplegable(OpcionesModificarCategoriaDesplegable desplegable) {
		return crud.leerCriterioDesplegable(desplegable.ordinal());
	}
	
	public Error desplegarOpcion(OpcionesModificarCategoriaDesplegable desplegable,String opcion) {
		return crud.desplegarCriterioDesplegable(desplegable.ordinal(),opcion);
	}
	
	public String leerOpcion(OpcionesModificarCategoriaCampo opcion) {
		return crud.leerOpcion(opcion.ordinal());
	}
	
	public void borrarOpcion(OpcionesModificarCategoriaCampo opcion) {
		crud.borrarOpcion(opcion.ordinal());
	}
	
	public void escribirOpcion(OpcionesModificarCategoriaCampo opcion, String valor) {
		crud.borrarOpcion(opcion.ordinal());
		crud.escribirOpcion(opcion.ordinal(), valor);
	}
	
	public boolean hayErrorValidacionOpcion(OpcionesModificarCategoriaCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(), ErroresValidacionCampoModificarCategoria.Cualquiera.ordinal());
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones,  String encabezado,
			String[] labels_variable, String[] valoresvariables, String[] labels) {
		
		boolean resultado=super.sintacticAnalysis(titulo, subtitulo, botones);
		return crud.sintacticAnalysis(encabezado,labels_variable,valoresvariables,labels) && resultado;

	}
}
