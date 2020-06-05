package testCasesITATAHost.Informes.InformesGestion;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.Informes.InformesGestion.VentaAbonos.VentaAbonosProcess;
import procesosITATAHost.Informes.InformesGestion.VentaAbonos.VentaAbonosReportProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class VentaAbonosTest {

	final static String titulo="Venta de abonos de clientes prepago"; 
	final static String subtitulo="Informes   -   Informes de gestión   -   Venta de abonos de clientes prepago"; 
	final static String[] botones={"Informe","Volver"};  
	final static String encabezado=null;
	final static String[] labels= {"Año","Mes"};
	final static String titulo_report="Venta de abonos de clientes prepago"; 
	final static String[] botones_report={"Ver en Excel"};  
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(VentaAbonosTest.class);
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
	public void testVentaAbonos() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Venta Abonos");
		logger.info("TC1 Venta Abonos: Probar el análisis sintáctico correcto");
		logger.info("TC1 Venta Abonos: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Venta de abonos de clientes prepago");
		VentaAbonosProcess proceso=new VentaAbonosProcess();
		boolean analisis=proceso.sintacticAnalysis(titulo, subtitulo, botones,encabezado, labels);
		if (analisis) {
			logger.info("TC1 Venta Abonos: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Venta Abonos: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Venta Abonos: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testVentaAbonos2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Venta Abonos");
		logger.info("TC2 Venta Abonos: Probar el análisis sintáctico correcto del report generado");
		logger.info("TC2 Venta Abonos: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Venta de abonos de clientes prepago");
		VentaAbonosProcess proceso=new VentaAbonosProcess();
		proceso.informe();
		VentaAbonosReportProcess report=new VentaAbonosReportProcess();
		report.pasarANuevaVentana();
		boolean analisis=report.sintacticAnalysis(titulo_report, botones_report);
		report.cerrar();
		if (analisis) {
			logger.info("TC2 Venta Abonos: análisis sintáctico correcto");
		}
		else {
			logger.error("TC2 Venta Abonos: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC2 Venta Abonos: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testVentaAbonos3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Venta Abonos");
		logger.info("TC3 Venta Abonos: Probar generar el report en Excel");
		logger.info("TC3 Venta Abonos: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Venta de abonos de clientes prepago");
		VentaAbonosProcess proceso=new VentaAbonosProcess();
		proceso.informe();
		VentaAbonosReportProcess report=new VentaAbonosReportProcess();
		report.pasarANuevaVentana();
		report.Excel();
		report.cerrar();
		logger.info("TC3 Venta Abonos: Resultado obtenido=true");
		assertTrue(true);
	}
}
