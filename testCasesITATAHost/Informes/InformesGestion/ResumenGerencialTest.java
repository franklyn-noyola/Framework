package testCasesITATAHost.Informes.InformesGestion;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.Informes.InformesGestion.ResumenGerencial.ResumenGerencialProcess;
import procesosITATAHost.Informes.InformesGestion.ResumenGerencial.ResumenGerencialReportProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class ResumenGerencialTest {

	final static String titulo="Resumen gerencial"; 
	final static String subtitulo="Informes   -   Informes de gestión   -   Resumen gerencial"; 
	final static String[] botones={"Informe","Volver"};  
	final static String encabezado=null;
	final static String[] labels= {"Año","Concesionaria","Mes"};
	final static String titulo_report="Informe resumen gerencial"; 
	final static String[] botones_report={"Ver en Excel"};  
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(ResumenGerencialTest.class);
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
	public void testResumenGerencial1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Resumen Gerencial");
		logger.info("TC1 Resumen Gerencial: Probar el análisis sintáctico correcto");
		logger.info("TC1 Resumen Gerencial: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Resumen gerencial");
		ResumenGerencialProcess proceso=new ResumenGerencialProcess();
		boolean analisis=proceso.sintacticAnalysis(titulo, subtitulo, botones,encabezado, labels);
		if (analisis) {
			logger.info("TC1 Resumen Gerencial: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Resumen Gerencial: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Resumen Gerencial: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testResumenGerencial2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Resumen Gerencial");
		logger.info("TC2 Resumen Gerencial: Probar el análisis sintáctico correcto del report generado");
		logger.info("TC2 Resumen Gerencial: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Resumen gerencial");
		ResumenGerencialProcess proceso=new ResumenGerencialProcess();
		proceso.informe();
		ResumenGerencialReportProcess report=new ResumenGerencialReportProcess();
		report.pasarANuevaVentana();
		boolean analisis=report.sintacticAnalysis(titulo_report, botones_report);
		report.cerrar();
		if (analisis) {
			logger.info("TC2 Resumen Gerencial: análisis sintáctico correcto");
		}
		else {
			logger.error("TC2 Resumen Gerencial: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC2 Resumen Gerencial: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testResumenGerencial3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Resumen Gerencial");
		logger.info("TC3 Resumen Gerencial: Probar generar el report en Excel");
		logger.info("TC3 Resumen Gerencial: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Resumen gerencial");
		ResumenGerencialProcess proceso=new ResumenGerencialProcess();
		proceso.informe();
		ResumenGerencialReportProcess report=new ResumenGerencialReportProcess();
		report.pasarANuevaVentana();
		report.Excel();
		report.cerrar();
		logger.info("TC3 Resumen Gerencial: Resultado obtenido=true");
		assertTrue(true);
	}
}
