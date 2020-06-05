package testCasesITATAHost.Informes.InformesGestion;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.Informes.InformesGestion.FaltantesTurno.FaltantesTurnoProcess;
import procesosITATAHost.Informes.InformesGestion.FaltantesTurno.FaltantesTurnoReportProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class FaltantesTurnoTest {

	final static String titulo="Faltantes por turno"; 
	final static String subtitulo="Informes   -   Informes de gestión   -   Faltantes por turno"; 
	final static String[] botones={"Informe","Volver"};  
	final static String encabezado=null;
	final static String[] labels= {"Año","Concesionaria", "Plaza","Mes"};
	final static String titulo_report="Informe de faltantes por turno"; 
	final static String[] botones_report={"Ver en Excel"}; 
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(FaltantesTurnoTest.class);
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
	public void tesFaltantesTurno1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Faltantes Turno");
		logger.info("TC1 Faltantes Turno: Probar el análisis sintáctico correcto");
		logger.info("TC1 Faltantes Turno: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Faltantes por turno");
		FaltantesTurnoProcess proceso=new FaltantesTurnoProcess();
		boolean analisis=proceso.sintacticAnalysis(titulo, subtitulo, botones,encabezado, labels);
		if (analisis) {
			logger.info("TC1 Faltantes Turno: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Faltantes Turno: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Faltantes Turno: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testFaltantesTurno2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Faltantes Turno");
		logger.info("TC2 Faltantes Turno: Probar el análisis sintáctico correcto del report generado");
		logger.info("TC2 Faltantes Turno: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Faltantes por turno");
		FaltantesTurnoProcess proceso=new FaltantesTurnoProcess();
		proceso.informe();
		FaltantesTurnoReportProcess report=new FaltantesTurnoReportProcess();
		report.pasarANuevaVentana();
		boolean analisis=report.sintacticAnalysis(titulo_report, botones_report);
		report.cerrar();
		if (analisis) {
			logger.info("TC2 Faltantes Turno: análisis sintáctico correcto");
		}
		else {
			logger.error("TC2 Faltantes Turno: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC2 Faltantes Turno: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testFaltantesTurno3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Faltantes Turno");
		logger.info("TC3 Faltantes Turno: Probar generar el report en Excel");
		logger.info("TC3 Faltantes Turno: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Faltantes por turno");
		FaltantesTurnoProcess proceso=new FaltantesTurnoProcess();
		proceso.informe();
		FaltantesTurnoReportProcess report=new FaltantesTurnoReportProcess();
		report.pasarANuevaVentana();
		report.Excel();
		report.cerrar();
		logger.info("TC3 Faltantes Turno: Resultado obtenido=true");
		assertTrue(true);
	}
}
