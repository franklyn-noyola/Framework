package testCasesITATAHost.General;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.General.CambiarContrase�a.CambiarContrase�aProcess;
import procesosITATAHost.General.CambiarContrase�a.OpcionesCambiarContrase�a;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;


public class CambiarContrase�aTest {

	final static String titulo_cambiar_contrase�a="Cambiar contrase�a"; 
	final static String subtitulo_cambiar_contrase�a="General   -   Cambiar contrase�a"; 
	final static String[] botones_cambiar_contrase�a={"Confirmar","Volver"}; 
	final static String encabezado_cambiar_contrase�a="Contrase�a de usuario";
	final static String[] labels_cambiar_contrase�a= {"Nueva Contrase�a","Repetir contrase�a","Operador"};
	final static String msgerror_cambiar_contrase�a="OK: Contrase�a modificadaA"; 
	
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(CambiarContrase�aTest.class);
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
	public void testCambiarContrase�a1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Cambiar Contrase�a");
		logger.info("TC1 Cambiar Contrase�a: probar el an�lisis sint�ctico correcto");
		logger.info("TC1: Cambiar Contrase�a: resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("General","Cambiar contrase�a");
		CambiarContrase�aProcess cambiarContrase�a=new CambiarContrase�aProcess();
		boolean analisis=cambiarContrase�a.sintacticAnalysis(titulo_cambiar_contrase�a, subtitulo_cambiar_contrase�a, botones_cambiar_contrase�a, encabezado_cambiar_contrase�a, labels_cambiar_contrase�a);
		if (analisis) {
			logger.info("TC1: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC1: : an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		assertTrue(analisis);
	}

	@Test
	public void testCambiarContrase�a2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 CambiarContrase�a");
		logger.info("TC2 Cambiar Contrase�a: probar el cambio correcto de contrase�a");
		logger.info("TC2: Cambiar Contrase�a: resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("General","Cambiar contrase�a");
		CambiarContrase�aProcess cambiarContrase�a=new CambiarContrase�aProcess();
		String usuario="00001";
		String nuevoPass="12456a$A";
		cambiarContrase�a.seleccionarOpcionOperador("00001 - Super Admin");
		cambiarContrase�a.escribirOpcion(OpcionesCambiarContrase�a.NuevaContrase�a,nuevoPass);
		cambiarContrase�a.escribirOpcion(OpcionesCambiarContrase�a.RepetirContrase�a,nuevoPass);
		cambiarContrase�a.confirmar();
		boolean analisis=false;
		if (cambiarContrase�a.hayMensajeError()) {
			analisis=cambiarContrase�a.mensajeError().sintacticAnalysis(msgerror_cambiar_contrase�a);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		botest.bo.logout();
		botest.bo.hayAlerta();
		botest.bo.aceptarAlerta();
		LoginTest newlogin=new LoginTest();
		LoginTest.user=usuario;
		LoginTest.password=nuevoPass;
		newlogin.testLogin1();
		assertTrue(analisis);
	}
}
