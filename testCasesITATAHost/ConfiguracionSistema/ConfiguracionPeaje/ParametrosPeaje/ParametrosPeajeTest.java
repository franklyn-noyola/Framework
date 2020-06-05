package testCasesITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje.ParametrosPeajeProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.AlertPopup;
import unidadesGraficas.ErrorMessage;

public class ParametrosPeajeTest {

	final static String titulo="Par�metros de peaje"; 
	final static String subtitulo="Configuraci�n sistema   -   Configuraci�n de peaje   -   Par�metros de peaje"; 
	final static String[] botones= {"Informe","Enviar a v�as","Volver"};
	final static String[] botonescrud= {"Crear","Modificar","Editar reglas","Eliminar"};
	final static String[] columnasgrid= {"Par�metro","Valor"};
	final static String[] botonesgrid={"Primera","<",">","�ltima"};
	final static String msg_error_noseleccionado="Ning�n elemento seleccionado";
	final static String alerta_confirmacion_borrado="�Est� seguro de que desea eliminar el par�metro?";
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(ParametrosPeajeTest.class);
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
	public void testParametrosPeaje1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Par�metros Peaje");
		logger.info("TC1 Par�metros Peaje: Probar el an�lisis sint�ctico correcto");
		logger.info("TC1 Par�metros Peaje: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Par�metros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		boolean analisis=parametros.sintacticAnalysis(titulo,subtitulo, botones,null,botonescrud,columnasgrid,botonesgrid);
		if (analisis) {
			logger.info("TC1 Par�metros Peaje: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC1 Par�metros Peaje: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Par�metros Peaje: Resultado obtenido="+analisis);
		parametros.volver();
		assertTrue(analisis);
	}
	
	@Test
	public void testParametrosPeaje2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Par�metros Peaje");
		logger.info("TC2 Par�metros Peaje: Probar la eliminaci�n incorrecta de un par�metro sin seleccionar par�metro");
		logger.info("TC2 Par�metros Peaje: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Par�metros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		parametros.eliminar();
		boolean analisis=false;
		boolean hayMsgError=parametros.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=parametros.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC2 Par�metros Peaje: Deber�a haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC2 Par�metros Peaje: Resultado obtenido="+(hayMsgError && analisis));
		parametros.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	
	@Test
	public void testParametrosPeaje3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Par�metros Peaje");
		logger.info("TC3 Par�metros Peaje");
		logger.info("TC3 Par�metros Peaje: Probar la eliminaci�n correcta de un par�metro cancelando el borrado");
		logger.info("TC3 Par�metros Peaje: Resultado esperado: true (aparece alerta de confirmaci�n)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Par�metros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		String campo="Par�metro";
		String codigo="Par�metro aa";
		int num_fila=parametros.obtenerNumFilaTabla(campo, codigo);
		boolean analisis=false;
		boolean hayAlerta=false;
		boolean encontradoTabla2=false;
		boolean encontradoTabla1=num_fila>=1 && num_fila<=parametros.numFilasTabla();
		if (!encontradoTabla1) {
			logger.error("TC3 Par�metros Peaje: El elemento seleccionado en la tabla no existe: "+codigo);
			LoginTest.critical++;
		}
		else {
			parametros.seleccionarFila(num_fila);
			parametros.eliminar();
			hayAlerta=parametros.hayAlerta();
			if (hayAlerta) {
				AlertPopup alerta=parametros.alerta();
				analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
				if (!analisis) {
					LoginTest.minors++;
				}
				parametros.cancelarAlerta();
				parametros.recargarTabla();
				encontradoTabla2=(parametros.obtenerFilaTabla(campo, codigo)!=null);
				if (!encontradoTabla2) {
					logger.error("TC3 Par�metros Peaje: Deber�a haber aparecido el par�metro borrado "+codigo+" en la tabla");
					LoginTest.majors++;
				}
			}
		}
		logger.info("TC3 Par�metros Peaje: Resultado obtenido: "+(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2));
		parametros.volver();
		assertTrue(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2);
	}

	
	@Test
	public void testParametrosPeaje4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Par�metros Peaje");
		logger.info("TC4 Par�metros Peaje: Comprobaci�n del contenido de la tabla contra BD");
		logger.info("TC4 Par�metros Peaje: Resultado esperado=true");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Par�metros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		
		// Se valida que la query de la BD est� contenida en la Tabla
		boolean iguales=true;
		try {
			String queryString = "select parameter,value from dbo.aparameter where sequence='000' order by parameter ASC";
			Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(queryString);
			int tama�o=RutinasTestComunes.obtenerTama�oConsulta(rs);
			if (tama�o!=parametros.numFilasTotalTabla()) {
				logger.error("TC4 Par�metros Peaje: El n�mero de registros de la b�squeda es " +tama�o+" y no coincide con el de la tabla: "+parametros.numFilasTabla());
				LoginTest.critical++;
				iguales=false;
			}
			while (rs.next() && iguales) {
				iguales=parametros.buscarRegistroBDEnTabla(rs);
			}
			if (!iguales) {
				LoginTest.critical++;
			}
		}
		catch (Exception e) {
			logger.fatal("TC4 Par�metros Peaje: Error al acceder a la BD");
			LoginTest.critical++;
			e.printStackTrace();
			fail();
		}
		
		// Se valida que el contenido de la Tabla est� en BD
		parametros.pulsarBotonTablaPrimero();
		boolean iguales2=true;
		do {
			parametros.recargarTabla();
			for (int num_fila=1; (num_fila<=parametros.numFilasTabla()) && iguales2; num_fila++) {
				String elemento=parametros.obtenerValorColumna(num_fila, "Par�metro");
				String queryString = "select parameter,value from dbo.aparameter where sequence='000'and parameter='"+elemento+"'";
				try {
					Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
					ResultSet rs = stmt.executeQuery(queryString);
					rs.next();
					iguales2=parametros.buscarRegistroTablaEnBD(rs, elemento);
					if (!iguales2) {
						logger.error("TC4 Par�metros Peaje: El registro de la tabla " +elemento+" no coincide con el de la BD.");
						LoginTest.majors++;
					}
				}
				catch (Exception e) {
					logger.fatal("TC4 Par�metros Peaje: Error al acceder a la BD");
					LoginTest.critical++;
					e.printStackTrace();
					fail();
				}
			}
			if (!parametros.ultimaTabla()) {
				parametros.pulsarBotonTablaSiguiente();
			}
		}
		while (!parametros.ultimaTabla() && iguales2);
		logger.info("TC4 Par�metros Peaje: Resultado obtenido="+(iguales && iguales2));
		parametros.volver();
		assertTrue(iguales && iguales2);
	}
	
	@Test
	public void testParametrosPeaje5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Par�metros Peaje");
		logger.info("TC5 Par�metros Peaje");
		logger.info("TC5 Par�metros Peaje: Probar la eliminaci�n correcta de un par�metro aceptando el borrado");
		logger.info("TC5 Par�metros Peaje: Resultado esperado: true (aparece alerta de confirmaci�n)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Par�metros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		String campo="Par�metro";
		String codigo="Par�metro aa";
		int num_fila=parametros.obtenerNumFilaTabla(campo, codigo);
		boolean analisis=false;
		boolean hayAlerta=false;
		boolean encontradoTabla2=false;
		boolean hayMsgError=false;
		boolean encontradoTabla1=num_fila>=1 && num_fila<=parametros.numFilasTabla();
		if (!encontradoTabla1) {
			logger.error("TC5 Par�metros Peaje: el elemento seleccionado en la tabla no existe: "+codigo);
			LoginTest.critical++;
		}
		else {
			parametros.seleccionarFila(num_fila);
			parametros.eliminar();
			hayAlerta=parametros.hayAlerta();
			if (hayAlerta) {
				AlertPopup alerta=parametros.alerta();
				analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
				if (!analisis) {
					LoginTest.minors++;
				}
				parametros.aceptarAlerta();
				hayMsgError=parametros.hayMensajeError();
				if (hayMsgError) {
					ErrorMessage errormsgobt=parametros.mensajeError();
					logger.error("TC5 Par�metros Peaje: No deber�a haber aparecido el mensaje de error '"+errormsgobt.getText()+"'");
					LoginTest.majors++;
				}
				parametros.recargarTabla();
				encontradoTabla2=(parametros.obtenerFilaTabla(campo, codigo)!=null);
				if (encontradoTabla2) {
					logger.error("TC5 Par�metros Peaje: No deber�a haber aparecido el par�metro borrado "+codigo+" en la tabla");
					LoginTest.majors++;
				}
			}
		}
		logger.info("TC5 Par�metros Peaje: Resultado obtenido: "+(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2 && !hayMsgError));
		parametros.volver();
		assertTrue(hayAlerta && analisis && encontradoTabla1 && !encontradoTabla2 && !hayMsgError);
	}

}
