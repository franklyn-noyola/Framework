package ventanasITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje;

import bloques.CrudBlock;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje.ErroresValidacionCampoCrearModificarParametrosPeaje;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje.OpcionesCrearModificarParametrosPeajeCampo;
import unidadesGraficas.TipoBy;
import ventanasComunes.Screen;

public class CrearModificarParametrosPeajeScreen extends Screen {

	private CrudBlock crud;
	
	private TipoBy i_encabezado_crud=null;
	private String str_encabezado_crud=null;
	private final int num_criterios_campo=2;
	private final int num_criterios_desplegable=0;
	private final int num_criterios_fecha=0;
	private TipoBy[] i_label={TipoBy.ID,TipoBy.ID};
	private String[] str_label={"ctl00_ContentZone_LblId","ctl00_ContentZone_LblValue"};
	private TipoBy[] i_field={TipoBy.ID,TipoBy.ID};
	private String[] str_field={"ctl00_ContentZone_BoxParameter","ctl00_ContentZone_BoxValue"};
	private TipoBy[][] i_tooltipcampo={{TipoBy.ID},null};
	private String[][] str_tooltipcampo={{"ctl00_ContentZone_ValBoxParameter"},null};
	private TipoBy[][][] i_tooltipfecha=null;
	private String[][][] str_tooltipfecha=null;
	
	public CrearModificarParametrosPeajeScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, TipoBy[] i_botones, String[] str_botones, TipoBy i_msgerror, String str_msgerror) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,i_msgerror,str_msgerror);
		crud=new CrudBlock(i_encabezado_crud, str_encabezado_crud, num_criterios_campo, num_criterios_desplegable,
				 num_criterios_fecha, i_label, str_label, i_field, str_field,i_tooltipcampo, str_tooltipcampo,
				 i_tooltipfecha,str_tooltipfecha);
	}

	
	public void escribirOpcion(OpcionesCrearModificarParametrosPeajeCampo opcion, String valor) {
		crud.escribirOpcion(opcion.ordinal(), valor);
	}
	
	public boolean hayErrorValidacionOpcion(OpcionesCrearModificarParametrosPeajeCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(),ErroresValidacionCampoCrearModificarParametrosPeaje.Cualquiera.ordinal());
	}
	
	public String leerOpcion(OpcionesCrearModificarParametrosPeajeCampo opcion) {
		return crud.leerOpcion(opcion.ordinal());
	}
	
	public void borrarOpcion(OpcionesCrearModificarParametrosPeajeCampo opcion) {
		crud.borrarOpcion(opcion.ordinal());
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones,  String encabezado_crud, String[] labels_crud) {
		
		boolean resultado=super.sintacticAnalysis(titulo, subtitulo, botones);
		return crud.sintacticAnalysis(encabezado_crud,labels_crud) && resultado;

	}
}
