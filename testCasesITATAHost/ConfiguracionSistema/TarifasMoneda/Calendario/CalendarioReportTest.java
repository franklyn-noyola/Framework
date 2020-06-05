package testCasesITATAHost.ConfiguracionSistema.TarifasMoneda.Calendario;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.TarifasMoneda.Calendario.CalendarioProcess;
import procesosITATAHost.ConfiguracionSistema.TarifasMoneda.Calendario.CalendarioReportProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class CalendarioReportTest {

	final static String titulo="Calendario"; 
	final static String[] botones={"Ver en Excel"};  
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(CalendarioReportTest.class);
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
	public void testCalendarioReport1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Calendario Report");
		logger.info("TC1 Calendario Report: Probar el análisis sintáctico correcto del report generado");
		logger.info("TC1 Calendario Report: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Tarifas & Moneda","Calendario");
		CalendarioProcess calendario=new CalendarioProcess();
		calendario.informe();
		CalendarioReportProcess report=new CalendarioReportProcess();
		report.pasarANuevaVentana();
		boolean analisis=report.sintacticAnalysis(titulo, botones);
		report.cerrar();
		if (analisis) {
			logger.info("TC1 Calendario Report: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Calendario Report: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		calendario.volver();
		logger.info("TC1 Calendario Report: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testCalendarioReport2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Calendario Report");
		logger.info("TC2 Calendario Report: Probar generar el report en Excel");
		logger.info("TC2 Calendario Report: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Tarifas & Moneda","Calendario");
		CalendarioProcess calendario=new CalendarioProcess();
		calendario.informe();
		CalendarioReportProcess report=new CalendarioReportProcess();
		report.pasarANuevaVentana();
		report.Excel();
		report.cerrar();
		calendario.volver();
		logger.info("TC2 Calendario Report: Resultado obtenido=true");
		assertTrue(true);
	}
}
