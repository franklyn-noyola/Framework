package testCasesITATAHost.GestionCobrador.HistoricoLiquidaciones;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.ConcesionariasProcess;
import procesosITATAHost.ConfiguracionSistema.TarifasMoneda.MonedaAceptada.MonedaAceptadaProcess;
import procesosITATAHost.GestionCobrador.HistoricoExpediciones.VerLiquidacionProcess;
import procesosITATAHost.GestionCobrador.HistoricoLiquidaciones.HistoricoLiquidacionesProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;



public class LiquidacionesVerLiquidacionTest {

	final String titulo="Ver liquidaci�n"; 
	final String subtitulo="Gesti�n de cobrador   -   Hist�rico de liquidaciones   -   Detalles"; 
	final String[] botones={"Volver"};  
	final String encabezado1="Detalle liquidaci�n";  
	final String[] labelsVariables= {"N� turno","Liquidaci�n"};
	final String[] labels= {"N� Bolsa","Concesionaria","Plaza","Hora liquidaci�n"};
	final String encabezado2="Valores";
	final String[] columnasgridTablaValores= {"Tipo","Denominaci�n","N�mero","Valor ($)"};
	final String[] columnaSuperiorgridTablaTotales= {"Detalle de bolsa"};
	final String[] columnagridTablaTotales= {"Origen","Valor ($)"};
	final String[] columna1TablaTotales= {"Efectivo","Cheques","Total en la bolsa"};
	final String msg_error_noseleccionado="Ning�n elemento seleccionado";
	static InitBOTest botest;
	public static int numFilas;
	static String[][] monedas;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(LiquidacionesVerLiquidacionTest.class);
		logger.debug("setUpBeforeClass-LiquidacionesVerLiquidacionTest");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		logger.debug("tearDownAfterClass-LiquidacionesVerLiquidacionTest");
		InitBOTest.tearDownAfterClass();
	}
	
	@After
	public void AfterTest() throws Exception {
		logger.info("Fin TC");
		logger.info("-----------------------------------------------------------------");
	}
	
	private String obtenerMonedasAceptadas(String concesionaria) {
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Concesionarias, Plazas, V�as");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String idConcesionaria=concesionarias.obtenerValorColumna("ID", "Nombre", concesionaria);
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Tarifas & Moneda","Moneda aceptada");
		MonedaAceptadaProcess moneda=new MonedaAceptadaProcess();
		int numFilasTabla=moneda.numFilasTotalTabla();
		numFilas=0;
		monedas=new String[numFilasTabla][moneda.numColumnasTabla()];
		for (int i=1; i<=numFilasTabla; i++) {
			if (moneda.obtenerValorColumna(i, "Concesionaria").equals(idConcesionaria)) {
				monedas[numFilas]=moneda.obtenerFilaTabla(i);
				numFilas++;
			}
		}
		return idConcesionaria;
	}
	
	@Test
	public void testLiquidacionesVerLiquidacion1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Liquidaciones Ver Liquidaci�n");
		logger.info("TC1 Liquidaciones Ver Liquidaci�n: Probar un an�lisis sint�ctico correcto");
		logger.info("TC3 Liquidaciones Ver Liquidaci�n: Resultado esperado=true (analisis correcto)");
		String concesionaria="Autopista del Itata";
		String plaza="Plaza Itata";	
		String idConcesionaria=obtenerMonedasAceptadas(concesionaria);
		botest.bo.seleccionarOpcionMenu("Gesti�n de cobrador","Hist�rico de liquidaciones");
		HistoricoLiquidacionesProcess liquidaciones=new HistoricoLiquidacionesProcess();
		liquidaciones.clicarCheckDesdeHasta();
		String[] valoresVariables=new String[labelsVariables.length];
		liquidaciones.seleccionarOpcionConcesionaria(concesionaria);
		liquidaciones.seleccionarOpcionPlaza(plaza);
		liquidaciones.actualizar();
		liquidaciones.recargarTabla();
		int num_fila=1;
		liquidaciones.seleccionarFila(num_fila);
		valoresVariables[0]=liquidaciones.obtenerValorColumna(num_fila, "N� turno");
		valoresVariables[1]=liquidaciones.obtenerValorColumna(num_fila, "Liquidaci�n");
		liquidaciones.detalles();
		VerLiquidacionProcess liquidacion=new VerLiquidacionProcess();
		boolean analisis=liquidacion.sintacticAnalysis(titulo, subtitulo, botones, encabezado1, labelsVariables,
				valoresVariables,labels,encabezado2, columnasgridTablaValores, idConcesionaria, monedas,
				columnaSuperiorgridTablaTotales, columnagridTablaTotales, columna1TablaTotales);
		if (analisis) {
			logger.info("TC1 Liquidaciones Ver Liquidaci�n: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC1 Liquidaciones Ver Liquidaci�n: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		liquidacion.volver();
		liquidaciones.volver();
		logger.info("TC3 Liquidaciones Ver Liquidaci�n: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}
	
	@Test
	public void testLiquidacionesVerLiquidacion2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Liquidaciones Ver Liquidaci�n");
		logger.info("TC2 Liquidaciones Ver Liquidaci�n: Validar el contenido de la tabla Valores");
		logger.info("TC2 Liquidaciones Ver Liquidaci�n: Resultado esperado=true (los c�lculos de las filas son correctos)");
		String concesionaria="Autopista del Itata";
		String plaza="Plaza Itata";	
		obtenerMonedasAceptadas(concesionaria);
		botest.bo.seleccionarOpcionMenu("Gesti�n de cobrador","Hist�rico de liquidaciones");
		HistoricoLiquidacionesProcess liquidaciones=new HistoricoLiquidacionesProcess();
		liquidaciones.clicarCheckDesdeHasta();
		liquidaciones.seleccionarOpcionConcesionaria(concesionaria);
		liquidaciones.seleccionarOpcionPlaza(plaza);
		liquidaciones.actualizar();
		liquidaciones.recargarTabla();
		int num_fila=1;
		liquidaciones.seleccionarFila(num_fila);
		liquidaciones.detalles();
		VerLiquidacionProcess liquidacion=new VerLiquidacionProcess();
		boolean resultado=liquidacion.validarTablaValores();
		logger.info("TC2 Liquidaciones Ver Liquidaci�n: Resultado obtenido="+resultado);
		liquidacion.volver();
		liquidaciones.volver();
		assertTrue(resultado);
	}
		
	@Test
	public void testLiquidacionesVerLiquidacion3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Liquidaciones Ver Liquidaci�n");
		logger.info("TC3 Liquidaciones Ver Liquidaci�n: Validar el contenido de la tabla Totales");
		logger.info("TC3 Liquidaciones Ver Liquidaci�n: Resultado esperado=true (los c�lculos de las filas son correctos)");
		String concesionaria="Autopista del Itata";
		String plaza="Plaza Itata";	
		obtenerMonedasAceptadas(concesionaria);
		botest.bo.seleccionarOpcionMenu("Gesti�n de cobrador","Hist�rico de liquidaciones");
		HistoricoLiquidacionesProcess liquidaciones=new HistoricoLiquidacionesProcess();
		liquidaciones.clicarCheckDesdeHasta();
		liquidaciones.seleccionarOpcionConcesionaria(concesionaria);
		liquidaciones.seleccionarOpcionPlaza(plaza);
		liquidaciones.actualizar();
		liquidaciones.recargarTabla();
		int num_fila=1;
		liquidaciones.seleccionarFila(num_fila);
		String total=liquidaciones.obtenerValorColumna(num_fila, "Total");
		liquidaciones.seleccionarFila(num_fila);
		liquidaciones.detalles();
		VerLiquidacionProcess liquidacion=new VerLiquidacionProcess();
		boolean resultado=liquidacion.validarTablaTotales(total);
		logger.info("TC3 Liquidaciones Ver Liquidaci�n: Resultado obtenido="+resultado);
		liquidacion.volver();
		liquidaciones.volver();
		assertTrue(resultado);
	}
	
}
