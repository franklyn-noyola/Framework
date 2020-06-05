package testCasesITATAHost.ConfiguracionSistema.TarifasMoneda.Calendario;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.TarifasMoneda.Calendario.CalendarioProcess;
import procesosITATAHost.ConfiguracionSistema.TarifasMoneda.Calendario.CrearModificarCalendarioProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.ErrorMessage;

public class CrearCalendarioTest {

	final String[] botones= {"Confirmar","Cancelar"};
	final String[] labels= {"Tipo de día"};
	final String[][] labelsrango={{null,"Desde","Hasta"}};
	final String msg_error_calendario_solapado="El rango de fechas introducido se solapa con fechas ya definidas en el calendario."
									+ " Rangos definidos con los que solapa:";
	final String msg_error_fechas_reves="Fecha 'Hasta' no puede ser anterior a 'Desde'";
	final String msg_error_fecha_pasada="Por favor, introduzca una fecha futura";
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(CrearCalendarioTest.class);
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
	public void testCrearCalendario1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Crear Calendario");
		logger.info("TC1 Crear Calendario: Probar el análisis sintáctico correcto");
		logger.info("TC1 Crear Calendario: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Tarifas & Moneda","Calendario");
		CalendarioProcess calendario=new CalendarioProcess();
		calendario.crear();
		CrearModificarCalendarioProcess crearCalendario=new CrearModificarCalendarioProcess();
		boolean analisis=crearCalendario.sintacticAnalysis(botones, labels, labelsrango);
		if (analisis) {
			logger.info("TC1 Crear Calendario: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Crear Calendario: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Crear Calendario: Resultado obtenido="+analisis);
		crearCalendario.cancelar();
		calendario.volver();
		assertTrue(analisis);
	}
	
