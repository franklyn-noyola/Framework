package testCasesITATAHost.ConfiguracionSistema.ParametrosCuenta.Recargos;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ParametrosCuenta.Recargos.CrearModificarRecargosProcess;
import procesosITATAHost.ConfiguracionSistema.ParametrosCuenta.Recargos.RecargosProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;

public class CrearRecargosTest {

	final static String titulo="Creación de recargo"; 
	final static String subtitulo="Configuración sistema   -   Parámetros de cuenta   -   Recargos   -   Crear"; 
	final static String[] botones= {"Confirmar","Volver"};
	final static String[] labels= {"Nombre","Descripción","Valor","Tipo","Tipo de aplicación"};
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(CrearRecargosTest.class);
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
	public void testCrearRecargos1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Crear Recargos");
		logger.info("TC1 Crear Recargos: Probar el análisis sintáctico correcto");
		logger.info("TC1 Crear Recargos: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Parámetros de cuenta","Recargos");
		RecargosProcess recargos=new RecargosProcess();
		recargos.crear();
		CrearModificarRecargosProcess crearRecargo=new CrearModificarRecargosProcess();
		boolean analisis=crearRecargo.sintacticAnalysis(titulo,subtitulo, botones, null, labels);
		if (analisis) {
			logger.info("TC1 Crear Recargos: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Crear Recargos: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Crear Recargos: Resultado obtenido="+analisis);
		crearRecargo.volver();
		assertTrue(analisis);
	}
	
	
	@Test
	public void testCrearRecargos2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Crear Recargos");
		logger.info("TC2 Crear Recargos: Crear un recargo");
		logger.info("TC2 Crear Recargos: Resultado esperado: true (operación correcta, comprobación de aparición en tabla)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Parámetros de cuenta","Recargos");
		RecargosProcess recargos=new RecargosProcess();
		recargos.crear();
		CrearModificarRecargosProcess crearRecargo=new CrearModificarRecargosProcess();
		String[] datosEntrada=new String[recargos.numColumnasTabla()];
		datosEntrada[0]="Nombreaa";
		datosEntrada[1]="Descripcion aa";
		datosEntrada[2]="Actualización de cuenta";
		datosEntrada[3]="Recurrente";
		datosEntrada[4]="10";
		crearRecargo.escribirCampoNombre(datosEntrada[0]);
		crearRecargo.escribirCampoDescripcion(datosEntrada[1]);
		crearRecargo.seleccionarCampoTipo(datosEntrada[2]);
		crearRecargo.escribirCampoValor(datosEntrada[4]);
		crearRecargo.confirmar();
		recargos.recargarTabla();
		String[] resultado=new String[recargos.numColumnasTabla()];
		datosEntrada[2]="$ "+datosEntrada[2];
		String[] columnas= {"Nombre","Descripción","Tipo","Tipo de aplicación","Valor"};
		resultado=recargos.obtenerFilaTabla(columnas, datosEntrada);
		boolean iguales=RutinasTestComunes.assertArrayEquals(datosEntrada,resultado);
		if (!iguales) {
			LoginTest.majors++;
		}
		logger.info("TC2 Crear Recargos: Resultado obtenido:"+iguales);
		recargos.volver();
		assertTrue(iguales);
	}
	
	@Test
	public void testCrearRecargos3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Crear Recargos");
		logger.info("TC3 Crear Recargos: Probar la validación del campo Valor");
		logger.info("TC3 Crear Recargos: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Parámetros de cuenta","Recargos");
		RecargosProcess recargos=new RecargosProcess();
		recargos.crear();
		CrearModificarRecargosProcess crearRecargo=new CrearModificarRecargosProcess();
		crearRecargo.escribirCampoValor("10!");
		crearRecargo.confirmar();
		boolean hayErrorValidacion=crearRecargo.hayErrorValidacionValor();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC3 Crear Parámetros Peaje: Resultado obtenido:"+hayErrorValidacion);
		crearRecargo.volver();
		assertTrue(hayErrorValidacion);
	}

}
