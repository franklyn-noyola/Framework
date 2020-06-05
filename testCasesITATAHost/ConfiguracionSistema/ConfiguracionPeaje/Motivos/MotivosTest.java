package testCasesITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Motivos;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Motivos.MotivosProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.AlertPopup;
import unidadesGraficas.ErrorMessage;

public class MotivosTest {

	final String titulo_motivos="Motivos"; 
	final String subtitulo_motivos="Configuraci�n sistema   -   Configuraci�n de peaje   -   Motivos"; 
	final String[] botones_motivos= {"Informe","Enviar a v�as","Volver"};
	final String[] botonescrud_motivos= {"Crear","Modificar","Eliminar"};
	final String[] columnasgrid_motivos= {"Motivo","Tipo","Descripci�n"};
	final String[] botonesgrid_motivos={"Primera","<",">","�ltima"};
	final String msg_error_noseleccionado="Ning�n elemento seleccionado";
	final String alerta_confirmacion_borrado="�Est� seguro de que desea eliminar el motivo?";
	public final static String[] tipos_motivo={"Cruce no-autorizado","Cruce no-autorizado controlado","Tipo de exento","Exenci�n"};
	
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(MotivosTest.class);
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
	public void testMotivos1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Motivos");
		logger.info("TC1 Motivos: Probar el an�lisis sint�ctico correcto");
		logger.info("TC1 Motivos: Resultado esperado=true (operaci�n correcta)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Motivos");
		MotivosProcess motivos=new MotivosProcess();
		boolean analisis=motivos.sintacticAnalysis(titulo_motivos,
				subtitulo_motivos, botones_motivos,null,
				botonescrud_motivos,columnasgrid_motivos,
				botonesgrid_motivos);
		if (analisis) {
			logger.info("TC1 Motivos: an�lisis sint�ctico correcto");
		}
		else {
			logger.error("TC1 Motivos: an�lisis sint�ctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Motivos: Resultado obtenido="+analisis);
		motivos.volver();
		assertTrue(analisis);
	}
	
