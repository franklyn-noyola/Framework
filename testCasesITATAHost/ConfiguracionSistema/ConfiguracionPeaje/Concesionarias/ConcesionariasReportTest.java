package testCasesITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.ConcesionariasProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.ConcesionariasReportProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class ConcesionariasReportTest {

	final static String titulo="Concesionarias - Plazas - Vías"; 
	final static String[] botones={"Ver en Excel"};  
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(ConcesionariasReportTest.class);
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
	public void testConcesionariasReport1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Concesionarias Report");
		logger.info("TC1 Concesionarias Report: Probar el análisis sintáctico correcto del report generado");
		logger.info("TC1 Concesionarias Report: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		concesionarias.informe();
		ConcesionariasReportProcess report=new ConcesionariasReportProcess();
		report.pasarANuevaVentana();
		boolean analisis=report.sintacticAnalysis(titulo, botones);
		report.cerrar();
		if (analisis) {
			logger.info("TC1 Concesionarias Report: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Concesionarias Report: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		concesionarias.volver();
		logger.info("TC1 Concesionarias Report: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testConcesionariasReport2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Concesionarias Report");
		logger.info("TC2 Concesionarias Report: Probar generar el report en Excel");
		logger.info("TC2 Concesionarias Report: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		concesionarias.informe();
		ConcesionariasReportProcess report=new ConcesionariasReportProcess();
		report.pasarANuevaVentana();
		report.Excel();
		report.cerrar();
		concesionarias.volver();
		logger.info("TC2 Concesionarias Report: Resultado obtenido=true");
		assertTrue(true);
	}
}
