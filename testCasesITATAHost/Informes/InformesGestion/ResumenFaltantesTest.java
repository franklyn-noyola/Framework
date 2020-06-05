package testCasesITATAHost.Informes.InformesGestion;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.Informes.InformesGestion.ResumenFaltantes.ResumenFaltantesProcess;
import procesosITATAHost.Informes.InformesGestion.ResumenFaltantes.ResumenFaltantesReportProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class ResumenFaltantesTest {

	final static String titulo="Resumen de faltantes"; 
	final static String subtitulo="Informes   -   Informes de gestión   -   Resumen de faltantes"; 
	final static String[] botones={"Informe","Volver"};  
	final static String encabezado=null;
	final static String[] labels= {"Año","Concesionaria", "Plaza","Mes"};
	final static String titulo_report="Informe de resumen de faltantes"; 
	final static String[] botones_report={"Ver en Excel"};
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(ResumenFaltantesTest.class);
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
	public void tesResumenFaltantes1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Resumen Faltantes");
		logger.info("TC1 Resumen Faltantes: Probar el análisis sintáctico correcto");
		logger.info("TC1 Resumen Faltantes: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Resumen de faltantes");
		ResumenFaltantesProcess proceso=new ResumenFaltantesProcess();
		boolean analisis=proceso.sintacticAnalysis(titulo, subtitulo, botones,encabezado, labels);
		if (analisis) {
			logger.info("TC1 Resumen Faltantes: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Resumen Faltantes: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Resumen Faltantes: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testResumenFaltantes2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Resumen Faltantes");
		logger.info("TC2 Resumen Faltantes: Probar el análisis sintáctico correcto del report generado");
		logger.info("TC2 Resumen Faltantes: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Resumen de faltantes");
		ResumenFaltantesProcess proceso=new ResumenFaltantesProcess();
		proceso.informe();
		ResumenFaltantesReportProcess report=new ResumenFaltantesReportProcess();
		report.pasarANuevaVentana();
		boolean analisis=report.sintacticAnalysis(titulo_report, botones_report);
		report.cerrar();
		if (analisis) {
			logger.info("TC2 Resumen Faltantes: análisis sintáctico correcto");
		}
		else {
			logger.error("TC2 Resumen Faltantes: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC2 Resumen Faltantes: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testResumenFaltantes3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Resumen Faltantes");
		logger.info("TC3 Resumen Faltantes: Probar generar el report en Excel");
		logger.info("TC3 Resumen Faltantes: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Resumen de faltantes");
		ResumenFaltantesProcess proceso=new ResumenFaltantesProcess();
		proceso.informe();
		ResumenFaltantesReportProcess report=new ResumenFaltantesReportProcess();
		report.pasarANuevaVentana();
		report.Excel();
		report.cerrar();
		logger.info("TC3 Resumen Faltantes: Resultado obtenido=true");
		assertTrue(true);
	}
}
