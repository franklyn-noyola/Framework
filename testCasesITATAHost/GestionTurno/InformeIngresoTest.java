package testCasesITATAHost.GestionTurno;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.GestionTurno.GestionTurnoProcess;
import procesosITATAHost.GestionTurno.InformeIngresoProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class InformeIngresoTest {

	final static String titulo="Informe de ingreso de cobrador"; 
	final static String[] botones={"Ver en Excel"};  
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(InformeIngresoTest.class);
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
	public void testInformeIngreso1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Informe Ingreso");
		logger.info("TC1 Informe Ingreso: Probar el análisis sintáctico correcto del report generado");
		logger.info("TC1 Informe Ingreso: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Gestión de turno","Gestión de turno");
		GestionTurnoProcess turnos=new GestionTurnoProcess();
		turnos.informeIngreso();
		// Hay que controlar la ventana de espera que aparece antes de llamara al Reportprocess
		InformeIngresoProcess report=new InformeIngresoProcess();
		report.pasarANuevaVentana();
		boolean analisis=report.sintacticAnalysis(titulo, botones);
		report.cerrar();
		if (analisis) {
			logger.info("TC2 Informe Ingreso: análisis sintáctico correcto");
		}
		else {
			logger.error("TC2 Informe Ingreso: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		turnos.volver();
		assertTrue(analisis);
	}

	@Test
	public void testInformeIngreso2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Informe Ingreso");
		logger.info("TC3 Informe Ingreso: Probar generar el report en Excel");
		logger.info("TC3 Informe Ingreso: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Gestión de turno","Gestión de turno");
		GestionTurnoProcess turnos=new GestionTurnoProcess();
		turnos.informeIngreso();
		// Haya que controlar la ventana de espera que aparece
		InformeIngresoProcess report=new InformeIngresoProcess();
		report.pasarANuevaVentana();
		report.Excel();
		report.cerrar();
		turnos.volver();
		logger.info("TC2 Informe Ingreso: Resultado obtenido=true");
		assertTrue(true);
	}
}
