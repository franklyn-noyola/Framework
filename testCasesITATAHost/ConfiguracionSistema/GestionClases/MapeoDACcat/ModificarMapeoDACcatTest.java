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

public class ModificarMapeoDACcatTest {

	final static String titulo="Mapeo DAC-Cat: Modificación"; 
	final static String subtitulo="Configuración sistema   -   Gestión de clases   -   Mapeo DAC-Cat   -   Modificar"; 
	final static String[] botones= {"Confirmar","Volver"};
	final static String encabezado="Información de mapeo";
	final static String[] labels= {"Índice","Min Rueda doble","Max Rueda doble","Num. Mí. Ejes","Num. Máx. Ejes",
									"Distancia Mín. 1º y 2º eje","Distancia Máx. 1º y 2º eje","Altura mínima",
									"Altura máxima","Anchura mínima","Anchura máxima","Concesionaria","Categoría"};
	final static String[][] labelsradiobutton= {{"Bus","Sí","No","indiferente"},{"Remolques","Sí","No","indiferente"}};
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(ModificarMapeoDACcatTest.class);
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
	public void testModificarMapeoDACcat1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Modificar Mapeo DACcat");
		logger.info("TC1 Modificar Mapeo DACcat: Probar el análisis sintáctico correcto");
		logger.info("TC1 Modificar Mapeo DACcat: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		mapeos.seleccionarFila(1);
		mapeos.modificar();
		CrearModificarMapeoDACcatProcess modificarMapeo=new CrearModificarMapeoDACcatProcess();
		boolean analisis=modificarMapeo.sintacticAnalysis(titulo,subtitulo, botones, encabezado, labels, labelsradiobutton);
		if (analisis) {
			logger.info("TC1 Modificar Mapeo DACcat: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Modificar Mapeo DACcat: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Modificar Mapeo DACcat: Resultado obtenido="+analisis);
		modificarMapeo.volver();
		mapeos.volver();
		assertTrue(analisis);
	}
	
	
	@Test
	public void testModificarMapeoDACcat2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Modificar Mapeo DACcat");
		logger.info("TC2 Modificar Mapeo DACcat: Modificar un mapeo");
		logger.info("TC2 Modificar Mapeo DACcat: Resultado esperado: true (operación correcta, comprobación de aparición en tabla)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		String[] resultado=new String[mapeos.numColumnasTabla()];
		String[] datosSalida=new String[mapeos.numColumnasTabla()];
		mapeos.seleccionarUltimaFila();
		mapeos.modificar();
		CrearModificarMapeoDACcatProcess modificarMapeo=new CrearModificarMapeoDACcatProcess();
		String[] datosEntrada={"99","00: Descripcion bb","11: Descripcion aa","11","21","31","41","51","61","71","81","91","101"};
		modificarMapeo.seleccionarCampoConcesionaria(datosEntrada[1]);
		modificarMapeo.seleccionarCampoCategoria(datosEntrada[2]);
		modificarMapeo.escribirCampoMinRuedaDoble(datosEntrada[3]);
		modificarMapeo.escribirCampoMaxRuedaDoble(datosEntrada[4]);
		modificarMapeo.escribirCampoNumMinEjes(datosEntrada[5]);
		modificarMapeo.escribirCampoNumMaxEjes(datosEntrada[6]);
		modificarMapeo.escribirCampoDistanciaMinEjes(datosEntrada[7]);
		modificarMapeo.escribirCampoDistanciaMaxEjes(datosEntrada[8]);
		modificarMapeo.escribirCampoAlturaMin(datosEntrada[9]);
		modificarMapeo.escribirCampoAlturaMax(datosEntrada[10]);
		modificarMapeo.escribirCampoAnchuraMin(datosEntrada[11]);
		modificarMapeo.escribirCampoAnchuraMax(datosEntrada[12]);
		modificarMapeo.clicarRadioButtonBusNo();
		datosSalida[0]=modificarMapeo.leerCampoIndice();
		datosSalida[1]=modificarMapeo.leerCampoConcesionaria();
		if (datosSalida[1].equals("Todos")) {
			datosSalida[1]="*";
		}
		datosSalida[2]=modificarMapeo.leerCampoMinRuedaDoble();
		datosSalida[3]=modificarMapeo.leerCampoMaxRuedaDoble();
		String label=modificarMapeo.leerRadioButtonBus();
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
		datosSalida[5]=modificarMapeo.leerCampoNumMinEjes();
		datosSalida[6]=modificarMapeo.leerCampoNumMaxEjes();
		datosSalida[7]=modificarMapeo.leerCampoDistanciaMinEjes();
		datosSalida[8]=modificarMapeo.leerCampoDistanciaMaxEjes();
		label=modificarMapeo.leerRadioButtonRemolques();
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
		datosSalida[10]=modificarMapeo.leerCampoAlturaMin();
		datosSalida[11]=modificarMapeo.leerCampoAlturaMax();
		datosSalida[12]=modificarMapeo.leerCampAnchuraMin();
		datosSalida[13]=modificarMapeo.leerCampAnchuraMax();
		String categoria=modificarMapeo.leerCampoCategoria();
		datosSalida[14]=categoria.substring(0, categoria.indexOf(":"));
		for (int i=2; i<=13; i++) {
			if (datosSalida[i].equals("")) {
				datosSalida[i]="-";
			}
		}
		modificarMapeo.confirmar();
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
		logger.info("TC2 Modificar Mapeo DACcat: Resultado obtenido:"+iguales);
		mapeos.volver();
		assertTrue(iguales);
	}
	
	
	@Test
	public void testModificarMapeoDACcat3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Modificar Mapeo DACcat");
		logger.info("TC3 Modificar Mapeo DACcat: Probar la validación del formato del campo Min Ruedas Doble");
		logger.info("TC3 Modificar Mapeo DACcat: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		mapeos.seleccionarUltimaFila();
		mapeos.modificar();
		CrearModificarMapeoDACcatProcess modificarMapeo=new CrearModificarMapeoDACcatProcess();
		modificarMapeo.escribirCampoMinRuedaDoble("!");
		modificarMapeo.confirmar();
		boolean hayErrorValidacion=modificarMapeo.hayErrorValidacionFormatoMinRuedaDoble();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC3 Modificar Mapeo DACcat: Resultado obtenido:"+hayErrorValidacion);
		modificarMapeo.volver();
		mapeos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testModificarMapeoDACcat4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Modificar Mapeo DACcat");
		logger.info("TC4 Modificar Mapeo DACcat: Probar la validación del formato del campo Máx Ruedas Doble");
		logger.info("TC4 Modificar Mapeo DACcat: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		mapeos.seleccionarUltimaFila();
		mapeos.modificar();
		CrearModificarMapeoDACcatProcess modificarMapeo=new CrearModificarMapeoDACcatProcess();
		modificarMapeo.escribirCampoMaxRuedaDoble("!");
		modificarMapeo.confirmar();
		boolean hayErrorValidacion=modificarMapeo.hayErrorValidacionFormatoMaxRuedaDoble();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC4 Modificar Mapeo DACcat: Resultado obtenido:"+hayErrorValidacion);
		modificarMapeo.volver();
		mapeos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testModificarMapeoDACcat5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Modificar Mapeo DACcat");
		logger.info("TC5 Modificar Mapeo DACcat: Probar la validación del formato del campo Núm. Min. Ejes.");
		logger.info("TC5 Modificar Mapeo DACcat: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		mapeos.seleccionarUltimaFila();
		mapeos.modificar();;
		CrearModificarMapeoDACcatProcess modificarMapeo=new CrearModificarMapeoDACcatProcess();
		modificarMapeo.escribirCampoNumMinEjes("!");
		modificarMapeo.confirmar();
		boolean hayErrorValidacion=modificarMapeo.hayErrorValidacionFormatoNumMinEjes();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC5 Modificar Mapeo DACcat: Resultado obtenido:"+hayErrorValidacion);
		modificarMapeo.volver();
		mapeos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	
	@Test
	public void testModificarMapeoDACcat6() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6 Modificar Mapeo DACcat");
		logger.info("TC6 Modificar Mapeo DACcat: Probar la validación del formato del campo Núm. Máx. Ejes.");
		logger.info("TC6 Modificar Mapeo DACcat: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		mapeos.seleccionarUltimaFila();
		mapeos.modificar();
		CrearModificarMapeoDACcatProcess modificarMapeo=new CrearModificarMapeoDACcatProcess();
		modificarMapeo.escribirCampoNumMaxEjes("!");
		modificarMapeo.confirmar();
		boolean hayErrorValidacion=modificarMapeo.hayErrorValidacionFormatoNumMaxEjes();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC6 Modificar Mapeo DACcat: Resultado obtenido:"+hayErrorValidacion);
		modificarMapeo.volver();
		mapeos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testModificarMapeoDACcat7() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC7 Modificar Mapeo DACcat");
		logger.info("TC7 Modificar Mapeo DACcat: Probar la validación del formato del campo Altura Mínima");
		logger.info("TC7 Modificar Mapeo DACcat: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		mapeos.seleccionarUltimaFila();
		mapeos.modificar();
		CrearModificarMapeoDACcatProcess modificarMapeo=new CrearModificarMapeoDACcatProcess();
		modificarMapeo.escribirCampoAlturaMin("!");
		modificarMapeo.confirmar();
		boolean hayErrorValidacion=modificarMapeo.hayErrorValidacionFormatoAlturaMinima();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC7 Modificar Mapeo DACcat: Resultado obtenido:"+hayErrorValidacion);
		modificarMapeo.volver();
		mapeos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testModificarMapeoDACcat8() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC8 Modificar Mapeo DACcat");
		logger.info("TC8 Modificar Mapeo DACcat: Probar la validación del formato del campo Altura Máxima");
		logger.info("TC8 Modificar Mapeo DACcat: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		mapeos.seleccionarUltimaFila();
		mapeos.modificar();
		CrearModificarMapeoDACcatProcess modificarMapeo=new CrearModificarMapeoDACcatProcess();
		modificarMapeo.escribirCampoAlturaMax("!");
		modificarMapeo.confirmar();
		boolean hayErrorValidacion=modificarMapeo.hayErrorValidacionFormatoAlturaMaxima();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC8 Modificar Mapeo DACcat: Resultado obtenido:"+hayErrorValidacion);
		modificarMapeo.volver();
		mapeos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testModificarMapeoDACcat9() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC9 Modificar Mapeo DACcat");
		logger.info("TC9 Modificar Mapeo DACcat: Probar la validación del formato del campo Anchura Mínima");
		logger.info("TC9 Modificar Mapeo DACcat: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		mapeos.seleccionarUltimaFila();
		mapeos.modificar();
		CrearModificarMapeoDACcatProcess modificarMapeo=new CrearModificarMapeoDACcatProcess();
		modificarMapeo.escribirCampoAnchuraMin("!");
		modificarMapeo.confirmar();
		boolean hayErrorValidacion=modificarMapeo.hayErrorValidacionFormatoAnchuraMinima();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC9 Modificar Mapeo DACcat: Resultado obtenido:"+hayErrorValidacion);
		modificarMapeo.volver();
		mapeos.volver();
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testModificarMapeoDACcat10() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC10 Modificar Mapeo DACcat");
		logger.info("TC10 Modificar Mapeo DACcat: Probar la validación del formato del campo Anchura Máxima");
		logger.info("TC10 Modificar Mapeo DACcat: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Gestión de clases","Mapeo DAC-Cat");
		MapeoDACcatProcess mapeos=new MapeoDACcatProcess();
		mapeos.seleccionarUltimaFila();
		mapeos.modificar();
		CrearModificarMapeoDACcatProcess modificarMapeo=new CrearModificarMapeoDACcatProcess();
		modificarMapeo.escribirCampoAnchuraMax("!");
		modificarMapeo.confirmar();
		boolean hayErrorValidacion=modificarMapeo.hayErrorValidacionFormatoAnchuraMaxima();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC10 Modificar Mapeo DACcat: Resultado obtenido:"+hayErrorValidacion);
		modificarMapeo.volver();
		mapeos.volver();
		assertTrue(hayErrorValidacion);
	}
	
}
