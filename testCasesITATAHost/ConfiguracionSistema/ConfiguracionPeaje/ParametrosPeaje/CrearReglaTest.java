package testCasesITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje.CrearModificarReglaProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje.GestionReglasProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.ParametrosPeaje.ParametrosPeajeProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;

public class CrearReglaTest {

	final static String titulo="Creación parámetros peaje"; 
	final static String subtitulo="Configuración sistema   -   Configuración de peaje   -   Parámetros de peaje   -   Editar reglas   -   Crear"; 
	final static String[] botones= {"Confirmar","Volver"};
	final static String encabezado1="Parámetro"; 
	final static String[] labelsVariables= {"Nombre"};
	final static String encabezado2="Condiciones"; 
	final static String[] labelsCheck= {"Concesionaria", "Plaza", "Num. Vía", "Sentido", "Tipo de vía"};
	final static String encabezado3="Valor"; 
	final static String[] labels={"Valor"};
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(CrearReglaTest.class);
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
	public void testCrearReglaPeaje1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Crear Regla");
		logger.info("TC1 Crear Regla: Probar el análisis sintáctico correcto");
		logger.info("TC1 Crear Regla: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Parámetros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		int numParametro=1;
		parametros.seleccionarFila(numParametro);
		String[] valoresVariables=new String[labelsVariables.length];
		valoresVariables[0]=parametros.obtenerValorColumna(numParametro, "Parámetro");
		parametros.editarRegla();
		GestionReglasProcess reglas=new GestionReglasProcess();
		reglas.crear();
		CrearModificarReglaProcess crearRegla=new CrearModificarReglaProcess();
		boolean analisis=crearRegla.sintacticAnalysis(titulo,subtitulo, botones, encabezado1, labelsVariables,
				encabezado2, labelsCheck, encabezado3, labels);
		if (analisis) {
			logger.info("TC1 Crear Regla: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Crear Regla: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Crear Regla: Resultado obtenido="+analisis);
		crearRegla.volver();
		reglas.volver();
		parametros.volver();
		assertTrue(analisis);
	}
	
	
	@Test
	public void testCrearRegla2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Crear Regla");
		logger.info("TC2 Crear Regla: Crear una regla");
		logger.info("TC2 Crear Regla: Resultado esperado: true (operación correcta, comprobación de aparición en tabla)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Parámetros de peaje");
		ParametrosPeajeProcess parametros=new ParametrosPeajeProcess();
		int numParametro=1;
		parametros.seleccionarFila(numParametro);
		parametros.editarRegla();
		GestionReglasProcess reglas=new GestionReglasProcess();
		reglas.crear();
		CrearModificarReglaProcess crearRegla=new CrearModificarReglaProcess();
		String[] datosEntrada={"00: Descripcion bb","98: Plaza98","01","P: Poniente","MI: Mixta","Valor aa"};
		crearRegla.clicarCheckConcesionaria();
		crearRegla.seleccionarCampoConcesionaria(datosEntrada[0]);
		crearRegla.clicarCheckPlaza();
		crearRegla.seleccionarCampoPlaza(datosEntrada[1]);
		crearRegla.clicarCheckNumVia();
		crearRegla.seleccionarCampoNumVia(datosEntrada[2]);
		crearRegla.clicarCheckSentido();
		crearRegla.seleccionarCampoSentido(datosEntrada[3]);
		crearRegla.clicarCheckTipoVia();
		crearRegla.seleccionarCampoTipoVia(datosEntrada[4]);
		crearRegla.escribirCampoValor(datosEntrada[5]);
		crearRegla.confirmar();
		reglas.recargarTabla();
		String[] resultado=new String[reglas.numColumnasTabla()];
		String[] columnas=new String[reglas.numColumnasTabla()];
		for (int i=0; i<columnas.length-1; i++) {
			columnas[i]=labelsCheck[i];
		}
		columnas[columnas.length-1]=labels[0];
		String[] valores=new String[reglas.numColumnasTabla()];
		valores=datosEntrada;
		valores[0]=valores[0].substring(0, valores[0].indexOf(":"));
		valores[1]=valores[1].substring(0, valores[1].indexOf(":"));
		valores[3]=valores[3].substring(0, valores[3].indexOf(":"));
		valores[4]=valores[4].substring(0, valores[4].indexOf(":"));
		resultado=reglas.obtenerFilaTabla(columnas, valores);
		boolean iguales=RutinasTestComunes.assertArrayEquals(datosEntrada,resultado);
		if (!iguales) {
			LoginTest.majors++;
		}
		logger.info("TC2 Crear Regla: Resultado obtenido:"+iguales);
		reglas.volver();
		parametros.volver();
		assertTrue(iguales);
	}
	
}
