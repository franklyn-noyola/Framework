package testCasesITATAHost.Informes.InformesGestion;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.Informes.InformesGestion.ExentosDiariosSInPersonal.ExentosDiariosSinPersonalProcess;
import procesosITATAHost.Informes.InformesGestion.ExentosDiariosSInPersonal.ExentosDiariosSinPersonalReportProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class ExentosDiariosSinPersonalTest {

	final static String titulo="Exentos diarios (sin personal de autopista)"; 
	final static String subtitulo="Informes   -   Informes de gestión   -   Exentos diarios (sin personal de autopista)"; 
	final static String[] botones={"Informe","Volver"};  
	final static String encabezado=null;
	final static String[] labels= {"Año","Concesionaria", "Plaza","Mes","Grupo exento"};
	final static String titulo_report="Informe de exentos diarios (sin personal de autopista)"; 
	final static String[] botones_report={"Ver en Excel"};  
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(ExentosDiariosSinPersonalTest.class);
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
	public void testExentosDiariosSinPersonal1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Exentos Diarios Sin Personal");
		logger.info("TC1 Exentos Diarios Sin Personal: Probar el análisis sintáctico correcto");
		logger.info("TC1 Exentos Diarios Sin Personal: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Exentos diarios (sin personal de autopista)");
		ExentosDiariosSinPersonalProcess proceso=new ExentosDiariosSinPersonalProcess();
		boolean analisis=proceso.sintacticAnalysis(titulo, subtitulo, botones,encabezado, labels);
		if (analisis) {
			logger.info("TC1 Exentos Diarios Sin Personal: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Exentos Diarios Sin Personal: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Exentos Diarios Sin Personal: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testExentosDiariosSinPersonal2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Exentos Diarios Sin Personal");
		logger.info("TC2 Exentos Diarios Sin Personal: Probar el análisis sintáctico correcto del report generado");
		logger.info("TC2 Exentos Diarios Sin Personal: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Exentos diarios (sin personal de autopista)");
		ExentosDiariosSinPersonalProcess proceso=new ExentosDiariosSinPersonalProcess();
		proceso.informe();
		ExentosDiariosSinPersonalReportProcess report=new ExentosDiariosSinPersonalReportProcess();
		report.pasarANuevaVentana();
		boolean analisis=report.sintacticAnalysis(titulo_report, botones_report);
		report.cerrar();
		if (analisis) {
			logger.info("TC2 Exentos Diarios Sin Personal: análisis sintáctico correcto");
		}
		else {
			logger.error("TC2 Exentos Diarios Sin Personal: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC2 Exentos Diarios Sin Personal: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testExentosDiariosSinPersonal3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Exentos Diarios Sin Personal");
		logger.info("TC3 Exentos Diarios Sin Personal: Probar generar el report en Excel");
		logger.info("TC3 Exentos Diarios Sin Personal: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Exentos diarios (sin personal de autopista)");
		ExentosDiariosSinPersonalProcess proceso=new ExentosDiariosSinPersonalProcess();
		proceso.informe();
		ExentosDiariosSinPersonalReportProcess report=new ExentosDiariosSinPersonalReportProcess();
		report.pasarANuevaVentana();
		report.Excel();
		report.cerrar();
		logger.info("TC3 Exentos Diarios Sin Personal: Resultado obtenido=true");
		assertTrue(true);
	}
}
