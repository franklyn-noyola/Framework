package testCasesITATAHost.ConfiguracionSistema.Operadores.GestionOperadores;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.CrearOperadorProcess;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.GestionOperadoresProcess;
import procesosITATAHost.ConfiguracionSistema.Operadores.GestionOperadores.ModificarOperadorProcess;
import testCasesITATAHost.InitBOTest;
import testCasesITATAHost.LoginTest;
import unidadesGraficas.AlertPopup;


public class SuiteCRUDOperadoresTest {

	final static String alerta_confirmacion_borrado="¿Está seguro de que desea eliminar el operador?";
	static InitBOTest botest;
	private static Logger logger;
	String operador;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		botest=new InitBOTest();
		InitBOTest.setUpBeforeClass();
		botest.setUpBefore();
		botest.testInitBO();
		logger = Logger.getLogger(SuiteCRUDOperadoresTest.class);
		logger.debug("setUpBeforeClass-SuiteCRUDOperadoresTest");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		logger.debug("tearDownAfterClass-SuiteCRUDOperadoresTest");
		InitBOTest.tearDownAfterClass();
	}
	
	@After
	public void AfterTest() throws Exception {
		logger.info("-----------------------------------------------------------------");
	}

	@Test
	public void testCRUDGestionOperadores1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC1 CRUD Gestion Operadores");
		logger.info("TC1 CRUD Gestion Operadores: Probar crear-editar-modificar-editar-borrar-editar");
		testCrearOperador2();
		pausa();
		testVerOperador2();
		pausa();
		testEliminarOperador1();
		pausa();
		testGestionOperadores5();
		pausa();
		logger.info("Fin TC1 CRUD Gestion Operadores");
		pausa();
	}
	

	@Test
	public void testCRUDGestionOperadores2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC2 CRUD Gestion Operadores");
		logger.info("TC2 CRUD Gestion Operadores: Probar crear un operador y borrarlo dos veces seguidas");
		testCrearOperador2();
		pausa();
		testEliminarOperador1();
		pausa();
		testEliminarOperador3();
		pausa();
		logger.info("Fin TC2 CRUD Gestion Operadores");
		pausa();
	}
	

	@Test
	public void testCRUDGestionOperadores3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC3 CRUD Gestion Operadores");
		logger.info("TC3 CRUD Gestion Operadores: Probar crear el mismo operador dos veces seguidas");
		testCrearOperador2();
		pausa();
		testCrearOperador3();
		pausa();
		logger.info("Fin TC3 CRUD Gestion Operadores");
	}
	

	public void testCrearOperador2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC: Crear Operador: Creación del primer operador");
		logger.info("TC: Crear Operador: Resultado esperado=false (operación correcta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.crear();
		CrearOperadorProcess crearOperador=new CrearOperadorProcess();
		crearOperador.escribirCampoNombre("Pepe2");
		crearOperador.escribirCampoApellidos("Pérez2");
		crearOperador.escribirCampoDireccion("Diagonal 234, 3º 3ª");
		crearOperador.escribirCampoCP("08023");
		crearOperador.escribirCampoCiudad("Barcelona");
		crearOperador.escribirCampoEmail("pepe3@0x.com");
		crearOperador.escribirCampoPais("España");
		crearOperador.escribirCampoTelefono("6457693");
		crearOperador.escribirCampoContraseña("1234567890");
		crearOperador.escribirCampoRepitacontraseña("1234567890");
		crearOperador.seleccionarOpcionGrupoOP("01: Cobradores");
		crearOperador.confirmar();
		boolean hayMsgError=crearOperador.hayMensajeError();
		if (hayMsgError) {
			LoginTest.minors++;
		}
		gestionOperadores.recargarTabla();
		logger.info("TC: Crear Operador: Resultado obtenido="+hayMsgError);
		assertFalse(hayMsgError);
	}
	
	public void testCrearOperador3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC: Crear Operador: Creación del mismo operador");
		logger.info("TC: Crear Operador: Resultado esperado=true (operación incorrecta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.crear();
		CrearOperadorProcess crearOperador=new CrearOperadorProcess();
		crearOperador.escribirCampoNombre("Pepe2");
		crearOperador.escribirCampoApellidos("Pérez2");
		crearOperador.escribirCampoDireccion("Diagonal 234, 3º 3ª");
		crearOperador.escribirCampoCP("08023");
		crearOperador.escribirCampoCiudad("Barcelona");
		crearOperador.escribirCampoEmail("pepe3@0x.com");
		crearOperador.escribirCampoPais("España");
		crearOperador.escribirCampoTelefono("6457693");
		crearOperador.escribirCampoContraseña("1234567890");
		crearOperador.escribirCampoRepitacontraseña("1234567890");
		crearOperador.seleccionarOpcionGrupoOP("01: Cobradores");
		crearOperador.confirmar();
		boolean hayMsgError=crearOperador.hayMensajeError();
		if (hayMsgError) {
			logger.info("TC: Crear Operador: Resultado obtenido="+hayMsgError);
		}
		else {
			LoginTest.minors++;
			logger.error("TC: Crear Operador: El contenido del operador es el mismo pero no hay mensaje de error");
			logger.info("TC: Crear Operador: Resultado obtenido="+hayMsgError);
		}
		gestionOperadores.recargarTabla();
		assertFalse(hayMsgError);
	}

	
	public void testVerOperador2() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC Ver Operador");
		logger.info("TC Ver Operador: Probar la edición del último operador creado");
		logger.info("TC Ver Operador: Resultado esperado=true (operación correcta)");
		String[] datosEntrada={"Sr","Masculino","Pepe","Pérez","Diagonal 234, 3º 3ª","08023","Barcelona","pepe@0x.com",
				"España","6457698","01: Cobradores"};
		String[] datosSalida=new String[11];
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.pulsarBotonTablaUltimo();
		int num_filas=gestionOperadores.numFilasTabla();
		String operador=gestionOperadores.obtenerValorColumna(num_filas, "Operador");
		gestionOperadores.escribirOpcionOperador(operador);
		gestionOperadores.busqueda();
		if (gestionOperadores.numFilasTabla()!=1) {
			logger.error("TC Ver Operador: el número de filas obtenidas en la tabla tras la búsqueda es incorrecto");
			logger.info("TC Ver Operador: Resultado esperado=false");
			LoginTest.majors++;
			fail();
		}
		else {
			gestionOperadores.seleccionarFila(1);
			gestionOperadores.vermodificar();
			ModificarOperadorProcess verOperador=new ModificarOperadorProcess();
			verOperador.seleccionarOpcionTitulo(datosEntrada[0]);
			verOperador.seleccionarOpcionGenero(datosEntrada[1]);
		    verOperador.escribirCampoNombre(datosEntrada[2]);
		    verOperador.escribirCampoApellidos(datosEntrada[3]);
		    verOperador.escribirCampoDireccion(datosEntrada[4]);
		    verOperador.escribirCampoCP(datosEntrada[5]);
		    verOperador.escribirCampoCiudad(datosEntrada[6]);
		    verOperador.escribirCampoEmail(datosEntrada[7]);
		    verOperador.escribirCampoPais(datosEntrada[8]);
		    verOperador.escribirCampoTelefono(datosEntrada[9]);
		    verOperador.seleccionarOpcionGrupoOP(datosEntrada[10]);
		    verOperador.confirmar();
	   }
	   gestionOperadores.escribirOpcionOperador(operador);
	   gestionOperadores.busqueda();
	   int numFilasObt1=gestionOperadores.numFilasTabla();
	   int numFilasObt2=1;
	   boolean compararArrays=true;
	   boolean compararArrays2=true;
	   if (numFilasObt1!=1) {
			logger.error("TC Ver Operador: el número de filas obtenidas en la tabla tras la búsqueda no es 1, es "+numFilasObt1);
			LoginTest.majors++;
	   }
	   else {
		   gestionOperadores.seleccionarFila(1);
		   gestionOperadores.vermodificar();
		   ModificarOperadorProcess verOperador=new ModificarOperadorProcess();
		   datosSalida[0]=verOperador.leerOpcionTitulo();
		   datosSalida[1]=verOperador.leerOpcionGenero();
		   datosSalida[2]=verOperador.leerCampoNombre();
		   datosSalida[3]=verOperador.leerCampoApellidos();
		   datosSalida[4]=verOperador.leerCampoDireccion();
		   datosSalida[5]=verOperador.leerCampoCP();
		   datosSalida[6]=verOperador.leerCampoCiudad();
		   datosSalida[7]=verOperador.leerCampoEmail();
		   datosSalida[8]=verOperador.leerCampoPais();
		   datosSalida[9]=verOperador.leerCampoTelefono()+" ";
		   datosSalida[10]=verOperador.leerOpcionGrupoOP();
		   compararArrays=assertArrayEquals(datosEntrada,datosSalida);
	       if (!compararArrays) {
	    	   logger.error("TC Ver Operador: el operador editado no coincide con el esperado");
	    	   LoginTest.majors++;
	       }
	       else {
	    	   verOperador.volver();
	    	   gestionOperadores.escribirOpcionOperador(operador);
	    	   gestionOperadores.busqueda();
	    	   numFilasObt2=gestionOperadores.numFilasTabla();
	    	   if (numFilasObt2!=1) {
	    		   logger.error("TC Ver Operador: el número de filas obtenidas en la tabla tras la búsqueda no es 1, es "+numFilasObt2);
	    		   LoginTest.majors++;
	    	   }
	    	   else {
	    		   String filatabla[]=new String[gestionOperadores.numColumnasTabla()];
	    		   String datosesperados[]=new String[gestionOperadores.numColumnasTabla()];
	    		   filatabla=gestionOperadores.obtenerFilaTabla(1);
	    		   datosesperados[0]=operador;
	    		   datosesperados[1]=datosSalida[0];
	    		   datosesperados[2]=datosSalida[1];
	    		   datosesperados[3]=datosSalida[2];
	    		   datosesperados[4]=datosSalida[3];
	    		   datosesperados[5]=filatabla[5];
	    		   datosesperados[6]=filatabla[6];
	    		   datosesperados[7]=datosSalida[3];
	    		   compararArrays2=assertArrayEquals(datosesperados,filatabla);
	    		   if (!compararArrays2) {
		    	   		logger.error("TC Ver Operador: los datos mostrados en la tabla para el operador editado no coinciden");
		    	   		LoginTest.majors++;
	    		   }
	    	   }
	       }
	   }
	   logger.info("TC Ver Operador: Resultado obtenido="+((numFilasObt1!=1) && compararArrays && (numFilasObt2!=1) && compararArrays2));
       assertTrue((numFilasObt1!=1) && compararArrays && (numFilasObt2!=1) && compararArrays2);
	}
	
	public void testEliminarOperador1() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC Eliminar Operador");
		logger.info("TC Eliminar Operador: Probar la eliminación correcta de un operador");
		logger.info("TC Eliminar Operador: Resultado esperado: true (aparece correctamente la alerta)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.pulsarBotonTablaUltimo();
		int num_filas=gestionOperadores.numFilasTabla();
		operador=gestionOperadores.obtenerValorColumna(num_filas, "Operador");
		gestionOperadores.escribirOpcionOperador(operador);
		gestionOperadores.busqueda();
		if (gestionOperadores.numFilasTabla()!=1) {
			logger.error("TC Eliminar Operador: el número de filas obtenidas en la tabla tras la búsqueda es incorrecto");
			LoginTest.majors++;
			fail();
		}
		gestionOperadores.seleccionarFila(1);
		gestionOperadores.eliminar();
		boolean analisis=false;
		boolean hayAlerta=gestionOperadores.hayAlerta();
		if (hayAlerta) {
			AlertPopup alerta=gestionOperadores.alerta();
			analisis=alerta.sintacticAnalysis(alerta_confirmacion_borrado);
			if (!analisis) {
				LoginTest.minors++;
			}
			gestionOperadores.aceptarAlerta();
			gestionOperadores.recargarTabla();
		}
		else {
			logger.error("TC: Debería haber aparecido la alerta de confirmación de borrado");
			LoginTest.minors++;
		}
		logger.info("TC Eliminar Operador: Resultado obtenido: "+(hayAlerta|| analisis));
		assertTrue(hayAlerta && analisis);
	}
	
	public void testEliminarOperador3() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC Eliminar Operador");
		logger.info("TC Eliminar Operador: Probar la eliminación de un operador inexistente");
		logger.info("TC Eliminar Operador: Resultado esperado: true (no se encuentra el operador en la búsqueda)");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.escribirOpcionOperador(operador);
		gestionOperadores.busqueda();
		int num_filasobt=gestionOperadores.numFilasTabla();
		if (num_filasobt!=0) {
			logger.error("TC Eliminar Operador: el número de filas obtenidas en la tabla tras la búsqueda es incorrecto: "+num_filasobt);
			LoginTest.majors++;
		}
		logger.info("TC Eliminar Operador: Resultado obtenido: "+(num_filasobt==0));
		assertTrue(num_filasobt==0);
	}
	
	public void testGestionOperadores5() {
		logger.info("-----------------------------------------------------------------");
		logger.info("TC Gestion Operadores");
		logger.info("TC Gestion Operadores: Probar la edición de un elemento inexistente");
		logger.info("TC Gestion Operadores: Resultado esperado=true");
		botest.bo.seleccionarOpcionMenu("Configuración sistema","Operadores","Gestión de operadores");
		GestionOperadoresProcess gestionOperadores=new GestionOperadoresProcess();
		gestionOperadores.pulsarBotonTablaUltimo();
		int num_filas=gestionOperadores.numFilasTabla();
		String operador=gestionOperadores.obtenerValorColumna(num_filas, "Operador");
		gestionOperadores.escribirOpcionOperador(operador);
		gestionOperadores.busqueda();
		int num_filasobt=gestionOperadores.numFilasTabla();
		if (num_filasobt!=0) {
			logger.error("TC Gestion Operadores: el número de filas obtenidas en la tabla debería ser 0");
			LoginTest.majors++;
		}
		logger.info("TC Gestion Operadores: Resultado obtenido="+(num_filasobt==0));
		assertTrue(num_filasobt==0);
	}
	
	public boolean assertArrayEquals(Object[] datosEntrada,Object[] datosSalida) {
		boolean resultado=true;
		
		if (datosEntrada.length!=datosSalida.length) {
			logger.debug("this.assertArrayEquals-Las longitudes de los dos parámetros no son iguales.");
			return false;
		}
		for (int i=0; i<datosEntrada.length && resultado; i++) {
			if (!(datosEntrada[i].equals(datosSalida[i]))) {
				logger.error("El elemento ["+i+"] no coincide para ambos arrays: '"+datosEntrada[i]+"' y '"+datosSalida[i]+"'");
				resultado=false;
			}
		}
		return resultado;
	}
	
	public void pausa() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
