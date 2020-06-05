package ventanasITATAHost.ConfiguracionSistema.ParametrosCuenta;

import bloques.CrudBlock;
import procesosComunes.Error;
import procesosITATAHost.ConfiguracionSistema.ParametrosCuenta.Recargos.ErroresValidacionCampoCrearModificarRecargo;
import procesosITATAHost.ConfiguracionSistema.ParametrosCuenta.Recargos.OpcionesCrearModificarRecargosCampo;
import procesosITATAHost.ConfiguracionSistema.ParametrosCuenta.Recargos.OpcionesCrearModificarRecargosDesplegable;
import unidadesGraficas.TipoBy;
import ventanasComunes.Screen;

public class CrearModificarRecargosScreen extends Screen {

	private CrudBlock crud;
	
	private TipoBy i_encabezado_crud=null;
	private String str_encabezado_crud=null;
	private final int num_criterios_campo=3;
	private final int num_criterios_desplegable=2;
	private final int num_criterios_fecha=0;
	private TipoBy[] i_label={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_label={"ctl00_ContentZone_txt_name_lbl_Description","ctl00_ContentZone_txt_description_lbl_Description",
								"ctl00_ContentZone_lbl_amount", "ctl00_ContentZone_cmb_type_lbl_Description",
								"ctl00_ContentZone_cmb_applicationType_lbl_Description"};
	private TipoBy[] i_field={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,null};
	private String[] str_field={"ctl00_ContentZone_txt_name_box_data","ctl00_ContentZone_txt_description_box_data",
								"ctl00_ContentZone_money_amount_txt_formated","ctl00_ContentZone_cmb_type_cmb_dropdown",null};
	private TipoBy[][] i_tooltipcampo={null,null,{TipoBy.ID}};
	private String[][] str_tooltipcampo={null,null,{"ctl00_ContentZone_money_amount_ValBoxplainValue"}};
	private TipoBy[][][] i_tooltipfecha=null;
	private String[][][] str_tooltipfecha=null;
	
	public CrearModificarRecargosScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, TipoBy[] i_botones, String[] str_botones, TipoBy i_msgerror, String str_msgerror) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,i_msgerror,str_msgerror);
		crud=new CrudBlock(i_encabezado_crud, str_encabezado_crud, num_criterios_campo, num_criterios_desplegable,
				 num_criterios_fecha, i_label, str_label, i_field, str_field,i_tooltipcampo, str_tooltipcampo,
				 i_tooltipfecha, str_tooltipfecha);
	}

	
	public String leerOpcion(OpcionesCrearModificarRecargosCampo opcion) {
		return crud.leerOpcion(opcion.ordinal());
	}
	
	public void escribirOpcion(OpcionesCrearModificarRecargosCampo opcion, String valor) {
		crud.escribirOpcion(opcion.ordinal(), valor);
	}
	
	public void borrarOpcion(OpcionesCrearModificarRecargosCampo opcion) {
		crud.borrarOpcion(opcion.ordinal());
	}
	
	public String leerOpcionDesplegable(OpcionesCrearModificarRecargosDesplegable opcion) {
		return crud.leerCriterioDesplegable(opcion.ordinal());
	}
	
	public Error seleccionarOpcionDesplegable(OpcionesCrearModificarRecargosDesplegable desplegable,String opcion) {
		return crud.desplegarCriterioDesplegable(desplegable.ordinal(),opcion);
	}
	
	public boolean hayErrorValidacionOpcion(OpcionesCrearModificarRecargosCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(),ErroresValidacionCampoCrearModificarRecargo.Formato.ordinal());
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones,  String encabezado_crud, String[] labels_crud) {
		
		boolean resultado=super.sintacticAnalysis(titulo, subtitulo, botones);
		return crud.sintacticAnalysis(encabezado_crud,labels_crud) && resultado;

	}
}
