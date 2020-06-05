package testCasesITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.ConcesionariasProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.GestionPlazaProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.AlertPopup;
import unidadesGraficas.ErrorMessage;

public class GestionPlazaTest {

	final static String titulo="Gesti�n de Plaza"; 
	final static String subtitulo="Configuraci�n sistema   -   Configuraci�n de peaje   -   Concesionarias, Plazas, V�as   -   Editar Plazas"; 
	final static String[] botones={"Volver"};
	final static String encabezado1="Concesionaria";
	final static String[] labelsVariables={"ID","Nombre"};
	final static String encabezado2="Plazas";
	final static String[] botonescrud={"Crear","Modificar","Editar V�as","Eliminar"};
	final static String[] columnasgrid={"C�digo","Nombre","Nodo Plaza","Carretera","Km"};
	final static String[] botonesgrid={"Primera","<",">","�ltima"};
	final static String msg_error_noseleccionado="Ning�n elemento seleccionado";
	final static String msg_error_plaza_con_vias="La plaza tiene v�as y no puede ser eliminada";
	final static String alerta_confirmacion_borrado="�Est� seguro de que desea eliminar la plaza?";
	static InitBOTest botest;
	private static Logger logger;
	 
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(GestionPlazaTest.class);
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
	public void testGestionPlaza1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Gesti�n Plaza");
		logger.info("TC1 Gesti�n Plaza: Probar el an�lisis sint�ctico correcto");
		logger.info("TC1 Gesti�n Plaza: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Concesionarias, Plazas, V�as");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		int numfila=1;
		concesionarias.seleccionarFila(numfila);
		String[] valoresVariables=new String[labelsVariables.length];
		for (int i=0;i<labelsVariables.length;i++) {
			valoresVariables[i]=concesionarias.obtenerValorColumna(numfila, labelsVariables[i]);
		}
		concesionarias.editarPlazas();
		GestionPlazaProcess nodoplaza=new GestionPlazaProcess();
		boolean analisis=nodoplaza.sintacticAnalysis(titulo,subtitulo, botones,encabezado1,labelsVariables,valoresVariables,encabezado2,botonescrud,columnasgrid,botonesgrid);
		if (analisis) {
			logger.info("TC1 Gesti�n Plaza: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC1 Gesti�n Plaza: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Gesti�n Plaza: Resultado obtenido="+analisis);
		nodoplaza.volver();
		assertTrue(analisis);
	}
	
	@Test
	public void testGestionPlaza2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Gesti�n Plaza");
		logger.info("TC2 Gesti�n Plaza: Probar la eliminaci�n incorrecta sin seleccionar plaza");
		logger.info("TC2 Gesti�n Plaza: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Concesionarias, Plazas, V�as");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		concesionarias.seleccionarFila(2);
		concesionarias.editarPlazas();
		GestionPlazaProcess nodoplaza=new GestionPlazaProcess();
		nodoplaza.eliminar();
		boolean analisis=false;
		boolean hayMsgError=nodoplaza.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=nodoplaza.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC2 Gesti�n Plaza: Deber�a haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC2 Gesti�n Plaza: Resultado obtenido="+(hayMsgError && analisis));
		nodoplaza.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	
	@Test
	public void testGestionPlaza3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Gesti�n Plaza");
		logger.info("TC3 Gesti�n Plaza: Probar la eliminaci�n correcta de una plaza cancelando el borrado");
		logger.info("TC3 Gesti�n Plaza: Resultado esperado: true (aparece alerta de confirmaci�n)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Concesionarias, Plazas, V�as");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		int numfilaconcesionaria=concesionarias.obtenerNumFilaTabla("ID","00");
		concesionarias.seleccionarFila(numfilaconcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess nodoplaza=new GestionPlazaProcess();
		String plaza="99";
		int num_fila=nodoplaza.obtenerNumFilaTabla("C�digo",plaza);
		boolean analisis=false;
		boolean hayAlerta=false;
		boolean encontradoTabla2=false;
		boolean encontradoTabla1=num_fila>=1 && num_fila<=nodoplaza.numFilasTabla();
		if (!encontradoTabla1) {
			logger.error("Error: el elemento seleccionado en la tabla no existe: ("+plaza+")");
			LoginTest.critical++;
		}
		else {
			nodoplaza.seleccionarFila(num_fila);
			nodoplaza.eliminar();
			hayAlerta=nodoplaza.hayAlerta();
			if (hayAlerta) {
				AlertPopup alerta=nodoplaza.alerta();
				analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
				if (!analisis) {
					LoginTest.minors++;
				}
				nodoplaza.cancelarAlerta();
				nodoplaza.recargarTabla();
				encontradoTabla2=(nodoplaza.obtenerFilaTabla("C�digo",plaza)!=null);
				if (!encontradoTabla2) {
					logger.error("TC3: Deber�a haber aparecido la plaza borrada ("+plaza+") en la tabla");
					LoginTest.majors++;
				}
			}
		}
		logger.info("TC3 Gesti�n Plaza: Resultado obtenido: "+(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2));
		nodoplaza.volver();
		assertTrue(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2);
	}

