package procesosITATAHost.ConfiguracionSistema.TarifasMoneda.Calendario;

import org.apache.log4j.Logger;

import unidadesGraficas.AlertPopup;
import unidadesGraficas.TipoBy;
import ventanasComunes.FormRadioScreen;
import ventanasComunes.FormScreen;
import ventanasITATAHost.ConfiguracionSistema.TarifasMoneda.Calendario.CalendarioScreen;
import procesosComunes.FormProcess;

public class CalendarioProcess extends FormProcess {
	
	private AlertPopup alert;
	
	private TipoBy[] i_botones= {TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private String[] str_botones= {"ctl00_ButtonsZone_BtnReport_IB_Label","ctl00_ButtonsZone_BtnDownload_IB_Label",
			"ctl00_ButtonsZone_BtnBack_IB_Label"};
	private final TipoBy i_encabezado=null;
	private final String str_encabezado=null;
	private final int num_criterios_campo=0;
	private final int num_criterios_desplegable=0;
	private final int num_criterios_fecha=0;
	private final TipoBy[] i_label=null;
	private final String[] str_label=null;
	private final TipoBy[] i_field=null;
	private final String[] str_field=null;
	private final TipoBy[][] i_tooltipcampo=null;
	private final String[][] str_tooltipcampo=null;
	private final TipoBy[][][] i_tooltipfecha=null;
	private final String[][][] str_tooltipfecha=null;
	
	private final int num_columnas=3;
	private final TipoBy i_leyendamostrado=TipoBy.ID;
	private final String str_leyendamostrado="ctl00_ContentZone_LblYear";
	private final TipoBy i_cabecera=TipoBy.XPATH; // Es la misma de siempre
	private final String str_cabecera="//*[@id='ctl00_ContentZone_TblResults']/tbody/"; // Es la misma de siempre
	private final TipoBy[] i_botonescrud={TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private final String[] str_botonescrud= {"ctl00_ContentZone_BtnCreate","ctl00_ContentZone_BtnModify","ctl00_ContentZone_BtnDelete"};
	private final TipoBy[] i_botonesgrid={TipoBy.ID,TipoBy.ID};
	private final String[] str_botonesgrid= {"ctl00_ContentZone_BtnPrev","ctl00_ContentZone_BtnNext"};

	private final static Logger logger = Logger.getLogger(CalendarioProcess.class);
	 
	public CalendarioProcess() {
		try {
			screen=new CalendarioScreen(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones,
				str_botones, i_msgerror,str_msgerror,  i_encabezado,  str_encabezado,
				 num_criterios_campo, num_criterios_desplegable, num_criterios_fecha,
				 i_label,  str_label,  i_field,  str_field,  i_tooltipcampo,  str_tooltipcampo, i_tooltipfecha,
				 str_tooltipfecha, i_botonescrud,  str_botonescrud,  i_cabecera,  str_cabecera, 
				 i_leyendamostrado,  str_leyendamostrado,  i_botonesgrid,
				 str_botonesgrid,  num_columnas,1);
			alert=new AlertPopup();
		}
		catch (RuntimeException e) {
			System.exit(0);
		}
	}
	
	public AlertPopup alerta() {
		return alert;
	}
	
	public boolean hayAlerta() {
		return alert.hayAlerta();
	}
	
	public void aceptarAlerta() {
		alert.aceptar();
	}
	
	public void cancelarAlerta() {
		alert.cancelar();
	}
	
	public void crear() {
		((FormRadioScreen) screen).clickBotonCrud(BotonesCrudCalendario.Crear.ordinal());
	}
	
	public void modificar() {
		((FormRadioScreen) screen).clickBotonCrud(BotonesCrudCalendario.Modificar.ordinal());
	}
	
	public void eliminar() {
		((FormRadioScreen) screen).clickBotonCrud(BotonesCrudCalendario.Eliminar.ordinal());
	}
	
	public void informe() {
		screen.clickBoton(BotonesCalendario.Informe.ordinal());
	}
	
	public void enviarAVias() {
		screen.clickBoton(BotonesCalendario.EnviarAvias.ordinal());
	}
	
	public void volver() {
		screen.clickBoton(BotonesCalendario.Volver.ordinal());
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String[] botonesCRUD,
			String[] labelCriteriosBusqueda, String[] columnasgrid, String[] botonesgrid) {
		logger.debug("Calendario: inicio análisis sintáctico");
		return ((FormScreen) screen).sintacticAnalysis(titulo, subtitulo, botones, botonesCRUD,
				null, labelCriteriosBusqueda, columnasgrid, botonesgrid);
	}

}
