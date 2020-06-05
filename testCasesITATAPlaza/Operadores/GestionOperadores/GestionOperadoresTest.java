package testCasesITATAPlaza.Operadores.GestionOperadores;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAPlaza.Operadores.GestionOperadores.GestionOperadoresProcess;
import testCasesITATAPlaza.InitBOTest;
import testCasesITATAPlaza.LoginTest;
import testsComunes.RutinasTestComunes;


public class GestionOperadoresTest {

	final static String titulo_gestion_operadores="Gestión de operadores"; 
	final static String subtitulo_gestion_operadores="Configuración sistema   -   Operadores   -   Gestión de operadores"; 
	final static String[] botones_gestion_operadores={"Informe","Búsqueda","Volver"};  
	final static String[] labels_gestion_operadores= {"Operador","Nombre","PAN","Grupo Op.","Estado"}; //Primero se indican los criterios-campo y luego los criterios-desplegable
	final static String[] columnasgrid_operadores= {"Operador","Título","Nombre","Apellidos","PAN","Estado","Grupo Op."};
	final static String[] botonescrud_operadores=null;
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
		logger.info("TC1 Gestion Operadores: Resultado esperado=true (operación correcta)");
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
		logger.info("TC1 Gestion Operadores: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}
	
	@Test
	public void testGestionOperadores2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Gestion Operadores");
		logger.info("TC2 Gestion Operadores: Comprobación del contenido de la tabla contra BD");
		logger.info("TC2 Gestion Operadores: Resultado esperado=true");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.busqueda();
		
		// Se valida que la query de la BD está contenida en la Tabla
		boolean iguales=true;
		try {
			String queryString = "select operator,title,forename,surname,pan,status,aoperator.opgroup,aopgroup.description"
					+ " from dbo.aoperator,dbo.aopgroup where aoperator.opgroup=aopgroup.opgroup order by tollcompany,operator";
			Statement stmt = LoginTest.DBconnectionPlaza.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(queryString);
			int tamaño=RutinasTestComunes.obtenerTamañoConsulta(rs);
			if (tamaño!=gestionOperadores.numFilasTotalTabla()) {
				logger.error("TC2 Gestion Operadores: El número de registros de la búsqueda es " +tamaño+" y no coincide con el de la tabla: "+gestionOperadores.numFilasTabla());
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
			logger.fatal("TC2 Gestion Operadores: Error al acceder a la BD");
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
					Statement stmt = LoginTest.DBconnectionPlaza.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs = stmt.executeQuery(queryString);
					rs.next();
					iguales2=gestionOperadores.buscarRegistroTablaEnBD(rs, operador);
					if (!iguales2) {
						logger.error("TC2 Gestion Operadores: El registro de la tabla " +operador+" no coincide con el de la BD.");
						LoginTest.majors++;
					}
				}
				catch (Exception e) {
					logger.fatal("TC2 Gestion Operadores: Error al acceder a la BD");
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
		logger.info("TC2 Gestion Operadores: Resultado obtenido="+(iguales && iguales2));
		assertTrue(iguales && iguales2);
	}
}
