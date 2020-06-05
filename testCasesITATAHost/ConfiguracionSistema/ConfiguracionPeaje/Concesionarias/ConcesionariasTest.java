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
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.AlertPopup;
import unidadesGraficas.ErrorMessage;

public class ConcesionariasTest {

	final static String titulo="Gesti�n Concesionaria"; 
	final static String subtitulo="Configuraci�n sistema   -   Configuraci�n de peaje   -   Concesionarias, Plazas, V�as"; 
	final static String[] botones= {"Informe","Enviar a v�as","Volver"};
	final static String[] botonescrud= {"Crear","Modificar","Editar Plazas","Eliminar"};
	final static String[] columnasgrid= {"ID","Nombre","N�mero fiscal"};
	final static String[] botonesgrid={"Primera","<",">","�ltima"};
	final static String msg_error_noseleccionado="Ning�n elemento seleccionado";
	final static String alerta_confirmacion_borrado="�Est� seguro de que desea eliminar la concesionaria?";
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(ConcesionariasTest.class);
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
	public void testConcesionarias1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Concesionarias");
		logger.info("TC1 Concesionarias: Probar el an�lisis sint�ctico correcto");
		logger.info("TC1 Concesionarias: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Concesionarias, Plazas, V�as");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		boolean analisis=concesionarias.sintacticAnalysis(titulo,subtitulo, botones,null,botonescrud,columnasgrid,botonesgrid);
		if (analisis) {
			logger.info("TC1 Concesionarias: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC1 Concesionarias: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Concesionarias: Resultado obtenido="+analisis);
		concesionarias.volver();
		assertTrue(analisis);
	}
	
