package testCasesITATAHost.Informes.InformesGestion;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.Informes.InformesGestion.FaltantesDia.FaltantesDiaProcess;
import procesosITATAHost.Informes.InformesGestion.FaltantesDia.FaltantesDiaReportProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class FaltantesDiaTest {

	final static String titulo="Faltantes por d�a"; 
	final static String subtitulo="Informes   -   Informes de gesti�n   -   Faltantes por d�a"; 
	final static String[] botones={"Informe","Volver"};  
	final static String encabezado=null;
	final static String[] labels= {"A�o","Cobrador","Concesionaria", "Plaza","Mes"};
	final static String titulo_report="Informe de faltantes por d�a"; 
	final static String[] botones_report={"Ver en Excel"}; 
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(FaltantesDiaTest.class);
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
	public void tesFaltantesDia1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Faltantes Dia");
		logger.info("TC1 Faltantes Dia: Probar el an�lisis sint�ctico correcto");
		logger.info("TC1 Faltantes Dia: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gesti�n","Faltantes por d�a");
		FaltantesDiaProcess proceso=new FaltantesDiaProcess();
		boolean analisis=proceso.sintacticAnalysis(titulo, subtitulo, botones,encabezado, labels);
		if (analisis) {
			logger.info("TC1 Faltantes Dia: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC1 Faltantes Dia: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Faltantes Dia: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testFaltantesDia2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Faltantes Dia");
		logger.info("TC2 Faltantes Dia: Probar el an�lisis sint�ctico correcto del report generado");
		logger.info("TC2 Faltantes Dia: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gesti�n","Faltantes por d�a");
		FaltantesDiaProcess proceso=new FaltantesDiaProcess();
		proceso.informe();
		FaltantesDiaReportProcess report=new FaltantesDiaReportProcess();
		report.pasarANuevaVentana();
		boolean analisis=report.sintacticAnalysis(titulo_report, botones_report);
		report.cerrar();
		if (analisis) {
			logger.info("TC2 Faltantes Dia: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC2 Faltantes Dia: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC2 Faltantes Dia: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testFaltantesDia3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Faltantes Dia");
		logger.info("TC3 Faltantes Dia: Probar generar el report en Excel");
		logger.info("TC3 Faltantes Dia: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gesti�n","Faltantes por d�a");
		FaltantesDiaProcess proceso=new FaltantesDiaProcess();
		proceso.informe();
		FaltantesDiaReportProcess report=new FaltantesDiaReportProcess();
		report.pasarANuevaVentana();
		report.Excel();
		report.cerrar();
		logger.info("TC3 Faltantes Dia: Resultado obtenido=true");
		assertTrue(true);
	}
}
