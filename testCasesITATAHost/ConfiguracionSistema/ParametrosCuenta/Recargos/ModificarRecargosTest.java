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

public class ModificarRecargosTest {

	final static String titulo="Modificación de recargo"; 
	final static String subtitulo="Configuración sistema   -   Parámetros de cuenta   -   Recargos   -   Modificar"; 
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
		logger = Logger.getLogger(ModificarRecargosTest.class);
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
	
	//@Test
	public void testModificarRecargo1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Modificar Recargo");
		logger.info("TC1 Modificar Recargo: Probar el análisis sintáctico correcto");
		logger.info("TC1 Modificar Recargo: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Parámetros de cuenta","Recargos");
		RecargosProcess recargos=new RecargosProcess();
		recargos.seleccionarFila(1);
		recargos.modificar();
		CrearModificarRecargosProcess modificarRecargo=new CrearModificarRecargosProcess();
		boolean analisis=modificarRecargo.sintacticAnalysis(titulo,subtitulo, botones, null, labels);
		if (analisis) {
			logger.info("TC1 Modificar Recargo: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Modificar Recargo: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Modificar Recargo: Resultado obtenido="+analisis);
		modificarRecargo.volver();
		assertTrue(analisis);
	}
	
	
	@Test
	public void testModificarRecargo2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Modificar Recargo");
		logger.info("TC2 Modificar Recargo: Probar la validación del campo Valor");
		logger.info("TC2 Modificar Recargo: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Parámetros de cuenta","Recargos");
		RecargosProcess recargos=new RecargosProcess();
		recargos.seleccionarFila(1);
		recargos.modificar();
		CrearModificarRecargosProcess modificarRecargo=new CrearModificarRecargosProcess();
		modificarRecargo.escribirCampoValor("!");
		modificarRecargo.confirmar();
		boolean hayErrorValidacion=modificarRecargo.hayErrorValidacionValor();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC2 Modificar Recargo: Resultado obtenido:"+hayErrorValidacion);
		recargos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	//@Test
	public void testModificarRecargo3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Modificar Recargo");
		logger.info("TC3 Modificar Recargo: Editar un recargo y comprobar que el cargado es el mismo que el seleccionado");
		logger.info("TC3 Modificar Recargo: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Parámetros de cuenta","Recargos");
		String[] datosEntrada={"Nombre aa","Descripción","Pérdida de tag","10"};
		RecargosProcess recargos=new RecargosProcess();
		recargos.seleccionarFila(1);
		recargos.modificar();
		CrearModificarRecargosProcess modificarRecargo=new CrearModificarRecargosProcess();
		String datosesperados[]=new String[recargos.numColumnasTabla()-1];  // El campo TipoApliacion no tiene strSelenium
		modificarRecargo.escribirCampoNombre(datosEntrada[0]);
		modificarRecargo.escribirCampoDescripcion(datosEntrada[1]);
		modificarRecargo.seleccionarCampoTipo(datosEntrada[2]);
		modificarRecargo.escribirCampoValor(datosEntrada[3]);
		datosesperados[0]=modificarRecargo.leerCampoNombre();
		datosesperados[1]=modificarRecargo.leerCampoDescripcion();
		datosesperados[2]=modificarRecargo.leerCampoTipo();
		datosesperados[3]=recargos.obtenerValorColumna(1, "Tipo de aplicación");  //Trampa
		datosesperados[4]=modificarRecargo.leerCampoValor();
		modificarRecargo.confirmar();
		String filatabla[]=new String[recargos.numColumnasTabla()];
		filatabla=recargos.obtenerFilaTabla(1);
		boolean iguales=RutinasTestComunes.assertArrayEquals(filatabla,datosesperados);
	    if (!iguales) {
	    	logger.error("TC3 Modificar Recargo: el recargo editado no coincide con el que devuelve la tabla");
	    	LoginTest.majors++;
	       }
		logger.info("TC3: Modificar Recargo: resultado obtenido="+iguales);
		assertTrue(iguales);
	}
	
}
