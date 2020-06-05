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

	final static String titulo="Modificación de los parámetros"; 
	final static String subtitulo="Configuración sistema   -   Configuración de peaje   -   Parámetros de peaje   -   Modificar"; 
	final static String[] botones= {"Confirmar","Volver"};
	final static String[] labels= {"Parámetro","Valor"};
	final static String msg_error_noseleccionado="Ningún elemento seleccionado";
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
		logger.info("TC1 Modificar Parámetros Peaje");
		logger.info("TC1 Modificar Parámetros Peaje: Probar el análisis sintáctico correcto");
		logger.info("TC1 Modificar Parámetros Peaje: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Parámetros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		parametros.seleccionarFila(1);
		parametros.modificar();
		CrearModificarParametroPeajeProcess modificarParametro=new CrearModificarParametroPeajeProcess();
		boolean analisis=modificarParametro.sintacticAnalysis(titulo,subtitulo, botones, null, labels);
		if (analisis) {
			logger.info("TC1 Modificar Parámetros Peaje: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Modificar Parámetros Peaje: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Modificar Parámetros Peaje: Resultado obtenido="+analisis);
		modificarParametro.volver();
		assertTrue(analisis);
	}
	
	
	@Test
	public void testModificarParametrosPeaje2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Modificar Parámetros Peaje");
		logger.info("TC2 Modificar Parámetros Peaje: Probar la validación del campo Parámetro");
		logger.info("TC2 Modificar Parámetros Peaje: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Parámetros de peaje");
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
		logger.info("TC2 Modificar Parámetros Peaje: Resultado obtenido:"+hayErrorValidacion);
		parametros.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testModificarParametrosPeaje3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Modificar Parámetros Peaje");
		logger.info("TC3 Modificar Parámetros Peaje: Editar un parámetro y comprobar que el cargado es el mismo que el seleccionado");
		logger.info("TC3 Modificar Parámetros Peaje: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Parámetros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		int num_fila=1;
		parametros.seleccionarFila(num_fila);
		String parametroTabla=parametros.obtenerValorColumna(num_fila, "Parámetro");
		String valorTabla=parametros.obtenerValorColumna(num_fila, "Valor");
		parametros.modificar();
		CrearModificarParametroPeajeProcess modificarParametro=new CrearModificarParametroPeajeProcess();
		String parametro2=modificarParametro.leerCampoParametro();
		boolean parametrosIguales=parametroTabla.equals(parametro2);
		if (!parametrosIguales) {
			logger.error("TC3 Modificar Parámetros Peaje: El campo parámetro de la tabla no coincide con el de la pantalla Modificar");
			LoginTest.minors++;
		}
		String valor2=modificarParametro.leerCampoValor();
		boolean valoresIguales=valorTabla.equals(valor2);
		if (!valoresIguales) {
			logger.error("TC3 Modificar Parámetros Peaje: El campo valor de la tabla no coincide con el de la pantalla Modificar");
			LoginTest.minors++;
		}
		modificarParametro.volver();
		logger.info("TC3 Modificar Parámetros Peaje: Resultado obtenido:"+(parametrosIguales && valoresIguales));
		parametros.volver();
		assertTrue((parametrosIguales && valoresIguales));
	}
	
	@Test
	public void testModificarParametrosPeaje4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Modificar Parámetros Peaje");
		logger.info("TC4 Modificar Parámetros Peaje: Probar la modificación incorrecta sin seleccionar parámetro");
		logger.info("TC4 Modificar Parámetros Peaje: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Parámetros de peaje");
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
			logger.error("TC4 Modificar Parámetros Peaje: Debería haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC4 Modificar Parámetros Peaje: Resultado obtenido="+(hayMsgError && analisis));
		parametros.volver();
		assertTrue(hayMsgError && analisis);
	}
}
