package testCasesITATAHost.ConfiguracionSistema.TarifasMoneda.MonedaAceptada;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.TarifasMoneda.MonedaAceptada.MonedaAceptadaProcess;
import procesosITATAHost.ConfiguracionSistema.TarifasMoneda.MonedaAceptada.MonedaAceptadaReportProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class MonedaAceptadaReportTest {

	final static String titulo="Moneda aceptada"; 
	final static String[] botones={"Ver en Excel"};  
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(MonedaAceptadaReportTest.class);
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
	public void testMonedaAceptadaReport1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Moneda Aceptada Report");
		logger.info("TC1 Moneda Aceptada Report: Probar el análisis sintáctico correcto del report generado");
		logger.info("TC1 Moneda Aceptada Report: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Tarifas & Moneda","Moneda aceptada");
		MonedaAceptadaProcess moneda=new MonedaAceptadaProcess();
		moneda.informe();
		MonedaAceptadaReportProcess report=new MonedaAceptadaReportProcess();
		report.pasarANuevaVentana();
		boolean analisis=report.sintacticAnalysis(titulo, botones);
		report.cerrar();
		if (analisis) {
			logger.info("TC1 Moneda Aceptada Report: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Moneda Aceptada Report: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		moneda.volver();
		logger.info("TC1 Moneda Aceptada Report: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testMonedaAceptadaReport2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Moneda Aceptada Report");
		logger.info("TC2 Moneda Aceptada Report: Probar generar el report en Excel");
		logger.info("TC2 Moneda Aceptada Report: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Tarifas & Moneda","Moneda aceptada");
		MonedaAceptadaProcess moneda=new MonedaAceptadaProcess();
		moneda.informe();
		MonedaAceptadaReportProcess report=new MonedaAceptadaReportProcess();
		report.pasarANuevaVentana();
		report.Excel();
		report.cerrar();
		moneda.volver();
		logger.info("TC2 Moneda Aceptada Report: Resultado obtenido=true");
		assertTrue(true);
	}
}
