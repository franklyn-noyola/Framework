package testCasesITATAPlaza.GestionCobrador.HistoricoExpediciones;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.ConcesionariasProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.GestionPlazaProcess;
import procesosITATAHost.GestionCobrador.HistoricoExpediciones.DetallesHistoricoLiquidacionesProcess;
import procesosITATAPlaza.GestionCobrador.HistoricoExpediciones.HistoricoExpedicionesProcess;
import testCasesITATAPlaza.InitBOTest;
import testCasesITATAPlaza.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.ErrorMessage;


public class DetallesHistoricoLiquidacionesTest {

	final  String titulo="Hist�rico de liquidaciones"; 
	final  String subtitulo="Gesti�n de cobrador   -   Hist�rico de expediciones   -   Detalles"; 
	final  String[] botones={"Volver"};  
	final  String[] labelsVariables= {"Expedici�n","Plaza"};
	final  String[] columnasgrid= {"Fecha de liquidaci�n","Operador","N� Bolsa","Liquidaci�n","Tipo","N� turno","Total"};
	final  String[] botonescrud= {"Detalles"};
	final  String[] botonesgrid= {"Primera","<",">","�ltima"};
	final  String msg_error_noseleccionado="Ning�n elemento seleccionado";
	static InitBOTest botest;
	static Connection DBconnectionPlaza;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(DetallesHistoricoLiquidacionesTest.class);
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
	
