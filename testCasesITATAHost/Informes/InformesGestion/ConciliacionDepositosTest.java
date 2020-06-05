package testCasesITATAHost.Informes.InformesGestion;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.Informes.InformesGestion.ConciliacionDepositos.ConciliacionDepositosProcess;
import procesosITATAHost.Informes.InformesGestion.ConciliacionDepositos.ConciliacionDepositosReportProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class ConciliacionDepositosTest {

	final static String titulo="Conciliación depósitos transporte de valores"; 
	final static String subtitulo="Informes   -   Informes de gestión   -   Conciliación depósitos transporte de valores"; 
	final static String[] botones={"Informe","Volver"};  
	final static String encabezado=null;
	final static String[] labels= {"Concesionaria","Plaza","Desde","Hasta"};
	final static String titulo_report="Informe de conciliación depósitos transporte de valores"; 
	final static String[] botones_report={"Ver en Excel"};  
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(ConciliacionDepositosTest.class);
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
	public void testConciliacionDepositos01() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Conciliacion Depositos");
		logger.info("TC1 Conciliacion Depositos: Probar el análisis sintáctico correcto");
		logger.info("TC1 Conciliacion Depositos: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Conciliación depósitos transporte de valores");
		ConciliacionDepositosProcess conciliacion=new ConciliacionDepositosProcess();
		boolean analisis=conciliacion.sintacticAnalysis(titulo, subtitulo, botones,
				encabezado, labels);
		if (analisis) {
			logger.info("TC1 Conciliacion Depositos: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Conciliacion Depositos: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Conciliacion Depositos: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testConciliacionDepositos02() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Conciliacion Depositos");
		logger.info("TC2 Conciliacion Depositos: Probar el análisis sintáctico correcto del report generado");
		logger.info("TC2 Conciliacion Depositos: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Conciliación depósitos transporte de valores");
		ConciliacionDepositosProcess conciliacion=new ConciliacionDepositosProcess();
		conciliacion.informe();
		ConciliacionDepositosReportProcess conciliacionreport=new ConciliacionDepositosReportProcess();
		conciliacionreport.pasarANuevaVentana();
		boolean analisis=conciliacionreport.sintacticAnalysis(titulo_report, botones_report);
		conciliacionreport.cerrar();
		if (analisis) {
			logger.info("TC2 Conciliacion Depositos: análisis sintáctico correcto");
		}
		else {
			logger.error("TC2 Conciliacion Depositos: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC2 Conciliacion Depositos: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}

	@Test
	public void testConciliacionDepositos03() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Conciliacion Depositos");
		logger.info("TC3 Conciliacion Depositos: Probar generar el report en Excel");
		logger.info("TC3 Conciliacion Depositos: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Conciliación depósitos transporte de valores");
		ConciliacionDepositosProcess conciliacion=new ConciliacionDepositosProcess();
		conciliacion.informe();
		ConciliacionDepositosReportProcess conciliacionreport=new ConciliacionDepositosReportProcess();
		conciliacionreport.pasarANuevaVentana();
		conciliacionreport.Excel();
		conciliacionreport.cerrar();
		logger.info("TC3 Conciliacion Depositos Resultado obtenido=true");
		assertTrue(true);
	}
	
	@Test
	public void testConciliacionDepositos04() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Conciliacion Depositos");
		logger.info("TC4 Conciliacion Depositos: Probar la validación de la existencia de la fecha Desde");
		logger.info("TC4 Conciliacion Depositos: Resultado esperado=true (aparece el tooltip)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Conciliación depósitos transporte de valores");
		ConciliacionDepositosProcess conciliacion=new ConciliacionDepositosProcess();
		conciliacion.borrarFechaDesde();
		boolean hayErrorValidacion=conciliacion.hayErrorValidacionRequeridoFechaDesde();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
			logger.info("TC4 Conciliacion Depositos: Debería haber aparecido el tooltip");
		}
		logger.info("TC4 Conciliacion Depositos: Resultado obtenido:"+hayErrorValidacion);
		assertTrue(true);
	}
	
	@Test
	public void testConciliacionDepositos05() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Conciliacion Depositos");
		logger.info("TC4 Conciliacion Depositos: Probar la validación del formato de la fecha Desde");
		logger.info("TC4 Conciliacion Depositos: Resultado esperado=true (aparece el tooltip)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Conciliación depósitos transporte de valores");
		ConciliacionDepositosProcess conciliacion=new ConciliacionDepositosProcess();
		conciliacion.escribirFechaDesde("!", null);
		boolean hayErrorValidacion=conciliacion.hayErrorValidacionFormatoFechaDesde();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
			logger.info("TC4 Conciliacion Depositos: Debería haber aparecido el tooltip");
		}
		logger.info("TC4 Conciliacion Depositos: Resultado obtenido:"+hayErrorValidacion);
		assertTrue(true);
	}
	
	@Test
	public void testConciliacionDepositos06() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6 Conciliacion Depositos");
		logger.info("TC6 Conciliacion Depositos: Probar la validación de la existencia de la hora Desde");
		logger.info("TC6 Conciliacion Depositos: Resultado esperado=true (aparece el tooltip)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Conciliación depósitos transporte de valores");
		ConciliacionDepositosProcess conciliacion=new ConciliacionDepositosProcess();
		conciliacion.borrarHoraDesde();
		boolean hayErrorValidacion=conciliacion.hayErrorValidacionRequeridoHoraDesde();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
			logger.info("TC6 Conciliacion Depositos: Debería haber aparecido el tooltip");
		}
		logger.info("TC6 Conciliacion Depositos: Resultado obtenido:"+hayErrorValidacion);
		assertTrue(true);
	}
	
	@Test
	public void testConciliacionDepositos07() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC7 Conciliacion Depositos");
		logger.info("TC7 Conciliacion Depositos: Probar la validación del formato de la hora Desde");
		logger.info("TC7 Conciliacion Depositos: Resultado esperado=true (aparece el tooltip)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Conciliación depósitos transporte de valores");
		ConciliacionDepositosProcess conciliacion=new ConciliacionDepositosProcess();
		conciliacion.escribirFechaDesde(null,"!");
		boolean hayErrorValidacion=conciliacion.hayErrorValidacionFormatoHoraDesde();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
			logger.info("TC7 Conciliacion Depositos: Debería haber aparecido el tooltip");
		}
		logger.info("TC7 Conciliacion Depositos: Resultado obtenido:"+hayErrorValidacion);
		assertTrue(true);
	}
	
	@Test
	public void testConciliacionDepositos08() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC8 Conciliacion Depositos");
		logger.info("TC8 Conciliacion Depositos: Probar la validación de la existencia de la fecha Hasta");
		logger.info("TC8 Conciliacion Depositos: Resultado esperado=true (aparece el tooltip)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Conciliación depósitos transporte de valores");
		ConciliacionDepositosProcess conciliacion=new ConciliacionDepositosProcess();
		conciliacion.borrarFechaHasta();
		boolean hayErrorValidacion=conciliacion.hayErrorValidacionRequeridoFechaHasta();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
			logger.info("TC8 Conciliacion Depositos: Debería haber aparecido el tooltip");
		}
		logger.info("TC8 Conciliacion Depositos: Resultado obtenido:"+hayErrorValidacion);
		assertTrue(true);
	}
	
	@Test
	public void testConciliacionDepositos09() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC9 Conciliacion Depositos");
		logger.info("TC9 Conciliacion Depositos: Probar la validación del formato de la fecha Hasta");
		logger.info("TC9 Conciliacion Depositos: Resultado esperado=true (aparece el tooltip)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Conciliación depósitos transporte de valores");
		ConciliacionDepositosProcess conciliacion=new ConciliacionDepositosProcess();
		conciliacion.escribirFechaHasta("!", null);
		boolean hayErrorValidacion=conciliacion.hayErrorValidacionFormatoFechaHasta();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
			logger.info("TC9 Conciliacion Depositos: Debería haber aparecido el tooltip");
		}
		logger.info("TC9 Conciliacion Depositos: Resultado obtenido:"+hayErrorValidacion);
		assertTrue(true);
	}
	
	@Test
	public void testConciliacionDepositos10() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC10 Conciliacion Depositos");
		logger.info("TC10 Conciliacion Depositos: Probar la validación de la existencia de la hora Hasta");
		logger.info("TC10 Conciliacion Depositos: Resultado esperado=true (aparece el tooltip)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Conciliación depósitos transporte de valores");
		ConciliacionDepositosProcess conciliacion=new ConciliacionDepositosProcess();
		conciliacion.borrarHoraHasta();
		boolean hayErrorValidacion=conciliacion.hayErrorValidacionRequeridoHoraHasta();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
			logger.info("TC10 Conciliacion Depositos: Debería haber aparecido el tooltip");
		}
		logger.info("TC10 Conciliacion Depositos: Resultado obtenido:"+hayErrorValidacion);
		assertTrue(true);
	}
	
	@Test
	public void testConciliacionDepositos11() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC11 Conciliacion Depositos");
		logger.info("TC11 Conciliacion Depositos: Probar la validación del formato de la hora Hasta");
		logger.info("TC11 Conciliacion Depositos: Resultado esperado=true (aparece el tooltip)");
		botest.bo.seleccionarOpcionMenu("Informes","Informes de gestión","Conciliación depósitos transporte de valores");
		ConciliacionDepositosProcess conciliacion=new ConciliacionDepositosProcess();
		conciliacion.escribirFechaHasta(null,"!");
		boolean hayErrorValidacion=conciliacion.hayErrorValidacionFormatoHoraHasta();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
			logger.info("TC11 Conciliacion Depositos: Debería haber aparecido el tooltip");
		}
		logger.info("TC11 Conciliacion Depositos: Resultado obtenido:"+hayErrorValidacion);
		assertTrue(true);
	}
}
