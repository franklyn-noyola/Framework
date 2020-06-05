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

	final static String titulo="Parámetros de peaje"; 
	final static String subtitulo="Configuración sistema   -   Configuración de peaje   -   Parámetros de peaje"; 
	final static String[] botones= {"Informe","Enviar a vías","Volver"};
	final static String[] botonescrud= {"Crear","Modificar","Editar reglas","Eliminar"};
	final static String[] columnasgrid= {"Parámetro","Valor"};
	final static String[] botonesgrid={"Primera","<",">","Última"};
	final static String msg_error_noseleccionado="Ningún elemento seleccionado";
	final static String alerta_confirmacion_borrado="¿Está seguro de que desea eliminar el parámetro?";
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
		logger.info("TC1 Parámetros Peaje");
		logger.info("TC1 Parámetros Peaje: Probar el análisis sintáctico correcto");
		logger.info("TC1 Parámetros Peaje: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Parámetros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		boolean analisis=parametros.sintacticAnalysis(titulo,subtitulo, botones,null,botonescrud,columnasgrid,botonesgrid);
		if (analisis) {
			logger.info("TC1 Parámetros Peaje: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Parámetros Peaje: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Parámetros Peaje: Resultado obtenido="+analisis);
		parametros.volver();
		assertTrue(analisis);
	}
	
	@Test
	public void testParametrosPeaje2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Parámetros Peaje");
		logger.info("TC2 Parámetros Peaje: Probar la eliminación incorrecta de un parámetro sin seleccionar parámetro");
		logger.info("TC2 Parámetros Peaje: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Parámetros de peaje");
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
			logger.error("TC2 Parámetros Peaje: Debería haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC2 Parámetros Peaje: Resultado obtenido="+(hayMsgError && analisis));
		parametros.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	
	@Test
	public void testParametrosPeaje3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Parámetros Peaje");
		logger.info("TC3 Parámetros Peaje");
		logger.info("TC3 Parámetros Peaje: Probar la eliminación correcta de un parámetro cancelando el borrado");
		logger.info("TC3 Parámetros Peaje: Resultado esperado: true (aparece alerta de confirmación)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Parámetros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		String campo="Parámetro";
		String codigo="Parámetro aa";
		int num_fila=parametros.obtenerNumFilaTabla(campo, codigo);
		boolean analisis=false;
		boolean hayAlerta=false;
		boolean encontradoTabla2=false;
		boolean encontradoTabla1=num_fila>=1 && num_fila<=parametros.numFilasTabla();
		if (!encontradoTabla1) {
			logger.error("TC3 Parámetros Peaje: El elemento seleccionado en la tabla no existe: "+codigo);
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
					logger.error("TC3 Parámetros Peaje: Debería haber aparecido el parámetro borrado "+codigo+" en la tabla");
					LoginTest.majors++;
				}
			}
		}
		logger.info("TC3 Parámetros Peaje: Resultado obtenido: "+(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2));
		parametros.volver();
		assertTrue(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2);
	}

	
	@Test
	public void testParametrosPeaje4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Parámetros Peaje");
		logger.info("TC4 Parámetros Peaje: Comprobación del contenido de la tabla contra BD");
		logger.info("TC4 Parámetros Peaje: Resultado esperado=true");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Parámetros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		
		// Se valida que la query de la BD está contenida en la Tabla
		boolean iguales=true;
		try {
			String queryString = "select parameter,value from dbo.aparameter where sequence='000' order by parameter ASC";
			Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(queryString);
			int tamaño=RutinasTestComunes.obtenerTamañoConsulta(rs);
			if (tamaño!=parametros.numFilasTotalTabla()) {
				logger.error("TC4 Parámetros Peaje: El número de registros de la búsqueda es " +tamaño+" y no coincide con el de la tabla: "+parametros.numFilasTabla());
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
			logger.fatal("TC4 Parámetros Peaje: Error al acceder a la BD");
			LoginTest.critical++;
			e.printStackTrace();
			fail();
		}
		
		// Se valida que el contenido de la Tabla está en BD
		parametros.pulsarBotonTablaPrimero();
		boolean iguales2=true;
		do {
			parametros.recargarTabla();
			for (int num_fila=1; (num_fila<=parametros.numFilasTabla()) && iguales2; num_fila++) {
				String elemento=parametros.obtenerValorColumna(num_fila, "Parámetro");
				String queryString = "select parameter,value from dbo.aparameter where sequence='000'and parameter='"+elemento+"'";
				try {
					Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
					ResultSet rs = stmt.executeQuery(queryString);
					rs.next();
					iguales2=parametros.buscarRegistroTablaEnBD(rs, elemento);
					if (!iguales2) {
						logger.error("TC4 Parámetros Peaje: El registro de la tabla " +elemento+" no coincide con el de la BD.");
						LoginTest.majors++;
					}
				}
				catch (Exception e) {
					logger.fatal("TC4 Parámetros Peaje: Error al acceder a la BD");
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
		logger.info("TC4 Parámetros Peaje: Resultado obtenido="+(iguales && iguales2));
		parametros.volver();
		assertTrue(iguales && iguales2);
	}
	
	@Test
	public void testParametrosPeaje5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Parámetros Peaje");
		logger.info("TC5 Parámetros Peaje");
		logger.info("TC5 Parámetros Peaje: Probar la eliminación correcta de un parámetro aceptando el borrado");
		logger.info("TC5 Parámetros Peaje: Resultado esperado: true (aparece alerta de confirmación)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Parámetros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		String campo="Parámetro";
		String codigo="Parámetro aa";
		int num_fila=parametros.obtenerNumFilaTabla(campo, codigo);
		boolean analisis=false;
		boolean hayAlerta=false;
		boolean encontradoTabla2=false;
		boolean hayMsgError=false;
		boolean encontradoTabla1=num_fila>=1 && num_fila<=parametros.numFilasTabla();
		if (!encontradoTabla1) {
			logger.error("TC5 Parámetros Peaje: el elemento seleccionado en la tabla no existe: "+codigo);
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
					logger.error("TC5 Parámetros Peaje: No debería haber aparecido el mensaje de error '"+errormsgobt.getText()+"'");
					LoginTest.majors++;
				}
				parametros.recargarTabla();
				encontradoTabla2=(parametros.obtenerFilaTabla(campo, codigo)!=null);
				if (encontradoTabla2) {
					logger.error("TC5 Parámetros Peaje: No debería haber aparecido el parámetro borrado "+codigo+" en la tabla");
					LoginTest.majors++;
				}
			}
		}
		logger.info("TC5 Parámetros Peaje: Resultado obtenido: "+(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2 && !hayMsgError));
		parametros.volver();
		assertTrue(hayAlerta && analisis && encontradoTabla1 && !encontradoTabla2 && !hayMsgError);
	}

}
