package testCasesITATAHost.ConfiguracionSistema.GestionClases.Categorias;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.GestionClases.Categorias.CategoriasProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.AlertPopup;
import unidadesGraficas.ErrorMessage;

public class CategoriasTest {

	final static String titulo="Gesti�n de tarifas de clase"; 
	final static String subtitulo="Configuraci�n sistema   -   Gesti�n de clases   -   Categor�as"; 
	final static String[] botones= {"Informe","Enviar a v�as","Volver"};
	final static String[] botonescrud= {"Crear","Modificar","Eliminar"};
	final static String[] columnasgrid= {"Concesionaria","Categor�a","Descripci�n","Tipo"};
	final static String[] botonesgrid={"Primera","<",">","�ltima"};
	final static String msg_error_noseleccionado="Ning�n elemento seleccionado";
	final static String alerta_confirmacion_borrado="�Est� seguro de que desea eliminar la categor�a?";
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(CategoriasTest.class);
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
	public void testCategorias1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Categorias");
		logger.info("TC1 Categorias: Probar el an�lisis sint�ctico correcto");
		logger.info("TC1 Categorias: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Gesti�n de clases","Categor�as");
		CategoriasProcess categorias=new CategoriasProcess();
		boolean analisis=categorias.sintacticAnalysis(titulo,subtitulo, botones,null,botonescrud,columnasgrid,botonesgrid);
		if (analisis) {
			logger.info("TC1 Categorias: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC1 Categorias: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Categorias: Resultado obtenido="+analisis);
		categorias.volver();
		assertTrue(analisis);
	}
	
