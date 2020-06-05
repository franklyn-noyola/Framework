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

public class ModificarPlazaTest {

	final static String titulo="Modificación Plaza"; 
	final static String subtitulo="Configuración sistema   -   Configuración de peaje   -   Concesionarias, Plazas, Vías   -   Editar Plazas   -   Modificar"; 
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
	final static String msg_error_nodo_existente="Ya existe una plaza con el código indicado";
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(ModificarPlazaTest.class);
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
	public void testModificarPlaza1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Modificar Plaza");
		logger.info("TC1 Modificar Plaza: Probar el análisis sintáctico correcto");
		logger.info("TC1 Modificar Plaza: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		int filaConcesionaria=1;
		String[] valoresVar=new String[labelsVariables.length];
		for (int i=0; i<labelsVariables.length; i++) {
			valoresVar[i]=concesionarias.obtenerValorColumna(filaConcesionaria, labelsVariables[i]);
		}
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		int filaPlaza=1;
		plaza.seleccionarFila(filaPlaza);
		plaza.modificar();
		CrearModificarPlazaProcess modificarPlaza=new CrearModificarPlazaProcess();
		boolean analisis=modificarPlaza.sintacticAnalysis(titulo,subtitulo, botones, encabezado1, labelsVariables,
				valoresVar,encabezado2,labels2,encabezado3, labelscheck, labelscheckdropdown, labelscheckfield);
		if (analisis) {
			logger.info("TC1 Modificar Plaza: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Modificar Plaza: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Modificar Plaza: Resultado obtenido="+analisis);
		modificarPlaza.volver();
		plaza.volver();
		concesionarias.volver();
		assertTrue(analisis);
	}

	
	@Test
	public void testModificarPlaza2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Modificar Plaza");
		logger.info("TC2 Modificar Plaza: Modificar una plaza con un nodo existente");
		logger.info("TC2 Modificar Plaza: Resultado esperado: true (operación correcta, comprobación de aparición en tabla)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		int filaConcesionaria=1;
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		int numColumnasGestionPlaza=plaza.numColumnasTabla();
		int filaPlaza=1;
		plaza.seleccionarFila(filaPlaza);
		plaza.modificar();
		CrearModificarPlazaProcess modificarPlaza=new CrearModificarPlazaProcess();
		String[] datosEntrada= {"98","Plaza98","Carretera98","Km98"};
		datosEntrada[0]=modificarPlaza.leerCampoCodigoPlaza(); //El código plaza es readonly
		modificarPlaza.escribirCampoNombrePlaza(datosEntrada[1]);
		modificarPlaza.escribirCampoCarretera(datosEntrada[2]);
		modificarPlaza.escribirCampoKm(datosEntrada[3]);
		String nodo=modificarPlaza.leerCampoNodoExistente();
		modificarPlaza.confirmar();
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
		datosSalida[2]=nodo;
		datosSalida[3]=datosEntrada[2];
		datosSalida[4]=datosEntrada[3];
		boolean iguales=RutinasTestComunes.assertArrayEquals(datosSalida,resultado);
		if (!iguales) {
			LoginTest.majors++;
		}
		logger.info("TC2 Modificar Plaza: Resultado obtenido:"+iguales);
		plaza.volver();
		concesionarias.volver();
		assertTrue(iguales);
	}
	
	@Test
	public void testModificarPlaza3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Modificar Plaza");
		logger.info("TC3 Modificar Plaza: Modificar una plaza con un nuevo nodo inexistente (el asignado por el usuario)");
		logger.info("TC3 Modificar Plaza: Resultado esperado: true (operación correcta, comprobación de aparición en tabla)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		int filaConcesionaria=1;
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		int numColumnasGestionPlaza=plaza.numColumnasTabla();
		int filaPlaza=1;
		plaza.seleccionarFila(filaPlaza);
		plaza.modificar();
		CrearModificarPlazaProcess modificarPlaza=new CrearModificarPlazaProcess();
		String[] datosEntrada= {"98","Plaza98","Carretera98","Km98","3003","2.4.6.12"};
		datosEntrada[0]=modificarPlaza.leerCampoCodigoPlaza(); //El código plaza es readonly
		modificarPlaza.escribirCampoNombrePlaza(datosEntrada[1]);
		modificarPlaza.escribirCampoCarretera(datosEntrada[2]);
		modificarPlaza.escribirCampoKm(datosEntrada[3]);
		modificarPlaza.clicarNuevoNodo();
		modificarPlaza.escribirCampoNuevoNodo(datosEntrada[4]);
		modificarPlaza.escribirCampoDireccion(datosEntrada[5]);
		String nodo=modificarPlaza.leerCampoNuevoNodo();
		modificarPlaza.confirmar();
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
		datosSalida[2]=nodo;
		datosSalida[3]=datosEntrada[2];
		datosSalida[4]=datosEntrada[3];
		boolean iguales=RutinasTestComunes.assertArrayEquals(datosSalida,resultado);
		if (!iguales) {
			LoginTest.majors++;
		}
		logger.info("TC3 Modificar Plaza: Resultado obtenido:"+iguales);
		plaza.volver();
		concesionarias.volver();
		assertTrue(iguales);
	}
	
	@Test
	public void testModificarPlaza4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Modificar Plaza");
		logger.info("TC4 Modificar Plaza: Modificar una plaza con un nuevo nodo existente");
		logger.info("TC4 Modificar Plaza: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		int filaConcesionaria=1;
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		int filaPlaza=1;
		plaza.seleccionarFila(filaPlaza);
		plaza.modificar();
		CrearModificarPlazaProcess modificarPlaza=new CrearModificarPlazaProcess();
		String[] datosEntrada= {"98","Plaza98","Carretera98","Km98","0011","2.4.6.9"};
		datosEntrada[0]=modificarPlaza.leerCampoCodigoPlaza(); //El código plaza es readonly
		modificarPlaza.escribirCampoNombrePlaza(datosEntrada[1]);
		modificarPlaza.escribirCampoCarretera(datosEntrada[2]);
		modificarPlaza.escribirCampoKm(datosEntrada[3]);
		modificarPlaza.clicarNuevoNodo();
		modificarPlaza.borrarCampoNuevoNodo();
		modificarPlaza.escribirCampoNuevoNodo(datosEntrada[4]);
		modificarPlaza.escribirCampoDireccion(datosEntrada[5]);
		modificarPlaza.confirmar();
		boolean hayMsgError=modificarPlaza.hayMensajeError();
		boolean analisis=false;
		if (hayMsgError) {
			ErrorMessage errormsgobt=modificarPlaza.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_nodo_existente);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC4 Modificar Plaza: No ha aparecido el mensaje de error '"+msg_error_nodo_existente+"'");
			LoginTest.majors++;
		}
		logger.info("TC4 Modificar Plaza: Resultado obtenido:"+(hayMsgError && analisis));
		modificarPlaza.volver();
		plaza.volver();
		concesionarias.volver();
		assertTrue(hayMsgError && analisis);
	}
	

