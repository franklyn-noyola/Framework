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

	final static String titulo="Modificaci�n de nodo de v�a"; 
	final static String subtitulo="Configuraci�n sistema   -   Configuraci�n de peaje   -   Nodos   -   Editar Nodo V�a   -   Modificar"; 
	final static String[] botones= {"Confirmar","Volver"};
	final static String encabezado1="Nodo Plaza";
	final static String[] labelsVariables1= {"C�digo","Descripci�n","Direcci�n"};
	final static String encabezado2="Nodo V�a";
	final static String[] labelsVariables2= {"C�digo"};
	final static String[] labels= {"Direcci�n"};
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
		logger.info("TC1 Modificar Nodo V�a");
		logger.info("TC1 Modificar Nodo V�a: Probar el an�lisis sint�ctico correcto");
		logger.info("TC1 Modificar Nodo V�a: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigonodo="0002";
		int numfilaPlaza=nodos.obtenerNumFilaTabla("C�digo", codigonodo);
		nodos.seleccionarFila(numfilaPlaza);
		String[] valoresVariables1=new String[labelsVariables1.length];
		for (int i =0; i<labelsVariables1.length; i++) {
			valoresVariables1[i]=nodos.obtenerValorColumna(numfilaPlaza, labelsVariables1[i]);
		}
		nodos.editarNodoVia();
		String codigonodovia="LNC010011";
		int numfilanodovia=nodos.obtenerNumFilaTabla("C�digo", codigonodovia);
		nodos.seleccionarFila(numfilanodovia);
		GestionNodosViaProcess nodosvia=new GestionNodosViaProcess();
		String[] valoresVariables2=new String[labelsVariables2.length];
		valoresVariables2[0]=codigonodovia;
		nodosvia.modificar();
		ModificarNodoViaProcess modificarNodoVia=new ModificarNodoViaProcess();
		boolean analisis=modificarNodoVia.sintacticAnalysis(titulo,subtitulo, botones, encabezado1, labelsVariables1, valoresVariables1,
						encabezado2,labelsVariables2, valoresVariables2, labels);
		if (analisis) {
			logger.info("TC1 Modificar Nodo V�a: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC1 Modificar Nodo V�a: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Modificar Nodo V�a: Resultado obtenido="+analisis);
		modificarNodoVia.volver();
		nodosvia.volver();
		nodos.volver();
		assertTrue(analisis);
	}
	
	
	@Test
	public void testModificarNodoVia2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Modificar Nodo V�a");
		logger.info("TC2 Modificar Nodo V�a: Modificar un nodo de v�a");
		logger.info("TC2 Modificar Nodo V�a: Resultado esperado: true (operaci�n correcta, comprobaci�n de aparici�n en tabla)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigonodo="0002";
		int numfilaPlaza=nodos.obtenerNumFilaTabla("C�digo", codigonodo);
		nodos.seleccionarFila(numfilaPlaza);
		nodos.editarNodoVia();
		GestionNodosViaProcess nodosvia=new GestionNodosViaProcess();
		String datosSalida[]=new String[nodosvia.numColumnasTabla()];
		String codigonodovia="LNC010011";
		int numfilanodovia=nodos.obtenerNumFilaTabla("C�digo", codigonodovia);
		nodos.seleccionarFila(numfilanodovia);
		nodosvia.modificar();
		ModificarNodoViaProcess modificarNodoVia=new ModificarNodoViaProcess();
		String datosEntrada[]= {"11.11.11.13"};
		datosSalida[0]=modificarNodoVia.leerCampoCodigoVia();
		datosSalida[1]=datosEntrada[0];
		datosSalida[2]=" ";
		datosSalida[3]="S�";
		modificarNodoVia.escribirCampoDireccionVia(datosEntrada[0]);
		modificarNodoVia.confirmar();
		nodosvia.recargarTabla();
		String[] resultado=new String[nodosvia.numColumnasTabla()];
		String[] columnas=new String[1];
		columnas[0]="C�digo";
		String[] valores=new String[1];
		valores[0]=datosSalida[0];
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
	public void testModificarNodoVia3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Modificar Nodo V�a");
		logger.info("TC3 Modificar Nodo V�a: Probar la validaci�n de la existencia del campo Direcci�n");
		logger.info("TC3 Modificar Nodo V�a: Resultado esperado: true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigonodo="0002";
		int numfilaPlaza=nodos.obtenerNumFilaTabla("C�digo", codigonodo);
		nodos.seleccionarFila(numfilaPlaza);
		nodos.editarNodoVia();
		GestionNodosViaProcess nodosvia=new GestionNodosViaProcess();
		String codigonodovia="LNC010011";
		int numfilanodovia=nodos.obtenerNumFilaTabla("C�digo", codigonodovia);
		nodos.seleccionarFila(numfilanodovia);
		nodosvia.modificar();
		ModificarNodoViaProcess modificarNodoVia=new ModificarNodoViaProcess();
		modificarNodoVia.borrarCampoDireccionVia();
		modificarNodoVia.confirmar();
		boolean hayErrorValidacion=modificarNodoVia.hayErrorValidacionRequeridoDireccionVia();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC3 Modificar Nodo V�a: Resultado obtenido:"+hayErrorValidacion);
		modificarNodoVia.volver();
		nodosvia.volver();
		nodos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testModificarNodoVia4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Modificar Nodo V�a");
		logger.info("TC4 Modificar Nodo V�a: Probar la validaci�n del formato del campo Direcci�n");
		logger.info("TC4 Modificar Nodo V�a: Resultado esperado: true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigonodo="0002";
		int numfilaPlaza=nodos.obtenerNumFilaTabla("C�digo", codigonodo);
		nodos.seleccionarFila(numfilaPlaza);
		nodos.editarNodoVia();
		GestionNodosViaProcess nodosvia=new GestionNodosViaProcess();
		String codigonodovia="LNC010011";
		int numfilanodovia=nodos.obtenerNumFilaTabla("C�digo", codigonodovia);
		nodos.seleccionarFila(numfilanodovia);
		nodosvia.modificar();
		ModificarNodoViaProcess modificarNodoVia=new ModificarNodoViaProcess();
		modificarNodoVia.escribirCampoDireccionVia("!");
		modificarNodoVia.confirmar();
		boolean hayErrorValidacion=modificarNodoVia.hayErrorValidacionFormatoDireccionVia();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC4 Modificar Nodo V�a: Resultado obtenido:"+hayErrorValidacion);
		modificarNodoVia.volver();
		nodosvia.volver();
		nodos.volver();
		assertTrue(hayErrorValidacion);
	}
}
