package procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias;

import java.sql.ResultSet;

import org.apache.log4j.Logger;

import bloques.CrudBlockVariable;
import unidadesGraficas.AlertPopup;
import unidadesGraficas.TipoBy;
import ventanasComunes.FormRadioScreen;
import procesosComunes.FormProcess;
import testsComunes.RutinasTestComunes;

public class GestionViasProcess extends FormProcess {
	
	private AlertPopup alert;
	private CrudBlockVariable crudVariable1;
	private CrudBlockVariable crudVariable2;
	
	private TipoBy[] i_botones= {TipoBy.ID};
	private String[] str_botones= {"ctl00_ButtonsZone_BtnBack_IB_Label"};
	
	private final TipoBy i_encabezadovar1=TipoBy.ID;
	private final String str_encabezadovar1="ctl00_ContentZone_LblSubtitleCompany_Lbl_SubtitleInfo";
	private final int num_criterios_var1=2;
	private final TipoBy[] i_labelvar1={TipoBy.ID,TipoBy.ID};
	private final String[] str_labelvar1={"ctl00_ContentZone_txt_CmpyCode_lbl_Description","ctl00_ContentZone_txt_CmpyName_lbl_Description"};
	private final TipoBy[] i_fieldvar1={TipoBy.ID,TipoBy.ID};
	private final String[] str_fieldvar1={"ctl00_ContentZone_txt_CmpyCode_box_data","ctl00_ContentZone_txt_CmpyName_box_data"};
	
	private final TipoBy i_encabezadovar2=TipoBy.ID;
	private final String str_encabezadovar2="ctl00_ContentZone_LblSubtitlePlaza_Lbl_SubtitleInfo";
	private final int num_criterios_var2=2;
	private final TipoBy[] i_labelvar2={TipoBy.ID,TipoBy.ID};
	private final String[] str_labelvar2={"ctl00_ContentZone_txt_PlzCode_lbl_Description","ctl00_ContentZone_txt_PlzName_lbl_Description"};
	private final TipoBy[] i_fieldvar2={TipoBy.ID,TipoBy.ID};
	private final String[] str_fieldvar2={"ctl00_ContentZone_txt_PlzCode_box_data","ctl00_ContentZone_txt_PlzName_box_data"};
	
	private final TipoBy i_encabezado=TipoBy.ID;
	private final String str_encabezado="ctl00_ContentZone_LblSubtitleLanes_Lbl_SubtitleInfo";
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
	
