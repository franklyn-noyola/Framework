package testCasesITATAHost.Informes.InformesMOP;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.Informes.InformesMOP.CaratulaMOP.CaratulaMOPReportProcess;
import procesosITATAHost.Informes.InformesMOP.FlujoVecinos.FlujoVecinosProcess;
import procesosITATAHost.Informes.InformesMOP.FlujoVecinos.FlujoVecinosReportProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class FlujoVecinosTest {

	final static String titulo="Flujo de vecinos"; 
	final static String subtitulo="Informes   -   Informes MOP   -   Flujo de vecinos"; 
	final static String[] botones={"Informe","Volver"};  
	final static String encabezado=null;
	final static String[] labels= {"Año","Concesionaria","Plaza","Mes"};
	final static String titulo_report="Informe de flujo de vecinos"; 
	final static String[] botones_report={"Ver en Excel"};   
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(FlujoVecinosTest.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		InitBOTest.tearDownAfterClass();
	}
	
	@After
	public void AfterTest() throws Exception {
		logger.info("Fin TC");
		logger.info("-----------------------------------------------------------------");
	}
	
	@Test
	public void testFlujoVecinos1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Flujo Vecinos");
		logger.info("TC1 Flujo Vecinos: Probar el análisis sintáctico correcto");
		logger.info("TC1 Flujo Vecinos: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes MOP","Flujo de vecinos");
		FlujoVecinosProcess flujoVecinos=new FlujoVecinosProcess();
		boolean analisis=flujoVecinos.sintacticAnalysis(titulo, subtitulo, botones,encabezado, labels);
		if (analisis) {
			logger.info("TC1 Flujo Vecinos: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Flujo Vecinos: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Flujo Vecinos: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testFlujoVecinos2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Flujo Vecinos");
		logger.info("TC2 Flujo Vecinos: Probar el análisis sintáctico correcto del report generado");
		logger.info("TC2 Flujo Vecinos: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes MOP","Flujo de vecinos");
		FlujoVecinosProcess flujoVecinos=new FlujoVecinosProcess();
		flujoVecinos.informe();
		CaratulaMOPReportProcess report=new CaratulaMOPReportProcess();
		report.pasarANuevaVentana();
		boolean analisis=report.sintacticAnalysis(titulo_report, botones_report);
		report.cerrar();
		if (analisis) {
			logger.info("TC2 Flujo Vecinos: análisis sintáctico correcto");
		}
		else {
			logger.error("TC2 Flujo Vecinos: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC2 Flujo Vecinos: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testFlujoVecinos3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Flujo Vecinos");
		logger.info("TC3 Flujo Vecinos: Probar generar el report en Excel");
		logger.info("TC3 Flujo Vecinos: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes MOP","Flujo de vecinos");
		FlujoVecinosProcess flujoVecinos=new FlujoVecinosProcess();
		flujoVecinos.informe();
		FlujoVecinosReportProcess report=new FlujoVecinosReportProcess();
		report.pasarANuevaVentana();
		report.Excel();
		report.cerrar();
		logger.info("TC3 Flujo Vecinos: Resultado obtenido=true");
		assertTrue(true);
	}
}
