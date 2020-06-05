package testCasesITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.CrearNodoProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.NodosProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.ErrorMessage;

public class CrearNodoTest {

	final static String titulo="Creación de Nodo de Plaza"; 
	final static String subtitulo="Configuración sistema   -   Configuración de peaje   -   Nodos   -   Crear"; 
	final static String[] botones= {"Confirmar","Volver"};
	final static String encabezado="Información del nodo";
	final static String[] labels= {"Código","Dirección","Descripción"};
	final static String msg_error_codigo_existente="Ya existe un nodo con el código indicado";
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(CrearNodoTest.class);
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
	public void testCrearNodo1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Crear Nodo");
		logger.info("TC1 Crear Nodo: Probar el análisis sintáctico correcto");
		logger.info("TC1 Crear Nodo: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		nodos.crear();
		CrearNodoProcess crearNodo=new CrearNodoProcess();
		boolean analisis=crearNodo.sintacticAnalysis(titulo,subtitulo, botones, encabezado, labels);
		if (analisis) {
			logger.info("TC1 Crear Nodo: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Crear Nodo: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Crear Nodo: Resultado obtenido="+analisis);
		crearNodo.volver();
		nodos.volver();
		assertTrue(analisis);
	}
	
	
	@Test
	public void testCrearNodo2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Crear Nodo");
		logger.info("TC2 Crear Nodo: Crear un nodo");
		logger.info("TC2 Crear Nodo: Resultado esperado: true (operación correcta, comprobación de aparición en tabla)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		nodos.crear();
		CrearNodoProcess crearNodo=new CrearNodoProcess();
		String[] datosEntrada=new String[nodos.numColumnasTabla()];
		datosEntrada[0]="0011";
		datosEntrada[1]="11.11.11.11";
		datosEntrada[2]="Descripcion aa";
		crearNodo.escribirCampoCodigo(datosEntrada[0]);
		crearNodo.escribirCampoDireccion(datosEntrada[1]);
		crearNodo.escribirCampoDescripcion(datosEntrada[2]);
		crearNodo.confirmar();
		nodos.recargarTabla();
		String[] resultado=new String[nodos.numColumnasTabla()];
		String[] columnas=new String[1];
		columnas[0]="Código";
		String[] valores=new String[1];
		valores[0]=datosEntrada[0];
		resultado=nodos.obtenerFilaTabla(columnas, valores);
		datosEntrada[3]=resultado[3];
		boolean iguales=RutinasTestComunes.assertArrayEquals(datosEntrada,resultado);
		if (!iguales) {
			LoginTest.majors++;
		}
		logger.info("TC2 Crear Nodo: Resultado obtenido:"+iguales);
		nodos.volver();
		assertTrue(iguales);
	}
	
	@Test
	public void testCrearNodo3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Crear Nodo");
		logger.info("TC3 Crear Nodo: Probar la validación de la existencia del campo Código");
		logger.info("TC3 Crear Nodo: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		nodos.crear();
		CrearNodoProcess crearNodo=new CrearNodoProcess();
		crearNodo.confirmar();
		boolean hayErrorValidacion=crearNodo.hayErrorValidacionRequeridoCodigo();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC3 Crear Nodo: Resultado obtenido:"+hayErrorValidacion);
		nodos.volver();
		assertTrue(hayErrorValidacion);
	}

	@Test
	public void testCrearNodo4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Crear Nodo");
		logger.info("TC4 Crear Nodo: Probar la validación del formato del campo Código");
		logger.info("TC4 Crear Nodo: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		nodos.crear();
		CrearNodoProcess crearNodo=new CrearNodoProcess();
		crearNodo.escribirCampoCodigo("aa");
		crearNodo.confirmar();
		boolean hayErrorValidacion=crearNodo.hayErrorValidacionFormatoCodigo();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC4 Crear Nodo: Resultado obtenido:"+hayErrorValidacion);
		nodos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearNodo5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Crear Nodo");
		logger.info("TC5 Crear Nodo: Probar la validación de la existencia del campo Dirección");
		logger.info("TC5 Crear Nodo: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		nodos.crear();
		CrearNodoProcess crearNodo=new CrearNodoProcess();
		crearNodo.confirmar();
		boolean hayErrorValidacion=crearNodo.hayErrorValidacionRequeridoDireccion();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC5 Crear Nodo: Resultado obtenido:"+hayErrorValidacion);
		nodos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearNodo6() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6 Crear Nodo");
		logger.info("TC6 Crear Nodo: Probar la validación del formato del campo Dirección");
		logger.info("TC6 Crear Nodo: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		nodos.crear();
		CrearNodoProcess crearNodo=new CrearNodoProcess();
		crearNodo.escribirCampoDireccion("11.11.11.aa");
		crearNodo.confirmar();
		boolean hayErrorValidacion=crearNodo.hayErrorValidacionFormatoDireccion();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC6 Crear Nodo: Resultado obtenido:"+hayErrorValidacion);
		nodos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearNodo7() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC7 Crear Nodo");
		logger.info("TC7 Crear Nodo: Crear un nodo con un código existente");
		logger.info("TC7 Crear Nodo: Resultado esperado: true (aparece un mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		nodos.crear();
		CrearNodoProcess crearNodo=new CrearNodoProcess();
		String datosEntrada[]= {"0011","11.11.11.11","Descripcion aa"};
		crearNodo.escribirCampoCodigo(datosEntrada[0]);
		crearNodo.escribirCampoDireccion(datosEntrada[1]);
		crearNodo.escribirCampoDescripcion(datosEntrada[2]);
		crearNodo.confirmar();
		boolean hayMsgError=crearNodo.hayMensajeError();
		boolean analisis=false;
		if (hayMsgError) {
			ErrorMessage errormsgobt=crearNodo.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_codigo_existente);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC7 Crear Nodo: No ha aparecido el mensaje de error '"+msg_error_codigo_existente+"'");
			LoginTest.majors++;
		}
		logger.info("T7 Crear Nodo: Resultado obtenido:"+(hayMsgError && analisis));
		assertTrue((hayMsgError && analisis));
	}
}
