package testCasesITATAHost.ConfiguracionSistema.GestionClases.Categorias;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.GestionClases.Categorias.CategoriasProcess;
import procesosITATAHost.ConfiguracionSistema.GestionClases.Categorias.CrearCategoriaProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.ErrorMessage;

public class CrearCategoriaTest {

	final static String titulo="Gesti�n de tarifas de clase: creaci�n de tarifa de clase"; 
	final static String subtitulo="Configuraci�n sistema   -   Gesti�n de clases   -   Categor�as   -   Crear"; 
	final static String[] botones= {"Confirmar","Volver"};
	final static String encabezado="Informaci�n de categor�a";
	final static String[] labels= {"ID Categor�a","Descripci�n","Concesionaria","Tipo"};
	final static String msg_error_codigo_existente="Ya existe una tarifa con el ID indicado";
	
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(CrearCategoriaTest.class);
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
	public void testCrearCategoria1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Crear Categor�a");
		logger.info("TC1 Crear Categor�a: Probar el an�lisis sint�ctico correcto");
		logger.info("TC1 Crear Categor�a: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Gesti�n de clases","Categor�as");
		CategoriasProcess categorias=new CategoriasProcess();
		categorias.crear();
		CrearCategoriaProcess crearCategoria=new CrearCategoriaProcess();
		boolean analisis=crearCategoria.sintacticAnalysis(titulo, subtitulo, botones, encabezado, labels);
		if (analisis) {
			logger.info("TC1 Crear Categor�a: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC1 Crear Categor�a: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Crear Categor�a: Resultado obtenido="+analisis);
		crearCategoria.volver();
		categorias.volver();
		assertTrue(analisis);
	}
	
	
	@Test
	public void testCrearCategoria2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Crear Categor�a");
		logger.info("TC2 Crear Categor�a: Crear una categor�a");
		logger.info("TC2 Crear Categor�a: Resultado esperado: true (operaci�n correcta, comprobaci�n de aparici�n en tabla)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Gesti�n de clases","Categor�as");
		CategoriasProcess categorias=new CategoriasProcess();
		categorias.crear();
		CrearCategoriaProcess crearCategoria=new CrearCategoriaProcess();
		String[] datosEntrada= {"00: Descripcion bb","00","Descripcion aa","Ligero"};
		crearCategoria.seleccionarCampoConcesionaria(datosEntrada[0]);
		crearCategoria.escribirCampoIDCategoria(datosEntrada[1]);
		crearCategoria.escribirCampoDescripcion(datosEntrada[2]);
		crearCategoria.seleccionarCampoTipo(datosEntrada[3]);
		crearCategoria.confirmar();
		categorias.recargarTabla();
		String[] resultado=new String[4];
		datosEntrada[0]=datosEntrada[0].substring(4);
		String[] columnas=new String[1];
		columnas[0]="Categor�a";
		String[] valores=new String[1];
		valores[0]=datosEntrada[1];
		resultado=categorias.obtenerFilaTabla(columnas, valores);
		boolean iguales=RutinasTestComunes.assertArrayEquals(datosEntrada,resultado);
		if (!iguales) {
			LoginTest.majors++;
		}
		logger.info("TC2 Crear Categor�a: Resultado obtenido:"+iguales);
		categorias.volver();
		assertTrue(iguales);
	}
	
	@Test
	public void testCrearCategoria3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Crear Categor�a");
		logger.info("TC3 Crear Categor�a: Probar la validaci�n de la existencia del campo ID Categor�a");
		logger.info("TC3 Crear Categor�a: Resultado esperado: true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Gesti�n de clases","Categor�as");
		CategoriasProcess categorias=new CategoriasProcess();
		categorias.crear();
		CrearCategoriaProcess crearCategoria=new CrearCategoriaProcess();
		crearCategoria.confirmar();
		boolean hayErrorValidacion=crearCategoria.hayErrorValidacionRequeridoIDCategoria();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC3 Crear Categor�a: Resultado obtenido:"+hayErrorValidacion);
		crearCategoria.volver();
		assertTrue(hayErrorValidacion);
	}

	@Test
	public void testCrearCategoria4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Crear Categor�a");
		logger.info("TC4 Crear Categor�a: Probar la validaci�n de la existencia del campo Descripci�n");
		logger.info("TC4 Crear Categor�a: Resultado esperado: true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Gesti�n de clases","Categor�as");
		CategoriasProcess categorias=new CategoriasProcess();
		categorias.crear();
		CrearCategoriaProcess crearCategoria=new CrearCategoriaProcess();
		crearCategoria.confirmar();
		boolean hayErrorValidacion=crearCategoria.hayErrorValidacionRequeridoDescripcion();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC4 Crear Categor�a: Resultado obtenido:"+hayErrorValidacion);
		crearCategoria.volver();
		assertTrue(hayErrorValidacion);
	}

	@Test
	public void testCrearCategoria5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Crear Categor�a");
		logger.info("TC5 Crear Categor�a: Probar la validaci�n del formato del campo ID Categor�a");
		logger.info("TC5 Crear Categor�a: Resultado esperado: true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Gesti�n de clases","Categor�as");
		CategoriasProcess categorias=new CategoriasProcess();
		categorias.crear();
		CrearCategoriaProcess crearCategoria=new CrearCategoriaProcess();
		crearCategoria.escribirCampoIDCategoria("aa");
		crearCategoria.confirmar();
		boolean hayErrorValidacion=crearCategoria.hayErrorValidacionFormatoIDCategoria();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC5 Crear Categor�a: Resultado obtenido:"+hayErrorValidacion);
		crearCategoria.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearCategoria6() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6 Crear Categor�a");
		logger.info("TC6 Crear Categor�a: Crear una categor�a con un ID existente");
		logger.info("TC6 Crear Categor�a: Resultado esperado: true (aparece un mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Gesti�n de clases","Categor�as");
		CategoriasProcess categorias=new CategoriasProcess();
		categorias.crear();
		CrearCategoriaProcess crearCategoria=new CrearCategoriaProcess();
		String[] datosEntrada= {"00: Descripcion bb","00","Descripcion aa","Ligero"};
		crearCategoria.seleccionarCampoConcesionaria(datosEntrada[0]);
		crearCategoria.escribirCampoIDCategoria(datosEntrada[1]);
		crearCategoria.escribirCampoDescripcion(datosEntrada[2]);
		crearCategoria.seleccionarCampoTipo(datosEntrada[3]);
		crearCategoria.confirmar();
		boolean hayMsgError=crearCategoria.hayMensajeError();
		boolean analisis=false;
		if (hayMsgError) {
			ErrorMessage errormsgobt=crearCategoria.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_codigo_existente);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC6 Crear Categor�a: No ha aparecido el mensaje de error '"+msg_error_codigo_existente+"'");
			LoginTest.majors++;
		}
		logger.info("T6 Crear Categor�a: Resultado obtenido:"+(hayMsgError && analisis));
		assertTrue((hayMsgError && analisis));
	}
}
