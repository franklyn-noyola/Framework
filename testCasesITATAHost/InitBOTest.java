package testCasesITATAHost;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosComunes.LoginProcess;
import procesosITATAHost.InitBOProcess;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.GestionOperadoresProcess;
import unidadesGraficas.AlertPopup;
import unidadesGraficas.ErrorMessage;

public class InitBOTest {

	 final static int MAXPESTA�AS=8;
	 final static int MAXOPCIONES=15;
	 final static int MAXSUBOPCIONES=12;
	 final static String[] opciones_menu={"Configuraci�n sistema","Gesti�n de cuentas","Gesti�n de cobrador", "Transacciones",
			 "Gesti�n de turno","M�dulo financiero", "General", "Informes"};
	 final static String alerta_salida="�Est� seguro de que desea salir? Se pueden perder cambios no guardados";
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
		opciones_desplegable[0][1][0]="Configuraci�n de peaje";
		opciones_desplegable[0][1][1]="Nodos";
		opciones_desplegable[0][1][2]="Concesionarias, Plazas, V�as";
		opciones_desplegable[0][1][3]="Par�metros de peaje";
		opciones_desplegable[0][1][4]="Motivos";
		opciones_desplegable[0][2][0]="Gesti�n de clases";
		opciones_desplegable[0][2][1]="Categor�as";
		opciones_desplegable[0][2][2]="Mapeo DAC-Cat";
		opciones_desplegable[0][3][0]="Tarifas & Moneda";
		opciones_desplegable[0][3][1]="Moneda aceptada";
		opciones_desplegable[0][3][2]="Calendario";
		opciones_desplegable[0][3][3]="Gesti�n de tarifas";
		opciones_desplegable[0][4][0]="Par�metros de cuenta";
		opciones_desplegable[0][4][1]="Recargos";
		opciones_desplegable[0][5][0]="Telecargas";
		opciones_desplegable[1][0][0]="Crear cuenta";
		opciones_desplegable[1][0][1]="Est�ndar";
		opciones_desplegable[1][0][2]="Comercial";
		opciones_desplegable[1][0][3]="Exenta";
		opciones_desplegable[1][1][0]="Seleccionar cuenta";
		opciones_desplegable[1][1][1]="Informe de beneficios";
		opciones_desplegable[1][1][2]="Informe de movimientos";
		opciones_desplegable[1][1][3]="Inventario de tags/Tarjetas";
		opciones_desplegable[2][0][0]="Hist�rico de liquidaciones";
		opciones_desplegable[2][1][0]="Hist�rico de expediciones";
		opciones_desplegable[3][0][0]="Consolidaci�n de informaci�n";
		opciones_desplegable[3][1][0]="Revisi�n de Transacciones";
		opciones_desplegable[3][2][0]="Revisi�n Ciclos de transacciones";
		opciones_desplegable[3][3][0]="Consolidaci�n de revisiones";
		opciones_desplegable[3][4][0]="Ver transacciones";
		opciones_desplegable[3][5][0]="Recaudaci�n financiera";
		opciones_desplegable[3][6][0]="Volumen de tr�fico";
		opciones_desplegable[3][7][0]="Informe de pagos con TSC";		
		opciones_desplegable[3][8][0]="Informe de tr�nsitos con TSC";
		opciones_desplegable[3][9][0]="Informe de tr�fico diario";
		opciones_desplegable[3][10][0]="Tiempo de atenci�n";
		opciones_desplegable[3][11][0]="Informe de Discrepancias";
		opciones_desplegable[3][12][0]="Informe de Exentos";
		opciones_desplegable[3][13][0]="Tr�ficos con TSC";
		opciones_desplegable[3][14][0]="Transacciones manuales";
		opciones_desplegable[4][0][0]="Gesti�n de turno";
		opciones_desplegable[4][1][0]="Cierre de d�a";
		opciones_desplegable[4][2][0]="Faltas de cobradores";
		opciones_desplegable[4][3][0]="Cierre de deudas de cobrador";
		opciones_desplegable[4][4][0]="Informe de recaudaci�n";
		opciones_desplegable[4][5][0]="Entrada recuento de bolsas";
		opciones_desplegable[4][6][0]="Informe de ingreso de cobrador";
		opciones_desplegable[5][0][0]="Matriz financiera";
		opciones_desplegable[5][1][0]="Incumplimiento de caja";
		opciones_desplegable[5][2][0]="Sobrante de caja";
		opciones_desplegable[5][3][0]="Tr�fico por categor�a";
		opciones_desplegable[5][4][0]="Tr�fico por hora";
		opciones_desplegable[5][5][0]="Anomal�as detectadas";
		opciones_desplegable[5][6][0]="Agudeza del cobrador";
		opciones_desplegable[5][7][0]="Fiabilidad DAC";
		opciones_desplegable[5][8][0]="Volumen exentos por cobrador";
		opciones_desplegable[5][9][0]="Valor exentos por cobrador";
		opciones_desplegable[5][10][0]="P�rdidas por exentos";
		opciones_desplegable[5][11][0]="Tiempo de apertura del carril";
		opciones_desplegable[5][12][0]="Comparativa de tr�fico";
		opciones_desplegable[5][13][0]="Informe mensual MOP (por categor�a)";
		opciones_desplegable[6][0][0]="Revisi�n de logs";
		opciones_desplegable[6][1][0]="Reimpresi�n de informes";
		opciones_desplegable[6][2][0]="Avisos";
		opciones_desplegable[6][3][0]="Cuadro sin�ptico";
		opciones_desplegable[6][4][0]="Cambiar contrase�a";
		opciones_desplegable[6][5][0]="Cambiar idioma";
		opciones_desplegable[6][6][0]="Control de sesiones";
		opciones_desplegable[6][7][0]="Desbloqueo manual";
		opciones_desplegable[7][0][0]="Informes MOP";
		opciones_desplegable[7][0][1]="Car�tula MOP";
		opciones_desplegable[7][0][2]="Tr�nsitos de tarjetas de proximidad";
		opciones_desplegable[7][0][3]="Valorizado de tarjetas de proximidad";
		opciones_desplegable[7][0][4]="Flujo de vecinos";
		opciones_desplegable[7][0][5]="Valorizado de vecinos";
		opciones_desplegable[7][0][6]="Car�tula MOP (respaldo factura)";
		opciones_desplegable[7][1][0]="Informes de gesti�n";
		opciones_desplegable[7][1][1]="Resumen gerencial";
		opciones_desplegable[7][1][2]="Venta de abonos de clientes prepago";
		opciones_desplegable[7][1][3]="Resumen de faltantes";
		opciones_desplegable[7][1][4]="Faltantes por turno";
		opciones_desplegable[7][1][5]="Sobrantes por d�a";
		opciones_desplegable[7][1][6]="Faltantes por d�a";
		opciones_desplegable[7][1][7]="Exentos diarios";
		opciones_desplegable[7][1][8]="Exentos diarios (sin personal de autopista)";
		opciones_desplegable[7][1][9]="Flujos diarios";
		opciones_desplegable[7][1][10]="Flujo de tarjetas de proximidad";
		opciones_desplegable[7][1][11]="Conciliaci�n dep�sitos transporte de valores";
		opciones_desplegable[7][2][0]="Informes adicionales";
		opciones_desplegable[7][2][1]="Informe mensual MOP (por exenci�n)";
		opciones_desplegable[7][2][2]="Cierres de turno de cobradores";
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
		String grupoop=gestionOperadores.obtenerValorColumna("Grupo Op.", "Operador", valoresVariables[0]);
		String nombre=gestionOperadores.obtenerValorColumna("Nombre", "Operador", valoresVariables[0]);
		String apellidos=gestionOperadores.obtenerValorColumna("Apellidos", "Operador", valoresVariables[0]);
		grupoop=grupoop.substring(3, grupoop.length());
		gestionOperadores.volver();
		String titulo_bo=valoresVariables[0]+" - "+nombre+" "+apellidos+" ["+grupoop+"]";
		String subtitulo_bo=valoresVariables[1];
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
		logger.info("TC2 InitBO: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

}
