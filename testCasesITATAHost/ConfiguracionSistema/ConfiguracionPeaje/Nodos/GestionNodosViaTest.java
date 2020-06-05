package testCasesITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.GestionNodosViaProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.NodosProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.AlertPopup;
import unidadesGraficas.ErrorMessage;

public class GestionNodosViaTest {

	final static String titulo="Gesti�n de Nodos de v�a"; 
	final static String subtitulo="Configuraci�n sistema   -   Configuraci�n de peaje   -   Nodos   -   Editar Nodo V�a"; 
	final static String[] botones={"Volver"};
	final static String encabezado1="Nodo Plaza";
	final static String[] labelsVariables={"C�digo","Descripci�n","Direcci�n"};
	final static String encabezado2="Nodos V�a";
	final static String[] botonescrud={"Crear","Modificar","Eliminar","Activar","Desactivar"};
	final static String[] columnasgrid={"C�digo","Direcci�n","�ltimo sincronismo","Activo"};
	final static String[] botonesgrid={"Primera","<",">","�ltima"};
	final static String msg_error_noseleccionado="Ning�n elemento seleccionado";
	final static String msg_activacion="Nodo activado";
	final static String msg_desactivacion="Nodo desactivado";
	final static String msg_eliminacion_nodo_con_vias="El nodo de v�a est� referenciado por v�as y no puede ser borrado";
	final static String alerta_confirmacion_borrado="�Est� seguro de que desea eliminar el nodo?";
	final static String alerta_confirmacion_desactivacion="�Est� seguro de que desea desactivar el nodo?\nEsto puede causar p�rdida de datos.";
	static InitBOTest botest;
	private static Logger logger;
	 
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(GestionNodosViaTest.class);
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
	public void testGestionNodosVia01() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Gesti�n Nodos V�a");
		logger.info("TC1 Gesti�n Nodos V�a: Probar el an�lisis sint�ctico correcto");
		logger.info("TC1 Gesti�n Nodos V�a: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		int numfila=1;
		nodos.seleccionarFila(numfila);
		String[] valoresVariables=new String[labelsVariables.length];
		for (int i=0;i<labelsVariables.length;i++) {
			valoresVariables[i]=nodos.obtenerValorColumna(numfila, labelsVariables[i]);
		}
		nodos.editarNodoVia();
		GestionNodosViaProcess nodosvia=new GestionNodosViaProcess();
		boolean analisis=nodosvia.sintacticAnalysis(titulo,subtitulo, botones,encabezado1,labelsVariables,valoresVariables,encabezado2,botonescrud,columnasgrid,botonesgrid);
		if (analisis) {
			logger.info("TC1 Gesti�n Nodos V�a: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC1 Gesti�n Nodos V�a: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Gesti�n Nodos V�a: Resultado obtenido="+analisis);
		nodosvia.volver();
		assertTrue(analisis);
	}
	
	@Test
	public void testGestionNodosVia02() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Gesti�n Nodos V�a");
		logger.info("TC2 Gesti�n Nodos V�a: Probar la eliminaci�n incorrecta sin seleccionar nodo de v�a");
		logger.info("TC2 Gesti�n Nodos V�a: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigo="3003";
		int numfila=nodos.obtenerNumFilaTabla("C�digo", codigo);
		nodos.seleccionarFila(numfila);
		nodos.editarNodoVia();
		GestionNodosViaProcess nodosvia=new GestionNodosViaProcess();
		nodosvia.eliminar();
		boolean analisis=false;
		boolean hayMsgError=nodosvia.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=nodosvia.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC2 Gesti�n Nodos V�a: Deber�a haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC2 Gesti�n Nodos V�a: Resultado obtenido="+(hayMsgError && analisis));
		nodosvia.volver();
		assertTrue(hayMsgError && analisis);
	}


	@Test
	public void testGestionNodosVia03() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Gesti�n Nodos V�a");
		logger.info("TC3 Gesti�n Nodos V�a");
		logger.info("TC3 Gesti�n Nodos V�a: Probar la eliminaci�n correcta de un nodo de v�a cancelando el borrado");
		logger.info("TC3 Gesti�n Nodos V�a: Resultado esperado: true (aparece alerta de confirmaci�n)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodoplaza=new NodosProcess();
		String codigoplaza="3003";
		int num_filaplaza=nodoplaza.obtenerNumFilaTabla("C�digo", codigoplaza);
		nodoplaza.seleccionarFila(num_filaplaza);
		nodoplaza.editarNodoVia();
		GestionNodosViaProcess nodos=new GestionNodosViaProcess();
		String codigovia="LNC014000";
		int num_fila=nodos.obtenerNumFilaTabla("C�digo", codigovia);
		boolean analisis=false;
		boolean hayAlerta=false;
		boolean encontradoTabla2=false;
		boolean encontradoTabla1=num_fila>=1 && num_fila<=nodos.numFilasTabla();
		if (!encontradoTabla1) {
			logger.error("TC3 Gesti�n Nodos V�a: El elemento seleccionado en la tabla no existe: "+codigovia);
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
				encontradoTabla2=(nodos.obtenerFilaTabla("C�digo", codigovia)!=null);
				if (!encontradoTabla2) {
					logger.error("TC3 Gesti�n Nodos V�a: Deber�a haber aparecido el nodo v�a borrado "+codigovia+" en la tabla");
					LoginTest.majors++;
				}
			}
		}
		logger.info("TC3 Gesti�n Nodos V�a: Resultado obtenido: "+(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2));
		nodos.volver();
		nodoplaza.volver();
		assertTrue(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2);
	}

	
	@Test
	public void testGestionNodosVia04() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Gesti�n Nodos V�a");
		logger.info("TC4 Gesti�n Nodos V�a: Probar la activaci�n incorrecta sin seleccionar nodo de v�a");
		logger.info("TC4 Gesti�n Nodos V�a: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigo="3003";
		int numfila=nodos.obtenerNumFilaTabla("C�digo", codigo);
		nodos.seleccionarFila(numfila);
		nodos.editarNodoVia();
		GestionNodosViaProcess nodosvia=new GestionNodosViaProcess();
		nodosvia.activar();
		boolean analisis=false;
		boolean hayMsgError=nodosvia.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=nodos.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC4 Gesti�n Nodos V�a: Deber�a haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC4 Gesti�n Nodos V�a: Resultado obtenido="+(hayMsgError && analisis));
		nodosvia.volver();
		nodos.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	@Test
	public void testGestionNodosVia05() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Gesti�n Nodos V�a");
		logger.info("TC5 Gesti�n Nodos V�a: Probar la desactivaci�n incorrecta sin seleccionar nodo de v�a");
		logger.info("TC5 Gesti�n Nodos V�a: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigo="3003";
		int numfila=nodos.obtenerNumFilaTabla("C�digo", codigo);
		nodos.seleccionarFila(numfila);
		nodos.editarNodoVia();
		GestionNodosViaProcess nodosvia=new GestionNodosViaProcess();
		nodosvia.desactivar();
		boolean analisis=false;
		boolean hayMsgError=nodosvia.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=nodos.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC5 Gesti�n Nodos V�a: Deber�a haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC5 Gesti�n Nodos V�a: Resultado obtenido="+(hayMsgError && analisis));
		nodosvia.volver();
		nodos.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	
	@Test
	public void testGestionNodosVia06() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6 Gesti�n Nodos V�a");
		logger.info("TC6 Gesti�n Nodos V�a: Probar la desactivaci�n correcta de un nodo de v�a");
		logger.info("TC6 Gesti�n Nodos V�a: Resultado esperado: true (aparece alerta de confirmaci�n y la tabla indica la desactivaci�n)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigo="3003";
		int numfila=nodos.obtenerNumFilaTabla("C�digo", codigo);
		nodos.seleccionarFila(numfila);
		nodos.editarNodoVia();
		GestionNodosViaProcess nodosvia=new GestionNodosViaProcess();
		String codigovia="LNC014000";
		numfila=nodosvia.obtenerNumFilaTabla("C�digo", codigovia);
		nodosvia.seleccionarFila(numfila);
		nodosvia.desactivar();
		boolean analisis=false;
		boolean hayAlerta=nodosvia.hayAlerta();
		if (hayAlerta) {
			AlertPopup alerta=nodosvia.alerta();
			analisis=alerta.sintacticAnalysis(alerta_confirmacion_desactivacion);
			if (!analisis) {
				LoginTest.minors++;
			}
			nodosvia.aceptarAlerta();
		}
		else {
			logger.error("TC6 Gesti�n Nodos V�a: No ha aparecido la alerta de confirmaci�n");
			LoginTest.minors++;
		}
		String activo=nodosvia.obtenerValorColumna("Activo", "C�digo", codigovia);
		boolean resultado=activo.equals("No");
		boolean hayMsgError=nodosvia.hayMensajeError();
		boolean analisis2=false;
		if (hayMsgError) {
			ErrorMessage errormsgobt=nodosvia.mensajeError();
			analisis2=errormsgobt.sintacticAnalysis(msg_desactivacion);
			if (!analisis2) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC6 Gesti�n Nodos V�a: Deber�a haber aparecido el mensaje de desactivaci�n '"+msg_desactivacion+"'");
			LoginTest.minors++;
		}
		if (!resultado) {
			logger.error("TC6 Gesti�n Nodos V�a: No aparece desactivado en la tabla");
			LoginTest.majors++;
		}
		logger.info("TC6 Nodos: Resultado obtenido: "+(hayAlerta && analisis && analisis2 && resultado));
		nodosvia.volver();
		nodos.volver();
		assertTrue(hayAlerta && analisis && analisis2 && resultado);
	}

	@Test
	public void testGestionNodosVia07() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC7 Gesti�n Nodos V�a");
		logger.info("TC7 Gesti�n Nodos V�a: Probar la activaci�n correcta de un nodo de v�a");
		logger.info("TC7 Gesti�n Nodos V�a: Resultado esperado: true (la tabla indica la activaci�n)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigo="3003";
		int numfila=nodos.obtenerNumFilaTabla("C�digo", codigo);
		nodos.seleccionarFila(numfila);
		nodos.editarNodoVia();
		GestionNodosViaProcess nodosvia=new GestionNodosViaProcess();
		String codigovia="LNC014000";
		numfila=nodosvia.obtenerNumFilaTabla("C�digo", codigovia);
		nodosvia.seleccionarFila(numfila);
		nodosvia.activar();
		String activo=nodosvia.obtenerValorColumna("Activo", "C�digo", codigovia);
		boolean hayMsgError=nodosvia.hayMensajeError();
		boolean analisis=false;
		if (hayMsgError) {
			ErrorMessage errormsgobt=nodosvia.mensajeError();
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
			logger.error("TC7 Nodos: No aparece activado en la tabla");
			LoginTest.majors++;
		}
		logger.info("TC7 Gesti�n Nodos V�a: Resultado obtenido: "+(hayMsgError && analisis && resultado));
		nodosvia.volver();
		nodos.volver();
		assertTrue((hayMsgError && analisis && resultado));
	}
	
	@Test
	public void testGestionNodosVia08() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC8 Gesti�n Nodos V�a");
		logger.info("TC8 Gesti�n Nodos V�a: Probar la eliminaci�n incorrecta de un nodo de v�a que tiene v�as asociadas");
		logger.info("TC8 Gesti�n Nodos V�a: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigo="0101";
		int numfila=nodos.obtenerNumFilaTabla("C�digo", codigo);
		nodos.seleccionarFila(numfila);
		nodos.editarNodoVia();
		GestionNodosViaProcess nodosvia=new GestionNodosViaProcess();
		String codigovia="LNC010101";
		numfila=nodosvia.obtenerNumFilaTabla("C�digo", codigovia);
		nodos.seleccionarFila(numfila);
		nodosvia.eliminar();
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
				analisis2=errormsgobt.sintacticAnalysis(msg_eliminacion_nodo_con_vias);
				if (!analisis2) {
					LoginTest.minors++;
				}
			}
			else {
				logger.error("TC8 Nodos: Deber�a haber aparecido el mensaje de error '"+msg_eliminacion_nodo_con_vias+"'");
				LoginTest.minors++;
			}
		}
		logger.info("TC8 Gesti�n Nodos V�a: Resultado obtenido="+(hayMsgError && analisis && analisis2 && hayAlerta));
		nodosvia.volver();
		assertTrue(hayMsgError && analisis && analisis2 && hayAlerta);
	}
	
