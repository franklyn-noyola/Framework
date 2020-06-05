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
import unidadesGraficas.ErrorMessage;

public class ModificarMotivosTest {

	final static String titulo="Modificación de motivo"; 
	final static String subtitulo="Configuración sistema   -   Configuración de peaje   -   Motivos   -   Modificar"; 
	final static String[] botones= {"Confirmar","Volver"};
	final static String[] labels= {"Motivo","Tipo","Descripción"};
	final static String msg_error_noseleccionado="Ningún elemento seleccionado";
	final static String msg_error_codigo_existente="Ya existe un motivo con el código indicado";
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(ModificarMotivosTest.class);
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
	public void testModificarMotivos1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Modificar Motivos");
		logger.info("TC1 Modificar Motivos: Probar el análisis sintáctico correcto");
		logger.info("TC1 Modificar Motivos: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Motivos");
		MotivosProcess motivos=new MotivosProcess();
		motivos.seleccionarFila(1);
		motivos.modificar();
		CrearModificarMotivoProcess modificarMotivo=new CrearModificarMotivoProcess();
		boolean analisis=modificarMotivo.sintacticAnalysis(titulo,subtitulo, botones,null,labels);
		if (analisis) {
			logger.info("TC1 Modificar Motivos: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Modificar Motivos: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Modificar Motivos: Resultado obtenido="+analisis);
		modificarMotivo.volver();
		assertTrue(analisis);
	}
	
	
	@Test
	public void testModificarMotivos2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Modificar Motivos");
		logger.info("TC2 Modificar Motivos: Probar la validación del campo Motivo");
		logger.info("TC2 Modificar Motivos: Resultado esperado: true (aparece un tooltip de validación)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Motivos");
		MotivosProcess motivos=new MotivosProcess();
		motivos.seleccionarFila(1);
		motivos.modificar();
		CrearModificarMotivoProcess modificarMotivo=new CrearModificarMotivoProcess();
		modificarMotivo.borrarCampoMotivo();
		modificarMotivo.confirmar();
		boolean hayErrorValidacion=modificarMotivo.hayErrorValidacionMotivo();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC2 Modificar Motivos: Resultado obtenido:"+hayErrorValidacion);
		motivos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testModificarMotivos3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Modificar Motivos");
		logger.info("TC3 Modificar Motivos: Probar la validación del campo Descripcion");
		logger.info("TC3 Modificar Motivos: Resultado esperado: true (aparece un tooltip de validación)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Motivos");
		MotivosProcess motivos=new MotivosProcess();
		motivos.seleccionarFila(1);
		motivos.modificar();
		CrearModificarMotivoProcess modificarMotivo=new CrearModificarMotivoProcess();
		modificarMotivo.borrarCampoDescripcion();
		modificarMotivo.confirmar();
		boolean hayErrorValidacion=modificarMotivo.hayErrorValidacionDescripcion();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC3 Modificar Motivos: Resultado obtenido:"+hayErrorValidacion);
		motivos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testModificarMotivos4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Modificar Motivos");
		logger.info("TC4 Modificar Motivos: Editar un motivo y comprobar que el editado es el mismo que el seleccionado");
		logger.info("TC4 Modificar Motivos: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Motivos");
		MotivosProcess motivos=new MotivosProcess();
		int num_fila=1;
		motivos.seleccionarFila(num_fila);
		String motivoTabla=motivos.obtenerValorColumna(num_fila, "Motivo");
		String tipoTabla=motivos.obtenerValorColumna(num_fila, "Tipo");
		String descripcionTabla=motivos.obtenerValorColumna(num_fila, "Descripción");
		motivos.modificar();
		CrearModificarMotivoProcess modificarMotivo=new CrearModificarMotivoProcess();
		String motivo2=modificarMotivo.leerCampoMotivo();
		boolean motivosIguales=motivoTabla.equals(motivo2);
		if (!motivosIguales) {
			logger.error("TC4 Modificar Motivos: El motivo de la tabla no coincide con el de la pantalla Modificar");
			LoginTest.minors++;
		}
		String tipo2=modificarMotivo.leerOpcionTipo();
		boolean tiposIguales=tipoTabla.equals(tipo2);
		if (!tiposIguales) {
			logger.error("TC4 Modificar Motivos: El tipo de la tabla no coincide con el de la pantalla Modificar");
			LoginTest.minors++;
		}
		String descripcion2=modificarMotivo.leerCampoDescripcion();
		boolean descripcionesIguales=descripcionTabla.equals(descripcion2);
		if (!descripcionesIguales) {
			logger.error("TC4 Modificar Motivos: La descripción de la tabla no coincide con el de la pantalla Modificar");
			LoginTest.minors++;
		}
		modificarMotivo.volver();
		logger.info("TC4 Modificar Motivos: Resultado obtenido:"+(motivosIguales && tiposIguales && descripcionesIguales));
		assertTrue((motivosIguales && tiposIguales && descripcionesIguales));
	}
	
	@Test
	public void testModificarMotivos5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Modificar Motivos");
		logger.info("TC5 Modificar Motivos: Probar la modificación incorrecta sin seleccionar motivo");
		logger.info("TC5 Modificar Motivos: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Motivos");
		MotivosProcess motivos=new MotivosProcess();
		motivos.modificar();
		boolean analisis=false;
		boolean hayMsgError=motivos.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=motivos.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC5 Modificar Motivos: Debería haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC5 Modificar Motivos: Resultado obtenido="+(hayMsgError && analisis));
		motivos.volver();
		assertTrue(hayMsgError && analisis);
	}
}
