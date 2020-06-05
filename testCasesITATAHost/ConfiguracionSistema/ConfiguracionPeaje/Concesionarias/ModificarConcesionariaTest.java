package testCasesITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.ConcesionariasProcess;
import procesosITATAHost.ConfiguracionSistema.ConfiguracionPeaje.Concesionarias.CrearModificarConcesionariaProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import testsComunes.RutinasTestComunes;
import unidadesGraficas.ErrorMessage;

public class ModificarConcesionariaTest {

	final static String titulo="Modificación Concesionaria"; 
	final static String subtitulo="Configuración sistema   -   Configuración de peaje   -   Concesionarias, Plazas, Vías   -   Modificar"; 
	final static String[] botones= {"Confirmar","Volver"};
	final static String encabezado="Información concesionaria";
	final static String[] labels= {"ID","Nombre","Número fiscal"};
	final static String msg_error_noseleccionado="Ningún elemento seleccionado";
	static InitBOTest botest;
	private static Logger logger;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(ModificarConcesionariaTest.class);
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
	public void testModificarConcesionaria1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 Modificar Concesionaria");
		logger.info("TC1 Modificar Concesionaria: Probar el análisis sintáctico correcto");
		logger.info("TC1 Modificar Concesionaria: Resultado esperado=true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		concesionarias.seleccionarFila(1);
		concesionarias.modificar();
		CrearModificarConcesionariaProcess modificarConcesionaria=new CrearModificarConcesionariaProcess();
		boolean analisis=modificarConcesionaria.sintacticAnalysis(titulo,subtitulo, botones, encabezado, labels);
		if (analisis) {
			logger.info("TC1 Modificar Concesionaria: análisis sintáctico correcto");
		}
		else {
			logger.error("TC1 Modificar Concesionaria: análisis sintáctico incorrecto");
			LoginTest.minors++;
		}
		logger.info("TC1 Modificar Concesionaria: Resultado obtenido="+analisis);
		concesionarias.volver();
		assertTrue(analisis);
	}
	
	
	@Test
	public void testModificarConcesionaria2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 Modificar Concesionaria");
		logger.info("TC2 Modificar Concesionaria: Probar la validación del campo Nombre");
		logger.info("TC2 Modificar Concesionaria: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		concesionarias.seleccionarFila(1);
		concesionarias.modificar();
		CrearModificarConcesionariaProcess modificarConcesionaria=new CrearModificarConcesionariaProcess();
		modificarConcesionaria.borrarCampoNombre();
		modificarConcesionaria.confirmar();
		boolean hayErrorValidacion=modificarConcesionaria.hayErrorValidacionNombre();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC2 Modificar Concesionaria: Resultado obtenido:"+hayErrorValidacion);
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testModificarConcesionaria3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 Modificar Concesionaria");
		logger.info("TC3 Modificar Concesionaria: Probar la validación del campo Número Fiscal");
		logger.info("TC3 Modificar Concesionaria: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		concesionarias.seleccionarFila(1);
		concesionarias.modificar();
		CrearModificarConcesionariaProcess modificarConcesionaria=new CrearModificarConcesionariaProcess();
		modificarConcesionaria.borrarCampoNumeroFiscal();
		modificarConcesionaria.confirmar();
		boolean hayErrorValidacion=modificarConcesionaria.hayErrorValidacionNumeroFiscal();
		if (!hayErrorValidacion) {
			LoginTest.minors++;
		}
		logger.info("TC3 Modificar Concesionaria: Resultado obtenido:"+hayErrorValidacion);
		assertTrue(hayErrorValidacion);
	}
	
	@Test
	public void testModificarConcesionaria4() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC4 Modificar Concesionaria");
		logger.info("TC4 Modificar Concesionaria: Modificar una concesionaria");
		logger.info("TC4 Modificar Concesionaria: Resultado esperado: true (operación correcta, comprobación de aparición en tabla)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		String datosEntrada[]= {null, "Descripcion bb","Número fiscal bb"};
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		concesionarias.seleccionarFila(1);
		concesionarias.modificar();
		CrearModificarConcesionariaProcess crearConcesionaria=new CrearModificarConcesionariaProcess();
		datosEntrada[0]=crearConcesionaria.leerCampoID();
		crearConcesionaria.escribirCampoNombre(datosEntrada[1]);
		crearConcesionaria.escribirCampoNumeroFiscal(datosEntrada[2]);
		crearConcesionaria.confirmar();
		concesionarias.recargarTabla();
		String[] resultado=new String[3];
		String[] columnas=new String[1];
		columnas[0]="ID";
		String[] valores=new String[1];
		valores[0]="00";
		resultado=concesionarias.obtenerFilaTabla(columnas, valores);
		boolean iguales=RutinasTestComunes.assertArrayEquals(datosEntrada,resultado);
		if (!iguales) {
			LoginTest.majors++;
		}
		logger.info("TC4 Modificar Concesionaria: Resultado obtenido:"+iguales);
		concesionarias.volver();
		assertTrue(iguales);
	}
	
	@Test
	public void testModificarConcesionaria5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC5 Modificar Concesionaria");
		logger.info("TC5 Modificar Concesionaria: Editar una concesionaria y comprobar que la editada es la misma que la seleccionada");
		logger.info("TC5 Modificar Concesionaria: Resultado esperado: true (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		int num_fila=1;
		concesionarias.seleccionarFila(num_fila);
		String idTabla=concesionarias.obtenerValorColumna(num_fila, "ID");
		String nombreTabla=concesionarias.obtenerValorColumna(num_fila, "Nombre");
		String numerofiscalTabla=concesionarias.obtenerValorColumna(num_fila, "Número fiscal");
		concesionarias.modificar();
		CrearModificarConcesionariaProcess modificarConcesionaria=new CrearModificarConcesionariaProcess();
		String id2=modificarConcesionaria.leerCampoID();
		boolean idsIguales=idTabla.equals(id2);
		if (!idsIguales) {
			logger.error("TC5 Modificar Concesionaria: El campo parámetro de la tabla no coincide con el de la pantalla Modificar");
			LoginTest.minors++;
		}
		String nombre2=modificarConcesionaria.leerCampoNombre();
		boolean nombresIguales=nombreTabla.equals(nombre2);
		if (!nombresIguales) {
			logger.error("TC5 Modificar Concesionaria: El campo valor de la tabla no coincide con el de la pantalla Modificar");
			LoginTest.minors++;
		}
		String numfiscal2=modificarConcesionaria.leerCampoNumeroFiscal();
		boolean numfiscalesIguales=numerofiscalTabla.equals(numfiscal2);
		if (!numfiscalesIguales) {
			logger.error("TC5 Modificar Concesionaria: El campo valor de la tabla no coincide con el de la pantalla Modificar");
			LoginTest.minors++;
		}
		modificarConcesionaria.volver();
		logger.info("TC5 Modificar Concesionaria: Resultado obtenido:"+(idsIguales && nombresIguales && numfiscalesIguales));
		concesionarias.volver();
		assertTrue((idsIguales && nombresIguales && numfiscalesIguales));
	}
	
	@Test
	public void testModificarConcesionaria6() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC6 Modificar Concesionaria");
		logger.info("TC6 Modificar Concesionaria: Probar la modificación incorrecta sin seleccionar concesionaria");
		logger.info("TC6 Modificar Concesionaria: Resultado esperado: true (aparece mensaje de error)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Configuración de peaje","Concesionarias, Plazas, Vías");
		ConcesionariasProcess concesionarias=new ConcesionariasProcess();
		concesionarias.modificar();
		boolean analisis=false;
		boolean hayMsgError=concesionarias.hayMensajeError();
		if (hayMsgError) {
			ErrorMessage errormsgobt=concesionarias.mensajeError();
			analisis=errormsgobt.sintacticAnalysis(msg_error_noseleccionado);
			if (!analisis) {
				LoginTest.minors++;
			}
		}
		else {
			logger.error("TC6 Modificar Concesionaria: Debería haber aparecido el mensaje de error");
			LoginTest.minors++;
		}
		logger.info("TC6 Modificar Concesionaria: Resultado obtenido="+(hayMsgError && analisis));
		concesionarias.volver();
		assertTrue(hayMsgError && analisis);
	}
}
