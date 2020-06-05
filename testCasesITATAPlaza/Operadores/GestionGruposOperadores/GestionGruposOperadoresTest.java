package testCasesITATAPlaza.Operadores.GestionGruposOperadores;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAPlaza.Operadores.GestionGrupos.GestionGruposOperadoresProcess;
import testCasesITATAPlaza.InitBOTest;
import testCasesITATAPlaza.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.AlertPopup;
import unidadesGraficas.ErrorMessage;

public class GestionGruposOperadoresTest {

	final static String titulo_gestion_gruposoperadores="Gestión de grupos de operadores"; 
	final static String subtitulo_gestion_gruposoperadores="Configuración sistema   -   Operadores   -   Gestión de grupos"; 
	final static String[] botones_gestion_gruposoperadores= {"Informe","Volver"};
	final static String[] botonescrud_gruposoperadores= {"Crear","Modificar","Eliminar"};
	final static String[] columnasgrid_gruposoperadores= {"Grupo Op.","Descripción","Acceso a Plaza","Acceso Vía"};
	final static String[] botonesgrid_gruposoperadores={"Primera","<",">","Última"};
	final static String msg_error_noseleccionado="Ningún elemento seleccionado";
	final static String msg_error_grupo_con_operadores="El grupo tiene operadores y no puede ser borrado";
	final static String alerta_confirmacion_borrado="¿Está seguro de que desea eliminar el grupo?";
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(GestionGruposOperadoresTest.class);
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
	public void testGestionGruposOperadores1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Gstión grupos de operadores");
		logger.info("TC1 Gstión grupos de operadores: Probar el análisis sintáctico correcto");
		logger.info("TC1 Gestión grupos de operadores: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de grupos");
		GestionGruposOperadoresProcess gestionGruposOperadores=new GestionGruposOperadoresProcess();
		boolean analisis=gestionGruposOperadores.sintacticAnalysis(titulo_gestion_gruposoperadores,
				subtitulo_gestion_gruposoperadores, botones_gestion_gruposoperadores,null,
				botonescrud_gruposoperadores,columnasgrid_gruposoperadores,
				botonesgrid_gruposoperadores);
		if (analisis) {
			logger.info("TC1 Gestión Grupos Operadores: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Gestión Grupos Operadores: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		assertTrue(analisis);
	}
	
	@Test
	public void testGestionGruposOperadores2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Gestión grupos de operadores");
		logger.info("TC2 Gestión grupos de operadores: Probar la eliminación incorrecta sin seleccionar grupo operador");
		logger.info("TC2 Gestión grupos de operadores: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de grupos");
		GestionGruposOperadoresProcess gestionGruposOperadores=new GestionGruposOperadoresProcess();
		gestionGruposOperadores.eliminar();
		boolean analisis=false;
		boolean hayMsgError=gestionGruposOperadores.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=gestionGruposOperadores.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC2 Gestión grupos de operadores: Debería haber aparecido el mensaje de error :'"+msg_error_noseleccionado+"'");
			LoginTest.minors++;
		}
		assertTrue(hayMsgError && analisis);
	}
	
	@Test
	public void testGestionGruposOperadores3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Gestión grupos de operadores");
		logger.info("TC3 Gestión grupos de operadores: Probar la eliminación correcta de un grupo operador cancelando el borrado");
		logger.info("TC3 Gestión grupos de operadores: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de grupos");
		GestionGruposOperadoresProcess gestionGruposOperadores=new GestionGruposOperadoresProcess();
		String grupo="12";
		int numfila=gestionGruposOperadores.obtenerNumFilaTabla("Grupo Op.", grupo);
		gestionGruposOperadores.seleccionarFila(numfila);
		gestionGruposOperadores.eliminar();
		boolean analisis=false;
		boolean encontrado=false;
		boolean hayAlerta=gestionGruposOperadores.hayAlerta();
		if (hayAlerta) {
			AlertPopup alerta=gestionGruposOperadores.alerta();
			analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
			if (!analisis) {
				LoginTest.minors++;
			}
			gestionGruposOperadores.cancelarAlerta();
			gestionGruposOperadores.recargarTabla();
			encontrado=(gestionGruposOperadores.obtenerFilaTabla("Grupo Op.", grupo)!=null);
			if (!encontrado) {
				logger.error("TC3 Gestión grupos de operadores: Debería haber aparecido el grupo '"+grupo+"' en la tabla");
				LoginTest.majors++;
			}
		}
		logger.info("TC3 Gestión grupos de operadores: Resultado obtenido: "+(hayAlerta && analisis && encontrado));
		gestionGruposOperadores.volver();
		assertTrue(hayAlerta && analisis && encontrado);
	}
	
	@Test
	public void testGestionGruposOperadores4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Gestión grupos de operadores");
		logger.info("TC4 Gestión grupos de operadores: Probar la eliminación correcta de un grupo operador aceptando el borrado");
		logger.info("TC4 Gestión grupos de operadores: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de grupos");
		GestionGruposOperadoresProcess gestionGruposOperadores=new GestionGruposOperadoresProcess();
		String grupo="16";
		int numfila=gestionGruposOperadores.obtenerNumFilaTabla("Grupo Op.", grupo);
		gestionGruposOperadores.seleccionarFila(numfila);
		gestionGruposOperadores.eliminar();
		boolean analisis=false;
		boolean encontrado=false;
		boolean hayAlerta=gestionGruposOperadores.hayAlerta();
		if (hayAlerta) {
			AlertPopup alerta=gestionGruposOperadores.alerta();
			analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
			if (!analisis) {
				LoginTest.minors++;
			}
			gestionGruposOperadores.aceptarAlerta();
			gestionGruposOperadores.recargarTabla();
			encontrado=(gestionGruposOperadores.obtenerFilaTabla("Grupo Op.", grupo)!=null);
			if (encontrado) {
				logger.error("TC4 Gestión grupos de operadores: No debería haber aparecido el operador borrado '"+grupo+"' en la tabla");
				LoginTest.majors++;
			}
		}
		logger.info("TC4 Gestión grupos de operadores: Resultado obtenido: "+(hayAlerta && analisis && !encontrado));
		gestionGruposOperadores.volver();
		assertTrue(hayAlerta && analisis && !encontrado);
	}
	
	@Test
	public void testGestionGruposOperadores5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Gestión grupos de operadores");
		logger.info("TC5 Gestión grupos de operadores: Probar la eliminación incorrecta de un grupo que tiene operadores asignados");
		logger.info("TC5 Gestión grupos de operadores: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de grupos");
		GestionGruposOperadoresProcess gestionGruposOperadores=new GestionGruposOperadoresProcess();
		String grupo="12";
		int numfila=gestionGruposOperadores.obtenerNumFilaTabla("Grupo Op.", grupo);
		gestionGruposOperadores.seleccionarFila(numfila);
		gestionGruposOperadores.eliminar();
		boolean analisis=false;
		boolean hayAlerta=gestionGruposOperadores.hayAlerta();
		if (hayAlerta) {
			AlertPopup alerta=gestionGruposOperadores.alerta();
			analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
			if (!analisis) {
				LoginTest.minors++;
			}
			gestionGruposOperadores.aceptarAlerta();
		}
		boolean analisis2=false;
		boolean hayMsgError=gestionGruposOperadores.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=gestionGruposOperadores.mensajeError();
			analisis2=errormsgobt.sintacticAnalysis(msg_error_grupo_con_operadores);
			if (!analisis2) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC5 Gestión grupos de operadores: Debería haber aparecido el mensaje de error :'"+msg_error_grupo_con_operadores+"'");
			LoginTest.minors++;
		}
		logger.info("TC5: Resultado obtenido: "+(hayAlerta && hayMsgError && analisis && analisis2));
		assertTrue(hayAlerta && hayMsgError && analisis && analisis2);
	}
	
	@Test
	public void testGestionGruposOperadores6() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6 Gestion grupos de operadores");
		logger.info("TC6 Gestion grupos de operadores: Comprobación del contenido de la tabla contra BD");
		logger.info("TC6 Gestion grupos de operadores: Resultado esperado=true");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de grupos");
		GestionGruposOperadoresProcess gestionGruposOperadores=new GestionGruposOperadoresProcess();
		
		// Se valida que la query de la BD está contenida en la Tabla
		boolean iguales=true;
		try {
			String queryString = "select opgroup,description,plazaaccess,laneaccess from dbo.aopgroup order by opgroup ASC";
			Statement stmt = LoginTest.DBconnectionPlaza.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(queryString);
			int tamaño=RutinasTestComunes.obtenerTamañoConsulta(rs);
			if (tamaño!=gestionGruposOperadores.numFilasTotalTabla()) {
				logger.error("TC6 Gestion grupos de operadores: El número de registros de la búsqueda es " +tamaño+" y no coincide con el de la tabla: "+gestionGruposOperadores.numFilasTabla());
				LoginTest.critical++;
				iguales=false;
			}
			while (rs.next() && iguales) {
				iguales=gestionGruposOperadores.buscarRegistroBDEnTabla(rs);
			}
			if (!iguales) {
				LoginTest.critical++;
			}
		}
		catch (Exception e) {
			logger.fatal("TC6 Gestion grupos de operadores: Error al acceder a la BD");
			LoginTest.critical++;
			e.printStackTrace();
			fail();
		}
		
		// Se valida que el contenido de la Tabla está en BD
		gestionGruposOperadores.pulsarBotonTablaPrimero();
		boolean iguales2=true;
		do {
			gestionGruposOperadores.recargarTabla();
			for (int num_fila=1; (num_fila<=gestionGruposOperadores.numFilasTabla()) && iguales2; num_fila++) {
				String grupoOperador=gestionGruposOperadores.obtenerValorColumna(num_fila, "Grupo Op.");
				String queryString = "select opgroup,description,plazaaccess,laneaccess from dbo.aopgroup where opgroup="+grupoOperador;
				try {
					Statement stmt = LoginTest.DBconnectionPlaza.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs = stmt.executeQuery(queryString);
					rs.next();
					iguales2=gestionGruposOperadores.buscarRegistroTablaEnBD(rs, grupoOperador);
					if (!iguales2) {
						logger.error("TC6 Gestion grupos de operadores: El registro de la tabla " +grupoOperador+" no coincide con el de la BD.");
						LoginTest.majors++;
					}
				}
				catch (Exception e) {
					logger.fatal("TC6 Gestion grupos de operadores: Error al acceder a la BD");
					LoginTest.critical++;
					e.printStackTrace();
					fail();
				}
			}
			if (!gestionGruposOperadores.ultimaTabla()) {
				gestionGruposOperadores.pulsarBotonTablaSiguiente();
			}
		}
		while (!gestionGruposOperadores.ultimaTabla() && iguales2);
		logger.info("TC6 Gestion grupos de operadores: Resultado obtenido="+(iguales && iguales2));
		assertTrue(iguales && iguales2);
	}
}
