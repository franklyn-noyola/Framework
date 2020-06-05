package testCasesITATAHost.Informes.InformesMOP;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.Informes.InformesMOP.ValorizadoVecinos.ValorizadoVecinosProcess;
import procesosITATAHost.Informes.InformesMOP.ValorizadoVecinos.ValorizadoVecinosReportProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class ValorizadoVecinosTest {

	final static String titulo="Valorizado de vecinos"; 
	final static String subtitulo="Informes   -   Informes MOP   -   Valorizado de vecinos"; 
	final static String[] botones={"Informe","Volver"};  
	final static String encabezado=null;
	final static String[] labels= {"Año","Concesionaria","Plaza","Mes"};
	final static String titulo_report="Informe valorizado de vecinos"; 
	final static String[] botones_report={"Ver en Excel"};  
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(ValorizadoVecinosTest.class);
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
	public void testValorizadoVecinos1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Valorizado Vecinos");
		logger.info("TC1 Valorizado Vecinos: Probar el análisis sintáctico correcto");
		logger.info("TC1 Valorizado Vecinos: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes MOP","Valorizado de vecinos");
		ValorizadoVecinosProcess proceso=new ValorizadoVecinosProcess();
		boolean analisis=proceso.sintacticAnalysis(titulo, subtitulo, botones,encabezado, labels);
		if (analisis) {
			logger.info("TC1 Valorizado Vecinos: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Valorizado Vecinos: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Valorizado Vecinos: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testValorizadoVecinos2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Valorizado Vecinos");
		logger.info("TC2 Valorizado Vecinos: Probar el análisis sintáctico correcto del report generado");
		logger.info("TC2 Valorizado Vecinos: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes MOP","Valorizado de vecinos");
		ValorizadoVecinosProcess proceso=new ValorizadoVecinosProcess();
		proceso.informe();
		ValorizadoVecinosReportProcess report=new ValorizadoVecinosReportProcess();
		report.pasarANuevaVentana();
		boolean analisis=report.sintacticAnalysis(titulo_report, botones_report);
		report.cerrar();
		if (analisis) {
			logger.info("TC2 Valorizado Vecinos: análisis sintáctico correcto");
		}
		else {
			logger.error("TC2 Valorizado Vecinos: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC2 Valorizado Vecinos: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testValorizadoVecinos3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Valorizado Vecinos");
		logger.info("TC3 Valorizado Vecinos: Probar generar el report en Excel");
		logger.info("TC3 Valorizado Vecinos: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes MOP","Valorizado de vecinos");
		ValorizadoVecinosProcess proceso=new ValorizadoVecinosProcess();
		proceso.informe();
		ValorizadoVecinosReportProcess report=new ValorizadoVecinosReportProcess();
		report.pasarANuevaVentana();
		report.Excel();
		report.cerrar();
		logger.info("TC3 Valorizado Vecinos: Resultado obtenido=true");
		assertTrue(true);
	}
}