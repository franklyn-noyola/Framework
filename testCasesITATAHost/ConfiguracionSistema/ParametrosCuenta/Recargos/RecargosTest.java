package testCasesITATAHost.ConfiguracionSistema.ParametrosCuenta.Recargos;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ParametrosCuenta.Recargos.RecargosProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import unidadesGraficas.AlertPopup;
import unidadesGraficas.ErrorMessage;

public class RecargosTest {

	final static String titulo="Gesti�n de recargos"; 
	final static String subtitulo="Configuraci�n sistema   -   Par�metros de cuenta   -   Recargos"; 
	final static String[] botones= {"Editar orden","Volver"};
	final static String[] botonescrud= {"Crear","Modificar","Eliminar","Subir","Bajar"};
	final static String[] columnasgrid= {"Nombre","Descripci�n","Tipo","Tipo de aplicaci�n","Valor"};
	final static String[] botonesgrid={"Primera","<",">","�ltima"};
	final static String msg_error_noseleccionado="Ning�n elemento seleccionado";
	final static String alerta_confirmacion_borrado="�Est� seguro de que desea eliminar el recargo seleccionado?";
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(RecargosTest.class);
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
	public void testRecargos1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Recargos");
		logger.info("TC1 Recargos: Probar el an�lisis sint�ctico correcto");
		logger.info("TC1 Recargos: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Par�metros de cuenta","Recargos");
		RecargosProcess recargos=new RecargosProcess();
		boolean analisis=recargos.sintacticAnalysis(titulo,subtitulo, botones,null,botonescrud,columnasgrid,botonesgrid);
		if (analisis) {
			logger.info("TC1 Recargos: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC1 Recargos: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Recargos: Resultado obtenido="+analisis);
		recargos.volver();
		assertTrue(analisis);
	}
	
	@Test
	public void testRecargos2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Recargos");
		logger.info("TC2 Recargos: Probar la eliminaci�n incorrecta sin seleccionar mapeo");
		logger.info("TC2 Recargos: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Par�metros de cuenta","Recargos");
		RecargosProcess recargos=new RecargosProcess();
		recargos.eliminar();
		boolean analisis=false;
		boolean hayMsgError=recargos.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=recargos.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC2 Recargos: Deber�a haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC2 Recargos: Resultado obtenido="+(hayMsgError && analisis));
		recargos.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	
	@Test
	public void testRecargos3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Recargos");
		logger.info("TC3 Recargos: Probar la eliminaci�n correcta de un mapeo con cancelaci�n");
		logger.info("TC3 Recargos: Resultado esperado: true (aparece alerta de confirmaci�n)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Par�metros de cuenta","Recargos");
		RecargosProcess recargos=new RecargosProcess();
		recargos.seleccionarFila(1);
		recargos.eliminar();
		boolean analisis=false;
		boolean hayAlerta=recargos.hayAlerta();
		if (hayAlerta) {
			AlertPopup alerta=recargos.alerta();
			analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
			if (!analisis) {
				LoginTest.minors++;
			}
			recargos.cancelarAlerta();
		}
		logger.info("TC3 Recargos: Resultado obtenido: "+(hayAlerta && analisis));
		recargos.volver();
		assertTrue(hayAlerta && analisis);
	}


}
