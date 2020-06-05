package procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias;

import java.sql.ResultSet;

import org.apache.log4j.Logger;

import bloques.CrudBlockVariable;
import unidadesGraficas.AlertPopup;
import unidadesGraficas.TipoBy;
import ventanasComunes.FormRadioScreen;
import procesosComunes.FormProcess;
import testsComunes.RutinasTestComunes;

public class GestionPlazaProcess extends FormProcess {
	
	private AlertPopup alert;
	private CrudBlockVariable crudVariable;
	
	private TipoBy[] i_botones= {TipoBy.ID};
	private String[] str_botones= {"ctl00_ButtonsZone_BtnBack_IB_Label"};
	
	private final TipoBy i_encabezadovar=TipoBy.ID;
	private final String str_encabezadovar="ctl00_ContentZone_LblSubtitleCompany_Lbl_SubtitleInfo";
	private final int num_criterios_var=2;
	private final TipoBy[] i_labelvar={TipoBy.ID,TipoBy.ID};
	private final String[] str_labelvar={"ctl00_ContentZone_txt_Code_lbl_Description","ctl00_ContentZone_txt_Name_lbl_Description"};
	private final TipoBy[] i_fieldvar={TipoBy.ID,TipoBy.ID};
	private final String[] str_fieldvar={"ctl00_ContentZone_txt_Code_box_data","ctl00_ContentZone_txt_Name_box_data"};
	
	private final TipoBy i_encabezado=TipoBy.ID;
	private final String str_encabezado="ctl00_ContentZone_LblSubtitlePlazas_Lbl_SubtitleInfo";
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
	private final TipoBy[] i_botonescrud={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private final String[] str_botonescrud= {"ctl00_ContentZone_BtnCreate","ctl00_ContentZone_BtnModify","ctl00_ContentZone_BtnLanes",
					"ctl00_ContentZone_BtnDelete"};
	private final TipoBy[] i_botonesgrid={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private final String[] str_botonesgrid={"ctl00_ContentZone_tablePager_BtnFirst","ctl00_ContentZone_tablePager_BtnPrev","ctl00_ContentZone_tablePager_BtnNext","ctl00_ContentZone_tablePager_BtnLast"};

	private final static Logger logger = Logger.getLogger(GestionPlazaProcess.class);
	 
	public GestionPlazaProcess() {
		try {
			screen=new FormRadioScreen(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones,
				str_botones, i_msgerror,str_msgerror,  i_encabezado,  str_encabezado,
				 num_criterios_campo, num_criterios_desplegable, num_criterios_fecha,
				 i_label,  str_label,  i_field,  str_field,  i_tooltipcampo,  str_tooltipcampo,i_tooltipfecha,  
				 str_tooltipfecha, i_botonescrud,  str_botonescrud,  i_cabecera,  str_cabecera, 
				 i_leyendamostrado,  str_leyendamostrado,  i_botonesgrid,
				 str_botonesgrid,  num_columnas);
			crudVariable=new CrudBlockVariable(i_encabezadovar,str_encabezadovar,num_criterios_var,i_labelvar,
						str_labelvar,i_fieldvar,str_fieldvar,0,0,0,null,null,null,null,null,null,null,null);
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
		((FormRadioScreen) screen).clickBotonCrud(BotonesCrudGestionPlaza.Crear.ordinal());
	}
	
	public void modificar() {
		((FormRadioScreen) screen).clickBotonCrud(BotonesCrudGestionPlaza.Modificar.ordinal());
	}
	
	public void eliminar() {
		((FormRadioScreen) screen).clickBotonCrud(BotonesCrudGestionPlaza.Eliminar.ordinal());
	}
	
	public void editarVias() {
		((FormRadioScreen) screen).clickBotonCrud(BotonesCrudGestionPlaza.EditarVias.ordinal());
	}
	
	public void volver() {
		screen.clickBoton(BotonesGestionPlaza.Volver.ordinal());
	}
	
	public boolean buscarRegistroBDEnTabla (ResultSet rs) {
		String[] columnas={"Código"};
		String[] valores=new String[columnas.length];
		try {
			valores[0]=rs.getString("plaza").trim();
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
	
	public boolean buscarRegistroTablaEnBD (ResultSet rs, String elemento) {
		try {
			String[] resultado=obtenerFilaTabla("Código", elemento);
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
			row[0]=rs.getString("plaza").trim();
			row[1]=rs.getString("name").trim();
			row[2]=rs.getString("plazanode").trim();
			row[3]=rs.getString("road");
			if (row[3]!=null) {
				row[3]=row[3].trim();
			}
			else {
				row[3]=" ";
			}
			row[4]=rs.getString("km");
			if (row[4]!=null) {
				row[4]=row[4].trim();
			}
			else {
				row[4]=" ";
			}
			return RutinasTestComunes.assertArrayEquals(row,resultado);
		}
		catch (Exception e) {
			logger.fatal("Error al acceder a la BD");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String encabezado1,
			String[] labelsVariables, String[] valoresVariables,String encabezado2,String[] botonesCRUD, String[] columnasgrid, String[] botonesgrid) {
		logger.debug("Gestion Plaza: inicio análisis sintáctico");
		boolean resultado=crudVariable.sintacticAnalysis(encabezado1, labelsVariables, valoresVariables, null);
		return ((FormRadioScreen) screen).sintacticAnalysis(titulo, subtitulo, botones, botonesCRUD,
				encabezado2, null, columnasgrid, botonesgrid) && resultado;
	}

}
