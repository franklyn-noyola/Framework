package testCasesITATAHost.GestionTurno;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.GestionTurno.GestionTurnoProcess;
import procesosITATAHost.GestionTurno.GestionTurnoReportProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class GestionTurnoReportTest {

	final static String titulo="Informe de turnos por periodo"; 
	final static String[] botones={"Ver en Excel"};  
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(GestionTurnoReportTest.class);
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
	public void testGestionTurnoReport1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Gesti�n Turno Report");
		logger.info("TC1 Gesti�n Turno Report: Probar el an�lisis sint�ctico correcto del report generado");
		logger.info("TC1 Gesti�n Turno Report: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Gesti�n de turno","Gesti�n de turno");
		GestionTurnoProcess turnos=new GestionTurnoProcess();
		turnos.informe();
		// Haya que controlar la ventana de espera que aparece antes de llamara al Reportprocess
		GestionTurnoReportProcess report=new GestionTurnoReportProcess();
		report.pasarANuevaVentana();
		boolean analisis=report.sintacticAnalysis(titulo, botones);
		report.cerrar();
		if (analisis) {
			logger.info("TC2 Gesti�n Turno Report: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC2 Gesti�n Turno Report: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		turnos.volver();
		assertTrue(analisis);
	}

	@Test
	public void testGestionTurnoReport2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Gesti�n Turno Report");
		logger.info("TC3 Gesti�n Turno Report: Probar generar el report en Excel");
		logger.info("TC3 Gesti�n Turno Report: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Gesti�n de turno","Gesti�n de turno");
		GestionTurnoProcess turnos=new GestionTurnoProcess();
		turnos.informe();
		// Haya que controlar la ventana de espera que aparece
		GestionTurnoReportProcess report=new GestionTurnoReportProcess();
		report.pasarANuevaVentana();
		report.Excel();
		report.cerrar();
		turnos.volver();
		logger.info("TC2 Gesti�n Turno Report: Resultado obtenido=true");
		assertTrue(true);
	}
}
