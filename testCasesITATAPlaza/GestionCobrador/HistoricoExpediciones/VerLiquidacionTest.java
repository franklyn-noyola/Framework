package testCasesITATAPlaza.GestionCobrador.HistoricoExpediciones;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.ConcesionariasProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.GestionPlazaProcess;
import procesosITATAHost.ConfiguracionSistema.TarifasMoneda.MonedaAceptada.MonedaAceptadaProcess;
import procesosITATAHost.GestionCobrador.HistoricoExpediciones.DetallesHistoricoLiquidacionesProcess;
import procesosITATAPlaza.GestionCobrador.HistoricoExpediciones.HistoricoExpedicionesProcess;
import procesosITATAPlaza.GestionCobrador.HistoricoExpediciones.VerLiquidacionProcess;
import testCasesITATAPlaza.InitBOTest;
import testCasesITATAPlaza.LoginTest;

public class VerLiquidacionTest {

	final String titulo="Ver liquidaci�n"; 
	final String subtitulo="Gesti�n de cobrador   -   Hist�rico de expediciones   -   Detalles   -   Detalles"; 
	final String[] botones={"Volver"};  
	final String encabezado1="Detalle liquidaci�n";  
	final String[] labelsVariables= {"N� turno","Liquidaci�n"};
	final String[] labels= {"N� Bolsa","Plaza","Hora liquidaci�n"};
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
		logger = Logger.getLogger(VerLiquidacionTest.class);
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
		botesthost.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Concesionarias, Plazas, V�as");
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
			botesthost.bo.seleccionarOpcionMenu("Configuraci�n sistema","Tarifas & Moneda","Moneda aceptada");
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
	public void testVerLiquidacion1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Ver Liquidaci�n");
		logger.info("TC1 Ver Liquidaci�n: Probar un an�lisis sint�ctico correcto");
		logger.info("TC1 Ver Liquidaci�n: Resultado esperado=true (analisis correcto)");
		String plaza="Plaza Itata";	
		String concesionaria=obtenerConcesionariaMonedas(plaza);
		String idConcesionaria=concesionaria.substring(0,2);
		botest.bo.seleccionarOpcionMenu("Gesti�n de cobrador","Hist�rico de expediciones");
		HistoricoExpedicionesProcess expediciones=new HistoricoExpedicionesProcess();
		expediciones.clicarCheckDesdeHasta();
		String[] valoresVariables=new String[labelsVariables.length];
		expediciones.actualizar();
		expediciones.recargarTabla();
		int num_fila=1;
		expediciones.seleccionarFila(num_fila);
		expediciones.detalles();
		DetallesHistoricoLiquidacionesProcess detalles=new DetallesHistoricoLiquidacionesProcess();
		valoresVariables[0]=expediciones.obtenerValorColumna(num_fila, "N� turno");
		valoresVariables[1]=expediciones.obtenerValorColumna(num_fila, "Liquidaci�n");
		int num_filadetalles=1;
		detalles.seleccionarFila(num_filadetalles);
		detalles.detalles();
		VerLiquidacionProcess liquidacion=new VerLiquidacionProcess();
		boolean analisis=liquidacion.sintacticAnalysis(titulo, subtitulo, botones, encabezado1, labelsVariables,
				valoresVariables,labels,encabezado2, columnasgridTablaValores, idConcesionaria, monedas,
				columnaSuperiorgridTablaTotales, columnagridTablaTotales, columna1TablaTotales);
		if (analisis) {
			logger.info("TC1 Ver Liquidaci�n: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC1 Ver Liquidaci�n: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		liquidacion.volver();
		detalles.volver();
		expediciones.volver();
		logger.info("TC1 Ver Liquidaci�n: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}
	
	@Test
	public void testVerLiquidacion2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Ver Liquidaci�n");
		logger.info("TC2 Ver Liquidaci�n: Validar el contenido de la tabla Valores");
		logger.info("TC2 Ver Liquidaci�n: Resultado esperado=true (los c�lculos de las filas son correctos)");
		String plaza="Plaza Itata";	
		obtenerConcesionariaMonedas(plaza);
		botest.bo.seleccionarOpcionMenu("Gesti�n de cobrador","Hist�rico de expediciones");
		HistoricoExpedicionesProcess expediciones=new HistoricoExpedicionesProcess();
		expediciones.clicarCheckDesdeHasta();
		expediciones.actualizar();
		expediciones.recargarTabla();
		int num_fila=1;
		expediciones.seleccionarFila(num_fila);
		expediciones.detalles();
		DetallesHistoricoLiquidacionesProcess detalles=new DetallesHistoricoLiquidacionesProcess();
		int num_filadetalles=1;
		detalles.seleccionarFila(num_filadetalles);
		detalles.detalles();
		VerLiquidacionProcess liquidacion=new VerLiquidacionProcess();
		boolean resultado=liquidacion.validarTablaValores();
		logger.info("TC2 Ver Liquidaci�n: Resultado obtenido="+resultado);
		assertTrue(resultado);
	}
		
	@Test
	public void testVerLiquidacion3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Ver Liquidaci�n");
		logger.info("TC3 Ver Liquidaci�n: Validar el contenido de la tabla Totales");
		logger.info("TC3 Ver Liquidaci�n: Resultado esperado=true (los c�lculos de las filas son correctos)");
		String plaza="Plaza Itata";	
		obtenerConcesionariaMonedas(plaza);
		botest.bo.seleccionarOpcionMenu("Gesti�n de cobrador","Hist�rico de expediciones");
		HistoricoExpedicionesProcess expediciones=new HistoricoExpedicionesProcess();
		expediciones.clicarCheckDesdeHasta();
		expediciones.actualizar();
		expediciones.recargarTabla();
		int num_fila=1;
		expediciones.seleccionarFila(num_fila);
		expediciones.detalles();
		DetallesHistoricoLiquidacionesProcess detalles=new DetallesHistoricoLiquidacionesProcess();
		int num_filadetalles=1;
		String total=detalles.obtenerValorColumna(num_filadetalles, "Total");
		detalles.seleccionarFila(num_filadetalles);
		detalles.detalles();
		VerLiquidacionProcess liquidacion=new VerLiquidacionProcess();
		boolean resultado=liquidacion.validarTablaTotales(total);
		logger.info("TC3 Ver Liquidaci�n: Resultado obtenido="+resultado);
		assertTrue(resultado);
	}
	
}
