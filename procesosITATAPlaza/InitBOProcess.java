package procesosITATAPlaza;

import org.apache.log4j.Logger;

import pantallasBase.*;
import unidadesGraficas.AlertPopup;
import unidadesGraficas.TipoBy;

public class InitBOProcess {

	private BOMainPage boPage;
	private AlertPopup alert;
	
	private final  int MAXPESTAÑAS=4;
	private final  int MAXOPCIONES=13;
	private final  int MAXSUBOPCIONES=3;
	 
	private TipoBy i_titulo=TipoBy.ID;
	private String str_titulo="ctl00_LblUserName";
	private TipoBy i_subtitulo=TipoBy.ID;  
	private String str_subtitulo="ctl00_LblNodeName";
	private TipoBy i_button=TipoBy.ID;  
	private String str_button="ctl00_BtnLogOut";
	private TipoBy[] i_opcion={TipoBy.LINK,TipoBy.LINK,TipoBy.LINK,TipoBy.LINK};
	private String[] str_opcion={"Configuración sistema","Gestión de cobrador", "General", "Caja fuerte"};
	private TipoBy[][][] i_desplegable;
	private String[][][] str_desplegable;
	private final static Logger logger = Logger.getLogger(InitBOProcess.class);
	
	public InitBOProcess() {
		 logger.debug("InitBOProcess");
		 inicializaciónBOMenu();
		 boPage=new BOMainPage(i_titulo, str_titulo, i_subtitulo, str_subtitulo, i_button,
				 str_button, i_opcion, str_opcion, i_desplegable, str_desplegable);
		 alert=new AlertPopup();
    }
	
	public void seleccionarOpcionMenu(String opcion, String subopcion) {
		boPage.seleccionar_opcion(opcion, subopcion);
	}
	
	public void seleccionarOpcionMenu(String opcion, String subopcion1, String subopcion2) {
		boPage.seleccionar_opcion(opcion, subopcion1, subopcion2);
	}
	
	public void logout() {
		boPage.logout();
	}
	
	public boolean hayAlerta() {
		return alert.hayAlerta();
	}
	
	public String mensajeAlerta() {
		return alert.getText();
	}
	
	public AlertPopup alerta() {
		return alert;
	}
	
	public void aceptarAlerta() {
		alert.aceptar();
	}
	
	public void cancelarAlerta() {
		alert.cancelar();
	}
	
	public boolean sintacticAnalysis(String titulo, String subtitulo, String[] labels, String[][][] desplegables) {
		logger.debug("Página MainBO: inicio análisis sintáctico");
		return boPage.sintacticAnalysis(titulo, subtitulo, labels, desplegables);
	}
	
	private void inicializaciónBOMenu() {
		str_desplegable=new String[MAXPESTAÑAS][MAXOPCIONES][MAXSUBOPCIONES];
		i_desplegable=new TipoBy[MAXPESTAÑAS][MAXOPCIONES][MAXSUBOPCIONES];
		
		for (int i=0; i<MAXPESTAÑAS;i++) {
			for (int j=0; j<MAXOPCIONES; j++) {
				for (int z=0; z<MAXSUBOPCIONES; z++) {
					i_desplegable[i][j][z]=TipoBy.LINK;
				}
			}
		}

		str_desplegable=new String[MAXPESTAÑAS][MAXOPCIONES][MAXSUBOPCIONES];
		str_desplegable[0][0][0]="Operadores";
		str_desplegable[0][0][1]="Gestión de operadores";
		str_desplegable[0][0][2]="Gestión de grupos";
		str_desplegable[1][0][0]="Liquidación parcial";
		str_desplegable[1][1][0]="Liquidación final";
		str_desplegable[1][2][0]="Histórico de liquidaciones";
		str_desplegable[1][3][0]="Creación de Expedición";
		str_desplegable[1][4][0]="Histórico de expediciones";
		str_desplegable[2][0][0]="Revisión de logs";
		str_desplegable[2][1][0]="Reimpresión de informes";
		str_desplegable[2][2][0]="Cambiar contraseña";
		str_desplegable[2][3][0]="Cambiar idioma";
		str_desplegable[2][4][0]="Control de sesiones";
		str_desplegable[3][0][0]="Abrir caja fuerte";
		str_desplegable[3][1][0]="Ingreso de efectivo";
		str_desplegable[3][2][0]="Concilia de caja";
		str_desplegable[3][3][0]="Retiro de caja";
		str_desplegable[3][4][0]="Retirada de fondo de carril";
		str_desplegable[3][5][0]="Devolución de fondo";
		str_desplegable[3][6][0]="Autorización de débito";
		str_desplegable[3][7][0]="Cierre";
		str_desplegable[3][8][0]="Cierre especial";
		str_desplegable[3][9][0]="Exclusión de deuda";
		str_desplegable[3][10][0]="Histórico de caja fuerte";
		str_desplegable[3][11][0]="Informe del estado actual";
		str_desplegable[3][12][0]="Informe de histórico de fondos";
	}
}