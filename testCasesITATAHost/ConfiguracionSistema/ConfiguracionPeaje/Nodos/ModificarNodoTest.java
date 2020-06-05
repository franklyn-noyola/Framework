package testCasesITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.ModificarNodoProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Nodos.NodosProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.ErrorMessage;


public class ModificarNodoTest {

	final static String titulo="Modificación Nodo de Plaza"; 
	final static String subtitulo="Configuración sistema   -   Configuración de peaje   -   Nodos   -   Modificar"; 
	final static String encabezado="Información del nodo";
	final static String[] botones= {"Confirmar","Volver"};
	final static String[] labels_variables= {"Código"};
	final static String[] labels= {"Dirección","Descripción"};
	final static String msg_error_noseleccionado="Ningún elemento seleccionado";
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass1() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(ModificarNodoTest.class);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		InitBOTest.tearDownAfterClass();
	}
	
	@After
	public void afterTest() throws Exception {
		logger.info("Fin TC");
		logger.info("-----------------------------------------------------------------");
	}
	
	@Test
	public void testModificarNodo1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Modificar Nodo");
		logger.info("TC1 Modificar Nodo: Probar que el análisis sintáctico es correcto");
		logger.info("TC1: Modificar Nodo: Resultado esperado=true (operación correcta)");
		String[] valoresvariables=new String[1];
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		int numfila=1;
		String columna="Código";
		String valor=nodos.obtenerValorColumna(numfila,columna);
		if (valor==null) {
			logger.error("TC1 Modificar Nodo: El nombre de la columna especificada no existe: "+columna);
		}
		else {
			valoresvariables[0]=valor;
		}
		nodos.seleccionarFila(numfila);
		nodos.modificar();
		ModificarNodoProcess modificarNodo=new ModificarNodoProcess();
		boolean analisis=modificarNodo.sintacticAnalysis(titulo, subtitulo,botones, encabezado, labels_variables,
				valoresvariables,labels);
		if (!analisis) {
			LoginTest.minors++;
		}
		logger.info("TC1: Modificar Nodo: resultado obtenido="+analisis);
		assertTrue(analisis);
	}
	
	@Test
	public void testModificarNodo2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Modificar Nodo");
		logger.info("TC2 Modificar Nodo: Probar la edición un nodo y comprobar que el editado es el mismo que el seleccionado");
		logger.info("TC2: Modificar Nodo: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		String filatabla[]=new String[nodos.numColumnasTabla()];
		String datosesperados[]=new String[nodos.numColumnasTabla()];
		filatabla=nodos.obtenerFilaTabla(1);
		nodos.seleccionarFila(1);
		nodos.modificar();
		ModificarNodoProcess modificarNodo=new ModificarNodoProcess();
		datosesperados[0]=modificarNodo.leerCampoCodigo();
		datosesperados[1]=modificarNodo.leerCampoDireccion();
		datosesperados[2]=modificarNodo.leerCampoDescripcion();
		datosesperados[3]=filatabla[3];
		boolean iguales=RutinasTestComunes.assertArrayEquals(filatabla,datosesperados);
	    if (!iguales) {
	    	logger.error("TC2 Modificar Nodo: el nodo editado no coincide con el de la tabla");
	    	LoginTest.majors++;
	       }
		logger.info("TC2: Modificar Nodo: Resultado obtenido="+iguales);
		assertTrue(iguales);
	}
	
	@Test
	public void testModificarNodo3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Modificar Nodo");
		logger.info("TC3 Modificar Nodo: Probar la edición de un elemento existente y comprobar que coincide con el"
				+ " de la tabla una vez modificado");
		logger.info("TC3: Modificar Nodo: Resultado esperado=true (operación correcta)");
		String[] datosEntrada={"11.11.11.11","Descripcion aa"};
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		nodos.seleccionarFila(1);
		nodos.modificar();
		ModificarNodoProcess modificarNodo=new ModificarNodoProcess();
		String datosesperados[]=new String[nodos.numColumnasTabla()];
		modificarNodo.escribirCampoDireccion(datosEntrada[0]);
		modificarNodo.escribirCampoDescripcion(datosEntrada[1]);
		datosesperados[0]=modificarNodo.leerCampoCodigo();
		datosesperados[1]=modificarNodo.leerCampoDireccion();
		datosesperados[2]=modificarNodo.leerCampoDescripcion();
		modificarNodo.confirmar();
		String filatabla[]=new String[nodos.numColumnasTabla()];
		filatabla=nodos.obtenerFilaTabla(1);
		datosesperados[3]=filatabla[3]; // El campo Activo de la tabla no aparece en ModificarNodo
		boolean iguales=RutinasTestComunes.assertArrayEquals(filatabla,datosesperados);
	    if (!iguales) {
	    	logger.error("TC3 Modificar Nodo: el nodo editado no coincide con el que devuelve la tabla");
	    	LoginTest.majors++;
	       }
		logger.info("TC3: Modificar Nodo: Resultado obtenido="+iguales);
		assertTrue(iguales);
	}
	
	@Test
	public void testModificarNodo4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Modificar Nodo");
		logger.info("TC4 Modificar Nodo: Probar la validación de la existencia del campo Dirección");
		logger.info("TC4 Modificar Nodo: Resultado esperado=true (aparece el tooltip correspondiente)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		nodos.seleccionarFila(1);
		nodos.modificar();
		ModificarNodoProcess modificarNodo=new ModificarNodoProcess();
		modificarNodo.borrarCampoDireccion();
		modificarNodo.confirmar();
		boolean hayMsgErrorReq=modificarNodo.hayErrorValidacionRequeridoDireccion();
		if (!hayMsgErrorReq) {
			LoginTest.minors++;
			logger.error("TC4 Modificar Nodo: Debería haber aparecido el tooltip de 'Campo requerido'");
		}
		logger.info("TC4 Modificar Nodo: Resultado obtenido="+hayMsgErrorReq);
		assertTrue(hayMsgErrorReq);
	}
	
	@Test
	public void testModificarNodo5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Modificar Nodo");
		logger.info("TC5 Modificar Nodo: Probar la validación del formato del campo Dirección");
		logger.info("TC5 Modificar Nodo: Resultado esperado=true (aparece el tooltip correspondiente)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		nodos.seleccionarFila(1);
		nodos.modificar();
		ModificarNodoProcess modificarNodo=new ModificarNodoProcess();
		modificarNodo.escribirCampoDireccion("11.11.11.aa");
		modificarNodo.confirmar();
		boolean hayMsgErrorReq=modificarNodo.hayErrorValidacionFormatoDireccion();
		if (!hayMsgErrorReq) {
			LoginTest.minors++;
			logger.error("TC5 Modificar Nodo: Debería haber aparecido el tooltip de 'Formato erróneo'");
		}
		logger.info("TC5 Modificar Nodo: Resultado obtenido="+hayMsgErrorReq);
		assertTrue(hayMsgErrorReq);
	}
	
	@Test
	public void testModificarNodo6() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6 Modificar Nodo");
		logger.info("TC6 Modificar Nodo: Probar la modificación incorrecta sin seleccionar nodo");
		logger.info("TC6 Modificar Nodo: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Nodos");
		NodosProcess nodos=new NodosProcess();
		nodos.modificar();
		boolean analisis=false;
		boolean hayMsgError=nodos.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=nodos.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC6 Modificar Nodo: Debería haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC6 Modificar Nodo: Resultado obtenido="+(hayMsgError && analisis));
		nodos.volver();
		assertTrue(hayMsgError && analisis);
	}
}
