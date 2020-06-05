package testCasesITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.GestionNodosViaProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.ModificarNodoViaProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.NodosProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;

public class ModificarNodoViaTest {

	final static String titulo="Modificación de nodo de vía"; 
	final static String subtitulo="Configuración sistema   -   Configuración de peaje   -   Nodos   -   Editar Nodo Vía   -   Modificar"; 
	final static String[] botones= {"Confirmar","Volver"};
	final static String encabezado1="Nodo Plaza";
	final static String[] labelsVariables1= {"Código","Descripción","Dirección"};
	final static String encabezado2="Nodo Vía";
	final static String[] labelsVariables2= {"Código"};
	final static String[] labels= {"Dirección"};
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(ModificarNodoViaTest.class);
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
	public void testModificarNodoVia1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Modificar Nodo Vía");
		logger.info("TC1 Modificar Nodo Vía: Probar el análisis sintáctico correcto");
		logger.info("TC1 Modificar Nodo Vía: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigonodo="0002";
		int numfilaPlaza=nodos.obtenerNumFilaTabla("Código", codigonodo);
		nodos.seleccionarFila(numfilaPlaza);
		String[] valoresVariables1=new String[labelsVariables1.length];
		for (int i =0; i<labelsVariables1.length; i++) {
			valoresVariables1[i]=nodos.obtenerValorColumna(numfilaPlaza, labelsVariables1[i]);
		}
		nodos.editarNodoVia();
		String codigonodovia="LNC010011";
		int numfilanodovia=nodos.obtenerNumFilaTabla("Código", codigonodovia);
		nodos.seleccionarFila(numfilanodovia);
		GestionNodosViaProcess nodosvia=new GestionNodosViaProcess();
		String[] valoresVariables2=new String[labelsVariables2.length];
		valoresVariables2[0]=codigonodovia;
		nodosvia.modificar();
		ModificarNodoViaProcess modificarNodoVia=new ModificarNodoViaProcess();
		boolean analisis=modificarNodoVia.sintacticAnalysis(titulo,subtitulo, botones, encabezado1, labelsVariables1, valoresVariables1,
						encabezado2,labelsVariables2, valoresVariables2, labels);
		if (analisis) {
			logger.info("TC1 Modificar Nodo Vía: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Modificar Nodo Vía: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Modificar Nodo Vía: Resultado obtenido="+analisis);
		modificarNodoVia.volver();
		nodosvia.volver();
		nodos.volver();
		assertTrue(analisis);
	}
	
	
	@Test
	public void testModificarNodoVia2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Modificar Nodo Vía");
		logger.info("TC2 Modificar Nodo Vía: Modificar un nodo de vía");
		logger.info("TC2 Modificar Nodo Vía: Resultado esperado: true (operación correcta, comprobación de aparición en tabla)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigonodo="0002";
		int numfilaPlaza=nodos.obtenerNumFilaTabla("Código", codigonodo);
		nodos.seleccionarFila(numfilaPlaza);
		nodos.editarNodoVia();
		GestionNodosViaProcess nodosvia=new GestionNodosViaProcess();
		String datosSalida[]=new String[nodosvia.numColumnasTabla()];
		String codigonodovia="LNC010011";
		int numfilanodovia=nodos.obtenerNumFilaTabla("Código", codigonodovia);
		nodos.seleccionarFila(numfilanodovia);
		nodosvia.modificar();
		ModificarNodoViaProcess modificarNodoVia=new ModificarNodoViaProcess();
		String datosEntrada[]= {"11.11.11.13"};
		datosSalida[0]=modificarNodoVia.leerCampoCodigoVia();
		datosSalida[1]=datosEntrada[0];
		datosSalida[2]=" ";
		datosSalida[3]="Sí";
		modificarNodoVia.escribirCampoDireccionVia(datosEntrada[0]);
		modificarNodoVia.confirmar();
		nodosvia.recargarTabla();
		String[] resultado=new String[nodosvia.numColumnasTabla()];
		String[] columnas=new String[1];
		columnas[0]="Código";
		String[] valores=new String[1];
		valores[0]=datosSalida[0];
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
	public void testModificarNodoVia3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Modificar Nodo Vía");
		logger.info("TC3 Modificar Nodo Vía: Probar la validación de la existencia del campo Dirección");
		logger.info("TC3 Modificar Nodo Vía: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigonodo="0002";
		int numfilaPlaza=nodos.obtenerNumFilaTabla("Código", codigonodo);
		nodos.seleccionarFila(numfilaPlaza);
		nodos.editarNodoVia();
		GestionNodosViaProcess nodosvia=new GestionNodosViaProcess();
		String codigonodovia="LNC010011";
		int numfilanodovia=nodos.obtenerNumFilaTabla("Código", codigonodovia);
		nodos.seleccionarFila(numfilanodovia);
		nodosvia.modificar();
		ModificarNodoViaProcess modificarNodoVia=new ModificarNodoViaProcess();
		modificarNodoVia.borrarCampoDireccionVia();
		modificarNodoVia.confirmar();
		boolean hayErrorValidacion=modificarNodoVia.hayErrorValidacionRequeridoDireccionVia();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC3 Modificar Nodo Vía: Resultado obtenido:"+hayErrorValidacion);
		modificarNodoVia.volver();
		nodosvia.volver();
		nodos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testModificarNodoVia4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Modificar Nodo Vía");
		logger.info("TC4 Modificar Nodo Vía: Probar la validación del formato del campo Dirección");
		logger.info("TC4 Modificar Nodo Vía: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigonodo="0002";
		int numfilaPlaza=nodos.obtenerNumFilaTabla("Código", codigonodo);
		nodos.seleccionarFila(numfilaPlaza);
		nodos.editarNodoVia();
		GestionNodosViaProcess nodosvia=new GestionNodosViaProcess();
		String codigonodovia="LNC010011";
		int numfilanodovia=nodos.obtenerNumFilaTabla("Código", codigonodovia);
		nodos.seleccionarFila(numfilanodovia);
		nodosvia.modificar();
		ModificarNodoViaProcess modificarNodoVia=new ModificarNodoViaProcess();
		modificarNodoVia.escribirCampoDireccionVia("!");
		modificarNodoVia.confirmar();
		boolean hayErrorValidacion=modificarNodoVia.hayErrorValidacionFormatoDireccionVia();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC4 Modificar Nodo Vía: Resultado obtenido:"+hayErrorValidacion);
		modificarNodoVia.volver();
		nodosvia.volver();
		nodos.volver();
		assertTrue(hayErrorValidacion);
	}
}
