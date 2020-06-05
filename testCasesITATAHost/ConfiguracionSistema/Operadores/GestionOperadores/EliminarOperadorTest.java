package testCasesITATAHost.ConfiguracionSistema.Operadores.GestionOperadores;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.GestionOperadoresProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import unidadesGraficas.AlertPopup;
import unidadesGraficas.ErrorMessage;


public class EliminarOperadorTest {

	final static String titulo_ver_operador="Gestión de operadores: operador"; 
	final static String subtitulo_ver_operador="Configuración sistema   -   Operadores   -   Gestión de operadores   -   Ver/modificar"; 
	final static String encabezadocrud_ver_operador1="Información de Operador";
	final static String[] botones_ver_operador= {"Confirmar","Volver"};
	final static String[] labels_ver_operadorvariables= {"Concesionaria","ID de Operador"};
	final static String[] labels_ver_operador1= {"Nombre","Apellidos","Dirección","C.P.","Ciudad",
			"País","E-mail","Teléfono","Título","Género","Grupo Op.","Fecha nacimiento"};
	final static String encabezadocrud_ver_operador2="Estado";
	final static String label_ver_operador2="Suspendido";
	final static String encabezadocrud_ver_operador3="Contraseña";
	final static String label_ver_operador3="Nueva contraseña";
	final static String msg_error_noseleccionado="Ningún elemento seleccionado";
	final static String alerta_confirmacion_borrado="¿Está seguro de que desea eliminar el operador?";
	
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(EliminarOperadorTest.class);
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
	public void testEliminarOperador1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Eliminar Operador");
		logger.info("TC1 Eliminar Operador: Probar la eliminación correcta de un operador cancelando el borrado");
		logger.info("TC1 Eliminar Operador: Resultado esperado: true (no aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		String operador="00185";
		gestionOperadores.escribirOpcionOperador(operador);
		gestionOperadores.busqueda();
		if (gestionOperadores.numFilasTabla()!=1) {
			logger.error("TC1 Eliminar Operador: el número de filas obtenidas en la tabla tras la búsqueda es incorrecto");
			LoginTest.majors++;
			fail();
		}
		gestionOperadores.seleccionarFila(1);
		gestionOperadores.eliminar();
		boolean analisis=false;
		boolean encontrado=false;
		boolean hayAlerta=gestionOperadores.hayAlerta();
		if (hayAlerta) {
			AlertPopup alerta=gestionOperadores.alerta();
			analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
			if (!analisis) {
				LoginTest.minors++;
			}
			gestionOperadores.cancelarAlerta();
			gestionOperadores.recargarTabla();
			encontrado=(gestionOperadores.obtenerFilaTabla("Operador", operador)!=null);
			if (encontrado) {
				logger.error("TC1 Eliminar Operador: Debería haber aparecido el operador '"+operador+"' en la tabla");
				LoginTest.majors++;
			}
		}
		logger.info("TC1 Eliminar Operador: Resultado obtenido: "+(hayAlerta && analisis && encontrado));
		gestionOperadores.volver();
		assertTrue(hayAlerta && analisis && encontrado);
	}

	@Test
	public void testEliminarOperador2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Eliminar Operador");
		logger.info("TC1 Eliminar Operador: Probar la eliminación incorrecta sin seleccionar operador");
		logger.info("TC1 Eliminar Operador: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.eliminar();
		boolean analisis=false;
		boolean hayMsgError=gestionOperadores.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=gestionOperadores.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC1 Eliminar Operador: Debería haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		assertTrue(hayMsgError || analisis);
	}
	
	@Test
	public void testEliminarOperador3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Eliminar Operador");
		logger.info("TC3 Eliminar Operador: Probar la eliminación de un operador inexistente");
		logger.info("TC3 Eliminar Operador: Resultado esperado: true (no se encuentra el operador en la búsqueda)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		String operador="00083";
		gestionOperadores.escribirOpcionOperador(operador);
		gestionOperadores.busqueda();
		int num_filasobt=gestionOperadores.numFilasTabla();
		if (num_filasobt==0) {
			logger.error("TC3 Eliminar Operador: Resultado esperado: "+(num_filasobt==0));
			assertTrue(true);
		}
		else {
			logger.error("TC3 Eliminar Operador: el número de filas obtenidas en la tabla tras la búsqueda es incorrecto");
			LoginTest.majors++;
			fail();
		}
	}

	@Test
	public void testEliminarOperador4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Eliminar Operador");
		logger.info("TC4 Eliminar Operador: Probar la eliminación correcta de un operador aceptando el borrado");
		logger.info("TC4 Eliminar Operador: Resultado esperado: true (el operador deja de aparecer en la tabla)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		String operador="00189";
		gestionOperadores.escribirOpcionOperador(operador);
		gestionOperadores.busqueda();
		if (gestionOperadores.numFilasTabla()!=1) {
			logger.error("TC4 Eliminar Operador: el número de filas obtenidas en la tabla tras la búsqueda es incorrecto");
			LoginTest.majors++;
			fail();
		}
		gestionOperadores.seleccionarFila(1);
		gestionOperadores.eliminar();
		boolean analisis=false;
		boolean encontrado=false;
		boolean hayAlerta=gestionOperadores.hayAlerta();
		if (hayAlerta) {
			AlertPopup alerta=gestionOperadores.alerta();
			analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
			if (!analisis) {
				LoginTest.minors++;
			}
			gestionOperadores.aceptarAlerta();
			gestionOperadores.recargarTabla();
			encontrado=(gestionOperadores.obtenerFilaTabla("Operador", operador)!=null);
			if (encontrado) {
				logger.error("TC4 Eliminar Operador: No debería haber aparecido el operador borrado '"+operador+"' en la tabla");
				LoginTest.majors++;
			}
		}
		logger.info("TC4 Eliminar Operador: Resultado obtenido: "+(hayAlerta && analisis && !encontrado));
		gestionOperadores.volver();
		assertTrue(hayAlerta && analisis && !encontrado);
	}
}