	@Test
	public void testGestionNodosVia09() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC9 Gesti�n Nodos V�a");
		logger.info("TC9 Gesti�n Nodos V�a: Probar la eliminaci�n correcta de un nodo via que no tiene vias asociadas aceptando el borrado");
		logger.info("TC9 Gesti�n Nodos V�a: Resultado esperado: true (aparece alerta de confirmaci�n)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodoplaza=new NodosProcess();
		String codigoplaza="0002";
		int num_filaplaza=nodoplaza.obtenerNumFilaTabla("C�digo", codigoplaza);
		nodoplaza.seleccionarFila(num_filaplaza);
		nodoplaza.editarNodoVia();
		GestionNodosViaProcess nodos=new GestionNodosViaProcess();
		String codigovia="LNC001122";
		int num_fila=nodos.obtenerNumFilaTabla("C�digo", codigovia);
		boolean analisis=false;
		boolean hayAlerta=false;
		boolean encontradoTabla2=false;
		boolean hayMsgError=false;
		boolean encontradoTabla1=num_fila>=1 && num_fila<=nodos.numFilasTabla();
		if (!encontradoTabla1) {
			logger.error("TC9 Gesti�n Nodos V�a: El elemento seleccionado en la tabla no existe: "+codigovia);
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
					logger.error("TC9 Gesti�n Nodos V�a: No deber�a haber aparecido el mensaje de error '"+errormsgobt.getText()+"'");
					LoginTest.majors++;
				}
				nodos.recargarTabla();
				encontradoTabla2=(nodos.obtenerFilaTabla("C�digo", codigovia)!=null);
				if (encontradoTabla2) {
					logger.error("TC9 Gesti�n Nodos V�a: No deber�a haber aparecido el nodo borrado "+codigovia+" en la tabla");
					LoginTest.majors++;
				}
			}
		}
		logger.info("TC9 Gesti�n Nodos V�a: Resultado obtenido: "+(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2 && !hayMsgError));
		nodos.volver();
		assertTrue(hayAlerta && analisis && encontradoTabla1 && !encontradoTabla2 && !hayMsgError);
	}
	
	@Test
	public void testGestionNodosVia10() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC10 Gesti�n Nodos V�a");
		logger.info("TC10 Gesti�n Nodos V�a: Comprobaci�n del contenido de la tabla contra BD");
		logger.info("TC10 Gesti�n Nodos V�a: Resultado esperado=true");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Nodos");
		NodosProcess nodoplaza=new NodosProcess();
		String codigoplaza="0101";
		int num_filaplaza=nodoplaza.obtenerNumFilaTabla("C�digo", codigoplaza);
		nodoplaza.seleccionarFila(num_filaplaza);
		nodoplaza.editarNodoVia();
		GestionNodosViaProcess nodos=new GestionNodosViaProcess();
		
		// Se valida que la query de la BD est� contenida en la Tabla
		boolean iguales=true;
		try {
			String queryString = "select anode.lanenode,ipaddress,amsgseq.onlinetime,managed "
					+ "from dbo.anode, dbo.amsgseq where anode.plazanode=amsgseq.plazanode and "
					+ "anode.lanenode=amsgseq.lanenode and amsgseq.msgseq='1' and anode.plazanode='"+codigoplaza+"' and anode.lanenode<>'LOCALSERV'";
			Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(queryString);
			int tama�o=RutinasTestComunes.obtenerTama�oConsulta(rs);
			if (tama�o!=nodos.numFilasTotalTabla()) {
				logger.error("TC10 Gesti�n Nodos V�a: El n�mero de registros de la b�squeda es " +tama�o+" y no coincide con el de la tabla: "+nodos.numFilasTabla());
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
			logger.fatal("TC10 Gesti�n Nodos V�a: Error al acceder a la BD (1)");
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
				String queryString = "select anode.lanenode,ipaddress,amsgseq.onlinetime,managed "
						+ "from dbo.anode, dbo.amsgseq where anode.plazanode=amsgseq.plazanode and "
						+ "anode.lanenode=amsgseq.lanenode and amsgseq.msgseq='1' and anode.plazanode='"+codigoplaza+"' and anode.lanenode='"+elemento+"'";
				try {
					Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs = stmt.executeQuery(queryString);
					rs.next();
					iguales2=nodos.buscarRegistroTablaEnBD(rs, elemento);
					if (!iguales2) {
						logger.error("TC10 Gesti�n Nodos V�a: El registro de la tabla ("+elemento+") no coincide con el de la BD.");
						LoginTest.majors++;
					}
				}
				catch (Exception e) {
					logger.fatal("TC10 Gesti�n Nodos V�a: Error al acceder a la BD");
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
		logger.info("TC10 Gesti�n Nodos V�a: Resultado obtenido="+(iguales && iguales2));
		assertTrue(iguales && iguales2);
	}
}
