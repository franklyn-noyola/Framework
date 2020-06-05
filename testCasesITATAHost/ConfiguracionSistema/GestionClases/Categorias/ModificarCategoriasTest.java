package testCasesITATAHost.ConfiguracionSistema.GestionClases.Categorias;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.ConcesionariasProcess;
import procesosITATAHost.ConfiguracionSistema.GestionClases.Categorias.CategoriasProcess;
import procesosITATAHost.ConfiguracionSistema.GestionClases.Categorias.ModificarCategoriaProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.ErrorMessage;


public class ModificarCategoriasTest {

	final static String titulo="Gestión de tarifas de clase: modificación de tarifas de clase"; 
	final static String subtitulo="Configuración sistema   -   Gestión de clases   -   Categorías   -   Modificar"; 
	final static String encabezado="Información de categoría";
	final static String[] botones= {"Confirmar","Volver"};
	final static String[] labels_variables= {"Concesionaria","ID Categoría"};
	final static String[] labels= {"Descripción","Tipo"};
	final static String msg_error_noseleccionado="Ningún elemento seleccionado";
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass1() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(ModificarCategoriasTest.class);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		InitBOTest.tearDownAfterClass();
	}
	
	@After
	public void afterTest() throws Exception {
		logger.info("Fin TC");
		logger.info("-----------------------------------------------------------------");
	}
	
	@Test
	public void testModificarCategoria1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Modificar categoría");
		logger.info("TC1 Modificar categoría: Probar que el análisis sintáctico es correcto");
		logger.info("TC1: Modificar categoría: resultado esperado=true (operación correcta)");
		String[] valoresvariables=new String[2];
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Categorías");
		CategoriasProcess categorias=new CategoriasProcess();
		int numfila=1;
		String columna="Concesionaria";
		String valor=categorias.obtenerValorColumna(numfila,columna);
		if (valor==null) {
			logger.error("TC1 Modificar categoría: El nombre de la columna especificada no existe: "+columna);
		}
		else {
			// Necesitamos obtener el código de la concesionaria
			botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
			ConcesionariasProcess concesionarias=new ConcesionariasProcess();
			String codigoConcesionaria=concesionarias.obtenerValorColumna("ID","Nombre",valor);
			valoresvariables[0]=codigoConcesionaria+": "+valor;
			botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Categorías");
			categorias=new CategoriasProcess();
		}
		columna="Categoría";	
		valor=categorias.obtenerValorColumna(numfila,columna);
		if (valor==null) {
			logger.error("TC1 Modificar categoría: El nombre de la columna especificada no existe: "+columna);
		}
		else {
			valoresvariables[1]=valor;
		}
		categorias.seleccionarFila(numfila);
		categorias.modificar();
		ModificarCategoriaProcess modificarCategoria=new ModificarCategoriaProcess();
		boolean analisis=modificarCategoria.sintacticAnalysis(titulo, subtitulo,botones, encabezado, labels_variables,
				valoresvariables,labels);
		if (!analisis) {
			LoginTest.minors++;
		}
		logger.info("TC1: Modificar categoría: resultado obtenido="+analisis);
		assertTrue(analisis);
	}
		
	
	@Test
	public void testModificarCategoria2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Modificar categoría");
		logger.info("TC2 Modificar categoría: Comprobar que al editar un elemento, los valores de la tabla coinciden"
				+ " con los cargados en la página de Modificar Categoría");
		logger.info("TC2: Modificar categoría: resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Categorías");
		CategoriasProcess categorias=new CategoriasProcess();
		String filatabla[]=new String[categorias.numColumnasTabla()];
		String datosesperados[]=new String[categorias.numColumnasTabla()];
		filatabla=categorias.obtenerFilaTabla(1);
		categorias.seleccionarFila(1);
		categorias.modificar();
		ModificarCategoriaProcess modificarCategoria=new ModificarCategoriaProcess();
		datosesperados[0]=modificarCategoria.leerCampoConcesionaria();
		datosesperados[1]=modificarCategoria.leerCampoID();
		datosesperados[2]=modificarCategoria.leerCampoDescripcion();
		datosesperados[3]=modificarCategoria.leerOpcionTipo();
		datosesperados[0]=datosesperados[0].substring(4); //Eliminamos el prefijo "xx: " de la Concesionaria para comparar con la tabla
		boolean iguales=RutinasTestComunes.assertArrayEquals(filatabla,datosesperados);
	    if (!iguales) {
	    	logger.error("TC2 Modificar categoría: la categoría editada no coincide con la de la tabla");
	    	LoginTest.majors++;
	       }
		logger.info("TC2: Modificar categoría: resultado obtenido="+iguales);
		assertTrue(iguales);
	}
	
	@Test
	public void testModificarCategoria3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Modificar categoría");
		logger.info("TC3 Modificar categoría: Probar la modificación de un elemento existente y comprobar que coincide con el"
				+ " de la tabla una vez modificado");
		logger.info("TC3: Modificar categoría: resultado esperado=true (operación correcta)");
		String[] datosEntrada={"Descripcion aa","Pesado"};
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Categorías");
		CategoriasProcess categorias=new CategoriasProcess();
		categorias.seleccionarFila(1);
		categorias.modificar();
		ModificarCategoriaProcess modificarCategoria=new ModificarCategoriaProcess();
		String datosesperados[]=new String[categorias.numColumnasTabla()];
		modificarCategoria.escribirCampoDescripcion(datosEntrada[0]);
		modificarCategoria.seleccionarOpcionTipo(datosEntrada[1]);
		datosesperados[0]=modificarCategoria.leerCampoConcesionaria();
		datosesperados[1]=modificarCategoria.leerCampoID();
		datosesperados[2]=modificarCategoria.leerCampoDescripcion();
		datosesperados[3]=modificarCategoria.leerOpcionTipo();
		datosesperados[0]=datosesperados[0].substring(4); //Eliminamos el prefijo "xx: " de la Concesionaria para comparar con la tabla
		modificarCategoria.confirmar();
		String filatabla[]=new String[categorias.numColumnasTabla()];
		filatabla=categorias.obtenerFilaTabla(1);
		boolean iguales=RutinasTestComunes.assertArrayEquals(filatabla,datosesperados);
	    if (!iguales) {
	    	logger.error("TC3 Modificar categoría: la categoría editada no coincide con la que devuelve la tabla");
	    	LoginTest.majors++;
	       }
		logger.info("TC3: Modificar categoría: resultado obtenido="+iguales);
		assertTrue(iguales);
	}
	
	@Test
	public void testModificarCategoria4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Modificar categoría");
		logger.info("TC4 Modificar categoría: Probar la validación del campo Descripción");
		logger.info("TC4 Modificar categoría: resultado esperado=true (aparece el tooltip correspondiente)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Categorías");
		CategoriasProcess categorias=new CategoriasProcess();
		categorias.seleccionarFila(1);
		categorias.modificar();
		ModificarCategoriaProcess modificarCategoria=new ModificarCategoriaProcess();
		modificarCategoria.borrarCampoDescripcion();
		modificarCategoria.confirmar();
		boolean hayMsgErrorReq=modificarCategoria.hayErrorValidacionDescripcion();
		if (!hayMsgErrorReq) {
			LoginTest.minors++;
			logger.error("TC4 Modificar categoría: Debería haber aparecido el tooltip de 'Campo requerido'");
		}
		logger.info("TC4 Modificar categoría: resultado obtenido="+hayMsgErrorReq);
		assertTrue(hayMsgErrorReq);
	}
	
	@Test
	public void testModificarCategoria5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Modificar categoría");
		logger.info("TC5 Modificar categoría: Probar la modificación incorrecta sin seleccionar categoría");
		logger.info("TC5 Modificar categoría: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		concesionarias.modificar();
		boolean analisis=false;
		boolean hayMsgError=concesionarias.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=concesionarias.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC5 Modificar categoría: Debería haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC5 Modificar categoría: Resultado obtenido="+(hayMsgError && analisis));
		concesionarias.volver();
		assertTrue(hayMsgError && analisis);
	}
}
