package testCasesITATAHost.ConfiguracionSistema.Operadores.GestionGruposOperadores;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.Operadores.GestionGruposOperadores.CrearModificarGrupoOperadoresProcess;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionGruposOperadores.GestionGruposOperadoresProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;


public class ModificarGrupoOperadoresTest {

	final static String titulo_modificar_grupooperadores="Gesti�n de grupos de operadores: modificar grupo"; 
	final static String subtitulo_modificar_grupooperadores="Configuraci�n sistema   -   Operadores   -   Gesti�n de grupos   -   Modificar"; 
	final static String[] botones_modificar_grupooperadores= {"Confirmar","Volver"};
	final static String[] labels_modificar_grupooperadores= {"Descripci�n","Acceso a Plaza","Acceso a V�a"};
	final static String encabezadocrud_modificar_grupooperadores="Informaci�n de Grupo";
	final static String encabezadotablas_modificar_grupooperadores="Permisos del grupo";
	final static String[] labelstablas_modificar_grupooperadores={"Permisos asignados","Permisos disponibles"};
	final static String[] botonestablas_modificar_grupooperadores={"<<",">>"};
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(ModificarGrupoOperadoresTest.class);
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
	public void testModificarGruposOperadores1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Modificar Grupo Operadores");
		logger.info("TC1 Modificar Grupo Operadores: Probar un an�lisis sint�ctico correcto");
		logger.info("TC1 Modificar Grupo de operadores: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Operadores","Gesti�n de grupos");
		GestionGruposOperadoresProcess gestionGruposOperadores=new GestionGruposOperadoresProcess();
		gestionGruposOperadores.seleccionarFila(2);
		gestionGruposOperadores.modificar();
		CrearModificarGrupoOperadoresProcess modificarGruposOperadores=new CrearModificarGrupoOperadoresProcess();
		boolean analisis=modificarGruposOperadores.sintacticAnalysis(titulo_modificar_grupooperadores, subtitulo_modificar_grupooperadores,
				botones_modificar_grupooperadores, encabezadocrud_modificar_grupooperadores, labels_modificar_grupooperadores,
				encabezadotablas_modificar_grupooperadores, labelstablas_modificar_grupooperadores, botonestablas_modificar_grupooperadores);
		if (analisis) {
			logger.info("TC1 Modificar Grupo Operadores: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC1 Modificar Grupo Operadores: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		modificarGruposOperadores.volver();
		assertTrue(analisis);
	}
	
	@Test
	public void testModificarGruposOperadores2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Modificar Grupo Operadores");
		logger.info("TC2 Modificar Grupo Operadores: Mover un permiso existente en la tabla de la izq a la derecha y viceversa");
		logger.info("TC2 Modificar Grupo Operadores: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Operadores","Gesti�n de grupos");
		GestionGruposOperadoresProcess gestionGruposOperadores=new GestionGruposOperadoresProcess();
		gestionGruposOperadores.seleccionarFila(2);
		gestionGruposOperadores.modificar();
		CrearModificarGrupoOperadoresProcess modificarGruposOperadores=new CrearModificarGrupoOperadoresProcess();
		String opcion="MCS - Ver v�deo";
		boolean izquierda=modificarGruposOperadores.existeOpcionEnTablaIzquierda(opcion);
		boolean derecha=true;
		if (!izquierda) {
			logger.error("Error: el elemento "+opcion+" no existe en la tabla de la izquierda");
			LoginTest.majors++;
		}
		else {
			modificarGruposOperadores.seleccionarTablaIzquierda(opcion);
			modificarGruposOperadores.botonDerecha();
			derecha=modificarGruposOperadores.existeOpcionEnTablaDerecha(opcion);
			if (!derecha) {
				logger.trace("Error: el elemento "+opcion+" no existe en la tabla de la derecha");
				LoginTest.majors++;
			}
			else {
				logger.debug("Movimiento correcto de la tabla izquierda a la derecha");
				modificarGruposOperadores.seleccionarTablaDerecha(opcion);
				modificarGruposOperadores.botonIzquierda();
				if (modificarGruposOperadores.existeOpcionEnTablaIzquierda(opcion)) {
					logger.trace("Movimiento correcto de la tabla derecha a la izquierda");
				}
				else {
					logger.error("Error: el elemento "+opcion+" no existe en la tabla izquierda");
					LoginTest.majors++;
				}
			}
		}
		logger.error("TC2 Modificar Grupo Operadores: Resultado obtenido="+(izquierda && derecha));
		gestionGruposOperadores.volver();
		assertTrue(izquierda && derecha);
	}

	@Test
	public void testModificarGruposOperadores3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Modificar Grupo Operadores");
		logger.info("TC3 Modificar Grupo Operadores: Probar el error de validaci�n del campo Descripci�n");
		logger.info("TC3 Modificar Grupo Operadores: Resultado esperado: true (aparece el tooltip)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Operadores","Gesti�n de grupos");
		GestionGruposOperadoresProcess gestionGrupoOperadores=new GestionGruposOperadoresProcess();
		String grupo="12";
		int numfila=gestionGrupoOperadores.obtenerNumFilaTabla("Grupo Op.", grupo);
		gestionGrupoOperadores.seleccionarFila(numfila);
		gestionGrupoOperadores.modificar();
		CrearModificarGrupoOperadoresProcess modificarGrupoOperador=new CrearModificarGrupoOperadoresProcess();
		modificarGrupoOperador.borrarCampoDescripcion();
		modificarGrupoOperador.confirmar();
		boolean hayAlertavalidacion=modificarGrupoOperador.hayErrorValidacionDescripcion();
		if (!hayAlertavalidacion) {
			logger.error("TC3 Modificar Grupo Operadores: Deber�a haber aparecido el tooltip de validaci�n de Descripci�n");
			LoginTest.minors++;
		}
		logger.info("TC3 Modificar Grupo Operadores: Resultado obtenido: "+hayAlertavalidacion);
		modificarGrupoOperador.volver();
		gestionGrupoOperadores.volver();
		assertTrue(hayAlertavalidacion);
	}
	
}
