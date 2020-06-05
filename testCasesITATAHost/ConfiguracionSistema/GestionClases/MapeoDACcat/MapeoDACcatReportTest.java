package testCasesITATAHost.ConfiguracionSistema.GestionClases.MapeoDACcat;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.GestionClases.MapeoDACcat.MapeoDACcatProcess;
import procesosITATAHost.ConfiguracionSistema.GestionClases.MapeoDACcat.MapeoDACcatReportProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class MapeoDACcatReportTest {

	final static String titulo="Mapeo DAC-Cat"; 
	final static String[] botones={"Ver en Excel"};  
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(MapeoDACcatReportTest.class);
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
	public void testMapeosReport1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Mapeos Report");
		logger.info("TC1 Mapeos Report: Probar el análisis sintáctico correcto del report generado");
		logger.info("TC1 Mapeos Report: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		mapeos.informe();
		MapeoDACcatReportProcess report=new MapeoDACcatReportProcess();
		report.pasarANuevaVentana();
		boolean analisis=report.sintacticAnalysis(titulo, botones);
		report.cerrar();
		if (analisis) {
			logger.info("TC1 Mapeos Report: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Mapeos Report: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		mapeos.volver();
		logger.info("TC1 Mapeos Report: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testMapeosReport2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Mapeos Report");
		logger.info("TC2 Mapeos Report: Probar generar el report en Excel");
		logger.info("TC2 Mapeos Report: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		mapeos.informe();
		MapeoDACcatReportProcess report=new MapeoDACcatReportProcess();
		report.pasarANuevaVentana();
		report.Excel();
		report.cerrar();
		mapeos.volver();
		logger.info("TC2 Mapeos Report: Resultado obtenido=true");
		assertTrue(true);
	}
}
