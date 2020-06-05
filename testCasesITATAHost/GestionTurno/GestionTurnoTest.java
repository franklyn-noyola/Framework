package testCasesITATAHost.GestionTurno;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.GestionTurno.GestionTurnoProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.ErrorMessage;


public class GestionTurnoTest {

	final  String titulo="Gestión de turno"; 
	final  String subtitulo="Gestión de turno   -   Gestión de turno"; 
	final  String[] botones={"Informe","Búsqueda","Volver"};  
	final  String[] labels= {"Operador","Nº turno","Concesionaria","Plaza","Estado"};
	final String[][] labels_rango= {{"Fecha","Desde","Hasta"}};
	final  String[] columnasgrid= {"Plaza","Nº Turno","Hora fin de turno","Oper.","Estado","Revisor","Teórico",
									"Liquidado","Recontado","Final","Dif","Carta"};
	final  String[] botonescrud= {"Ver/Editar liquidaciones","Validar","Borrar validación","Validar todos",
									"Informe de Turno","Informe de Ingreso"};
	final  String[] botonesgrid= {"Primera","<",">","Última"};
	final  String msg_error_noseleccionado="Ningún turno seleccionado";
	static InitBOTest botest;
	static Connection DBconnection;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(GestionTurnoTest.class);
		logger.debug("setUpBeforeClass-GestionTurnoTest");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		logger.debug("tearDownAfterClass-GestionTurnoTest");
		InitBOTest.tearDownAfterClass();
	}
	
	@After
	public void AfterTest() throws Exception {
		logger.info("Fin TC");
		logger.info("-----------------------------------------------------------------");
	}
	
	@Test
	public void testGestionTurno1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Gestión Turno");
		logger.info("TC1 Gestión Turno: Probar un análisis sintáctico correcto");
		logger.info("TC1 GestionTurno: Resultado esperado=true (análisis sintáctico correcto)");
		botest.bo.seleccionarOpcionMenu("Gestión de turno","Gestión de turno");
		GestionTurnoProcess turnos=new GestionTurnoProcess();
		
		boolean analisis=turnos.sintacticAnalysis(titulo, subtitulo, botones, botonescrud, labels, labels_rango,
				columnasgrid, botonesgrid);
		if (analisis) {
			logger.info("TC1 Gestión Turno: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Gestión Turno: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Gestion Turno: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}
	
	@Test
	public void testGestionTurno2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Gestión Turno");
		logger.info("TC2 Gestión Turno: Probar editar las liquidaciones de un turno sin seleccionarlo");
		logger.info("TC2 Gestión Turno: Resultado esperado=true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Gestión de turno","Gestión de turno");
		GestionTurnoProcess turnos=new GestionTurnoProcess();

		turnos.verEditarLiquidaciones();
		boolean analisis=false;
		boolean hayMsgError=turnos.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=turnos.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC2 Gestión Turno: Debería haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC2 Gestión Turno: Resultado obtenido="+(hayMsgError && analisis));
		turnos.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	@Test
	public void testGestionTurno3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Gestión Turno");
		logger.info("TC3 Gestión Turno: Probar validar un turno sin seleccionarlo");
		logger.info("TC3 Gestión Turno: Resultado esperado=true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Gestión de turno","Gestión de turno");
		GestionTurnoProcess turnos=new GestionTurnoProcess();

		turnos.validar();
		boolean analisis=false;
		boolean hayMsgError=turnos.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=turnos.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC3 Gestión Turno: Debería haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC3 Gestión Turno: Resultado obtenido="+(hayMsgError && analisis));
		turnos.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	@Test
	public void testGestionTurno4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Gestión Turno");
		logger.info("TC4 Gestión Turno: Probar obtener el informe de un turno sin seleccionarlo");
		logger.info("TC4 Gestión Turno: Resultado esperado=true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Gestión de turno","Gestión de turno");
		GestionTurnoProcess turnos=new GestionTurnoProcess();

		turnos.informeTurno();
		boolean analisis=false;
		boolean hayMsgError=turnos.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=turnos.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC4 Gestión Turno: Debería haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC4 Gestión Turno: Resultado obtenido="+(hayMsgError && analisis));
		turnos.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	@Test
	public void testGestionTurno5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Gestión Turno");
		logger.info("TC5 Gestión Turno: Probar obtener el informe de ingreso de un turno sin seleccionarlo");
		logger.info("TC5 Gestión Turno: Resultado esperado=true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Gestión de turno","Gestión de turno");
		GestionTurnoProcess turnos=new GestionTurnoProcess();

		turnos.informeIngreso();
		boolean analisis=false;
		boolean hayMsgError=turnos.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=turnos.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC5 Gestión Turno: Debería haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC5 Gestión Turno: Resultado obtenido="+(hayMsgError && analisis));
		turnos.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	@Test
	public void testGestionTurno6() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6 Gestión Turno");
		logger.info("TC6 Gestión Turno: Probar la validación del formato del campo Operador");
		logger.info("TC6 Gestión Turno: Resultado esperado=true (aparece el tooltip)");
		botest.bo.seleccionarOpcionMenu("Gestión de turno","Gestión de turno");
		GestionTurnoProcess turnos=new GestionTurnoProcess();

		turnos.escribirOpcionOperador("a");
		boolean hayMsgError=turnos.hayErrorValidacionOperador();
		if (!hayMsgError){
			LoginTest.minors++;
			logger.error("TC6 Gestión Turno: Debería haber aparecido el tooltip");
		}
		logger.info("TC6 Gestión Turno: resultado obtenido="+hayMsgError);
		turnos.volver();
		assertTrue(hayMsgError);
	}
	
	@Test
	public void testGestionTurno7() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC7 Gestión Turno");
		logger.info("TC7 Gestión Turno: Probar la validación del formato del campo Nº Turno");
		logger.info("TC7 Gestión Turno: Resultado esperado=true (aparece el tooltip)");
		botest.bo.seleccionarOpcionMenu("Gestión de turno","Gestión de turno");
		GestionTurnoProcess turnos=new GestionTurnoProcess();

		turnos.escribirOpcionNumTurno("a");
		boolean hayMsgError=turnos.hayErrorValidacionNumTurno();
		if (!hayMsgError){
			LoginTest.minors++;
			logger.error("TC7 Gestión Turno: Debería haber aparecido el tooltip");
		}
		logger.info("TC7 Gestión Turno: resultado obtenido="+hayMsgError);
		turnos.volver();
		assertTrue(hayMsgError);
	}
	
	@Test
	public void testGestionTurno8() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC8 Gestión Turno");
		logger.info("TC8 Gestión Turno: Probar la validación de formato del campo Desde-Hasta");
		logger.info("TC8 Gestión Turno: Resultado esperado=true (aparecen los tooltips correspondientes)");
		botest.bo.seleccionarOpcionMenu("Gestión de turno","Gestión de turno");
		GestionTurnoProcess turnos=new GestionTurnoProcess();

		if (!turnos.estaCheckActivado()) {
			turnos.clicarCheckDesdeHasta();
		}
		turnos.escribirFechaDesde("a");
		boolean hayMsgError=turnos.hayErrorValidacionFormatoFechaDesde();
		if (!hayMsgError) {
			logger.error("TC8 Gestión Turno: Debería haber aparecido el tooltip de error de formato por fecha en Desde");
			LoginTest.minors++;
		}
		turnos.escribirFechaHasta("a");
		boolean hayMsgError2=turnos.hayErrorValidacionFormatoFechaHasta();
		if (!hayMsgError2) {
			logger.error("TC8 Gestión Turno: Debería haber aparecido el tooltip de error de formato por fecha en Hasta");
			LoginTest.minors++;
		}
		turnos.escribirFechaDesde("01/01/2018");
		turnos.escribirFechaHasta("01/01/2018");
		turnos.escribirHoraDesde("a");
		boolean hayMsgError3=turnos.hayErrorValidacionFormatoHoraDesde();
		if (!hayMsgError3) {
			logger.error("TC8 Gestión Turno: Debería haber aparecido el tooltip de error de formato por hora en Desde");
			LoginTest.minors++;
		}
		turnos.escribirHoraHasta("a");
		boolean hayMsgError4=turnos.hayErrorValidacionFormatoHoraHasta();
		if (!hayMsgError4) {
			logger.error("TC8 Gestión Turno: Debería haber aparecido el tooltip de error de formato por hora en Hasta");
			LoginTest.minors++;
		}
		logger.info("TC8 Gestión Turno: Resultado obtenido="+(hayMsgError && hayMsgError2 && hayMsgError3 && hayMsgError4));
		turnos.volver();
		assertTrue(hayMsgError && hayMsgError2 && hayMsgError3 && hayMsgError4);
	}
	
	@Test
	public void testGestionTurno9() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC8 Gestión Turno");
		logger.info("TC8 Gestión Turno: Probar la validación de requerido del campo Desde-Hasta");
		logger.info("TC8 Gestión Turno: Resultado esperado=true (aparecen los tooltips correspondientes)");
		botest.bo.seleccionarOpcionMenu("Gestión de turno","Gestión de turno");
		GestionTurnoProcess turnos=new GestionTurnoProcess();
		if (!turnos.estaCheckActivado()) {
			turnos.clicarCheckDesdeHasta();
		}
		turnos.borrarFechaDesde();
		boolean hayMsgError=turnos.hayErrorValidacionRequeridoFechaDesde();
		if (!hayMsgError) {
			logger.error("TC8 Gestión Turno: Debería haber aparecido el tooltip de error de requerido por fecha en Desde");
			LoginTest.minors++;
		}
		turnos.borrarFechaHasta();
		boolean hayMsgError2=turnos.hayErrorValidacionRequeridoFechaHasta();
		if (!hayMsgError2) {
			logger.error("TC8 Gestión Turno: Debería haber aparecido el tooltip de error de requerido por fecha en Hasta");
			LoginTest.minors++;
		}
		turnos.escribirFechaDesde("01/01/2018");
		turnos.escribirFechaHasta("01/01/2018");
		
		turnos.borrarHoraDesde();
		boolean hayMsgError3=turnos.hayErrorValidacionRequeridoHoraDesde();
		if (!hayMsgError3) {
			logger.error("TC8 Gestión Turno: Debería haber aparecido el tooltip de error de requerido por hora en Desde");
			LoginTest.minors++;
		}
		turnos.borrarHoraHasta();
		boolean hayMsgError4=turnos.hayErrorValidacionRequeridoHoraHasta();
		if (!hayMsgError4) {
			logger.error("TC8 Gestión Turno: Debería haber aparecido el tooltip de error de requerido por hora en Hasta");
			LoginTest.minors++;
		}
		logger.info("TC8 Gestión Turno: Resultado obtenido="+(hayMsgError && hayMsgError2 && hayMsgError3 && hayMsgError4));
		turnos.volver();
		assertTrue(hayMsgError && hayMsgError2 && hayMsgError3 && hayMsgError4);
	}
	
	/*
	@Test
	public void testGestionTurno3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 GestionTurno");
		logger.info("TC3 GestionTurno: Comprobación del contenido de la tabla contra BD");
		logger.info("TC3 GestionTurno: Resultado esperado=true");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String concesionaria="Autopista del Itata";
		String plaza="Plaza Itata";
		String codigoconcesionaria=concesionarias.obtenerValorColumna("ID", "Nombre", concesionaria);
		int indice=concesionarias.obtenerNumFilaTabla("ID", codigoconcesionaria);
		concesionarias.seleccionarFila(indice);
		concesionarias.editarPlazas();
		GestionPlazaProcess nodoplaza=new GestionPlazaProcess();
		String codigoplaza=nodoplaza.obtenerValorColumna("Código", "Nombre", plaza);
		
		botest.bo.seleccionarOpcionMenu("Gestión de cobrador","Histórico de expediciones");
		HistoricoExpedicionesProcess expediciones=new HistoricoExpedicionesProcess();
		expediciones.clicarCheckDesdeHasta();
		expediciones.seleccionarOpcionConcesionaria(concesionaria);
		expediciones.seleccionarOpcionPlaza(plaza);
		expediciones.actualizar();
		expediciones.recargarTabla();
		
		// Se valida que la query de la BD está contenida en la Tabla
		boolean iguales=true;
		try {  // Falta calcular el valor
			String queryString = "select time,banktour,operator,vanplate,employees,comment"
					+ " from dbo.abanktour where tollcompany='"+codigoconcesionaria+"' and plaza='"+codigoplaza+"' order by time desc";
			Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(queryString);
			int tamaño=RutinasTestComunes.obtenerTamañoConsulta(rs);
			if (tamaño!=expediciones.numFilasTotalTabla()) {
				logger.error("TC3 GestionTurno: El número de registros de la búsqueda es " +tamaño+" y no coincide con el de la tabla: "+expediciones.numFilasTabla());
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
			logger.fatal("TC3 GestionTurno: Error al acceder a la BD");
			LoginTest.critical++;
			e.printStackTrace();
			fail();
		}
		
		// Se valida que el contenido de la Tabla está en BD
		expediciones.pulsarBotonTablaPrimero();
		boolean iguales2=true;
		do {
			expediciones.recargarTabla();
			for (int num_fila=1; (num_fila<=expediciones.numFilasTabla()) && iguales2; num_fila++) {
				String elemento=expediciones.obtenerValorColumna(num_fila, "Expedición");
				String queryString = "select time,banktour,operator,vanplate,employees,comment"
						+ " from dbo.abanktour where tollcompany='"+codigoconcesionaria+"' and plaza='"+codigoplaza+"' and banktour='"+elemento+"'";
				try {
					Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs = stmt.executeQuery(queryString);
					int tamaño=RutinasTestComunes.obtenerTamañoConsulta(rs);
					if (tamaño!=1) {
						logger.error("TC3 GestionTurno: El número de registros de la búsqueda es " +tamaño+" y debería ser 1");
						LoginTest.critical++;
						iguales2=false;
					}
					else {
						rs.next();
						iguales2=expediciones.buscarRegistroTablaEnBD(rs, elemento);
						if (!iguales2) {
							logger.error("TC3 GestionTurno: El registro de la tabla " +elemento+" no coincide con el de la BD.");
							LoginTest.majors++;
						}
					}
				}
				catch (Exception e) {
					logger.fatal("TC3 GestionTurno: Error al acceder a la BD");
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
		logger.info("TC3 GestionTurno: Resultado obtenido="+(iguales && iguales2));
		assertTrue(iguales && iguales2);
	}
	*/
}
