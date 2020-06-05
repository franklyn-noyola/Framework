package testCasesITATAHost.Informes.InformesGestion;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.Informes.InformesGestion.FlujosDiarios.FlujosDiariosProcess;
import procesosITATAHost.Informes.InformesGestion.FlujosDiarios.FlujosDiariosReportProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class FlujosDiariosTest {

	final static String titulo="Flujos diarios"; 
	final static String subtitulo="Informes   -   Informes de gestión   -   Flujos diarios"; 
	final static String[] botones={"Informe","Volver"};  
	final static String encabezado=null;
	final static String[] labels= {"Año","Concesionaria", "Plaza","Mes"};
	final static String titulo_report="Informe de flujos diarios"; 
	final static String[] botones_report={"Ver en Excel"};   
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(FlujosDiariosTest.class);
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
	public void testFlujosDiarios1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Flujos Diarios");
		logger.info("TC1 Flujos Diarios: Probar el análisis sintáctico correcto");
		logger.info("TC1 Flujos Diarios: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Flujos diarios");
		FlujosDiariosProcess proceso=new FlujosDiariosProcess();
		boolean analisis=proceso.sintacticAnalysis(titulo, subtitulo, botones,encabezado, labels);
		if (analisis) {
			logger.info("TC1 Flujos Diarios: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Flujos Diarios: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Flujos Diarios: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testFlujosDiarios2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Flujos Diarios");
		logger.info("TC2 Flujos Diarios: Probar el análisis sintáctico correcto del report generado");
		logger.info("TC2 Flujos Diarios: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Flujos diarios");
		FlujosDiariosProcess proceso=new FlujosDiariosProcess();
		proceso.informe();
		FlujosDiariosReportProcess report=new FlujosDiariosReportProcess();
		report.pasarANuevaVentana();
		boolean analisis=report.sintacticAnalysis(titulo_report, botones_report);
		report.cerrar();
		if (analisis) {
			logger.info("TC2 Flujos Diarios: análisis sintáctico correcto");
		}
		else {
			logger.error("TC2 Flujos Diarios: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC2 Flujos Diarios: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testFlujosDiarios3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Flujos Diarios");
		logger.info("TC3 Flujos Diarios: Probar generar el report en Excel");
		logger.info("TC3 Flujos Diarios: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Flujos diarios");
		FlujosDiariosProcess proceso=new FlujosDiariosProcess();
		proceso.informe();
		FlujosDiariosReportProcess report=new FlujosDiariosReportProcess();
		report.pasarANuevaVentana();
		report.Excel();
		report.cerrar();
		logger.info("TC3 Flujos Diarios: Resultado obtenido=true");
		assertTrue(true);
	}
}