	@Test
	public void testMotivos2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Motivos");
		logger.info("TC2 Motivos: Probar la eliminaci�n incorrecta sin seleccionar motivo");
		logger.info("TC2 Motivos: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Motivos");
		MotivosProcess motivos=new MotivosProcess();
		motivos.eliminar();
		boolean analisis=false;
		boolean hayMsgError=motivos.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=motivos.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC2 Motivos: Deber�a haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC2 Motivos: Resultado obtenido="+(hayMsgError && analisis));
		motivos.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	@Test
	public void testMotivos3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Motivos");
		logger.info("TC3 Motivos: Probar la eliminaci�n correcta de un motivo cancelando el borrado");
		logger.info("TC3 Motivos: Resultado esperado: true (aparece alerta de confirmaci�n)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Motivos");
		MotivosProcess motivos=new MotivosProcess();
		String[] columnas= {"Motivo","Tipo"};
		String[] valores= {"09",tipos_motivo[0]};
		int num_fila=motivos.obtenerNumFilaTabla(columnas, valores);
		boolean analisis=false;
		boolean hayAlerta=false;
		boolean encontradoTabla2=false;
		boolean encontradoTabla1=num_fila>=1 && num_fila<=motivos.numFilasTabla();
		if (!encontradoTabla1) {
			logger.error("Error: el elemento seleccionado en la tabla no existe: ("+valores[0]+","+valores[1]+")");
			LoginTest.critical++;
		}
		else {
			motivos.seleccionarFila(num_fila);
			motivos.eliminar();
			analisis=false;
			hayAlerta=motivos.hayAlerta();
			if (hayAlerta) {
				AlertPopup alerta=motivos.alerta();
				analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
				if (!analisis) {
					LoginTest.minors++;
				}
				motivos.cancelarAlerta();
				motivos.recargarTabla();
				encontradoTabla2=(motivos.obtenerFilaTabla(columnas, valores)!=null);
				if (!encontradoTabla2) {
					logger.error("TC3 Motivos: Deber�a haber aparecido el motivo ("+valores[0]+","+valores[1]+") en la tabla");
					LoginTest.majors++;
				}
			}
		}
		logger.info("TC3 Motivos: Resultado obtenido: "+(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2));
		motivos.volver();
		assertTrue(hayAlerta && analisis && encontradoTabla1 && encontradoTabla2);
	}
	
	@Test
	public void testMotivos4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Motivos");
		logger.info("TC4 Motivos: Probar la eliminaci�n correcta de un motivo aceptando el borrado");
		logger.info("TC4 Motivos: Resultado esperado: true (aparece alerta de confirmaci�n)");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Motivos");
		MotivosProcess motivos=new MotivosProcess();
		String[] columnas= {"Motivo","Tipo"};
		String[] valores= {"99",tipos_motivo[2]};
		int num_fila=motivos.obtenerNumFilaTabla(columnas, valores);
		boolean analisis=false;
		boolean hayAlerta=false;
		boolean encontradoTabla1=num_fila>=1 && num_fila<=motivos.numFilasTabla();
		boolean encontradoTabla2=false;
		if (!encontradoTabla1) {
			logger.error("Error: el elemento seleccionado en la tabla no existe: ("+valores[0]+","+valores[1]+")");
			LoginTest.critical++;
		}
		else {
			motivos.seleccionarFila(num_fila);
			motivos.eliminar();
			analisis=false;
			hayAlerta=motivos.hayAlerta();
			if (hayAlerta) {
				AlertPopup alerta=motivos.alerta();
				analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
				if (!analisis) {
					LoginTest.minors++;
				}
				motivos.aceptarAlerta();
				motivos.recargarTabla();
				encontradoTabla2=(motivos.obtenerFilaTabla(columnas, valores)!=null);
				if (encontradoTabla2) {
					logger.error("TC4: No deber�a haber aparecido el motivo borrado ("+valores[0]+","+valores[1]+") en la tabla");
					LoginTest.majors++;
				}
			}
		}
		logger.info("TC4 Motivos: Resultado obtenido: "+(hayAlerta && analisis && encontradoTabla1 && !encontradoTabla2));
		motivos.volver();
		assertTrue(hayAlerta && analisis && encontradoTabla1 && !encontradoTabla2);
	}
	
	@Test
	public void testMotivos5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Motivos");
		logger.info("TC5 Motivos: Comprobaci�n del contenido de la tabla contra BD");
		logger.info("TC5 Motivos: Resultado esperado=true");
		botest.bo.seleccionarOpcionMenu("Configuraci�n sistema","Configuraci�n de peaje","Motivos");
		MotivosProcess motivos=new MotivosProcess();
		
		// Se valida que la query de la BD est� contenida en la Tabla
		boolean iguales=true;
		try {
			String queryString = "select \"option\",choicetype,description from dbo.aoptionchoice order by choicetype,\"option\" ASC";
			Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(queryString);
			int tama�o=RutinasTestComunes.obtenerTama�oConsulta(rs);
			if (tama�o!=motivos.numFilasTotalTabla()) {
				logger.error("El n�mero de registros de la b�squeda es " +tama�o+" y no coincide con el de la tabla: "+motivos.numFilasTabla());
				LoginTest.critical++;
				iguales=false;
			}
			while (rs.next() && iguales) {
				iguales=motivos.buscarRegistroBDEnTabla(rs);
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
		
		// Se valida que el contenido de la Tabla est� en BD
		motivos.pulsarBotonTablaPrimero();
		boolean iguales2=true;
		int codigoTipo=0;
		do {
			motivos.recargarTabla();
			for (int num_fila=1; (num_fila<=motivos.numFilasTabla()) && iguales2 && codigoTipo!=-1; num_fila++) {
				
				String[] elemento=new String[motivos.numColumnasTabla()];
				elemento=motivos.obtenerFilaTabla(num_fila);
				codigoTipo=obtenerCodigoTipo(elemento[1]);
				if (codigoTipo==-1) {
					logger.error("La descripci�n del tipo mostrada en la fila "+num_fila+" es err�nea");
					LoginTest.majors++;
				}
				else {
					String queryString = "select \"option\",choicetype,description from dbo.aoptionchoice where"
							+ " \"option\"='"+elemento[0]+"' and choicetype='"+codigoTipo+"'";
					try {
						Statement stmt = LoginTest.DBconnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						ResultSet rs = stmt.executeQuery(queryString);
						rs.next();
						iguales2=motivos.buscarRegistroTablaEnBD(rs, elemento[0], tipos_motivo[codigoTipo-1]);
						if (!iguales2) {
							logger.error("El registro de la tabla " +elemento[0]+" no coincide con el de la BD.");
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
			}
			if (!motivos.ultimaTabla()) {
				motivos.pulsarBotonTablaSiguiente();
			}
		}
		while (!motivos.ultimaTabla() && iguales2);
		logger.info("TC5 Motivos: Resultado obtenido="+(iguales && iguales2 && (codigoTipo!=-1)));
		assertTrue(iguales && iguales2 && (codigoTipo!=-1));
	}
	
	private int obtenerCodigoTipo(String descripcionTipo) {
		boolean encontrado=false;
		
		for (int i=0; i<tipos_motivo.length && !encontrado; i++) {
			if (tipos_motivo[i].equals(descripcionTipo)) {
				encontrado=true;
				return i+1; // Se devuelve i+1 ya que es el valor que tiene en BD
			}
		}
		return -1;
	}
}
