package testCasesITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.ConcesionariasProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.CrearModificarConcesionariaProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.ErrorMessage;

public class CrearConcesionariaTest {

	final static String titulo="Creaci�n Concesionaria"; 
	final static String subtitulo="Configuraci�n sistema   -   Configuraci�n de peaje   -   Concesionarias, Plazas, V�as   -   Crear"; 
	final static String[] botones= {"Confirmar","Volver"};
	final static String encabezado="Informaci�n concesionaria";
	final static String[] labels= {"ID","Nombre","N�mero fiscal"};
	final static String msg_error_codigo_existente="Ya existe una concesionaria con el c�digo indicado";
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(CrearConcesionariaTest.class);
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
	public void testCrearConcesionaria1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Crear Concesionaria");
		logger.info("TC1 Crear Concesionaria: Probar el an�lisis sint�ctico correcto");
		logger.info("TC1 Crear Concesionaria: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Concesionarias, Plazas, V�as");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		concesionarias.crear();
		CrearModificarConcesionariaProcess crearConcesionaria=new CrearModificarConcesionariaProcess();
		boolean analisis=crearConcesionaria.sintacticAnalysis(titulo,subtitulo, botones, encabezado, labels);
		if (analisis) {
			logger.info("TC1 Crear Concesionaria: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC1 Crear Concesionaria: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Crear Concesionaria: Resultado obtenido="+analisis);
		crearConcesionaria.volver();
		concesionarias.volver();
		assertTrue(analisis);
	}

	
	@Test
	public void testCrearConcesionaria2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Crear Concesionaria");
		logger.info("TC2 Crear Concesionaria: Crear una concesionaria con un c�digo inexistente");
		logger.info("TC2 Crear Concesionaria: Resultado esperado: true (operaci�n correcta, comprobaci�n de aparici�n en tabla)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Concesionarias, Plazas, V�as");
		String[] datosEntrada= {"99","Descripcion aa","N�mero fiscal aa"};
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		concesionarias.crear();
		CrearModificarConcesionariaProcess crearConcesionaria=new CrearModificarConcesionariaProcess();
		crearConcesionaria.escribirCampoID(datosEntrada[0]);
		crearConcesionaria.escribirCampoNombre(datosEntrada[1]);
		crearConcesionaria.escribirCampoNumeroFiscal(datosEntrada[2]);
		crearConcesionaria.confirmar();
		concesionarias.recargarTabla();
		String[] resultado=new String[3];
		String[] columnas=new String[1];
		columnas[0]="ID";
		String[] valores=new String[1];
		valores[0]=datosEntrada[0];
		resultado=concesionarias.obtenerFilaTabla(columnas, valores);
		boolean iguales=RutinasTestComunes.assertArrayEquals(datosEntrada,resultado);
		if (!iguales) {
			LoginTest.majors++;
		}
		logger.info("TC2 Crear Concesionaria: Resultado obtenido:"+iguales);
		concesionarias.volver();
		assertTrue(iguales);
	}
	
	@Test
	public void testCrearConcesionaria3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Crear Concesionaria");
		logger.info("TC3 Crear Concesionaria: Probar la validaci�n de la existencia del campo ID");
		logger.info("TC3 Crear Concesionaria: Resultado esperado: true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Concesionarias, Plazas, V�as");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		concesionarias.crear();
		CrearModificarConcesionariaProcess crearConcesionaria=new CrearModificarConcesionariaProcess();
		crearConcesionaria.confirmar();
		boolean hayErrorValidacion=crearConcesionaria.hayErrorValidacionRequeridoID();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC3 Crear Concesionaria: Resultado obtenido:"+hayErrorValidacion);
		concesionarias.volver();
		assertTrue(hayErrorValidacion);
	}

	@Test
	public void testCrearConcesionaria4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Crear Concesionaria");
		logger.info("TC4 Crear Concesionaria: Probar la validaci�n del formato del campo ID");
		logger.info("TC4 Crear Concesionaria: Resultado esperado: true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Concesionarias, Plazas, V�as");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		concesionarias.crear();
		CrearModificarConcesionariaProcess crearConcesionaria=new CrearModificarConcesionariaProcess();
		crearConcesionaria.confirmar();
		boolean hayErrorValidacion=crearConcesionaria.hayErrorValidacionFormatoID();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC4 Crear Concesionaria: Resultado obtenido:"+hayErrorValidacion);
		concesionarias.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearConcesionaria5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Crear Concesionaria");
		logger.info("TC5 Crear Concesionaria: Probar la validaci�n del campo N�mero Fiscal");
		logger.info("TC5 Crear Concesionaria: Resultado esperado: true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Concesionarias, Plazas, V�as");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		concesionarias.crear();
		CrearModificarConcesionariaProcess crearConcesionaria=new CrearModificarConcesionariaProcess();
		crearConcesionaria.confirmar();
		boolean hayErrorValidacion=crearConcesionaria.hayErrorValidacionNumeroFiscal();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC5 Crear Concesionaria: Resultado obtenido:"+hayErrorValidacion);
		concesionarias.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearConcesionaria6() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6 Crear Concesionaria");
		logger.info("TC6 Crear Concesionaria: Crear una concesionaria con un c�digo existente");
		logger.info("TC6 Crear Concesionaria: Resultado esperado: true (aparece un mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Concesionarias, Plazas, V�as");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		concesionarias.crear();
		CrearModificarConcesionariaProcess crearConcesionaria=new CrearModificarConcesionariaProcess();
		crearConcesionaria.escribirCampoID("00");
		crearConcesionaria.escribirCampoNombre("Descripcion aa");
		crearConcesionaria.escribirCampoNumeroFiscal("N�mero fiscal aa");
		crearConcesionaria.confirmar();
		boolean hayMsgError=crearConcesionaria.hayMensajeError();
		boolean analisis=false;
		if (hayMsgError) {
			ErrorMessage errormsgobt=crearConcesionaria.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_codigo_existente);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC6 Crear Concesionaria: No ha aparecido el mensaje de error '"+msg_error_codigo_existente+"'");
			LoginTest.majors++;
		}
		logger.info("T6 Crear Concesionaria: Resultado obtenido:"+(hayMsgError && analisis));
		assertTrue((hayMsgError && analisis));
	}
}
