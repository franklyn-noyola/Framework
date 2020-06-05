package testCasesITATAPlaza;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosComunes.LoginProcess;
import procesosITATAPlaza.InitBOProcess;
import procesosITATAPlaza.Operadores.GestionOperadores.GestionOperadoresProcess;
import testCasesITATAPlaza.LoginTest;
import unidadesGraficas.AlertPopup;
import unidadesGraficas.ErrorMessage;

public class InitBOTest {

	 final static int MAXPESTA�AS=4;
	 final static int MAXOPCIONES=13;
	 final static int MAXSUBOPCIONES=3;
	 final static String[] opciones_menu={"Configuraci�n sistema","Gesti�n de cobrador", "General", "Caja fuerte"};
	 final static String alerta_salida="�Est� seguro de que quiere salir? Se pueden perder cambios no guardados";
	 final static String mssError_login_cerrado="Sesi�n cerrada";
	 
	 static String[][][] opciones_desplegable;
	 static LoginTest logintest;
	 static Connection DBconnection;
	 public InitBOProcess bo;
	 private static String[] valoresVariables=new String[2];
	 private static Logger logger;
		
	 static void inicializaci�nMenu() {
		opciones_desplegable=new String[MAXPESTA�AS][MAXOPCIONES][MAXSUBOPCIONES];
		opciones_desplegable[0][0][0]="Operadores";
		opciones_desplegable[0][0][1]="Gesti�n de operadores";
		opciones_desplegable[0][0][2]="Gesti�n de grupos";
		opciones_desplegable[1][0][0]="Liquidaci�n parcial";
		opciones_desplegable[1][1][0]="Liquidaci�n final";
		opciones_desplegable[1][2][0]="Hist�rico de liquidaciones";
		opciones_desplegable[1][3][0]="Creaci�n de Expedici�n";
		opciones_desplegable[1][4][0]="Hist�rico de expediciones";
		opciones_desplegable[2][0][0]="Revisi�n de logs";
		opciones_desplegable[2][1][0]="Reimpresi�n de informes";
		opciones_desplegable[2][2][0]="Cambiar contrase�a";
		opciones_desplegable[2][3][0]="Cambiar idioma";
		opciones_desplegable[2][4][0]="Control de sesiones";
		opciones_desplegable[3][0][0]="Abrir caja fuerte";
		opciones_desplegable[3][1][0]="Ingreso de efectivo";
		opciones_desplegable[3][2][0]="Concilia de caja";
		opciones_desplegable[3][3][0]="Retiro de caja";
		opciones_desplegable[3][4][0]="Retirada de fondo de carril";
		opciones_desplegable[3][5][0]="Devoluci�n de fondo";
		opciones_desplegable[3][6][0]="Autorizaci�n de d�bito";
		opciones_desplegable[3][7][0]="Cierre";
		opciones_desplegable[3][8][0]="Cierre especial";
		opciones_desplegable[3][9][0]="Exclusi�n de deuda";
		opciones_desplegable[3][10][0]="Hist�rico de caja fuerte";
		opciones_desplegable[3][11][0]="Informe del estado actual";
		opciones_desplegable[3][12][0]="Informe de hist�rico de fondos";

	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logintest=new LoginTest();
		LoginTest.setUpBeforeClass();
		logger=Logger.getLogger(InitBOTest.class);
		logger.debug("setUpBeforeClass-InitBOtest");
	}
		
	@Before
	public void setUpBefore() throws Exception {
		logintest.testLoginBasico();
		valoresVariables=LoginTest.valoresVariables;
		logger.debug("setUpBefore-InitBOtest");
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		logger.debug("tearDownAfterClass-InitBOtest");
		LoginTest.tearDownAfterClass();
	}
	

	@After
	public void AfterTest() throws Exception {
		logger.info("Fin TC");
		logger.info("-----------------------------------------------------------------");
	}
	
	public void testInitBO() {
		bo=new InitBOProcess();
		inicializaci�nMenu();
	}
	
	@Test
	public void testInitBO1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 InitBO");
		logger.info("TC1 InitBO: Probar entrar en InitBO y salir");
		logger.info("TC1 InitBO: Resultado esperado=true (operaci�n correcta)");
		bo=new InitBOProcess();
		inicializaci�nMenu();
		bo.logout();
		boolean analisis=false;
		boolean analisis2=false;
		boolean hayAlerta=bo.hayAlerta();
		boolean haymsgError=false;
		if (!hayAlerta) {
			logger.error("TC1 InitBO: No ha aparecido la alerta de salida del BO");
			LoginTest.minors++;
		}
		else {
			AlertPopup alertaObt=bo.alerta();
			analisis=alertaObt.sintacticAnalysis(alerta_salida);
			if (!analisis) {
				LoginTest.minors++;
			}
			bo.aceptarAlerta();
			LoginProcess login=new LoginProcess();
			haymsgError=login.hayMensajeError();
			if (haymsgError) {
				ErrorMessage errormsgobt=login.mensajeError();
				analisis2=errormsgobt.sintacticAnalysis(mssError_login_cerrado);
				if (!analisis2) {
					logger.error("TC1 InitBO: El mensaje de error mostrado en la pantalla de Login no es correcto:");
					logger.error("TC1 InitBO: Esperado: '"+mssError_login_cerrado+"'");
					logger.error("TC1 InitBO: Obtenido: '"+errormsgobt.getText()+"'");
					LoginTest.minors++;
				}
			}
			else {
				logger.error("No ha aparecido mensaje de Sesi�n cerrada");
				LoginTest.minors++;
			}
		}
		logger.info("TC2 InitBO: Resultado obtenido="+(analisis && analisis2 && haymsgError && hayAlerta));
	}
	
	@Test
	public void testInitBO2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 InitBO");
		logger.info("TC2 InitBO: Probar el an�lisis sint�ctico correcto");
		logger.info("TC2 InitBO: Resultado esperado=true (operaci�n correcta)");
		bo=new InitBOProcess();
		inicializaci�nMenu();
		bo.seleccionarOpcionMenu("Configuraci�n sistema","Operadores","Gesti�n de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		String nombre=gestionOperadores.obtenerValorColumna("Nombre", "Operador", valoresVariables[0]);
		String apellidos=gestionOperadores.obtenerValorColumna("Apellidos", "Operador", valoresVariables[0]);
		String grupoop=gestionOperadores.obtenerValorColumna("Grupo Op.", "Operador", valoresVariables[0]);
		grupoop=grupoop.substring(3, grupoop.length());
		String titulo_bo=valoresVariables[0]+" - "+nombre+" "+apellidos+" ["+grupoop+"]";
		String subtitulo_bo=valoresVariables[1];
		gestionOperadores.volver();
		boolean analisis=bo.sintacticAnalysis(titulo_bo, subtitulo_bo, opciones_menu, opciones_desplegable);
		if (analisis) {
			logger.info("TC2 InitBO: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC2 InitBO: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		bo.logout();
		boolean hayAlerta=bo.hayAlerta();
		if (hayAlerta) {
			bo.aceptarAlerta();
		}
		assertTrue(analisis);
	}
}
