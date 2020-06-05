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

	final static String titulo="Creaci�n de par�metros"; 
	final static String subtitulo="Configuraci�n sistema   -   Configuraci�n de peaje   -   Par�metros de peaje   -   Crear"; 
	final static String[] botones= {"Confirmar","Volver"};
	final static String[] labels= {"Par�metro","Valor"};
	final static String msg_error_duplicidad="Ya existe un par�metro con el nombre indicado";
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
		logger.info("TC1 Crear Par�metros Peaje");
		logger.info("TC1 Crear Par�metros Peaje: Probar el an�lisis sint�ctico correcto");
		logger.info("TC1 Crear Par�metros Peaje: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Par�metros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		parametros.crear();
		CrearModificarParametroPeajeProcess crearParametro=new CrearModificarParametroPeajeProcess();
		boolean analisis=crearParametro.sintacticAnalysis(titulo,subtitulo, botones, null, labels);
		if (analisis) {
			logger.info("TC1 Crear Par�metros Peaje: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC1 Crear Par�metros Peaje: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Crear Par�metros Peaje: Resultado obtenido="+analisis);
		crearParametro.volver();
		assertTrue(analisis);
	}
	
	
	@Test
	public void testCrearParametrosPeaje2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Crear Par�metros Peaje");
		logger.info("TC2 Crear Par�metros Peaje: Crear un par�metro peaje");
		logger.info("TC2 Crear Par�metros Peaje: Resultado esperado: true (operaci�n correcta, comprobaci�n de aparici�n en tabla)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Par�metros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		parametros.crear();
		CrearModificarParametroPeajeProcess crearParametro=new CrearModificarParametroPeajeProcess();
		String[] datosEntrada={"Par�metro aa","Descripci�n aa"};
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
		logger.info("TC2 Crear Par�metros Peaje: Resultado obtenido:"+iguales);
		parametros.volver();
		assertTrue(iguales);
	}
	
	@Test
	public void testCrearParametrosPeaje3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Crear Par�metros Peaje");
		logger.info("TC3 Crear Par�metros Peaje: Probar la validaci�n del campo Par�metro");
		logger.info("TC3 Crear Par�metros Peaje: Resultado esperado: true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Par�metros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		parametros.crear();
		CrearModificarParametroPeajeProcess crearParametro=new CrearModificarParametroPeajeProcess();
		crearParametro.confirmar();
		boolean hayErrorValidacion=crearParametro.hayErrorValidacionParametro();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC3 Crear Par�metros Peaje: Resultado obtenido:"+hayErrorValidacion);
		parametros.volver();
		assertTrue(hayErrorValidacion);
	}

	@Test
	public void testCrearParametrosPeaje4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Crear Par�metros Peaje");
		logger.info("TC4 Crear Par�metros Peaje: Probar la creaci�n de un par�metro existente");
		logger.info("TC4 Crear Par�metros Peaje: Resultado esperado: true (aparece un mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Par�metros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		parametros.crear();
		String datosEntrada[]={"Par�metro aa","Descripci�n aa"};
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
			logger.error("TC4 Crear Par�metros Peaje: No ha aparecido el mensaje de error '"+msg_error_duplicidad+"'");
			LoginTest.majors++;
		}
		logger.info("TC4 Crear Par�metros Peaje: Resultado obtenido:"+(hayError && analisis));
		parametros.volver();
		assertTrue(hayError && analisis);
	}
}
