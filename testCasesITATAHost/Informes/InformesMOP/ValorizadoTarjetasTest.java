package testCasesITATAHost.Informes.InformesMOP;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.Informes.InformesMOP.ValorizadoTarjetas.ValorizadoTarjetasProcess;
import procesosITATAHost.Informes.InformesMOP.ValorizadoTarjetas.ValorizadoTarjetasReportProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class ValorizadoTarjetasTest {

	final static String titulo="Valorizado de tarjetas de proximidad"; 
	final static String subtitulo="Informes   -   Informes MOP   -   Valorizado de tarjetas de proximidad"; 
	final static String[] botones={"Informe","Volver"};  
	final static String encabezado=null;
	final static String[] labels= {"Año","Concesionaria","Plaza","Mes"};
	final static String titulo_report="Informe valorizado de tarjetas de proximidad"; 
	final static String[] botones_report={"Ver en Excel"};  
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(ValorizadoTarjetasTest.class);
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
	public void testValorizadoTarjetas1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Valorizado Tarjetas");
		logger.info("TC1 Valorizado Tarjetas: Probar el análisis sintáctico correcto");
		logger.info("TC1 Valorizado Tarjetas: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes MOP","Valorizado de tarjetas de proximidad");
		ValorizadoTarjetasProcess proceso=new ValorizadoTarjetasProcess();
		boolean analisis=proceso.sintacticAnalysis(titulo, subtitulo, botones,encabezado, labels);
		if (analisis) {
			logger.info("TC1 Valorizado Tarjetas: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Valorizado Tarjetas: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Valorizado Tarjetas: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testValorizadoTarjetas2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Valorizado Tarjetas");
		logger.info("TC2 Valorizado VeciTarjetas: Probar el análisis sintáctico correcto del report generado");
		logger.info("TC2 Valorizado Tarjetas: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes MOP","Valorizado de tarjetas de proximidad");
		ValorizadoTarjetasProcess proceso=new ValorizadoTarjetasProcess();
		proceso.informe();
		ValorizadoTarjetasReportProcess report=new ValorizadoTarjetasReportProcess();
		report.pasarANuevaVentana();
		boolean analisis=report.sintacticAnalysis(titulo_report, botones_report);
		report.cerrar();
		if (analisis) {
			logger.info("TC2 Valorizado Tarjetas: análisis sintáctico correcto");
		}
		else {
			logger.error("TC2 Valorizado Tarjetas: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC2 Valorizado Tarjetas: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testValorizadoTarjetas3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Valorizado Tarjetas");
		logger.info("TC3 Valorizado Tarjetas: Probar generar el report en Excel");
		logger.info("TC3 Valorizado Tarjetas: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes MOP","Valorizado de tarjetas de proximidad");
		ValorizadoTarjetasProcess proceso=new ValorizadoTarjetasProcess();
		proceso.informe();
		ValorizadoTarjetasReportProcess report=new ValorizadoTarjetasReportProcess();
		report.pasarANuevaVentana();
		report.Excel();
		report.cerrar();
		logger.info("TC3 Valorizado Tarjetas: Resultado obtenido=true");
		assertTrue(true);
	}
}
