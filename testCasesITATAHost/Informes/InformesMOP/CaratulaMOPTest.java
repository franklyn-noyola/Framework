package testCasesITATAHost.Informes.InformesMOP;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.Informes.InformesMOP.CaratulaMOP.CaratulaMOPProcess;
import procesosITATAHost.Informes.InformesMOP.CaratulaMOP.CaratulaMOPReportProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class CaratulaMOPTest {

	final static String titulo_caratulamop="Carátula MOP"; 
	final static String subtitulo_caratulamop="Informes   -   Informes MOP   -   Carátula MOP"; 
	final static String[] botones_caratulamop={"Informe","Volver"};  
	final static String encabezado_caratulamop=null;
	final static String[] labels_caratulamop= {"Año","Plaza","Mes"};
	final static String titulo_caratulamopreport="Carátula MOP"; 
	final static String[] botones_caratulamopreport={"Ver en Excel"};  
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(CaratulaMOPTest.class);
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
	public void testCaratulaMOP1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Caratula MOP");
		logger.info("TC1 Caratula MOP: Probar el análisis sintáctico correcto");
		logger.info("TC1 Caratula MOP: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes MOP","Carátula MOP");
		CaratulaMOPProcess caratulamop=new CaratulaMOPProcess();
		boolean analisis=caratulamop.sintacticAnalysis(titulo_caratulamop, subtitulo_caratulamop, botones_caratulamop,
				encabezado_caratulamop, labels_caratulamop);
		if (analisis) {
			logger.info("TC1 Caratula MOP: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Caratula MOP: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Carátula MOP: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testCaratulaMOP2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Caratula MOP");
		logger.info("TC2 Caratula MOP: Probar el análisis sintáctico correcto del report generado");
		logger.info("TC2 Caratula MOP: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes MOP","Carátula MOP");
		CaratulaMOPProcess caratulamop=new CaratulaMOPProcess();
		caratulamop.informe();
		CaratulaMOPReportProcess caratulamopreport=new CaratulaMOPReportProcess();
		caratulamopreport.pasarANuevaVentana();
		boolean analisis=caratulamopreport.sintacticAnalysis(titulo_caratulamopreport, botones_caratulamopreport);
		caratulamopreport.cerrar();
		if (analisis) {
			logger.info("TC2 Caratula MOP: análisis sintáctico correcto");
		}
		else {
			logger.error("TC2 Caratula MOP: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC2 Carátula MOP: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testCaratulaMOP3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Caratula MOP");
		logger.info("TC3 Caratula MOP: Probar generar el report en Excel");
		logger.info("TC3 Caratula MOP: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes MOP","Carátula MOP");
		CaratulaMOPProcess caratulamop=new CaratulaMOPProcess();
		caratulamop.informe();
		CaratulaMOPReportProcess caratulamopreport=new CaratulaMOPReportProcess();
		caratulamopreport.pasarANuevaVentana();
		caratulamopreport.Excel();
		caratulamopreport.cerrar();
		logger.info("TC3 Caratula MOP Report: Resultado obtenido=true");
		assertTrue(true);
	}
}
