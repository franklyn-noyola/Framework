package testCasesITATAHost.Informes.InformesMOP;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.Informes.InformesMOP.CaratulaMOPRespaldo.CaratulaMOPRespaldoProcess;
import procesosITATAHost.Informes.InformesMOP.CaratulaMOPRespaldo.CaratulaMOPRespaldoReportProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class CaratulaMOPRespaldoTest {

	final static String titulo_caratulamop="Car�tula MOP (respaldo factura)"; 
	final static String subtitulo_caratulamop="Informes   -   Informes MOP   -   Car�tula MOP (respaldo factura)"; 
	final static String[] botones_caratulamop={"Informe","Volver"};  
	final static String encabezado_caratulamop=null;
	final static String[] labels_caratulamop= {"A�o","Concesionaria","Mes"};
	final static String titulo_caratulamopreport="Car�tula MOP (respaldo factura)"; 
	final static String[] botones_caratulamopreport={"Ver en Excel"};  
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(CaratulaMOPRespaldoTest.class);
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
		logger.info("TC1 Caratula MOP Respaldo");
		logger.info("TC1 Caratula MOP Respaldo: Probar el an�lisis sint�ctico correcto");
		logger.info("TC1 Caratula MOP Respaldo: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes MOP","Car�tula MOP (respaldo factura)");
		CaratulaMOPRespaldoProcess caratulamoprespaldo=new CaratulaMOPRespaldoProcess();
		boolean analisis=caratulamoprespaldo.sintacticAnalysis(titulo_caratulamop, subtitulo_caratulamop, botones_caratulamop,
				encabezado_caratulamop, labels_caratulamop);
		if (analisis) {
			logger.info("TC1 Caratula MOP Respaldo: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC1 Caratula MOP Respaldo: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Car�tula MOP Respaldo: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testCaratulaMOP2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Caratula MOP Respaldo");
		logger.info("TC2 Caratula MOP Respaldo: Probar el an�lisis sint�ctico correcto del report generado");
		logger.info("TC2 Caratula MOP Respaldo: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes MOP","Car�tula MOP (respaldo factura)");
		CaratulaMOPRespaldoProcess caratulamop=new CaratulaMOPRespaldoProcess();
		caratulamop.informe();
		CaratulaMOPRespaldoReportProcess caratulamoprespaldoreport=new CaratulaMOPRespaldoReportProcess();
		caratulamoprespaldoreport.pasarANuevaVentana();
		boolean analisis=caratulamoprespaldoreport.sintacticAnalysis(titulo_caratulamopreport, botones_caratulamopreport);
		caratulamoprespaldoreport.cerrar();
		if (analisis) {
			logger.info("TC2 Caratula MOP Respaldo: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC2 Caratula MOP Respaldo: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC2 Car�tula MOP Respaldo: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testCaratulaMOP3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Caratula MOP Respaldo");
		logger.info("TC3 Caratula MOP Respaldo: Probar generar el report en Excel");
		logger.info("TC3 Caratula MOP Respaldo: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes MOP","Car�tula MOP (respaldo factura)");
		CaratulaMOPRespaldoProcess caratulamop=new CaratulaMOPRespaldoProcess();
		caratulamop.informe();
		CaratulaMOPRespaldoReportProcess caratulamopreport=new CaratulaMOPRespaldoReportProcess();
		caratulamopreport.pasarANuevaVentana();
		caratulamopreport.Excel();
		caratulamopreport.cerrar();
		logger.info("TC3 Caratula MOP Respaldo: Resultado obtenido=true");
		assertTrue(true);
	}
}
