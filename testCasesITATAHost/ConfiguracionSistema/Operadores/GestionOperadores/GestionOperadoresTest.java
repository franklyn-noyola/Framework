package testCasesITATAHost.ConfiguracionSistema.Operadores.GestionOperadores;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.CrearOperadorProcess;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.GestionOperadoresProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;


public class GestionOperadoresTest {

	final static String titulo_gestion_operadores="Gestión de operadores"; 
	final static String subtitulo_gestion_operadores="Configuración sistema   -   Operadores   -   Gestión de operadores"; 
	final static String[] botones_gestion_operadores={"Informe","Búsqueda","Enviar a vías","Volver"};  
	final static String[] labels_gestion_operadores= {"Operador","Nombre","PAN","Grupo Op.","Estado"}; //Primero se indican los criterios-campo y luego los criterios-desplegable
	final static String[] columnasgrid_operadores= {"Operador","Título","Nombre","Apellidos","PAN","Estado","Grupo Op."};
	final static String[] botonescrud_operadores= {"Crear","Ver/modificar","Eliminar"};
	final static String[] botonesgrid_operadores= {"Primera","<",">","Última"};
	static InitBOTest botest;
	static Connection DBconnection;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(GestionOperadoresTest.class);
		logger.debug("setUpBeforeClass-GestionOperadoresTest");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		logger.debug("tearDownAfterClass-GestionOperadoresTest");
		InitBOTest.tearDownAfterClass();
	}
	
	@After
	public void AfterTest() throws Exception {
		logger.info("Fin TC");
		logger.info("-----------------------------------------------------------------");
	}
	
	@Test
	public void testGestionOperadores1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Gestion Operadores");
		logger.info("TC1 Gestion Operadores: Probar un análisis sintáctico correcto");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		boolean analisis=gestionOperadores.sintacticAnalysis(titulo_gestion_operadores, subtitulo_gestion_operadores,
				botones_gestion_operadores, botonescrud_operadores, labels_gestion_operadores, columnasgrid_operadores,
				botonesgrid_operadores);
		if (analisis) {
			logger.info("TC1 Gestión Operadores: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Gestión Operadores: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		assertTrue(analisis);
	}

	@Test
	public void testGestionOperadores2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Gestion Operadores");
		logger.info("TC2 Gestion Operadores: Probar el error de validación de campo Nombre");
		logger.info("TC2 Gestion Operadores: Resultado esperado: true (aparece el tooltip)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.crear();
		CrearOperadorProcess crearOperador=new CrearOperadorProcess();
		crearOperador.confirmar();
		boolean hayAlertavalidacion=crearOperador.hayErrorValidacionNombre();
		if (!hayAlertavalidacion) {
			logger.error("TC2 Gestion Operadores: Debería haber aparecido el tooltip de validación de Nombre");
			LoginTest.minors++;
		}
		logger.info("TC2 Gestion Operadores: Resultado obtenido: "+hayAlertavalidacion);
		assertTrue(hayAlertavalidacion);
	}
	
	@Test
	public void testGestionOperadores3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Gestion Operadores");
		logger.info("TC3 Gestion Operadores: Probar el error de validación de campo Apellidos");
		logger.info("TC3 Gestion Operadores: Resultado esperado: true (aparece el tooltip)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.crear();
		CrearOperadorProcess crearOperador=new CrearOperadorProcess();
		crearOperador.confirmar();
		boolean hayAlertavalidacion=crearOperador.hayErrorValidacionApellidos();
		if (!hayAlertavalidacion) {
			logger.error("TC3 Gestion Operadores: Debería haber aparecido el tooltip de validación de Apellidos");
			LoginTest.minors++;
		}
		logger.info("TC3 Gestion Operadores: Resultado obtenido: "+hayAlertavalidacion);
		assertTrue(hayAlertavalidacion);
	}
	
	@Test
	public void testGestionOperadores4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Gestion Operadores");
		logger.info("TC4 Gestion Operadores: Probar el error de validación de campo Contraseña");
		logger.info("TC4 Gestion Operadores: Resultado esperado: true (aparece el tooltip)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.crear();
		CrearOperadorProcess crearOperador=new CrearOperadorProcess();
		crearOperador.confirmar();
		boolean hayAlertavalidacion=crearOperador.hayErrorValidacionContraseña();
		if (!hayAlertavalidacion) {
			logger.error("TC4 Gestion Operadores: Debería haber aparecido el tooltip de validación de Contraseña");
			LoginTest.minors++;
		}
		logger.info("TC4 Gestion Operadores: Resultado obtenido: "+hayAlertavalidacion);
		assertTrue(hayAlertavalidacion);
	}
	
	@Test
	public void testGestionOperadores5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Gestion Operadores");
		logger.info("TC5 Gestion Operadores: Probar la edición de un elemento inexistente");
		logger.info("TC5 Gestion Operadores: Resultado esperado=true");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		String operador="00083";
		gestionOperadores.escribirOpcionOperador(operador);
		gestionOperadores.busqueda();
		int num_filasobt=gestionOperadores.numFilasTabla();
		if (num_filasobt!=0) {
			logger.error("TC5 Gestion Operadores: el número de filas obtenidas en la tabla debería ser 0");
			LoginTest.majors++;
		}
		logger.info("TC5 Gestion Operadores: Resultado obtenido="+(num_filasobt==0));
		assertTrue(num_filasobt==0);
	}
	
	@Test
	public void testGestionOperadores6() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6 Gestion Operadores");
		logger.info("TC6 Gestion Operadores: Comprobación del contenido de la tabla contra BD");
		logger.info("TC6 Gestion Operadores: Resultado esperado=true");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.busqueda();
		
		// Se valida que la query de la BD está contenida en la Tabla
		boolean iguales=true;
		try {
			String queryString = "select operator,title,forename,surname,pan,status,aoperator.opgroup,aopgroup.description"
					+ " from dbo.aoperator,dbo.aopgroup where aoperator.opgroup=aopgroup.opgroup order by tollcompany,operator";
			Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(queryString);
			int tamaño=RutinasTestComunes.obtenerTamañoConsulta(rs);
			if (tamaño!=gestionOperadores.numFilasTotalTabla()) {
				logger.error("TC6 Gestion Operadores: El número de registros de la búsqueda es " +tamaño+" y no coincide con el de la tabla: "+gestionOperadores.numFilasTabla());
				LoginTest.critical++;
				iguales=false;
			}
			while (rs.next() && iguales) {
				iguales=gestionOperadores.buscarRegistroBDEnTabla(rs);
			}
			if (!iguales) {
				LoginTest.critical++;
			}
		}
		catch (Exception e) {
			logger.fatal("TC6 Gestion Operadores: Error al acceder a la BD");
			LoginTest.critical++;
			e.printStackTrace();
			fail();
		}
		
		// Se valida que el contenido de la Tabla está en BD
		gestionOperadores.pulsarBotonTablaPrimero();
		boolean iguales2=true;
		do {
			gestionOperadores.recargarTabla();
			for (int num_fila=1; (num_fila<=gestionOperadores.numFilasTabla()) && iguales2; num_fila++) {
				String operador=gestionOperadores.obtenerValorColumna(num_fila, "Operador");
				String queryString = "select operator,title,forename,surname,pan,status,aoperator.opgroup,aopgroup.description"
					+ " from dbo.aoperator,dbo.aopgroup where aoperator.opgroup=aopgroup.opgroup and operator="+operador;
				try {
					Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs = stmt.executeQuery(queryString);
					rs.next();
					iguales2=gestionOperadores.buscarRegistroTablaEnBD(rs, operador);
					if (!iguales2) {
						logger.error("TC6 Gestion Operadores: El registro de la tabla " +operador+" no coincide con el de la BD.");
						LoginTest.majors++;
					}
				}
				catch (Exception e) {
					logger.fatal("TC6 Gestion Operadores: Error al acceder a la BD");
					LoginTest.critical++;
					e.printStackTrace();
					fail();
				}
			}
			if (!gestionOperadores.ultimaTabla()) {
				gestionOperadores.pulsarBotonTablaSiguiente();
			}
		}
		while (!gestionOperadores.ultimaTabla() && iguales2);
		logger.info("TC6 Gestion Operadores: Resultado obtenido="+(iguales && iguales2));
		assertTrue(iguales && iguales2);
	}
}
