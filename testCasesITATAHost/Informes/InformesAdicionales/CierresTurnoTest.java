package testCasesITATAHost.Informes.InformesAdicionales;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.Informes.InformesAdicionales.CierreTurnos.CierresTurnoProcess;
import procesosITATAHost.Informes.InformesAdicionales.CierreTurnos.CierresTurnoReportProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class CierresTurnoTest {

	final static String titulo="Cierres de turno de cobradores"; 
	final static String subtitulo="Informes   -   Informes adicionales   -   Cierres de turno de cobradores"; 
	final static String[] botones={"Informe","Volver"};  
	final static String encabezado=null;
	final static String[] labels= {"Año","Cobrador","Concesionaria", "Plaza","Mes"};
	final static String titulo_report="Informe de cierres de turno de cobradores"; 
	final static String[] botones_report={"Ver en Excel"};   
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(CierresTurnoTest.class);
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
	public void testCierresTurno1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Cierres Turno");
		logger.info("TC1 Cierres Turno: Probar el análisis sintáctico correcto");
		logger.info("TC1 Cierres Turno: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes adicionales","Cierres de turno de cobradores");
		CierresTurnoProcess proceso=new CierresTurnoProcess();
		boolean analisis=proceso.sintacticAnalysis(titulo, subtitulo, botones,encabezado, labels);
		if (analisis) {
			logger.info("TC1 Cierres Turno: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Cierres Turno: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Cierres Turno: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testCierresTurno2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Cierres Turno");
		logger.info("TC2 Cierres Turno: Probar el análisis sintáctico correcto del report generado");
		logger.info("TC2 Cierres Turno: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes adicionales","Cierres de turno de cobradores");
		CierresTurnoProcess proceso=new CierresTurnoProcess();
		proceso.informe();
		CierresTurnoReportProcess report=new CierresTurnoReportProcess();
		report.pasarANuevaVentana();
		boolean analisis=report.sintacticAnalysis(titulo_report, botones_report);
		report.cerrar();
		if (analisis) {
			logger.info("TC2 Cierres Turno: análisis sintáctico correcto");
		}
		else {
			logger.error("TC2 Cierres Turno: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC2 Cierres Turno: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testCierresTurno3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Cierres Turno");
		logger.info("TC3 Cierres Turno: Probar generar el report en Excel");
		logger.info("TC3 Cierres Turno: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes adicionales","Cierres de turno de cobradores");
		CierresTurnoProcess proceso=new CierresTurnoProcess();
		proceso.informe();
		CierresTurnoReportProcess report=new CierresTurnoReportProcess();
		report.pasarANuevaVentana();
		report.Excel();
		report.cerrar();
		logger.info("TC3 Cierres Turno: Resultado obtenido=true");
		assertTrue(true);
	}
	
	@Test
	public void testCierresTurno4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Cierres Turno");
		logger.info("TC4 Cierres Turno: Comprobar validación formato campo Año");
		logger.info("TC4 Cierres Turno: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes adicionales","Cierres de turno de cobradores");
		CierresTurnoProcess proceso=new CierresTurnoProcess();
		proceso.escribirOpcionAño("!");
		proceso.informe();
		boolean hayErrorValidacion=proceso.hayErrorValidacionFormatoAño();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC4 Crear Cierres Turno: Resultado obtenido:"+hayErrorValidacion);
		proceso.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCierresTurno5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Cierres Turno");
		logger.info("TC5 Cierres Turno: Comprobar validación formato campo Cobrador");
		logger.info("TC5 Cierres Turno: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes adicionales","Cierres de turno de cobradores");
		CierresTurnoProcess proceso=new CierresTurnoProcess();
		proceso.escribirOpcionCobrador("!");
		proceso.informe();
		boolean hayErrorValidacion=proceso.hayErrorValidacionFormatoCobrador();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC5 Crear Plaza: Resultado obtenido:"+hayErrorValidacion);
		proceso.volver();
		assertTrue(hayErrorValidacion);
	}
}
