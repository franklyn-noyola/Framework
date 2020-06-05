package testCasesITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.ConcesionariasProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.CrearModificarViaProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.GestionPlazaProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.GestionViasProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.ErrorMessage;

public class CrearViaTest {

	final static String titulo="Creación de vía"; 
	final static String subtitulo="Configuración sistema   -   Configuración de peaje   -   Concesionarias, Plazas, Vías   -   Editar Plazas   -   Editar Vías   -   Crear"; 
	final static String[] botones= {"Confirmar","Volver"};
	final static String encabezado1="Concesionaria";
	final static String[] labelsVariables1={"ID","Nombre"};
	final static String encabezado2="Plaza";
	final static String[] labelsVariables2={"Código","Nombre","Nodo Plaza"};
	final static String encabezado3="Vía";
	final static String[] labels3={"Num. Vía","Sentido","Tipo de vía"};
	final static String encabezado4="Nodo Vía";
	final static String[] labels4CriteriosBusqueda={"Dirección"};
	final static String[] labels4labelCriteriosCheckDropdown={"Nodo existente"};
	final static String[] labels4CriteriosCheckField={"Nuevo nodo"};
	final static String msg_error_codigo_existente="Ya existe una vía con esos datos";
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(CrearViaTest.class);
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
	public void testCrearVia01() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Crear Via");
		logger.info("TC1 Crear Via: Probar el análisis sintáctico correcto (crear vía en plaza con algún nodo vía existente)");
		logger.info("TC1 Crear Via: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String id="00";
		int filaConcesionaria=concesionarias.obtenerNumFilaTabla("ID", id);
		String[] valoresVar1=new String[labelsVariables1.length];
		for (int i=0; i<labelsVariables1.length; i++) {
			valoresVar1[i]=concesionarias.obtenerValorColumna(filaConcesionaria, labelsVariables1[i]);
		}
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		String codigo="99";
		int filaPlaza=plaza.obtenerNumFilaTabla("Código", codigo);
		String[] valoresVar2=new String[labelsVariables2.length];
		for (int i=0; i<labelsVariables2.length; i++) {
			valoresVar2[i]=plaza.obtenerValorColumna(filaPlaza, labelsVariables2[i]);
		}
		valoresVar2[2]=valoresVar2[2].substring(0, 4); //Nos quedamos sólo con el código del nodo de plaza, eliminamos su estado
		plaza.seleccionarFila(filaPlaza);
		plaza.editarVias();
		GestionViasProcess via=new GestionViasProcess();
		via.crear();
		CrearModificarViaProcess crearVia=new CrearModificarViaProcess();
		boolean analisis=crearVia.sintacticAnalysis(titulo,subtitulo, botones, encabezado1, labelsVariables1, valoresVar1,encabezado2,
				labelsVariables2, valoresVar2, encabezado3, labels3, encabezado4, labels4CriteriosBusqueda,labels4labelCriteriosCheckDropdown,
				labels4CriteriosCheckField);
		if (analisis) {
			logger.info("TC1 Crear Via: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Crear Via: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Crear Via: Resultado obtenido="+analisis);
		crearVia.volver();
		plaza.volver();
		concesionarias.volver();
		assertTrue(analisis);
	}

	
	@Test
	public void testCrearVia02() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Crear Via");
		logger.info("TC2 Crear Via: Crear una vía con un código inexistente en una plaza con nodo activado");
		logger.info("TC2 Crear Via: Resultado esperado: true (operación correcta, comprobación de aparición en tabla)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String id="01";
		int filaConcesionaria=concesionarias.obtenerNumFilaTabla("ID", id); 
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		String codigo="01";
		int filaPlaza=plaza.obtenerNumFilaTabla("Código", codigo);
		plaza.seleccionarFila(filaPlaza);
		plaza.editarVias();
		GestionViasProcess via=new GestionViasProcess();
		int numColumnasGestionVia=via.numColumnasTabla();
		via.crear();
		CrearModificarViaProcess crearVia=new CrearModificarViaProcess();
		String nodopPlaza=crearVia.leerCampoNodoPlaza();
		String[] datosEntrada= {"99","O: Oriente","MA: Manual"};
		crearVia.escribirCampoNumVia(datosEntrada[0]);
		crearVia.seleccionarCampoSentido(datosEntrada[1]);
		crearVia.seleccionarCampoTipoVia(datosEntrada[2]);
		crearVia.confirmar();
		crearVia.volver();  //Hay que hacer esto porque al confirmar aparece un mensaje de confirmación y no vuelve solo
		via.recargarTabla();
		String[] columnas=new String[3];
		columnas[0]=labels3[0];
		columnas[1]=labels3[1];
		columnas[2]=labels3[2];
		String[] valores=new String[columnas.length];
		valores[0]=datosEntrada[0];
		valores[1]=datosEntrada[1];
		valores[2]=datosEntrada[2];
		String[] resultado=new String[numColumnasGestionVia];
		resultado=via.obtenerFilaTabla(columnas, valores);
		String[] datosSalida=new String[numColumnasGestionVia];
		datosSalida[0]=datosEntrada[0];
		datosSalida[1]=datosEntrada[1];
		datosSalida[2]=datosEntrada[2];
		datosSalida[3]=nodopPlaza;
		datosSalida[4]=resultado[4]; //Nodo via -> OJO, no resuelto todavia
		boolean iguales=RutinasTestComunes.assertArrayEquals(datosSalida,resultado);
		if (!iguales) {
			LoginTest.majors++;
		}
		logger.info("TC2 Crear Via: Resultado obtenido:"+iguales);
		via.volver();
		plaza.volver();
		concesionarias.volver();
		assertTrue(iguales);
	}
	
	@Test
	public void testCrearVia03() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Crear Via");
		logger.info("TC3 Crear Via: Probar la validación de la existencia del campo Num Vía");
		logger.info("TC3 Crear Via: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String id="00";
		int filaConcesionaria=concesionarias.obtenerNumFilaTabla("ID", id);
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		String codigo="98";
		int filaPlaza=plaza.obtenerNumFilaTabla("Código", codigo);
		plaza.seleccionarFila(filaPlaza);
		plaza.editarVias();
		GestionViasProcess via=new GestionViasProcess();
		via.crear();
		CrearModificarViaProcess crearVia=new CrearModificarViaProcess();
		crearVia.confirmar();
		boolean hayErrorValidacion=crearVia.hayErrorValidacionRequeridoNumVia();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC3 Crear Via: Resultado obtenido:"+hayErrorValidacion);
		crearVia.volver();
		via.volver();
		plaza.volver();
		concesionarias.volver();
		assertTrue(hayErrorValidacion);
	}

	@Test
	public void testCrearVia04() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Crear Via");
		logger.info("TC4 Crear Via: Probar la validación del formato del campo Num Vía");
		logger.info("TC4 Crear Via: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String id="00";
		int filaConcesionaria=concesionarias.obtenerNumFilaTabla("ID", id);
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		String codigo="98";
		int filaPlaza=plaza.obtenerNumFilaTabla("Código", codigo);
		plaza.seleccionarFila(filaPlaza);
		plaza.editarVias();
		GestionViasProcess via=new GestionViasProcess();
		via.crear();
		CrearModificarViaProcess crearVia=new CrearModificarViaProcess();
		crearVia.escribirCampoNumVia("!!");
		crearVia.confirmar();
		boolean hayErrorValidacion=crearVia.hayErrorValidacionFormatoNumVia();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC4 Crear Via: Resultado obtenido:"+hayErrorValidacion);
		crearVia.volver();
		via.volver();
		plaza.volver();
		concesionarias.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearVia05() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Crear Via");
		logger.info("TC5 Crear Via: Probar la validación de la existencia del campo Nuevo nodo");
		logger.info("TC5 Crear Via: Resultado esperado: true (aparece el tooltip)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String id="00";
		int filaConcesionaria=concesionarias.obtenerNumFilaTabla("ID", id);
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		String codigo="98";
		int filaPlaza=plaza.obtenerNumFilaTabla("Código", codigo);
		plaza.seleccionarFila(filaPlaza);
		plaza.editarVias();
		GestionViasProcess via=new GestionViasProcess();
		via.crear();
		CrearModificarViaProcess crearVia=new CrearModificarViaProcess();
		crearVia.confirmar();
		boolean hayErrorValidacion=crearVia.hayErrorValidacionRequeridoNuevoNodo();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC5 Crear Via: Resultado obtenido:"+hayErrorValidacion);
		crearVia.volver();
		via.volver();
		plaza.volver();
		concesionarias.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearVia06() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6 Crear Via");
		logger.info("TC6 Crear Via: Probar la validación de la existencia del campo Dirección");
		logger.info("TC6 Crear Via: Resultado esperado: true (aparece el tooltip)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String id="00";
		int filaConcesionaria=concesionarias.obtenerNumFilaTabla("ID", id);
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		String codigo="98";
		int filaPlaza=plaza.obtenerNumFilaTabla("Código", codigo);
		plaza.seleccionarFila(filaPlaza);
		plaza.editarVias();
		GestionViasProcess via=new GestionViasProcess();
		via.crear();
		CrearModificarViaProcess crearVia=new CrearModificarViaProcess();
		crearVia.confirmar();
		boolean hayErrorValidacion=crearVia.hayErrorValidacionRequeridoDireccion();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC5 Crear Via: Resultado obtenido:"+hayErrorValidacion);
		crearVia.volver();
		via.volver();
		plaza.volver();
		concesionarias.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearVia07() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC7 Crear Via");
		logger.info("TC7 Crear Via: Probar la validación del formato del campo Nuevo nodo");
		logger.info("TC7 Crear Via: Resultado esperado: true (aparece el tooltip)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String id="00";
		int filaConcesionaria=concesionarias.obtenerNumFilaTabla("ID", id);
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		String codigo="98";
		int filaPlaza=plaza.obtenerNumFilaTabla("Código", codigo);
		plaza.seleccionarFila(filaPlaza);
		plaza.editarVias();
		GestionViasProcess via=new GestionViasProcess();
		via.crear();
		CrearModificarViaProcess crearVia=new CrearModificarViaProcess();
		crearVia.escribirCampoNuevoNodo("!");
		crearVia.confirmar();
		boolean hayErrorValidacion=crearVia.hayErrorValidacionRequeridoDireccion();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC7 Crear Via: Resultado obtenido:"+hayErrorValidacion);
		crearVia.volver();
		via.volver();
		plaza.volver();
		concesionarias.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearVia08() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC8 Crear Via");
		logger.info("TC8 Crear Via: Probar la validación del formato del campo Dirección");
		logger.info("TC8 Crear Via: Resultado esperado: true (aparece el tooltip)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String id="00";
		int filaConcesionaria=concesionarias.obtenerNumFilaTabla("ID", id);
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		String codigo="98";
		int filaPlaza=plaza.obtenerNumFilaTabla("Código", codigo);
		plaza.seleccionarFila(filaPlaza);
		plaza.editarVias();
		GestionViasProcess via=new GestionViasProcess();
		via.crear();
		CrearModificarViaProcess crearVia=new CrearModificarViaProcess();
		crearVia.escribirCampoDireccion("!");
		crearVia.confirmar();
		boolean hayErrorValidacion=crearVia.hayErrorValidacionRequeridoDireccion();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC8 Crear Via: Resultado obtenido:"+hayErrorValidacion);
		crearVia.volver();
		via.volver();
		plaza.volver();
		concesionarias.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test  //KO: Este testcase está pendiente de la incidencia abierta
	public void testCrearVia09() {  
		logger.info("-----------------------------------------------------------------");
		logger.info("TC9 Crear Via");
		logger.info("TC9 Crear Via: Crear una vía sin nodo asociado (primera vía de una plaza, o con un nodo desactivado)");
		logger.info("TC9 Crear Via: Resultado esperado: true (aparece un mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String id="00";
		int filaConcesionaria=concesionarias.obtenerNumFilaTabla("ID", id);
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		String codigo="98";
		int filaPlaza=plaza.obtenerNumFilaTabla("Código", codigo);
		plaza.seleccionarFila(filaPlaza);
		plaza.editarVias();
		GestionViasProcess via=new GestionViasProcess();
		via.crear();
		CrearModificarViaProcess crearVia=new CrearModificarViaProcess();
		String[] datosEntrada= {"01","O: Oriente","MA: Manual"};
		crearVia.escribirCampoNumVia(datosEntrada[0]);
		crearVia.seleccionarCampoSentido(datosEntrada[1]);
		crearVia.seleccionarCampoTipoVia(datosEntrada[2]);
		crearVia.confirmar();
		boolean hayMsgError=crearVia.hayMensajeError();
		boolean analisis=false;
		if (hayMsgError) {
			ErrorMessage errormsgobt=crearVia.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_codigo_existente);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC9 Crear Via: No ha aparecido el mensaje de error '"+msg_error_codigo_existente+"'");
			LoginTest.majors++;
		}
		logger.info("T9 Crear Via: Resultado obtenido:"+(hayMsgError && analisis));
		crearVia.volver();
		via.volver();
		plaza.volver();
		concesionarias.volver();
		assertTrue((hayMsgError && analisis));
	}
	
	@Test
	 public void testCrearVia10() {
			logger.info("-----------------------------------------------------------------");
			logger.info("TC10 Crear Via");
			logger.info("TC10 Crear Via: Crear una vía con un número, sentido y tipo existentes");
			logger.info("TC10 Crear Via: Resultado esperado: true (aparece un mensaje de error)");
			botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
			ConcesionariasProcess concesionarias=new ConcesionariasProcess();
			String id="01";
			int filaConcesionaria=concesionarias.obtenerNumFilaTabla("ID", id);
			concesionarias.seleccionarFila(filaConcesionaria);
			concesionarias.editarPlazas();
			GestionPlazaProcess plaza=new GestionPlazaProcess();
			String codigo="01";
			int filaPlaza=plaza.obtenerNumFilaTabla("Código", codigo);
			plaza.seleccionarFila(filaPlaza);
			plaza.editarVias();
			GestionViasProcess via=new GestionViasProcess();
			via.crear();
			CrearModificarViaProcess crearVia=new CrearModificarViaProcess();
			String[] datosEntrada= {"01","P: Poniente","MI: Mixta"};
			crearVia.escribirCampoNumVia(datosEntrada[0]);
			crearVia.seleccionarCampoSentido(datosEntrada[1]);
			crearVia.seleccionarCampoTipoVia(datosEntrada[2]);
			crearVia.confirmar();
			boolean hayMsgError=crearVia.hayMensajeError();
			boolean analisis=false;
			if (hayMsgError) {
				ErrorMessage errormsgobt=crearVia.mensajeError();
				analisis=errormsgobt.sintacticAnalysis(msg_error_codigo_existente);
				if (!analisis) {
					LoginTest.minors++;
				}
			}
			else {
				logger.error("TC10 Crear Via: No ha aparecido el mensaje de error '"+msg_error_codigo_existente+"'");
				LoginTest.majors++;
			}
			logger.info("TC10 Crear Via: Resultado obtenido:"+(hayMsgError && analisis));
			crearVia.volver();
			via.volver();
			plaza.volver();
			concesionarias.volver();
			assertTrue((hayMsgError && analisis));
		}
}
