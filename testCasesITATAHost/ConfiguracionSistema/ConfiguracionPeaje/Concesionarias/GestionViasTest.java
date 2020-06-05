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
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.GestionViasProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.AlertPopup;
import unidadesGraficas.ErrorMessage;

public class GestionViasTest {

	final static String titulo="Gestión de vías"; 
	final static String subtitulo="Configuración sistema   -   Configuración de peaje   -   Concesionarias, Plazas, Vías   -   Editar Plazas   -   Editar Vías"; 
	final static String[] botones={"Volver"};
	final static String encabezado1="Concesionaria";
	final static String[] labelsVariables1={"ID","Nombre"};
	final static String encabezado2="Plaza";
	final static String[] labelsVariables2={"Código","Nombre"};
	final static String encabezado3="Vías";
	final static String[] botonescrud={"Crear","Modificar","Eliminar"};
	final static String[] columnasgrid={"Num. Vía","Sentido","Tipo de vía","Nodo Plaza","Nodo Vía"};
	final static String[] botonesgrid={"Primera","<",">","Última"};
	final static String msg_error_noseleccionado="Ningún elemento seleccionado";
	final static String alerta_confirmacion_borrado="¿Está seguro de que desea eliminar la vía?";
	static InitBOTest botest;
	private static Logger logger;
	 
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(GestionViasTest.class);
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
	public void testGestionVias1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Gestión Vias");
		logger.info("TC1 Gestión Vias: Probar el análisis sintáctico correcto");
		logger.info("TC1 Gestión Vias: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		int numfilaconcesionaria=2;
		concesionarias.seleccionarFila(numfilaconcesionaria);
		String[] valoresVariables1=new String[labelsVariables1.length];
		for (int i=0;i<labelsVariables1.length;i++) {
			valoresVariables1[i]=concesionarias.obtenerValorColumna(numfilaconcesionaria, labelsVariables1[i]);
		}
		concesionarias.editarPlazas();
		GestionPlazaProcess nodoplaza=new GestionPlazaProcess();
		int numfilaplaza=1;
		nodoplaza.seleccionarFila(numfilaplaza);
		String[] valoresVariables2=new String[labelsVariables2.length];
		for (int i=0;i<labelsVariables2.length;i++) {
			valoresVariables2[i]=concesionarias.obtenerValorColumna(numfilaplaza, labelsVariables2[i]);
		}
		nodoplaza.editarVias();
		GestionViasProcess nodovia=new GestionViasProcess();
		boolean analisis=nodovia.sintacticAnalysis(titulo,subtitulo, botones,encabezado1,labelsVariables1,valoresVariables1,
				encabezado2,labelsVariables2,valoresVariables2,encabezado3,botonescrud,columnasgrid,botonesgrid);
		if (analisis) {
			logger.info("TC1 Gestión Vias: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Gestión Vias: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Gestión Vias: Resultado obtenido="+analisis);
		nodovia.volver();
		nodoplaza.volver();
		assertTrue(analisis);
	}
	
	@Test
	public void testGestionVias2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Gestión Vias");
		logger.info("TC2 Gestión Vias: Probar la eliminación incorrecta sin seleccionar vía");
		logger.info("TC2 Gestión Vias: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		int numfilaconcesionaria=2;
		concesionarias.seleccionarFila(numfilaconcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess nodoplaza=new GestionPlazaProcess();
		int numfilaplaza=1;
		nodoplaza.seleccionarFila(numfilaplaza);
		nodoplaza.editarVias();
		GestionViasProcess nodovia=new GestionViasProcess();
		nodovia.eliminar();
		boolean analisis=false;
		boolean hayMsgError=nodovia.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=nodovia.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC2 Gestión Vias: Debería haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC2 Gestión Vias: Resultado obtenido="+(hayMsgError && analisis));
		nodovia.volver();
		nodoplaza.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	
	@Test
	public void testGestionVias3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Gestión Vias");
		logger.info("TC3 Gestión Vias: Probar la eliminación correcta de una vía con cancelación");
		logger.info("TC3 Gestión Vias: Resultado esperado: true (aparece alerta de confirmación)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		int numfilaconcesionaria=concesionarias.obtenerNumFilaTabla("ID","00");
		concesionarias.seleccionarFila(numfilaconcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess nodoplaza=new GestionPlazaProcess();
		String plaza="98";
		int num_fila=nodoplaza.obtenerNumFilaTabla("Código",plaza);
		nodoplaza.seleccionarFila(num_fila);
		nodoplaza.editarVias();
		GestionViasProcess nodovia=new GestionViasProcess();
		String via="01";
		num_fila=nodovia.obtenerNumFilaTabla("Num. Vía",via);
		boolean analisis=false;
		boolean hayAlerta=false;
		boolean encontradoTabla2=false;
		boolean encontradoTabla1=num_fila>=1 && num_fila<=nodovia.numFilasTabla();
		if (!encontradoTabla1) {
			logger.error("Error: el elemento seleccionado en la tabla no existe: ("+via+")");
			LoginTest.critical++;
		}
		else {
			nodovia.seleccionarFila(num_fila);
			nodovia.eliminar();
			hayAlerta=nodovia.hayAlerta();
			if (hayAlerta) {
				AlertPopup alerta=nodovia.alerta();
				analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
				if (!analisis) {
					LoginTest.minors++;
				}
				nodovia.cancelarAlerta();
				nodovia.recargarTabla();
				encontradoTabla2=(nodovia.obtenerFilaTabla("Num. Vía",via)!=null);
				if (!encontradoTabla2) {
					logger.error("TC3 : Debería haber aparecido la vía borrada ("+via+") en la tabla");
					LoginTest.majors++;
				}
			}
		}
		logger.info("TC3 Gestión Vias: Resultado obtenido: "+(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2));
		nodovia.volver();
		nodoplaza.volver();
		concesionarias.volver();
		assertTrue(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2);
	}

	@Test
	public void testGestionVias4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Gestión Vias");
		logger.info("TC4 Gestión Vias: Probar la eliminación correcta de una plaza aceptando el borrado");
		logger.info("TC4 Gestión Vias: Resultado esperado: true (aparece alerta de confirmación)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		int numfilaconcesionaria=concesionarias.obtenerNumFilaTabla("ID","00");
		concesionarias.seleccionarFila(numfilaconcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess nodoplaza=new GestionPlazaProcess();
		String plaza="98";
		int num_fila=nodoplaza.obtenerNumFilaTabla("Código",plaza);
		nodoplaza.seleccionarFila(num_fila);
		nodoplaza.editarVias();
		GestionViasProcess nodovia=new GestionViasProcess();
		String via="01";
		num_fila=nodovia.obtenerNumFilaTabla("Num. Vía",via);
		boolean analisis=false;
		boolean hayAlerta=false;
		boolean encontradoTabla2=false;
		boolean hayMsgError=false;
		boolean encontradoTabla1=num_fila>=1 && num_fila<=nodovia.numFilasTabla();
		if (!encontradoTabla1) {
			logger.error("Error: el elemento seleccionado en la tabla no existe: ("+via+")");
			LoginTest.critical++;
		}
		else {
			nodovia.seleccionarFila(num_fila);
			nodovia.eliminar();
			hayAlerta=nodovia.hayAlerta();
			if (hayAlerta) {
				AlertPopup alerta=nodovia.alerta();
				analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
				if (!analisis) {
					LoginTest.minors++;
				}
				nodovia.aceptarAlerta();
				hayMsgError=nodovia.hayMensajeError();
				if (hayMsgError) {
					ErrorMessage errormsgobt=nodovia.mensajeError();
					logger.error("TC4 Gestión Vias: No debería haber aparecido el mensaje de error '"+errormsgobt.getText()+"'");
					LoginTest.majors++;
				}
				nodovia.recargarTabla();
				encontradoTabla2=(nodovia.obtenerFilaTabla("Num. Vía",via)!=null);
				if (encontradoTabla2) {
					logger.error("TC4 Gestión Vias: Debería haber aparecido la vía borrada ("+via+") en la tabla");
					LoginTest.majors++;
				}
			}
		}
		logger.info("TC4 Gestión Vias: Resultado obtenido: "+(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2 && !hayMsgError));
		nodovia.volver();
		nodoplaza.volver();
		concesionarias.volver();
		assertTrue(hayAlerta && analisis && encontradoTabla1 && !encontradoTabla2 && !hayMsgError);
	}
	
	@Test
	public void testGestionVias5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Gestión Vias");
		logger.info("TC5 Gestión Vias: Comprobación del contenido de la tabla contra BD");
		logger.info("TC5 Gestión Vias: Resultado esperado=true");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String concesionaria="01";
		int numfilaconcesionaria=concesionarias.obtenerNumFilaTabla("ID",concesionaria);
		concesionarias.seleccionarFila(numfilaconcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess nodoplaza=new GestionPlazaProcess();
		String plaza="01";
		int num_filavia=nodoplaza.obtenerNumFilaTabla("Código",plaza);
		nodoplaza.seleccionarFila(num_filavia);
		nodoplaza.editarVias();
		GestionViasProcess nodovia=new GestionViasProcess();
		
		// Se valida que la query de la BD está contenida en la Tabla
		boolean iguales=true;
		try {
			String queryString = "select lanenum,alane.LANEDIR,alanedir.description as desc1,alane.LANETYPE,alanetype.description,plazanode,lanenode from dbo.alane,dbo.alanetype,dbo.alanedir " +
					"where alanetype.lanetype=alane.lanetype and alane.LANEDIR=ALANEDIR.LANEDIR and tollcompany='"+concesionaria+"' and plaza='"+plaza+"'";
			Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(queryString);
			int tamaño=RutinasTestComunes.obtenerTamañoConsulta(rs);
			if (tamaño!=nodovia.numFilasTotalTabla()) {
				logger.error("TC5 Gestión Vias: El número de registros de la búsqueda es " +tamaño+" y no coincide con el de la tabla: "+nodovia.numFilasTabla());
				LoginTest.critical++;
				iguales=false;
			}
			while (rs.next() && iguales) {
				iguales=nodovia.buscarRegistroBDEnTabla(rs);
			}
			if (!iguales) {
				LoginTest.critical++;
			}
		}
		catch (Exception e) {
			logger.fatal("TC5 Gestión Vias: Error al acceder a la BD (1)");
			LoginTest.critical++;
			e.printStackTrace();
			fail();
		}
		
		// Se valida que el contenido de la Tabla está en BD
		nodovia.pulsarBotonTablaPrimero();
		boolean iguales2=true;
		do {
			nodovia.recargarTabla();
			for (int num_fila=1; (num_fila<=nodovia.numFilasTabla()) && iguales2; num_fila++) {
				String[] elemento=new String[3];
				elemento[0]=nodovia.obtenerValorColumna(num_fila, "Num. Vía");
				elemento[1]=nodovia.obtenerValorColumna(num_fila, "Sentido");
				elemento[2]=nodovia.obtenerValorColumna(num_fila, "Tipo de vía");
				String queryString = "select lanenum,alane.LANEDIR,alanedir.description as desc1,alane.LANETYPE,alanetype.description,plazanode,lanenode from dbo.alane,dbo.alanetype,dbo.alanedir " + 
						"where alanetype.lanetype=alane.lanetype and alane.LANEDIR=ALANEDIR.LANEDIR and tollcompany='"+concesionaria+"' and plaza='"+plaza+"' and lanenum='"+elemento[0]+"'" +
						" and alane.LANEDIR='"+elemento[1].substring(0,1)+"' and alane.LANETYPE='"+elemento[2].substring(0,2)+"'";
				try {
					Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs = stmt.executeQuery(queryString);
					rs.next();
					iguales2=nodovia.buscarRegistroTablaEnBD(rs, elemento[0], elemento[1], elemento[2]);
					if (!iguales2) {
						logger.error("TC5 Gestión Vias: El registro de la tabla ("+elemento[0]+","+elemento[1]+","+elemento[2]+") no coincide con el de la BD.");
						LoginTest.majors++;
					}
				}
				catch (Exception e) {
					logger.fatal("TC5 Gestión Vias: Error al acceder a la BD");
					LoginTest.critical++;
					e.printStackTrace();
					fail();
				}
			}
			if (!nodovia.ultimaTabla()) {
				nodovia.pulsarBotonTablaSiguiente();
			}
		}
		while (!nodovia.ultimaTabla() && iguales2);
		logger.info("TC5 Gestión Vias: Resultado obtenido="+(iguales && iguales2));
		assertTrue(iguales && iguales2);
	}
}
