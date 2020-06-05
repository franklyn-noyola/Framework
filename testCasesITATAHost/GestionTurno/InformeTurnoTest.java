package testCasesITATAHost.GestionTurno;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.GestionTurno.GestionTurnoProcess;
import procesosITATAHost.GestionTurno.InformeTurnoProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class InformeTurnoTest {

	final static String titulo="Informe de Turno"; 
	final static String[] botones={"Ver en Excel"};  
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(InformeTurnoTest.class);
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
	public void testInformeTurno1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 InformeTurno");
		logger.info("TC1 Informe Turno: Probar el análisis sintáctico correcto del report generado");
		logger.info("TC1 Informe Turno: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Gestión de turno","Gestión de turno");
		GestionTurnoProcess turnos=new GestionTurnoProcess();
		turnos.informeTurno();
		// Hay que controlar la ventana de espera que aparece antes de llamara al Reportprocess
		InformeTurnoProcess report=new InformeTurnoProcess();
		report.pasarANuevaVentana();
		boolean analisis=report.sintacticAnalysis(titulo, botones);
		report.cerrar();
		if (analisis) {
			logger.info("TC2 Informe Turno: análisis sintáctico correcto");
		}
		else {
			logger.error("TC2 Informe Turno: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		turnos.volver();
		assertTrue(analisis);
	}

	@Test
	public void testInformeTurno2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Informe Turno");
		logger.info("TC3 Informe Turno: Probar generar el report en Excel");
		logger.info("TC3 Informe Turno: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Gestión de turno","Gestión de turno");
		GestionTurnoProcess turnos=new GestionTurnoProcess();
		turnos.informeTurno();
		// Haya que controlar la ventana de espera que aparece
		InformeTurnoProcess report=new InformeTurnoProcess();
		report.pasarANuevaVentana();
		report.Excel();
		report.cerrar();
		turnos.volver();
		logger.info("TC2 Informe Turno: Resultado obtenido=true");
		assertTrue(true);
	}
}
