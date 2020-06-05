package testCasesITATAHost.General;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.General.CambiarIdioma.CambiarIdiomaProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import unidadesGraficas.ErrorMessage;


public class CambiarIdiomaTest {

	final static String titulo_cambiar_idioma="Gestión de operadores: Cambiar idioma"; 
	final static String subtitulo_cambiar_idioma="General   -   Cambiar idioma"; 
	final static String[] botones_cambiar_idioma={"Confirmar","Volver"}; 
	final static String encabezado_cambiar_idioma=null;
	final static String[] labels_cambiar_idioma= {"Idioma"};
	final static String msg_cambioidioma="Debe cerrar la sesión para aplicar los cambios.A";
	
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(CambiarIdiomaTest.class);
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
	public void testCambiarIdioma1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Cambiar idioma: Análisis sintáctico correcto");
		logger.info("TC1 Cambiar idioma: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("General","Cambiar idioma");
		CambiarIdiomaProcess cambiarIdioma=new CambiarIdiomaProcess();
		cambiarIdioma.seleccionarOpcionIdioma("French (France)");
		cambiarIdioma.seleccionarOpcionIdioma("Spanish (Spain)");
		cambiarIdioma.confirmar();
		boolean analisis=false;
		boolean hayerror=cambiarIdioma.hayMensajeError();
		if (hayerror) {
			ErrorMessage errormsgobt=cambiarIdioma.mensajeError();
			analisis=cambiarIdioma.sintacticAnalysis(titulo_cambiar_idioma, 
				subtitulo_cambiar_idioma, botones_cambiar_idioma, encabezado_cambiar_idioma,
				labels_cambiar_idioma) && errormsgobt.sintacticAnalysis(msg_cambioidioma);
			if (analisis) {
				logger.info("TC1 Cambiar idioma:: análisis sintáctico correcto");
			}
			else {
				logger.error("TC1 Cambiar idioma:: análisis sintáctico incorrecto");
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC1 Cambiar idioma:: Debería haber aparecido el mensaje de advertencia: '"+msg_cambioidioma+"'");
			LoginTest.minors++;
		}
		assertTrue(hayerror || analisis);
	}
}
