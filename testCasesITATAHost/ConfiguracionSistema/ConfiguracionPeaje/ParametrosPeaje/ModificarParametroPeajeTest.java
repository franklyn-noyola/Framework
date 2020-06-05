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
import unidadesGraficas.ErrorMessage;

public class ModificarParametroPeajeTest {

	final static String titulo="Modificaci�n de los par�metros"; 
	final static String subtitulo="Configuraci�n sistema   -   Configuraci�n de peaje   -   Par�metros de peaje   -   Modificar"; 
	final static String[] botones= {"Confirmar","Volver"};
	final static String[] labels= {"Par�metro","Valor"};
	final static String msg_error_noseleccionado="Ning�n elemento seleccionado";
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(ModificarParametroPeajeTest.class);
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
	public void testModificarParametrosPeaje1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Modificar Par�metros Peaje");
		logger.info("TC1 Modificar Par�metros Peaje: Probar el an�lisis sint�ctico correcto");
		logger.info("TC1 Modificar Par�metros Peaje: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Par�metros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		parametros.seleccionarFila(1);
		parametros.modificar();
		CrearModificarParametroPeajeProcess modificarParametro=new CrearModificarParametroPeajeProcess();
		boolean analisis=modificarParametro.sintacticAnalysis(titulo,subtitulo, botones, null, labels);
		if (analisis) {
			logger.info("TC1 Modificar Par�metros Peaje: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC1 Modificar Par�metros Peaje: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Modificar Par�metros Peaje: Resultado obtenido="+analisis);
		modificarParametro.volver();
		assertTrue(analisis);
	}
	
	
	@Test
	public void testModificarParametrosPeaje2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Modificar Par�metros Peaje");
		logger.info("TC2 Modificar Par�metros Peaje: Probar la validaci�n del campo Par�metro");
		logger.info("TC2 Modificar Par�metros Peaje: Resultado esperado: true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Par�metros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		parametros.seleccionarFila(1);
		parametros.modificar();
		CrearModificarParametroPeajeProcess modificarParametro=new CrearModificarParametroPeajeProcess();
		modificarParametro.borrarCampoParametro();
		modificarParametro.confirmar();
		boolean hayErrorValidacion=modificarParametro.hayErrorValidacionParametro();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC2 Modificar Par�metros Peaje: Resultado obtenido:"+hayErrorValidacion);
		parametros.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testModificarParametrosPeaje3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Modificar Par�metros Peaje");
		logger.info("TC3 Modificar Par�metros Peaje: Editar un par�metro y comprobar que el cargado es el mismo que el seleccionado");
		logger.info("TC3 Modificar Par�metros Peaje: Resultado esperado: true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Par�metros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		int num_fila=1;
		parametros.seleccionarFila(num_fila);
		String parametroTabla=parametros.obtenerValorColumna(num_fila, "Par�metro");
		String valorTabla=parametros.obtenerValorColumna(num_fila, "Valor");
		parametros.modificar();
		CrearModificarParametroPeajeProcess modificarParametro=new CrearModificarParametroPeajeProcess();
		String parametro2=modificarParametro.leerCampoParametro();
		boolean parametrosIguales=parametroTabla.equals(parametro2);
		if (!parametrosIguales) {
			logger.error("TC3 Modificar Par�metros Peaje: El campo par�metro de la tabla no coincide con el de la pantalla Modificar");
			LoginTest.minors++;
		}
		String valor2=modificarParametro.leerCampoValor();
		boolean valoresIguales=valorTabla.equals(valor2);
		if (!valoresIguales) {
			logger.error("TC3 Modificar Par�metros Peaje: El campo valor de la tabla no coincide con el de la pantalla Modificar");
			LoginTest.minors++;
		}
		modificarParametro.volver();
		logger.info("TC3 Modificar Par�metros Peaje: Resultado obtenido:"+(parametrosIguales && valoresIguales));
		parametros.volver();
		assertTrue((parametrosIguales && valoresIguales));
	}
	
	@Test
	public void testModificarParametrosPeaje4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Modificar Par�metros Peaje");
		logger.info("TC4 Modificar Par�metros Peaje: Probar la modificaci�n incorrecta sin seleccionar par�metro");
		logger.info("TC4 Modificar Par�metros Peaje: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Par�metros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		parametros.modificar();
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
			logger.error("TC4 Modificar Par�metros Peaje: Deber�a haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC4 Modificar Par�metros Peaje: Resultado obtenido="+(hayMsgError && analisis));
		parametros.volver();
		assertTrue(hayMsgError && analisis);
	}
}
