package ventanasITATAHost.ConfiguracionSistema.Operadores.GestionGrupos;

import bloques.CrudBlock;
import procesosComunes.Error;
import bloques.TablasChoiceBlock;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionGruposOperadores.ErroresValidacionCampoCrearModificarGrupoOperadores;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionGruposOperadores.OpcionesCrearModificarGrupoOperadoresCampo;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionGruposOperadores.OpcionesCrearModificarGrupoOperadoresDesplegable;
import unidadesGraficas.TipoBy;
import ventanasComunes.Screen;

public class CrearModificarGrupoOperadoresScreen extends Screen {

	private CrudBlock crud;
	private TablasChoiceBlock tablas;
	
	private TipoBy i_encabezado_crud=TipoBy.ID;
	private String str_encabezado_crud="ctl00_ContentZone_LblSubtitleInfo_Lbl_SubtitleInfo";
	private final int num_criterios_campo=1;
	private final int num_criterios_desplegable=2;
	private final int num_criterios_fecha=0;
	private TipoBy[] i_label={TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_label={"ctl00_ContentZone_txt_Description_lbl_Description","ctl00_ContentZone_cmb_PlazaAccess_lbl_Description",
			"ctl00_ContentZone_cmb_LaneAccess_lbl_Description"};
	private TipoBy[] i_field={TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_field={"ctl00_ContentZone_txt_Description_box_data","ctl00_ContentZone_cmb_PlazaAccess_cmb_dropdown",
			"ctl00_ContentZone_cmb_LaneAccess_cmb_dropdown"};
	private TipoBy[][] i_tooltipcampo= {{TipoBy.ID}}; 
	private String[][] str_tooltipcampo= {{"ctl00_ContentZone_txt_Description_ValRequired"}};
	private TipoBy[][][] i_tooltipfecha=null;
	private String[][][] str_tooltipfecha=null;
	
	private TipoBy i_encabezado_tablas=TipoBy.ID;
	private String str_encabezado_tablas="ctl00_ContentZone_LblSubtitlePerm_Lbl_SubtitleInfo";
	private TipoBy[] i_titulostabla={TipoBy.ID,TipoBy.ID};
	private String[] str_titulostabla= {"ctl00_ContentZone_LblGrpPerm","ctl00_ContentZone_LblAllPerm"};
	private TipoBy[] i_botonestabla={TipoBy.ID,TipoBy.ID};
	private String[] str_botonestabla= {"ctl00_ContentZone_btn_add","ctl00_ContentZone_btn_del"};
	private TipoBy i_tablaizquierda=TipoBy.ID;;
	private String str_tablaizquierda="ctl00_ContentZone_list_permits";
	private TipoBy i_tabladerecha=TipoBy.ID;
	private String str_tabladerecha="ctl00_ContentZone_list_all_permits";

	public CrearModificarGrupoOperadoresScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, TipoBy[] i_botones, String[] str_botones, TipoBy i_msgerror, String str_msgerror) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,i_msgerror,str_msgerror);
		crud=new CrudBlock(i_encabezado_crud, str_encabezado_crud, num_criterios_campo, num_criterios_desplegable,
				 num_criterios_fecha, i_label, str_label, i_field, str_field,i_tooltipcampo,str_tooltipcampo,
				 i_tooltipfecha, str_tooltipfecha);
		tablas=new TablasChoiceBlock(i_encabezado_tablas, str_encabezado_tablas, i_titulostabla, str_titulostabla, i_botonestabla, str_botonestabla,
				i_tablaizquierda,str_tablaizquierda,i_tabladerecha,str_tabladerecha);
	}
	
	public String leerOpcion(OpcionesCrearModificarGrupoOperadoresCampo opcion) {
		return crud.leerOpcion(opcion.ordinal());
	}
	
	public void escribirOpcion(OpcionesCrearModificarGrupoOperadoresCampo opcion, String valor) {
		crud.escribirOpcion(opcion.ordinal(), valor);
	}
	
	public void borrarOpcion(OpcionesCrearModificarGrupoOperadoresCampo opcion) {
		crud.borrarOpcion(opcion.ordinal());
	}
	
	public boolean hayErrorValidacionOpcion(OpcionesCrearModificarGrupoOperadoresCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(), ErroresValidacionCampoCrearModificarGrupoOperadores.Cualquiera.ordinal());
	}
	
	public boolean hayErrorValidacionOpcionRequerido(OpcionesCrearModificarGrupoOperadoresCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(),ErroresValidacionCampoCrearModificarGrupoOperadores.Requerido.ordinal());
	}
	
	public String leerOpcionDesplegable(OpcionesCrearModificarGrupoOperadoresDesplegable desplegable) {
		return crud.leerCriterioDesplegable(desplegable.ordinal());
	}
	
	public Error desplegarOpcion(OpcionesCrearModificarGrupoOperadoresDesplegable desplegable,String opcion) {
		return crud.desplegarCriterioDesplegable(desplegable.ordinal(),opcion);
	}
	
	public void seleccionarTablaIzquierda(int indice) {
		tablas.seleccionarTablaIzquierda(indice);
	}
	
	public void seleccionarTablaDerecha(int indice) {
		tablas.seleccionarTablaDerecha(indice);
	}
	
	public void seleccionarTablaIzquierda(String opcion) {
		tablas.seleccionarTablaIzquierda(opcion);
	}
	
	public void seleccionarTablaDerecha(String opcion) {
		tablas.seleccionarTablaDerecha(opcion);
	}
	
	public void botonIzquierda() {
		tablas.BotonIzquierda();
	}
	
	public void botonDerecha() {
		tablas.BotonDerecha();
	}
	
	public boolean existeOpcionEnTablaIzquierda(String opcion) {
		return tablas.existeOpcionEnTablaIzquierda(opcion);
	}
	
	public boolean existeOpcionEnTablaDerecha(String opcion) {
		return tablas.existeOpcionEnTablaDerecha(opcion);
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones,  String encabezado_crud, String[] labels_crud, 
			 String encabezado_tablas, String[] labels_tablas, String[] botones_tablas) {
		
		boolean resultado=super.sintacticAnalysis(titulo, subtitulo, botones);
		return resultado && crud.sintacticAnalysis(encabezado_crud,labels_crud) && tablas.sintacticAnalysis(encabezado_tablas,labels_tablas,botones_tablas);

	}
}
