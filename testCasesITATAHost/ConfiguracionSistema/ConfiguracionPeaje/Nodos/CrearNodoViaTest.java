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

	final static String titulo="Creación de Nodo vía"; 
	final static String subtitulo="Configuración sistema   -   Configuración de peaje   -   Nodos   -   Editar Nodo Vía   -   Crear"; 
	final static String[] botones= {"Confirmar","Volver"};
	final static String encabezado1="Nodo Plaza";
	final static String[] labelsVariables= {"Código","Descripción","Dirección"};
	final static String encabezado2="Nodo Vía";
	final static String[] labels= {"Código","Dirección"};
	final static String msg_error_codigo_existente="Ya existe un nodo con el código indicado";
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
		logger.info("TC1 Crear Nodo Vía");
		logger.info("TC1 Crear Nodo Vía: Probar el análisis sintáctico correcto");
		logger.info("TC1 Crear Nodo Vía: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigonodo="0002";
		int numfilaPlaza=nodos.obtenerNumFilaTabla("Código", codigonodo);
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
			logger.info("TC1 Crear Nodo Vía: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Crear Nodo Vía: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Crear Nodo Vía: Resultado obtenido="+analisis);
		crearNodoVia.volver();
		nodosvia.volver();
		nodos.volver();
		assertTrue(analisis);
	}
	
	
	@Test
	public void testCrearNodoVia2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Crear Nodo Vía");
		logger.info("TC2 Crear Nodo Vía: Crear un nodo de vía");
		logger.info("TC2 Crear Nodo Vía: Resultado esperado: true (operación correcta, comprobación de aparición en tabla)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigonodo="0002";
		int numfilaPlaza=nodos.obtenerNumFilaTabla("Código", codigonodo);
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
		datosSalida[3]="Sí";
		crearNodoVia.escribirCampoCodigoVia(datosEntrada[0]);
		crearNodoVia.escribirCampoDireccionVia(datosEntrada[1]);
		crearNodoVia.confirmar();
		nodosvia.recargarTabla();
		String[] resultado=new String[nodosvia.numColumnasTabla()];
		String[] columnas=new String[1];
		columnas[0]="Código";
		String[] valores=new String[1];
		valores[0]=datosEntrada[0];
		resultado=nodosvia.obtenerFilaTabla(columnas, valores);
		boolean iguales=RutinasTestComunes.assertArrayEquals(datosSalida,resultado);
		if (!iguales) {
			LoginTest.majors++;
		}
		logger.info("TC2 Crear Nodo Vía: Resultado obtenido:"+iguales);
		nodosvia.volver();
		nodos.volver();
		assertTrue(iguales);
	}
	
	@Test
	public void testCrearNodoVia3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Crear Nodo Vía");
		logger.info("TC3 Crear Nodo Vía: Probar la validación de la existencia del campo Código");
		logger.info("TC3 Crear Nodo Vía: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigonodo="0002";
		int numfilaPlaza=nodos.obtenerNumFilaTabla("Código", codigonodo);
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
		logger.info("TC3 Crear Nodo Vía: Resultado obtenido:"+hayErrorValidacion);
		crearNodoVia.volver();
		nodosvia.volver();
		nodos.volver();
		assertTrue(hayErrorValidacion);
	}

	@Test
	public void testCrearNodoVia4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Crear Nodo Vía");
		logger.info("TC4 Crear Nodo Vía: Probar la validación del formato del campo Código");
		logger.info("TC4 Crear Nodo Vía: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigonodo="0002";
		int numfilaPlaza=nodos.obtenerNumFilaTabla("Código", codigonodo);
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
		logger.info("TC4 Crear Nodo Vía: Resultado obtenido:"+hayErrorValidacion);
		crearNodoVia.volver();
		nodosvia.volver();
		nodos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearNodoVia5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Crear Nodo Vía");
		logger.info("TC5 Crear Nodo Vía: Probar la validación de la existencia del campo Dirección");
		logger.info("TC5 Crear Nodo Vía: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigonodo="0002";
		int numfilaPlaza=nodos.obtenerNumFilaTabla("Código", codigonodo);
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
		logger.info("TC5 Crear Nodo Vía: Resultado obtenido:"+hayErrorValidacion);
		crearNodoVia.volver();
		nodosvia.volver();
		nodos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearNodoVia6() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6 Crear Nodo Vía");
		logger.info("TC6 Crear Nodo Vía: Probar la validación del formato del campo Dirección");
		logger.info("TC6 Crear Nodo Vía: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigonodo="0002";
		int numfilaPlaza=nodos.obtenerNumFilaTabla("Código", codigonodo);
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
		logger.info("TC6 Crear Nodo Vía: Resultado obtenido:"+hayErrorValidacion);
		crearNodoVia.volver();
		nodosvia.volver();
		nodos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearNodoVia7() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC7 Crear Nodo Vía");
		logger.info("TC7 Crear Nodo Vía: Crear un nodo con un código existente");
		logger.info("TC7 Crear Nodo Vía: Resultado esperado: true (aparece un mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigonodo="0002";
		int numfilaPlaza=nodos.obtenerNumFilaTabla("Código", codigonodo);
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
			logger.error("TC7 Crear Nodo Vía: No ha aparecido el mensaje de error '"+msg_error_codigo_existente+"'");
			LoginTest.majors++;
		}
		logger.info("T7 Crear Nodo Vía: Resultado obtenido:"+(hayMsgError && analisis));
		assertTrue((hayMsgError && analisis));
	}
}