	@Test
	public void testGestionPlaza4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Gesti�n Plaza");
		logger.info("TC4 Gesti�n Plaza: Probar la eliminaci�n incorrecta de una plaza con v�as asociadas");
		logger.info("TC4 Gesti�n Plaza: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Concesionarias, Plazas, V�as");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		int numfilaconcesionaria=concesionarias.obtenerNumFilaTabla("ID","01");
		concesionarias.seleccionarFila(numfilaconcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess nodoplaza=new GestionPlazaProcess();
		nodoplaza.seleccionarFila(1);
		nodoplaza.eliminar();
		boolean analisis=false;
		boolean analisis2=false;
		boolean hayAlerta=nodoplaza.hayAlerta();
		if (hayAlerta) {
			AlertPopup alerta=nodoplaza.alerta();
			analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
			if (!analisis) {
				LoginTest.minors++;
			}
			nodoplaza.aceptarAlerta();
		}
		boolean hayMsgError=nodoplaza.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=nodoplaza.mensajeError();
			analisis2=errormsgobt.sintacticAnalysis(msg_error_plaza_con_vias);
			if (!analisis2) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC4 Gesti�n Plaza: Deber�a haber aparecido el mensaje de error '"+msg_error_plaza_con_vias+"'");
			LoginTest.minors++;
		}
		logger.info("TC4 Gesti�n Plaza: Resultado obtenido="+(hayAlerta && analisis && analisis2 && hayMsgError));
		nodoplaza.volver();
		assertTrue(hayAlerta && analisis && analisis2 && hayMsgError);
	}
	
	@Test
	public void testGestionPlaza5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Gesti�n Plaza");
		logger.info("TC5 Gesti�n Plaza: Probar la eliminaci�n correcta de una plaza aceptando el borrado");
		logger.info("TC5 Gesti�n Plaza: Resultado esperado: true (aparece alerta de confirmaci�n)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Concesionarias, Plazas, V�as");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		int numfilaconcesionaria=concesionarias.obtenerNumFilaTabla("ID","00");
		concesionarias.seleccionarFila(numfilaconcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess nodoplaza=new GestionPlazaProcess();
		String plaza="99";
		int num_fila=nodoplaza.obtenerNumFilaTabla("C�digo",plaza);
		boolean analisis=false;
		boolean hayAlerta=false;
		boolean encontradoTabla2=false;
		boolean hayMsgError=false;
		boolean encontradoTabla1=num_fila>=1 && num_fila<=nodoplaza.numFilasTabla();
		if (!encontradoTabla1) {
			logger.error("Error: el elemento seleccionado en la tabla no existe: ("+plaza+")");
			LoginTest.critical++;
		}
		else {
			nodoplaza.seleccionarFila(num_fila);
			nodoplaza.eliminar();
			hayAlerta=nodoplaza.hayAlerta();
			if (hayAlerta) {
				AlertPopup alerta=nodoplaza.alerta();
				analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
				if (!analisis) {
					LoginTest.minors++;
				}
				nodoplaza.aceptarAlerta();
				hayMsgError=nodoplaza.hayMensajeError();
				if (hayMsgError) {
					ErrorMessage errormsgobt=nodoplaza.mensajeError();
					logger.error("TC5: No deber�a haber aparecido el mensaje de error '"+errormsgobt.getText()+"'");
					LoginTest.majors++;
				}
				nodoplaza.recargarTabla();
				encontradoTabla2=(nodoplaza.obtenerFilaTabla("C�digo",plaza)!=null);
				if (encontradoTabla2) {
					logger.error("TC5: Deber�a haber aparecido la plaza borrada ("+plaza+") en la tabla");
					LoginTest.majors++;
				}
			}
		}
		logger.info("TC5 Gesti�n Plaza: Resultado obtenido: "+(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2 && !hayMsgError));
		nodoplaza.volver();
		concesionarias.volver();
		assertTrue(hayAlerta && analisis && encontradoTabla1 && !encontradoTabla2 && !hayMsgError);
	}
	
	@Test
	public void testGestionPlaza6() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6 Gesti�n Plaza");
		logger.info("TC6 Gesti�n Plaza: Comprobaci�n del contenido de la tabla contra BD");
		logger.info("TC6 Gesti�n Plaza: Resultado esperado=true");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Concesionarias, Plazas, V�as");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String concesionaria="01";
		int numfilaconcesionaria=concesionarias.obtenerNumFilaTabla("ID",concesionaria);
		concesionarias.seleccionarFila(numfilaconcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess nodoplaza=new GestionPlazaProcess();
		
		// Se valida que la query de la BD est� contenida en la Tabla
		boolean iguales=true;
		try {
			String queryString = "select plaza,name,plazanode,road,km from dbo.aplaza where tollcompany='"+concesionaria+"'";
			Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(queryString);
			int tama�o=RutinasTestComunes.obtenerTama�oConsulta(rs);
			if (tama�o!=nodoplaza.numFilasTotalTabla()) {
				logger.error("TC6 Gesti�n Plaza: El n�mero de registros de la b�squeda es " +tama�o+" y no coincide con el de la tabla: "+nodoplaza.numFilasTabla());
				LoginTest.critical++;
				iguales=false;
			}
			while (rs.next() && iguales) {
				iguales=nodoplaza.buscarRegistroBDEnTabla(rs);
			}
			if (!iguales) {
				LoginTest.critical++;
			}
		}
		catch (Exception e) {
			logger.fatal("TC6 Gesti�n Plaza: Error al acceder a la BD (1)");
			LoginTest.critical++;
			e.printStackTrace();
			fail();
		}
		
		// Se valida que el contenido de la Tabla est� en BD
		nodoplaza.pulsarBotonTablaPrimero();
		boolean iguales2=true;
		do {
			nodoplaza.recargarTabla();
			for (int num_fila=1; (num_fila<=nodoplaza.numFilasTabla()) && iguales2; num_fila++) {
				String elemento=nodoplaza.obtenerValorColumna(num_fila, "C�digo");
				String queryString = "select plaza,name,plazanode,road,km from dbo.aplaza where tollcompany='"+concesionaria+"' and plaza='"+elemento+"'";
				try {
					Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs = stmt.executeQuery(queryString);
					rs.next();
					iguales2=nodoplaza.buscarRegistroTablaEnBD(rs, elemento);
					if (!iguales2) {
						logger.error("TC10 Gesti�n Plaza: El registro de la tabla ("+elemento+") no coincide con el de la BD.");
						LoginTest.majors++;
					}
				}
				catch (Exception e) {
					logger.fatal("TC6 Gesti�n Plaza: Error al acceder a la BD");
					LoginTest.critical++;
					e.printStackTrace();
					fail();
				}
			}
			if (!nodoplaza.ultimaTabla()) {
				nodoplaza.pulsarBotonTablaSiguiente();
			}
		}
		while (!nodoplaza.ultimaTabla() && iguales2);
		logger.info("TC6 Gesti�n Plaza: Resultado obtenido="+(iguales && iguales2));
		assertTrue(iguales && iguales2);
	}
}
