package procesosITATAPlaza.Operadores.GestionOperadores;

import java.sql.ResultSet;

import org.apache.log4j.Logger;
import procesosComunes.Error;
import unidadesGraficas.AlertPopup;
import unidadesGraficas.TipoBy;
import ventanasComunes.FormRadioScreen;
import ventanasComunes.FormScreen;
import ventanasITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.GestionOperadoresScreen;
import procesosComunes.FormProcess;
import testsComunes.RutinasTestComunes;

public class GestionOperadoresProcess extends FormProcess {
	
	private AlertPopup alert;
	
	private final int num_columnas=7;
	private final TipoBy[] i_botones= {TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private final String[] str_botones= {"ctl00_ButtonsZone_BtnReport_IB_Label","ctl00_ButtonsZone_BtnSearch_IB_Label","ctl00_ButtonsZone_BtnBack_IB_Label"};
	private final TipoBy i_encabezado=null;
	private final String str_encabezado=null;
	private final int num_criterios_campo=3;
	private final int num_criterios_desplegable=2;
	private final int num_criterios_fecha=0;
	private final TipoBy[] i_label={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private final String[] str_label={"ctl00_ContentZone_txt_operator_lbl_Description","ctl00_ContentZone_txt_name_lbl_Description","ctl00_ContentZone_txt_pan_txt_token_lbl_Description",
			"ctl00_ContentZone_cmb_group_lbl_Description","ctl00_ContentZone_cmb_status_lbl_Description"};
	private final TipoBy[] i_field={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private final String[] str_field={"ctl00_ContentZone_txt_operator_box_data","ctl00_ContentZone_txt_name_box_data","ctl00_ContentZone_txt_pan_txt_token_box_data",
			"ctl00_ContentZone_cmb_group_cmb_dropdown","ctl00_ContentZone_cmb_status_cmb_dropdown"};
	private final TipoBy[][] i_tooltipcampo=null;
	private final String[][] str_tooltipcampo=null;
	private final TipoBy[][][] i_tooltipfecha=null;
	private final String[][][] str_tooltipfecha=null;
	
	private final TipoBy i_leyendamostrado=TipoBy.ID;
	private final String str_leyendamostrado="ctl00_ContentZone_tablePager_LblCounter"; // Es la misma de siempre
	private final TipoBy i_cabecera=TipoBy.XPATH; // Es la misma de siempre
	private final String str_cabecera="//*[@id='ctl00_ContentZone_TblResults']/tbody/"; // Es la misma de siempre
	private final TipoBy[] i_botonescrud=null;
	private final String[] str_botonescrud=null;
	private final TipoBy[] i_botonesgrid={TipoBy.ID,TipoBy.ID,TipoBy.ID,TipoBy.ID};
	private final String[] str_botonesgrid={"ctl00_ContentZone_tablePager_BtnFirst","ctl00_ContentZone_tablePager_BtnPrev","ctl00_ContentZone_tablePager_BtnNext","ctl00_ContentZone_tablePager_BtnLast"};

	private final static Logger logger = Logger.getLogger(GestionOperadoresProcess.class);
	 
	public GestionOperadoresProcess() {
		try {
		screen=new GestionOperadoresScreen(i_titulo,str_titulo,i_subtitulo,str_subtitulo, i_botones,
				str_botones, i_msgerror,str_msgerror,  i_encabezado,  str_encabezado,
				 num_criterios_campo, num_criterios_desplegable, num_criterios_fecha,
				 i_label,  str_label,  i_field,  str_field,  i_tooltipcampo,  str_tooltipcampo, i_tooltipfecha,
				 str_tooltipfecha, i_botonescrud,  str_botonescrud,  i_cabecera,  str_cabecera, 
				 i_leyendamostrado,  str_leyendamostrado,  i_botonesgrid,
				 str_botonesgrid,  num_columnas,1);
		}
		catch (RuntimeException e) {
			System.exit(0);
		}
		alert=new AlertPopup();
	}
	
	public Error seleccionarOpcionGrupoOperadores(String opcion) {
		return ((FormRadioScreen) screen).desplegarOpcion(OpcionesGestionOperadoresDesplegable.GrupoOp.ordinal(),opcion);
	}
	
	public Error seleccionarOpcionEstado(String opcion) {
		return ((FormRadioScreen) screen).desplegarOpcion(OpcionesGestionOperadoresDesplegable.Estado.ordinal(),opcion);
	}
	
	public void escribirOpcionOperador(String valor) {
		((FormRadioScreen) screen).escribirOpcion(OpcionesGestionOperadoresCampo.Operador.ordinal(), valor);
	}
	
	public void escribirOpcionNombre(String valor) {
		((FormRadioScreen) screen).escribirOpcion(OpcionesGestionOperadoresCampo.Nombre.ordinal(), valor);
	}
	
	public void escribirOpcionPAN(String valor) {
		((FormRadioScreen) screen).escribirOpcion(OpcionesGestionOperadoresCampo.PAN.ordinal(), valor);
	}
	
	public void borrarOpcionOperador() {
		((FormRadioScreen) screen).borrarOpcion(OpcionesGestionOperadoresCampo.Operador.ordinal());
	}
	
	public void borrarOpcionNombre() {
		((FormRadioScreen) screen).borrarOpcion(OpcionesGestionOperadoresCampo.Nombre.ordinal());
	}
	
	public void borrarOpcionPAN() {
		((FormRadioScreen) screen).borrarOpcion(OpcionesGestionOperadoresCampo.PAN.ordinal());
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
	
	public void informe() {
		screen.clickBoton(BotonesGestionOperadores.Informe.ordinal());
	}
	
	public void busqueda() {
		((GestionOperadoresScreen) screen).clickBotonBusqueda(BotonesGestionOperadores.Busqueda.ordinal());
	}
	
	public void volver() {
		screen.clickBoton(BotonesGestionOperadores.Volver.ordinal());
	}
	
	public boolean buscarRegistroBDEnTabla (ResultSet rs) {
		String[] columnas= {"Operador"};
		String[] valores=new String[1];
		try {
			valores[0]=rs.getString("operator").trim();
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
				logger.error("No se ha encontrado el operador "+valores[0]+" en la tabla");
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
			String[] columnas={"Operador"};
			String[] valores=new String[1];
			valores[0]=elemento;
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
			row[0]=rs.getString("operator").trim();
			if (rs.getString("title")!=null) {
				switch (rs.getString("title").trim())  {
					case "1": row[1]="Sr"; break;
					case "2": row[1]="Sra"; break;
					default: break;
				}
			}
			else row[1]=" ";
			row[2]=rs.getString("forename").trim();
			row[3]=rs.getString("surname").trim();
			if (rs.getString("pan")!=null) {
				row[4]=rs.getString("pan").trim();
			}
			else row[4]=" ";
			switch (rs.getString("status").trim())  {
				case "A": row[5]="Activo"; break;
				case "S": row[5]="Suspendido"; break;
				default:  row[5]=resultado[5]; break;
			}
			row[6]=rs.getString("opgroup")+"-"+rs.getString("description");
			return RutinasTestComunes.assertArrayEquals(row,resultado);
		}
		catch (Exception e) {
			logger.fatal("Error al acceder a la BD");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] botones, String[] botonesCRUD,
			String[] labelCriteriosBusqueda, String[] columnasgrid, String[] botonesgrid) {
		logger.debug("Gestion operadores: inicio análisis sintáctico");
		return ((FormScreen) screen).sintacticAnalysis(titulo, subtitulo, botones, botonesCRUD,
				null, labelCriteriosBusqueda, columnasgrid, botonesgrid);
	}

}
