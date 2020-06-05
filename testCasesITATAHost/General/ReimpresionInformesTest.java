package testCasesITATAHost.General;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.General.ReimpresionInformes.OpcionesReimpresionInformesFecha;
import procesosITATAHost.General.ReimpresionInformes.ReimpresionInformesProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;

public class ReimpresionInformesTest {

	final static String titulo_reimpresion_informes="Selección de informes"; 
	final static String subtitulo_reimpresion_informes="General   -   Reimpresión de informes"; 
	final static String[] botones_reimpresion_informes={"Búsqueda","Volver"}; 
	final static String encabezado_reimpresion_informes="Parámetros de búsqueda para encontrar informes impresos";
	final static String[] labels_reimpresion_informes= {"Operador","Tipo de informe","Desde","Hasta","h","h"};
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(ReimpresionInformesTest.class);
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
	public void testReimpresionInformes() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Reimpresión de informes");
		logger.info("TC1 Reimpresión de informesa: probar el análisis sintáctico correcto");
		logger.info("TC1 Reimpresión de informes: resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("General","Reimpresión de informes");
		ReimpresionInformesProcess reimpresionInformes=new ReimpresionInformesProcess();
		boolean analisis=reimpresionInformes.sintacticAnalysis(titulo_reimpresion_informes, subtitulo_reimpresion_informes,
				botones_reimpresion_informes, encabezado_reimpresion_informes, labels_reimpresion_informes);
		if (analisis) {
			logger.info("TC1 Reimpresión Informes: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Reimpresión Informes: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		assertTrue(analisis);
	}
	
	@Test
	public void testReimpresionInformes2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Reimpresión de informes");
		logger.info("TC2  Reimpresión de informesa: probar la búsqueda correcta");
		logger.info("TC2:  Reimpresión de informes: resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("General","Reimpresión de informes");
		ReimpresionInformesProcess reimpresionInformes=new ReimpresionInformesProcess();
		reimpresionInformes.borrarFecha(OpcionesReimpresionInformesFecha.Desde);
		reimpresionInformes.escribirFecha(OpcionesReimpresionInformesFecha.Desde,"25/03/2018","00:00");
		reimpresionInformes.escribirFecha(OpcionesReimpresionInformesFecha.Hasta,"07/05/2018",null);
		reimpresionInformes.busqueda();
	}
}
