package testCasesITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.CrearNodoViaProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.GestionNodosViaProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.NodosProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.ErrorMessage;

public class CrearNodoViaTest {

	final static String titulo="Creaci�n de Nodo v�a"; 
	final static String subtitulo="Configuraci�n sistema   -   Configuraci�n de peaje   -   Nodos   -   Editar Nodo V�a   -   Crear"; 
	final static String[] botones= {"Confirmar","Volver"};
	final static String encabezado1="Nodo Plaza";
	final static String[] labelsVariables= {"C�digo","Descripci�n","Direcci�n"};
	final static String encabezado2="Nodo V�a";
	final static String[] labels= {"C�digo","Direcci�n"};
	final static String msg_error_codigo_existente="Ya existe un nodo con el c�digo indicado";
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(CrearNodoViaTest.class);
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
	public void testCrearNodoVia1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Crear Nodo V�a");
		logger.info("TC1 Crear Nodo V�a: Probar el an�lisis sint�ctico correcto");
		logger.info("TC1 Crear Nodo V�a: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigonodo="0002";
		int numfilaPlaza=nodos.obtenerNumFilaTabla("C�digo", codigonodo);
		nodos.seleccionarFila(numfilaPlaza);
		String[] valoresVariables=new String[labelsVariables.length];
		for (int i =0; i<labelsVariables.length; i++) {
			valoresVariables[i]=nodos.obtenerValorColumna(numfilaPlaza, labelsVariables[i]);
		}
		nodos.editarNodoVia();
		GestionNodosViaProcess nodosvia=new GestionNodosViaProcess();
		nodosvia.crear();
		CrearNodoViaProcess crearNodoVia=new CrearNodoViaProcess();
		boolean analisis=crearNodoVia.sintacticAnalysis(titulo,subtitulo, botones, encabezado1, labelsVariables, valoresVariables, encabezado2, labels);
		if (analisis) {
			logger.info("TC1 Crear Nodo V�a: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC1 Crear Nodo V�a: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Crear Nodo V�a: Resultado obtenido="+analisis);
		crearNodoVia.volver();
		nodosvia.volver();
		nodos.volver();
		assertTrue(analisis);
	}
	
	
	@Test
	public void testCrearNodoVia2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Crear Nodo V�a");
		logger.info("TC2 Crear Nodo V�a: Crear un nodo de v�a");
		logger.info("TC2 Crear Nodo V�a: Resultado esperado: true (operaci�n correcta, comprobaci�n de aparici�n en tabla)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigonodo="0002";
		int numfilaPlaza=nodos.obtenerNumFilaTabla("C�digo", codigonodo);
		nodos.seleccionarFila(numfilaPlaza);
		nodos.editarNodoVia();
		GestionNodosViaProcess nodosvia=new GestionNodosViaProcess();
		String datosSalida[]=new String[nodosvia.numColumnasTabla()];
		nodosvia.crear();
		CrearNodoViaProcess crearNodoVia=new CrearNodoViaProcess();
		String datosEntrada[]= {"LNC010011","11.11.11.12"};
		datosSalida[0]=datosEntrada[0];
		datosSalida[1]=datosEntrada[1];
		datosSalida[2]=" ";
		datosSalida[3]="S�";
		crearNodoVia.escribirCampoCodigoVia(datosEntrada[0]);
		crearNodoVia.escribirCampoDireccionVia(datosEntrada[1]);
		crearNodoVia.confirmar();
		nodosvia.recargarTabla();
		String[] resultado=new String[nodosvia.numColumnasTabla()];
		String[] columnas=new String[1];
		columnas[0]="C�digo";
		String[] valores=new String[1];
		valores[0]=datosEntrada[0];
		resultado=nodosvia.obtenerFilaTabla(columnas, valores);
		boolean iguales=RutinasTestComunes.assertArrayEquals(datosSalida,resultado);
		if (!iguales) {
			LoginTest.majors++;
		}
		logger.info("TC2 Crear Nodo V�a: Resultado obtenido:"+iguales);
		nodosvia.volver();
		nodos.volver();
		assertTrue(iguales);
	}
	
	@Test
	public void testCrearNodoVia3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Crear Nodo V�a");
		logger.info("TC3 Crear Nodo V�a: Probar la validaci�n de la existencia del campo C�digo");
		logger.info("TC3 Crear Nodo V�a: Resultado esperado: true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigonodo="0002";
		int numfilaPlaza=nodos.obtenerNumFilaTabla("C�digo", codigonodo);
		nodos.seleccionarFila(numfilaPlaza);
		nodos.editarNodoVia();
		GestionNodosViaProcess nodosvia=new GestionNodosViaProcess();
		nodosvia.crear();
		CrearNodoViaProcess crearNodoVia=new CrearNodoViaProcess();
		crearNodoVia.borrarCampoCodigoVia(); // Lo borramos porque por defecto hay ya un valor
		crearNodoVia.confirmar();
		boolean hayErrorValidacion=crearNodoVia.hayErrorValidacionRequeridoCodigoVia();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC3 Crear Nodo V�a: Resultado obtenido:"+hayErrorValidacion);
		crearNodoVia.volver();
		nodosvia.volver();
		nodos.volver();
		assertTrue(hayErrorValidacion);
	}

	@Test
	public void testCrearNodoVia4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Crear Nodo V�a");
		logger.info("TC4 Crear Nodo V�a: Probar la validaci�n del formato del campo C�digo");
		logger.info("TC4 Crear Nodo V�a: Resultado esperado: true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigonodo="0002";
		int numfilaPlaza=nodos.obtenerNumFilaTabla("C�digo", codigonodo);
		nodos.seleccionarFila(numfilaPlaza);
		nodos.editarNodoVia();
		GestionNodosViaProcess nodosvia=new GestionNodosViaProcess();
		nodosvia.crear();
		CrearNodoViaProcess crearNodoVia=new CrearNodoViaProcess();
		crearNodoVia.escribirCampoCodigoVia("!");
		crearNodoVia.confirmar();
		boolean hayErrorValidacion=crearNodoVia.hayErrorValidacionFormatoCodigoVia();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC4 Crear Nodo V�a: Resultado obtenido:"+hayErrorValidacion);
		crearNodoVia.volver();
		nodosvia.volver();
		nodos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearNodoVia5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Crear Nodo V�a");
		logger.info("TC5 Crear Nodo V�a: Probar la validaci�n de la existencia del campo Direcci�n");
		logger.info("TC5 Crear Nodo V�a: Resultado esperado: true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigonodo="0002";
		int numfilaPlaza=nodos.obtenerNumFilaTabla("C�digo", codigonodo);
		nodos.seleccionarFila(numfilaPlaza);
		nodos.editarNodoVia();
		GestionNodosViaProcess nodosvia=new GestionNodosViaProcess();
		nodosvia.crear();
		CrearNodoViaProcess crearNodoVia=new CrearNodoViaProcess();
		crearNodoVia.confirmar();
		boolean hayErrorValidacion=crearNodoVia.hayErrorValidacionRequeridoDireccionVia();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC5 Crear Nodo V�a: Resultado obtenido:"+hayErrorValidacion);
		crearNodoVia.volver();
		nodosvia.volver();
		nodos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearNodoVia6() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6 Crear Nodo V�a");
		logger.info("TC6 Crear Nodo V�a: Probar la validaci�n del formato del campo Direcci�n");
		logger.info("TC6 Crear Nodo V�a: Resultado esperado: true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigonodo="0002";
		int numfilaPlaza=nodos.obtenerNumFilaTabla("C�digo", codigonodo);
		nodos.seleccionarFila(numfilaPlaza);
		nodos.editarNodoVia();
		GestionNodosViaProcess nodosvia=new GestionNodosViaProcess();
		nodosvia.crear();
		CrearNodoViaProcess crearNodoVia=new CrearNodoViaProcess();
		crearNodoVia.escribirCampoDireccionVia("!");
		crearNodoVia.confirmar();
		boolean hayErrorValidacion=crearNodoVia.hayErrorValidacionFormatoDireccionVia();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC6 Crear Nodo V�a: Resultado obtenido:"+hayErrorValidacion);
		crearNodoVia.volver();
		nodosvia.volver();
		nodos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearNodoVia7() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC7 Crear Nodo V�a");
		logger.info("TC7 Crear Nodo V�a: Crear un nodo con un c�digo existente");
		logger.info("TC7 Crear Nodo V�a: Resultado esperado: true (aparece un mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigonodo="0002";
		int numfilaPlaza=nodos.obtenerNumFilaTabla("C�digo", codigonodo);
		nodos.seleccionarFila(numfilaPlaza);
		nodos.editarNodoVia();
		GestionNodosViaProcess nodosvia=new GestionNodosViaProcess();
		nodosvia.crear();
		CrearNodoViaProcess crearNodoVia=new CrearNodoViaProcess();
		String datosEntrada[]= {"LNC010011","11.11.11.12"};
		crearNodoVia.escribirCampoCodigoVia(datosEntrada[0]);
		crearNodoVia.escribirCampoDireccionVia(datosEntrada[1]);
		crearNodoVia.confirmar();
		boolean hayMsgError=crearNodoVia.hayMensajeError();
		boolean analisis=false;
		if (hayMsgError) {
			ErrorMessage errormsgobt=crearNodoVia.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_codigo_existente);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC7 Crear Nodo V�a: No ha aparecido el mensaje de error '"+msg_error_codigo_existente+"'");
			LoginTest.majors++;
		}
		logger.info("T7 Crear Nodo V�a: Resultado obtenido:"+(hayMsgError && analisis));
		assertTrue((hayMsgError && analisis));
	}
}
