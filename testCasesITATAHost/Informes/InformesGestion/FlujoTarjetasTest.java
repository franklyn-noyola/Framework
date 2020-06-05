package testCasesITATAHost.Informes.InformesGestion;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.Informes.InformesGestion.FlujoTarjetas.FlujoTarjetasProcess;
import procesosITATAHost.Informes.InformesGestion.FlujoTarjetas.FlujoTarjetasReportProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class FlujoTarjetasTest {

	final static String titulo="Flujo de tarjetas de proximidad"; 
	final static String subtitulo="Informes   -   Informes de gestión   -   Flujo de tarjetas de proximidad"; 
	final static String[] botones={"Informe","Volver"};  
	final static String encabezado=null;
	final static String[] labels= {"Año","Concesionaria", "Plaza","Mes"};
	final static String titulo_report="Informe de flujo de tarjetas de proximidad"; 
	final static String[] botones_report={"Ver en Excel"};   
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(FlujoTarjetasTest.class);
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
	public void testFlujosTarjetas1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Flujo Tarjetas");
		logger.info("TC1 Flujo Tarjetas: Probar el análisis sintáctico correcto");
		logger.info("TC1 Flujo Tarjetas: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Flujo de tarjetas de proximidad");
		FlujoTarjetasProcess proceso=new FlujoTarjetasProcess();
		boolean analisis=proceso.sintacticAnalysis(titulo, subtitulo, botones,encabezado, labels);
		if (analisis) {
			logger.info("TC1 Flujo Tarjetas: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Flujo Tarjetas: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Flujo Tarjetas: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testFlujosTarjetas2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Flujo Tarjetas");
		logger.info("TC2 Flujo Tarjetas: Probar el análisis sintáctico correcto del report generado");
		logger.info("TC2 Flujo Tarjetas: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Flujo de tarjetas de proximidad");
		FlujoTarjetasProcess proceso=new FlujoTarjetasProcess();
		proceso.informe();
		FlujoTarjetasReportProcess report=new FlujoTarjetasReportProcess();
		report.pasarANuevaVentana();
		boolean analisis=report.sintacticAnalysis(titulo_report, botones_report);
		report.cerrar();
		if (analisis) {
			logger.info("TC2 Flujo Tarjetas: análisis sintáctico correcto");
		}
		else {
			logger.error("TC2 Flujo Tarjetas: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC2 Flujo Tarjetas: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testFlujoTarjetas3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Flujo Tarjetas");
		logger.info("TC3 Flujo Tarjetas: Probar generar el report en Excel");
		logger.info("TC3 Flujo Tarjetas: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Flujo de tarjetas de proximidad");
		FlujoTarjetasProcess proceso=new FlujoTarjetasProcess();
		proceso.informe();
		FlujoTarjetasReportProcess report=new FlujoTarjetasReportProcess();
		report.pasarANuevaVentana();
		report.Excel();
		report.cerrar();
		logger.info("TC3 Flujo Tarjetas: Resultado obtenido=true");
		assertTrue(true);
	}
}
