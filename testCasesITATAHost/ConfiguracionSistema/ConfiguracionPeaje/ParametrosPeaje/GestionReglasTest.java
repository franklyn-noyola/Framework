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

	final static String titulo="Reglas de los par�metros de peaje"; 
	final static String subtitulo="Configuraci�n sistema   -   Configuraci�n de peaje   -   Par�metros de peaje   -   Editar reglas"; 
	final static String[] botones={"Editar orden","Volver"};
	final static String encabezado1="Par�metro";
	final static String[] labelsVariables={"Nombre"};
	final static String encabezado2="Reglas";
	final static String[] botonescrud={"Crear","Modificar","Eliminar","Subir","Bajar"};
	final static String[] columnasgrid={"Concesionaria","Plaza","Num. V�a","Sentido","Tipo de v�a","Valor"};
	final static String[] botonesgrid={"Primera","<",">","�ltima"};
	final static String msg_error_noseleccionado="Ning�n elemento seleccionado";
	final static String alerta_confirmacion_borrado="�Est� seguro de que desea eliminar la regla?";
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
		logger.info("TC1 Gesti�n Reglas");
		logger.info("TC1 Gesti�n Reglas: Probar el an�lisis sint�ctico correcto");
		logger.info("TC1 Gesti�n Reglas: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Par�metros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		String parametro="a";
		int numParametro=parametros.obtenerNumFilaTabla("Par�metro", parametro);
		parametros.seleccionarFila(numParametro);
		String[] valoresVariables=new String[labelsVariables.length];
		valoresVariables[0]=parametros.obtenerValorColumna(numParametro, "Par�metro");
		parametros.editarRegla();
		GestionReglasProcess reglas=new GestionReglasProcess();
		boolean analisis=reglas.sintacticAnalysis(titulo,subtitulo, botones,encabezado1,labelsVariables,valoresVariables,encabezado2,botonescrud,columnasgrid,botonesgrid);
		if (analisis) {
			logger.info("TC1 Gesti�n Reglas: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC1 Gesti�n Reglas: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Gesti�n Plaza: Resultado obtenido="+analisis);
		reglas.volver();
		assertTrue(analisis);
	}
	
	@Test
	public void testGestionReglas2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Gesti�n Reglas");
		logger.info("TC2 Gesti�n Reglas: Probar la eliminaci�n incorrecta sin seleccionar regla");
		logger.info("TC2 Gesti�n Reglas: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Par�metros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		String parametro="a";
		int numParametro=parametros.obtenerNumFilaTabla("Par�metro", parametro);
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
			logger.error("TC2 Gesti�n Reglas: Deber�a haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC2 Gesti�n Reglas: Resultado obtenido="+(hayMsgError && analisis));
		reglas.volver();
		parametros.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	
	@Test
	public void testGestionReglas3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Gesti�n Reglas");
		logger.info("TC3 Gesti�n Reglas: Probar la eliminaci�n correcta de una regla cancelando el borrado");
		logger.info("TC3 Gesti�n Reglas: Resultado esperado: true (aparece alerta de confirmaci�n)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Par�metros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		String parametro="a";
		int numParametro=parametros.obtenerNumFilaTabla("Par�metro", parametro);
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
		logger.info("TC3 Gesti�n Reglas: Resultado obtenido: "+(hayAlerta && analisis));
		reglas.volver();
		parametros.volver();
		assertTrue(hayAlerta && analisis);
	}

	@Test
	public void testGestionReglas4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Gesti�n Reglas");
		logger.info("TC4 Gesti�n Reglas: Probar la modificaci�n incorrecta sin seleccionar regla");
		logger.info("TC4 Gesti�n Reglas: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Par�metros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		String parametro="a";
		int numParametro=parametros.obtenerNumFilaTabla("Par�metro", parametro);
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
			logger.error("TC4 Gesti�n Reglas: Deber�a haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC4 Gesti�n Reglas: Resultado obtenido="+(hayMsgError && analisis));
		reglas.volver();
		assertTrue(hayMsgError && analisis);
	}

	@Test
	public void testGestionReglas5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Gesti�n Reglas");
		logger.info("TC5 Gesti�n Reglas: Probar en la edici�n de orden la subida incorrecta sin seleccionar regla");
		logger.info("TC5 Gesti�n Reglas: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Par�metros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		String parametro="a";
		int numParametro=parametros.obtenerNumFilaTabla("Par�metro", parametro);
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
			logger.error("TC5 Gesti�n Reglas: Deber�a haber aparecido el mensaje de error: '"+msg_error_noseleccionado+"'");
			LoginTest.minors++;
		}
		logger.info("TC5 Gesti�n Reglas: Resultado obtenido="+(hayMsgError && analisis));
		reglas.cancelar();
		reglas.volver();
		parametros.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	@Test
	public void testGestionReglas6() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6 Gesti�n Reglas");
		logger.info("TC6 Gesti�n Reglas: Probar en la edici�n de orden la bajada incorrecta sin seleccionar regla");
		logger.info("TC6 Gesti�n Reglas: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Par�metros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		String parametro="a";
		int numParametro=parametros.obtenerNumFilaTabla("Par�metro", parametro);
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
			logger.error("TC6 Gesti�n Reglas: Deber�a haber aparecido el mensaje de error: '"+msg_error_noseleccionado+"'");
			LoginTest.minors++;
		}
		logger.info("TC6 Gesti�n Reglas: Resultado obtenido="+(hayMsgError && analisis));
		reglas.cancelar();
		reglas.volver();
		parametros.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	@Test
	public void testGestionReglas7() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC7 Gesti�n Reglas");
		logger.info("TC7 Gesti�n Reglas: Probar en la edici�n de orden la subida correcta de una regla");
		logger.info("TC7 Gesti�n Reglas: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Par�metros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		String parametro="a";
		int numParametro=parametros.obtenerNumFilaTabla("Par�metro", parametro);
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
		logger.info("TC4 Gesti�n Reglas");
		logger.info("TC4 Gesti�n Reglas: Comprobaci�n del contenido de la tabla contra BD");
		logger.info("TC4 Gesti�n Reglas: Resultado esperado=true");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Par�metros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		int numParametro=1;
		parametros.seleccionarFila(numParametro);
		parametros.editarRegla();
		GestionReglasProcess reglas=new GestionReglasProcess();
		String nombreparametro=reglas.leerCampoNombre();
		
		// Se valida que la query de la BD est� contenida en la Tabla
		boolean iguales=true;
		try {
			String queryString = "select tollcompany,plaza,lanenum,lanedir,lanetype,value "
					+ "from dbo.aparameter where parameter='"+nombreparametro+"' order by sequence ASC";
			Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(queryString);
			int tama�o=RutinasTestComunes.obtenerTama�oConsulta(rs);
			if (tama�o!=reglas.numFilasTotalTabla()) {
				logger.error("El n�mero de registros de la b�squeda es " +tama�o+" y no coincide con el de la tabla: "+parametros.numFilasTabla());
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
		
		// Se valida que el contenido de la Tabla est� en BD
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
		logger.info("TC4 Par�metros Peaje: Resultado obtenido="+(iguales && iguales2));
		assertTrue(iguales && iguales2);
	}*/
	
	
}
