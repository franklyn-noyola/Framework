package testCasesITATAHost.ConfiguracionSistema.Operadores.GestionOperadores;

import static org.junit.Assert.*;
import procesosComunes.Error;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.CrearOperadorProcess;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.GestionOperadoresProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;


public class CrearOperadorTest {

	final static String titulo_crear_operador="Gesti�n de operadores: Creaci�n de operador"; 
	final static String subtitulo_crear_operador="Configuraci�n sistema   -   Operadores   -   Gesti�n de operadores   -   Crear"; 
	final static String encabezadocrud_crear_operador1="Informaci�n de Operador";
	final static String[] botones_crear_operador= {"Confirmar","Volver"};
	final static String[] labels_crear_operador1= {"Nombre","Apellidos","Direcci�n","C.P.","Ciudad",
			"Pa�s","E-mail","Tel�fono","Concesionaria","T�tulo","G�nero","Grupo Op.","Fecha nacimiento"};
	final static String encabezadocrud_crear_operador2="Definici�n de contrase�a";
	final static String[] labels_crear_operador2={"Contrase�a","Repita la contrase�a"};
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(CrearOperadorTest.class);
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
	public void testCrearOperador1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1: Crear Operador: an�lisis sint�ctico");
		logger.info("TC1: Crear Operador: resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Operadores","Gesti�n de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.crear();
		CrearOperadorProcess crearOperador=new CrearOperadorProcess();
		boolean analisis=crearOperador.sintacticAnalysis(titulo_crear_operador, subtitulo_crear_operador,
				botones_crear_operador, encabezadocrud_crear_operador1, labels_crear_operador1,
				encabezadocrud_crear_operador2, labels_crear_operador2);
		if (analisis) {
			logger.info("P�gina Crear operador: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("P�gina Crear operador: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1: Crear Operador: resultado obtenido="+analisis);
		assertTrue(analisis);
	}
	
	@Test
	public void testCrearOperador2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2: Crear Operador: creaci�n del primer operador");
		logger.info("TC2: Crear Operador: resultado esperado=false (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Operadores","Gesti�n de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.crear();
		CrearOperadorProcess crearOperador=new CrearOperadorProcess();
		crearOperador.escribirCampoNombre("Pepe2");
		crearOperador.escribirCampoApellidos("P�rez2");
		crearOperador.escribirCampoDireccion("Diagonal 234, 3� 3�");
		crearOperador.escribirCampoCP("08023");
		crearOperador.escribirCampoCiudad("Barcelona");
		crearOperador.escribirCampoEmail("pepe3@0x.com");
		crearOperador.escribirCampoPais("Espa�a");
		crearOperador.escribirCampoTelefono("6457693");
		crearOperador.escribirCampoContrase�a("1234567890");
		crearOperador.escribirCampoRepitacontrase�a("1234567890");
		Error errorDetectado=crearOperador.seleccionarOpcionGrupoOP("01: Cobradores");
		if (errorDetectado==Error.Error) {
			LoginTest.majors++;
			logger.error("TC2: Crear Operador: opci�n GrupoOP no encontrada en combo");
			fail();
		}
		crearOperador.escribirCampoFecha("03/05/1967");
		crearOperador.confirmar();
		boolean hayMsgError=crearOperador.hayMensajeError();
		if (hayMsgError) {
			LoginTest.minors++;
		}
		logger.info("TC2: Crear Operador: resultado obtenido="+hayMsgError);
		assertFalse(hayMsgError);
	}
	
	@Test
	public void testCrearOperador3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3: Crear Operador: creaci�n del mismo operador");
		logger.info("TC3: Crear Operador: resultado esperado=true (operaci�n incorrecta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Operadores","Gesti�n de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.crear();
		CrearOperadorProcess crearOperador=new CrearOperadorProcess();
		crearOperador.escribirCampoNombre("Pepe2");
		crearOperador.escribirCampoApellidos("P�rez2");
		crearOperador.escribirCampoDireccion("Diagonal 234, 3� 3�");
		crearOperador.escribirCampoCP("08023");
		crearOperador.escribirCampoCiudad("Barcelona");
		crearOperador.escribirCampoEmail("pepe3@0x.com");
		crearOperador.escribirCampoPais("Espa�a");
		crearOperador.escribirCampoTelefono("6457693");
		crearOperador.escribirCampoContrase�a("1234567890");
		crearOperador.escribirCampoRepitacontrase�a("1234567890");
		Error errorDetectado=crearOperador.seleccionarOpcionGrupoOP("01: Cobradores");
		if (errorDetectado==Error.Error) {
			LoginTest.majors++;
			logger.error("TC3: Crear Operador: opci�n GrupoOP no encontrada en combo");
			fail();
		}
		crearOperador.confirmar();
		boolean hayMsgError=crearOperador.hayMensajeError();
		if (hayMsgError) {
			logger.debug("TC3: Crear Operador: resultado obtenido="+hayMsgError);
		}
		else {
			LoginTest.minors++;
			logger.error("TC3: Crear Operador: resultado obtenido="+hayMsgError);
		}
		assertFalse(hayMsgError);
	}
	
	@Test
	public void testCrearOperador4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4: Crear Operador: creaci�n de un operador sin repetir contrase�a");
		logger.info("TC4: Crear Operador: resultado esperado=true (operaci�n incorrecta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Operadores","Gesti�n de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.crear();
		CrearOperadorProcess crearOperador=new CrearOperadorProcess();
		crearOperador.escribirCampoNombre("Pepe2");
		crearOperador.escribirCampoApellidos("P�rez2");
		crearOperador.escribirCampoDireccion("Diagonal 234, 3� 3�");
		crearOperador.escribirCampoCP("08023");
		crearOperador.escribirCampoCiudad("Barcelona");
		crearOperador.escribirCampoEmail("pepe3@0x.com");
		crearOperador.escribirCampoPais("Espa�a");
		crearOperador.escribirCampoTelefono("6457693");
		crearOperador.escribirCampoContrase�a("1234567890");
		Error errorDetectado=crearOperador.seleccionarOpcionGrupoOP("01: Cobradores");
		if (errorDetectado==Error.Error) {
			LoginTest.majors++;
			logger.error("TC4: Crear Operador: opci�n GrupoOP no encontrada en combo");
			fail();
		}
		crearOperador.escribirCampoFecha("03/05/1967");
		crearOperador.confirmar();
		boolean hayMsgError=crearOperador.hayMensajeError();
		if (hayMsgError) {
			assertTrue(crearOperador.verMensajeError().equals("Contrase�as diferentes"));
		}
		else {
			LoginTest.minors++;
			fail("Deber�a haber aparecido el mensaje de error 'Contrase�as diferentes'");
		}
		logger.info("TC4: Crear Operador: resultado obtenido="+hayMsgError);
	}

	@Test
	public void testCrearOperador5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5: Crear Operador: validaci�n de que no se introduce el nombre");
		logger.info("TC5: Crear Operador: resultado esperado=true (operaci�n incorrecta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Operadores","Gesti�n de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.crear();
		CrearOperadorProcess crearOperador=new CrearOperadorProcess();
		crearOperador.escribirCampoApellidos("P�rez2");
		crearOperador.escribirCampoDireccion("Diagonal 234, 3� 3�");
		crearOperador.escribirCampoCP("08023");
		crearOperador.escribirCampoCiudad("Barcelona");
		crearOperador.escribirCampoEmail("pepe3@0x.com");
		crearOperador.escribirCampoPais("Espa�a");
		crearOperador.escribirCampoTelefono("6457693");
		crearOperador.escribirCampoContrase�a("1234567890");
		Error errorDetectado=crearOperador.seleccionarOpcionGrupoOP("01: Cobradores");
		if (errorDetectado==Error.Error) {
			LoginTest.majors++;
			logger.error("TC5: Crear Operador: opci�n GrupoOP no encontrada en combo");
			fail();
		}
		crearOperador.escribirCampoFecha("03/05/1967");
		crearOperador.confirmar();
		boolean hayMsgError=crearOperador.hayErrorValidacionNombre();
		if (!hayMsgError){
			LoginTest.minors++;
			logger.error("Deber�a haber aparecido el mensaje de error 'Campo requerido'");
		}
		logger.info("TC5: Crear Operador: resultado obtenido="+hayMsgError);
		assertTrue(hayMsgError);
	}
	
	@Test
	public void testCrearOperador6() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6: Crear Operador: validaci�n de que no se introduce el apellido");
		logger.info("TC6: Crear Operador: resultado esperado=true (operaci�n incorrecta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Operadores","Gesti�n de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.crear();
		CrearOperadorProcess crearOperador=new CrearOperadorProcess();
		crearOperador.escribirCampoNombre("Pepe");
		crearOperador.escribirCampoDireccion("Diagonal 234, 3� 3�");
		crearOperador.escribirCampoCP("08023");
		crearOperador.escribirCampoCiudad("Barcelona");
		crearOperador.escribirCampoEmail("pepe3@0x.com");
		crearOperador.escribirCampoPais("Espa�a");
		crearOperador.escribirCampoTelefono("6457693");
		crearOperador.escribirCampoContrase�a("1234567890");
		Error errorDetectado=crearOperador.seleccionarOpcionGrupoOP("01: Cobradores");
		if (errorDetectado==Error.Error) {
			LoginTest.majors++;
			logger.error("TC6: Crear Operador: opci�n GrupoOP no encontrada en combo");
			fail();
		}
		crearOperador.escribirCampoFecha("03/05/1967");
		crearOperador.confirmar();
		boolean hayMsgError=crearOperador.hayErrorValidacionApellidos();
		if (hayMsgError) {
			assertTrue(true);
		}
		else {
			LoginTest.minors++;
			fail("Deber�a haber aparecido el mensaje de error 'Campo requerido'");
		}
		logger.info("TC6: Crear Operador: resultado obtenido="+hayMsgError);
	}
	
	@Test
	public void testCrearOperador7() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC7: Crear Operador: validaci�n de que no se introduce la contrase�a");
		logger.info("TC7: Crear Operador: resultado esperado=true (operaci�n incorrecta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Operadores","Gesti�n de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.crear();
		CrearOperadorProcess crearOperador=new CrearOperadorProcess();
		crearOperador.escribirCampoNombre("Pepe");
		crearOperador.escribirCampoApellidos("P�rez2");
		crearOperador.escribirCampoDireccion("Diagonal 234, 3� 3�");
		crearOperador.escribirCampoCP("08023");
		crearOperador.escribirCampoCiudad("Barcelona");
		crearOperador.escribirCampoEmail("pepe3@0x.com");
		crearOperador.escribirCampoPais("Espa�a");
		crearOperador.escribirCampoTelefono("6457693");
		Error errorDetectado=crearOperador.seleccionarOpcionGrupoOP("01: Cobradores");
		if (errorDetectado==Error.Error) {
			LoginTest.majors++;
			logger.error("TC7: Crear Operador: opci�n GrupoOP no encontrada en combo");
			fail();
		}
		crearOperador.escribirCampoFecha("03/05/1967");
		crearOperador.confirmar();
		boolean hayMsgError=crearOperador.hayErrorValidacionContrase�a();
		logger.info("TC7: Crear Operador: resultado obtenido="+hayMsgError);
		if (hayMsgError) {
			assertTrue(true);
		}
		else {
			LoginTest.minors++;
			fail("Deber�a haber aparecido el mensaje de error 'Campo requerido'");
		}
	}
	
	@Test
	public void testCrearOperador8() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC8: Crear Operador: validaci�n de que no se introduce la fecha de nacimiento");
		logger.info("TC8: Crear Operador: resultado esperado=true (operaci�n incorrecta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Operadores","Gesti�n de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.crear();
		CrearOperadorProcess crearOperador=new CrearOperadorProcess();
		crearOperador.escribirCampoNombre("Pepe");
		crearOperador.escribirCampoApellidos("P�rez2");
		crearOperador.escribirCampoDireccion("Diagonal 234, 3� 3�");
		crearOperador.escribirCampoCP("08023");
		crearOperador.escribirCampoCiudad("Barcelona");
		crearOperador.escribirCampoEmail("pepe3@0x.com");
		crearOperador.escribirCampoPais("Espa�a");
		crearOperador.escribirCampoTelefono("6457693");
		Error errorDetectado=crearOperador.seleccionarOpcionGrupoOP("01: Cobradores");
		if (errorDetectado==Error.Error) {
			LoginTest.majors++;
			logger.error("TC8: Crear Operador: opci�n GrupoOP no encontrada en combo");
			fail();
		}
		crearOperador.escribirCampoContrase�a("1234567890");
		crearOperador.borrarCampoFecha();
		crearOperador.confirmar();
		boolean hayMsgError=crearOperador.hayErrorValidacionFecha();
		logger.info("TC8: Crear Operador: resultado obtenido="+hayMsgError);
		if (hayMsgError) {
			assertTrue(true);
		}
		else {
			LoginTest.minors++;
			fail("Deber�a haber aparecido el tooltip de 'Campo requerido'");
		}
	}
	
	@Test
	public void testCrearOperador9() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC9: Crear Operador: validaci�n de que se introduce la fecha de nacimiento con formato err�neo");
		logger.info("TC9: Crear Operador: resultado esperado=true (operaci�n incorrecta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Operadores","Gesti�n de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.crear();
		CrearOperadorProcess crearOperador=new CrearOperadorProcess();
		crearOperador.escribirCampoNombre("Pepe");
		crearOperador.escribirCampoApellidos("P�rez2");
		crearOperador.escribirCampoDireccion("Diagonal 234, 3� 3�");
		crearOperador.escribirCampoCP("08023");
		crearOperador.escribirCampoCiudad("Barcelona");
		crearOperador.escribirCampoEmail("pepe3@0x.com");
		crearOperador.escribirCampoPais("Espa�a");
		crearOperador.escribirCampoTelefono("6457693");
		Error errorDetectado=crearOperador.seleccionarOpcionGrupoOP("01: Cobradores");
		if (errorDetectado==Error.Error) {
			LoginTest.majors++;
			logger.error("TC9: Crear Operador: opci�n GrupoOP no encontrada en combo");
			fail();
		}
		crearOperador.escribirCampoContrase�a("1234567890");
		crearOperador.escribirCampoFecha("03/05/");
		crearOperador.confirmar();
		boolean hayMsgError=crearOperador.hayErrorValidacionFecha();
		logger.info("TC9: Crear Operador: resultado obtenido="+hayMsgError);
		if (hayMsgError) {
			assertTrue(true);
		}
		else {
			LoginTest.minors++;
			fail("Deber�a haber aparecido el tooltip de 'Formato err�neo'");
		}
	}
}
