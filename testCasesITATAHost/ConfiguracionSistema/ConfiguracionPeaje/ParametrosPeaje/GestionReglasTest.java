package testCasesITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje.GestionReglasProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje.ParametrosPeajeProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.AlertPopup;
import unidadesGraficas.ErrorMessage;

public class GestionReglasTest {

	final static String titulo="Reglas de los parámetros de peaje"; 
	final static String subtitulo="Configuración sistema   -   Configuración de peaje   -   Parámetros de peaje   -   Editar reglas"; 
	final static String[] botones={"Editar orden","Volver"};
	final static String encabezado1="Parámetro";
	final static String[] labelsVariables={"Nombre"};
	final static String encabezado2="Reglas";
	final static String[] botonescrud={"Crear","Modificar","Eliminar","Subir","Bajar"};
	final static String[] columnasgrid={"Concesionaria","Plaza","Num. Vía","Sentido","Tipo de vía","Valor"};
	final static String[] botonesgrid={"Primera","<",">","Última"};
	final static String msg_error_noseleccionado="Ningún elemento seleccionado";
	final static String alerta_confirmacion_borrado="¿Está seguro de que desea eliminar la regla?";
	static InitBOTest botest;
	private static Logger logger;
	 
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(GestionReglasTest.class);
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
	public void testGestionReglas1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Gestión Reglas");
		logger.info("TC1 Gestión Reglas: Probar el análisis sintáctico correcto");
		logger.info("TC1 Gestión Reglas: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Parámetros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		String parametro="a";
		int numParametro=parametros.obtenerNumFilaTabla("Parámetro", parametro);
		parametros.seleccionarFila(numParametro);
		String[] valoresVariables=new String[labelsVariables.length];
		valoresVariables[0]=parametros.obtenerValorColumna(numParametro, "Parámetro");
		parametros.editarRegla();
		GestionReglasProcess reglas=new GestionReglasProcess();
		boolean analisis=reglas.sintacticAnalysis(titulo,subtitulo, botones,encabezado1,labelsVariables,valoresVariables,encabezado2,botonescrud,columnasgrid,botonesgrid);
		if (analisis) {
			logger.info("TC1 Gestión Reglas: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Gestión Reglas: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Gestión Plaza: Resultado obtenido="+analisis);
		reglas.volver();
		assertTrue(analisis);
	}
	
	@Test
	public void testGestionReglas2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Gestión Reglas");
		logger.info("TC2 Gestión Reglas: Probar la eliminación incorrecta sin seleccionar regla");
		logger.info("TC2 Gestión Reglas: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Parámetros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		String parametro="a";
		int numParametro=parametros.obtenerNumFilaTabla("Parámetro", parametro);
		parametros.seleccionarFila(numParametro);
		parametros.editarRegla();
		GestionReglasProcess reglas=new GestionReglasProcess();
		reglas.eliminar();
		boolean analisis=false;
		boolean hayMsgError=reglas.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=reglas.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC2 Gestión Reglas: Debería haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC2 Gestión Reglas: Resultado obtenido="+(hayMsgError && analisis));
		reglas.volver();
		parametros.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	
	@Test
	public void testGestionReglas3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Gestión Reglas");
		logger.info("TC3 Gestión Reglas: Probar la eliminación correcta de una regla cancelando el borrado");
		logger.info("TC3 Gestión Reglas: Resultado esperado: true (aparece alerta de confirmación)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Parámetros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		String parametro="a";
		int numParametro=parametros.obtenerNumFilaTabla("Parámetro", parametro);
		parametros.seleccionarFila(numParametro);
		parametros.editarRegla();
		GestionReglasProcess reglas=new GestionReglasProcess();
		int numRegla=1;
		reglas.seleccionarFila(numRegla);
		reglas.eliminar();
		boolean analisis=false;
		boolean hayAlerta=reglas.hayAlerta();
		if (hayAlerta) {
			AlertPopup alerta=reglas.alerta();
			analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
			if (!analisis) {
				LoginTest.minors++;
			}
			reglas.cancelarAlerta();
		}
		logger.info("TC3 Gestión Reglas: Resultado obtenido: "+(hayAlerta && analisis));
		reglas.volver();
		parametros.volver();
		assertTrue(hayAlerta && analisis);
	}

	@Test
	public void testGestionReglas4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Gestión Reglas");
		logger.info("TC4 Gestión Reglas: Probar la modificación incorrecta sin seleccionar regla");
		logger.info("TC4 Gestión Reglas: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Parámetros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		String parametro="a";
		int numParametro=parametros.obtenerNumFilaTabla("Parámetro", parametro);
		parametros.seleccionarFila(numParametro);
		parametros.editarRegla();
		GestionReglasProcess reglas=new GestionReglasProcess();
		reglas.modificar();
		boolean analisis=false;
		boolean hayMsgError=reglas.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=reglas.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC4 Gestión Reglas: Debería haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC4 Gestión Reglas: Resultado obtenido="+(hayMsgError && analisis));
		reglas.volver();
		assertTrue(hayMsgError && analisis);
	}

	@Test
	public void testGestionReglas5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Gestión Reglas");
		logger.info("TC5 Gestión Reglas: Probar en la edición de orden la subida incorrecta sin seleccionar regla");
		logger.info("TC5 Gestión Reglas: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Parámetros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		String parametro="a";
		int numParametro=parametros.obtenerNumFilaTabla("Parámetro", parametro);
		parametros.seleccionarFila(numParametro);
		parametros.editarRegla();
		GestionReglasProcess reglas=new GestionReglasProcess();
		reglas.editarOrden();
		reglas.subir();
		boolean analisis=false;
		boolean hayMsgError=reglas.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=reglas.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC5 Gestión Reglas: Debería haber aparecido el mensaje de error: '"+msg_error_noseleccionado+"'");
			LoginTest.minors++;
		}
		logger.info("TC5 Gestión Reglas: Resultado obtenido="+(hayMsgError && analisis));
		reglas.cancelar();
		reglas.volver();
		parametros.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	@Test
	public void testGestionReglas6() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6 Gestión Reglas");
		logger.info("TC6 Gestión Reglas: Probar en la edición de orden la bajada incorrecta sin seleccionar regla");
		logger.info("TC6 Gestión Reglas: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Parámetros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		String parametro="a";
		int numParametro=parametros.obtenerNumFilaTabla("Parámetro", parametro);
		parametros.seleccionarFila(numParametro);
		parametros.editarRegla();
		GestionReglasProcess reglas=new GestionReglasProcess();
		reglas.editarOrden();
		reglas.bajar();
		boolean analisis=false;
		boolean hayMsgError=reglas.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=reglas.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC6 Gestión Reglas: Debería haber aparecido el mensaje de error: '"+msg_error_noseleccionado+"'");
			LoginTest.minors++;
		}
		logger.info("TC6 Gestión Reglas: Resultado obtenido="+(hayMsgError && analisis));
		reglas.cancelar();
		reglas.volver();
		parametros.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	@Test
	public void testGestionReglas7() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC7 Gestión Reglas");
		logger.info("TC7 Gestión Reglas: Probar en la edición de orden la subida correcta de una regla");
		logger.info("TC7 Gestión Reglas: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Parámetros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		String parametro="a";
		int numParametro=parametros.obtenerNumFilaTabla("Parámetro", parametro);
		parametros.seleccionarFila(numParametro);
		parametros.editarRegla();
		GestionReglasProcess reglas=new GestionReglasProcess();
		reglas.editarOrden();
		int numfila=1;
		reglas.seleccionarFila(numfila);
		String[] filaseleccionada=new String[reglas.numColumnasTabla()];
		filaseleccionada=reglas.obtenerFilaTabla(numfila);
		reglas.subir();
		int numfilaobtenida=1;
		String[] filaobtenida=new String[reglas.numColumnasTabla()];
		filaobtenida=reglas.obtenerFilaTabla(numfilaobtenida);
		boolean movimientoCorrecto=RutinasTestComunes.assertArrayEquals(filaseleccionada,filaobtenida);
		for (int i=1; i<reglas.numFilasTabla() && movimientoCorrecto; i++) {
			filaseleccionada=reglas.obtenerFilaTabla(i);
			reglas.bajar();
			filaobtenida=reglas.obtenerFilaTabla(i+1);
			movimientoCorrecto=RutinasTestComunes.assertArrayEquals(filaseleccionada,filaobtenida);
		}
		filaseleccionada=reglas.obtenerFilaTabla(reglas.numFilasTabla());
		reglas.bajar();
		filaobtenida=reglas.obtenerFilaTabla(reglas.numFilasTabla());
		movimientoCorrecto=RutinasTestComunes.assertArrayEquals(filaseleccionada,filaobtenida);
		for (int i=reglas.numFilasTabla(); i>1 && movimientoCorrecto; i--) {
			filaseleccionada=reglas.obtenerFilaTabla(i);
			reglas.subir();
			filaobtenida=reglas.obtenerFilaTabla(i-1);
			movimientoCorrecto=RutinasTestComunes.assertArrayEquals(filaseleccionada,filaobtenida);
		}
		reglas.cancelar();
		reglas.volver();
		parametros.volver();
		assertTrue(movimientoCorrecto);
	}
	
	/* TO DO
	@Test
	public void testParametrosPeaje8() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Gestión Reglas");
		logger.info("TC4 Gestión Reglas: Comprobación del contenido de la tabla contra BD");
		logger.info("TC4 Gestión Reglas: Resultado esperado=true");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Parámetros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		int numParametro=1;
		parametros.seleccionarFila(numParametro);
		parametros.editarRegla();
		GestionReglasProcess reglas=new GestionReglasProcess();
		String nombreparametro=reglas.leerCampoNombre();
		
		// Se valida que la query de la BD está contenida en la Tabla
		boolean iguales=true;
		try {
			String queryString = "select tollcompany,plaza,lanenum,lanedir,lanetype,value "
					+ "from dbo.aparameter where parameter='"+nombreparametro+"' order by sequence ASC";
			Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(queryString);
			int tamaño=RutinasTestComunes.obtenerTamañoConsulta(rs);
			if (tamaño!=reglas.numFilasTotalTabla()) {
				logger.error("El número de registros de la búsqueda es " +tamaño+" y no coincide con el de la tabla: "+parametros.numFilasTabla());
				LoginTest.critical++;
				iguales=false;
			}
			while (rs.next() && iguales) {
				iguales=reglas.buscarRegistroBDEnTabla(rs);
			}
			if (!iguales) {
				LoginTest.critical++;
			}
		}
		catch (Exception e) {
			logger.fatal("Error al acceder a la BD");
			LoginTest.critical++;
			e.printStackTrace();
			fail();
		}
		
		// Se valida que el contenido de la Tabla está en BD
		reglas.pulsarBotonTablaPrimero();
		boolean iguales2=true;
		do {
			reglas.recargarTabla();
			for (int num_fila=1; (num_fila<=reglas.numFilasTabla()) && iguales2; num_fila++) {
				String elemento = String.format(String.format("%%0%dd", 3), num_fila); 
				String queryString = "select tollcompany,plaza,lanenum,lanedir,lanetype,value "
						+ "from dbo.aparameter where parameter='"+nombreparametro+" and sequence='"+elemento+"'";
				try {
					Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
					ResultSet rs = stmt.executeQuery(queryString);
					rs.next();
					iguales2=reglas.buscarRegistroTablaEnBD(rs, elemento);
					if (!iguales2) {
						logger.error("El registro de la tabla " +elemento+" no coincide con el de la BD.");
						LoginTest.majors++;
					}
				}
				catch (Exception e) {
					logger.fatal("Error al acceder a la BD");
					LoginTest.critical++;
					e.printStackTrace();
					fail();
				}
			}
			if (!reglas.ultimaTabla()) {
				reglas.pulsarBotonTablaSiguiente();
			}
		}
		while (!reglas.ultimaTabla() && iguales2);
		logger.info("TC4 Parámetros Peaje: Resultado obtenido="+(iguales && iguales2));
		assertTrue(iguales && iguales2);
	}*/
	
	
}
