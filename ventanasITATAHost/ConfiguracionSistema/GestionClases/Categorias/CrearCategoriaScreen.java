package ventanasITATAHost.ConfiguracionSistema.GestionClases.Categorias;

import bloques.CrudBlock;
import procesosComunes.Error;
import procesosITATAHost.ConfiguracionSistema.GestionClases.Categorias.ErroresValidacionCampoModificarCategoria;
import procesosITATAHost.ConfiguracionSistema.GestionClases.Categorias.OpcionesCrearCategoriaCampo;
import procesosITATAHost.ConfiguracionSistema.GestionClases.Categorias.OpcionesCrearCategoriaDesplegable;
import unidadesGraficas.TipoBy;
import ventanasComunes.Screen;

public class CrearCategoriaScreen extends Screen {

	private CrudBlock crud;
	
	private TipoBy i_encabezado_crud=TipoBy.ID;
	private String str_encabezado_crud="ctl00_ContentZone_LblSubtitleInfo";
	private final int num_criterios_campo=2;
	private final int num_criterios_desplegable=2;
	private final int num_criterios_fecha=0;
	private TipoBy[] i_label={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_label={"ctl00_ContentZone_LblId","ctl00_ContentZone_LblDescription","ctl00_ContentZone_LblTollCompany","ctl00_ContentZone_LblType"};
	private TipoBy[] i_field={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_field={"ctl00_ContentZone_BoxId","ctl00_ContentZone_BoxDescription","ctl00_ContentZone_CmbCompany","ctl00_ContentZone_CmbType"};
	private TipoBy[][] i_tooltipcampo={{TipoBy.ID,TipoBy.ID},{TipoBy.ID,null}};
	private String[][] str_tooltipcampo={{"ctl00_ContentZone_ValBoxId","ctl00_ContentZone_ValBoxId2"},
						{"ctl00_ContentZone_ValDescription",null}};
	private TipoBy[][][] i_tooltipfecha=null;
	private String[][][] str_tooltipfecha=null;
	
	public CrearCategoriaScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, TipoBy[] i_botones, String[] str_botones, TipoBy i_msgerror, String str_msgerror) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,i_msgerror,str_msgerror);
		crud=new CrudBlock(i_encabezado_crud, str_encabezado_crud, num_criterios_campo, num_criterios_desplegable,
				 num_criterios_fecha, i_label, str_label, i_field, str_field,i_tooltipcampo, str_tooltipcampo,
				 i_tooltipfecha, str_tooltipfecha);
	}

	public String leerOpcion(OpcionesCrearCategoriaCampo opcion) {
		return crud.leerOpcion(opcion.ordinal());
	}
	
	public void escribirOpcion(OpcionesCrearCategoriaCampo opcion, String valor) {
		crud.escribirOpcion(opcion.ordinal(), valor);
	}
	
	public void borrarOpcion(OpcionesCrearCategoriaCampo opcion) {
		crud.borrarOpcion(opcion.ordinal());
	}
	
	public boolean hayErrorValidacionOpcion(OpcionesCrearCategoriaCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(),ErroresValidacionCampoModificarCategoria.Cualquiera.ordinal());
	}
	
	public boolean hayErrorValidacionOpcionRequerido(OpcionesCrearCategoriaCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(),ErroresValidacionCampoModificarCategoria.Requerido.ordinal());
	}
	
	public boolean hayErrorValidacionOpcionFormato(OpcionesCrearCategoriaCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(),ErroresValidacionCampoModificarCategoria.Formato.ordinal());
	}
	
	public String leerOpcionDesplegable(OpcionesCrearCategoriaDesplegable opcion) {
		return crud.leerCriterioDesplegable(opcion.ordinal());
	}
	
	public Error seleccionarOpcionDesplegable(OpcionesCrearCategoriaDesplegable desplegable,String opcion) {
		return crud.desplegarCriterioDesplegable(desplegable.ordinal(),opcion);
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones,  String encabezado_crud, String[] labels_crud) {
		
		boolean resultado=super.sintacticAnalysis(titulo, subtitulo, botones);
		return crud.sintacticAnalysis(encabezado_crud,labels_crud) && resultado;

	}
}
