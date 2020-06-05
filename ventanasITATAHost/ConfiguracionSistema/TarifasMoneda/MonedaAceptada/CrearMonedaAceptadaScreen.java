package ventanasITATAHost.ConfiguracionSistema.TarifasMoneda.MonedaAceptada;

import bloques.CrudBlock;
import procesosComunes.Error;
import procesosITATAHost.ConfiguracionSistema.TarifasMoneda.MonedaAceptada.ErroresValidacionDenominacionCrearMonedaAceptada;
import procesosITATAHost.ConfiguracionSistema.TarifasMoneda.MonedaAceptada.OpcionesCrearMonedaAceptadaCampo;
import procesosITATAHost.ConfiguracionSistema.TarifasMoneda.MonedaAceptada.OpcionesCrearMonedaAceptadaDesplegable;
import unidadesGraficas.TipoBy;
import ventanasComunes.Screen;

public class CrearMonedaAceptadaScreen extends Screen {

	private CrudBlock crud;
	
	private TipoBy i_encabezado_crud=TipoBy.ID;
	private String str_encabezado_crud="ctl00_ContentZone_LblSubtitleInfo";
	private final int num_criterios_campo=1;
	private final int num_criterios_desplegable=2;
	private final int num_criterios_fecha=0;
	private TipoBy[] i_label={TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_label={"ctl00_ContentZone_LblFaceValue","ctl00_ContentZone_LblTollCompany","ctl00_ContentZone_LblCashType"};
	private TipoBy[] i_field={TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_field={"ctl00_ContentZone_BoxFaceValue","ctl00_ContentZone_CmbCompany","ctl00_ContentZone_CmbCashType"};
	private TipoBy[][] i_tooltipcampo={{TipoBy.ID,TipoBy.ID}};
	private String[][] str_tooltipcampo={{"ctl00_ContentZone_ValBoxRequiredFaceValue","ctl00_ContentZone_ValBoxFaceValue"}};
	private final TipoBy[][][] i_tooltipfecha=null;
	private final String[][][] str_tooltipfecha=null;
	
	public CrearMonedaAceptadaScreen(TipoBy i_titulo, String str_titulo, TipoBy i_subtitulo, String str_subtitulo, TipoBy[] i_botones, String[] str_botones, TipoBy i_msgerror, String str_msgerror) {
		
		super(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones, str_botones,i_msgerror,str_msgerror);
		crud=new CrudBlock(i_encabezado_crud, str_encabezado_crud, num_criterios_campo, num_criterios_desplegable,
				 num_criterios_fecha, i_label, str_label, i_field, str_field,i_tooltipcampo, str_tooltipcampo,
				 i_tooltipfecha, str_tooltipfecha);
	}

	public String leerOpcion(OpcionesCrearMonedaAceptadaCampo opcion) {
		return crud.leerOpcion(opcion.ordinal());
	}
	
	public void escribirOpcion(OpcionesCrearMonedaAceptadaCampo opcion, String valor) {
		crud.escribirOpcion(opcion.ordinal(), valor);
	}
	
	public void borrarOpcion(OpcionesCrearMonedaAceptadaCampo opcion) {
		crud.borrarOpcion(opcion.ordinal());
	}
	
	public boolean hayErrorValidacionOpcion(OpcionesCrearMonedaAceptadaCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(),ErroresValidacionDenominacionCrearMonedaAceptada.Cualquiera.ordinal());
	}
	
	public boolean hayErrorValidacionOpcionRequerido(OpcionesCrearMonedaAceptadaCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(),ErroresValidacionDenominacionCrearMonedaAceptada.Requerido.ordinal());
	}
	
	public boolean hayErrorValidacionOpcionFormato(OpcionesCrearMonedaAceptadaCampo opcion) {
		return crud.hayErrorValidacionOpcion(opcion.ordinal(),ErroresValidacionDenominacionCrearMonedaAceptada.Formato.ordinal());
	}
	
	public String leerOpcionDesplegable(OpcionesCrearMonedaAceptadaDesplegable opcion) {
		return crud.leerCriterioDesplegable(opcion.ordinal());
	}
	
	public Error seleccionarOpcionDesplegable(OpcionesCrearMonedaAceptadaDesplegable desplegable,String opcion) {
		return crud.desplegarCriterioDesplegable(desplegable.ordinal(),opcion);
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones,  String encabezado_crud, String[] labels_crud) {
		
		boolean resultado=super.sintacticAnalysis(titulo, subtitulo, botones);
		return crud.sintacticAnalysis(encabezado_crud,labels_crud) && resultado;

	}
}
