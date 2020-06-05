package testCasesITATAHost.ConfiguracionSistema.TarifasMoneda.MonedaAceptada;

import static org.junit.Assert.*;
import procesosComunes.Error;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import procesosITATAHost.ConfiguracionSistema.TarifasMoneda.MonedaAceptada.CrearMonedaAceptadaProcess;
import procesosITATAHost.ConfiguracionSistema.TarifasMoneda.MonedaAceptada.MonedaAceptadaProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.ErrorMessage;

public class CrearMonedaAceptadaTest {

	final static String titulo="Moneda aceptada: Creación"; 
	final static String subtitulo="Configuración sistema   -   Tarifas & Moneda   -   Moneda aceptada   -   Crear"; 
	final static String[] botones= {"Confirmar","Volver"};
	final static String encabezado="Información moneda aceptada";
	final static String[] labels= {"Denominación ($) :","Concesionaria","Tipo de moneda"};
	final static String msg_error_codigo_existente="Error: El valor ya existe.";
	
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(CrearMonedaAceptadaTest.class);
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
	public void testCrearMonedaAceptada1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Crear Moneda Aceptada");
		logger.info("TC1 Crear Moneda Aceptada: Probar el análisis sintáctico correcto");
		logger.info("TC1 Crear Moneda Aceptada: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Tarifas & Moneda","Moneda aceptada");
		MonedaAceptadaProcess moneda=new MonedaAceptadaProcess();
		moneda.crear();
		CrearMonedaAceptadaProcess crearMoneda=new CrearMonedaAceptadaProcess();
		boolean analisis=crearMoneda.sintacticAnalysis(titulo,subtitulo, botones, encabezado, labels);
		if (analisis) {
			logger.info("TC1 Crear Moneda Aceptada: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Crear Moneda Aceptada: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Crear Moneda Aceptada: Resultado obtenido="+analisis);
		crearMoneda.volver();
		moneda.volver();
		assertTrue(analisis);
	}
	
	
	@Test
	public void testCrearMonedaAceptada2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Crear Moneda Aceptada");
		logger.info("TC2 Crear Moneda Aceptada: Crear una moneda aceptada");
		logger.info("TC2 Crear Moneda Aceptada: Resultado esperado: true (operación correcta, comprobación de aparición en tabla)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Tarifas & Moneda","Moneda aceptada");
		MonedaAceptadaProcess moneda=new MonedaAceptadaProcess();
		moneda.crear();
		CrearMonedaAceptadaProcess crearMoneda=new CrearMonedaAceptadaProcess();
		String[] datosEntrada=new String[moneda.numColumnasTabla()];
		datosEntrada[0]="00: Descripcion bb";
		datosEntrada[1]="C : Moneda";
		datosEntrada[2]="3";
		Error errorDetectado=crearMoneda.seleccionarCampoConcesionaria(datosEntrada[0]);
		if (errorDetectado==Error.Error) {
			LoginTest.majors++;
			logger.error("TC2: Crear Moneda Aceptada: opción Concesionaria no encontrada en combo");
			fail();
		}
		errorDetectado=crearMoneda.seleccionarCampoTipoMoneda(datosEntrada[1]);
		if (errorDetectado==Error.Error) {
			LoginTest.majors++;
			logger.error("TC2: Crear Moneda Aceptada: opción Tipo Moneda no encontrada en combo");
			fail();
		}
		crearMoneda.escribirCampoDenominacion(datosEntrada[2]);
		crearMoneda.confirmar();
		moneda.recargarTabla();
		String[] resultado=new String[moneda.numColumnasTabla()];
		datosEntrada[0]=datosEntrada[0].substring(0, 2);
		datosEntrada[1]=datosEntrada[1].substring(4);
		datosEntrada[2]="$ "+datosEntrada[2];
		String[] columnas= {"Concesionaria","Tipo de moneda", "Denominación"};
		resultado=moneda.obtenerFilaTabla(columnas, datosEntrada);
		boolean iguales=RutinasTestComunes.assertArrayEquals(datosEntrada,resultado);
		if (!iguales) {
			LoginTest.majors++;
		}
		logger.info("TC2 Crear Moneda Aceptada: Resultado obtenido:"+iguales);
		moneda.volver();
		assertTrue(iguales);
	}
	
	@Test
	public void testCrearMonedaAceptada3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Crear Moneda Aceptada");
		logger.info("TC3 Crear Moneda Aceptada: Probar la validación de la existencia del campo Denominación");
		logger.info("TC3 Crear Moneda Aceptada: Resultado esperado: true (aparece el tooltip correspondiente)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Tarifas & Moneda","Moneda aceptada");
		MonedaAceptadaProcess moneda=new MonedaAceptadaProcess();
		moneda.crear();
		CrearMonedaAceptadaProcess crearMoneda=new CrearMonedaAceptadaProcess();
		crearMoneda.confirmar();
		boolean hayErrorValidacion=crearMoneda.hayErrorValidacionRequeridoDenominacion();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC3 Crear Moneda Aceptada: Resultado obtenido:"+hayErrorValidacion);
		crearMoneda.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearMonedaAceptada4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Crear Moneda Aceptada");
		logger.info("TC4 Crear Moneda Aceptada: Probar la validación del formato del campo Denominación");
		logger.info("TC4 Crear Moneda Aceptada: Resultado esperado: true (aparece el tooltip correspondiente)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Tarifas & Moneda","Moneda aceptada");
		MonedaAceptadaProcess moneda=new MonedaAceptadaProcess();
		moneda.crear();
		CrearMonedaAceptadaProcess crearMoneda=new CrearMonedaAceptadaProcess();
		crearMoneda.escribirCampoDenominacion("0,5");
		crearMoneda.confirmar();
		boolean hayErrorValidacion=crearMoneda.hayErrorValidacionFormatoDenominacion();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC4 Crear Moneda Aceptada: Resultado obtenido:"+hayErrorValidacion);
		crearMoneda.volver();
		assertTrue(hayErrorValidacion);
	}

	@Test
	public void testCrearMonedaAceptada5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Crear Moneda Aceptada");
		logger.info("TC5 Crear Moneda Aceptada: Crear una moneda existente");
		logger.info("TC5 Crear Moneda Aceptada: Resultado esperado: true (Aparece el mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Tarifas & Moneda","Moneda aceptada");
		MonedaAceptadaProcess moneda=new MonedaAceptadaProcess();
		moneda.crear();
		CrearMonedaAceptadaProcess crearMoneda=new CrearMonedaAceptadaProcess();
		String[] datosEntrada=new String[moneda.numColumnasTabla()];
		datosEntrada[0]="00: Descripcion bb";
		datosEntrada[1]="C : Moneda";
		datosEntrada[2]="3";
		crearMoneda.seleccionarCampoConcesionaria(datosEntrada[0]);
		crearMoneda.seleccionarCampoTipoMoneda(datosEntrada[1]);
		crearMoneda.escribirCampoDenominacion(datosEntrada[2]);
		crearMoneda.confirmar();
		boolean hayMsgError=crearMoneda.hayMensajeError();
		boolean analisis=false;
		if (hayMsgError) {
			ErrorMessage errormsgobt=crearMoneda.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_codigo_existente);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC5 Crear Moneda Aceptada: No ha aparecido el mensaje de error '"+msg_error_codigo_existente+"'");
			LoginTest.majors++;
		}
		logger.info("T5 Crear Moneda Aceptada: Resultado obtenido:"+(hayMsgError && analisis));
		assertTrue((hayMsgError && analisis));
	}
}
