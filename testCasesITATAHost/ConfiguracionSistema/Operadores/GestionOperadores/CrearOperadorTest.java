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

	final static String titulo_crear_operador="Gestión de operadores: Creación de operador"; 
	final static String subtitulo_crear_operador="Configuración sistema   -   Operadores   -   Gestión de operadores   -   Crear"; 
	final static String encabezadocrud_crear_operador1="Información de Operador";
	final static String[] botones_crear_operador= {"Confirmar","Volver"};
	final static String[] labels_crear_operador1= {"Nombre","Apellidos","Dirección","C.P.","Ciudad",
			"País","E-mail","Teléfono","Concesionaria","Título","Género","Grupo Op.","Fecha nacimiento"};
	final static String encabezadocrud_crear_operador2="Definición de contraseña";
	final static String[] labels_crear_operador2={"Contraseña","Repita la contraseña"};
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
		logger.info("TC1: Crear Operador: análisis sintáctico");
		logger.info("TC1: Crear Operador: resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.crear();
		CrearOperadorProcess crearOperador=new CrearOperadorProcess();
		boolean analisis=crearOperador.sintacticAnalysis(titulo_crear_operador, subtitulo_crear_operador,
				botones_crear_operador, encabezadocrud_crear_operador1, labels_crear_operador1,
				encabezadocrud_crear_operador2, labels_crear_operador2);
		if (analisis) {
			logger.info("Página Crear operador: análisis sintáctico correcto");
		}
		else {
			logger.error("Página Crear operador: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1: Crear Operador: resultado obtenido="+analisis);
		assertTrue(analisis);
	}
	
	@Test
	public void testCrearOperador2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2: Crear Operador: creación del primer operador");
		logger.info("TC2: Crear Operador: resultado esperado=false (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.crear();
		CrearOperadorProcess crearOperador=new CrearOperadorProcess();
		crearOperador.escribirCampoNombre("Pepe2");
		crearOperador.escribirCampoApellidos("Pérez2");
		crearOperador.escribirCampoDireccion("Diagonal 234, 3º 3ª");
		crearOperador.escribirCampoCP("08023");
		crearOperador.escribirCampoCiudad("Barcelona");
		crearOperador.escribirCampoEmail("pepe3@0x.com");
		crearOperador.escribirCampoPais("España");
		crearOperador.escribirCampoTelefono("6457693");
		crearOperador.escribirCampoContraseña("1234567890");
		crearOperador.escribirCampoRepitacontraseña("1234567890");
		Error errorDetectado=crearOperador.seleccionarOpcionGrupoOP("01: Cobradores");
		if (errorDetectado==Error.Error) {
			LoginTest.majors++;
			logger.error("TC2: Crear Operador: opción GrupoOP no encontrada en combo");
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
		logger.info("TC3: Crear Operador: creación del mismo operador");
		logger.info("TC3: Crear Operador: resultado esperado=true (operación incorrecta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.crear();
		CrearOperadorProcess crearOperador=new CrearOperadorProcess();
		crearOperador.escribirCampoNombre("Pepe2");
		crearOperador.escribirCampoApellidos("Pérez2");
		crearOperador.escribirCampoDireccion("Diagonal 234, 3º 3ª");
		crearOperador.escribirCampoCP("08023");
		crearOperador.escribirCampoCiudad("Barcelona");
		crearOperador.escribirCampoEmail("pepe3@0x.com");
		crearOperador.escribirCampoPais("España");
		crearOperador.escribirCampoTelefono("6457693");
		crearOperador.escribirCampoContraseña("1234567890");
		crearOperador.escribirCampoRepitacontraseña("1234567890");
		Error errorDetectado=crearOperador.seleccionarOpcionGrupoOP("01: Cobradores");
		if (errorDetectado==Error.Error) {
			LoginTest.majors++;
			logger.error("TC3: Crear Operador: opción GrupoOP no encontrada en combo");
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
		logger.info("TC4: Crear Operador: creación de un operador sin repetir contraseña");
		logger.info("TC4: Crear Operador: resultado esperado=true (operación incorrecta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.crear();
		CrearOperadorProcess crearOperador=new CrearOperadorProcess();
		crearOperador.escribirCampoNombre("Pepe2");
		crearOperador.escribirCampoApellidos("Pérez2");
		crearOperador.escribirCampoDireccion("Diagonal 234, 3º 3ª");
		crearOperador.escribirCampoCP("08023");
		crearOperador.escribirCampoCiudad("Barcelona");
		crearOperador.escribirCampoEmail("pepe3@0x.com");
		crearOperador.escribirCampoPais("España");
		crearOperador.escribirCampoTelefono("6457693");
		crearOperador.escribirCampoContraseña("1234567890");
		Error errorDetectado=crearOperador.seleccionarOpcionGrupoOP("01: Cobradores");
		if (errorDetectado==Error.Error) {
			LoginTest.majors++;
			logger.error("TC4: Crear Operador: opción GrupoOP no encontrada en combo");
			fail();
		}
		crearOperador.escribirCampoFecha("03/05/1967");
		crearOperador.confirmar();
		boolean hayMsgError=crearOperador.hayMensajeError();
		if (hayMsgError) {
			assertTrue(crearOperador.verMensajeError().equals("Contraseñas diferentes"));
		}
		else {
			LoginTest.minors++;
			fail("Debería haber aparecido el mensaje de error 'Contraseñas diferentes'");
		}
		logger.info("TC4: Crear Operador: resultado obtenido="+hayMsgError);
	}

	@Test
	public void testCrearOperador5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5: Crear Operador: validación de que no se introduce el nombre");
		logger.info("TC5: Crear Operador: resultado esperado=true (operación incorrecta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.crear();
		CrearOperadorProcess crearOperador=new CrearOperadorProcess();
		crearOperador.escribirCampoApellidos("Pérez2");
		crearOperador.escribirCampoDireccion("Diagonal 234, 3º 3ª");
		crearOperador.escribirCampoCP("08023");
		crearOperador.escribirCampoCiudad("Barcelona");
		crearOperador.escribirCampoEmail("pepe3@0x.com");
		crearOperador.escribirCampoPais("España");
		crearOperador.escribirCampoTelefono("6457693");
		crearOperador.escribirCampoContraseña("1234567890");
		Error errorDetectado=crearOperador.seleccionarOpcionGrupoOP("01: Cobradores");
		if (errorDetectado==Error.Error) {
			LoginTest.majors++;
			logger.error("TC5: Crear Operador: opción GrupoOP no encontrada en combo");
			fail();
		}
		crearOperador.escribirCampoFecha("03/05/1967");
		crearOperador.confirmar();
		boolean hayMsgError=crearOperador.hayErrorValidacionNombre();
		if (!hayMsgError){
			LoginTest.minors++;
			logger.error("Debería haber aparecido el mensaje de error 'Campo requerido'");
		}
		logger.info("TC5: Crear Operador: resultado obtenido="+hayMsgError);
		assertTrue(hayMsgError);
	}
	
	@Test
	public void testCrearOperador6() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6: Crear Operador: validación de que no se introduce el apellido");
		logger.info("TC6: Crear Operador: resultado esperado=true (operación incorrecta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.crear();
		CrearOperadorProcess crearOperador=new CrearOperadorProcess();
		crearOperador.escribirCampoNombre("Pepe");
		crearOperador.escribirCampoDireccion("Diagonal 234, 3º 3ª");
		crearOperador.escribirCampoCP("08023");
		crearOperador.escribirCampoCiudad("Barcelona");
		crearOperador.escribirCampoEmail("pepe3@0x.com");
		crearOperador.escribirCampoPais("España");
		crearOperador.escribirCampoTelefono("6457693");
		crearOperador.escribirCampoContraseña("1234567890");
		Error errorDetectado=crearOperador.seleccionarOpcionGrupoOP("01: Cobradores");
		if (errorDetectado==Error.Error) {
			LoginTest.majors++;
			logger.error("TC6: Crear Operador: opción GrupoOP no encontrada en combo");
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
			fail("Debería haber aparecido el mensaje de error 'Campo requerido'");
		}
		logger.info("TC6: Crear Operador: resultado obtenido="+hayMsgError);
	}
	
	@Test
	public void testCrearOperador7() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC7: Crear Operador: validación de que no se introduce la contraseña");
		logger.info("TC7: Crear Operador: resultado esperado=true (operación incorrecta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.crear();
		CrearOperadorProcess crearOperador=new CrearOperadorProcess();
		crearOperador.escribirCampoNombre("Pepe");
		crearOperador.escribirCampoApellidos("Pérez2");
		crearOperador.escribirCampoDireccion("Diagonal 234, 3º 3ª");
		crearOperador.escribirCampoCP("08023");
		crearOperador.escribirCampoCiudad("Barcelona");
		crearOperador.escribirCampoEmail("pepe3@0x.com");
		crearOperador.escribirCampoPais("España");
		crearOperador.escribirCampoTelefono("6457693");
		Error errorDetectado=crearOperador.seleccionarOpcionGrupoOP("01: Cobradores");
		if (errorDetectado==Error.Error) {
			LoginTest.majors++;
			logger.error("TC7: Crear Operador: opción GrupoOP no encontrada en combo");
			fail();
		}
		crearOperador.escribirCampoFecha("03/05/1967");
		crearOperador.confirmar();
		boolean hayMsgError=crearOperador.hayErrorValidacionContraseña();
		logger.info("TC7: Crear Operador: resultado obtenido="+hayMsgError);
		if (hayMsgError) {
			assertTrue(true);
		}
		else {
			LoginTest.minors++;
			fail("Debería haber aparecido el mensaje de error 'Campo requerido'");
		}
	}
	
	@Test
	public void testCrearOperador8() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC8: Crear Operador: validación de que no se introduce la fecha de nacimiento");
		logger.info("TC8: Crear Operador: resultado esperado=true (operación incorrecta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.crear();
		CrearOperadorProcess crearOperador=new CrearOperadorProcess();
		crearOperador.escribirCampoNombre("Pepe");
		crearOperador.escribirCampoApellidos("Pérez2");
		crearOperador.escribirCampoDireccion("Diagonal 234, 3º 3ª");
		crearOperador.escribirCampoCP("08023");
		crearOperador.escribirCampoCiudad("Barcelona");
		crearOperador.escribirCampoEmail("pepe3@0x.com");
		crearOperador.escribirCampoPais("España");
		crearOperador.escribirCampoTelefono("6457693");
		Error errorDetectado=crearOperador.seleccionarOpcionGrupoOP("01: Cobradores");
		if (errorDetectado==Error.Error) {
			LoginTest.majors++;
			logger.error("TC8: Crear Operador: opción GrupoOP no encontrada en combo");
			fail();
		}
		crearOperador.escribirCampoContraseña("1234567890");
		crearOperador.borrarCampoFecha();
		crearOperador.confirmar();
		boolean hayMsgError=crearOperador.hayErrorValidacionFecha();
		logger.info("TC8: Crear Operador: resultado obtenido="+hayMsgError);
		if (hayMsgError) {
			assertTrue(true);
		}
		else {
			LoginTest.minors++;
			fail("Debería haber aparecido el tooltip de 'Campo requerido'");
		}
	}
	
	@Test
	public void testCrearOperador9() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC9: Crear Operador: validación de que se introduce la fecha de nacimiento con formato erróneo");
		logger.info("TC9: Crear Operador: resultado esperado=true (operación incorrecta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.crear();
		CrearOperadorProcess crearOperador=new CrearOperadorProcess();
		crearOperador.escribirCampoNombre("Pepe");
		crearOperador.escribirCampoApellidos("Pérez2");
		crearOperador.escribirCampoDireccion("Diagonal 234, 3º 3ª");
		crearOperador.escribirCampoCP("08023");
		crearOperador.escribirCampoCiudad("Barcelona");
		crearOperador.escribirCampoEmail("pepe3@0x.com");
		crearOperador.escribirCampoPais("España");
		crearOperador.escribirCampoTelefono("6457693");
		Error errorDetectado=crearOperador.seleccionarOpcionGrupoOP("01: Cobradores");
		if (errorDetectado==Error.Error) {
			LoginTest.majors++;
			logger.error("TC9: Crear Operador: opción GrupoOP no encontrada en combo");
			fail();
		}
		crearOperador.escribirCampoContraseña("1234567890");
		crearOperador.escribirCampoFecha("03/05/");
		crearOperador.confirmar();
		boolean hayMsgError=crearOperador.hayErrorValidacionFecha();
		logger.info("TC9: Crear Operador: resultado obtenido="+hayMsgError);
		if (hayMsgError) {
			assertTrue(true);
		}
		else {
			LoginTest.minors++;
			fail("Debería haber aparecido el tooltip de 'Formato erróneo'");
		}
	}
}
