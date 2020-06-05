package ventanasITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Motivos;

import bloques.CrudBlock;
import procesosComunes.Error;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Motivos.ErroresValidacionCampoCrearModificarMotivo;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Motivos.OpcionesCrearModificarMotivosCampo;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Motivos.OpcionesCrearModificarMotivosDesplegable;
import unidadesGraficas.TipoBy;
import ventanasComunes.Screen;

public class CrearModificarMotivoScreen extends Screen {

	private CrudBlock crud;
	
	private TipoBy i_encabezado_crud=null;
	private String str_encabezado_crud=null;
	private final int num_criterios_campo=2;
	private final int num_criterios_desplegable=1;
	private final int num_criterios_fecha=0;
	private TipoBy[] i_label={TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_label={"ctl00_ContentZone_txt_violation_lbl_Description","ctl00_ContentZone_cmb_type_lbl_Description",
			"ctl00_ContentZone_txt_description_lbl_Description"};
	private TipoBy[] i_field={TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_field={"ctl00_ContentZone_txt_violation_box_data","ctl00_ContentZone_txt_description_box_data",
			"ctl00_ContentZone_cmb_type_cmb_dropdown"};
	private TipoBy[][] i_tooltipcampo={{TipoBy.ID},{TipoBy.ID}};
	private String[][] str_tooltipcampo={{"ctl00_ContentZone_txt_violation_ValRequired"},{"ctl00_ContentZone_txt_description_ValRequired"}};
	private TipoBy[][][] i_tooltipfecha=null;
	private String[][][] str_tooltipfecha=null;
	
	public CrearModificarMotivoScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, TipoBy[] i_botones, String[] str_botones, TipoBy i_msgerror, String str_msgerror) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,i_msgerror,str_msgerror);
		crud=new CrudBlock(i_encabezado_crud, str_encabezado_crud, num_criterios_campo, num_criterios_desplegable,
				 num_criterios_fecha, i_label, str_label, i_field, str_field,i_tooltipcampo, str_tooltipcampo,i_tooltipfecha,
				 str_tooltipfecha);
	}

	
	public Error desplegarOpcion(OpcionesCrearModificarMotivosDesplegable desplegable,String opcion) {
		return crud.desplegarCriterioDesplegable(desplegable.ordinal(),opcion);
	}
	
	public String leerOpcion(OpcionesCrearModificarMotivosDesplegable desplegable) {
		return crud.leerCriterioDesplegable(desplegable.ordinal());
	}
	
	public void escribirOpcion(OpcionesCrearModificarMotivosCampo opcion, String valor) {
		crud.escribirOpcion(opcion.ordinal(), valor);
	}
	
	public void borrarOpcion(OpcionesCrearModificarMotivosCampo opcion) {
		crud.borrarOpcion(opcion.ordinal());
	}
	
	public boolean hayErrorValidacionOpcion(OpcionesCrearModificarMotivosCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(),ErroresValidacionCampoCrearModificarMotivo.Cualquiera.ordinal());
	}
	
	public String leerOpcion(OpcionesCrearModificarMotivosCampo opcion) {
		return crud.leerOpcion(opcion.ordinal());
	}

	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones,  String encabezado_crud, String[] labels_crud) {
		
		boolean resultado=super.sintacticAnalysis(titulo, subtitulo, botones);
		return crud.sintacticAnalysis(encabezado_crud,labels_crud) && resultado;

	}
}