	@Test
	public void testDetallesHistoricoLiquidaciones1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Detalles Hist�rico Liquidaciones");
		logger.info("TC1 Detalles Hist�rico Liquidaciones: Probar un an�lisis sint�ctico correcto");
		botest.bo.seleccionarOpcionMenu("Gesti�n de cobrador","Hist�rico de expediciones");
		HistoricoExpedicionesProcess expediciones=new HistoricoExpedicionesProcess();
		expediciones.clicarCheckDesdeHasta();
		String[] valoresVariables=new String[labelsVariables.length];
		expediciones.actualizar();
		expediciones.recargarTabla();
		int num_fila=1;
		valoresVariables[0]=expediciones.obtenerValorColumna(num_fila, "Expedici�n");
		valoresVariables[1]=expediciones.leerOpcionPlaza();
		expediciones.seleccionarFila(num_fila);
		expediciones.detalles();
		DetallesHistoricoLiquidacionesProcess detalles=new DetallesHistoricoLiquidacionesProcess();
		boolean analisis=detalles.sintacticAnalysis(titulo, subtitulo, botones,  labelsVariables,
				valoresVariables,botonescrud, columnasgrid, botonesgrid);
		if (analisis) {
			logger.info("TC1 Detalles Hist�rico Liquidaciones: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC1 Detalles Hist�rico Liquidaciones: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		detalles.volver();
		expediciones.volver();
		assertTrue(analisis);
	}
	
	@Test
	public void testDetallesHistoricoLiquidaciones2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Detalles Hist�rico Liquidaciones");
		logger.info("TC2 Detalles Hist�rico Liquidaciones: Probar la visualizaci�n de los detalles de una liquidaci�n sin seleccionarla");
		logger.info("TC2 Detalles Hist�rico Liquidaciones: Resultado esperado=true");
		botest.bo.seleccionarOpcionMenu("Gesti�n de cobrador","Hist�rico de expediciones");
		HistoricoExpedicionesProcess expediciones=new HistoricoExpedicionesProcess();
		expediciones.clicarCheckDesdeHasta();
		expediciones.actualizar();
		expediciones.recargarTabla();
		int num_fila=1;
		expediciones.seleccionarFila(num_fila);
		expediciones.detalles();
		DetallesHistoricoLiquidacionesProcess detalles=new DetallesHistoricoLiquidacionesProcess();
		detalles.detalles();
		boolean analisis=false;
		boolean hayMsgError=expediciones.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=expediciones.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC2 Detalles Hist�rico Liquidaciones: Deber�a haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC2 Detalles Hist�rico Liquidaciones: Resultado obtenido="+(hayMsgError && analisis));
		expediciones.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	@Ignore
	public void testDetallesHistoricoLiquidaciones3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Detalles Hist�rico Liquidaciones");
		logger.info("TC3 Detalles Hist�rico Liquidaciones: Comprobaci�n del contenido de la tabla contra BD");
		logger.info("TC3 Detalles Hist�rico Liquidaciones: Resultado esperado=true");
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
		
		botest.bo.seleccionarOpcionMenu("Gesti�n de cobrador","Hist�rico de expediciones");
		HistoricoExpedicionesProcess expediciones=new HistoricoExpedicionesProcess();
		expediciones.clicarCheckDesdeHasta();
		//expediciones.seleccionarOpcionConcesionaria(concesionaria);
		expediciones.seleccionarOpcionPlaza(plaza);
		expediciones.actualizar();
		expediciones.recargarTabla();
		int numfila=1;
		expediciones.seleccionarFila(numfila);
		expediciones.detalles();
		DetallesHistoricoLiquidacionesProcess detalles=new DetallesHistoricoLiquidacionesProcess();
		
		// Se valida que la query de la BD est� contenida en la Tabla
		boolean iguales=true;
		try {  // Falta calcular el valor
			String queryString = "select time,banktour,operator,vanplate,employees,comment"
					+ " from dbo.abanktour where tollcompany='"+codigoconcesionaria+"' and plaza='"+codigoplaza+"' order by time desc";
			Statement stmt = LoginTest.DBconnectionPlaza.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(queryString);
			int tama�o=RutinasTestComunes.obtenerTama�oConsulta(rs);
			if (tama�o!=detalles.numFilasTotalTabla()) {
				logger.error("TC3 Detalles Hist�rico Liquidaciones: El n�mero de registros de la b�squeda es " +tama�o+" y no coincide con el de la tabla: "+detalles.numFilasTabla());
				LoginTest.critical++;
				iguales=false;
			}
			while (rs.next() && iguales) {
				iguales=detalles.buscarRegistroBDEnTabla(rs);
			}
			if (!iguales) {
				LoginTest.critical++;
			}
		}
		catch (Exception e) {
			logger.fatal("TC3 Detalles Hist�rico Liquidaciones: Error al acceder a la BD");
			LoginTest.critical++;
			e.printStackTrace();
			fail();
		}
		
		// Se valida que el contenido de la Tabla est� en BD
		detalles.pulsarBotonTablaPrimero();
		boolean iguales2=true;
		do {
			detalles.recargarTabla();
			for (int num_fila=1; (num_fila<=detalles.numFilasTabla()) && iguales2; num_fila++) {
				String elemento=detalles.obtenerValorColumna(num_fila, "Expedici�n");
				String queryString = "select time,banktour,operator,vanplate,employees,comment"
						+ " from dbo.abanktour where tollcompany='"+codigoconcesionaria+"' and plaza='"+codigoplaza+"' and banktour='"+elemento+"'";
				try {
					Statement stmt = LoginTest.DBconnectionPlaza.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs = stmt.executeQuery(queryString);
					int tama�o=RutinasTestComunes.obtenerTama�oConsulta(rs);
					if (tama�o!=1) {
						logger.error("TC3 Detalles Hist�rico Liquidaciones: El n�mero de registros de la b�squeda es " +tama�o+" y deber�a ser 1");
						LoginTest.critical++;
						iguales2=false;
					}
					else {
						rs.next();
						iguales2=detalles.buscarRegistroTablaEnBD(rs, elemento);
						if (!iguales2) {
							logger.error("TC3 Detalles Hist�rico Liquidaciones: El registro de la tabla " +elemento+" no coincide con el de la BD.");
							LoginTest.majors++;
						}
					}
				}
				catch (Exception e) {
					logger.fatal("TC3 Detalles Hist�rico Liquidaciones: Error al acceder a la BD");
					LoginTest.critical++;
					e.printStackTrace();
					fail();
				}
			}
			if (!detalles.ultimaTabla()) {
				detalles.pulsarBotonTablaSiguiente();
			}
		}
		while (!detalles.ultimaTabla() && iguales2);
		logger.info("TC3 Detalles Hist�rico Liquidaciones: Resultado obtenido="+(iguales && iguales2));
		detalles.volver();
		expediciones.volver();
		assertTrue(iguales && iguales2);
	}
}
