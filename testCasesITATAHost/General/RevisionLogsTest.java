package testCasesITATAHost.General;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import procesosITATAHost.General.OpcionesRevisionLogsFecha;
import procesosITATAHost.General.RevisionLogsProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class RevisionLogsTest {

	final static String titulo_revision_logs="Revisión de logs"; 
	final static String subtitulo_revision_logs="General   -   Revisión de logs"; 
	final static String[] botones_revision_logs={"Informe","Búsqueda","Volver"};  
	final static String encabezado_revision_logs=null;
	final static String[] labels_revision_logs= {"Operador","Clave de acción","Objeto","Acción","Desde","Hasta"};
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(RevisionLogsTest.class);
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
	public void testRevisionLogs1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Revision Logs");
		logger.info("TC1 Revision Logs: Probar el análisis sintáctico correcto");
		logger.info("TC1 Revision Logs: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("General","Revisión de logs");
		RevisionLogsProcess revisionLogs=new RevisionLogsProcess();
		boolean analisis=revisionLogs.sintacticAnalysis(titulo_revision_logs, subtitulo_revision_logs, botones_revision_logs, encabezado_revision_logs, labels_revision_logs);
		if (analisis) {
			logger.info("TC1 Revision Logss: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Revision Logs: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		assertTrue(analisis);
	}

	@Test
	public void testRevisionLogs2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Revision Logs");
		logger.info("TC2 Revision Logsa: probar la búsqueda correcta");
		logger.info("TC2 Revision Logs: resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("General","Revisión de logs");
		RevisionLogsProcess revisionLogs=new RevisionLogsProcess();
		revisionLogs.escribirFecha(OpcionesRevisionLogsFecha.Desde, "19/03/2018",null);
		revisionLogs.escribirFecha(OpcionesRevisionLogsFecha.Hasta, "19/03/2018",null);
		revisionLogs.busqueda();
		boolean limpiar=revisionLogs.limpiar();
		if (!limpiar) {
			LoginTest.majors++;
		}
		logger.info("TC2: Resultado obtenido: "+limpiar);
		assertTrue(limpiar);
	}
	
	@Test
	public void testRevisionLogs3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Revision Logs");
		logger.info("TC2 Revision Logsa: probar el borrado correcto de criterios de búsqueda");
		logger.info("TC2 Revision Logs: resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("General","Revisión de logs");
		RevisionLogsProcess revisionLogs=new RevisionLogsProcess();
		revisionLogs.escribirFecha(OpcionesRevisionLogsFecha.Desde, "19/03/2018",null);
		revisionLogs.escribirFecha(OpcionesRevisionLogsFecha.Hasta, "19/03/2018",null);
		//revisionLogs.busqueda();
		boolean limpiar=revisionLogs.limpiar();
		if (!limpiar) {
			LoginTest.majors++;
		}
		logger.info("TC2: Resultado obtenido: "+limpiar);
		assertTrue(limpiar);
	}
}
