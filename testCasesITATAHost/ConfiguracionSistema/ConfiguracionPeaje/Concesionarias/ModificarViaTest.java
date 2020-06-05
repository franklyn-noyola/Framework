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

public class ModificarViaTest {

	final static String titulo="Modificación de vía"; 
	final static String subtitulo="Configuración sistema   -   Configuración de peaje   -   Concesionarias, Plazas, Vías   -   Editar Plazas   -   Editar Vías   -   Modificar"; 
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
		logger = Logger.getLogger(ModificarViaTest.class);
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
	public void testModificarVia1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Modificar Via");
		logger.info("TC1 Modificar Via: Probar el análisis sintáctico correcto");
		logger.info("TC1 Modificar Via: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		int filaConcesionaria=2;
		String[] valoresVar1=new String[labelsVariables1.length];
		for (int i=0; i<labelsVariables1.length; i++) {
			valoresVar1[i]=concesionarias.obtenerValorColumna(filaConcesionaria, labelsVariables1[i]);
		}
		concesionarias.seleccionarFila(filaConcesionaria);
		concesionarias.editarPlazas();
		GestionPlazaProcess plaza=new GestionPlazaProcess();
		int filaPlaza=1;
		String[] valoresVar2=new String[labelsVariables2.length];
		for (int i=0; i<labelsVariables2.length; i++) {
			valoresVar2[i]=plaza.obtenerValorColumna(filaPlaza, labelsVariables2[i]);
		}
		valoresVar2[2]=valoresVar2[2].substring(0, 4); //Nos quedamos sólo con el código del nodo de plaza, eliminamos su estado
		plaza.seleccionarFila(filaPlaza);
		plaza.editarVias();
		GestionViasProcess via=new GestionViasProcess();
		int filaVia=1;
		via.seleccionarFila(filaVia);
		via.modificar();
		CrearModificarViaProcess modificarVia=new CrearModificarViaProcess();
		boolean analisis=modificarVia.sintacticAnalysis(titulo,subtitulo, botones, encabezado1, labelsVariables1, valoresVar1,encabezado2,
				labelsVariables2, valoresVar2, encabezado3, labels3, encabezado4, labels4CriteriosBusqueda,labels4labelCriteriosCheckDropdown,
				labels4CriteriosCheckField);
		if (analisis) {
			logger.info("TC1 Crear Modificar: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Crear Modificar: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Crear Modificar: Resultado obtenido="+analisis);
		modificarVia.volver();
		via.volver();
		plaza.volver();
		concesionarias.volver();
		assertTrue(analisis);
	}


	/*
	//@Test  //Aqui hay que probar la modificación del nodo dela via (pendiente)
	public void testModificarVia2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Modificar Via");
		logger.info("TC2 Modificar Via: Modificar una vía con un código inexistente");
		logger.info("TC2 Modificar Via: Resultado esperado: true (operación correcta, comprobación de aparición en tabla)");
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
		modificarPlaza.confirmar();
		modificarVia.volver(); //Esto hay que hacerlo asi por un error de usabilidad (aparece el mensaje "Via x creada con éxito" cuando no se está creando.
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
		datosSalida[2]=resultado[2]; //OJO, esto hay que cambiarlo por lo que se obtenga del Nodo Plaza en la pantalla anterior
		datosSalida[3]=datosEntrada[2];
		datosSalida[4]=datosEntrada[3];
		boolean iguales=RutinasTestComunes.assertArrayEquals(datosSalida,resultado);
		if (!iguales) {
			LoginTest.majors++;
		}
		logger.info("TC2 Modificar Via: Resultado obtenido:"+iguales);
		plaza.volver();
		concesionarias.volver();
		assertTrue(iguales);
	}*/

}
