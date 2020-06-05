package testCasesITATAHost.ConfiguracionSistema.TarifasMoneda.MonedaAceptada;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.TarifasMoneda.MonedaAceptada.MonedaAceptadaProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.AlertPopup;
import unidadesGraficas.ErrorMessage;

public class MonedaAceptadaTest {

	final static String titulo="Moneda aceptada"; 
	final static String subtitulo="Configuración sistema   -   Tarifas & Moneda   -   Moneda aceptada"; 
	final static String[] botones= {"Informe","Enviar a vías","Volver"};
	final static String[] botonescrud= {"Crear","Eliminar"};
	final static String[] columnasgrid= {"Concesionaria","Tipo de moneda","Denominación"};
	final static String[] botonesgrid={"Primera","<",">","Última"};
	final static String msg_error_noseleccionado="Ningún elemento seleccionado";
	final static String alerta_confirmacion_borrado="¿Está seguro de que desea eliminar esta entrada?";
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(MonedaAceptadaTest.class);
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
	public void testMonedaAceptada1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Moneda Aceptada");
		logger.info("TC1 Moneda Aceptada: Probar el análisis sintáctico correcto");
		logger.info("TC1 Moneda Aceptada: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Tarifas & Moneda","Moneda aceptada");
		MonedaAceptadaProcess moneda=new MonedaAceptadaProcess();
		boolean analisis=moneda.sintacticAnalysis(titulo,subtitulo, botones,null,botonescrud,columnasgrid,botonesgrid);
		if (analisis) {
			logger.info("TC1 Moneda Aceptada: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Moneda Aceptada: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Moneda Aceptada: Resultado obtenido="+analisis);
		moneda.volver();
		assertTrue(analisis);
	}
	
	@Test
	public void testMonedaAceptada2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Moneda Aceptada");
		logger.info("TC2 Moneda Aceptada: Probar la eliminación incorrecta sin seleccionar moneda");
		logger.info("TC2 Moneda Aceptada: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Tarifas & Moneda","Moneda aceptada");
		MonedaAceptadaProcess moneda=new MonedaAceptadaProcess();
		moneda.eliminar();
		boolean analisis=false;
		boolean hayMsgError=moneda.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=moneda.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC2 Moneda Aceptada: Debería haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC2 Moneda Aceptada: Resultado obtenido="+(hayMsgError && analisis));
		moneda.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	
	@Test
	public void testMonedaAceptada3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Moneda Aceptada");
		logger.info("TC3 Moneda Aceptada: Probar la eliminación correcta de una moneda");
		logger.info("TC3 Moneda Aceptada: Resultado esperado: true (aparece alerta de confirmación)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Tarifas & Moneda","Moneda aceptada");
		MonedaAceptadaProcess moneda=new MonedaAceptadaProcess();
		moneda.seleccionarFila(1);
		moneda.eliminar();
		boolean analisis=false;
		boolean hayAlerta=moneda.hayAlerta();
		if (hayAlerta) {
			AlertPopup alerta=moneda.alerta();
			analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
			if (!analisis) {
				LoginTest.minors++;
			}
			moneda.cancelarAlerta();
		}
		logger.info("TC3 Moneda Aceptada: Resultado obtenido: "+(hayAlerta && analisis));
		moneda.volver();
		assertTrue(hayAlerta && analisis);
	}

	@Test
	public void testMonedaAceptada4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Moneda Aceptada");
		logger.info("TC4 Moneda Aceptada: Comprobación del contenido de la tabla contra BD");
		logger.info("TC4 Moneda Aceptada: Resultado esperado=true");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Tarifas & Moneda","Moneda aceptada");
		MonedaAceptadaProcess moneda=new MonedaAceptadaProcess();
		
		// Se valida que la query de la BD está contenida en la Tabla
		boolean iguales=true;
		try {
			String queryString = "select * from dbo.AACCEPTEDCASH order by tollcompany ASC, CASHTYPE DESC, FACEVALUE DESC";
			Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(queryString);
			int tamaño=RutinasTestComunes.obtenerTamañoConsulta(rs);
			if (tamaño!=moneda.numFilasTotalTabla()) {
				logger.error("El número de registros de la búsqueda es " +tamaño+" y no coincide con el de la tabla: "+moneda.numFilasTabla());
				LoginTest.critical++;
				iguales=false;
			}
			while (rs.next() && iguales) {
				iguales=moneda.buscarRegistroBDEnTabla(rs);
			}
			if (!iguales) {
				LoginTest.critical++;
			}
		}
		catch (Exception e) {
			logger.fatal("Error al acceder a la BD");
			LoginTest.critical++;
			e.printStackTrace();
			fail();
		}
		
		// Se valida que el contenido de la Tabla está en BD
		moneda.pulsarBotonTablaPrimero();
		boolean iguales2=true;
		String[] elemento=new String[moneda.numColumnasTabla()];
		do {
			moneda.recargarTabla();
			for (int num_fila=1; (num_fila<=moneda.numFilasTabla()) && iguales2; num_fila++) {
				elemento=moneda.obtenerFilaTabla(num_fila);
				switch (elemento[1]) {
					case "Moneda": elemento[1]="C "; break;
					case "Billete": elemento[1]="N "; break;
					default: 
							logger.error("El valor del tipo de moneda en la tabla es incorrecto.");
							LoginTest.majors++;
							break;
				}
				try {
					elemento[2]=String.valueOf(Integer.parseInt(elemento[2].substring(2).replace(".","")));
				}
				catch (Exception e) {
					fail();
				}
				String queryString = "select * from dbo.AACCEPTEDCASH where"
						+ " tollcompany='"+elemento[0]+"' and cashtype='"+elemento[1]+"' and FACEVALUE="+elemento[2];
				try {
					Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
					ResultSet rs = stmt.executeQuery(queryString);
					rs.next();
					iguales2=moneda.buscarRegistroTablaEnBD(rs, num_fila);
					if (!iguales2) {
						logger.error("El registro de la tabla ("+elemento[0]+","+elemento[1]+","+elemento[2]+") no coincide con el de la BD.");
						LoginTest.majors++;
					}
				}
				catch (Exception e) {
					logger.fatal("Error al acceder a la BD");
					LoginTest.critical++;
					e.printStackTrace();
					fail();
				}
			}
			if (!moneda.ultimaTabla()) {
				moneda.pulsarBotonTablaSiguiente();
			}
		}
		while (!moneda.ultimaTabla() && iguales2);
		logger.info("TC4 Moneda Aceptada: Resultado obtenido="+(iguales && iguales2));
		moneda.volver();
		assertTrue(iguales && iguales2);
	}
}