	@Test
	public void testCrearCalendario2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Crear Calendario");
		logger.info("TC2 Crear Calendario: Crear un calendario con fechas que no se solapen con ninguna existente");
		logger.info("TC2 Crear Calendario: Resultado esperado: true (operación correcta, comprobación de aparición en tabla)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Categorías");
		CalendarioProcess calendario=new CalendarioProcess();
		calendario.crear();
		CrearModificarCalendarioProcess crearCalendario=new CrearModificarCalendarioProcess();
		String[] datosEntrada= {"20/09/2018","29/09/2018","D: Bank holidays"};
		crearCalendario.escribirFechaDesde(datosEntrada[0]);
		crearCalendario.clicarCheckDesdeHasta();
		crearCalendario.escribirFechaHasta(datosEntrada[1]);
		crearCalendario.seleccionarOpcionTipoDia(datosEntrada[2]);
		crearCalendario.confirmar();
		calendario.recargarTabla();
		String[] columnas= {"Desde","Hasta","Tipo de día"};
		String[] resultado=new String[calendario.numColumnasTabla()];
		resultado=calendario.obtenerFilaTabla(columnas, datosEntrada);
		boolean iguales=RutinasTestComunes.assertArrayEquals(datosEntrada,resultado);
		if (!iguales) {
			LoginTest.majors++;
		}
		logger.info("TC2 Crear Calendario: Resultado obtenido:"+iguales);
		calendario.volver();
		assertTrue(iguales);
	}
	
	@Test
	public void testCrearCalendario3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Crear Calendario");
		logger.info("TC3 Crear Calendario: Crear un calendario con fechas que se solapan al principio del rango con alguna existente");
		logger.info("TC3 Crear Calendario: Resultado esperado: true (aparece un mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Categorías");
		CalendarioProcess calendario=new CalendarioProcess();
		calendario.crear();
		CrearModificarCalendarioProcess crearCalendario=new CrearModificarCalendarioProcess();
		String[] datosEntrada= {"24/09/2018","30/09/2018","D: Bank holidays"};
		crearCalendario.escribirFechaDesde(datosEntrada[0]);
		crearCalendario.clicarCheckDesdeHasta();
		crearCalendario.escribirFechaHasta(datosEntrada[1]);
		crearCalendario.seleccionarOpcionTipoDia(datosEntrada[2]);
		crearCalendario.confirmar();
		boolean hayMsgError=calendario.hayMensajeError();
		boolean analisis=false;
		String msg=null;
		if (hayMsgError) {
			ErrorMessage errormsgobt=calendario.mensajeError();
			//msg=msg_error_calendario_solapado+datosEntrada[0]+"-"+datosEntrada[1];
			msg=errormsgobt.getText();  // OJO: Linea temporal
			analisis=errormsgobt.sintacticAnalysis(msg);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC3 Crear Calendario: No ha aparecido el mensaje de error '"+msg+"'");
			LoginTest.majors++;
		}
		logger.info("TC3 Crear Calendario: Resultado obtenido:"+(hayMsgError && analisis));
		crearCalendario.cancelar();
		calendario.volver();
		assertTrue((hayMsgError && analisis));
	}
	
	@Test
	public void testCrearCalendario4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Crear Calendario");
		logger.info("TC4 Crear Calendario: Crear un calendario con fechas que se solapan completemanete con alguna existente");
		logger.info("TC4 Crear Calendario: Resultado esperado: true (aparece un mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Categorías");
		CalendarioProcess calendario=new CalendarioProcess();
		calendario.crear();
		CrearModificarCalendarioProcess crearCalendario=new CrearModificarCalendarioProcess();
		String[] datosEntrada= {"21/09/2018","22/09/2018","D: Bank holidays"};
		crearCalendario.escribirFechaDesde(datosEntrada[0]);
		crearCalendario.clicarCheckDesdeHasta();
		crearCalendario.escribirFechaHasta(datosEntrada[1]);
		crearCalendario.seleccionarOpcionTipoDia(datosEntrada[2]);
		crearCalendario.confirmar();
		boolean hayMsgError=calendario.hayMensajeError();
		boolean analisis=false;
		String msg=null;
		if (hayMsgError) {
			ErrorMessage errormsgobt=calendario.mensajeError();
			//msg=msg_error_calendario_solapado+datosEntrada[0]+"-"+datosEntrada[1];
			msg=errormsgobt.getText();  // OJO: Linea temporal
			analisis=errormsgobt.sintacticAnalysis(msg);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC4 Crear Calendario: No ha aparecido el mensaje de error '"+msg+"'");
			LoginTest.majors++;
		}
		logger.info("TC4 Crear Calendario: Resultado obtenido:"+(hayMsgError && analisis));
		crearCalendario.cancelar();
		calendario.volver();
		assertTrue((hayMsgError && analisis));
	}
	
	@Test
	public void testCrearCalendario5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Crear Calendario");
		logger.info("TC5 Crear Calendario: Crear un calendario con la fecha Desde posterior a la fecha Hasta");
		logger.info("TC5 Crear Calendario: Resultado esperado: true (aparece un mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Categorías");
		CalendarioProcess calendario=new CalendarioProcess();
		calendario.crear();
		CrearModificarCalendarioProcess crearCalendario=new CrearModificarCalendarioProcess();
		String[] datosEntrada= {"22/10/2018","21/10/2018","D: Bank holidays"};
		crearCalendario.escribirFechaDesde(datosEntrada[0]);
		crearCalendario.clicarCheckDesdeHasta();
		crearCalendario.escribirFechaHasta(datosEntrada[1]);
		crearCalendario.seleccionarOpcionTipoDia(datosEntrada[2]);
		crearCalendario.confirmar();
		boolean hayMsgError=calendario.hayMensajeError();
		boolean analisis=false;
		String msg=null;
		if (hayMsgError) {
			ErrorMessage errormsgobt=calendario.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_fechas_reves);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC5 Crear Calendario: No ha aparecido el mensaje de error '"+msg+"'");
			LoginTest.majors++;
		}
		logger.info("TC5 Crear Calendario: Resultado obtenido:"+(hayMsgError && analisis));
		crearCalendario.cancelar();
		calendario.volver();
		assertTrue((hayMsgError && analisis));
	}
	
	@Test
	public void testCrearCalendario6() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6 Crear Calendario");
		logger.info("TC6 Crear Calendario: Crear un calendario con la fecha Desde anterior a la fecha actual y la fecha Hasta futura");
		logger.info("TC6 Crear Calendario: Resultado esperado: true (aparece un mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Categorías");
		CalendarioProcess calendario=new CalendarioProcess();
		calendario.crear();
		CrearModificarCalendarioProcess crearCalendario=new CrearModificarCalendarioProcess();
		String[] datosEntrada= {"22/10/2018","21/10/2018","D: Bank holidays"};
		crearCalendario.escribirFechaDesde(datosEntrada[0]);
		crearCalendario.clicarCheckDesdeHasta();
		crearCalendario.escribirFechaHasta(datosEntrada[1]);
		crearCalendario.seleccionarOpcionTipoDia(datosEntrada[2]);
		crearCalendario.confirmar();
		boolean hayMsgError=calendario.hayMensajeError();
		boolean analisis=false;
		String msg=null;
		if (hayMsgError) {
			ErrorMessage errormsgobt=calendario.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_fecha_pasada);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC6 Crear Calendario: No ha aparecido el mensaje de error '"+msg+"'");
			LoginTest.majors++;
		}
		logger.info("TC6 Crear Calendario: Resultado obtenido:"+(hayMsgError && analisis));
		crearCalendario.cancelar();
		calendario.volver();
		assertTrue((hayMsgError && analisis));
	}
	
	@Test
	public void testCrearCalendario7() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC7 Crear Calendario");
		logger.info("TC7 Crear Calendario: Crear un calendario con las fechas Desde y Hasta anteriores a la fecha actual");
		logger.info("TC7 Crear Calendario: Resultado esperado: true (aparece un mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Categorías");
		CalendarioProcess calendario=new CalendarioProcess();
		calendario.crear();
		CrearModificarCalendarioProcess crearCalendario=new CrearModificarCalendarioProcess();
		String[] datosEntrada= {"22/10/2018","21/10/2018","D: Bank holidays"};
		crearCalendario.escribirFechaDesde(datosEntrada[0]);
		crearCalendario.clicarCheckDesdeHasta();
		crearCalendario.escribirFechaHasta(datosEntrada[1]);
		crearCalendario.seleccionarOpcionTipoDia(datosEntrada[2]);
		crearCalendario.confirmar();
		boolean hayMsgError=calendario.hayMensajeError();
		boolean analisis=false;
		String msg=null;
		if (hayMsgError) {
			ErrorMessage errormsgobt=calendario.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_fecha_pasada);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC7 Crear Calendario: No ha aparecido el mensaje de error '"+msg+"'");
			LoginTest.majors++;
		}
		logger.info("TC7 Crear Calendario: Resultado obtenido:"+(hayMsgError && analisis));
		crearCalendario.cancelar();
		calendario.volver();
		assertTrue((hayMsgError && analisis));
	}
}
