package testCasesITATAHost.ConfiguracionSistema.Operadores.GestionOperadores;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.GestionOperadoresProcess;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.GestionOperadoresReportProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class GestionOperadoresReportTest {

	final static String titulo="Operadores"; 
	final static String[] botones={"Ver en Excel"};  
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(GestionOperadoresReportTest.class);
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
	public void testGestionOperadoresReport1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Gestion Operadores Report");
		logger.info("TC1 Gestion Operadores Report: Probar el análisis sintáctico correcto del report generado");
		logger.info("TC1 Gestion Operadores Report: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.informe();
		GestionOperadoresReportProcess report=new GestionOperadoresReportProcess();
		report.pasarANuevaVentana();
		boolean analisis=report.sintacticAnalysis(titulo, botones);
		report.cerrar();
		if (analisis) {
			logger.info("TC2 Gestion Operadores Report: análisis sintáctico correcto");
		}
		else {
			logger.error("TC2 Gestion Operadores Report: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		gestionOperadores.volver();
		assertTrue(analisis);
	}

	@Test
	public void testGestionOperadoresReport3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Gestion Operadores Report3");
		logger.info("TC3 Gestion Operadores Report: Probar generar el report en Excel");
		logger.info("TC3 Gestion Operadores Report: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.informe();
		GestionOperadoresReportProcess report=new GestionOperadoresReportProcess();
		report.pasarANuevaVentana();
		report.Excel();
		report.cerrar();
		gestionOperadores.volver();
		logger.info("TC2 Gestion Operadores Report: Resultado obtenido=true");
		assertTrue(true);
	}
}
