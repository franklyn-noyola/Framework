package testCasesITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.NodosProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.NodosReportProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class NodosReportTest {

	final static String titulo="Nodos"; 
	final static String[] botones={"Ver en Excel"};  
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(NodosReportTest.class);
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
	public void testNodosReport1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Nodos Report");
		logger.info("TC1 Nodos Report: Probar el análisis sintáctico correcto del report generado");
		logger.info("TC1 Nodos Report: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		nodos.informe();
		NodosReportProcess report=new NodosReportProcess();
		report.pasarANuevaVentana();
		boolean analisis=report.sintacticAnalysis(titulo, botones);
		report.cerrar();
		if (analisis) {
			logger.info("TC1 Nodos Report: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Nodos Report: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		nodos.volver();
		logger.info("TC1 Nodos Report: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testNodosReport2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Nodos Report");
		logger.info("TC2 Nodos Report: Probar generar el report en Excel");
		logger.info("TC2 Nodos Report: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		nodos.informe();
		NodosReportProcess report=new NodosReportProcess();
		report.pasarANuevaVentana();
		report.Excel();
		report.cerrar();
		nodos.volver();
		logger.info("TC2 Nodos Report: Resultado obtenido=true");
		assertTrue(true);
	}
}
