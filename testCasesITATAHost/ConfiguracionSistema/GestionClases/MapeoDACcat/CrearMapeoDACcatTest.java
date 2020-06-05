package testCasesITATAHost.ConfiguracionSistema.GestionClases.MapeoDACcat;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.GestionClases.MapeoDACcat.CrearModificarMapeoDACcatProcess;
import procesosITATAHost.ConfiguracionSistema.GestionClases.MapeoDACcat.MapeoDACcatProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.ErrorMessage;

public class CrearMapeoDACcatTest {

	final static String titulo="Mapeo DAC-Cat: Creación"; 
	final static String subtitulo="Configuración sistema   -   Gestión de clases   -   Mapeo DAC-Cat   -   Crear"; 
	final static String[] botones= {"Confirmar","Volver"};
	final static String encabezado="Información de mapeo";
	final static String[] labels= {"Índice","Min Rueda doble","Max Rueda doble","Num. Mí. Ejes","Num. Máx. Ejes",
									"Distancia Mín. 1º y 2º eje","Distancia Máx. 1º y 2º eje","Altura mínima",
									"Altura máxima","Anchura mínima","Anchura máxima","Concesionaria","Categoría"};
	final static String[][] labelsradiobutton= {{"Bus","Sí","No","indiferente"},{"Remolques","Sí","No","indiferente"}};
	final static String msg_error_duplicidad="Un mapeo con el índice especificado ya existe.";
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(CrearMapeoDACcatTest.class);
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
	public void testCrearMapeoDACcat1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Crear Mapeo DACcat");
		logger.info("TC1 Crear Mapeo DACcat: Probar el análisis sintáctico correcto");
		logger.info("TC1 Crear Mapeo DACcat: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		mapeos.crear();
		CrearModificarMapeoDACcatProcess crearMapeo=new CrearModificarMapeoDACcatProcess();
		boolean analisis=crearMapeo.sintacticAnalysis(titulo,subtitulo, botones, encabezado, labels, labelsradiobutton);
		if (analisis) {
			logger.info("TC1 Crear Mapeo DACcat: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Crear Mapeo DACcat: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Crear Mapeo DACcat: Resultado obtenido="+analisis);
		crearMapeo.volver();
		mapeos.volver();
		assertTrue(analisis);
	}
	
	@Test
	public void testCrearMapeoDACcat2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Crear Mapeo DACcat");
		logger.info("TC2 Crear Mapeo DACcat: Crear un mapeo de indice inexistente");
		logger.info("TC2 Crear Mapeo DACcat: Resultado esperado: true (operación correcta, comprobación de aparición en tabla)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		String[] resultado=new String[mapeos.numColumnasTabla()];
		String[] datosSalida=new String[mapeos.numColumnasTabla()];
		mapeos.crear();
		CrearModificarMapeoDACcatProcess crearMapeo=new CrearModificarMapeoDACcatProcess();
		String[] datosEntrada={"99","00: Descripcion bb","11: Descripcion aa","1","2","3","4","5","6","7","8","9","10"};
		crearMapeo.escribirCampoIndice(datosEntrada[0]);
		crearMapeo.seleccionarCampoConcesionaria(datosEntrada[1]);
		crearMapeo.seleccionarCampoCategoria(datosEntrada[2]);
		crearMapeo.escribirCampoMinRuedaDoble(datosEntrada[3]);
		crearMapeo.escribirCampoMaxRuedaDoble(datosEntrada[4]);
		crearMapeo.escribirCampoNumMinEjes(datosEntrada[5]);
		crearMapeo.escribirCampoNumMaxEjes(datosEntrada[6]);
		crearMapeo.escribirCampoDistanciaMinEjes(datosEntrada[7]);
		crearMapeo.escribirCampoDistanciaMaxEjes(datosEntrada[8]);
		crearMapeo.escribirCampoAlturaMin(datosEntrada[9]);
		crearMapeo.escribirCampoAlturaMax(datosEntrada[10]);
		crearMapeo.escribirCampoAnchuraMin(datosEntrada[11]);
		crearMapeo.escribirCampoAnchuraMax(datosEntrada[12]);
		crearMapeo.clicarRadioButtonBusSi();
		datosSalida[0]=crearMapeo.leerCampoIndice();
		datosSalida[1]=crearMapeo.leerCampoConcesionaria();
		if (datosSalida[1].equals("Todos")) {
			datosSalida[1]="*";
		}
		datosSalida[2]=crearMapeo.leerCampoMinRuedaDoble();
		datosSalida[3]=crearMapeo.leerCampoMaxRuedaDoble();
		String label=crearMapeo.leerRadioButtonBus();
		switch (label) {
			case "Sí":
				datosSalida[4]="S";
				break;
			case "No":
				datosSalida[4]="N";
				break;
			case "indiferente":
				datosSalida[4]="-";
				break;
		}
		datosSalida[5]=crearMapeo.leerCampoNumMinEjes();
		datosSalida[6]=crearMapeo.leerCampoNumMaxEjes();
		datosSalida[7]=crearMapeo.leerCampoDistanciaMinEjes();
		datosSalida[8]=crearMapeo.leerCampoDistanciaMaxEjes();
		label=crearMapeo.leerRadioButtonRemolques();
		switch (label) {
			case "Sí":
				datosSalida[9]="S";
				break;
			case "No":
				datosSalida[9]="N";
				break;
			case "indiferente":
				datosSalida[9]="-";
				break;
		}

		datosSalida[10]=crearMapeo.leerCampoAlturaMin();
		datosSalida[11]=crearMapeo.leerCampoAlturaMax();
		datosSalida[12]=crearMapeo.leerCampAnchuraMin();
		datosSalida[13]=crearMapeo.leerCampAnchuraMax();
		String categoria=crearMapeo.leerCampoCategoria();
		datosSalida[14]=categoria.substring(0, categoria.indexOf(":"));
		for (int i=2; i<=13; i++) {
			if (datosSalida[i].equals("")) {
				datosSalida[i]="-";
			}
		}
		crearMapeo.confirmar();
		mapeos.recargarTabla();
		String[] columnas=new String[1];
		columnas[0]=labels[0];
		String[] valores=new String[1];
		valores[0]=datosEntrada[0];
		resultado=mapeos.obtenerFilaTabla(columnas, valores);
		boolean iguales=RutinasTestComunes.assertArrayEquals(datosSalida,resultado);
		if (!iguales) {
			LoginTest.majors++;
		}
		logger.info("TC2 Crear Mapeo DACcat: Resultado obtenido:"+iguales);
		mapeos.volver();
		assertTrue(iguales);
	}
	
	
	@Test
	public void testCrearMapeoDACcat3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Crear Mapeo DACcat");
		logger.info("TC3 Crear Mapeo DACcat: Probar la validación de la existencia del campo Índice");
		logger.info("TC3 Crear Mapeo DACcat: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		mapeos.crear();
		CrearModificarMapeoDACcatProcess crearMapeo=new CrearModificarMapeoDACcatProcess();
		crearMapeo.confirmar();
		boolean hayErrorValidacion=crearMapeo.hayErrorValidacionRequeridoIndice();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC3 Crear Mapeo DACcat: Resultado obtenido:"+hayErrorValidacion);
		crearMapeo.volver();
		mapeos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearMapeoDACcat4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Crear Mapeo DACcat");
		logger.info("TC4 Crear Mapeo DACcat: Probar la validación del formato del campo Índice");
		logger.info("TC4 Crear Mapeo DACcat: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		mapeos.crear();
		CrearModificarMapeoDACcatProcess crearMapeo=new CrearModificarMapeoDACcatProcess();
		crearMapeo.escribirCampoIndice("!");
		crearMapeo.confirmar();
		boolean hayErrorValidacion=crearMapeo.hayErrorValidacionFormatoIndice();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC4 Crear Mapeo DACcat: Resultado obtenido:"+hayErrorValidacion);
		crearMapeo.volver();
		mapeos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearMapeoDACcat5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Crear Mapeo DACcat");
		logger.info("TC5 Crear Mapeo DACcat: Probar la validación del formato del campo Min Ruedas Doble");
		logger.info("TC5 Crear Mapeo DACcat: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		mapeos.crear();
		CrearModificarMapeoDACcatProcess crearMapeo=new CrearModificarMapeoDACcatProcess();
		crearMapeo.escribirCampoMinRuedaDoble("!");
		crearMapeo.confirmar();
		boolean hayErrorValidacion=crearMapeo.hayErrorValidacionFormatoMinRuedaDoble();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC5 Crear Mapeo DACcat: Resultado obtenido:"+hayErrorValidacion);
		crearMapeo.volver();
		mapeos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearMapeoDACcat6() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6 Crear Mapeo DACcat");
		logger.info("TC6 Crear Mapeo DACcat: Probar la validación del formato del campo Máx Ruedas Doble");
		logger.info("TC6 Crear Mapeo DACcat: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		mapeos.crear();
		CrearModificarMapeoDACcatProcess crearMapeo=new CrearModificarMapeoDACcatProcess();
		crearMapeo.escribirCampoMaxRuedaDoble("!");
		crearMapeo.confirmar();
		boolean hayErrorValidacion=crearMapeo.hayErrorValidacionFormatoMaxRuedaDoble();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC6 Crear Mapeo DACcat: Resultado obtenido:"+hayErrorValidacion);
		crearMapeo.volver();
		mapeos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearMapeoDACcat7() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC7 Crear Mapeo DACcat");
		logger.info("TC7 Crear Mapeo DACcat: Probar la validación del formato del campo Núm. Min. Ejes.");
		logger.info("TC7 Crear Mapeo DACcat: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		mapeos.crear();
		CrearModificarMapeoDACcatProcess crearMapeo=new CrearModificarMapeoDACcatProcess();
		crearMapeo.escribirCampoNumMinEjes("!");
		crearMapeo.confirmar();
		boolean hayErrorValidacion=crearMapeo.hayErrorValidacionFormatoNumMinEjes();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC7 Crear Mapeo DACcat: Resultado obtenido:"+hayErrorValidacion);
		crearMapeo.volver();
		mapeos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	
	@Test
	public void testCrearMapeoDACcat8() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC8 Crear Mapeo DACcat");
		logger.info("TC8 Crear Mapeo DACcat: Probar la validación del formato del campo Núm. Máx. Ejes.");
		logger.info("TC8 Crear Mapeo DACcat: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		mapeos.crear();
		CrearModificarMapeoDACcatProcess crearMapeo=new CrearModificarMapeoDACcatProcess();
		crearMapeo.escribirCampoNumMaxEjes("!");
		crearMapeo.confirmar();
		boolean hayErrorValidacion=crearMapeo.hayErrorValidacionFormatoNumMaxEjes();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC8 Crear Mapeo DACcat: Resultado obtenido:"+hayErrorValidacion);
		crearMapeo.volver();
		mapeos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearMapeoDACcat9() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC9 Crear Mapeo DACcat");
		logger.info("TC9 Crear Mapeo DACcat: Probar la validación del formato del campo Altura Mínima");
		logger.info("TC9 Crear Mapeo DACcat: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		mapeos.crear();
		CrearModificarMapeoDACcatProcess crearMapeo=new CrearModificarMapeoDACcatProcess();
		crearMapeo.escribirCampoAlturaMin("!");
		crearMapeo.confirmar();
		boolean hayErrorValidacion=crearMapeo.hayErrorValidacionFormatoAlturaMinima();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC9 Crear Mapeo DACcat: Resultado obtenido:"+hayErrorValidacion);
		crearMapeo.volver();
		mapeos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearMapeoDACcat10() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC10 Crear Mapeo DACcat");
		logger.info("TC10 Crear Mapeo DACcat: Probar la validación del formato del campo Altura Máxima");
		logger.info("TC10 Crear Mapeo DACcat: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		mapeos.crear();
		CrearModificarMapeoDACcatProcess crearMapeo=new CrearModificarMapeoDACcatProcess();
		crearMapeo.escribirCampoAlturaMax("!");
		crearMapeo.confirmar();
		boolean hayErrorValidacion=crearMapeo.hayErrorValidacionFormatoAlturaMaxima();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC10 Crear Mapeo DACcat: Resultado obtenido:"+hayErrorValidacion);
		crearMapeo.volver();
		mapeos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearMapeoDACcat11() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC11 Crear Mapeo DACcat");
		logger.info("TC11 Crear Mapeo DACcat: Probar la validación del formato del campo Anchura Mínima");
		logger.info("TC11 Crear Mapeo DACcat: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		mapeos.crear();
		CrearModificarMapeoDACcatProcess crearMapeo=new CrearModificarMapeoDACcatProcess();
		crearMapeo.escribirCampoAnchuraMin("!");
		crearMapeo.confirmar();
		boolean hayErrorValidacion=crearMapeo.hayErrorValidacionFormatoAnchuraMinima();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC11 Crear Mapeo DACcat: Resultado obtenido:"+hayErrorValidacion);
		crearMapeo.volver();
		mapeos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearMapeoDACcat12() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC12 Crear Mapeo DACcat");
		logger.info("TC12 Crear Mapeo DACcat: Probar la validación del formato del campo Anchura Máxima");
		logger.info("TC12 Crear Mapeo DACcat: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		mapeos.crear();
		CrearModificarMapeoDACcatProcess crearMapeo=new CrearModificarMapeoDACcatProcess();
		crearMapeo.escribirCampoAnchuraMax("!");
		crearMapeo.confirmar();
		boolean hayErrorValidacion=crearMapeo.hayErrorValidacionFormatoAnchuraMaxima();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC12 Crear Mapeo DACcat: Resultado obtenido:"+hayErrorValidacion);
		crearMapeo.volver();
		mapeos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testCrearMapeoDACcat13() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC13 Crear Mapeo DACcat");
		logger.info("TC13 Crear Mapeo DACcat: Crear un mapeo de indice existente");
		logger.info("TC13 Crear Mapeo DACcat: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		mapeos.crear();
		CrearModificarMapeoDACcatProcess crearMapeo=new CrearModificarMapeoDACcatProcess();
		String[] datosEntrada={"99","00: Descripcion bb","11: Descripcion aa","1","2","3","4","5","6","7","8","9","10"};
		crearMapeo.escribirCampoIndice(datosEntrada[0]);
		crearMapeo.seleccionarCampoConcesionaria(datosEntrada[1]);
		crearMapeo.seleccionarCampoCategoria(datosEntrada[2]);
		crearMapeo.escribirCampoMinRuedaDoble(datosEntrada[3]);
		crearMapeo.escribirCampoMaxRuedaDoble(datosEntrada[4]);
		crearMapeo.escribirCampoNumMinEjes(datosEntrada[5]);
		crearMapeo.escribirCampoNumMaxEjes(datosEntrada[6]);
		crearMapeo.escribirCampoDistanciaMinEjes(datosEntrada[7]);
		crearMapeo.escribirCampoDistanciaMaxEjes(datosEntrada[8]);
		crearMapeo.escribirCampoAlturaMin(datosEntrada[9]);
		crearMapeo.escribirCampoAlturaMax(datosEntrada[10]);
		crearMapeo.escribirCampoAnchuraMin(datosEntrada[11]);
		crearMapeo.escribirCampoAnchuraMax(datosEntrada[12]);
		crearMapeo.clicarRadioButtonBusSi();
		crearMapeo.clicarRadioButtonBusNo();
		crearMapeo.confirmar();
		boolean hayError=crearMapeo.hayMensajeError();
		boolean analisis=false;
		if (hayError) {
			ErrorMessage errormsgobt=crearMapeo.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_duplicidad);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC13 Crear Mapeo DACcat: No ha aparecido el mensaje de error '"+msg_error_duplicidad+"'");
			LoginTest.majors++;
		}
		logger.info("TC13 Crear Mapeo DACcat: Resultado obtenido:"+(hayError && analisis));
		crearMapeo.volver();
		assertTrue(hayError && analisis);
	}
}
