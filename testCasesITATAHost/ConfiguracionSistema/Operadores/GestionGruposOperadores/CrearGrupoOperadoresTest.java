package testCasesITATAHost.ConfiguracionSistema.Operadores.GestionGruposOperadores;

import static org.junit.Assert.*;
import procesosComunes.Error;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionGruposOperadores.CrearModificarGrupoOperadoresProcess;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionGruposOperadores.GestionGruposOperadoresProcess;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.GestionOperadoresProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;


public class CrearGrupoOperadoresTest {

	final static String titulo_crear_grupooperadores="Gesti�n de grupos de operadores: creaci�n grupo"; 
	final static String subtitulo_crear_grupooperadores="Configuraci�n sistema   -   Operadores   -   Gesti�n de grupos   -   Crear"; 
	final static String[] botones_crear_grupooperadores= {"Confirmar","Volver"};
	final static String[] labels_crear_grupooperadores= {"Descripci�n","Acceso a Plaza","Acceso V�a"};
	final static String encabezadocrud_crear_grupooperadores="Informaci�n de Grupo";
	final static String encabezadotablas_crear_grupooperadores="Permisos del grupo";
	final static String[] labelstablas_crear_grupooperadores={"Permisos asignados","Permisos disponibles"};
	final static String[] botonestablas_crear_grupooperadores={"<<",">>"};
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(CrearGrupoOperadoresTest.class);
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
	public void testCrearGrupoOperador1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1: Crear Grupo Operador: An�lisis sint�ctico");
		logger.info("TC1: Crear Grupo Operador: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Operadores","Gesti�n de grupos");
		GestionGruposOperadoresProcess gestionGrupoOperadores=new GestionGruposOperadoresProcess();
		gestionGrupoOperadores.crear();
		CrearModificarGrupoOperadoresProcess crearGrupoOperador=new CrearModificarGrupoOperadoresProcess();
		boolean analisis=crearGrupoOperador.sintacticAnalysis(titulo_crear_grupooperadores, subtitulo_crear_grupooperadores,
				botones_crear_grupooperadores, encabezadocrud_crear_grupooperadores, labels_crear_grupooperadores,
				encabezadotablas_crear_grupooperadores, labelstablas_crear_grupooperadores, botonestablas_crear_grupooperadores);
		if (analisis) {
			logger.info("P�gina Crear grupo operador: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("P�gina Crear grupo operador: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1: Crear grupo Operador: resultado obtenido="+analisis);
		assertTrue(analisis);
	}
	
	@Test
	public void testCrearGrupoOperador2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2: Crear Grupo Operador: creaci�n del primer grupo");
		logger.info("TC2: Crear Grupo Operador: resultado esperado=false (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Operadores","Gesti�n de grupos");
		GestionGruposOperadoresProcess gestionGrupoOperadores=new GestionGruposOperadoresProcess();
		gestionGrupoOperadores.crear();
		CrearModificarGrupoOperadoresProcess crearGrupoOperador=new CrearModificarGrupoOperadoresProcess();
		String nombreGrupo="Grupo1";
		crearGrupoOperador.escribirCampoDescripcion(nombreGrupo);
		Error errorAccesoPlaza=crearGrupoOperador.seleccionarOpcionAccesoPlaza("Cobrador");
		if (errorAccesoPlaza==Error.Error) {
			LoginTest.majors++;
			logger.error("TC2: Crear Grupo Operador: opci�n Acceso Plaza no encontrada en combo");
			fail();
		}
		Error errorAccesoVia=crearGrupoOperador.seleccionarOpcionAccesoVia("Cobrador");
		if (errorAccesoVia==Error.Error) {
			LoginTest.majors++;
			logger.error("TC2: Crear Grupo Operador: opci�n Acceso V�a no encontrada en combo");
			fail();
		}
		crearGrupoOperador.confirmar();
		boolean hayMsgError=crearGrupoOperador.hayMensajeError();
		if (hayMsgError) {
			LoginTest.minors++;
		}
		
		// Comprobamos que el nuevo grupo aparece en el combo en la b�squeda de un operador
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Operadores","Gesti�n de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		Error errorDetectado=gestionOperadores.seleccionarOpcionGrupoOperadores(nombreGrupo);
		boolean hayError=errorDetectado==Error.Error;
		if (hayError) {
			LoginTest.majors++;
			logger.error("TC2: Crear Grupo Operador: el nombre del nuevo grupo no aparece en el combo al crear un operador");
		}
		logger.info("TC2: Crear Grupo Operador: resultado obtenido="+(hayMsgError && !hayError));
		assertFalse(hayMsgError && !hayError);
	}
	
	@Test
	public void testCrearGrupoOperador3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Crear Grupo Operador");
		logger.info("TC3 Crear Grupo Operador: Creaci�n del mismo grupo");
		logger.info("TC3 Crear Grupo Operador: resultado esperado=true (operaci�n incorrecta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Operadores","Gesti�n de grupos");
		GestionGruposOperadoresProcess gestionGrupoOperadores=new GestionGruposOperadoresProcess();
		gestionGrupoOperadores.crear();
		CrearModificarGrupoOperadoresProcess crearGrupoOperador=new CrearModificarGrupoOperadoresProcess();
		crearGrupoOperador.escribirCampoDescripcion("Grupo1");
		Error errorAccesoPlaza=crearGrupoOperador.seleccionarOpcionAccesoPlaza("Cobrador");
		if (errorAccesoPlaza==Error.Error) {
			LoginTest.majors++;
			logger.error("TC3 Crear Grupo Operador: Opci�n Acceso Plaza no encontrada en combo");
			fail();
		}
		Error errorAccesoVia=crearGrupoOperador.seleccionarOpcionAccesoVia("Cobrador");
		if (errorAccesoVia==Error.Error) {
			LoginTest.majors++;
			logger.error("TC3 Crear Grupo Operador: Opci�n Acceso V�a no encontrada en combo");
			fail();
		}
		crearGrupoOperador.confirmar();
		boolean hayMsgError=crearGrupoOperador.hayMensajeError();
		if (!hayMsgError) {
			LoginTest.minors++;
		}
		logger.error("TC3 Crear Grupo Operador: Resultado obtenido="+hayMsgError);
		assertFalse(hayMsgError);
	}
	
	@Test
	public void testCrearGrupoOperador4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Crear Grupo Operador");
		logger.info("TC4 Crear Grupo Operador: Probar el error de validaci�n de campo Descripci�n");
		logger.info("TC4 Crear Grupo Operador: Resultado esperado: true (aparece el tooltip)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Operadores","Gesti�n de grupos");
		GestionGruposOperadoresProcess gestionGrupoOperadores=new GestionGruposOperadoresProcess();
		gestionGrupoOperadores.crear();
		CrearModificarGrupoOperadoresProcess crearGrupoOperador=new CrearModificarGrupoOperadoresProcess();
		crearGrupoOperador.confirmar();
		boolean hayAlertavalidacion=crearGrupoOperador.hayErrorValidacionDescripcion();
		if (!hayAlertavalidacion) {
			logger.error("TC4 Crear Grupo Operador: Deber�a haber aparecido el tooltip de validaci�n de Descripci�n");
			LoginTest.minors++;
		}
		logger.info("TC4 Crear Grupo Operador: Resultado obtenido: "+hayAlertavalidacion);
		crearGrupoOperador.volver();
		gestionGrupoOperadores.volver();
		assertTrue(hayAlertavalidacion);
	}
}
