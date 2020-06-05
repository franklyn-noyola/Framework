package testCasesITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje.CrearModificarParametroPeajeProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje.ParametrosPeajeProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.ErrorMessage;

public class CrearParametroPeajeTest {

	final static String titulo="Creación de parámetros"; 
	final static String subtitulo="Configuración sistema   -   Configuración de peaje   -   Parámetros de peaje   -   Crear"; 
	final static String[] botones= {"Confirmar","Volver"};
	final static String[] labels= {"Parámetro","Valor"};
	final static String msg_error_duplicidad="Ya existe un parámetro con el nombre indicado";
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(CrearParametroPeajeTest.class);
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
	public void testCrearParametrosPeaje1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Crear Parámetros Peaje");
		logger.info("TC1 Crear Parámetros Peaje: Probar el análisis sintáctico correcto");
		logger.info("TC1 Crear Parámetros Peaje: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Parámetros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		parametros.crear();
		CrearModificarParametroPeajeProcess crearParametro=new CrearModificarParametroPeajeProcess();
		boolean analisis=crearParametro.sintacticAnalysis(titulo,subtitulo, botones, null, labels);
		if (analisis) {
			logger.info("TC1 Crear Parámetros Peaje: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Crear Parámetros Peaje: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Crear Parámetros Peaje: Resultado obtenido="+analisis);
		crearParametro.volver();
		assertTrue(analisis);
	}
	
	
	@Test
	public void testCrearParametrosPeaje2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Crear Parámetros Peaje");
		logger.info("TC2 Crear Parámetros Peaje: Crear un parámetro peaje");
		logger.info("TC2 Crear Parámetros Peaje: Resultado esperado: true (operación correcta, comprobación de aparición en tabla)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Parámetros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		parametros.crear();
		CrearModificarParametroPeajeProcess crearParametro=new CrearModificarParametroPeajeProcess();
		String[] datosEntrada={"Parámetro aa","Descripción aa"};
		crearParametro.escribirCampoParametro(datosEntrada[0]);
		crearParametro.escribirCampoValor(datosEntrada[1]);
		crearParametro.confirmar();
		parametros.recargarTabla();
		String[] resultado=new String[3];
		String[] columnas=new String[1];
		columnas[0]=labels[0];
		String[] valores=new String[1];
		valores[0]=datosEntrada[0];
		resultado=parametros.obtenerFilaTabla(columnas, valores);
		boolean iguales=RutinasTestComunes.assertArrayEquals(datosEntrada,resultado);
		if (!iguales) {
			LoginTest.majors++;
		}
		logger.info("TC2 Crear Parámetros Peaje: Resultado obtenido:"+iguales);
		parametros.volver();
		assertTrue(iguales);
	}
	
	@Test
	public void testCrearParametrosPeaje3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Crear Parámetros Peaje");
		logger.info("TC3 Crear Parámetros Peaje: Probar la validación del campo Parámetro");
		logger.info("TC3 Crear Parámetros Peaje: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Parámetros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		parametros.crear();
		CrearModificarParametroPeajeProcess crearParametro=new CrearModificarParametroPeajeProcess();
		crearParametro.confirmar();
		boolean hayErrorValidacion=crearParametro.hayErrorValidacionParametro();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC3 Crear Parámetros Peaje: Resultado obtenido:"+hayErrorValidacion);
		parametros.volver();
		assertTrue(hayErrorValidacion);
	}

	@Test
	public void testCrearParametrosPeaje4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Crear Parámetros Peaje");
		logger.info("TC4 Crear Parámetros Peaje: Probar la creación de un parámetro existente");
		logger.info("TC4 Crear Parámetros Peaje: Resultado esperado: true (aparece un mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Parámetros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		parametros.crear();
		String datosEntrada[]={"Parámetro aa","Descripción aa"};
		CrearModificarParametroPeajeProcess crearParametro=new CrearModificarParametroPeajeProcess();
		crearParametro.escribirCampoParametro(datosEntrada[0]);
		crearParametro.escribirCampoValor(datosEntrada[1]);
		crearParametro.confirmar();
		boolean hayError=crearParametro.hayMensajeError();
		boolean analisis=false;
		if (hayError) {
			ErrorMessage errormsgobt=crearParametro.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_duplicidad);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC4 Crear Parámetros Peaje: No ha aparecido el mensaje de error '"+msg_error_duplicidad+"'");
			LoginTest.majors++;
		}
		logger.info("TC4 Crear Parámetros Peaje: Resultado obtenido:"+(hayError && analisis));
		parametros.volver();
		assertTrue(hayError && analisis);
	}
}
