package ventanasITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos;

import bloques.CrudBlock;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.ErroresValidacionCrearModificarNodo;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.OpcionesCrearNodoCampo;
import unidadesGraficas.TipoBy;
import ventanasComunes.Screen;

public class CrearNodoScreen extends Screen {

	private CrudBlock crud;
	
	private TipoBy i_encabezado_crud=TipoBy.ID;
	private String str_encabezado_crud="ctl00_ContentZone_LblSubtitleInfo";
	private final int num_criterios_campo=3;
	private final int num_criterios_desplegable=0;
	private final int num_criterios_fecha=0;
	private TipoBy[] i_label={TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_label={"ctl00_ContentZone_LblCode","ctl00_ContentZone_LblAddress","ctl00_ContentZone_LblDescription"};
	private TipoBy[] i_field={TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_field={"ctl00_ContentZone_BoxCode","ctl00_ContentZone_BoxAddress","ctl00_ContentZone_BoxDescription"};
	private TipoBy[][] i_tooltipcampo={{TipoBy.ID,TipoBy.ID},{TipoBy.ID,TipoBy.ID},null};
	private String[][] str_tooltipcampo={{"ctl00_ContentZone_ValBoxCode","ctl00_ContentZone_ValBoxCodeExp"},
			{"ctl00_ContentZone_ValAddress1","ctl00_ContentZone_ValAddress2"},null};
	private TipoBy[][][] i_tooltipfecha=null;
	private String[][][] str_tooltipfecha=null;
	
	public CrearNodoScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, TipoBy[] i_botones, String[] str_botones, TipoBy i_msgerror, String str_msgerror) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,i_msgerror,str_msgerror);
		crud=new CrudBlock(i_encabezado_crud, str_encabezado_crud, num_criterios_campo, num_criterios_desplegable,
				 num_criterios_fecha, i_label, str_label, i_field, str_field,i_tooltipcampo, str_tooltipcampo,
				 i_tooltipfecha,str_tooltipfecha);
	}
	
	public String leerOpcion(OpcionesCrearNodoCampo opcion) {
		return crud.leerOpcion(opcion.ordinal());
	}
	
	public void escribirOpcion(OpcionesCrearNodoCampo opcion, String valor) {
		crud.escribirOpcion(opcion.ordinal(), valor);
	}
	
	public void borrarOpcion(OpcionesCrearNodoCampo opcion) {
		crud.borrarOpcion(opcion.ordinal());
	}
	
	public boolean hayErrorValidacionOpcion(OpcionesCrearNodoCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(),ErroresValidacionCrearModificarNodo.Cualquiera.ordinal());
	}
	
	public boolean hayErrorValidacionOpcionRequerido(OpcionesCrearNodoCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(),ErroresValidacionCrearModificarNodo.Requerido.ordinal());
	}
	
	public boolean hayErrorValidacionOpcionFormato(OpcionesCrearNodoCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(),ErroresValidacionCrearModificarNodo.Formato.ordinal());
	}

	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones,  String encabezado_crud, String[] labels_crud) {
		
		boolean resultado=super.sintacticAnalysis(titulo, subtitulo, botones);
		return crud.sintacticAnalysis(encabezado_crud,labels_crud) && resultado;

	}
}
