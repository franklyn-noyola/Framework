package testCasesITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje.ParametrosPeajeProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje.ParametrosPeajeReportProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class ParametrosPeajeReportTest {

	final static String titulo="Par�metros de peaje"; 
	final static String[] botones={"Ver en Excel"};  
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(ParametrosPeajeReportTest.class);
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
	public void testParametrosPeajeReport1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Parametros Peaje Report");
		logger.info("TC1 Parametros Peaje Report: Probar el an�lisis sint�ctico correcto del report generado");
		logger.info("TC1 Parametros Peaje Report: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Par�metros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		parametros.informe();
		ParametrosPeajeReportProcess report=new ParametrosPeajeReportProcess();
		report.pasarANuevaVentana();
		boolean analisis=report.sintacticAnalysis(titulo, botones);
		report.cerrar();
		if (analisis) {
			logger.info("TC1 Parametros Peaje Report: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC1 Parametros Peaje Report: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		parametros.volver();
		logger.info("TC1 Parametros Peaje  Report: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testParametrosPeajeReport2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Parametros Peaje Report");
		logger.info("TC2 Parametros Peaje Report: Probar generar el report en Excel");
		logger.info("TC2 Parametros Peaje Report: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Par�metros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		parametros.informe();
		ParametrosPeajeReportProcess report=new ParametrosPeajeReportProcess();
		report.pasarANuevaVentana();
		report.Excel();
		report.cerrar();
		parametros.volver();
		logger.info("TC2 Parametros Report: Resultado obtenido=true");
		assertTrue(true);
	}
}
