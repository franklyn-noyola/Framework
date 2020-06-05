package testCasesITATAHost.General;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.General.CambiarContraseña.CambiarContraseñaProcess;
import procesosITATAHost.General.CambiarContraseña.OpcionesCambiarContraseña;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;


public class CambiarContraseñaTest {

	final static String titulo_cambiar_contraseña="Cambiar contraseña"; 
	final static String subtitulo_cambiar_contraseña="General   -   Cambiar contraseña"; 
	final static String[] botones_cambiar_contraseña={"Confirmar","Volver"}; 
	final static String encabezado_cambiar_contraseña="Contraseña de usuario";
	final static String[] labels_cambiar_contraseña= {"Nueva Contraseña","Repetir contraseña","Operador"};
	final static String msgerror_cambiar_contraseña="OK: Contraseña modificadaA"; 
	
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(CambiarContraseñaTest.class);
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
	public void testCambiarContraseña1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Cambiar Contraseña");
		logger.info("TC1 Cambiar Contraseña: probar el análisis sintáctico correcto");
		logger.info("TC1: Cambiar Contraseña: resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("General","Cambiar contraseña");
		CambiarContraseñaProcess cambiarContraseña=new CambiarContraseñaProcess();
		boolean analisis=cambiarContraseña.sintacticAnalysis(titulo_cambiar_contraseña, subtitulo_cambiar_contraseña, botones_cambiar_contraseña, encabezado_cambiar_contraseña, labels_cambiar_contraseña);
		if (analisis) {
			logger.info("TC1: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1: : análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		assertTrue(analisis);
	}

	@Test
	public void testCambiarContraseña2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 CambiarContraseña");
		logger.info("TC2 Cambiar Contraseña: probar el cambio correcto de contraseña");
		logger.info("TC2: Cambiar Contraseña: resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("General","Cambiar contraseña");
		CambiarContraseñaProcess cambiarContraseña=new CambiarContraseñaProcess();
		String usuario="00001";
		String nuevoPass="12456a$A";
		cambiarContraseña.seleccionarOpcionOperador("00001 - Super Admin");
		cambiarContraseña.escribirOpcion(OpcionesCambiarContraseña.NuevaContraseña,nuevoPass);
		cambiarContraseña.escribirOpcion(OpcionesCambiarContraseña.RepetirContraseña,nuevoPass);
		cambiarContraseña.confirmar();
		boolean analisis=false;
		if (cambiarContraseña.hayMensajeError()) {
			analisis=cambiarContraseña.mensajeError().sintacticAnalysis(msgerror_cambiar_contraseña);
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