	@Test
	public void testCategorias2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Categorias");
		logger.info("TC2 Categorias: Probar la eliminaci�n incorrecta sin seleccionar categor�a");
		logger.info("TC2 Categorias: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Gesti�n de clases","Categor�as");
		CategoriasProcess categorias=new CategoriasProcess();
		categorias.eliminar();
		boolean analisis=false;
		boolean hayMsgError=categorias.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=categorias.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC2 Categorias: Deber�a haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC2 Categorias: Resultado obtenido="+(hayMsgError && analisis));
		categorias.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	
	@Test
	public void testCategorias3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Categorias");
		logger.info("TC3 Categorias: Probar la eliminaci�n correcta de una categor�a cancelando el borrado");
		logger.info("TC3 Categorias: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Gesti�n de clases","Categor�as");
		CategoriasProcess categorias=new CategoriasProcess();
		String[] campos= {"Concesionaria","Categor�a"};
		String[] valores= {"Descripcion bb","00"};
		int num_fila=categorias.obtenerNumFilaTabla(campos,valores);
		boolean analisis=false;
		boolean hayAlerta=false;
		boolean encontradoTabla2=false;
		boolean encontradoTabla1=num_fila>=1 && num_fila<=categorias.numFilasTabla();
		if (!encontradoTabla1) {
			logger.error("TC3 Categorias: El elemento seleccionado en la tabla no existe: ("+valores[0]+","+valores[1]+")");
			LoginTest.critical++;
		}
		else {
			categorias.seleccionarFila(num_fila);
			categorias.eliminar();
			hayAlerta=categorias.hayAlerta();
			if (hayAlerta) {
				AlertPopup alerta=categorias.alerta();
				analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
				if (!analisis) {
					LoginTest.minors++;
				}
				categorias.cancelarAlerta();
				categorias.recargarTabla();
				encontradoTabla2=(categorias.obtenerFilaTabla(campos,valores)!=null);
				if (!encontradoTabla2) {
					logger.error("TC3 Categorias: Deber�a haber aparecido la categor�a borrada ("+valores[0]+","+valores[1]+") en la tabla");
					LoginTest.majors++;
				}
			}
		}
		logger.info("TC3 Categorias: Resultado obtenido: "+(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2));
		categorias.volver();
		assertTrue(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2);
	}

	

	@Test
	public void testCategorias4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Categorias");
		logger.info("TC4 Categorias: Comprobaci�n del contenido de la tabla contra BD");
		logger.info("TC4 Categorias: Resultado esperado=true");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Gesti�n de clases","Categor�as");
		CategoriasProcess categorias=new CategoriasProcess();
		
		// Se valida que la query de la BD est� contenida en la Tabla
		boolean iguales=true;
		try {
			String queryString = "select atollcompany.name,class,description,type from dbo.aclass,dbo.atollcompany"
					+ " where atollcompany.tollcompany=aclass.tollcompany order by atollcompany.tollcompany,class ASC";
			Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(queryString);
			int tama�o=RutinasTestComunes.obtenerTama�oConsulta(rs);
			if (tama�o!=categorias.numFilasTotalTabla()) {
				logger.error("TC4 Categorias: El n�mero de registros de la b�squeda es " +tama�o+" y no coincide con el de la tabla: "+categorias.numFilasTabla());
				LoginTest.critical++;
				iguales=false;
			}
			while (rs.next() && iguales) {
				iguales=categorias.buscarRegistroBDEnTabla(rs);
			}
			if (!iguales) {
				LoginTest.critical++;
			}
		}
		catch (Exception e) {
			logger.fatal("TC4 Categorias: Error al acceder a la BD");
			LoginTest.critical++;
			e.printStackTrace();
			fail();
		}
		
		// Se valida que el contenido de la Tabla est� en BD
		categorias.pulsarBotonTablaPrimero();
		boolean iguales2=true;
		do {
			categorias.recargarTabla();
			for (int num_fila=1; (num_fila<=categorias.numFilasTabla()) && iguales2; num_fila++) {
				String elemento1=categorias.obtenerValorColumna(num_fila, "Concesionaria");
				String elemento2=categorias.obtenerValorColumna(num_fila, "Categor�a");
				String queryString = "select atollcompany.name,class,description,type from dbo.aclass,dbo.atollcompany"
						+ " where atollcompany.tollcompany=aclass.tollcompany and atollcompany.name='"+elemento1+"' and class='"+elemento2+"'";
				try {
					Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs = stmt.executeQuery(queryString);
					rs.next();
					iguales2=categorias.buscarRegistroTablaEnBD(rs, elemento1, elemento2);
					if (!iguales2) {
						logger.error("TC4 Categorias: El registro de la tabla ("+elemento1+","+elemento2+") no coincide con el de la BD.");
						LoginTest.majors++;
					}
				}
				catch (Exception e) {
					logger.fatal("TC4 Categorias: Error al acceder a la BD");
					LoginTest.critical++;
					e.printStackTrace();
					fail();
				}
			}
			if (!categorias.ultimaTabla()) {
				categorias.pulsarBotonTablaSiguiente();
			}
		}
		while (!categorias.ultimaTabla() && iguales2);
		logger.info("TC4 Categorias: Resultado obtenido="+(iguales && iguales2));
		assertTrue(iguales && iguales2);
	}
	
	@Test
	public void testCategorias5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Categorias");
		logger.info("TC5 Categorias: Probar la eliminaci�n correcta de una categor�a aceptando el borrado");
		logger.info("TC5 Categorias: Resultado esperado: true (aparece alerta de confirmaci�n)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Gesti�n de clases","Categor�as");
		CategoriasProcess categorias=new CategoriasProcess();
		String[] campos= {"Concesionaria","Categor�a"};
		String[] valores= {"Descripcion bb","00"};
		int num_fila=categorias.obtenerNumFilaTabla(campos,valores);
		boolean analisis=false;
		boolean hayAlerta=false;
		boolean encontradoTabla2=false;
		boolean hayMsgError=false;
		boolean encontradoTabla1=num_fila>=1 && num_fila<=categorias.numFilasTabla();
		if (!encontradoTabla1) {
			logger.error("TC5 Categorias: El elemento seleccionado en la tabla no existe: ("+valores[0]+","+valores[1]+")");
			LoginTest.critical++;
		}
		else {
			categorias.seleccionarFila(num_fila);
			categorias.eliminar();
			hayAlerta=categorias.hayAlerta();
			if (hayAlerta) {
				AlertPopup alerta=categorias.alerta();
				analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
				if (!analisis) {
					LoginTest.minors++;
				}
				categorias.aceptarAlerta();
				hayMsgError=categorias.hayMensajeError();
				if (hayMsgError) {
					ErrorMessage errormsgobt=categorias.mensajeError();
					logger.error("TC5 Categorias: No deber�a haber aparecido el mensaje de error '"+errormsgobt.getText()+"'");
					LoginTest.majors++;
				}
				categorias.recargarTabla();
				encontradoTabla2=(categorias.obtenerFilaTabla(campos,valores)!=null);
				if (encontradoTabla2) {
					logger.error("TC5 Categorias: Deber�a haber aparecido la categor�a borrada ("+valores[0]+","+valores[1]+") en la tabla");
					LoginTest.majors++;
				}
			}
		}
		logger.info("TC5 Categorias: Resultado obtenido: "+(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2 && !hayMsgError));
		categorias.volver();
		assertTrue(hayAlerta && analisis && encontradoTabla1 && !encontradoTabla2 && !hayMsgError);
	}
}