	@Test
	public void testModificarPlaza5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Modificar Plaza");
		logger.info("TC5 Modificar Plaza: Probar la validación de la existencia del campo Nombre");
		logger.info("TC5 Modificar Plaza: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		int filaConcesionaria=1;
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		int filaPlaza=1;
		plaza.seleccionarFila(filaPlaza);
		plaza.modificar();
		CrearModificarPlazaProcess modificarPlaza=new CrearModificarPlazaProcess();
		modificarPlaza.borrarCampoNombrePlaza();
		modificarPlaza.confirmar();
		boolean hayErrorValidacion=modificarPlaza.hayErrorValidacionRequeridoNombre();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC5 Modificar Plaza: Resultado obtenido:"+hayErrorValidacion);
		modificarPlaza.volver();
		plaza.volver();
		concesionarias.volver();
		assertTrue(hayErrorValidacion);
	}

	@Test
	public void testModificarPlaza6() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6 Modificar Plaza");
		logger.info("TC6 Modificar Plaza: Probar la validación de la existencia del campo Nuevo Nodo");
		logger.info("TC6 Modificar Plaza: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		int filaConcesionaria=1;
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		int filaPlaza=1;
		plaza.seleccionarFila(filaPlaza);
		plaza.modificar();
		CrearModificarPlazaProcess modificarPlaza=new CrearModificarPlazaProcess();
		modificarPlaza.clicarNuevoNodo();
		modificarPlaza.confirmar();
		boolean hayErrorValidacion=modificarPlaza.hayErrorValidacionRequeridoNuevoNodo();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC6 Modificar Plaza: Resultado obtenido:"+hayErrorValidacion);
		modificarPlaza.volver();
		concesionarias.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testModificarPlaza7() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC7 Modificar Plaza");
		logger.info("TC7 Modificar Plaza: Probar la validación de la existencia del campo Dirección");
		logger.info("TC7 Modificar Plaza: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		int filaConcesionaria=1;
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		int filaPlaza=1;
		plaza.seleccionarFila(filaPlaza);
		plaza.modificar();
		CrearModificarPlazaProcess modificarPlaza=new CrearModificarPlazaProcess();
		modificarPlaza.clicarNuevoNodo();
		modificarPlaza.confirmar();
		boolean hayErrorValidacion=modificarPlaza.hayErrorValidacionRequeridoDireccion();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC7 Modificar Plaza: Resultado obtenido:"+hayErrorValidacion);
		modificarPlaza.volver();
		concesionarias.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testModificarPlaza8() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC8 Modificar Plaza");
		logger.info("TC8 Modificar Plaza: Probar la validación del formato del campo Dirección");
		logger.info("TC8 Modificar Plaza: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		int filaConcesionaria=1;
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		int filaPlaza=1;
		plaza.seleccionarFila(filaPlaza);
		plaza.modificar();
		CrearModificarPlazaProcess modificarPlaza=new CrearModificarPlazaProcess();
		modificarPlaza.clicarNuevoNodo();
		modificarPlaza.escribirCampoDireccion("!");
		modificarPlaza.confirmar();
		boolean hayErrorValidacion=modificarPlaza.hayErrorValidacionFormatoDireccion();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC8 Modificar Plaza: Resultado obtenido:"+hayErrorValidacion);
		modificarPlaza.volver();
		concesionarias.volver();
		assertTrue(hayErrorValidacion);
	}
	
}
