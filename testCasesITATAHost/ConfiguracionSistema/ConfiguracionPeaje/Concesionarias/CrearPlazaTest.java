package testCasesITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.ConcesionariasProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.CrearModificarPlazaProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.GestionPlazaProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.ErrorMessage;

public class CrearPlazaTest {

	final static String titulo="Creación de Plaza"; 
	final static String subtitulo="Configuración sistema   -   Configuración de peaje   -   Concesionarias, Plazas, Vías   -   Editar Plazas   -   Crear"; 
	final static String[] botones= {"Confirmar","Volver"};
	final static String encabezado1="Concesionaria";
	final static String[] labelsVariables={"ID","Nombre"};
	final static String encabezado2="Plaza";
	final static String[] labels2={"Código","Nombre","Carretera","Km"};
	final static String encabezado3="Nodo Plaza";
	final static String[] labelscheck={"Dirección"};
	final static String[] labelscheckdropdown={"Nodo existente"};
	final static String[] labelscheckfield={"Nuevo nodo"};
	final static String msg_error_codigo_existente="Ya existe una plaza con el código indicado";
	final static String msg_error_nodo_existente="Existe un nodo con el código indicado";
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(CrearPlazaTest.class);
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
	public void testCrearPlaza01() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Crear Plaza");
		logger.info("TC1 Crear Plaza: Probar el análisis sintáctico correcto");
		logger.info("TC1 Crear Plaza: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String id="00";
		int filaConcesionaria=concesionarias.obtenerNumFilaTabla("ID", id);
		String[] valoresVar=new String[labelsVariables.length];
		for (int i=0; i<labelsVariables.length; i++) {
			valoresVar[i]=concesionarias.obtenerValorColumna(filaConcesionaria, labelsVariables[i]);
		}
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		plaza.crear();
		CrearModificarPlazaProcess crearPlaza=new CrearModificarPlazaProcess();
		boolean analisis=crearPlaza.sintacticAnalysis(titulo,subtitulo, botones, encabezado1, labelsVariables,valoresVar,
				encabezado2,labels2,encabezado3, labelscheck, labelscheckdropdown, labelscheckfield);
		if (analisis) {
			logger.info("TC1 Crear Plaza: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Crear Plaza: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Crear Plaza: Resultado obtenido="+analisis);
		crearPlaza.volver();
		plaza.volver();
		concesionarias.volver();
		assertTrue(analisis);
	}

	
	@Test
	public void testCrearPlaza02() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Crear Plaza");
		logger.info("TC2 Crear Plaza: Crear una plaza con un código inexistente y nodo existente");
		logger.info("TC2 Crear Plaza: Resultado esperado: true (operación correcta, comprobación de aparición en tabla)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String id="00";
		int filaConcesionaria=concesionarias.obtenerNumFilaTabla("ID", id);
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		int numColumnasGestionPlaza=plaza.numColumnasTabla();
		plaza.crear();
		CrearModificarPlazaProcess crearPlaza=new CrearModificarPlazaProcess();
		String[] datosEntrada= {"99","Plaza99","Carretera99","Km99"};
		crearPlaza.escribirCampoCodigoPlaza(datosEntrada[0]);
		crearPlaza.escribirCampoNombrePlaza(datosEntrada[1]);
		crearPlaza.escribirCampoCarretera(datosEntrada[2]);
		crearPlaza.escribirCampoKm(datosEntrada[3]);
		String nodoexistente=crearPlaza.leerCampoNodoExistente(); // El nodo existente es el que por defecto aparece en el combo
		crearPlaza.confirmar();
		plaza.recargarTabla();
		String[] resultado=new String[numColumnasGestionPlaza];
		String[] columnas=new String[1];
		columnas[0]="Código";
		String[] valores=new String[1];
		valores[0]=datosEntrada[0];
		resultado=plaza.obtenerFilaTabla(columnas, valores);
		String[] datosSalida=new String[numColumnasGestionPlaza];
		datosSalida[0]=datosEntrada[0];
		datosSalida[1]=datosEntrada[1];
		datosSalida[2]=nodoexistente;
		datosSalida[3]=datosEntrada[2];
		datosSalida[4]=datosEntrada[3];
		boolean iguales=RutinasTestComunes.assertArrayEquals(datosSalida,resultado);
		if (!iguales) {
			LoginTest.majors++;
		}
		logger.info("TC2 Crear Plaza: Resultado obtenido:"+iguales);
		plaza.volver();
		concesionarias.volver();
		assertTrue(iguales);
	}
	
	@Test
	public void testCrearPlaza03() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Crear Plaza");
		logger.info("TC3 Crear Plaza: Crear una plaza con un código inexistente y nuevo nodo inexistente");
		logger.info("TC3 Crear Plaza: Resultado esperado: true (operación correcta, comprobación de aparición en tabla)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String id="00";
		int filaConcesionaria=concesionarias.obtenerNumFilaTabla("ID", id);
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		int numColumnasGestionPlaza=plaza.numColumnasTabla();
		plaza.crear();
		CrearModificarPlazaProcess crearPlaza=new CrearModificarPlazaProcess();
		String[] datosEntrada= {"98","Plaza98","Carretera98","Km98","3009","1.3.5.7"};
		crearPlaza.escribirCampoCodigoPlaza(datosEntrada[0]);
		crearPlaza.escribirCampoNombrePlaza(datosEntrada[1]);
		crearPlaza.escribirCampoCarretera(datosEntrada[2]);
		crearPlaza.escribirCampoKm(datosEntrada[3]);
		crearPlaza.clicarNuevoNodo();
		crearPlaza.borrarCampoNuevoNodo();
		crearPlaza.escribirCampoNuevoNodo(datosEntrada[4]);
		crearPlaza.escribirCampoDireccion(datosEntrada[5]);
		crearPlaza.confirmar();
		plaza.recargarTabla();
		String[] resultado=new String[numColumnasGestionPlaza];
		String[] columnas=new String[1];
		columnas[0]="Código";
		String[] valores=new String[1];
		valores[0]=datosEntrada[0];
		resultado=plaza.obtenerFilaTabla(columnas, valores);
		String[] datosSalida=new String[numColumnasGestionPlaza];
		datosSalida[0]=datosEntrada[0];
		datosSalida[1]=datosEntrada[1];
		datosSalida[2]=datosEntrada[4]; 
		datosSalida[3]=datosEntrada[2];
		datosSalida[4]=datosEntrada[3];
		boolean iguales=RutinasTestComunes.assertArrayEquals(datosSalida,resultado);
		if (!iguales) {
			LoginTest.majors++;
		}
		logger.info("TC3 Crear Plaza: Resultado obtenido:"+iguales);
		plaza.volver();
		concesionarias.volver();
		assertTrue(iguales);
	}
	
	@Test
	public void testCrearPlaza04() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Crear Plaza");
		logger.info("TC4 Crear Plaza: Crear una plaza con un código inexistente y nuevo nodo existente");
		logger.info("TC4 Crear Plaza: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String id="00";
		int filaConcesionaria=concesionarias.obtenerNumFilaTabla("ID", id);
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		plaza.crear();
		CrearModificarPlazaProcess crearPlaza=new CrearModificarPlazaProcess();
		String[] datosEntrada= {"97","Plaza97","Carretera97","Km97","0011","2.3.5.7"};
		crearPlaza.escribirCampoCodigoPlaza(datosEntrada[0]);
		crearPlaza.escribirCampoNombrePlaza(datosEntrada[1]);
		crearPlaza.escribirCampoCarretera(datosEntrada[2]);
		crearPlaza.escribirCampoKm(datosEntrada[3]);
		crearPlaza.clicarNuevoNodo();
		crearPlaza.borrarCampoNuevoNodo();
		crearPlaza.escribirCampoNuevoNodo(datosEntrada[4]);
		crearPlaza.escribirCampoDireccion(datosEntrada[5]);
		crearPlaza.confirmar();
		boolean hayMsgError=crearPlaza.hayMensajeError();
		boolean analisis=false;
		if (hayMsgError) {
			ErrorMessage errormsgobt=crearPlaza.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_nodo_existente);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC4 Crear Plaza: No ha aparecido el mensaje de error '"+msg_error_nodo_existente+"'");
			LoginTest.majors++;
		}
		logger.info("TC4 Crear Plaza: Resultado obtenido:"+(hayMsgError && analisis));
		crearPlaza.volver();
		concesionarias.volver();
		assertTrue(hayMsgError && analisis);
	}
	
	
	@Test
	public void testCrearPlaza05() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Crear Plaza");
		logger.info("TC5 Crear Plaza: Probar la validación de la existencia del campo Código");
		logger.info("TC5 Crear Plaza: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String id="00";
		int filaConcesionaria=concesionarias.obtenerNumFilaTabla("ID", id);
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		plaza.crear();
		CrearModificarPlazaProcess crearPlaza=new CrearModificarPlazaProcess();
		crearPlaza.confirmar();
		boolean hayErrorValidacion=crearPlaza.hayErrorValidacionFormatoCodigo();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC5 Crear Plaza: Resultado obtenido:"+hayErrorValidacion);
		crearPlaza.volver();
		concesionarias.volver();
		assertTrue(hayErrorValidacion);
	}

