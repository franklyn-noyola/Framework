package testCasesITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Motivos;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Motivos.CrearModificarMotivoProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Motivos.MotivosProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.ErrorMessage;

public class CrearMotivosTest {

	final static String titulo="Creación de motivo"; 
	final static String subtitulo="Configuración sistema   -   Configuración de peaje   -   Motivos   -   Crear"; 
	final static String[] botones= {"Confirmar","Volver"};
	final static String[] labels= {"Motivo","Tipo","Descripción"};
	final static String msg_error_codigo_existente="Ya existe un motivo con el código (Motivo/Tipo) indicado";
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(CrearMotivosTest.class);
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
	public void testCrearMotivos1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Crear Motivos");
		logger.info("TC1 Crear Motivos: Probar el análisis sintáctico correcto");
		logger.info("TC1 Crear Motivos: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Motivos");
		MotivosProcess motivos=new MotivosProcess();
		motivos.crear();
		CrearModificarMotivoProcess crearMotivo=new CrearModificarMotivoProcess();
		boolean analisis=crearMotivo.sintacticAnalysis(titulo,subtitulo, botones, null, labels);
		if (analisis) {
			logger.info("TC1 Crear Motivos: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Crear Motivos: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Crear Motivos: Resultado obtenido="+analisis);
		motivos.volver();
		assertTrue(analisis);
	}
	
	
	@Test
	public void testCrearMotivos2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Crear Motivos");
		logger.info("TC2 Crear Motivos: Crear un motivo");
		logger.info("TC2 Crear Motivos: Resultado esperado: true (operación correcta, comprobación de aparición en tabla)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Motivos");
		MotivosProcess motivos=new MotivosProcess();
		motivos.crear();
		CrearModificarMotivoProcess crearMotivo=new CrearModificarMotivoProcess();
		String[] datosEntrada= {"99","Tipo de exento","Descripcion aa"};
		crearMotivo.escribirCampoMotivo(datosEntrada[0]);
		crearMotivo.seleccionarOpcionTipo(datosEntrada[1]);
		crearMotivo.escribirCampoDescripcion(datosEntrada[2]);
		crearMotivo.confirmar();
		motivos.recargarTabla();
		String[] resultado=new String[3];
		String[] columnas=new String[1];
		columnas[0]="Motivo";
		String[] valores=new String[1];
		valores[0]=datosEntrada[0];
		resultado=motivos.obtenerFilaTabla(columnas, valores);
		boolean iguales=RutinasTestComunes.assertArrayEquals(datosEntrada,resultado);
		if (!iguales) {
			LoginTest.majors++;
		}
		logger.info("TC2 Crear Motivos: Resultado obtenido:"+iguales);
		motivos.volver();
		assertTrue(iguales);
	}
	

	@Test
	public void testCrearMotivos3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Crear Motivos");
		logger.info("TC3 Crear Motivos: Probar la validación del campo Motivo");
		logger.info("TC3 Crear Motivos: Resultado esperado: true (aparece un tooltip de validación)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Motivos");
		MotivosProcess motivos=new MotivosProcess();
		motivos.crear();
		CrearModificarMotivoProcess crearMotivo=new CrearModificarMotivoProcess();
		crearMotivo.seleccionarOpcionTipo("Tipo de exento");
		crearMotivo.escribirCampoDescripcion("Descripcion aa");
		crearMotivo.confirmar();
		boolean hayErrorValidacion=crearMotivo.hayErrorValidacionMotivo();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC3 Crear Motivos: Resultado obtenido:"+hayErrorValidacion);
		motivos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearMotivos4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Crear Motivos");
		logger.info("TC4 Crear Motivos: Probar la validación del campo Descripcion");
		logger.info("TC4 Crear Motivos: Resultado esperado: true (aparece un tooltip de validación)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Motivos");
		MotivosProcess motivos=new MotivosProcess();
		motivos.crear();
		CrearModificarMotivoProcess crearMotivo=new CrearModificarMotivoProcess();
		crearMotivo.escribirCampoMotivo("aa");
		crearMotivo.seleccionarOpcionTipo("Tipo de exento");
		crearMotivo.confirmar();
		boolean hayErrorValidacion=crearMotivo.hayErrorValidacionDescripcion();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC4 Crear Motivos: Resultado obtenido:"+hayErrorValidacion);
		motivos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearMotivos5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Crear Motivos");
		logger.info("TC5 Crear Motivos: Crear un motivo con un código existente y el resto de campos iguales");
		logger.info("TC5 Crear Motivos: Resultado esperado: true (aparece un mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Motivos");
		MotivosProcess motivos=new MotivosProcess();
		motivos.crear();
		CrearModificarMotivoProcess crearMotivo=new CrearModificarMotivoProcess();
		String[] datosEntrada= {"99","Tipo de exento","Descripcion aa"};
		crearMotivo.escribirCampoMotivo(datosEntrada[0]);
		crearMotivo.seleccionarOpcionTipo(datosEntrada[1]);
		crearMotivo.escribirCampoDescripcion(datosEntrada[2]);
		crearMotivo.confirmar();
		boolean hayMsgError=crearMotivo.hayMensajeError();
		boolean analisis=false;
		if (hayMsgError) {
			ErrorMessage errormsgobt=crearMotivo.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_codigo_existente);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC5 Crear Motivos: No ha aparecido el mensaje de error '"+msg_error_codigo_existente+"'");
			LoginTest.majors++;
		}
		logger.info("T5 Crear Motivos: Resultado obtenido:"+(hayMsgError && analisis));
		assertTrue((hayMsgError && analisis));
	}
	
	@Test
	public void testCrearMotivos6() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6 Crear Motivos");
		logger.info("TC6 Crear Motivos: Crear un motivo con un código existente y alguno de los otros campos distintos");
		logger.info("TC6 Crear Motivos: Resultado esperado: true (aparece un mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Motivos");
		MotivosProcess motivos=new MotivosProcess();
		motivos.crear();
		CrearModificarMotivoProcess crearMotivo=new CrearModificarMotivoProcess();
		String[] datosEntrada= {"99","Tipo de exento","Descripcion bb"};
		crearMotivo.escribirCampoMotivo(datosEntrada[0]);
		crearMotivo.seleccionarOpcionTipo(datosEntrada[1]);
		crearMotivo.escribirCampoDescripcion(datosEntrada[2]);
		crearMotivo.confirmar();
		boolean hayMsgError=crearMotivo.hayMensajeError();
		boolean analisis=false;
		if (hayMsgError) {
			ErrorMessage errormsgobt=crearMotivo.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_codigo_existente);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC6 Crear Motivos: No ha aparecido el mensaje de error '"+msg_error_codigo_existente+"'");
			LoginTest.majors++;
		}
		logger.info("T6 Crear Motivos: Resultado obtenido:"+(hayMsgError && analisis));
		assertTrue((hayMsgError && analisis));
	}
}
