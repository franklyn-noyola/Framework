package testCasesITATAPlaza.GestionCobrador.HistoricoLiquidaciones;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.ConcesionariasProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.GestionPlazaProcess;
import procesosITATAHost.ConfiguracionSistema.TarifasMoneda.MonedaAceptada.MonedaAceptadaProcess;
import procesosITATAPlaza.GestionCobrador.HistoricoExpediciones.VerLiquidacionProcess;
import procesosITATAPlaza.GestionCobrador.HistoricoLiquidaciones.HistoricoLiquidacionesProcess;
import testCasesITATAPlaza.InitBOTest;
import testCasesITATAPlaza.LoginTest;

public class LiquidacionesVerLiquidacionTest {

	final String titulo="Ver liquidación"; 
	final String subtitulo="Gestión de cobrador   -   Histórico de liquidaciones   -   Detalles"; 
	final String[] botones={"Volver"};  
	final String encabezado1="Detalle liquidación";  
	final String[] labelsVariables= {"Nº turno","Liquidación"};
	final String[] labels= {"Nº Bolsa","Plaza","Hora liquidación"};
	final String encabezado2="Valores";
	final String[] columnasgridTablaValores= {"Tipo","Denominación","Número","Valor ($)"};
	final String[] columnaSuperiorgridTablaTotales= {"Detalle de bolsa"};
	final String[] columnagridTablaTotales= {"Origen","Valor ($)"};
	final String[] columna1TablaTotales= {"Efectivo","Cheques","Total en la bolsa"};
	final String msg_error_noseleccionado="Ningún elemento seleccionado";
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
		logger.debug("setUpBeforeClass-HistoricoExpedicionesTest");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		logger.debug("tearDownAfterClass-HistoricoExpedicionesTest");
		InitBOTest.tearDownAfterClass();
	}
	
	@After
	public void AfterTest() throws Exception {
		logger.info("Fin TC");
		logger.info("-----------------------------------------------------------------");
	}
	
	private String obtenerConcesionariaMonedas(String plaza) {
		
		testCasesITATAHost.InitBOTest botesthost=new testCasesITATAHost.InitBOTest();
		try {
			testCasesITATAHost.InitBOTest.setUpBeforeClass();
			botesthost.setUpBefore();
			botesthost.testInitBO();
		}
		catch (Exception e) {
			System.exit(0);
		}
		botesthost.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		boolean encontrado=false;
		String concesionaria=null;
		int filaconcesionaria=1;
		for (int i=1; i<=concesionarias.numFilasTabla() && !encontrado;i++) {
			concesionarias.seleccionarFila(i);
			concesionarias.editarPlazas();
			GestionPlazaProcess plazas=new GestionPlazaProcess();
			for (int j=1;j<=plazas.numFilasTabla() && !encontrado;j++) {
				plazas.seleccionarFila(j);
				if (plazas.obtenerValorColumna(j, "Nombre").equals(plaza)) {
					encontrado=true;
					filaconcesionaria=i;
				}
			}
			plazas.volver();
		}
		if (encontrado) {
			concesionaria=concesionarias.obtenerValorColumna(filaconcesionaria, "ID")+"-"+concesionarias.obtenerValorColumna(filaconcesionaria, "Nombre");
			String idConcesionaria=concesionaria.substring(0,2);
			botesthost.bo.seleccionarOpcionMenu("Configuración sistema","Tarifas & Moneda","Moneda aceptada");
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
		}
		try {
			testCasesITATAHost.InitBOTest.tearDownAfterClass();
		}
		catch (Exception e) {
			System.exit(0);
		}
		return concesionaria;
	}
	
	@Test
	public void testLiquidacionesVerLiquidacion1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Liquidaciones Ver Liquidación");
		logger.info("TC1 Liquidaciones Ver Liquidación: Probar un análisis sintáctico correcto");
		logger.info("TC1 Liquidaciones Ver Liquidación: Resultado esperado=true (analisis correcto)");
		String plaza="Plaza Itata";	
		String concesionaria=obtenerConcesionariaMonedas(plaza);
		String idConcesionaria=concesionaria.substring(0,2);
		botest.bo.seleccionarOpcionMenu("Gestión de cobrador","Histórico de liquidaciones");
		HistoricoLiquidacionesProcess liquidaciones=new HistoricoLiquidacionesProcess();
		liquidaciones.clicarCheckDesdeHasta();
		String[] valoresVariables=new String[labelsVariables.length];
		liquidaciones.actualizar();
		liquidaciones.recargarTabla();
		int num_fila=1;
		valoresVariables[0]=liquidaciones.obtenerValorColumna(num_fila, "Nº turno");
		valoresVariables[1]=liquidaciones.obtenerValorColumna(num_fila, "Liquidación");
		liquidaciones.seleccionarFila(num_fila);
		liquidaciones.detalles();
		VerLiquidacionProcess liquidacion=new VerLiquidacionProcess();
		boolean analisis=liquidacion.sintacticAnalysis(titulo, subtitulo, botones, encabezado1, labelsVariables,
				valoresVariables,labels,encabezado2, columnasgridTablaValores, idConcesionaria, monedas,
				columnaSuperiorgridTablaTotales, columnagridTablaTotales, columna1TablaTotales);
		if (analisis) {
			logger.info("TC1 Liquidaciones Ver Liquidación: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Liquidaciones Ver Liquidación: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		liquidacion.volver();
		liquidaciones.volver();
		logger.info("TC1 Liquidaciones Ver Liquidación: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}
	
	@Test
	public void testLiquidacionesVerLiquidacion2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Liquidaciones Ver Liquidación");
		logger.info("TC2 Liquidaciones Ver Liquidación: Validar el contenido de la tabla Valores");
		logger.info("TC2 Liquidaciones Ver Liquidación: Resultado esperado=true (los cálculos de las filas son correctos)");
		String plaza="Plaza Itata";	
		obtenerConcesionariaMonedas(plaza);
		botest.bo.seleccionarOpcionMenu("Gestión de cobrador","Histórico de liquidaciones");
		HistoricoLiquidacionesProcess liquidaciones=new HistoricoLiquidacionesProcess();
		liquidaciones.clicarCheckDesdeHasta();
		liquidaciones.actualizar();
		liquidaciones.recargarTabla();
		int num_fila=1;
		liquidaciones.seleccionarFila(num_fila);
		liquidaciones.detalles();
		VerLiquidacionProcess liquidacion=new VerLiquidacionProcess();
		boolean resultado=liquidacion.validarTablaValores();
		logger.info("TC2 Liquidaciones Ver Liquidación: Resultado obtenido="+resultado);
		assertTrue(resultado);
	}
		
	@Test
	public void testLiquidacionesVerLiquidacion3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Liquidaciones Ver Liquidación");
		logger.info("TC3 Liquidaciones Ver Liquidación: Validar el contenido de la tabla Totales");
		logger.info("TC3 Liquidaciones Ver Liquidación: Resultado esperado=true (los cálculos de las filas son correctos)");
		String plaza="Plaza Itata";	
		obtenerConcesionariaMonedas(plaza);
		botest.bo.seleccionarOpcionMenu("Gestión de cobrador","Histórico de liquidaciones");
		HistoricoLiquidacionesProcess liquidaciones=new HistoricoLiquidacionesProcess();
		liquidaciones.clicarCheckDesdeHasta();
		liquidaciones.actualizar();
		liquidaciones.recargarTabla();
		int num_fila=1;
		String total=liquidaciones.obtenerValorColumna(num_fila, "Total");
		liquidaciones.seleccionarFila(num_fila);
		liquidaciones.detalles();
		VerLiquidacionProcess liquidacion=new VerLiquidacionProcess();
		boolean resultado=liquidacion.validarTablaTotales(total);
		logger.info("TC3 Liquidaciones Ver Liquidación: Resultado obtenido="+resultado);
		assertTrue(resultado);
	}
	
}
