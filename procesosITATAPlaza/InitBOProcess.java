package procesosITATAPlaza;

import org.apache.log4j.Logger;

import pantallasBase.*;
import unidadesGraficas.AlertPopup;
import unidadesGraficas.TipoBy;

public class InitBOProcess {

	private BOMainPage boPage;
	private AlertPopup alert;
	
	private final  int MAXPESTA�AS=4;
	private final  int MAXOPCIONES=13;
	private final  int MAXSUBOPCIONES=3;
	 
	private TipoBy i_titulo=TipoBy.ID;
	private String str_titulo="ctl00_LblUserName";
	private TipoBy i_subtitulo=TipoBy.ID;  
	private String str_subtitulo="ctl00_LblNodeName";
	private TipoBy i_button=TipoBy.ID;  
	private String str_button="ctl00_BtnLogOut";
	private TipoBy[] i_opcion={TipoBy.LINK,TipoBy.LINK,TipoBy.LINK,TipoBy.LINK};
	private String[] str_opcion={"Configuraci�n sistema","Gesti�n de cobrador", "General", "Caja fuerte"};
	private TipoBy[][][] i_desplegable;
	private String[][][] str_desplegable;
	private final static Logger logger = Logger.getLogger(InitBOProcess.class);
	
	public InitBOProcess() {
		 logger.debug("InitBOProcess");
		 inicializaci�nBOMenu();
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
		logger.debug("P�gina MainBO: inicio an�lisis sint�ctico");
		return boPage.sintacticAnalysis(titulo, subtitulo, labels, desplegables);
	}
	
	private void inicializaci�nBOMenu() {
		str_desplegable=new String[MAXPESTA�AS][MAXOPCIONES][MAXSUBOPCIONES];
		i_desplegable=new TipoBy[MAXPESTA�AS][MAXOPCIONES][MAXSUBOPCIONES];
		
		for (int i=0; i<MAXPESTA�AS;i++) {
			for (int j=0; j<MAXOPCIONES; j++) {
				for (int z=0; z<MAXSUBOPCIONES; z++) {
					i_desplegable[i][j][z]=TipoBy.LINK;
				}
			}
		}

		str_desplegable=new String[MAXPESTA�AS][MAXOPCIONES][MAXSUBOPCIONES];
		str_desplegable[0][0][0]="Operadores";
		str_desplegable[0][0][1]="Gesti�n de operadores";
		str_desplegable[0][0][2]="Gesti�n de grupos";
		str_desplegable[1][0][0]="Liquidaci�n parcial";
		str_desplegable[1][1][0]="Liquidaci�n final";
		str_desplegable[1][2][0]="Hist�rico de liquidaciones";
		str_desplegable[1][3][0]="Creaci�n de Expedici�n";
		str_desplegable[1][4][0]="Hist�rico de expediciones";
		str_desplegable[2][0][0]="Revisi�n de logs";
		str_desplegable[2][1][0]="Reimpresi�n de informes";
		str_desplegable[2][2][0]="Cambiar contrase�a";
		str_desplegable[2][3][0]="Cambiar idioma";
		str_desplegable[2][4][0]="Control de sesiones";
		str_desplegable[3][0][0]="Abrir caja fuerte";
		str_desplegable[3][1][0]="Ingreso de efectivo";
		str_desplegable[3][2][0]="Concilia de caja";
		str_desplegable[3][3][0]="Retiro de caja";
		str_desplegable[3][4][0]="Retirada de fondo de carril";
		str_desplegable[3][5][0]="Devoluci�n de fondo";
		str_desplegable[3][6][0]="Autorizaci�n de d�bito";
		str_desplegable[3][7][0]="Cierre";
		str_desplegable[3][8][0]="Cierre especial";
		str_desplegable[3][9][0]="Exclusi�n de deuda";
		str_desplegable[3][10][0]="Hist�rico de caja fuerte";
		str_desplegable[3][11][0]="Informe del estado actual";
		str_desplegable[3][12][0]="Informe de hist�rico de fondos";
	}
}