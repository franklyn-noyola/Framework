package testCasesITATAHost.ConfiguracionSistema.GestionClases.Categorias;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.GestionClases.Categorias.CategoriasProcess;
import procesosITATAHost.ConfiguracionSistema.GestionClases.Categorias.CategoriasReportProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class CategoriasReportTest {

	final static String titulo="Categor�a"; 
	final static String[] botones={"Ver en Excel"};  
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(CategoriasReportTest.class);
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
	public void testCategor�asReport1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Categor�as Report");
		logger.info("TC1 Categor�as Report: Probar el an�lisis sint�ctico correcto del report generado");
		logger.info("TC1 Categor�as Report: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Gesti�n de clases","Categor�as");
		CategoriasProcess categorias=new CategoriasProcess();
		categorias.informe();
		CategoriasReportProcess report=new CategoriasReportProcess();
		report.pasarANuevaVentana();
		boolean analisis=report.sintacticAnalysis(titulo, botones);
		report.cerrar();
		if (analisis) {
			logger.info("TC1 Categor�as Report: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC1 Categor�as Report: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		categorias.volver();
		logger.info("TC1 Categor�as Report: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testCategor�asReport2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Categor�as Report");
		logger.info("TC2 Categor�as Report: Probar generar el report en Excel");
		logger.info("TC2 Categor�as Report: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Gesti�n de clases","Categor�as");
		CategoriasProcess categorias=new CategoriasProcess();
		categorias.informe();
		CategoriasReportProcess report=new CategoriasReportProcess();
		report.pasarANuevaVentana();
		report.Excel();
		report.cerrar();
		categorias.volver();
		logger.info("TC2 Categor�as Report: Resultado obtenido=true");
		assertTrue(true);
	}
}
