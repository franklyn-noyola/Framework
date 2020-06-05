package testCasesITATAHost.GestionCobrador.HistoricoLiquidaciones;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.ConcesionariasProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.GestionPlazaProcess;
import procesosITATAHost.GestionCobrador.HistoricoExpediciones.HistoricoExpedicionesProcess;
import procesosITATAHost.GestionCobrador.HistoricoLiquidaciones.HistoricoLiquidacionesProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.ErrorMessage;


public class HistoricoLiquidacionesTest {

	final  String titulo="Hist�rico de liquidaciones"; 
	final  String subtitulo="Gesti�n de cobrador   -   Hist�rico de liquidaciones"; 
	final  String[] botones={"Actualizar","Volver"};  
	final  String[] labels= {"Operador","Concesionaria","Plaza","Tipo"};
	final String[][] labels_rango= {{null,"Desde","Hasta"}};
	final  String[] columnasgrid= {"Fecha de liquidaci�n","Operador","N� Bolsa","Liquidaci�n","Tipo","N� turno","Total"};
	final  String[] botonescrud= {"Detalles"};
	final  String[] botonesgrid= {"Primera","<",">","�ltima"};
	final  String msg_error_noseleccionado="Ning�n elemento seleccionado";
	static InitBOTest botest;
	static Connection DBconnection;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(HistoricoLiquidacionesTest.class);
		logger.debug("setUpBeforeClass-HistoricoLiquidacionesTest");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		logger.debug("tearDownAfterClass-HistoricoLiquidacionesTest");
		InitBOTest.tearDownAfterClass();
	}
	
	@After
	public void AfterTest() throws Exception {
		logger.info("Fin TC");
		logger.info("-----------------------------------------------------------------");
	}
	
	@Test
	public void testHistoricoLiquidaciones1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Hist�rico Liquidaciones");
		logger.info("TC1 Hist�rico Liquidaciones: Probar un an�lisis sint�ctico correcto");
		botest.bo.seleccionarOpcionMenu("Gesti�n de cobrador","Hist�rico de liquidaciones");
		HistoricoLiquidacionesProcess liquidaciones=new HistoricoLiquidacionesProcess();
		boolean analisis=liquidaciones.sintacticAnalysis(titulo, subtitulo, botones, botonescrud, labels, labels_rango,
				columnasgrid, botonesgrid);
		if (analisis) {
			logger.info("TC1 Hist�rico Liquidaciones: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC1 Hist�rico Liquidaciones: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		assertTrue(analisis);
	}
	
	@Test
	public void testHistoricoLiquidaciones2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Hist�rico Liquidaciones");
		logger.info("TC2 Hist�rico Liquidaciones: Probar la visualizaci�n de los detalles de una liquidaci�n sin seleccionarla");
		logger.info("TC2 Hist�rico Liquidaciones: Resultado esperado=true");
		botest.bo.seleccionarOpcionMenu("Gesti�n de cobrador","Hist�rico de liquidaciones");
		HistoricoLiquidacionesProcess liquidaciones=new HistoricoLiquidacionesProcess();

		liquidaciones.detalles();
		boolean analisis=false;
		boolean hayMsgError=liquidaciones.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=liquidaciones.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC2 Hist�rico Liquidaciones: Deber�a haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC2 Hist�rico Liquidaciones: Resultado obtenido="+(hayMsgError && analisis));
		liquidaciones.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	@Test
	public void testHistoricoLiquidaciones3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Hist�rico Liquidaciones");
		logger.info("TC3 Hist�rico Liquidaciones: Probar la validaci�n de formato del campo Desde-Hasta");
		logger.info("TC3 Hist�rico Liquidaciones: Resultado esperado=true (aparecen los tooltips correspondientes)");
		botest.bo.seleccionarOpcionMenu("Gesti�n de cobrador","Hist�rico de liquidaciones");
		HistoricoLiquidacionesProcess liquidaciones=new HistoricoLiquidacionesProcess();

		if (!liquidaciones.estaCheckActivado()) {
			liquidaciones.clicarCheckDesdeHasta();
		}
		liquidaciones.escribirFechaDesde("a");
		boolean hayMsgError=liquidaciones.hayErrorValidacionFormatoFechaDesde();
		if (!hayMsgError) {
			logger.error("TC3 Hist�rico Liquidaciones: Deber�a haber aparecido el tooltip de error de formato por fecha en Desde");
			LoginTest.minors++;
		}
		liquidaciones.escribirFechaHasta("a");
		boolean hayMsgError2=liquidaciones.hayErrorValidacionFormatoFechaHasta();
		if (!hayMsgError2) {
			logger.error("TC3 Hist�rico Liquidaciones: Deber�a haber aparecido el tooltip de error de formato por fecha en Hasta");
			LoginTest.minors++;
		}
		liquidaciones.escribirFechaDesde("01/01/2018");
		liquidaciones.escribirFechaHasta("01/01/2018");
		liquidaciones.escribirHoraDesde("a");
		boolean hayMsgError3=liquidaciones.hayErrorValidacionFormatoHoraDesde();
		if (!hayMsgError3) {
			logger.error("TC3 Hist�rico Liquidaciones: Deber�a haber aparecido el tooltip de error de formato por hora en Desde");
			LoginTest.minors++;
		}
		liquidaciones.escribirHoraHasta("a");
		boolean hayMsgError4=liquidaciones.hayErrorValidacionFormatoHoraHasta();
		if (!hayMsgError4) {
			logger.error("TC3 Hist�rico Liquidaciones: Deber�a haber aparecido el tooltip de error de formato por hora en Hasta");
			LoginTest.minors++;
		}
		logger.info("TC3 Hist�rico Liquidaciones: Resultado obtenido="+(hayMsgError && hayMsgError2 && hayMsgError3 && hayMsgError4));
		liquidaciones.volver();
		assertTrue(hayMsgError && hayMsgError2 && hayMsgError3 && hayMsgError4);
	}
	
	@Test
	public void testHistoricoLiquidaciones4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Hist�rico Liquidaciones");
		logger.info("TC4 Hist�rico Liquidaciones: Probar la validaci�n de requerido del campo Desde-Hasta");
		logger.info("TC4 Hist�rico Liquidaciones: Resultado esperado=true (aparecen los tooltips correspondientes)");
		botest.bo.seleccionarOpcionMenu("Gesti�n de cobrador","Hist�rico de liquidaciones");
		HistoricoLiquidacionesProcess liquidaciones=new HistoricoLiquidacionesProcess();

		if (!liquidaciones.estaCheckActivado()) {
			liquidaciones.clicarCheckDesdeHasta();
		}
		liquidaciones.borrarFechaDesde();
		boolean hayMsgError=liquidaciones.hayErrorValidacionRequeridoFechaDesde();
		if (!hayMsgError) {
			logger.error("TC4 Hist�rico Liquidaciones: Deber�a haber aparecido el tooltip de error de requerido por fecha en Desde");
			LoginTest.minors++;
		}
		liquidaciones.borrarFechaHasta();
		boolean hayMsgError2=liquidaciones.hayErrorValidacionRequeridoFechaHasta();
		if (!hayMsgError2) {
			logger.error("TC4 Hist�rico Liquidaciones: Deber�a haber aparecido el tooltip de error de requerido por fecha en Hasta");
			LoginTest.minors++;
		}
		liquidaciones.escribirFechaDesde("01/01/2018");
		liquidaciones.escribirFechaHasta("01/01/2018");
		
		liquidaciones.borrarHoraDesde();
		boolean hayMsgError3=liquidaciones.hayErrorValidacionRequeridoHoraDesde();
		if (!hayMsgError3) {
			logger.error("TC4 Hist�rico Liquidaciones: Deber�a haber aparecido el tooltip de error de requerido por hora en Desde");
			LoginTest.minors++;
		}
		liquidaciones.borrarHoraHasta();
		boolean hayMsgError4=liquidaciones.hayErrorValidacionRequeridoHoraHasta();
		if (!hayMsgError4) {
			logger.error("TC4 Hist�rico Liquidaciones: Deber�a haber aparecido el tooltip de error de requerido por hora en Hasta");
			LoginTest.minors++;
		}
		logger.info("TC4 Hist�rico Liquidaciones: Resultado obtenido="+(hayMsgError && hayMsgError2 && hayMsgError3 && hayMsgError4));
		liquidaciones.volver();
		assertTrue(hayMsgError && hayMsgError2 && hayMsgError3 && hayMsgError4);
	}
	
	//@Test //No funciona
	public void testHistoricoLiquidaciones5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Hist�rico Liquidaciones");
		logger.info("TC5 Hist�rico Liquidaciones: Comprobaci�n del contenido de la tabla contra BD");
		logger.info("TC5 Hist�rico Liquidaciones: Resultado esperado=true");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Concesionarias, Plazas, V�as");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String concesionaria="Autopista del Itata";
		String plaza="Plaza Itata";
		String codigoconcesionaria=concesionarias.obtenerValorColumna("ID", "Nombre", concesionaria);
		int indice=concesionarias.obtenerNumFilaTabla("ID", codigoconcesionaria);
		concesionarias.seleccionarFila(indice);
		concesionarias.editarPlazas();
		GestionPlazaProcess nodoplaza=new GestionPlazaProcess();
		String codigoplaza=nodoplaza.obtenerValorColumna("C�digo", "Nombre", plaza);
		
		botest.bo.seleccionarOpcionMenu("Gesti�n de cobrador","Hist�rico de liquidaciones");
		HistoricoExpedicionesProcess expediciones=new HistoricoExpedicionesProcess();
		expediciones.clicarCheckDesdeHasta();
		expediciones.seleccionarOpcionConcesionaria(concesionaria);
		expediciones.seleccionarOpcionPlaza(plaza);
		expediciones.actualizar();
		expediciones.recargarTabla();
		
		// Se valida que la query de la BD est� contenida en la Tabla
		boolean iguales=true;
		try {  // Falta calcular el valor
			String queryString = "select time,banktour,operator,vanplate,employees,comment"
					+ " from dbo.abanktour where tollcompany='"+codigoconcesionaria+"' and plaza='"+codigoplaza+"' order by time desc";
			Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(queryString);
			int tama�o=RutinasTestComunes.obtenerTama�oConsulta(rs);
			if (tama�o!=expediciones.numFilasTotalTabla()) {
				logger.error("TC5 Hist�rico Liquidaciones: El n�mero de registros de la b�squeda es " +tama�o+" y no coincide con el de la tabla: "+expediciones.numFilasTabla());
				LoginTest.critical++;
				iguales=false;
			}
			while (rs.next() && iguales) {
				iguales=expediciones.buscarRegistroBDEnTabla(rs);
			}
			if (!iguales) {
				LoginTest.critical++;
			}
		}
		catch (Exception e) {
			logger.fatal("TC5 Hist�rico Liquidaciones: Error al acceder a la BD");
			LoginTest.critical++;
			e.printStackTrace();
			fail();
		}
		
		// Se valida que el contenido de la Tabla est� en BD
		expediciones.pulsarBotonTablaPrimero();
		boolean iguales2=true;
		do {
			expediciones.recargarTabla();
			for (int num_fila=1; (num_fila<=expediciones.numFilasTabla()) && iguales2; num_fila++) {
				String elemento=expediciones.obtenerValorColumna(num_fila, "Expedici�n");
				String queryString = "select time,banktour,operator,vanplate,employees,comment"
						+ " from dbo.abanktour where tollcompany='"+codigoconcesionaria+"' and plaza='"+codigoplaza+"' and banktour='"+elemento+"'";
				try {
					Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs = stmt.executeQuery(queryString);
					int tama�o=RutinasTestComunes.obtenerTama�oConsulta(rs);
					if (tama�o!=1) {
						logger.error("TC5 Hist�rico Liquidaciones: El n�mero de registros de la b�squeda es " +tama�o+" y deber�a ser 1");
						LoginTest.critical++;
						iguales2=false;
					}
					else {
						rs.next();
						iguales2=expediciones.buscarRegistroTablaEnBD(rs, elemento);
						if (!iguales2) {
							logger.error("TC5 Hist�rico Liquidaciones: El registro de la tabla " +elemento+" no coincide con el de la BD.");
							LoginTest.majors++;
						}
					}
				}
				catch (Exception e) {
					logger.fatal("TC5 Hist�rico Liquidaciones: Error al acceder a la BD");
					LoginTest.critical++;
					e.printStackTrace();
					fail();
				}
			}
			if (!expediciones.ultimaTabla()) {
				expediciones.pulsarBotonTablaSiguiente();
			}
		}
		while (!expediciones.ultimaTabla() && iguales2);
		logger.info("TC5 Hist�rico Liquidaciones: Resultado obtenido="+(iguales && iguales2));
		assertTrue(iguales && iguales2);
	}
}
