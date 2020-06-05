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

	final static String titulo="Gestión de Nodos de vía"; 
	final static String subtitulo="Configuración sistema   -   Configuración de peaje   -   Nodos   -   Editar Nodo Vía"; 
	final static String[] botones={"Volver"};
	final static String encabezado1="Nodo Plaza";
	final static String[] labelsVariables={"Código","Descripción","Dirección"};
	final static String encabezado2="Nodos Vía";
	final static String[] botonescrud={"Crear","Modificar","Eliminar","Activar","Desactivar"};
	final static String[] columnasgrid={"Código","Dirección","Último sincronismo","Activo"};
	final static String[] botonesgrid={"Primera","<",">","Última"};
	final static String msg_error_noseleccionado="Ningún elemento seleccionado";
	final static String msg_activacion="Nodo activado";
	final static String msg_desactivacion="Nodo desactivado";
	final static String msg_eliminacion_nodo_con_vias="El nodo de vía está referenciado por vías y no puede ser borrado";
	final static String alerta_confirmacion_borrado="¿Está seguro de que desea eliminar el nodo?";
	final static String alerta_confirmacion_desactivacion="¿Está seguro de que desea desactivar el nodo?\nEsto puede causar pérdida de datos.";
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
		logger.info("TC1 Gestión Nodos Vía");
		logger.info("TC1 Gestión Nodos Vía: Probar el análisis sintáctico correcto");
		logger.info("TC1 Gestión Nodos Vía: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
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
			logger.info("TC1 Gestión Nodos Vía: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Gestión Nodos Vía: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Gestión Nodos Vía: Resultado obtenido="+analisis);
		nodosvia.volver();
		assertTrue(analisis);
	}
	
	@Test
	public void testGestionNodosVia02() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Gestión Nodos Vía");
		logger.info("TC2 Gestión Nodos Vía: Probar la eliminación incorrecta sin seleccionar nodo de vía");
		logger.info("TC2 Gestión Nodos Vía: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigo="3003";
		int numfila=nodos.obtenerNumFilaTabla("Código", codigo);
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
			logger.error("TC2 Gestión Nodos Vía: Debería haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC2 Gestión Nodos Vía: Resultado obtenido="+(hayMsgError && analisis));
		nodosvia.volver();
		assertTrue(hayMsgError && analisis);
	}


	@Test
	public void testGestionNodosVia03() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Gestión Nodos Vía");
		logger.info("TC3 Gestión Nodos Vía");
		logger.info("TC3 Gestión Nodos Vía: Probar la eliminación correcta de un nodo de vía cancelando el borrado");
		logger.info("TC3 Gestión Nodos Vía: Resultado esperado: true (aparece alerta de confirmación)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodoplaza=new NodosProcess();
		String codigoplaza="3003";
		int num_filaplaza=nodoplaza.obtenerNumFilaTabla("Código", codigoplaza);
		nodoplaza.seleccionarFila(num_filaplaza);
		nodoplaza.editarNodoVia();
		GestionNodosViaProcess nodos=new GestionNodosViaProcess();
		String codigovia="LNC014000";
		int num_fila=nodos.obtenerNumFilaTabla("Código", codigovia);
		boolean analisis=false;
		boolean hayAlerta=false;
		boolean encontradoTabla2=false;
		boolean encontradoTabla1=num_fila>=1 && num_fila<=nodos.numFilasTabla();
		if (!encontradoTabla1) {
			logger.error("TC3 Gestión Nodos Vía: El elemento seleccionado en la tabla no existe: "+codigovia);
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
				encontradoTabla2=(nodos.obtenerFilaTabla("Código", codigovia)!=null);
				if (!encontradoTabla2) {
					logger.error("TC3 Gestión Nodos Vía: Debería haber aparecido el nodo vía borrado "+codigovia+" en la tabla");
					LoginTest.majors++;
				}
			}
		}
		logger.info("TC3 Gestión Nodos Vía: Resultado obtenido: "+(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2));
		nodos.volver();
		nodoplaza.volver();
		assertTrue(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2);
	}

	
	@Test
	public void testGestionNodosVia04() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Gestión Nodos Vía");
		logger.info("TC4 Gestión Nodos Vía: Probar la activación incorrecta sin seleccionar nodo de vía");
		logger.info("TC4 Gestión Nodos Vía: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigo="3003";
		int numfila=nodos.obtenerNumFilaTabla("Código", codigo);
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
			logger.error("TC4 Gestión Nodos Vía: Debería haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC4 Gestión Nodos Vía: Resultado obtenido="+(hayMsgError && analisis));
		nodosvia.volver();
		nodos.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	@Test
	public void testGestionNodosVia05() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Gestión Nodos Vía");
		logger.info("TC5 Gestión Nodos Vía: Probar la desactivación incorrecta sin seleccionar nodo de vía");
		logger.info("TC5 Gestión Nodos Vía: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigo="3003";
		int numfila=nodos.obtenerNumFilaTabla("Código", codigo);
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
			logger.error("TC5 Gestión Nodos Vía: Debería haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC5 Gestión Nodos Vía: Resultado obtenido="+(hayMsgError && analisis));
		nodosvia.volver();
		nodos.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	
	@Test
	public void testGestionNodosVia06() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6 Gestión Nodos Vía");
		logger.info("TC6 Gestión Nodos Vía: Probar la desactivación correcta de un nodo de vía");
		logger.info("TC6 Gestión Nodos Vía: Resultado esperado: true (aparece alerta de confirmación y la tabla indica la desactivación)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigo="3003";
		int numfila=nodos.obtenerNumFilaTabla("Código", codigo);
		nodos.seleccionarFila(numfila);
		nodos.editarNodoVia();
		GestionNodosViaProcess nodosvia=new GestionNodosViaProcess();
		String codigovia="LNC014000";
		numfila=nodosvia.obtenerNumFilaTabla("Código", codigovia);
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
			logger.error("TC6 Gestión Nodos Vía: No ha aparecido la alerta de confirmación");
			LoginTest.minors++;
		}
		String activo=nodosvia.obtenerValorColumna("Activo", "Código", codigovia);
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
			logger.error("TC6 Gestión Nodos Vía: Debería haber aparecido el mensaje de desactivación '"+msg_desactivacion+"'");
			LoginTest.minors++;
		}
		if (!resultado) {
			logger.error("TC6 Gestión Nodos Vía: No aparece desactivado en la tabla");
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
		logger.info("TC7 Gestión Nodos Vía");
		logger.info("TC7 Gestión Nodos Vía: Probar la activación correcta de un nodo de vía");
		logger.info("TC7 Gestión Nodos Vía: Resultado esperado: true (la tabla indica la activación)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigo="3003";
		int numfila=nodos.obtenerNumFilaTabla("Código", codigo);
		nodos.seleccionarFila(numfila);
		nodos.editarNodoVia();
		GestionNodosViaProcess nodosvia=new GestionNodosViaProcess();
		String codigovia="LNC014000";
		numfila=nodosvia.obtenerNumFilaTabla("Código", codigovia);
		nodosvia.seleccionarFila(numfila);
		nodosvia.activar();
		String activo=nodosvia.obtenerValorColumna("Activo", "Código", codigovia);
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
			logger.error("TC7 Nodos: Debería haber aparecido el mensaje de activación '"+msg_activacion+"'");
			LoginTest.minors++;
		}
		boolean resultado=activo.equals("Sí");
		if (!resultado) {
			logger.error("TC7 Nodos: No aparece activado en la tabla");
			LoginTest.majors++;
		}
		logger.info("TC7 Gestión Nodos Vía: Resultado obtenido: "+(hayMsgError && analisis && resultado));
		nodosvia.volver();
		nodos.volver();
		assertTrue((hayMsgError && analisis && resultado));
	}
	
	@Test
	public void testGestionNodosVia08() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC8 Gestión Nodos Vía");
		logger.info("TC8 Gestión Nodos Vía: Probar la eliminación incorrecta de un nodo de vía que tiene vías asociadas");
		logger.info("TC8 Gestión Nodos Vía: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String codigo="0101";
		int numfila=nodos.obtenerNumFilaTabla("Código", codigo);
		nodos.seleccionarFila(numfila);
		nodos.editarNodoVia();
		GestionNodosViaProcess nodosvia=new GestionNodosViaProcess();
		String codigovia="LNC010101";
		numfila=nodosvia.obtenerNumFilaTabla("Código", codigovia);
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
				logger.error("TC8 Nodos: Debería haber aparecido el mensaje de error '"+msg_eliminacion_nodo_con_vias+"'");
				LoginTest.minors++;
			}
		}
		logger.info("TC8 Gestión Nodos Vía: Resultado obtenido="+(hayMsgError && analisis && analisis2 && hayAlerta));
		nodosvia.volver();
		assertTrue(hayMsgError && analisis && analisis2 && hayAlerta);
	}
	
	@Test
	public void testGestionNodosVia09() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC9 Gestión Nodos Vía");
		logger.info("TC9 Gestión Nodos Vía: Probar la eliminación correcta de un nodo via que no tiene vias asociadas aceptando el borrado");
		logger.info("TC9 Gestión Nodos Vía: Resultado esperado: true (aparece alerta de confirmación)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodoplaza=new NodosProcess();
		String codigoplaza="0002";
		int num_filaplaza=nodoplaza.obtenerNumFilaTabla("Código", codigoplaza);
		nodoplaza.seleccionarFila(num_filaplaza);
		nodoplaza.editarNodoVia();
		GestionNodosViaProcess nodos=new GestionNodosViaProcess();
		String codigovia="LNC001122";
		int num_fila=nodos.obtenerNumFilaTabla("Código", codigovia);
		boolean analisis=false;
		boolean hayAlerta=false;
		boolean encontradoTabla2=false;
		boolean hayMsgError=false;
		boolean encontradoTabla1=num_fila>=1 && num_fila<=nodos.numFilasTabla();
		if (!encontradoTabla1) {
			logger.error("TC9 Gestión Nodos Vía: El elemento seleccionado en la tabla no existe: "+codigovia);
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
					logger.error("TC9 Gestión Nodos Vía: No debería haber aparecido el mensaje de error '"+errormsgobt.getText()+"'");
					LoginTest.majors++;
				}
				nodos.recargarTabla();
				encontradoTabla2=(nodos.obtenerFilaTabla("Código", codigovia)!=null);
				if (encontradoTabla2) {
					logger.error("TC9 Gestión Nodos Vía: No debería haber aparecido el nodo borrado "+codigovia+" en la tabla");
					LoginTest.majors++;
				}
			}
		}
		logger.info("TC9 Gestión Nodos Vía: Resultado obtenido: "+(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2 && !hayMsgError));
		nodos.volver();
		assertTrue(hayAlerta && analisis && encontradoTabla1 && !encontradoTabla2 && !hayMsgError);
	}
	
	@Test
	public void testGestionNodosVia10() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC10 Gestión Nodos Vía");
		logger.info("TC10 Gestión Nodos Vía: Comprobación del contenido de la tabla contra BD");
		logger.info("TC10 Gestión Nodos Vía: Resultado esperado=true");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodoplaza=new NodosProcess();
		String codigoplaza="0101";
		int num_filaplaza=nodoplaza.obtenerNumFilaTabla("Código", codigoplaza);
		nodoplaza.seleccionarFila(num_filaplaza);
		nodoplaza.editarNodoVia();
		GestionNodosViaProcess nodos=new GestionNodosViaProcess();
		
		// Se valida que la query de la BD está contenida en la Tabla
		boolean iguales=true;
		try {
			String queryString = "select anode.lanenode,ipaddress,amsgseq.onlinetime,managed "
					+ "from dbo.anode, dbo.amsgseq where anode.plazanode=amsgseq.plazanode and "
					+ "anode.lanenode=amsgseq.lanenode and amsgseq.msgseq='1' and anode.plazanode='"+codigoplaza+"' and anode.lanenode<>'LOCALSERV'";
			Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(queryString);
			int tamaño=RutinasTestComunes.obtenerTamañoConsulta(rs);
			if (tamaño!=nodos.numFilasTotalTabla()) {
				logger.error("TC10 Gestión Nodos Vía: El número de registros de la búsqueda es " +tamaño+" y no coincide con el de la tabla: "+nodos.numFilasTabla());
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
			logger.fatal("TC10 Gestión Nodos Vía: Error al acceder a la BD (1)");
			LoginTest.critical++;
			e.printStackTrace();
			fail();
		}
		
		// Se valida que el contenido de la Tabla está en BD
		nodos.pulsarBotonTablaPrimero();
		boolean iguales2=true;
		do {
			nodos.recargarTabla();
			for (int num_fila=1; (num_fila<=nodos.numFilasTabla()) && iguales2; num_fila++) {
				String elemento=nodos.obtenerValorColumna(num_fila, "Código");
				String queryString = "select anode.lanenode,ipaddress,amsgseq.onlinetime,managed "
						+ "from dbo.anode, dbo.amsgseq where anode.plazanode=amsgseq.plazanode and "
						+ "anode.lanenode=amsgseq.lanenode and amsgseq.msgseq='1' and anode.plazanode='"+codigoplaza+"' and anode.lanenode='"+elemento+"'";
				try {
					Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs = stmt.executeQuery(queryString);
					rs.next();
					iguales2=nodos.buscarRegistroTablaEnBD(rs, elemento);
					if (!iguales2) {
						logger.error("TC10 Gestión Nodos Vía: El registro de la tabla ("+elemento+") no coincide con el de la BD.");
						LoginTest.majors++;
					}
				}
				catch (Exception e) {
					logger.fatal("TC10 Gestión Nodos Vía: Error al acceder a la BD");
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
		logger.info("TC10 Gestión Nodos Vía: Resultado obtenido="+(iguales && iguales2));
		assertTrue(iguales && iguales2);
	}
}
