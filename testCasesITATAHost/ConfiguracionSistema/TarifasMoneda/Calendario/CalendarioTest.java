package testCasesITATAHost.ConfiguracionSistema.TarifasMoneda.Calendario;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.TarifasMoneda.Calendario.CalendarioProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import unidadesGraficas.AlertPopup;
import unidadesGraficas.ErrorMessage;

public class CalendarioTest {

	final static String titulo="Calendario"; 
	final static String subtitulo="Configuraci�n sistema   -   Tarifas & Moneda   -   Calendario"; 
	final static String[] botones= {"Informe","Enviar a v�as","Volver"};
	final static String[] botonescrud= {"Desde","Hasta","Tipo de d�a"};
	final static String[] columnasgrid= {"Concesionaria","Tipo de moneda","Denominaci�n"};
	final static String[] botonesgrid={"<2017","2019>"};
	final static String msg_error_noseleccionado="Ning�n elemento seleccionado";
	final static String msg_error_borradofutura="Solo se pueden eliminar d�as futuros";
	final static String alerta_confirmacion_borrado="�Est� seguro de que desea eliminar el d�a seleccionado?";
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(CalendarioTest.class);
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
	public void testCalendario1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Calendario");
		logger.info("TC1 Calendario: Probar el an�lisis sint�ctico correcto");
		logger.info("TC1 Calendario: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Tarifas & Moneda","Calendario");
		CalendarioProcess calendario=new CalendarioProcess();
		boolean analisis=calendario.sintacticAnalysis(titulo,subtitulo, botones,null,botonescrud,columnasgrid,botonesgrid);
		if (analisis) {
			logger.info("TC1 Calendario: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC1 Calendario: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Calendario: Resultado obtenido="+analisis);
		calendario.volver();
		assertTrue(analisis);
	}
	
	//@Test
	public void testCalendario2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Calendario");
		logger.info("TC2 Calendario: Probar la eliminaci�n incorrecta sin seleccionar nodo");
		logger.info("TC2 Calendario: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Tarifas & Moneda","Calendario");
		CalendarioProcess calendario=new CalendarioProcess();
		calendario.eliminar();
		boolean analisis=false;
		boolean hayMsgError=calendario.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=calendario.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC2 Calendario: Deber�a haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC2 Calendario: Resultado obtenido="+(hayMsgError && analisis));
		calendario.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	
	//@Test
	public void testCalendario3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Calendario");
		logger.info("TC3 Calendario: Probar la eliminaci�n correcta de un nodo");
		logger.info("TC3 Calendario: Resultado esperado: true (aparece alerta de confirmaci�n)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Tarifas & Moneda","Calendario");
		CalendarioProcess calendario=new CalendarioProcess();
		calendario.seleccionarFila(1);
		calendario.eliminar();
		boolean analisis=false;
		boolean hayAlerta=calendario.hayAlerta();
		if (hayAlerta) {
			AlertPopup alerta=calendario.alerta();
			analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
			if (!analisis) {
				LoginTest.minors++;
			}
			calendario.cancelarAlerta();
		}
		logger.info("TC3 Calendario: Resultado obtenido: "+(hayAlerta && analisis));
		calendario.volver();
		assertTrue(hayAlerta && analisis);
	}


}
