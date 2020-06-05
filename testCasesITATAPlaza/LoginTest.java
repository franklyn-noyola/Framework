package testCasesITATAPlaza;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import procesosComunes.LoginProcess;
import procesosITATAHost.InitBOProcess;
import unidadesGraficas.ErrorMessage;
import unidadesGraficas.Navigator;
import unidadesGraficas.SeleniumSession;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

public class LoginTest {

	final static String url="http://virtualbo-qa/BOQAPlazaITATA/web/forms/core/login.aspx";
	final static String titulo_login="Back-Office";
	final static String subtitulo_login="Plaza N2 - Calidad";
	final static String label_field1_login="Usuario:";
	final static String label_field2_login="Contraseña:";
	final static String button1_login="Entrar";
	final static String button2_login="Limpiar";
	final static String subtitulo_bo="Host N3 - Calidad";
	final static String mssError_login_cerrado="Sesión cerrada";
	final static String mssError_contraseña_erronea="ERROR: Contraseña incorrecta";
	final static String mssError_usuario_erroneo="No existe el usuario";
	
	public static String user="00001";
	public static String password="00001";
	static SeleniumSession session;
	static String[] valoresVariables=new String[2];
	public static int minors=0;
	public static int majors=0;
	public static int critical=0;
	private static Logger logger;
	public static Connection DBconnectionPlaza;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	   session=new SeleniumSession(url, Navigator.CHROME,0);
	   FileInputStream file=null;
	   try {
	    file= new FileInputStream("C:\\Users\\morenos\\Desktop\\fichero.txt");
	   }
	   catch (Exception e) {
		   e.printStackTrace();
	   }
	   PropertyConfigurator.configure(file);
	   file.close();
	   logger = Logger.getLogger(LoginTest.class);
	   logger.debug("setUpBeforeClass LoginTest");
	   
	   try {
		   String connectionUrlPlaza = "jdbc:sqlserver://172.18.130.46:1433;DataBaseName=ITATA_QA_TOLLPLAZA";
		   DBconnectionPlaza = DriverManager.getConnection(connectionUrlPlaza, "Fgarcia", "Demo.1234");
	   }
	   catch (Exception e) {
		   System.out.println("Error en conexión a BD");
		   e.printStackTrace();
		   System.exit(0);
	   }
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		session.close();
		logger.debug("tearDownAfterClass");
		if (session.allDriversClosed()) {
			logger.info("Critical: "+critical);
			logger.info("Majors: "+majors);
			logger.info("Minors: "+minors);
			logger.info("Fin de pruebas");
		}
		DBconnectionPlaza.close();
	}

	@After
	public void AfterTest() throws Exception {
		logger.info("Fin TC");
		logger.info("-----------------------------------------------------------------");
	}
	
	public void testLoginBasico() {
		LoginProcess login=new LoginProcess();
		valoresVariables=login.valoresVariables();
		login.escribirUsuario(user);
		login.escribirPassword(password);
		login.entrar();
	}

	
	@Test
	public void testLogin1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Login");
		logger.info("TC1 Login: Probar el análisis sintáctico correcto");
		logger.info("TC1 Login: Resultado esperado=true (operación correcta)");
		LoginProcess login=new LoginProcess();
		boolean analisis=login.sintacticAnalysis(titulo_login, subtitulo_login,label_field1_login,
					label_field2_login, button1_login, button2_login);
		if (analisis) {
			logger.info("TC1 Login: Análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Login: Análisis sintáctico incorrecto");
			minors++;
		}
		logger.info("TC1 Login: Resultado obtenido="+analisis);
		assertTrue(analisis);
	}
		
	@Test
	public void testLogin2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Login");
		logger.info("TC2 Login: Probar el login correcto");
		logger.info("TC2 Login: Resultado esperado=true (operación correcta)");
		LoginProcess login=new LoginProcess();
		login.escribirUsuario(user);
		login.escribirPassword(password);
		boolean logincorrecto=login.entrar();
		if (logincorrecto) {
			logger.info("TC2 Login: Login correcto");
		}
		else {
			logger.error("TC2 Login: Login incorrecto");
			majors++;
		}
		InitBOProcess bo=new InitBOProcess();
		bo.logout();
		if (bo.hayAlerta()) {
			bo.aceptarAlerta();
		}
		else {
			logger.error("TC2 Login: No ha aperacido la alerta de salida del BO");
			minors++;
		}
		logger.info("TC2 Login: Resultado esperado="+logincorrecto);
		assertTrue(logincorrecto);
	}

	@Test
	public void testLogin3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Login");
		logger.info("TC3 Login: Probar el login con una contraseña incorrecta");
		logger.info("TC3 Login: Resultado esperado=true (aparece mensaje de error)");
		LoginProcess login=new LoginProcess();
		login.escribirUsuario(user);
		login.escribirPassword("2");
		boolean logincorrecto=login.entrar();
		boolean hayerror=true;
		boolean msgErrCorrecto=true;
		if (!logincorrecto) {
			hayerror=login.hayMensajeError();
			if (hayerror) {
				ErrorMessage errormsgobt=login.mensajeError();
				msgErrCorrecto=errormsgobt.sintacticAnalysis(mssError_contraseña_erronea);
				if (msgErrCorrecto) {
					logger.info("TC3 Login: Mensaje de error correcto");
				}
				else {
					logger.error("TC3 Login: El mensaje de error visualizado es incorrecto: '"+errormsgobt.getText());
					minors++;
				}
			}
			else {
				logger.error("TC3 Login: Debería haber un mensaje de error");
				majors++;
			}
		}
		else {
			logger.error("TC3 Login: No debería haber saltado de pantalla");
			majors++;
		}
		logger.info("TC3 Login: Resultado esperado="+(!logincorrecto && hayerror && msgErrCorrecto));
		assertTrue(!logincorrecto && hayerror && msgErrCorrecto);
	}
	
	@Test
	public void testLogin4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Login");
		logger.info("TC4 Login: Probar el login con un usuario inexistente");
		logger.info("TC4 Login: Resultado esperado=true (aparece mensaje de error)");
		LoginProcess login=new LoginProcess();
		login.escribirUsuario("0ñaña0");
		login.escribirPassword("2");
		boolean logincorrecto=login.entrar();
		boolean hayerror=true;
		boolean msgErrCorrecto=true;
		if (!logincorrecto) {
			hayerror=login.hayMensajeError();
			if (hayerror) {
				ErrorMessage errormsgobt=login.mensajeError();
				msgErrCorrecto=errormsgobt.sintacticAnalysis(mssError_usuario_erroneo);
				if (msgErrCorrecto) {
					logger.info("TC4 Login: Mensaje de error correcto");
				}
				else {
					logger.error("TC4 Login: El mensaje de error visualizado es incorrecto: '"+errormsgobt.getText());
					minors++;
				}
			}
			else {
				logger.error("TC4 Login: Debería haber un mensaje de error");
				majors++;
			}
		}
		else {
			logger.error("TC4 Login: No debería haber saltado de pantalla");
			majors++;
		}
		logger.info("TC4 Login: Resultado esperado="+(!logincorrecto && hayerror && msgErrCorrecto));
		assertTrue(!logincorrecto && hayerror && msgErrCorrecto);
	}
}
