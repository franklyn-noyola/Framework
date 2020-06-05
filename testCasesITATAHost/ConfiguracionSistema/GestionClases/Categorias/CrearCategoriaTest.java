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

	final static String titulo="Gestión de tarifas de clase: creación de tarifa de clase"; 
	final static String subtitulo="Configuración sistema   -   Gestión de clases   -   Categorías   -   Crear"; 
	final static String[] botones= {"Confirmar","Volver"};
	final static String encabezado="Información de categoría";
	final static String[] labels= {"ID Categoría","Descripción","Concesionaria","Tipo"};
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
		logger.info("TC1 Crear Categoría");
		logger.info("TC1 Crear Categoría: Probar el análisis sintáctico correcto");
		logger.info("TC1 Crear Categoría: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Categorías");
		CategoriasProcess categorias=new CategoriasProcess();
		categorias.crear();
		CrearCategoriaProcess crearCategoria=new CrearCategoriaProcess();
		boolean analisis=crearCategoria.sintacticAnalysis(titulo, subtitulo, botones, encabezado, labels);
		if (analisis) {
			logger.info("TC1 Crear Categoría: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Crear Categoría: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Crear Categoría: Resultado obtenido="+analisis);
		crearCategoria.volver();
		categorias.volver();
		assertTrue(analisis);
	}
	
	
	@Test
	public void testCrearCategoria2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Crear Categoría");
		logger.info("TC2 Crear Categoría: Crear una categoría");
		logger.info("TC2 Crear Categoría: Resultado esperado: true (operación correcta, comprobación de aparición en tabla)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Categorías");
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
		columnas[0]="Categoría";
		String[] valores=new String[1];
		valores[0]=datosEntrada[1];
		resultado=categorias.obtenerFilaTabla(columnas, valores);
		boolean iguales=RutinasTestComunes.assertArrayEquals(datosEntrada,resultado);
		if (!iguales) {
			LoginTest.majors++;
		}
		logger.info("TC2 Crear Categoría: Resultado obtenido:"+iguales);
		categorias.volver();
		assertTrue(iguales);
	}
	
	@Test
	public void testCrearCategoria3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Crear Categoría");
		logger.info("TC3 Crear Categoría: Probar la validación de la existencia del campo ID Categoría");
		logger.info("TC3 Crear Categoría: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Categorías");
		CategoriasProcess categorias=new CategoriasProcess();
		categorias.crear();
		CrearCategoriaProcess crearCategoria=new CrearCategoriaProcess();
		crearCategoria.confirmar();
		boolean hayErrorValidacion=crearCategoria.hayErrorValidacionRequeridoIDCategoria();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC3 Crear Categoría: Resultado obtenido:"+hayErrorValidacion);
		crearCategoria.volver();
		assertTrue(hayErrorValidacion);
	}

	@Test
	public void testCrearCategoria4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Crear Categoría");
		logger.info("TC4 Crear Categoría: Probar la validación de la existencia del campo Descripción");
		logger.info("TC4 Crear Categoría: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Categorías");
		CategoriasProcess categorias=new CategoriasProcess();
		categorias.crear();
		CrearCategoriaProcess crearCategoria=new CrearCategoriaProcess();
		crearCategoria.confirmar();
		boolean hayErrorValidacion=crearCategoria.hayErrorValidacionRequeridoDescripcion();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC4 Crear Categoría: Resultado obtenido:"+hayErrorValidacion);
		crearCategoria.volver();
		assertTrue(hayErrorValidacion);
	}

	@Test
	public void testCrearCategoria5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Crear Categoría");
		logger.info("TC5 Crear Categoría: Probar la validación del formato del campo ID Categoría");
		logger.info("TC5 Crear Categoría: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Categorías");
		CategoriasProcess categorias=new CategoriasProcess();
		categorias.crear();
		CrearCategoriaProcess crearCategoria=new CrearCategoriaProcess();
		crearCategoria.escribirCampoIDCategoria("aa");
		crearCategoria.confirmar();
		boolean hayErrorValidacion=crearCategoria.hayErrorValidacionFormatoIDCategoria();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC5 Crear Categoría: Resultado obtenido:"+hayErrorValidacion);
		crearCategoria.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearCategoria6() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6 Crear Categoría");
		logger.info("TC6 Crear Categoría: Crear una categoría con un ID existente");
		logger.info("TC6 Crear Categoría: Resultado esperado: true (aparece un mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Categorías");
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
			logger.error("TC6 Crear Categoría: No ha aparecido el mensaje de error '"+msg_error_codigo_existente+"'");
			LoginTest.majors++;
		}
		logger.info("T6 Crear Categoría: Resultado obtenido:"+(hayMsgError && analisis));
		assertTrue((hayMsgError && analisis));
	}
}