	private final int num_columnas=5;
	private final TipoBy i_leyendamostrado=TipoBy.ID;
	private final String str_leyendamostrado="ctl00_ContentZone_tablePager_LblCounter";
	private final TipoBy i_cabecera=TipoBy.XPATH;
	private final String str_cabecera="//*[@id='ctl00_ContentZone_TblResults']/tbody/";
	private final TipoBy[] i_botonescrud={TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private final String[] str_botonescrud= {"ctl00_ContentZone_BtnCreate","ctl00_ContentZone_BtnModify","ctl00_ContentZone_BtnDelete"};
	private final TipoBy[] i_botonesgrid={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private final String[] str_botonesgrid={"ctl00_ContentZone_tablePager_BtnFirst","ctl00_ContentZone_tablePager_BtnPrev","ctl00_ContentZone_tablePager_BtnNext","ctl00_ContentZone_tablePager_BtnLast"};

	private final static Logger logger = Logger.getLogger(GestionViasProcess.class);
	 
	public GestionViasProcess() {
		try {
			screen=new FormRadioScreen(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones,
					str_botones, i_msgerror,str_msgerror,  i_encabezado,  str_encabezado,
					 num_criterios_campo, num_criterios_desplegable, num_criterios_fecha,
					 i_label,  str_label,  i_field,  str_field,  i_tooltipcampo,  str_tooltipcampo,i_tooltipfecha,  
					 str_tooltipfecha, i_botonescrud,  str_botonescrud,  i_cabecera,  str_cabecera, 
					 i_leyendamostrado,  str_leyendamostrado,  i_botonesgrid,
					 str_botonesgrid,  num_columnas);
			crudVariable1=new CrudBlockVariable(i_encabezadovar1,str_encabezadovar1,num_criterios_var1,i_labelvar1,
						str_labelvar1,i_fieldvar1,str_fieldvar1,0,0,0,null,null,null,null,null,null,null,null);
			crudVariable2=new CrudBlockVariable(i_encabezadovar2,str_encabezadovar2,num_criterios_var2,i_labelvar2,
					str_labelvar2,i_fieldvar2,str_fieldvar2,0,0,0,null,null,null,null,null,null,null,null);
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
		((FormRadioScreen) screen).clickBotonCrud(BotonesCrudGestionVias.Crear.ordinal());
	}
	
	public void modificar() {
		((FormRadioScreen) screen).clickBotonCrud(BotonesCrudGestionVias.Modificar.ordinal());
	}
	
	public void eliminar() {
		((FormRadioScreen) screen).clickBotonCrud(BotonesCrudGestionVias.Eliminar.ordinal());
	}
	
	public void volver() {
		screen.clickBoton(BotonesGestionVias.Volver.ordinal());
	}
	
	public boolean buscarRegistroBDEnTabla (ResultSet rs) {
		String[] columnas={"Num. Vía","Sentido","Tipo de vía"};
		String[] valores=new String[columnas.length];
		try {
			valores[0]=rs.getString("lanenum").trim();
			valores[1]=rs.getString("lanedir").trim()+": "+rs.getString("desc1").trim();
			valores[2]=rs.getString("lanetype").trim()+": "+rs.getString("description").trim();
			String[] resultado=obtenerFilaTabla(columnas, valores);
			while (resultado==null && !ultimaTabla()) {
				pulsarBotonTablaSiguiente();
				recargarTabla();
				resultado=obtenerFilaTabla(columnas, valores);
			}
			if (resultado!=null) {
				return compararRegistroDBFilaTabla(rs, resultado);
			}
			else {
				logger.error("No se ha encontrado el elemento "+valores[0]+" en la tabla");
				return false;
			}
		}
		catch (Exception e) {
			logger.fatal("Error al acceder a la BD");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean buscarRegistroTablaEnBD (ResultSet rs, String elemento1, String elemento2, String elemento3) {
		try {
			String[] columnas={"Num. Vía","Sentido","Tipo de vía"};
			String[] valores=new String[columnas.length];
			valores[0]=elemento1;
			valores[1]=elemento2;
			valores[2]=elemento3;
			String[] resultado=obtenerFilaTabla(columnas, valores);
			return compararRegistroDBFilaTabla(rs, resultado);
		}
		catch (Exception e) {
			logger.fatal("Error al acceder a la BD");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean compararRegistroDBFilaTabla(ResultSet rs, String[] resultado) {
		try {
			String [] row=new String[resultado.length];
			row[0]=rs.getString("lanenum").trim();
			row[1]=rs.getString("lanedir").trim()+": "+rs.getString("desc1").trim();
			row[2]=rs.getString("lanetype").trim()+": "+rs.getString("description").trim();
			row[3]=rs.getString("plazanode").trim();
			row[4]=rs.getString("lanenode").trim();
			return RutinasTestComunes.assertArrayEquals(row,resultado);
		}
		catch (Exception e) {
			logger.fatal("Error al acceder a la BD");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String encabezado1,
			String[] labelsVariables1, String[] valoresVariables1,String encabezado2,
			String[] labelsVariables2, String[] valoresVariables2, String encabezado3,String[] botonesCRUD, String[] columnasgrid, String[] botonesgrid) {
		logger.debug("Gestion Vías: inicio análisis sintáctico");
		boolean resultado=crudVariable1.sintacticAnalysis(encabezado1, labelsVariables1, valoresVariables1, null);
		resultado=crudVariable2.sintacticAnalysis(encabezado2, labelsVariables2, valoresVariables2, null) && resultado;
		return ((FormRadioScreen) screen).sintacticAnalysis(titulo, subtitulo, botones, botonesCRUD,
				encabezado3, null, columnasgrid, botonesgrid) && resultado;
	}

}
