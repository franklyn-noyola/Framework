package testCasesITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.NodosProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.AlertPopup;
import unidadesGraficas.ErrorMessage;

public class NodosTest {

	final  String titulo="Gesti�n de Nodos de plaza"; 
	final  String subtitulo="Configuraci�n sistema   -   Configuraci�n de peaje   -   Nodos"; 
	final  String[] botones= {"Informe","Enviar a v�as","Volver"};
	final  String[] botonescrud= {"Crear","Modificar","Editar Nodo V�a","Eliminar","Activar","Desactivar"};
	final  String[] columnasgrid= {"C�digo","Direcci�n","Descripci�n","Activo"};
	final  String[] botonesgrid={"Primera","<",">","�ltima"};
	final  String msg_error_noseleccionado="Ning�n elemento seleccionado";
	final  String msg_activacion="Nodo activado";
	final  String msg_desactivacion="Nodo desactivado";
	final  String msg_eliminacion_nodo_con_plazas="El Nodo Plaza est� referenciado por alguna Plaza y no puede ser borrado";
	final  String msg_eliminacion_nodo_con_nodosvia="El Nodo Plaza contiene Nodos V�a y no puede ser eliminado";
	final  String alerta_confirmacion_borrado="�Est� seguro de que desea eliminar el nodo?";
	final  String alerta_confirmacion_desactivacion="�Est� seguro de que desea desactivar el nodo?\nEsto puede causar p�rdida de datos.";
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(NodosTest.class);
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
	public void testNodos01() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Nodos");
		logger.info("TC1 Nodos: Probar el an�lisis sint�ctico correcto");
		logger.info("TC1 Nodos: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		boolean analisis=nodos.sintacticAnalysis(titulo,subtitulo, botones,null,botonescrud,columnasgrid,botonesgrid);
		if (analisis) {
			logger.info("TC1 Nodos: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC1 Nodos: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Nodos: Resultado obtenido="+analisis);
		nodos.volver();
		assertTrue(analisis);
	}
	
	@Test
	public void testNodos02() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Nodos");
		logger.info("TC2 Nodos: Probar la eliminaci�n incorrecta sin seleccionar nodo");
		logger.info("TC2 Nodos: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		nodos.eliminar();
		boolean analisis=false;
		boolean hayMsgError=nodos.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=nodos.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC2 Nodos: Deber�a haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC2 Nodos: Resultado obtenido="+(hayMsgError && analisis));
		nodos.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	
	@Test
	public void testNodos03() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Nodos");
		logger.info("TC3 Nodos: Probar la eliminaci�n correcta de un nodo cancelando el borrado");
		logger.info("TC3 Nodos: Resultado esperado: true (aparece alerta de confirmaci�n)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigo="0002";
		int num_fila=nodos.obtenerNumFilaTabla("C�digo", codigo);
		boolean analisis=false;
		boolean hayAlerta=false;
		boolean encontradoTabla2=false;
		boolean encontradoTabla1=num_fila>=1 && num_fila<=nodos.numFilasTabla();
		if (!encontradoTabla1) {
			logger.error("TC3 Nodos: El elemento seleccionado en la tabla no existe: "+codigo);
			LoginTest.critical++;
		}
		else {
			nodos.seleccionarFila(num_fila);
			nodos.eliminar();
			hayAlerta=nodos.hayAlerta();
			if (hayAlerta) {
				AlertPopup alerta=nodos.alerta();
				analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
				if (!analisis) {
					LoginTest.minors++;
				}
				nodos.cancelarAlerta();
				nodos.recargarTabla();
				encontradoTabla2=(nodos.obtenerFilaTabla("C�digo", codigo)!=null);
				if (!encontradoTabla2) {
					logger.error("TC3 Nodos: Deber�a haber aparecido el nodo borrado "+codigo+" en la tabla");
					LoginTest.majors++;
				}
			}
		}
		logger.info("TC3 Nodos: Resultado obtenido: "+(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2));
		nodos.volver();
		assertTrue(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2);
	}


	@Test
	public void testNodos04() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Nodos");
		logger.info("TC4 Nodos: Probar la activaci�n incorrecta sin seleccionar nodo");
		logger.info("TC4 Nodos: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		nodos.activar();
		boolean analisis=false;
		boolean hayMsgError=nodos.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=nodos.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC4 Nodos: Deber�a haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC4 Nodos: Resultado obtenido="+(hayMsgError && analisis));
		nodos.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	@Test
	public void testNodos05() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Nodos");
		logger.info("TC5 Nodos: Probar la desactivaci�n incorrecta sin seleccionar nodo");
		logger.info("TC5 Nodos: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		nodos.desactivar();
		boolean analisis=false;
		boolean hayMsgError=nodos.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=nodos.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC5 Nodos: Deber�a haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC5 Nodos: Resultado obtenido="+(hayMsgError && analisis));
		nodos.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	
	@Test
	public void testNodos06() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6 Nodos");
		logger.info("TC6 Nodos: Probar la desactivaci�n correcta de un nodo");
		logger.info("TC6 Nodos: Resultado esperado: true (aparece alerta de confirmaci�n y la tabla indica la desactivaci�n)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		int numfila=1;
		nodos.seleccionarFila(numfila);
		String codigo=nodos.obtenerValorColumna(numfila, "C�digo");
		nodos.desactivar();
		boolean analisis=false;
		boolean hayAlerta=nodos.hayAlerta();
		if (hayAlerta) {
			AlertPopup alerta=nodos.alerta();
			analisis=alerta.sintacticAnalysis(alerta_confirmacion_desactivacion);
			if (!analisis) {
				LoginTest.minors++;
			}
			nodos.aceptarAlerta();
		}
		else {
			logger.error("TC6 Nodos: No ha aparecido la alerta de confirmaci�n");
			LoginTest.minors++;
		}
		String activo=nodos.obtenerValorColumna("Activo", "C�digo", codigo);
		boolean resultado=activo.equals("No");
		boolean hayMsgError=nodos.hayMensajeError();
		boolean analisis2=false;
		if (hayMsgError) {
			ErrorMessage errormsgobt=nodos.mensajeError();
			analisis2=errormsgobt.sintacticAnalysis(msg_desactivacion);
			if (!analisis2) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC6 Nodos: Deber�a haber aparecido el mensaje de desactivaci�n '"+msg_desactivacion+"'");
			LoginTest.minors++;
		}
		if (!resultado) {
			logger.error("TC6 Nodos: No aparece desactivado en la tabla");
			LoginTest.majors++;
		}
		logger.info("TC6 Nodos: Resultado obtenido: "+(hayAlerta && analisis && analisis2 && resultado));
		nodos.volver();
		assertTrue(hayAlerta && analisis && analisis2 && resultado);
	}

	@Test
	public void testNodos07() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC7 Nodos");
		logger.info("TC7 Nodos: Probar la activaci�n correcta de un nodo");
		logger.info("TC7 Nodos: Resultado esperado: true (la tabla indica la activaci�n)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		int numfila=1;
		nodos.seleccionarFila(numfila);
		String codigo=nodos.obtenerValorColumna(numfila, "C�digo");
		nodos.activar();
		String activo=nodos.obtenerValorColumna("Activo", "C�digo", codigo);
		boolean hayMsgError=nodos.hayMensajeError();
		boolean analisis=false;
		if (hayMsgError) {
			ErrorMessage errormsgobt=nodos.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_activacion);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC7 Nodos: Deber�a haber aparecido el mensaje de activaci�n '"+msg_activacion+"'");
			LoginTest.minors++;
		}
		boolean resultado=activo.equals("S�");
		if (!resultado) {
			logger.error("TC6 Nodos: No aparece activado en la tabla");
			LoginTest.majors++;
		}
		logger.info("TC7 Nodos: Resultado obtenido: "+(hayMsgError && analisis && resultado));
		nodos.volver();
		assertTrue((hayMsgError && analisis && resultado));
	}
	
	@Test
	public void testNodos08() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC8 Nodos");
		logger.info("TC8 Nodos: Probar la eliminaci�n incorrecta de un nodo que tiene plazas asociadas");
		logger.info("TC8 Nodos: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigo="0097";
		int num_fila=nodos.obtenerNumFilaTabla("C�digo", codigo);
		nodos.seleccionarFila(num_fila);
		nodos.eliminar();
		boolean analisis=false;
		boolean analisis2=false;
		boolean hayAlerta=nodos.hayAlerta();
		boolean hayMsgError=false;
		if (hayAlerta) {
			AlertPopup alerta=nodos.alerta();
			analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
			if (!analisis) {
				LoginTest.minors++;
			}
			nodos.aceptarAlerta();
			hayMsgError=nodos.hayMensajeError();
			if (hayMsgError) {
				ErrorMessage errormsgobt=nodos.mensajeError();
				analisis2=errormsgobt.sintacticAnalysis(msg_eliminacion_nodo_con_plazas);
				if (!analisis) {
					LoginTest.minors++;
				}
			}
			else {
				logger.error("TC8 Nodos: Deber�a haber aparecido el mensaje de error '"+msg_eliminacion_nodo_con_plazas+"'");
				LoginTest.minors++;
			}
		}
		logger.info("TC8 Nodos: Resultado obtenido: "+(hayAlerta && analisis && hayMsgError && analisis2));
		nodos.volver();
		assertTrue(hayAlerta && analisis && hayMsgError && analisis2);
	}
	
	@Test
	public void testNodos09() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC9 Nodos");
		logger.info("TC9 Nodos: Probar la eliminaci�n correcta de un nodo plaza sin nodos via ni plazas asociadas, aceptando el borrado");
		logger.info("TC9 Nodos: Resultado esperado: true (aparece alerta de confirmaci�n)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigo="3000";
		int num_fila=nodos.obtenerNumFilaTabla("C�digo", codigo);
		boolean analisis=false;
		boolean hayAlerta=false;
		boolean encontradoTabla2=false;
		boolean hayMsgError=false;
		boolean encontradoTabla1=num_fila>=1 && num_fila<=nodos.numFilasTabla();
		if (!encontradoTabla1) {
			logger.error("TC9 Nodos: El elemento seleccionado en la tabla no existe: "+codigo);
			LoginTest.critical++;
		}
		else {
			nodos.seleccionarFila(num_fila);
			nodos.eliminar();
			hayAlerta=nodos.hayAlerta();
			if (hayAlerta) {
				AlertPopup alerta=nodos.alerta();
				analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
				if (!analisis) {
					LoginTest.minors++;
				}
				nodos.aceptarAlerta();
				hayMsgError=nodos.hayMensajeError();
				if (hayMsgError) {
					ErrorMessage errormsgobt=nodos.mensajeError();
					logger.error("TC9 Nodos: No deber�a haber aparecido el mensaje de error '"+errormsgobt.getText()+"'");
					LoginTest.majors++;
				}
				nodos.recargarTabla();
				encontradoTabla2=(nodos.obtenerFilaTabla("C�digo", codigo)!=null);
				if (encontradoTabla2) {
					logger.error("TC9 Nodos: No deber�a haber aparecido el nodo borrado "+codigo+" en la tabla");
					LoginTest.majors++;
				}
			}
		}
		logger.info("TC9 Nodos: Resultado obtenido: "+(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2 && !hayMsgError));
		nodos.volver();
		assertTrue(hayAlerta && analisis && encontradoTabla1 && !encontradoTabla2 && !hayMsgError);
	}
	
	@Test
	public void testNodos10() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC10 Nodos");
		logger.info("TC10 Nodos: Comprobaci�n del contenido de la tabla contra BD");
		logger.info("TC10 Nodos: Resultado esperado=true");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		
		// Se valida que la query de la BD est� contenida en la Tabla
		boolean iguales=true;
		try {
			String queryString = "select plazanode,ipaddress,description,managed from dbo.anode where lanenode='LOCALSERV'";
			Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(queryString);
			int tama�o=RutinasTestComunes.obtenerTama�oConsulta(rs);
			if (tama�o!=nodos.numFilasTotalTabla()) {
				logger.error("TC10 Nodos: El n�mero de registros de la b�squeda es " +tama�o+" y no coincide con el de la tabla: "+nodos.numFilasTabla());
				LoginTest.critical++;
				iguales=false;
			}
			while (rs.next() && iguales) {
				iguales=nodos.buscarRegistroBDEnTabla(rs);
			}
			if (!iguales) {
				LoginTest.critical++;
			}
		}
		catch (Exception e) {
			logger.fatal("TC10 Nodos: Error al acceder a la BD (1)");
			LoginTest.critical++;
			e.printStackTrace();
			fail();
		}
		
		// Se valida que el contenido de la Tabla est� en BD
		nodos.pulsarBotonTablaPrimero();
		boolean iguales2=true;
		do {
			nodos.recargarTabla();
			for (int num_fila=1; (num_fila<=nodos.numFilasTabla()) && iguales2; num_fila++) {
				String elemento=nodos.obtenerValorColumna(num_fila, "C�digo");
				String queryString = "select plazanode,ipaddress,description,managed from dbo.anode where lanenode='LOCALSERV' and plazanode='"+elemento+"'";
				try {
					Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs = stmt.executeQuery(queryString);
					rs.next();
					iguales2=nodos.buscarRegistroTablaEnBD(rs, elemento);
					if (!iguales2) {
						logger.error("TC10 Nodos: El registro de la tabla ("+elemento+") no coincide con el de la BD.");
						LoginTest.majors++;
					}
				}
				catch (Exception e) {
					logger.fatal("TC10 Nodos: Error al acceder a la BD");
					LoginTest.critical++;
					e.printStackTrace();
					fail();
				}
			}
			if (!nodos.ultimaTabla()) {
				nodos.pulsarBotonTablaSiguiente();
			}
		}
		while (!nodos.ultimaTabla() && iguales2);
		logger.info("TC10 Nodos: Resultado obtenido="+(iguales && iguales2));
		assertTrue(iguales && iguales2);
	}
	
	@Test
	public void testNodos11() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC11 Nodos");
		logger.info("TC11 Nodos: Probar la eliminaci�n incorrecta de un nodo que tiene nodos v�a asociados");
		logger.info("TC11 Nodos: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigo="3003";
		int numfila=nodos.obtenerNumFilaTabla("C�digo", codigo);
		nodos.seleccionarFila(numfila);
		nodos.eliminar();
		boolean analisis=false;
		boolean analisis2=false;
		boolean hayAlerta=nodos.hayAlerta();
		boolean hayMsgError=false;
		if (hayAlerta) {
			AlertPopup alerta=nodos.alerta();
			analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
			if (!analisis) {
				LoginTest.minors++;
			}
			nodos.aceptarAlerta();
			hayMsgError=nodos.hayMensajeError();
			if (hayMsgError) {
				ErrorMessage errormsgobt=nodos.mensajeError();
				analisis2=errormsgobt.sintacticAnalysis(msg_eliminacion_nodo_con_nodosvia);
				if (!analisis) {
					LoginTest.minors++;
				}
			}
			else {
				logger.error("TC11 Nodos: Deber�a haber aparecido el mensaje de error '"+msg_eliminacion_nodo_con_nodosvia+"'");
				LoginTest.minors++;
			}
		}
		logger.info("TC11 Nodos: Resultado obtenido: "+(hayAlerta && analisis && hayMsgError && analisis2));
		nodos.volver();
		assertTrue(hayAlerta && analisis && hayMsgError && analisis2);
	}
}