	@Test
	public void testCrearPlaza06() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6 Crear Plaza");
		logger.info("TC6 Crear Plaza: Probar la validación del formato del campo Código");
		logger.info("TC6 Crear Plaza: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String id="00";
		int filaConcesionaria=concesionarias.obtenerNumFilaTabla("ID", id);
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		plaza.crear();
		CrearModificarPlazaProcess crearPlaza=new CrearModificarPlazaProcess();
		crearPlaza.escribirCampoCodigoPlaza("!");
		crearPlaza.confirmar();
		boolean hayErrorValidacion=crearPlaza.hayErrorValidacionFormatoCodigo();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC6 Crear Plaza: Resultado obtenido:"+hayErrorValidacion);
		crearPlaza.volver();
		concesionarias.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearPlaza07() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC7 Crear Plaza");
		logger.info("TC7 Crear Plaza: Probar la validación de la existencia del campo Nombre");
		logger.info("TC7 Crear Plaza: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String id="00";
		int filaConcesionaria=concesionarias.obtenerNumFilaTabla("ID", id);
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		plaza.crear();
		CrearModificarPlazaProcess crearPlaza=new CrearModificarPlazaProcess();
		crearPlaza.confirmar();
		boolean hayErrorValidacion=crearPlaza.hayErrorValidacionRequeridoNombre();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC7 Crear Plaza: Resultado obtenido:"+hayErrorValidacion);
		crearPlaza.volver();
		concesionarias.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearPlaza08() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC8 Crear Plaza");
		logger.info("TC8 Crear Plaza: Probar la validación de la existencia del campo Nuevo Nodo");
		logger.info("TC8 Crear Plaza: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String id="00";
		int filaConcesionaria=concesionarias.obtenerNumFilaTabla("ID", id);
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		plaza.crear();
		CrearModificarPlazaProcess crearPlaza=new CrearModificarPlazaProcess();
		crearPlaza.clicarNuevoNodo();
		crearPlaza.confirmar();
		boolean hayErrorValidacion=crearPlaza.hayErrorValidacionRequeridoNuevoNodo();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC8 Crear Plaza: Resultado obtenido:"+hayErrorValidacion);
		crearPlaza.volver();
		concesionarias.volver();
		assertTrue(hayErrorValidacion);
	}
		
	@Test
	public void testCrearPlaza09() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC9 Crear Plaza");
		logger.info("TC9 Crear Plaza: Probar la validación de la existencia del campo Dirección");
		logger.info("TC9 Crear Plaza: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String id="00";
		int filaConcesionaria=concesionarias.obtenerNumFilaTabla("ID", id);
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		plaza.crear();
		CrearModificarPlazaProcess crearPlaza=new CrearModificarPlazaProcess();
		crearPlaza.clicarNuevoNodo();
		crearPlaza.confirmar();
		boolean hayErrorValidacion=crearPlaza.hayErrorValidacionRequeridoDireccion();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC9 Crear Plaza: Resultado obtenido:"+hayErrorValidacion);
		crearPlaza.volver();
		concesionarias.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearPlaza10() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC10 Crear Plaza");
		logger.info("TC10 Crear Plaza: Probar la validación del formato del campo Nuevo Nodo");
		logger.info("TC10 Crear Plaza: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String id="00";
		int filaConcesionaria=concesionarias.obtenerNumFilaTabla("ID", id);
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		plaza.crear();
		CrearModificarPlazaProcess crearPlaza=new CrearModificarPlazaProcess();
		crearPlaza.clicarNuevoNodo();
		crearPlaza.escribirCampoNuevoNodo("!");
		crearPlaza.confirmar();
		boolean hayErrorValidacion=crearPlaza.hayErrorValidacionFormatoNuevoNodo();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC10 Crear Plaza: Resultado obtenido:"+hayErrorValidacion);
		crearPlaza.volver();
		concesionarias.volver();
		assertTrue(hayErrorValidacion);
	}
		
	@Test
	public void testCrearPlaza11() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC11 Crear Plaza");
		logger.info("TC11 Crear Plaza: Probar la validación del formato del campo Dirección");
		logger.info("TC11 Crear Plaza: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String id="00";
		int filaConcesionaria=concesionarias.obtenerNumFilaTabla("ID", id);
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		plaza.crear();
		CrearModificarPlazaProcess crearPlaza=new CrearModificarPlazaProcess();
		crearPlaza.clicarNuevoNodo();
		crearPlaza.escribirCampoDireccion("!");
		crearPlaza.confirmar();
		boolean hayErrorValidacion=crearPlaza.hayErrorValidacionFormatoDireccion();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC11 Crear Plaza: Resultado obtenido:"+hayErrorValidacion);
		crearPlaza.volver();
		concesionarias.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearPlaza12() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC12 Crear Plaza");
		logger.info("TC12 Crear Plaza: Crear una plaza con un código existente");
		logger.info("TC12 Crear Plaza: Resultado esperado: true (aparece un mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		String id="00";
		int filaConcesionaria=concesionarias.obtenerNumFilaTabla("ID", id);
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		plaza.crear();
		CrearModificarPlazaProcess crearPlaza=new CrearModificarPlazaProcess();
		String[] datosEntrada= {"99","Plaza99","Carretera99","Km99"};
		crearPlaza.escribirCampoCodigoPlaza(datosEntrada[0]);
		crearPlaza.escribirCampoNombrePlaza(datosEntrada[1]);
		crearPlaza.escribirCampoCarretera(datosEntrada[2]);
		crearPlaza.escribirCampoKm(datosEntrada[3]);
		crearPlaza.confirmar();
		boolean hayMsgError=crearPlaza.hayMensajeError();
		boolean analisis=false;
		if (hayMsgError) {
			ErrorMessage errormsgobt=crearPlaza.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_codigo_existente);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC12 Crear Plaza: No ha aparecido el mensaje de error '"+msg_error_codigo_existente+"'");
			LoginTest.majors++;
		}
		logger.info("TC12 Crear Plaza: Resultado obtenido:"+(hayMsgError && analisis));
		crearPlaza.volver();
		concesionarias.volver();
		assertTrue((hayMsgError && analisis));
	}
	
}