	@Test
	public void testConcesionarias2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Concesionarias");
		logger.info("TC2 Concesionarias: Probar la eliminaci�n incorrecta sin seleccionar concesionaria");
		logger.info("TC2 Concesionarias: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Concesionarias, Plazas, V�as");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		concesionarias.eliminar();
		boolean analisis=false;
		boolean hayMsgError=concesionarias.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=concesionarias.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC2 Concesionarias: Deber�a haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC2 Concesionarias: Resultado obtenido="+(hayMsgError && analisis));
		concesionarias.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	
	@Test
	public void testConcesionarias3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Concesionarias");
		logger.info("TC3 Concesionarias: Probar la eliminaci�n correcta de una concesionaria cancelando el borrado");
		logger.info("TC3 Concesionarias: Resultado esperado: true (aparece alerta de confirmaci�n)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Concesionarias, Plazas, V�as");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String concesionaria="99";
		int num_fila=concesionarias.obtenerNumFilaTabla("ID",concesionaria);
		boolean analisis=false;
		boolean hayAlerta=false;
		boolean encontradoTabla2=false;
		boolean encontradoTabla1=num_fila>=1 && num_fila<=concesionarias.numFilasTabla();
		if (!encontradoTabla1) {
			logger.error("Error: el elemento seleccionado en la tabla no existe: ("+concesionaria+")");
			LoginTest.critical++;
		}
		else {
			concesionarias.seleccionarFila(num_fila);
			concesionarias.eliminar();
			hayAlerta=concesionarias.hayAlerta();
			if (hayAlerta) {
				AlertPopup alerta=concesionarias.alerta();
				analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
				if (!analisis) {
					LoginTest.minors++;
				}
				concesionarias.cancelarAlerta();
				concesionarias.recargarTabla();
				encontradoTabla2=(concesionarias.obtenerFilaTabla("ID",concesionaria)!=null);
				if (!encontradoTabla2) {
					logger.error("TC3 Concesionarias: Deber�a haber aparecido la concesionaria borrada ("+concesionaria+") en la tabla");
					LoginTest.majors++;
				}
			}
		}
		logger.info("TC3 Concesionarias: Resultado obtenido: "+(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2));
		concesionarias.volver();
		assertTrue(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2);
	}

	@Test
	public void testConcesionarias4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Concesionarias");
		logger.info("TC4 Concesionarias: Comprobaci�n del contenido de la tabla contra BD");
		logger.info("TC4 Concesionarias: Resultado esperado=true");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Concesionarias, Plazas, V�as");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		
		// Se valida que la query de la BD est� contenida en la Tabla
		boolean iguales=true;
		try {
			String queryString = "select tollcompany,name,VATREGNUM from dbo.atollcompany order by tollcompany ASC";
			Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(queryString);
			int tama�o=RutinasTestComunes.obtenerTama�oConsulta(rs);
			if (tama�o!=concesionarias.numFilasTotalTabla()) {
				logger.error("TC4 Concesionarias: El n�mero de registros de la b�squeda es " +tama�o+" y no coincide con el de la tabla: "+concesionarias.numFilasTabla());
				LoginTest.critical++;
				iguales=false;
			}
			while (rs.next() && iguales) {
				iguales=concesionarias.buscarRegistroBDEnTabla(rs);
			}
			if (!iguales) {
				LoginTest.critical++;
			}
		}
		catch (Exception e) {
			logger.fatal("TC4 Concesionarias: Error al acceder a la BD");
			LoginTest.critical++;
			e.printStackTrace();
			fail();
		}
		
		// Se valida que el contenido de la Tabla est� en BD
		concesionarias.pulsarBotonTablaPrimero();
		boolean iguales2=true;
		do {
			concesionarias.recargarTabla();
			for (int num_fila=1; (num_fila<=concesionarias.numFilasTabla()) && iguales2; num_fila++) {
				String elemento=concesionarias.obtenerValorColumna(num_fila, "ID");
				String queryString = "select tollcompany,name,VATREGNUM from dbo.atollcompany where tollcompany="+elemento;
				try {
					Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs = stmt.executeQuery(queryString);
					rs.next();
					iguales2=concesionarias.buscarRegistroTablaEnBD(rs, elemento);
					if (!iguales2) {
						logger.error("TC4 Concesionarias: El registro de la tabla " +elemento+" no coincide con el de la BD.");
						LoginTest.majors++;
					}
				}
				catch (Exception e) {
					logger.fatal("TC4 Concesionarias: Error al acceder a la BD");
					LoginTest.critical++;
					e.printStackTrace();
					fail();
				}
			}
			if (!concesionarias.ultimaTabla()) {
				concesionarias.pulsarBotonTablaSiguiente();
			}
		}
		while (!concesionarias.ultimaTabla() && iguales2);
		logger.info("TC4 Concesionarias: Resultado obtenido="+(iguales && iguales2));
		assertTrue(iguales && iguales2);
	}
	
	@Test
	public void testConcesionarias5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Concesionarias");
		logger.info("TC5 Concesionarias: Probar la eliminaci�n correcta de una concesionaria aceptando el borrado");
		logger.info("TC5 Concesionarias: Resultado esperado: true (aparece alerta de confirmaci�n)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Concesionarias, Plazas, V�as");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String concesionaria="99";
		int num_fila=concesionarias.obtenerNumFilaTabla("ID",concesionaria);
		boolean analisis=false;
		boolean hayAlerta=false;
		boolean encontradoTabla2=false;
		boolean hayMsgError=false;
		boolean encontradoTabla1=num_fila>=1 && num_fila<=concesionarias.numFilasTabla();
		if (!encontradoTabla1) {
			logger.error("Error: el elemento seleccionado en la tabla no existe: ("+concesionaria+")");
			LoginTest.critical++;
		}
		else {
			concesionarias.seleccionarFila(num_fila);
			concesionarias.eliminar();
			hayAlerta=concesionarias.hayAlerta();
			if (hayAlerta) {
				AlertPopup alerta=concesionarias.alerta();
				analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
				if (!analisis) {
					LoginTest.minors++;
				}
				concesionarias.aceptarAlerta();
				hayMsgError=concesionarias.hayMensajeError();
				if (hayMsgError) {
					ErrorMessage errormsgobt=concesionarias.mensajeError();
					logger.error("TC5: No deber�a haber aparecido el mensaje de error '"+errormsgobt.getText()+"'");
					LoginTest.majors++;
				}
				concesionarias.recargarTabla();
				encontradoTabla2=(concesionarias.obtenerFilaTabla("ID",concesionaria)!=null);
				if (encontradoTabla2) {
					logger.error("TC3: Deber�a haber aparecido la categor�a borrada ("+concesionaria+") en la tabla");
					LoginTest.majors++;
				}
			}
		}
		logger.info("TC5 Categorias: Resultado obtenido: "+(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2 && !hayMsgError));
		concesionarias.volver();
		assertTrue(hayAlerta && analisis && encontradoTabla1 && !encontradoTabla2 && !hayMsgError);
	}
}
