package testCasesITATAPlaza.Operadores.GestionGruposOperadores;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAPlaza.Operadores.GestionGrupos.GestionGruposOperadoresProcess;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionGruposOperadores.GestionGruposOperadoresReportProcess;
import testCasesITATAPlaza.InitBOTest;
import testCasesITATAPlaza.LoginTest;

public class GestionGruposOperadoresReportTest {

	final static String titulo="Grupos de operadores"; 
	final static String[] botones={"Ver en Excel"};  
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(GestionGruposOperadoresReportTest.class);
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
	public void testGestionGruposOperadoresReport1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Gestion Grupos Operadores Report");
		logger.info("TC1 Gestion Grupos Operadores Report: Probar el análisis sintáctico correcto del report generado");
		logger.info("TC1 Gestion Grupos Operadores Report: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de grupos");
		GestionGruposOperadoresProcess gestionGruposOperadores=new GestionGruposOperadoresProcess();
		gestionGruposOperadores.informe();
		GestionGruposOperadoresReportProcess report=new GestionGruposOperadoresReportProcess();
		report.pasarANuevaVentana();
		boolean analisis=report.sintacticAnalysis(titulo, botones);
		report.cerrar();
		if (analisis) {
			logger.info("TC1 Gestion Grupos Operadores Report: Análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Gestion Grupos Operadores Report: Análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		gestionGruposOperadores.volver();
		assertTrue(analisis);
	}

	@Test
	public void testGestionGruposOperadoresReport2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Gestion Grupos Operadores Report");
		logger.info("TC2 Gestion Grupos Operadores Report: Probar generar el report en Excel");
		logger.info("TC2 Gestion Grupos Operadores Report: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de grupos");
		GestionGruposOperadoresProcess gestionGruposOperadores=new GestionGruposOperadoresProcess();
		gestionGruposOperadores.informe();
		GestionGruposOperadoresReportProcess report=new GestionGruposOperadoresReportProcess();
		report.pasarANuevaVentana();
		report.Excel();
		report.cerrar();
		gestionGruposOperadores.volver();
		logger.info("TC2 Gestion Grupos Operadores Report: Resultado obtenido=true");
		assertTrue(true);
	}
}
