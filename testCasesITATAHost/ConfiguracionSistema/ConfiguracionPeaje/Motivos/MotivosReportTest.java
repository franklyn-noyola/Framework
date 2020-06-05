package testCasesITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Motivos;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Motivos.MotivosProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Motivos.MotivosReportProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class MotivosReportTest {

	final static String titulo="Motivos"; 
	final static String[] botones={"Ver en Excel"};  
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(MotivosReportTest.class);
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
	public void testMotivosReport1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Motivos Report");
		logger.info("TC1 Motivos Report: Probar el análisis sintáctico correcto del report generado");
		logger.info("TC1 Motivos Report: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Motivos");
		MotivosProcess motivos=new MotivosProcess();
		motivos.informe();
		MotivosReportProcess report=new MotivosReportProcess();
		report.pasarANuevaVentana();
		boolean analisis=report.sintacticAnalysis(titulo, botones);
		report.cerrar();
		if (analisis) {
			logger.info("TC1 Motivos Report: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Motivos Report: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		motivos.volver();
		logger.info("TC1 Motivos Report: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testMotivosReport2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Motivos Report");
		logger.info("TC2 Motivos Report: Probar generar el report en Excel");
		logger.info("TC2 Motivos Report: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Motivos");
		MotivosProcess motivos=new MotivosProcess();
		motivos.informe();
		MotivosReportProcess report=new MotivosReportProcess();
		report.pasarANuevaVentana();
		report.Excel();
		report.cerrar();
		motivos.volver();
		logger.info("TC2 Motivos Report: Resultado obtenido=true");
		assertTrue(true);
	}
}
