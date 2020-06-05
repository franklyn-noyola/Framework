package testCasesITATAHost.ConfiguracionSistema.GestionClases.MapeoDACcat;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.GestionClases.MapeoDACcat.MapeoDACcatProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.AlertPopup;
import unidadesGraficas.ErrorMessage;

public class MapeoDACcatTest {

	final static String titulo="Gestión de Mapeo DAC-cat"; 
	final static String subtitulo="Configuración sistema   -   Gestión de clases   -   Mapeo DAC-Cat"; 
	final static String[] botones= {"Informe","Enviar a vías","Editar orden","Volver"};
	final static String[] botonescrud= {"Crear","Modificar","Eliminar","Subir","Bajar"};
	final static String[] columnasgrid= {"Índice","Concesionaria","Min Rueda doble","Max Rueda doble","Bus",
			"Num. Mí. Ejes","Num. Máx. Ejes","Distancia Mín. 1º y 2º eje","Distancia Máx. 1º y 2º eje","Remolques","Altura mínima","Altura máxima",
			"Anchura mínima","Anchura máxima","Categoría"};
	final static String[] botonesgrid={"Primera","<",">","Última"};
	final static String msg_error_noseleccionado="Ningún elemento seleccionado";
	final static String alerta_confirmacion_borrado="¿Está seguro de que desea eliminar el mapeo?";
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(MapeoDACcatTest.class);
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
	public void testMapeos1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Mapeos");
		logger.info("TC1 Mapeos: Probar el análisis sintáctico correcto");
		logger.info("TC1 Mapeos: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		boolean analisis=mapeos.sintacticAnalysis(titulo,subtitulo, botones,null,botonescrud,columnasgrid,botonesgrid);
		if (analisis) {
			logger.info("TC1 Mapeos: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Mapeos: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Mapeos: Resultado obtenido="+analisis);
		mapeos.volver();
		assertTrue(analisis);
	}
	
	@Test
	public void testMapeos2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Mapeos");
		logger.info("TC2 Mapeos: Probar la eliminación incorrecta sin seleccionar mapeo");
		logger.info("TC2 Mapeos: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		mapeos.eliminar();
		boolean analisis=false;
		boolean hayMsgError=mapeos.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=mapeos.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC2 Mapeos: Debería haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC2 Mapeos: Resultado obtenido="+(hayMsgError && analisis));
		mapeos.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	
	@Test
	public void testMapeos3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Mapeos");
		logger.info("TC3 Mapeos: Probar la eliminación correcta de un mapeo cancelando el borrado");
		logger.info("TC3 Mapeos: Resultado esperado: true (aparece alerta de confirmación)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		String indice="99";
		int num_fila=mapeos.obtenerNumFilaTabla("Índice",indice);
		boolean analisis=false;
		boolean hayAlerta=false;
		boolean encontradoTabla2=false;
		boolean encontradoTabla1=num_fila>=1 && num_fila<=mapeos.numFilasTabla();
		if (!encontradoTabla1) {
			logger.error("TC3 Mapeos: El elemento seleccionado en la tabla no existe: ("+indice+")");
			LoginTest.critical++;
		}
		else {
			mapeos.seleccionarFila(num_fila);
			mapeos.eliminar();
			hayAlerta=mapeos.hayAlerta();
			if (hayAlerta) {
				AlertPopup alerta=mapeos.alerta();
				analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
				if (!analisis) {
					LoginTest.minors++;
				}
				mapeos.cancelarAlerta();
				mapeos.recargarTabla();
				encontradoTabla2=(mapeos.obtenerFilaTabla("Índice",indice)!=null);
				if (!encontradoTabla2) {
					logger.error("TC3 Mapeos: Debería haber aparecido la categoría borrada ("+indice+")");
					LoginTest.majors++;
				}
			}
		}
		logger.info("TC3 Mapeos: Resultado obtenido: "+(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2));
		mapeos.volver();
		assertTrue(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2);
	}


	@Test
	public void testMapeos4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Mapeos");
		logger.info("TC4 Mapeos: Comprobación del contenido de la tabla contra BD");
		logger.info("TC4 Mapeos: Resultado esperado=true");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		
		// Se valida que la query de la BD está contenida en la Tabla
		boolean iguales=true;
		try {
			String queryString = "select mapindex,tollcompany,MINDOUBLEWHEEL,MAXDOUBLEWHEEL,\r\n" + 
					"BUSFLAG,MINAXLES,MAXAXLES,MINAXLE12DIST,MAXAXLE12DIST,TRAILER,MINHEIGHT,MAXHEIGHT,MINWIDTH,\r\n" + 
					"MAXWIDTH,CLASS from dbo.AAVCCLASSMAP";
			Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(queryString);
			int tamaño=RutinasTestComunes.obtenerTamañoConsulta(rs);
			if (tamaño!=mapeos.numFilasTotalTabla()) {
				logger.error("TC4 Mapeos: El número de registros de la búsqueda es " +tamaño+" y no coincide con el de la tabla: "+mapeos.numFilasTabla());
				LoginTest.critical++;
				iguales=false;
			}
			while (rs.next() && iguales) {
				iguales=mapeos.buscarRegistroBDEnTabla(rs);
			}
			if (!iguales) {
				LoginTest.critical++;
			}
		}
		catch (Exception e) {
			logger.fatal("TC4 Mapeos: Error al acceder a la BD");
			LoginTest.critical++;
			e.printStackTrace();
			fail();
		}
		
		// Se valida que el contenido de la Tabla está en BD
		mapeos.pulsarBotonTablaPrimero();
		boolean iguales2=true;
		do {
			mapeos.recargarTabla();
			for (int num_fila=1; (num_fila<=mapeos.numFilasTabla()) && iguales2; num_fila++) {
				String elemento=mapeos.obtenerValorColumna(num_fila, "Índice");
				String queryString = "select mapindex,tollcompany,MINDOUBLEWHEEL,MAXDOUBLEWHEEL,BUSFLAG,MINAXLES,"
						+ "MAXAXLES,MINAXLE12DIST,MAXAXLE12DIST,TRAILER,MINHEIGHT,MAXHEIGHT,MINWIDTH,MAXWIDTH,CLASS"
						+ " from dbo.AAVCCLASSMAP where mapindex='"+elemento+"'";
				try {
					Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs = stmt.executeQuery(queryString);
					rs.next();
					iguales2=mapeos.buscarRegistroTablaEnBD(rs, elemento);
					if (!iguales2) {
						logger.error("TC4 Mapeos: El registro de la tabla " +elemento+" no coincide con el de la BD.");
						LoginTest.majors++;
					}
				}
				catch (Exception e) {
					logger.fatal("TC4 Mapeos: Error al acceder a la BD");
					LoginTest.critical++;
					e.printStackTrace();
					fail();
				}
			}
			if (!mapeos.ultimaTabla()) {
				mapeos.pulsarBotonTablaSiguiente();
			}
		}
		while (!mapeos.ultimaTabla() && iguales2);
		logger.info("TC4 Mapeos: Resultado obtenido="+(iguales && iguales2));
		assertTrue(iguales && iguales2);
	}
	
	@Test
	public void testMapeos5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Mapeos");
		logger.info("TC5 Mapeos: Probar la eliminación correcta de un nodo aceptando el borrado");
		logger.info("TC5 Mapeos: Resultado esperado: true (aparece alerta de confirmación)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		String indice="99";
		int num_fila=mapeos.obtenerNumFilaTabla("Índice",indice);
		boolean analisis=false;
		boolean hayAlerta=false;
		boolean encontradoTabla2=false;
		boolean hayMsgError=false;
		boolean encontradoTabla1=num_fila>=1 && num_fila<=mapeos.numFilasTabla();
		if (!encontradoTabla1) {
			logger.error("TC5 Mapeos: El elemento seleccionado en la tabla no existe: ("+indice+")");
			LoginTest.critical++;
		}
		else {
			mapeos.seleccionarFila(num_fila);
			mapeos.eliminar();
			hayAlerta=mapeos.hayAlerta();
			if (hayAlerta) {
				AlertPopup alerta=mapeos.alerta();
				analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
				if (!analisis) {
					LoginTest.minors++;
				}
				mapeos.aceptarAlerta();
				hayMsgError=mapeos.hayMensajeError();
				if (hayMsgError) {
					ErrorMessage errormsgobt=mapeos.mensajeError();
					logger.error("TC5 Mapeos: No debería haber aparecido el mensaje de error '"+errormsgobt.getText()+"'");
					LoginTest.majors++;
				}
				mapeos.recargarTabla();
				encontradoTabla2=(mapeos.obtenerFilaTabla("Índice",indice)!=null);
				if (encontradoTabla2) {
					logger.error("TC5 Mapeos: Debería haber aparecido la categoría borrada ("+indice+")");
					LoginTest.majors++;
				}
			}
		}
		logger.info("TC5 Mapeos: Resultado obtenido: "+(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2 && !hayMsgError));
		mapeos.volver();
		assertTrue(hayAlerta && analisis && encontradoTabla1 && !encontradoTabla2 && !hayMsgError);
	}
}
