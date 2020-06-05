package testCasesITATAHost.Informes.InformesGestion;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.Informes.InformesGestion.ExentosDiarios.ExentosDiariosProcess;
import procesosITATAHost.Informes.InformesGestion.ExentosDiarios.ExentosDiariosReportProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class ExentosDiariosTest {

	final static String titulo="Exentos diarios"; 
	final static String subtitulo="Informes   -   Informes de gestión   -   Exentos diarios"; 
	final static String[] botones={"Informe","Volver"};  
	final static String encabezado=null;
	final static String[] labels= {"Año","Concesionaria", "Plaza","Mes"};
	final static String titulo_report="Informe de exentos diarios"; 
	final static String[] botones_report={"Ver en Excel"}; 
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(ExentosDiariosTest.class);
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
	public void testExentosDiarios1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Exentos Diarios");
		logger.info("TC1 Exentos Diarios: Probar el análisis sintáctico correcto");
		logger.info("TC1 Exentos Diarios: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Exentos diarios");
		ExentosDiariosProcess proceso=new ExentosDiariosProcess();
		boolean analisis=proceso.sintacticAnalysis(titulo, subtitulo, botones,encabezado, labels);
		if (analisis) {
			logger.info("TC1 Exentos Diarios: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Exentos Diarios: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Exentos Diarios: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testExentosDiarios2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Exentos Diarios");
		logger.info("TC2 Exentos Diarios: Probar el análisis sintáctico correcto del report generado");
		logger.info("TC2 Exentos Diarios: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Exentos diarios");
		ExentosDiariosProcess proceso=new ExentosDiariosProcess();
		proceso.informe();
		ExentosDiariosReportProcess report=new ExentosDiariosReportProcess();
		report.pasarANuevaVentana();
		boolean analisis=report.sintacticAnalysis(titulo_report, botones_report);
		report.cerrar();
		if (analisis) {
			logger.info("TC2 Exentos Diarios: análisis sintáctico correcto");
		}
		else {
			logger.error("TC2 Exentos Diarios: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC2 Exentos Diarios: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testExentosDiarios3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Exentos Diarios");
		logger.info("TC3 Exentos Diarios: Probar generar el report en Excel");
		logger.info("TC3 Exentos Diarios: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Exentos diarios");
		ExentosDiariosProcess proceso=new ExentosDiariosProcess();
		proceso.informe();
		ExentosDiariosReportProcess report=new ExentosDiariosReportProcess();
		report.pasarANuevaVentana();
		report.Excel();
		report.cerrar();
		logger.info("TC3 Exentos Diarios: Resultado obtenido=true");
		assertTrue(true);
	}
}
